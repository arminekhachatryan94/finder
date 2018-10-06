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

    FinderContent(String type, int width, int height){
        this.type = type;
        this.WIDTH = width;
        this.HEIGHT = height;
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
        JLabel findWhat = new JLabel("Find:");
        findWhat.setBackground(Color.red);
        findWhat.setBounds(10, 10, 70, 20);
        add(findWhat);

        JTextArea findText = new JTextArea("");
        findText.setBorder(BorderFactory.createLineBorder(Color.black));
        findText.setBackground(Color.white);
        findText.setBounds(90, 10, 150, 20);
        add(findText);

        // row 2
        JLabel findWhere = new JLabel("Path:");
        findWhere.setBackground(Color.red);
        findWhere.setBounds(10, 35, 70, 20);
        add(findWhere);

        JTextArea path = new JTextArea("");
        path.setBorder(BorderFactory.createLineBorder(Color.black));
        path.setBackground(Color.white);
        path.setBounds(90, 35, 150, 20);
        add(path);

        FinderButton chooser = new FinderButton("...", "find", findText, path, null);
        chooser.setBounds(250, 30, 50, 30);
        add(chooser);

        // buttons
        FinderButton findBtn = new FinderButton("Find", "find", findText, path, null);
        FinderButton countBtn = new FinderButton("Count", "find", findText, path, null);
        FinderButton closeBtn = new FinderButton("Close", "find", findText, path, null);

        findBtn.setBounds(310, 7, 180, 30);
        countBtn.setBounds(310, 40, 180, 30);
        closeBtn.setBounds(310, 73, 180, 30);

        add(findBtn);
        add(countBtn);
        add(closeBtn);
    }

    public void createReplacePanel() {
        // row 1
        JLabel findWhat = new JLabel("Find:");
        findWhat.setBackground(Color.red);
        findWhat.setBounds(10, 10, 70, 20);
        add(findWhat);

        JTextArea findText = new JTextArea("");
        findText.setBorder(BorderFactory.createLineBorder(Color.black));
        findText.setBackground(Color.white);
        findText.setBounds(90, 10, 150, 20);
        add(findText);
        
        // row 2
        JLabel replaceWhat = new JLabel("Replace:");
        replaceWhat.setBackground(Color.red);
        replaceWhat.setBounds(10, 35, 70, 20);
        add(replaceWhat);

        JTextArea replaceText = new JTextArea("");
        replaceText.setBorder(BorderFactory.createLineBorder(Color.black));
        replaceText.setBackground(Color.white);
        replaceText.setBounds(90, 35, 150, 20);
        add(replaceText);

        // row 3
        JLabel findWhere = new JLabel("Path:");
        findWhere.setBackground(Color.red);
        findWhere.setBounds(10, 60, 70, 20);
        add(findWhere);

        JTextArea path = new JTextArea("");
        path.setBorder(BorderFactory.createLineBorder(Color.black));
        path.setBackground(Color.white);
        path.setBounds(90, 60, 150, 20);
        add(path);

        FinderButton chooser = new FinderButton("...", "replace", findText, path, replaceText);
        chooser.setBounds(250, 56, 50, 30);
        add(chooser);

        // buttons on right column
        FinderButton replaceAllBtn = new FinderButton("Replace All", "replace", findText, path, replaceText);
        FinderButton countBtn = new FinderButton("Count", "replace", findText, path, replaceText);
        FinderButton closeBtn = new FinderButton("Close", "replace", findText, path, replaceText);

        replaceAllBtn.setBounds(310, 7, 180, 30);
        countBtn.setBounds(310, 40, 180, 30);
        closeBtn.setBounds(310, 73, 180, 30);

        add(replaceAllBtn);
        add(countBtn);
        add(closeBtn);
    }

    public void createFindInFilesPanel(){
        // row 1
        JLabel findWhat = new JLabel("Find:");
        findWhat.setBackground(Color.red);
        findWhat.setBounds(10, 10, 70, 20);
        add(findWhat);

        JTextArea findText = new JTextArea("");
        findText.setBorder(BorderFactory.createLineBorder(Color.black));
        findText.setBackground(Color.white);
        findText.setBounds(90, 10, 150, 20);
        add(findText);

        // row 2
        JLabel replaceWhat = new JLabel("Replace:");
        replaceWhat.setBackground(Color.red);
        replaceWhat.setBounds(10, 35, 70, 20);
        add(replaceWhat);

        JTextArea replaceText = new JTextArea("");
        replaceText.setBorder(BorderFactory.createLineBorder(Color.black));
        replaceText.setBackground(Color.white);
        replaceText.setBounds(90, 35, 150, 20);
        add(replaceText);

        // row 3
        JLabel findWhere = new JLabel("Path:");
        findWhere.setBackground(Color.red);
        findWhere.setBounds(10, 35, 70, 20);
        add(findWhere);

        JTextArea path = new JTextArea("");
        path.setBorder(BorderFactory.createLineBorder(Color.black));
        path.setBackground(Color.white);
        path.setBounds(90, 50, 150, 20);
        add(path);

        FinderButton chooser = new FinderButton("...", "find all", findText, path, replaceText);
        chooser.setBounds(250, 50, 50, 30);
        add(chooser);

        // buttons
        FinderButton findAllBtn = new FinderButton("Find All", "find all", findText, path, replaceText);
        FinderButton replaceAllBtn = new FinderButton("Replace All", "find all", findText, path, replaceText);
        FinderButton countBtn = new FinderButton("Count", "find all", findText, path, replaceText);
        FinderButton countFilesBtn = new FinderButton("Count Files", "find all", findText, path, replaceText);
        FinderButton closeBtn = new FinderButton("Close", "find all", findText, path, replaceText);

        findAllBtn.setBounds(310, 7, 180, 30);
        replaceAllBtn.setBounds(310, 40, 180, 30);
        countBtn.setBounds(310, 73, 180, 30);
        countFilesBtn.setBounds(310, 106, 180, 30);
        closeBtn.setBounds(310, 139, 180, 30);

        add(findAllBtn);
        add(replaceAllBtn);
        add(countBtn);
        add(countFilesBtn);
        add(closeBtn);
    }
}