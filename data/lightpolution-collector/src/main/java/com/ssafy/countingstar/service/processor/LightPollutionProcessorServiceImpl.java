package com.ssafy.countingstar.service.processor;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.data.CollectedDataKey;
import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbData;

@Service
public class LightPollutionProcessorServiceImpl implements LightPollutionProcessorService{
	
	private transient SparkSession spark;
	
	private final int skipI;
	private final int skipJ;
	
	final static transient ZoneId utc = ZoneId.of("UTC");
	final static transient ZoneId kst = ZoneId.of("Asia/Seoul");
	
    private float maxLat;
    private float minLat;
    private float maxLng;
    private float minLng;
	
	public LightPollutionProcessorServiceImpl(
			@Value("${spark.master.host}") String sparkMaster,
			@Value("${lightpollution-process-r-skip}") int skipI,
			@Value("${lightpollution-process-c-skip}") int skipJ,
    		@Value("${lightpollution-limit-max-lat}") float maxLat,
    		@Value("${lightpollution-limit-min-lat}") float minLat,
    		@Value("${lightpollution-limit-max-lng}") float maxLng,
    		@Value("${lightpollution-limit-min-lng}") float minLng
		) {
		SparkConf conf = new SparkConf().setAppName("Light-Pollution-Processor").setMaster(sparkMaster);
		this.spark = SparkSession.builder().config(conf).getOrCreate();
		this.skipI = skipI;
		this.skipJ = skipJ;
		
    	this.maxLat = maxLat;
    	this.minLat = minLat;
    	this.maxLng = maxLng;
    	this.minLng = minLng;
		
	}
	
	static Timestamp dateStringToTimestamp(String date, String time) {
		String datetimeStr = date + " " + time;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Timestamp timestamp;
		try {
			timestamp = new Timestamp(dateFormat.parse(datetimeStr).getTime());
		} catch (ParseException e) {
			timestamp = null;
		}
        return timestamp;
	}
	
	
	static LightPollution interpolationByCorner(Unit1 unit) {
		// 1. 코너 정보를 통해 각 코너를 잇는 선분에서 idx : size-idx 로 내분하는 점을 찾는다.
		// 내분점은 그 점을 구하는 과정에서 벡터성분으로 나누어 계산할 수 있기 때문에, 각 백터 성분을 따로 계산한다.
		// 2. 해당 내분점의 무게중심을 구한다. 해당 무게중심 점이 바로 보정된 위치값이 된다.
		
		float[] lats = unit.cornerLat;
		float[] lngs = unit.cornerLng;
		
		float lpi = unit.i + 0.5f;
		float lpj = unit.j + 0.5f;
		
		float r = unit.r;
		float c = unit.c;
		
		float lat1 = lats[0];
		float lat2 = lats[3];
		float lat3 = lats[1];
		float lat4 = lats[2];
		float lng1 = lngs[0];
		float lng2 = lngs[1];
		float lng3 = lngs[2];
		float lng4 = lngs[3];
				
		float lat = (getIDP(lat1, lat2, lpi, r) + getIDP(lat3, lat4, lpi, r)) / 2;
		float lng = (getIDP(lng1, lng2, lpj, c) + getIDP(lng3, lng4, lpj, c)) / 2;
		
		return new LightPollution(new CollectedDataKey(unit.date, unit.hour, lat, lng), unit.rad);
		
	}
	
	// 전체 length중 portion만큼의 비율만큼 앞으로 간 점.
	static float getIDP(float point1, float point2, float portion, float length) {
		if(point1 > point2) {
			float tmp = point1;
			point1 = point2;
			point2 = tmp;
		}
		return point1 + (point2 - point1) * portion / length;
		
	}

	@Override
	public Iterable<LightPollution> process(List<SuomiNppViirsDnbData> rawData) {
		
		final int skipI = this.skipI;
		final int skipJ = this.skipJ;
		
		final float maxLat = this.maxLat;
		final float minLat = this.minLat;
		final float maxLng = this.maxLng;
		final float minLng = this.minLng;
		
		/// 간단한 연산은 서비스에서 처리해서 넘겨준다.
		List<Unit0> before = rawData.stream()
			.filter(x->x.getDayNightFlag() == 0)
			.map(x->{
							LocalDate ld = LocalDate.parse(x.getRangeEndingDate());
							LocalTime lt = LocalTime.of(Integer.parseInt(x.getRangeEndingTime().substring(0,2)),0);
							ZonedDateTime zdt = ZonedDateTime.of(ld, lt, utc).withZoneSameInstant(kst);
							return new Unit0(
								x.getgRingPointLatitude(),
								x.getgRingPointLongitude(),
								zdt.toLocalDate(),
								zdt.getHour(),
								x.getRadiance()
							);
				
					}
				).collect(Collectors.toList());
		
		
		
		
		Dataset<Unit0> suomiDataset = spark.createDataset(before, Encoders.bean(Unit0.class));
		

			
		return 
			suomiDataset
			.flatMap((FlatMapFunction<Unit0,Unit1>)x->{
					int r = x.rad.length;
					int c = x.rad[0].length;
					float[][] rad = x.rad;
					List<Unit1> list = new ArrayList<Unit1>(r*c);
					for(int i=0; i<r; i+=skipI) {
						for(int j=0; j<c; j+=skipJ) {
							Unit1 u = 
									new Unit1(
											x.cornerLat, 
											x.cornerLng, 
											x.date,
											x.hour,
											i,
											j,
											r,
											c,
											rad[i][j]
									);
							list.add(u);
						}
					}
					return list.iterator();
				}
				,Encoders.bean(Unit1.class)
			)
			// 휘도는 음수가 불가능. 음수 값은 예외 처리한다.
			.filter((FilterFunction<Unit1>)x->x.rad >= 0)
			.map((MapFunction<Unit1,LightPollution>)x->interpolationByCorner(x), Encoders.bean(LightPollution.class))
			.filter((FilterFunction<LightPollution>)x->x.getKey().getLat() >= minLat)
			.filter((FilterFunction<LightPollution>)x->x.getKey().getLat() <= maxLat)
			.filter((FilterFunction<LightPollution>)x->x.getKey().getLng() >= minLng)
			.filter((FilterFunction<LightPollution>)x->x.getKey().getLng() <= maxLng)
			.collectAsList();
	}

}
