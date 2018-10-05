import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;

public class FinderContent extends JPanel {
    public String type;
    public int WIDTH;
    public int HEIGHT;
    public JTextArea findText;
    public JTextArea replaceText;
    public JTextArea path;

    FinderContent(String type, int width, int height, JTextArea findText, JTextArea path){
        this.type = type;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.findText = findText;
        this.path = path;
        setUp();
    }

    FinderContent(String type, int width, int height, JTextArea findText, JTextArea path, JTextArea replaceText){
        this.type = type;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.findText = findText;
        this.path = path;
        this.replaceText = replaceText;
        setUp();
    }

    public void setUp(){
        setSize(new Dimension(this.WIDTH, this.HEIGHT));
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
        findWhat.setBounds(10, 10, 70, 20);
        add(findWhat);

        findText.setBorder(BorderFactory.createLineBorder(Color.black));
        findText.setBackground(Color.white);
        findText.setBounds(90, 10, 150, 20);
        add(findText);

        // row 2
        JLabel findWhere = new JLabel("Directory:");
        findWhere.setBackground(Color.red);
        findWhere.setBounds(10, 35, 70, 20);
        add(findWhere);

        path.setBorder(BorderFactory.createLineBorder(Color.black));
        path.setBackground(Color.white);
        path.setBounds(90, 35, 150, 20);
        add(path);

        FinderButton chooser = new FinderButton("...", findText, path);
        chooser.setBounds(250, 30, 50, 30);
        add(chooser);

        // buttons
        FinderButton findNextBtn = new FinderButton("Find Next", findText, path);
        FinderButton countBtn = new FinderButton("Count", findText, path);
        FinderButton findAllOpenedDocsBtn = new FinderButton("<html>Find All in Opened<br>Documents</html>", findText, path);
        FinderButton findAllCurrentDocBtn = new FinderButton("<html>Find All in Current<br>Document</html>", findText, path);
        FinderButton closeBtn = new FinderButton("Close", findText, path);

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
        JLabel l = new JLabel();
        l.setText("Replace");
        l.setBounds(100, 100, 100, 100);
        add(l);
    }

    public void createFindInFilesPanel(){
        JLabel l = new JLabel();
        l.setText("Find in Files");
        l.setBounds(100, 100, 100, 100);
        add(l);
    }
}