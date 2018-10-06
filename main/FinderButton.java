import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FinderButton extends JButton implements ActionListener {
    public String btnText = null;
    public String belongsTo = null; // panel button belongs to
    public JTextArea find = null;
    public JTextArea replace = null;
    public JTextArea path = null;
    public JTextArea filters = null;
    public JCheckBox[] match = null;

    public int returnValue;
    public JFileChooser fileChooser;

    FinderButton(String btnText, String belongsTo, JTextArea find, JTextArea path, JTextArea replace, JTextArea filters, JCheckBox[] match){
        if( btnText == "..."){
            this.fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("All Files", "java", "txt", "html", "css", "cfg");
            if( belongsTo == "find all" ){
                this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            } else {
                this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            }
            this.fileChooser.setFileFilter(filter);
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("Java", "java"));
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("HTML", "html"));
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("CSS", "css"));
            this.fileChooser.setFileFilter(new FileNameExtensionFilter("CFG", "cfg"));
            this.fileChooser.setFileFilter(filter);
            this.fileChooser.setAcceptAllFileFilterUsed(false);
            this.fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        }
        this.btnText = btnText;
        this.belongsTo = belongsTo;
        this.find = find;
        this.path = path;
        this.replace = replace;
        this.filters = filters;
        this.match = new JCheckBox[2];
        this.match[0] = match[0];
        this.match[1] = match[1];

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
                path.setText(fileChooser.getSelectedFile().getPath());
            }
        } else if(command == "Find"){
            // if( this.path == )
            System.out.println(command);
        }
    }
}