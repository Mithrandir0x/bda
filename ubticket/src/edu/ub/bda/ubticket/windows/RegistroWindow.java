package edu.ub.bda.ubticket.windows;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.PasswordBox;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import com.googlecode.lanterna.terminal.Terminal;
import edu.ub.bda.UBTicket;
import edu.ub.bda.ubticket.utils.AutenticacionServicio;

/**
 *
 * @author olopezsa13
 */
public class RegistroWindow extends Window
{
    
    private TextBox login;
    private PasswordBox password;
    
    public RegistroWindow(final UBTicket ubticket)
    {
        super("Autenticación");
        
        this.setSoloWindow(true);
        
        addComponent(new Label("EXTERMINATE", Terminal.Color.RED));
        Panel p = new Panel(new Border.Invisible(), Panel.Orientation.HORISONTAL);
        
        Panel left = new Panel(new Border.Invisible(), Panel.Orientation.VERTICAL);
        left.addComponent(new Label("LOGIN:"));
        left.addComponent(new Label("PASSWORD:"));
        
        Panel right = new Panel(new Border.Invisible(), Panel.Orientation.VERTICAL);
        login = new TextBox();
        password = new PasswordBox();
        right.addComponent(login);
        right.addComponent(password);
        
        p.addComponent(left);
        p.addComponent(right);
        addComponent(p);
        
        addComponent(new Button("OK", new Action() {

            @Override
            public void doAction()
            {
                // Validate data
                if ( AutenticacionServicio.Registrar(login.getText(), password.getText()) )
                {
                    ubticket.iniciarSesion();
                }
                else
                {
                    MessageBox.showMessageBox(ubticket.getGUIScreen(), "Atención", "No existe el usuario o la contraseña es incorrecta.");
                }
            }
        
        }));
    }
    
    @Override
    public void onVisible()
    {
        login.setText("");
        password.setText("");
        
        this.setFocus(login);
    }
    
}
