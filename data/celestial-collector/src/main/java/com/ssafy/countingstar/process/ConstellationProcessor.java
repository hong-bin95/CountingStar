package com.ssafy.countingstar.process;

import java.io.IOException;
import java.util.List;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import com.ssafy.countingstar.model.dao.ConstellationDAO;
import com.ssafy.countingstar.model.dto.ConstellationDTO;
import com.ssafy.countingstar.data.raw.IAUConstellation;
import com.ssafy.countingstar.resource.Constant;
import com.ssafy.countingstar.util.IAUConstellationParser;

public class ConstellationProcessor {

	static SparkSession spark = SparkSession.builder().appName("Celestial1stProcessor").master("local[*]").getOrCreate();
	static ConstellationDAO constellationDAO = ConstellationDAO.getInstance();
	
	
	public void process(List<IAUConstellation> iauConstellation){
		Dataset<IAUConstellation> iauConstellationDataset = spark.createDataset(iauConstellation, Encoders.bean(IAUConstellation.class));
		iauConstellationDataset
			.map((MapFunction<IAUConstellation, ConstellationDTO>) x-> new ConstellationDTO(x.getSno(), x.getConstellation(), null), Encoders.bean(ConstellationDTO.class))
			.foreach(x->{
				constellationDAO.addConstellation(x);
			});
	}
	
	public static void main(String[] args) throws IOException {
		ConstellationProcessor processor = new ConstellationProcessor();
		processor.process(
			IAUConstellationParser.parseCSV(Constant.iauConstellationSourcePath)
		);
	}

}
