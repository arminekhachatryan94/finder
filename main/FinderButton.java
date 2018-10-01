import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

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
        }
    }
}