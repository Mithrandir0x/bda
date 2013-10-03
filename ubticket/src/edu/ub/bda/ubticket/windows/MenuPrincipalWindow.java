package edu.ub.bda.ubticket.windows;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import edu.ub.bda.UBTicket;
import edu.ub.bda.ubticket.beans.Usuario;
import edu.ub.bda.ubticket.utils.AutenticacionServicio;

/**
 *
 * @author olopezsa13
 */
public class MenuPrincipalWindow extends Window
{
    
    private Button gestionarContenidosButton;
    
    public MenuPrincipalWindow(final UBTicket ubticket)
    {
        super("Menú principal");
        
        this.setSoloWindow(true);

        gestionarContenidosButton = new Button("1. Gestionar contenidos", new Action() {

            @Override
            public void doAction()
            {
                ubticket.gestionarContenidos();
            }
        });
        addComponent(gestionarContenidosButton);
        
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
        
    @Override
    public void onVisible()
    {
        Usuario usuario = AutenticacionServicio.GetUsuario();
        gestionarContenidosButton.setVisible(usuario.getTipo_usuario().equals(Usuario.Tipos.ADMINISTRADOR.toString()));
    }
    
}
