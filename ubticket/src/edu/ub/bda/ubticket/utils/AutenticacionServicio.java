package edu.ub.bda.ubticket.utils;

import edu.ub.bda.ubticket.beans.Usuario;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author olopezsa13
 */
public class AutenticacionServicio
{

    private static Usuario usuario;
    
    /**
     * Intenta registrar el usuario con login y contraseña pasados por parámetro,
     * y retorna si ha podido ser registrado en el sistema o no.
     * 
     * @param login Nombre de cuenta del usuario
     * @param password Contraseña de la cuenta del usuario
     * @return 
     */
    public static boolean Registrar(final String login, final String password)
    {
        // System.out.println("LGN: '" + login + "'");
        // System.out.println("PWD: '" + password + "'");
        
        usuario = new HibernateTransaction<Usuario>() {

            @Override
            public Usuario run(){
                Criteria cQuery = session.createCriteria(Usuario.class)
                        .add(Restrictions.eq("login", login))
                        .add(Restrictions.eq("password", password));
                
                return (Usuario) cQuery.uniqueResult();
            }
        
        }.execute();
        
        return usuario != null;
    }
    
    /**
     * 
     * @return El usuario
     */
    public static Usuario GetUsuario()
    {
        return usuario;
    }
    
    /**
     * Cierra la sesión del usuario conectado actualmente
     */
    public static void Cerrar()
    {
        usuario = null;
    }
    
}
