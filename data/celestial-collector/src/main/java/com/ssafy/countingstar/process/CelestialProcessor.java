package com.ssafy.countingstar.process;

import java.io.IOException;
import java.util.List;

import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.substring;

import com.ssafy.countingstar.data.Celestial;
import com.ssafy.countingstar.data.raw.IAUConstellation;
import com.ssafy.countingstar.data.raw.IAUStar;
import com.ssafy.countingstar.data.raw.YBSCStar;
import com.ssafy.countingstar.model.dto.Star;
import com.ssafy.countingstar.resource.Constant;
import com.ssafy.countingstar.util.IAUConstellationParser;
import com.ssafy.countingstar.util.IAUStarParser;
import com.ssafy.countingstar.util.YBSCStarParser;

import scala.Tuple2;

// 원천데이터로부터 정형화된 Celestial을 가공하여 내보낸다.
public class CelestialProcessor {
	static SparkSession spark = SparkSession.builder().appName("Celestial1stProcessor").master("local[*]").getOrCreate();
	
	public void process(List<YBSCStar> ybscStarList, List<IAUStar> iauStarList, List<IAUConstellation> iauConstellation){
		Dataset<YBSCStar> ybscStarSet = spark.createDataset(ybscStarList, Encoders.bean(YBSCStar.class));
		Dataset<IAUStar> iauStarSet = spark.createDataset(iauStarList, Encoders.bean(IAUStar.class));
		Dataset<IAUConstellation> iauConstellationSet = spark.createDataset(iauConstellation, Encoders.bean(IAUConstellation.class));
	
		//iauStarSet.show();
		Dataset<Star> a = iauStarSet
			.joinWith(iauConstellationSet, iauStarSet.col("Con").equalTo(iauConstellationSet.col("iauAbbreviation")))
			.map(
					(MapFunction<Tuple2<IAUStar,IAUConstellation>,Star>)
						x-> new Star(x._1.getNameDiacritics(), 0, x._2.getSno()), Encoders.bean(Star.class));
			
		List<Long> countedHDs = iauStarSet.select("hd").as(Encoders.LONG()).collectAsList();
		
		
		Dataset<Row> ybseStartSet2 = ybscStarSet.filter((FilterFunction<YBSCStar>)x->!countedHDs.contains(x.getHD()))
				.withColumn("Con", substring(ybscStarSet.col("Name"), -3, 3));
	
		
		//ybscStarSet.show();
		Dataset<Star> b = 
				ybseStartSet2
				.joinWith(iauConstellationSet, ybseStartSet2.col("Con").equalTo(iauConstellationSet.col("iauAbbreviation")), "left")
				.map(
					(MapFunction<Tuple2<Row,IAUConstellation>, Star>)
						x-> new Star("HD ".concat(String.valueOf((Long)x._1.getAs("HD"))),0 , x._2 != null? x._2.getSno() : null), Encoders.bean(Star.class)
					);
		
		Dataset<Star> c = a.union(b);
		c.show();
	}
	
	public static void main(String[] args) throws IOException {
		CelestialProcessor processor = new CelestialProcessor();
		processor.process(
				YBSCStarParser.parseCatalog(Constant.ybscStarSourcePath),
				IAUStarParser.parse(Constant.iauStarSourcePath),
				IAUConstellationParser.parseCSV(Constant.iauConstellationSourcePath)
			);
	}

}
