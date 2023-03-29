package com.ssafy.countingstar.service.processor;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ssafy.countingstar.data.LightPollution;
import com.ssafy.countingstar.data.raw.SuomiNppViirsDnbData;

@Service
public class LightPollutionProcessorServiceImpl implements LightPollutionProcessorService{
	
	private SparkSession spark;
	
	public LightPollutionProcessorServiceImpl(@Value("${spark.master.host}") String sparkMaster) {
		SparkConf conf = new SparkConf().setAppName("Light-Pollution-Processor").setMaster(sparkMaster);
		this.spark = SparkSession.builder().config(conf).getOrCreate();
	}
	
	static class Unit0{
		float[] cornerLat;
		float[] cornerLng;
		Timestamp timestamp;
		float[][] rad;
		
		public Unit0(float[] cornerLat, float[] cornerLng, Timestamp timestamp, float[][] rad) {
			this.cornerLat = cornerLat;
			this.cornerLng = cornerLng;
			this.timestamp = timestamp;
			this.rad = rad;
		}
	}
	
	static class Unit1{
		float[] cornerLat;
		float[] cornerLng;
		Timestamp timestamp;
		int i;
		int j;
		int r;
		int c;
		float rad;
		
		public Unit1(float[] cornerLat, float[] cornerLng, Timestamp timestamp, int i, int j, int r, int c, float rad) {
			this.cornerLat = cornerLat;
			this.cornerLng = cornerLng;
			this.timestamp = timestamp;
			this.i = i;
			this.j = j;
			this.r = r;
			this.c = c;
			this.rad = rad;
		}
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
		
		float lat = 0f;
		float lng = 0f;
		
		float lpi = unit.i + 0.5f;
		float lpj = unit.j + 0.5f;
		
		float r = unit.r;
		float c = unit.c;
		
		
		lat += getIDP(lats[0],lats[3],lpi,r);
		lat += getIDP(lats[1],lats[2],lpi,r);
		lat /= 2;
		
		lng += getIDP(lngs[0],lngs[1],lpj,c);
		lng += getIDP(lngs[2],lngs[3],lpj,c);
		lng /= 2;
		
		return new LightPollution(lat, lng, unit.timestamp, unit.rad);
		
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
		
		Dataset<SuomiNppViirsDnbData> suomiDataset = spark.createDataset(rawData, Encoders.bean(SuomiNppViirsDnbData.class));
		
		return suomiDataset
			.filter((FilterFunction<SuomiNppViirsDnbData>)x->x.getDayNightFlag() == 0)
			.map(
					(MapFunction<SuomiNppViirsDnbData, Unit0>)
					x->new Unit0(
							x.getgRingPointLatitude(),
							x.getgRingPointLongitude(),
							dateStringToTimestamp(x.getRangeEndingDate(), x.getRangeEndingTime()),
							x.getRadiance()
					), Encoders.bean(Unit0.class)
			)
			.flatMap((FlatMapFunction<Unit0,Unit1>)x->{
					int r = x.rad.length;
					int c = x.rad[0].length;
					float[][] rad = x.rad;
					List<Unit1> list = new ArrayList<Unit1>(r*c);
					for(int i=0; i<r; i++) {
						for(int j=0; j<c; j++) {
							Unit1 u = 
									new Unit1(
											x.cornerLat, 
											x.cornerLng, 
											x.timestamp,
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
			).map((MapFunction<Unit1,LightPollution>)x->interpolationByCorner(x), Encoders.bean(LightPollution.class))
			.collectAsList();
	}

}
