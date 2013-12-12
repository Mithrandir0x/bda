package edu.ub.bda.hadoop.queries;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * @author olopezsa13
 */
public class MostVisitedSite
{
    
    private static class Map extends MapReduceBase implements Mapper
    {

        @Override
        public void map(Object k1, Object v1, OutputCollector outputCollector, Reporter reporter) throws IOException
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    private static class Reduce extends MapReduceBase implements Reducer
    {

        @Override
        public void reduce(Object k2, Iterator itrtr, OutputCollector oc, Reporter rprtr) throws IOException
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public static void main(String[] args) throws Exception
    {
        JobConf jobConfiguration = new JobConf(MostVisitedSite.class);
        jobConfiguration.setJobName("most-visited-site");
        
        // Set OutputKeyClass
        // Set OutputValueClass
        
        // Set MapperClass
        // Set CombinerClass
        // Set ReducerClass
    }
    
}
