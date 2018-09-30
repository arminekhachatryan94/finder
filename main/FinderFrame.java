import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        FinderTab find_tab = new FinderTab("Find");
        find_tab.setBackground(Color.lightGray);
        FinderTab replace_tab = new FinderTab("Replace");
        FinderTab findInFiles_tab = new FinderTab("Find in Files");

        MouseListener click = new tabClickListener();
        find_tab.addMouseListener(click);
        replace_tab.addMouseListener(click);
        findInFiles_tab.addMouseListener(click);

        FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
        flow.setHgap(0);
        flow.setVgap(0);
        
        tabs = new JPanel();
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
        find.setBackground(Color.blue);
        getContentPane().add(find);
    }

    public static void setBgFg( Component c, boolean set){
        if( set ){
            c.setBackground(Color.lightGray);
        } else {
            c.setBackground(Color.white);
        }
    }
    
    private class tabClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e){
            JLabel l = (JLabel) e.getSource();
            if( l.getText() == "Find"){
                setBgFg(tabs.getComponent(0), true);
                setBgFg(tabs.getComponent(1), false);
                setBgFg(tabs.getComponent(2), false);
            } else if( l.getText() == "Replace"){
                setBgFg(tabs.getComponent(0), false);
                setBgFg(tabs.getComponent(1), true);
                setBgFg(tabs.getComponent(2), false);
            } else if( l.getText() == "Find in Files") {
                setBgFg(tabs.getComponent(0), false);
                setBgFg(tabs.getComponent(1), false);
                setBgFg(tabs.getComponent(2), true);
            }
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e){}

        @Override
        public void mouseReleased(MouseEvent e){}

        @Override
        public void mouseEntered(MouseEvent e){}

        @Override
        public void mouseExited(MouseEvent e){}
    }

}