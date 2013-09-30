package edu.ub.bda.ubticket.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author olopezsa13
 */
public class HibernateSessionFactory
{
    
    private static SessionFactory sf = null;
    private static Session s = null;
  
    static
    {
        //Inicializa el SF buscando los ficheros de configuracion
        try
        {
            sf = new Configuration().configure().buildSessionFactory();
            //System.out.println("Instanciando SF");
        }
        catch (HibernateException e)
        {
            System.out.println("Error SF: "+e.getMessage());
        }
    }

     /**
     * @return the sesion
     */
    public static Session getSession()
    {
        if ( s == null )
        {
            s = sf.openSession();
        }
        
        return s;
    }
}
