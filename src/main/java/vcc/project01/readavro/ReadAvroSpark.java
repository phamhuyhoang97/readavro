package vcc.project01.readavro;

import java.io.IOException;
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
		
		String filePath = "hdfs://localhost:9000/topics/rest_test/partition=0/rest_test+0+0000011852+0000012851.avro";
		Configuration conf = new Configuration();
		FsInput avroData = new FsInput(new Path(filePath), conf);
		
		Dataset<Row> usersDF = spark.read().format("avro").load(filePath);
		usersDF.createOrReplaceTempView("logs");
		Dataset<Row> temDF = spark.sql("SELECT count(b) FROM logs WHERE b = 'a'");
		temDF.show();
		
		spark.stop();
	}
}