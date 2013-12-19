package edu.ub.bda.hadoop.queries;

import edu.ub.bda.hadoop.queries.utils.ContextRequests;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * A MapReduce query that fetches the most visited site of a day.
 * 
 * @author olopezsa13
 */
public class MostVisitedSite extends Configured implements Tool
{

    private static final String root = "/user/hive/warehouse";
    private static final String outRootPath = "/user/cloudera/";
    
    public static class Map extends Mapper<LongWritable, Text, Text, ContextRequests>
    {
        
        private static Log log = LogFactory.getLog(Map.class);
        
        private static Pattern inputPattern = Pattern.compile("(.+)(\\s)(.+)(\\s)(\\d+)(\\s)(\\d+)");
        
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException
        {
            String date = context.getConfiguration().get("date");
            Matcher inputMatch = inputPattern.matcher(value.toString());
            
            if ( inputMatch.matches() )
            {
                String rContext = inputMatch.group(3);
                Long rRequests = Long.parseLong(inputMatch.group(5));
                
                //log.info("M[" + rContext + "] --> [" + "" + ", " + rRequests + "]" );

                //context.write(new Text(rContext), new ContextRequests("", rRequests));
                context.write(new Text(date), new ContextRequests(rContext, rRequests));
            }
        }
        
    }
    
    public static class Combiner extends Reducer<Text, ContextRequests, Text, ContextRequests>
    {
        
        private static Log log = LogFactory.getLog(Combiner.class);
        
        @Override
        protected void reduce(Text key, Iterable<ContextRequests> values, Context context) throws IOException, InterruptedException
        {
            String date = context.getConfiguration().get("date");
            
            Long sum = 0L;
            for ( ContextRequests value : values )
            {
                sum += value.getRequests();
            }
            
            log.info("C[" + date + "] --> [" + key.toString() + ", " + sum + "]" );
            
            context.write(new Text(date), new ContextRequests(key.toString(), sum));
        }
        
    }
    
    public static class Reduce extends Reducer<Text, ContextRequests, Text, Text>
    {
        
        private static Log log = LogFactory.getLog(Reduce.class);
        
        @Override
        protected void reduce(Text key, Iterable<ContextRequests> values, Context context) throws IOException, InterruptedException
        {
            Long max = Long.MIN_VALUE;
            String mContext = null;
            java.util.Map<String, Long> t = new HashMap<String, Long>(); // Access to the map is slow as hell...
            
            for ( ContextRequests value : values )
            {
                //log.info("R[" + value.getContext() + "] --> [" + value.getRequests() + "]" );
                
                Long v;
                if ( t.containsKey(value.getContext()) )
                    v = t.get(value.getContext()) + value.getRequests();
                else
                    v = value.getRequests();
                
                t.put(value.getContext(), v);
                
                if ( v > max )
                {
                    max = v;
                    mContext = value.getContext();
                }
            }
            
            log.info("R[" + key + "] --> [" + mContext + ", " + max + "]");
            
            context.write(new Text("[" + key + "] -->"), new Text("[" + mContext + ", " + max + "]"));
        }
        
    }

    
    @Override
    public int run(String[] args) throws Exception
    {
        Configuration conf = getConf();
        args = new GenericOptionsParser(conf, args).getRemainingArgs();

        // Get the input and output table names, and database name as arguments
        String dbName = args[0] + ".db";
        String tableName = args[1];
        
        String year = null, month = null, day = null;
        year = args[2];
        month = args[3];
        day = args[4];
        
        conf.set("date", year + month + day);

        Job job = new Job(conf, "most-visited-site");
        job.setJarByClass(MostVisitedSite.class);
        
        Calendar calendar = Calendar.getInstance();
        
        FileInputFormat.setInputPaths(job, getTablePartitionPaths(year + month + day, dbName, tableName));
        FileOutputFormat.setOutputPath(job, new Path(outRootPath + "/most-visited-site-" + year + "-" + month + "-" + day + "-" + calendar.getTimeInMillis()));
        
        job.setMapperClass(Map.class);
        //job.setCombinerClass(Combiner.class);
        job.setReducerClass(Reduce.class);
                
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(ContextRequests.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        return (job.waitForCompletion(true) ? 0 : 1);
    }

    /**
     * For each partition of a table, get all the files inside that partition.
     * 
     * @param partition A partition pattern
     * @param dbName The database name
     * @param tableName The table name
     * @return String A comma separated list of paths to each file inside each partition of the table
     * @throws IOException 
     */
    private String getTablePartitionPaths(String partition, String dbName, String tableName) throws IOException
    {
        Pattern inputPattern = Pattern.compile("(.*)ds=" + partition + "-(\\d{4})$");
        //Pattern inputPattern = Pattern.compile("(.*)ds=" + partition + "-0200$");
        
        List<Path> listpath = new ArrayList<Path>();
        FileSystem fs = FileSystem.get(new Configuration());
        
        FileStatus[] tableStatus = fs.listStatus(new Path(root + "/" + dbName + "/" + tableName + "/"));
        for ( FileStatus partitionStatus : tableStatus )
        {
            Matcher inputMatch = inputPattern.matcher(partitionStatus.getPath().toString());
            if ( inputMatch.matches() )
            {
                FileStatus[] rowsStatus = fs.listStatus(partitionStatus.getPath());
                for ( FileStatus rowsFile : rowsStatus )
                {
                    System.out.println(rowsFile.getPath());
                    listpath.add(rowsFile.getPath());
                }
            }
        }
        
        String pts = "";
        for (Path p : listpath)
        {
            pts = pts.concat(p.toString() + ",");
        }
        
        return pts.substring(0, (pts.length() - 1));
    }
    
    public static void main(String[] args) throws Exception
    {
        int exitCode = ToolRunner.run(new MostVisitedSite(), args);
        System.exit(exitCode);
    }
    
}
