package com.ssafy.countingstar.process;

import java.io.IOException;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import com.ssafy.countingstar.model.dao.ConstellationDAO;
import com.ssafy.countingstar.model.dao.StarDAO;
import com.ssafy.countingstar.model.dto.ConstellationDTO;
import com.datastax.spark.connector.japi.CassandraJavaUtil;
import com.ssafy.countingstar.data.Constellation;
import com.ssafy.countingstar.data.raw.IAUConstellation;
import com.ssafy.countingstar.resource.CassandraSecret;
import com.ssafy.countingstar.resource.Constant;
import com.ssafy.countingstar.util.IAUConstellationParser;

public class ConstellationProcessor {
	


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
	static ConstellationDAO constellationDAO = ConstellationDAO.getInstance();
	
	
	public void process(List<IAUConstellation> iauConstellation){
		Dataset<IAUConstellation> iauConstellationDataset = spark.createDataset(iauConstellation, Encoders.bean(IAUConstellation.class));
		Dataset<ConstellationDTO> constallationDTOs = iauConstellationDataset
			.map((MapFunction<IAUConstellation, ConstellationDTO>) x-> new ConstellationDTO(x.getSno(), x.getConstellation(), null), Encoders.bean(ConstellationDTO.class));
		Dataset<Constellation> constallations = iauConstellationDataset
				.map((MapFunction<IAUConstellation, Constellation>) x-> new Constellation(x.getSno(), x.getConstellation(), x.getIauAbbreviation(), x.getFamily()), Encoders.bean(Constellation.class));
			
			constallationDTOs.foreach(x->{
				constellationDAO.addConstellation(x);
			})	;
		
		CassandraJavaUtil.javaFunctions(constallations.javaRDD())
        	.writerBuilder(CassandraSecret.keyspace, "constellation", CassandraJavaUtil.mapToRow(Constellation.class))
        	.saveToCassandra();
	}
	
	public static void main(String[] args) throws IOException {
		ConstellationProcessor processor = new ConstellationProcessor();
		processor.process(
			IAUConstellationParser.parseCSV(Constant.iauConstellationSourcePath)
		);
	}

}
