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
import javax.swing.border.EmptyBorder;

public class FinderFrame extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 400;
    
    public FinderMenu menu;
    public JPanel tabs;
    public JLabel find_tab, replace_tab, findInFiles_tab;
    public JPanel find, replace, findInFiles;

    public FinderFrame() {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.white);

        createMenu();
        createTabs();
        createFindPanel();
        
        setJMenuBar(menu);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createMenu(){
        menu = new FinderMenu();
    }

    public void createTabs(){
        tabs = new JPanel();

        FinderTab find_tab = new FinderTab("Find", tabs);
        find_tab.setBackground(Color.white);
        FinderTab replace_tab = new FinderTab("Replace", tabs);
        FinderTab findInFiles_tab = new FinderTab("Find in Files", tabs);

        FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
        flow.setHgap(0);
        flow.setVgap(0);
        
        tabs.setLayout(flow);
        tabs.setSize(500, 20);
        tabs.add(find_tab);
        tabs.add(replace_tab);
        tabs.add(findInFiles_tab);
        getContentPane().add(tabs);
    }

    public void createFindPanel(){
        find = new JPanel();
        find.setSize(FRAME_WIDTH, FRAME_HEIGHT - 20);
        find.setBackground(Color.white);
        getContentPane().add(find);
    }
}