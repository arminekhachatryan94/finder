import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FinderButton extends JButton implements ActionListener {
    String text;
    FinderButton(String text){
        this.text = text;
        setText(text);
        if( text.contains("<br>")){
            setSize(180, 30);
        } else {
            setSize(180, 50);
        }
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        System.out.println(command);
    }
}