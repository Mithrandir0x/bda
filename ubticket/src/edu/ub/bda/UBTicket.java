package edu.ub.bda;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import edu.ub.bda.ubticket.windows.GestionContenidosWindow;
import edu.ub.bda.ubticket.windows.MenuPrincipalWindow;
import edu.ub.bda.ubticket.windows.RegistroWindow;
import java.nio.charset.Charset;

/**
 * 
 * @author olopezsa13
 */
public class UBTicket
{
    
    private boolean autenticacion = false;
    
    private Terminal terminal;
    private GUIScreen gui;
    private Screen screen;
    
    private RegistroWindow registroWindow;
    private MenuPrincipalWindow menuPrincipalWindow;
    private GestionContenidosWindow gestionContenidosWindow;
    
    public UBTicket(boolean activarAutenticacion)
    {
        this.autenticacion = activarAutenticacion;
        
        terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        gui = TerminalFacade.createGUIScreen(terminal);
        screen = gui.getScreen();
        
        inicializarComponentes();
        
        if ( gui == null )
        {
            System.err.println("OHNOES");
            return;
        }
        
        screen.startScreen();

        if ( autenticacion )
        {
            gui.showWindow(registroWindow);
        }
        else
        {
            gui.showWindow(menuPrincipalWindow);
        }

        screen.stopScreen();
    }
    
    private void inicializarComponentes()
    {
         registroWindow = new RegistroWindow(this);
         menuPrincipalWindow = new MenuPrincipalWindow(this);
         gestionContenidosWindow = new GestionContenidosWindow(this);
    }
    
    public void iniciarSesion()
    {
        registroWindow.close();
        gui.invalidate();
        gui.showWindow(menuPrincipalWindow);
    }
    
    public void gestionarContenidos()
    {
        gui.showWindow(gestionContenidosWindow);
    }
    
    public void cerrarSesion()
    {
        menuPrincipalWindow.close();
        gui.invalidate();
        gui.showWindow(registroWindow);
    }
    
    public void cerrar()
    {
        gui.getActiveWindow().close();
    }
    
    public GUIScreen getGUIScreen()
    {
        return gui;
    }
    
    /**
     * 
     * @param args 
     */
    public static void main(String[] args)
    {
        new UBTicket(true);
    }

}
