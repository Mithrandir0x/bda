package edu.ub.bda.ubticket.windows;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.Table;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import com.googlecode.lanterna.gui.dialog.TextInputDialog;
import edu.ub.bda.UBTicket;
import edu.ub.bda.ubticket.beans.Categoria;
import edu.ub.bda.ubticket.beans.Espectaculo;
import edu.ub.bda.ubticket.utils.HibernateTransaction;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author olopezsa13
 */
public class GestorGenericoWindow extends Window
{
    
    private Class<?> claseEntidad;
    private Table tabla;
    private Panel panel;

    public GestorGenericoWindow(final UBTicket ubticket, Class c)
    {
        super("Gestionar [" + c.getSimpleName() + "]");
        final Object self = this;
        
        claseEntidad = c;
        
        panel = new Panel(new Border.Invisible(), Panel.Orientation.HORISONTAL);
        addComponent(panel);
        
        Panel p = new Panel(new Border.Invisible(), Panel.Orientation.HORISONTAL);
        
        p.addComponent(new Button("CREAR", new Action() {

            @Override
            public void doAction()
            {
            }
        
        }));
        
        p.addComponent(new Button("EDITAR", new Action() {

            @Override
            public void doAction()
            {
            }
        
        }));
        
        p.addComponent(new Button("BORRAR", new Action() {

            @Override
            public void doAction()
            {
                String input = TextInputDialog.showTextInputBox(ubticket.getGUIScreen(), "Atención", "¿Qué tabla desea eliminar?", "");
                
                if ( input.length() != 0 )
                {
                    try
                    {
                        final Integer i = Integer.parseInt(input);
                        
                        /* new HibernateTransaction<Object>() {

                            @Override
                            public Object run()
                            {
                                return null;
                            }

                        }.execute(); */
                    }
                    catch ( NumberFormatException ex )
                    {
                        MessageBox.showMessageBox(ubticket.getGUIScreen(), "Atención", "Identificador inválido.");
                    }
                }
            }
        
        }));
        
        p.addComponent(new Button("Salir", new Action() {

            @Override
            public void doAction()
            {
                ((Window) self).close();
            }
        
        }));
        
        addComponent(p);
    }
    
    @Override
    public void onVisible()
    {
        final String nombreClaseEntidad = claseEntidad.getSimpleName();
        
        List<Object> list = new ArrayList<>();
        list = new HibernateTransaction<List<Object>>() {

            @Override
            public List<Object> run()
            {
                Query query = session.createQuery("from " + nombreClaseEntidad);
                return query.list();
            }
        
        }.execute();
        
        if ( list != null && list.size() > 0 )
        {
            tabla = new Table(8, "Contenido");
            
            for ( Object objeto : list )
            {                
                if ( objeto instanceof Categoria )
                {
                    Categoria o = (Categoria) objeto;
                    tabla.addRow(new Label(o.getId().toString()), new Label(o.getNombre()));
                }
                else if ( objeto instanceof Espectaculo )
                {
                    Espectaculo o = (Espectaculo) objeto;
                    tabla.addRow(new Label(o.getId().toString()),
                            new Label(o.getTitulo()),
                            new Label(o.getCategoria().getNombre()));
                }
            }
        
            panel.addComponent(tabla);
        }
    }
    
    /* private void escribirFilaTabla(Object object)
    {
        List<Component> fila = new ArrayList<>();
        
        try
        {
            PropertyDescriptor[] descriptores = Introspector.getBeanInfo(claseEntidad, Object.class).getPropertyDescriptors();
            
            for ( PropertyDescriptor descriptorPropiedades : descriptores )
            {
                Method m = descriptorPropiedades.getReadMethod();
                fila.add(new Label(m.invoke(object).toString()));
            }
        }
        catch ( Exception ex )
        {
            Logger.getLogger(GestorGenericoWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Component[] arr = new Component[fila.size()];
        tabla.addRow(fila.toArray(arr));
    } */
    
}
