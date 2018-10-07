import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FinderButton extends JButton implements ActionListener {
    public String btnText = null;
    public String belongsTo = null; // panel button belongs to
    public JTextField find = null;
    public JTextField replace = null;
    public JTextField path = null;
    public JTextField filters = null;
    public JCheckBox[] match = null;

    public int returnValue;
    public JFileChooser fileChooser;

    FinderButton(String btnText, String belongsTo, JTextField find, JTextField path, JTextField replace, JTextField filters, JCheckBox[] match){
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
    
    public boolean isValidFile(){
        File f = new File(this.path.getText());
        if( !f.isFile() && f.exists() ){
            JOptionPane.showMessageDialog(null, "Error: Path selected is not a valid file.");
            return false;
        } else if( f.isFile() ) {
            int index = 0;
            int i = this.path.getText().indexOf('.');
            String sub = this.path.getText();
            while( i != -1 ){
                index = i;
                sub = sub.substring(index+1, sub.length());
                i = sub.indexOf('.');
            }
            if( (sub.equals("java") || sub == "cfg" || sub.equals("txt") || sub == "html" || sub == "css") && this.find.getText().length() > 0 ){
                return true;
            } else if( this.find.getText().length() == 0 ){
                JOptionPane.showMessageDialog(null, "Error: Please provide a word to find.");
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "Error: This application does not support those files.");
                return false;
            }
        } else {
            JOptionPane.showConfirmDialog(null, "Error: Invalid path.");
            return false;
        }
    }

    public ArrayList<String> findWord(){
        ArrayList<String> found = new ArrayList<String>();
        if( isValidFile() ){
            int count = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(new File(this.path.getText())))) {
                String line;
                while ((line = br.readLine()) != null) {
                    count++;
                    if( this.match[0].isSelected() && this.match[1].isSelected() ){
                        String regex = ".*\\b" + this.find.getText() + "\\b.*";
                        if(line.matches(regex)){
                            found.add(new String("Line" + count + ": " + line));
                        }
                    } else if( this.match[0].isSelected() ){
                        String regex = ".*\\b" + this.find.getText().toLowerCase() + "\\b.*";
                        if(line.toLowerCase().matches(regex)){
                            found.add(new String("Line" + count + ": " + line));
                        }
                    } else if( this.match[1].isSelected() ){
                        if(line.contains(this.find.getText())){
                            System.out.println(line);
                            found.add(new String("Line " + count + ": " + line));
                        }
                    } else {
                        String _word = this.find.getText().toLowerCase();
                        String _line = line.toLowerCase();
                        if(_line.contains(_word)){
                            System.out.println(line);
                            found.add(new String("Line " + count + ": " + line));
                        }
                    }
                }
            } catch( Exception e ){
                System.out.println(e.toString());
            }
            return found;
        } else {
            return null;
        }
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
            // find word in a file
            ArrayList<String> found = findWord();
            if( found != null ){
                // create a frame and display the words found
                JFrame frame = new JFrame();
                frame.setSize(400, 400);
                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(400, 400));
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                BorderLayout borderLayout = new BorderLayout(10, 0);
                if(found.size() > 0 ){
                    for( int i = 0; i < found.size(); i++ ){
                        JLabel label = new JLabel();
                        label.setLayout(borderLayout);
                        label.setText(found.get(i));
                        label.setSize(400, 10);
                        panel.add(label);
                    }
                } else {
                    JLabel label = new JLabel();
                    label.setText("No occurrences of " + this.find.getText());
                }
                JScrollPane scroller = new JScrollPane(panel);
                scroller.setAutoscrolls(true);
                scroller.setAlignmentX(RIGHT_ALIGNMENT);

                frame.add(panel, BorderLayout.PAGE_END);
                frame.add(scroller, BorderLayout.CENTER);
                frame.setVisible(true);
                frame.setResizable(false);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        }
    }
}