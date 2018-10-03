import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Image;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DirectoryModal extends JFrame {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;

    public String path;

    public JPanel panel;

    DirectoryModal() {}

    public void openDirectory(String path){
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(Color.white);
        this.path = new String(path);
        createPanel();
        getContentPane().add(panel, BorderLayout.PAGE_END);

        JScrollPane scroller = new JScrollPane(panel);
        scroller.setAutoscrolls(true);
        scroller.setAlignmentX(LEFT_ALIGNMENT);
        add(scroller, BorderLayout.WEST);
        setResizable(false);

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void createPanel() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        ImageIcon dir_img = new ImageIcon(new ImageIcon("../img/dir.Default").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        ImageIcon file_img = new ImageIcon(new ImageIcon("../img/file.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

        MouseListener iconClick = new IconClickListener();

        File folder = new File(this.path);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            JLabel l = new JLabel();

            if (listOfFiles[i].isFile()) {
                l.setIcon(file_img);
            } else if (listOfFiles[i].isDirectory()) {
                l.setIcon(dir_img);
            }
            l.setText(listOfFiles[i].getName());
            l.addMouseListener(iconClick);

            panel.add(l);
        }
    }

    private class IconClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e){
            int count = e.getClickCount();

            JLabel label = (JLabel) e.getSource();
            System.out.println(label.getText());

            File f = new File(path + '/' + label.getText());
            
            if( count >= 2 && f.isDirectory() ){
                path += ("/" + label.getText());
                createPanel();
                repaint();
            } else {
                ;
            }
        }

        @Override
        public void mouseExited(MouseEvent e){
            ;
        }

        public void mousePressed(MouseEvent e){
            ;
        }

        public void mouseReleased(MouseEvent e){
            ;
        }

        public void mouseEntered(MouseEvent e){
            ;
        }
    }

}