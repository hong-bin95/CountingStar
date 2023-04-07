package com.ssafy.countingstar.process;

import java.io.IOException;
import java.util.List;

import org.apache.spark.SparkConf;
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
import com.ssafy.countingstar.model.dao.StarDAO;
import com.ssafy.countingstar.model.dto.StarDTO;
import com.ssafy.countingstar.resource.CassandraSecret;
import com.ssafy.countingstar.resource.Constant;
import com.ssafy.countingstar.util.IAUConstellationParser;
import com.ssafy.countingstar.util.IAUStarParser;
import com.ssafy.countingstar.util.YBSCStarParser;

import com.datastax.spark.connector.japi.CassandraJavaUtil;

import org.apache.spark.sql.expressions.Window;
import org.apache.spark.sql.expressions.WindowSpec;
import org.apache.spark.sql.functions;

import scala.Tuple2;

public class CelestialProcessor {
	
	static SparkConf conf = new SparkConf();
	
	static {
		conf
		.setAppName("Celestial-Constellation-Processor")
        .setMaster("local[*]")
        .set("spark.cassandra.connection.host", CassandraSecret.host)
        .set("spark.cassandra.auth.username", CassandraSecret.username)
        .set("spark.cassandra.auth.password", CassandraSecret.password);
	}
	
	static SparkSession spark = SparkSession.builder().config(conf).getOrCreate();
	static StarDAO starDAO = StarDAO.getInstance();
	
	static Long hdProcess(Long hd) {
		if(hd != null && hd < 0) return null;
		return hd;
	}
	
	static String nameProcess(Long hd) {
		if(hd == null) return "Unknown";
		return ("HD "+hd.longValue());
	}
	
	static double hmsRaToDegRa(short h, short m, double s) {
		double result = 15.0*(h + (m/60.0) + (s/3600.0));
		return result;
	}
	
	static double degarcmarcsDecToDegDec(byte de, short deg, short arcmin, short arcsec) {
		double result = deg + arcmin/60.0 + arcsec/3600.0;
		if(de == '-') result = -result;
		return result;
	}
	
	public void process(List<YBSCStar> ybscStarList, List<IAUStar> iauStarList, List<IAUConstellation> iauConstellation){
		Dataset<YBSCStar> ybscStarSet = spark.createDataset(ybscStarList, Encoders.bean(YBSCStar.class));
		Dataset<IAUStar> iauStarSet = spark.createDataset(iauStarList, Encoders.bean(IAUStar.class));
		Dataset<IAUConstellation> iauConstellationSet = spark.createDataset(iauConstellation, Encoders.bean(IAUConstellation.class));
	
		//iauStarSet.show();
		Dataset<Celestial> a = iauStarSet
			.joinWith(iauConstellationSet, iauStarSet.col("Con").equalTo(iauConstellationSet.col("iauAbbreviation")))
			.map(
					(MapFunction<Tuple2<IAUStar,IAUConstellation>,Celestial>)
						x-> new Celestial(
								null,
								null,
								x._1.getNameDiacritics(),
								hdProcess(x._1.getHd()), 
								x._1.getRaJ2000().doubleValue(),
								x._1.getDecJ2000().doubleValue(),
								x._1.getMag()==null?10.5:x._1.getMag().doubleValue(),
								x._2.getSno()
						), Encoders.bean(Celestial.class)
				); 
		
		
		List<Long> countedHDs = iauStarSet.select("hd").as(Encoders.LONG()).collectAsList();
		
		
		Dataset<Row> ybseStartSet2 = ybscStarSet.filter((FilterFunction<YBSCStar>)x->!countedHDs.contains(x.getHD()))
				.withColumn("Con", substring(ybscStarSet.col("Name"), -3, 3));
	
		
		Dataset<Celestial> b = 
				ybseStartSet2
				.joinWith(iauConstellationSet, ybseStartSet2.col("Con").equalTo(iauConstellationSet.col("iauAbbreviation")), "left")
				.map(
					(MapFunction<Tuple2<Row,IAUConstellation>, Celestial>)
						x-> new Celestial(
								null,
								null,
								nameProcess((Long)x._1.getAs("HD")),
								hdProcess((Long)x._1.getAs("HD")), 
								hmsRaToDegRa(
										((Short)x._1.getAs("RAh")).shortValue(),
										((Short)x._1.getAs("RAm")).shortValue(),
										((Double)x._1.getAs("RAs")).doubleValue()
										),
								degarcmarcsDecToDegDec(
										((Byte)x._1.getAs("DE_")).byteValue(),
										((Short)x._1.getAs("DEd")).shortValue(),
										((Short)x._1.getAs("DEm")).shortValue(),
										((Short)x._1.getAs("DEs")).shortValue()
										),
								((Double)x._1.getAs("vmag")).doubleValue(),
								x._2==null?null:x._2.getSno()
							)
						, Encoders.bean(Celestial.class)
					);
		
		WindowSpec window = Window.orderBy(functions.rand(123456L));
		Dataset<Celestial> c = a.union(b).withColumn("id", functions.row_number().over(window))
				.map(
					      (MapFunction<Row, Celestial>) row -> {
					    	  Celestial star = new Celestial();
					    	  star.setId(row.getAs("id"));
					          star.setStarId(row.getAs("id"));
					          star.setName(row.getAs("name"));
					          star.setHd(row.getAs("hd"));
					          star.setRightAscension(row.getAs("rightAscension"));
					          star.setDeclination(row.getAs("declination"));
					          star.setVisualMagnitude(row.getAs("visualMagnitude"));
					          star.setConstellationId(row.getAs("constellationId"));
					          return star;
					        },
					      Encoders.bean(Celestial.class));
		
		c
		.filter((FilterFunction<Celestial>)x->x.getRightAscension() > 50.0)
		.show();		

		CassandraJavaUtil.javaFunctions(c.javaRDD())
			.writerBuilder(CassandraSecret.keyspace, "celestial", CassandraJavaUtil.mapToRow(Celestial.class))
			.saveToCassandra();
		
		c.foreach(x->{
			starDAO.addStar(new StarDTO(x.getStarId().intValue(), x.getName(), 2, x.getConstellationId()));
		});
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
