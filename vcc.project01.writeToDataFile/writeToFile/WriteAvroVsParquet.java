package writeToFile;

import java.sql.Timestamp;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class WriteAvroVsParquet {
	public static void main(String[] args) {
		SparkSession spark = SparkSession
				  .builder()
				  .appName("Java Spark SQL basic example")
				  .config("spark.some.config.option", "some-value")
				  .config("spark.master", "local")
				  .getOrCreate();
		
		String filePath = "sample_text/*";
		
		JavaRDD<LogObject> peopleRDD = spark.read()
				  .textFile(filePath)
				  .javaRDD()
				  .map(line -> {
				    String[] parts = line.split("\t");
				    LogObject logObject = new LogObject();
				    logObject.setTimeCreate(Timestamp.valueOf(parts[0]));
				    logObject.setCookieCreate(Timestamp.valueOf(parts[1]));
				    logObject.setBrowserCode(Integer.parseInt(parts[2].trim()));
				    logObject.setBrowserVer(parts[3]);
				    logObject.setOsCode(Integer.parseInt(parts[4].trim()));
				    logObject.setOsVer(parts[5]);
				    logObject.setIp(Long.parseLong(parts[6].trim()));
				    logObject.setLocId(Integer.parseInt(parts[7].trim()));
				    logObject.setDomain(parts[8]);
				    logObject.setSiteId(Integer.parseInt(parts[9].trim()));
				    logObject.setcId(Integer.parseInt(parts[10].trim()));
				    logObject.setPath(parts[11]);
				    logObject.setReferer(parts[12]);
				    logObject.setGuid(Long.parseLong(parts[13]));
				    logObject.setFlashVersion(parts[14]);
				    logObject.setJre(parts[15]);
				    logObject.setSr(parts[16]);
				    logObject.setSc(parts[17]);
				    logObject.setGeographic(Integer.parseInt(parts[18].trim()));
				    return logObject;
				  });
		
		Dataset<Row> logDF = spark.createDataFrame(peopleRDD, LogObject.class);

		// Write to file Parquet and Calculator time
		long startTimePar = System.nanoTime();
		logDF.write().format("parquet").save("logData.parquet");		
		long endTimePar  = System.nanoTime();
		long totalTimePar = endTimePar - startTimePar;
		System.out.println("Time for writing to Parquet File: " + totalTimePar/1000000000.0);
		
		// Write to file Avro and Calculator time
		long startTimeAvro = System.nanoTime();
		logDF.write().format("avro").save("logData.avro");	
		long endTimeAvro   = System.nanoTime();
		long totalTimeAvro = endTimeAvro - startTimeAvro;
		System.out.println("Time for writing to Avro File: " + totalTimeAvro/1000000000.0);
	}
}
