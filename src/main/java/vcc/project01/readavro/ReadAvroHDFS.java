package vcc.project01.readavro;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.FsInput;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ReadAvroHDFS {
	public static void main(String[] args) throws IOException {
		
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
		String file = "rest_test+0+0000000000+0000000999.avro";
		String filePath = folder + file;
		FsInput avroData = new FsInput(new Path(filePath), conf);
		
		ReadAvoFile(avroData, schema, schema);
	}
	
	public static void ReadAvoFile(FsInput avroData, Schema writeSchema, Schema readSchema) throws IOException {
		DataFileReader dataFileReader = new DataFileReader<GenericRecord>(avroData, 
				new GenericDatumReader<GenericRecord>(writeSchema, readSchema));
		
		while (dataFileReader.hasNext()) {
            System.out.println(dataFileReader.next());
        }
	}
}
