import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class FinderMenu extends JMenuBar {
    public JMenu menu = new JMenu("Menu");
    public JMenuItem exit = new JMenuItem("exit");
    
    FinderMenu(){
        menu.add(exit);
        menu.setVisible(true);
        add(menu);
        setVisible(true);
        addMenuListeners();
    }
    
    private void addMenuListeners() {
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitActionPerformed();
            }
        });
    }

    private void exitActionPerformed() {
        System.exit(0);
    }
}