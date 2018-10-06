import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FinderFrame extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 400;
    
    public FinderMenu menu;
    public JPanel tabs;
    public FinderContent[] contents;
    public JTextField findText;
    public JTextField path;
    public JTextField replaceText;

    public FinderFrame() {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.white);
        setResizable(false);

        findText = new JTextField("");
        path = new JTextField("");
        replaceText = new JTextField("");

        createMenu();

        contents = new FinderContent[3];
        createFindPanel();
        createReplacePanel();
        createFindInFilesPanel();

        createTabs();
        
        getContentPane().add(contents[0], "Center");
        
        setJMenuBar(menu);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createMenu(){
        menu = new FinderMenu();
    }

    public void createTabs(){
        tabs = new JPanel();
        tabs.setSize(FRAME_WIDTH, 20);

        FinderTab find_tab = new FinderTab("Find", tabs, this, contents);
        find_tab.setBackground(Color.white);
        FinderTab replace_tab = new FinderTab("Replace", tabs, this, contents);
        FinderTab findInFiles_tab = new FinderTab("Find in Files", tabs, this, contents);

        FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
        flow.setHgap(0);
        flow.setVgap(0);
        flow.preferredLayoutSize(tabs);
        
        tabs.setLayout(flow);
        tabs.add(find_tab);
        tabs.add(replace_tab);
        tabs.add(findInFiles_tab);
        getContentPane().add(tabs, "North");
    }

    public void createFindPanel(){
        contents[0] = new FinderContent("Find", FRAME_WIDTH, FRAME_HEIGHT - 20);
    }

    public void createReplacePanel(){
        contents[1] = new FinderContent("Replace", FRAME_WIDTH, FRAME_HEIGHT - 20);
    }

    public void createFindInFilesPanel(){
        contents[2] = new FinderContent("Find in Files", FRAME_WIDTH, FRAME_HEIGHT - 20);
    }
}