package vcc.project01.readavro;

import java.io.File;
import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.mapred.FsInput;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class ReadAvroHDFS {
	public static void main(String[] args) throws IOException {
		
		File schemaPath = new File("logObject.avsc");
		Schema schema = new Schema.Parser().parse(schemaPath);
		String filePath = "hdfs://localhost:9000/topics/rest_test/partition=0/rest_test+0+0000000000+0000000999.avro";
		Configuration conf = new Configuration();
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
