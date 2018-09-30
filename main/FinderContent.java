import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class FinderContent extends JPanel {
    public String type;
    public int WIDTH;
    public int HEIGHT;
    public static JTextArea text;

    FinderContent(String type, int width, int height){
        this.type = type;
        this.WIDTH = width;
        this.HEIGHT = height;
        setSize(new Dimension(width, height));
        setBackground(Color.blue);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        
        if( type == "Find" ){
            createFindPanel();
        } /*else if( type == "Replace" ){
            createReplacePanel();
        } else if( type == "Find in Files" ){
            createFindInFilesPanel();
        }*/
    }

    public void createFindPanel(){
        text = new JTextArea();
        add(text);
    }

}