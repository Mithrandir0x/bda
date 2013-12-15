package edu.ub.bda.hadoop.queries;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hcatalog.mapreduce.HCatInputFormat;

/**
 *
 * @author olopezsa13
 */
public class MostVisitedSite
{
    
    public static void main(String[] args) throws Exception
    {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        
        Job job = new Job(conf);
        job.setInputFormatClass(HCatInputFormat.class);
        
        // Set OutputKeyClass
        // Set OutputValueClass
        
        // Set MapperClass
        // Set CombinerClass
        // Set ReducerClass
    }
    
}
