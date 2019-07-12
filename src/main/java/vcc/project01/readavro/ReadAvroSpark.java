package vcc.project01.readavro;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.avro.Schema;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
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
		
		String fileAvsc = "logObject.avsc";
		String folderAvsc = "hdfs://localhost:9000/topics/rest_test/";
		String filePathAvsc = folderAvsc + fileAvsc;
		Configuration conf = new Configuration();
		conf.addResource("/usr/local/hadoop/etc/hadoop/core-site.xml");
		conf.addResource("/usr/local/hadoop/etc/hadoop/hdfs-site.xml");
		FileSystem fs = null;
		try {
			fs = FileSystem.get(new URI("hdfs://localhost:9000"), conf);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FSDataInputStream in = fs.open(new Path(filePathAvsc));
		Schema schema = new Schema.Parser().parse(in);
		
		String folder = "hdfs://localhost:9000/topics/rest_test/partition=0/";
		String file = "*";
		String filePath = folder + file;
		
		Dataset<Row> usersDF = spark.read()
				.format("avro")
				.option("avroSchema", schema.toString())
				.load(filePath);
		usersDF.createOrReplaceTempView("logs");
		Dataset<Row> temDF = spark.sql("SELECT * FROM logs WHERE b = 'a'");
		temDF.show(100, false);
		
		spark.stop();
	}
}