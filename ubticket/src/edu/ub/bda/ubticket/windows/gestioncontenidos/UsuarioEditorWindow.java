
package edu.ub.bda.ubticket.windows.gestioncontenidos;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import edu.ub.bda.UBTicket;
import edu.ub.bda.ubticket.beans.Usuario;
import edu.ub.bda.ubticket.utils.HibernateTransaction;
import edu.ub.bda.ubticket.windows.GestorGenericoWindow;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author domenicocitera
 */
public class UsuarioEditorWindow extends Window {

    private final Usuario usuario;
    private final GestorGenericoWindow gestor;
    private final UBTicket ubticket;
    private TextBox nombreTextBox;
    private TextBox loginTextBox;
    private TextBox fechaTextBox;
    private TextBox passwordTextBox;

    public UsuarioEditorWindow(final UBTicket ubticket, GestorGenericoWindow gestor) {

        super("Crear [Usuario]");
        usuario = new Usuario();
        this.gestor = gestor;
        this.ubticket = ubticket;
        inicializarComponentes();
    }

    public UsuarioEditorWindow(final UBTicket ubticket, GestorGenericoWindow gestor, Usuario usuario) {

        super("Editar [Usuario][" + usuario.getId().toString() + "]");
        this.usuario = usuario;
        this.gestor = gestor;
        this.ubticket = ubticket;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        final Object self = this;


        Panel left = new Panel(new Border.Invisible(), Panel.Orientation.VERTICAL);
        left.addComponent(new Label("     LOGIN:"));
        left.addComponent(new Label("    NOMBRE:"));
        left.addComponent(new Label("  PASSWORD:"));


        Panel right = new Panel(new Border.Invisible(), Panel.Orientation.VERTICAL);
        loginTextBox = new TextBox(usuario.getLogin());
        nombreTextBox = new TextBox(usuario.getNombre());
        passwordTextBox = new TextBox(usuario.getPassword());

        right.addComponent(loginTextBox);
        right.addComponent(nombreTextBox);
        right.addComponent(passwordTextBox);

        Panel editor = new Panel(new Border.Invisible(), Panel.Orientation.HORISONTAL);
        editor.addComponent(left);
        editor.addComponent(right);

        addComponent(editor);

        Panel buttons = new Panel(new Border.Invisible(), Panel.Orientation.HORISONTAL);
        buttons.addComponent(new Button("GUARDAR", new Action() {
            @Override
            public void doAction() {
                try {
                    usuario.setNombre(nombreTextBox.getText());
                    usuario.setLogin(loginTextBox.getText());
                    usuario.setPassword(passwordTextBox.getText());
                    if (usuario.getFecha_alta() == null) {
                        usuario.setFecha_alta(new Timestamp(new Date().getTime()));
                    }
                    
                    List<Object> list = new HibernateTransaction<List<Object>>() {
                        @Override
                        public List<Object> run() {
                            Query query = session.createQuery("from Usuario");
                            return query.list();
                        }
                    }.execute();
                    
                    boolean use = false;
                    for (Object i : list) {
                        Usuario us = (Usuario) i;
                        if ((us.getId() != usuario.getId())
                                && (us.getLogin().equals(loginTextBox.getText()))) {
                            use = true;
                        }
                    }
                    
                    if (use) {
                        MessageBox.showMessageBox(ubticket.getGUIScreen(), "Atención", "Login in uso.");
                    } else {
                        new HibernateTransaction<Object>() {
                            @Override
                            public Object run() {
                                session.saveOrUpdate(usuario);
                                return null;
                            }
                        }.execute();

                        gestor.actualizarTabla();
                        ((Window) self).close();
                    }
                } catch (Exception ex) {
                    MessageBox.showMessageBox(ubticket.getGUIScreen(), "Atención", "No se ha podido guardar correctamente.");
                }
            }
        }));

        buttons.addComponent(new Button("CANCELAR", new Action() {
            @Override
            public void doAction() {
                ((Window) self).close();
            }
        }));

        addComponent(buttons);
    }
}
