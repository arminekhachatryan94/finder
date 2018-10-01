import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;

public class FinderContent extends JPanel {
    public String type;
    public int WIDTH;
    public int HEIGHT;
    public String text;
    public String location;
    public DirectoryModal dir_modal;

    FinderContent(String type, int width, int height, String text, String location, DirectoryModal dir_modal){
        this.type = type;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.text = text;
        this.location = location;
        this.dir_modal = dir_modal;

        setSize(new Dimension(width, height));
        setBackground(Color.white);
        setLayout(null);

        if( type == "Find" ){
            createFindPanel();
        }/* else if( type == "Replace" ){
            createReplacePanel();
        } else if( type == "Find in Files" ){
            createFindInFilesPanel();
        }*/
    }

    public void createFindPanel(){
        // row 1
        JLabel findWhat = new JLabel("Find what:");
        findWhat.setBackground(Color.red);
        findWhat.setBounds(20, 10, 70, 20);
        add(findWhat);

        JTextArea jtext = new JTextArea(1, 15);
        jtext.setText(text);
        jtext.setBorder(BorderFactory.createLineBorder(Color.black));
        jtext.setBackground(Color.white);
        jtext.setBounds(100, 10, 200, 20);
        add(jtext);

        // row 2
        JLabel findWhere = new JLabel("Directory:");
        findWhere.setBackground(Color.red);
        findWhere.setBounds(20, 35, 70, 20);
        add(findWhere);

        JTextArea jlocation = new JTextArea(1, 15);
        jlocation.setText(location);
        jlocation.setBorder(BorderFactory.createLineBorder(Color.black));
        jlocation.setBackground(Color.white);
        jlocation.setBounds(100, 35, 200, 20);
        add(jlocation);
        
        // buttons
        FinderButton findNextBtn = new FinderButton("Find Next", text, location, dir_modal);
        FinderButton countBtn = new FinderButton("Count", text, location, dir_modal);
        FinderButton findAllOpenedDocsBtn = new FinderButton("<html>Find All in Opened<br>Documents</html>", text, location, dir_modal);
        FinderButton findAllCurrentDocBtn = new FinderButton("<html>Find All in Current<br>Document</html>", text, location, dir_modal);
        FinderButton closeBtn = new FinderButton("Close", text, location, dir_modal);

        findNextBtn.setBounds(310, 7, 180, 30);
        countBtn.setBounds(310, 40, 180, 30);
        findAllOpenedDocsBtn.setBounds(310, 73, 180, 50);
        findAllCurrentDocBtn.setBounds(310, 126, 180, 50);
        closeBtn.setBounds(310, 179, 180, 30);

        add(countBtn);
        add(findNextBtn);
        add(findAllOpenedDocsBtn);
        add(findAllCurrentDocBtn);
        add(closeBtn);
    }

    public void createReplacePanel() {
        ;
    }

    public void createFindInFilesPanel(){
        ;
    }
}