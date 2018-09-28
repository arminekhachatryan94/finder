import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
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
        JLabel find = new JLabel("Find");
        JLabel replace = new JLabel("Replace");
        JLabel findInFiles = new JLabel("Find in Files");

        find.setOpaque(true);
        find.setForeground(Color.black);
        find.setBackground(Color.white);
        find.setBorder(new EmptyBorder(2,5,2,5));
        replace.setOpaque(true);
        replace.setForeground(Color.black);
        replace.setBackground(Color.white);
        replace.setBorder(new EmptyBorder(2,5,2,5));
        findInFiles.setOpaque(true);
        findInFiles.setForeground(Color.black);
        findInFiles.setBackground(Color.white);
        findInFiles.setBorder(new EmptyBorder(2,5,2,5));

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