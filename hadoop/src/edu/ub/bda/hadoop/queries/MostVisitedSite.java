package edu.ub.bda.hadoop.queries;

import edu.ub.bda.hadoop.queries.utils.ContextDate;
import edu.ub.bda.hadoop.queries.utils.ContextRequests;
import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hcatalog.data.DefaultHCatRecord;
import org.apache.hcatalog.data.HCatRecord;
import org.apache.hcatalog.data.schema.HCatSchema;
import org.apache.hcatalog.mapreduce.HCatInputFormat;
import org.apache.hcatalog.mapreduce.HCatOutputFormat;
import org.apache.hcatalog.mapreduce.InputJobInfo;
import org.apache.hcatalog.mapreduce.OutputJobInfo;

/**
 *
 * @author olopezsa13
 */
public class MostVisitedSite extends Configured implements Tool
{
    
    public static class Map extends Mapper<WritableComparable, HCatRecord, ContextDate, LongWritable>
    {
        
        @Override
        protected void map(WritableComparable key, HCatRecord row, Context context) throws IOException, InterruptedException
        {
            // 0 -> domain
            // 1 -> context
            // 2 -> requests
            // 3 -> sizecontent
            // 4 -> ds??
            String rContext = (String) row.get(1);
            String rDateTime = (String) row.get(4);
            Long rRequests = (Long) row.get(2);
            
            String date = rDateTime.split("-")[0];
            
            context.write(new ContextDate(rContext, date), new LongWritable(rRequests));
        }
        
    }
    
    public static class Combiner extends Reducer<ContextDate, LongWritable, String, ContextRequests>
    {
        
        @Override
        protected void reduce(ContextDate key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException
        {
            Long sum = 0L;
            for ( LongWritable value : values )
            {
                sum += value.get();
            }
            
            context.write(key.getDate(), new ContextRequests(key.getContext(), sum));
        }
        
    }
    
    public static class Reduce extends Reducer<String, ContextRequests, WritableComparable, HCatRecord>
    {
        
        @Override
        protected void reduce(String key, Iterable<ContextRequests> values, Context context) throws IOException, InterruptedException
        {
            Long max = Long.MIN_VALUE;
            String mContext = null;
            for ( ContextRequests value : values )
            {
                if ( value.getRequests() > max )
                {
                    max = value.getRequests();
                    mContext = value.getContext();
                }
            }
            
            HCatRecord record = new DefaultHCatRecord(2);
            record.set(0, key);
            record.set(1, mContext);
            
            context.write(null, record);
        }
        
    }

    @Override
    public int run(String[] args) throws Exception
    {
        Configuration conf = getConf();
        args = new GenericOptionsParser(conf, args).getRemainingArgs();

        // Get the input and output table names, and database name as arguments
        String dbName = args[0];
        String inputTableName = args[1];
        String outputTableName = args[2];

        Job job = new Job(conf, "most-visited-site");
        HCatInputFormat.setInput(job, InputJobInfo.create(dbName, inputTableName, null));
        job.setJarByClass(MostVisitedSite.class);
        job.setMapperClass(Map.class);
        job.setCombinerClass(Combiner.class);
        job.setReducerClass(Reduce.class);
        
        // An HCatalog record as input
        job.setInputFormatClass(HCatInputFormat.class);
        
        // Mapper emits a string as key and an integer as value
        job.setMapOutputKeyClass(ContextDate.class);
        job.setMapOutputValueClass(LongWritable.class);

        // Ignore the key for the reducer output; emitting an HCatalog record as value
        job.setOutputKeyClass(WritableComparable.class);
        job.setOutputValueClass(DefaultHCatRecord.class);
        job.setOutputFormatClass(HCatOutputFormat.class);

        HCatOutputFormat.setOutput(job, OutputJobInfo.create(dbName, outputTableName, null));
        HCatSchema s = HCatOutputFormat.getTableSchema(job);
        
        System.err.println("INFO: output schema explicitly set for writing:" + s);
        
        HCatOutputFormat.setSchema(job, s);
        
        return (job.waitForCompletion(true) ? 0 : 1);
    }
    
    public static void main(String[] args) throws Exception
    {
        int exitCode = ToolRunner.run(new MostVisitedSite(), args);
        System.exit(exitCode);
    }
    
}
