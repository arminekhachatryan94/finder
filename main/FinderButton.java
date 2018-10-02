import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class FinderButton extends JButton implements ActionListener {
    public String btnText;
    public JTextArea text;
    public JTextArea location;
    public DirectoryModal dir_modal;

    FinderButton(String btnText, JTextArea text, JTextArea location, DirectoryModal dir_modal){
        this.btnText = btnText;
        this.text = text;
        this.location = location;
        this.dir_modal = dir_modal;

        setText(btnText);
        if( btnText.contains("<br>")){
            setSize(180, 30);
        } else {
            setSize(180, 50);
        }
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command == "Find Next"){
            System.out.println(location.getText());
            File f = new File(location.getText());
            if( f.exists()){
                dir_modal.openDirectory(location.getText());
                dir_modal.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "File path does not exist");
            }
        }
    }
}