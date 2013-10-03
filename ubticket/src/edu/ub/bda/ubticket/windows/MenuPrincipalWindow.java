package edu.ub.bda.ubticket.windows;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import edu.ub.bda.UBTicket;
import edu.ub.bda.ubticket.beans.Usuario;

/**
 *
 * @author olopezsa13
 */
public class MenuPrincipalWindow extends Window
{
    
    public MenuPrincipalWindow(final UBTicket ubticket)
    {
        super("Menú principal");
        
        this.setSoloWindow(true);
            addComponent(new Button("1. Gestionar contenidos", new Action() {

                @Override
                public void doAction()
                {  
                    if (Usuario.getTipo_usuario()<=20) ////?????
                        ubticket.gestionarContenidos();
                    else
                         MessageBox.showMessageBox(ubticket.getGUIScreen(), "Atención", "Sólo los administradores.");
                }
            }));
        
        
        addComponent(new Button("2. Comprar entradas", new Action() {

            @Override
            public void doAction()
            {
                ubticket.comprarEntradas();
            }
        
        }));
        
        addComponent(new Button("  2.1. Ver entradas compradas", new Action() {

            @Override
            public void doAction()
            {
                ubticket.gestionarEntradas();
            }
        
        }));
        
        addComponent(new Button("3. Cerrar sesión", new Action() {

            @Override
            public void doAction()
            {
                ubticket.cerrarSesion();
            }
        
        }));
        
        addComponent(new Button("4. Salir", new Action() {

            @Override
            public void doAction()
            {
                ubticket.cerrar();
            }
        
        }));
    }
    
}
