package Lab4Gecys;

import studijosKTU.Ks;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Jurgis on 2015-12-01.
 */
public class MyApplet extends JApplet {
    @Override
    public void init() {
        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            MyPanel panel = new MyPanel();
            panel.initComponents();
            SwingUtilities.invokeAndWait(() -> {
                add(panel);
            });
        } catch (InterruptedException | InvocationTargetException/* | ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException*/ ex) {
            Ks.ou(ex.getMessage());
        }
    }
}
