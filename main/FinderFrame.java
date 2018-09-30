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

    public FinderFrame() {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.white);

        createMenu();
        createTabs();

        getContentPane().add(menu, BorderLayout.PAGE_START);
        getContentPane().add(tabs, BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createMenu(){
        menu = new FinderMenu();
    }

    public void createTabs(){
        FinderTab find = new FinderTab("Find");
        FinderTab replace = new FinderTab("Replace");
        FinderTab findInFiles = new FinderTab("Find in Files");

        FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
        flow.setHgap(0);
        flow.setVgap(0);
        
        tabs = new JPanel();
        tabs.setLayout(flow);
        tabs.setSize(500, 20);
        tabs.add(find);
        tabs.add(replace);
        tabs.add(findInFiles);
    }
}