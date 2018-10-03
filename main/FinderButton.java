import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FinderButton extends JButton implements ActionListener {
    public String btnText;
    public JTextArea text;
    public JTextArea location;

    public int returnValue;
    public JFileChooser fileChooser;

    FinderButton(String btnText, JTextArea text, JTextArea location){
        if( btnText == "..."){
            this.fileChooser = new JFileChooser();
            this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("All Files", "java", "cpp", "txt", "html", "css", "js", "cfg"));
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("Java", "java"));
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("C++", "cpp"));
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("HTML", "html"));
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("CSS", "css"));
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("Javascript", "js"));
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("CFG", "cfg"));
            this.fileChooser.setAcceptAllFileFilterUsed(false);
        }
        this.btnText = btnText;
        this.text = text;
        this.location = location;

        setText(btnText);
        if( btnText.contains("<br>")){
            setSize(180, 30);
        } else if(btnText.contains("...")) {
            setSize(50, 30);
        } else {
            setSize(180, 50);
        }
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command == "..."){
            returnValue = fileChooser.showOpenDialog(null);
            if( returnValue == JFileChooser.APPROVE_OPTION){
                location.setText(fileChooser.getSelectedFile().getPath());
            }
        }
    }
}