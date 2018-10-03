import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import java.io.File;

public class Finder {
    public static void main(String[] args){
        FinderFrame frame = new FinderFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}