package edu.ub.bda.hadoop.queries.utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;

/**
 *
 * @author olopezsa13
 */
public class ContextDate implements WritableComparable<ContextDate>
{

    protected String context;
    protected String date;
    
    public ContextDate()
    {
    }

    public ContextDate(String c, String dt)
    {
        context = c;
        date = dt;
    }

    public String getContext()
    {
        return context;
    }

    public String getDate()
    {
        return date;
    }

    @Override
    public void write(DataOutput out) throws IOException
    {
        out.writeUTF(context);
        out.writeUTF(date);
    }

    @Override
    public void readFields(DataInput in) throws IOException
    {
        context = in.readUTF();
        date = in.readUTF();
    }

    @Override
    public int compareTo(ContextDate ct)
    {
        int cmp = this.context.compareTo(ct.context);
        if ( cmp != 0 )
        {
            return cmp;
        }

        return this.date.compareTo(ct.date);
    }

}
