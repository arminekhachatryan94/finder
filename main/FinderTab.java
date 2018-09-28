import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class FinderTab extends javax.swing.JLabel {
    public FinderTab(String text){
        setText(text);
        setOpaque(true);
        setForeground(Color.black);
        setBackground(Color.white);
        setBorder(new EmptyBorder(2,5,2,5));
    }
}