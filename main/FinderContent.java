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
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

        JTextField findText = new JTextField("");
        findText.setBorder(BorderFactory.createLineBorder(Color.black));
        findText.setBackground(Color.white);
        findText.setBounds(90, 10, 150, 20);
        add(findText);

        // row 2
        JLabel findWhere = new JLabel("Path:");
        findWhere.setBackground(Color.red);
        findWhere.setBounds(10, 35, 70, 20);
        add(findWhere);

        JTextField path = new JTextField("");
        path.setBorder(BorderFactory.createLineBorder(Color.black));
        path.setBackground(Color.white);
        path.setBounds(90, 35, 150, 20);
        add(path);

        JCheckBox[] match = new JCheckBox[2];
        // row 3
        match[0] = new JCheckBox("Match whole word only");
        match[0].setBounds(10, 60, 200, 50);
        add(match[0]);
        // row 4
        match[1] = new JCheckBox("Match case");
        match[1].setBounds(10, 95, 200, 20);
        add(match[1]);

        // file chooser button
        FinderButton chooser = new FinderButton("...", "find", findText, path, null, null, match);
        chooser.setBounds(250, 30, 50, 30);
        add(chooser);

        // buttons
        FinderButton findBtn = new FinderButton("Find", "find", findText, path, null, null, match);

        findBtn.setBounds(310, 7, 180, 30);

        add(findBtn);
    }

    public void createReplacePanel() {
        // row 1
        JLabel findWhat = new JLabel("Find:");
        findWhat.setBackground(Color.red);
        findWhat.setBounds(10, 10, 70, 20);
        add(findWhat);

        JTextField findText = new JTextField("");
        findText.setBorder(BorderFactory.createLineBorder(Color.black));
        findText.setBackground(Color.white);
        findText.setBounds(90, 10, 150, 20);
        add(findText);
        
        // row 2
        JLabel replaceWhat = new JLabel("Replace:");
        replaceWhat.setBackground(Color.red);
        replaceWhat.setBounds(10, 35, 70, 20);
        add(replaceWhat);

        JTextField replaceText = new JTextField("");
        replaceText.setBorder(BorderFactory.createLineBorder(Color.black));
        replaceText.setBackground(Color.white);
        replaceText.setBounds(90, 35, 150, 20);
        add(replaceText);

        // row 3
        JLabel findWhere = new JLabel("Path:");
        findWhere.setBackground(Color.red);
        findWhere.setBounds(10, 60, 70, 20);
        add(findWhere);

        JTextField path = new JTextField("");
        path.setBorder(BorderFactory.createLineBorder(Color.black));
        path.setBackground(Color.white);
        path.setBounds(90, 60, 150, 20);
        add(path);

        JCheckBox[] match = new JCheckBox[2];
        // row 4
        match[0] = new JCheckBox("Match whole word only");
        match[0].setBounds(10, 85, 200, 50);
        add(match[0]);
        // row 5
        match[1] = new JCheckBox("Match case");
        match[1].setBounds(10, 120, 200, 20);
        add(match[1]);
        
        // file chooser button
        FinderButton chooser = new FinderButton("...", "replace", findText, path, replaceText, null, match);
        chooser.setBounds(250, 56, 50, 30);
        add(chooser);

        // buttons on right column
        FinderButton replaceAllBtn = new FinderButton("Replace All", "replace", findText, path, replaceText, null, match);

        replaceAllBtn.setBounds(310, 7, 180, 30);

        add(replaceAllBtn);
    }

    public void createFindInFilesPanel(){
        // row 1
        JLabel findWhat = new JLabel("Find:");
        findWhat.setBackground(Color.red);
        findWhat.setBounds(10, 10, 70, 20);
        add(findWhat);

        JTextField findText = new JTextField("");
        findText.setBorder(BorderFactory.createLineBorder(Color.black));
        findText.setBackground(Color.white);
        findText.setBounds(90, 10, 150, 20);
        add(findText);

        // row 2
        JLabel replaceWhat = new JLabel("Replace:");
        replaceWhat.setBackground(Color.red);
        replaceWhat.setBounds(10, 35, 70, 20);
        add(replaceWhat);

        JTextField replaceText = new JTextField("");
        replaceText.setBorder(BorderFactory.createLineBorder(Color.black));
        replaceText.setBackground(Color.white);
        replaceText.setBounds(90, 35, 150, 20);
        add(replaceText);

        // row 3
        JLabel filtersLabel = new JLabel("Filters:");
        filtersLabel.setBackground(Color.red);
        filtersLabel.setBounds(10, 60, 70, 20);
        add(filtersLabel);

        JTextField filters = new JTextField("");
        filters.setBorder(BorderFactory.createLineBorder(Color.black));
        filters.setBackground(Color.white);
        filters.setBounds(90, 60, 150, 20);
        add(filters);

        // row 4
        JLabel findWhere = new JLabel("Path:");
        findWhere.setBackground(Color.red);
        findWhere.setBounds(10, 85, 70, 20);
        add(findWhere);

        JTextField path = new JTextField("");
        path.setBorder(BorderFactory.createLineBorder(Color.black));
        path.setBackground(Color.white);
        path.setBounds(90, 85, 150, 20);
        add(path);

        JCheckBox[] match = new JCheckBox[2];
        //row 5
        match[0] = new JCheckBox("Match whole word only");
        match[0].setBounds(10, 110, match[0].getWidth(), match[0].getHeight());
        // add(match[0]);
        
        // // row 6
        match[1] = new JCheckBox("Match case");
        match[1].setBounds(10, 145, match[1].getWidth(), match[1].getHeight());
        // add(match[1]);

        JCheckBox jcb = new JCheckBox("TEST");
        jcb.setBounds(10, 145, 200, 20);
        add(jcb);

        JCheckBox jcb2 = new JCheckBox("TEST");
        jcb2.setBounds(10, 200, 200, 20);
        add(jcb2);

        // file chooser button
        FinderButton chooser = new FinderButton("...", "find all", findText, path, replaceText, filters, match);
        chooser.setBounds(250, 82, 50, 30);
        add(chooser);

        // buttons
        FinderButton findAllBtn = new FinderButton("Find All", "find all", findText, path, replaceText, filters, match);
        FinderButton replaceAllBtn = new FinderButton("Replace All", "find all", findText, path, replaceText, filters, match);

        findAllBtn.setBounds(310, 7, 180, 30);
        replaceAllBtn.setBounds(310, 40, 180, 30);

        add(findAllBtn);
        add(replaceAllBtn);
    }
}