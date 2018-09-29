import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

public class FinderFrame extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 400;
    
    public FinderMenu menu;
    public JPanel tabs;

    public JPanel directory;

    public FinderFrame() {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.white);

        createMenu();
        createTabs();

        getContentPane().add(menu, BorderLayout.PAGE_START);
        getContentPane().add(tabs, BorderLayout.CENTER);
        createDirectory();
        getContentPane().add(directory, BorderLayout.PAGE_END);

        JScrollPane scroller = new JScrollPane(directory);
        scroller.setAutoscrolls(true);
        scroller.setAlignmentX(LEFT_ALIGNMENT);
        add(scroller, BorderLayout.WEST);
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

    public void createDirectory() {
        directory = new JPanel();
        directory.setLayout(new BoxLayout(directory, BoxLayout.Y_AXIS));

        ImageIcon dir_img = new ImageIcon(new ImageIcon("../img/dir.Default").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon file_img = new ImageIcon(new ImageIcon("../img/file.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        File folder = FileSystemView.getFileSystemView().getHomeDirectory();
        folder = new File(folder.getPath() + "/Desktop");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            JLabel l = new JLabel();

            if (listOfFiles[i].isFile()) {
                l.setIcon(file_img);
            } else if (listOfFiles[i].isDirectory()) {
                l.setIcon(dir_img);
            }
            l.setText(listOfFiles[i].getName());

            directory.add(l);
        }
    }
}