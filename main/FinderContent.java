import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javafx.geometry.Pos;

public class FinderContent extends JPanel {
    public String type;
    public int WIDTH;
    public int HEIGHT;
    public static JTextArea text;
    public static JTextArea location;

    FinderContent(String type, int width, int height){
        this.type = type;
        this.WIDTH = width;
        this.HEIGHT = height;
        setSize(new Dimension(width, height));
        setBackground(Color.white);
        setLayout(null);

        if( type == "Find" ){
            createFindPanel();
        } else if( type == "Replace" ){
            createReplacePanel();
        } else if( type == "Find in Files" ){
            createFindInFilesPanel();
        }
    }

    public void createFindPanel(){
        // row 1
        JLabel findWhat = new JLabel("Find what:");
        findWhat.setBackground(Color.red);
        findWhat.setBounds(20, 10, 70, 20);
        add(findWhat);

        text = new JTextArea(1, 15);
        text.setBorder(BorderFactory.createLineBorder(Color.black));
        text.setBackground(Color.white);
        text.setBounds(100, 10, 200, 20);
        add(text);

        // row 2
        JLabel findWhere = new JLabel("Directory:");
        findWhere.setBackground(Color.red);
        findWhere.setBounds(20, 35, 70, 20);
        add(findWhere);

        location = new JTextArea(1, 15);
        location.setBorder(BorderFactory.createLineBorder(Color.black));
        location.setBackground(Color.white);
        location.setBounds(100, 35, 200, 20);
        add(location);
        
        // buttons
        JButton findNextBtn = new JButton("Find Next");
        JButton countBtn = new JButton("Count");
        JButton findAllOpenedDocsBtn = new JButton("<html>Find All in Opened<br>Documents</html>");
        JButton findAllCurrentDocBtn = new JButton("<html>Find All in Current<br>Document</html>");
        JButton closeBtn = new JButton("Close");

        findNextBtn.setSize(180, 30);
        findNextBtn.setBounds(310, 7, 180, 30);
        countBtn.setSize(180, 30);
        countBtn.setBounds(310, 40, 180, 30);
        findAllOpenedDocsBtn.setSize(180, 50);
        findAllOpenedDocsBtn.setBounds(310, 73, 180, 50);
        findAllCurrentDocBtn.setSize(180, 50);
        findAllCurrentDocBtn.setBounds(310, 126, 180, 50);
        closeBtn.setSize(180, 30);
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