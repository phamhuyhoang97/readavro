package vcc.project01.readavro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.mapred.FsInput;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;


public class ReadAvroSpark {
	public static void main(String[] args) throws AnalysisException, IllegalArgumentException, IOException{
		
		SparkSession spark = SparkSession
				  .builder()
				  .appName("Java Spark SQL basic example")
				  .config("spark.some.config.option", "some-value")
				  .config("spark.master", "local")
				  .getOrCreate();
		
		File schemaPath = new File("logObject.avsc");
		Schema schema = new Schema.Parser().parse(schemaPath);
		
		String folder = "hdfs://localhost:9000/topics/rest_test/partition=0/";
		String file = "*";
		String filePath = folder + file;
		
		Dataset<Row> usersDF = spark.read()
				.format("avro")
				.option("avroSchema", schema.toString())
				.load(filePath);
		usersDF.createOrReplaceTempView("logs");
		Dataset<Row> temDF = spark.sql("SELECT count(d) FROM logs WHERE d = 'null'");
		temDF.show();
		
		spark.stop();
	}
}