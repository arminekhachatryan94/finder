import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FinderTab extends JLabel implements MouseListener {
    JPanel tabs;

    public FinderTab(String text, JPanel tabs){
        this.tabs = tabs;

        setText(text);
        setOpaque(true);
        setForeground(Color.black);
        setBackground(Color.lightGray);
        setBorder(new EmptyBorder(2,5,2,5));

        addMouseListener(this);
    }

    public static void setBgFg( Component c, boolean set){
        if( set ){
            c.setBackground(Color.white);
        } else {
            c.setBackground(Color.lightGray);
        }
    }

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