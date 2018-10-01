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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class FinderFrame extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 400;
    
    public FinderMenu menu;
    public JPanel tabs;
    public FinderContent find, replace, findInFiles;
    public JTextArea text;
    public JTextArea location;

    public DirectoryModal dir_modal;

    public FinderFrame() {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.white);
        setResizable(false);

        text = new JTextArea("");
        location = new JTextArea("");

        createMenu();
        createTabs();
        createFindPanel();
        
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

        FinderTab find_tab = new FinderTab("Find", tabs);
        find_tab.setBackground(Color.white);
        FinderTab replace_tab = new FinderTab("Replace", tabs);
        FinderTab findInFiles_tab = new FinderTab("Find in Files", tabs);

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
        find = new FinderContent("Find", FRAME_WIDTH, FRAME_HEIGHT - 20, text, location, dir_modal);
        getContentPane().add(find, "Center");
    }
}