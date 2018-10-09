import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
import javax.swing.SwingWorker;
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
        if( btnText.equals("...")){
            this.fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("All Files", "java", "txt", "html", "css", "cfg");
            if( belongsTo.equals("files") ){
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
        if( this.path == null || this.path.getText().length() == 0){
            JOptionPane.showMessageDialog(null, "Error: Please provide a path.");
            return false;
        } else if( this.find == null || this.find.getText().length() == 0 ){
            JOptionPane.showMessageDialog(null, "Error: Please provide a word to find.");
            return false;
        } else if( (this.replace == null || this.replace.getText().length() == 0) && this.belongsTo.equals("replace")){
            JOptionPane.showMessageDialog(null, "Error: Please provide a word to replace with.");
            return false;
        } else {
            File f = new File(this.path.getText());
            if( !f.isFile() && f.exists() ){
                JOptionPane.showMessageDialog(null, "Error: Path selected is not a valid file.");
                return false;
            } else if( f.isFile() ){
                int index = 0;
                int i = this.path.getText().indexOf('.');
                String sub = this.path.getText();
                while( i != -1 ){
                    index = i;
                    sub = sub.substring(index+1, sub.length());
                    i = sub.indexOf('.');
                }
                if( (sub.equals("java") || sub.equals("cfg") || sub.equals("txt") || sub.equals("html") || sub.equals("css")) ){
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Error: This application does not support those files.");
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Invalid path.");
                return false;
            }
        }
        // if( !f.isFile() && f.exists() ){
        //     JOptionPane.showMessageDialog(null, "Error: Path selected is not a valid file.");
        //     return false;
        // } else if( f.isFile() ) {
        //     int index = 0;
        //     int i = this.path.getText().indexOf('.');
        //     String sub = this.path.getText();
        //     while( i != -1 ){
        //         index = i;
        //         sub = sub.substring(index+1, sub.length());
        //         i = sub.indexOf('.');
        //     }
        //     if( (this.replace == null || this.replace.getText().length() == 0) && this.belongsTo.equals("replace")){
        //         JOptionPane.showMessageDialog(null, "Error: Please provide a word to replace with.");
        //         return false;
        //     } else if( this.find == null || this.find.getText().length() == 0 ){
        //         JOptionPane.showMessageDialog(null, "Error: Please provide a word to find.");
        //         return false;
        //     } else if( (sub.equals("java") || sub.equals("cfg") || sub.equals("txt") || sub.equals("html") || sub.equals("css")) ){
        //         return true;
        //     } else {
        //         JOptionPane.showMessageDialog(null, "Error: This application does not support those files.");
        //         return false;
        //     }
        // } else {
        //     if( this.path == null || this.path.getText().length() == 0 ){
        //         JOptionPane.showMessageDialog(null, "Error: Please provide a path.");
        //         return false;
        //     } else {
        //         JOptionPane.showMessageDialog(null, "Error: Invalid path.");
        //         return false;
        //     }
        // }
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

    public void replaceInArray(ArrayList<String> lines) {
        for( int i = 0; i < lines.size(); i++ ){
            String line = lines.get(i);
            String sub = line.substring(0, line.indexOf(':') + 1);
            line = line.substring(line.indexOf(':') + 1);
            if( match[0].isSelected() && match[1].isSelected() ){
                String regex = ".*\\b" + this.find.getText() + "\\b.*";
                line = line.replaceAll(regex, this.replace.getText());
                lines.set(i, sub + line);
            } else if( match[0].isSelected() ){
                String regex = ".*\\b" + this.find.getText().toLowerCase() + "\\b.*";
                line = line.replaceAll(regex, this.replace.getText());
                lines.set(i, sub + line);
            } else if( match[1].isSelected() ){
                line = line.replaceAll(this.find.getText(), this.replace.getText());
                lines.set(i, sub + line);
            } else {
                line = line.replaceAll(this.find.getText().toLowerCase(), this.replace.getText());
                lines.set(i, sub + line);
            }
            System.out.println(sub + line);
        }
    }

    public void replaceInFile(ArrayList<String> lines) {
        try {
            // read file into oldText ArrayList
            BufferedReader file = new BufferedReader(new FileReader(this.path.getText()));
            ArrayList<String> oldText = new ArrayList<String>();
            String line;
            while ((line = file.readLine()) != null) {
                oldText.add(line);
            }
            file.close();


            String outputString = "";
            String newText = lines.get(0);
            String newLine = newText.substring(newText.indexOf(':')+2);
            int line_num = Integer.parseInt(newText.substring(5, newText.indexOf(':')));
            int count = 0;
            for( int i = 0; i < oldText.size(); i++ ){
                if( i+1 == line_num ){
                    if( i != 0 ){
                        outputString += '\n';
                    }
                    outputString += newLine;
                    count++;
                    if( count < lines.size()){
                        newText = lines.get(count);
                        newLine = newText.substring(newText.indexOf(':')+2);
                        line_num = Integer.parseInt(newText.substring(5, newText.indexOf(':')));
                    }
                } else {
                    if( i != 0 ){
                        outputString += '\n';
                    }
                    outputString += oldText.get(i);
                }
            }

            System.out.print(outputString);
            
            // write the new String with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(this.path.getText());
            fileOut.write(outputString.getBytes());
            fileOut.close();
            JOptionPane.showMessageDialog(null, "Successfully replaced everything");
        } catch (Exception e) {
            System.out.println("Problem replacing the words in the file.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
        if(command.equals("...")){
            returnValue = fileChooser.showOpenDialog(null);
            if( returnValue == JFileChooser.APPROVE_OPTION){
                path.setText(fileChooser.getSelectedFile().getPath());
            }
        } else if(command.equals("Find All") && belongsTo.equals("find")){
            // find word in a file
            ArrayList<String> found = findWord();
            if( found != null && found.size() > 0 ){
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
            } else if( found == null ){
                ;
            } else if( found.size() == 0 && found != null ){
                JOptionPane.showMessageDialog(null, "No words found");
            }
        } else if(command.equals("Replace All") && belongsTo.equals("replace")){
            // find word in a file
            ArrayList<String> found = findWord();
            if( found != null && found.size() > 0 ){
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

                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to replace?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                    frame.dispose();

                    // replace occurrences in temporary array
                    replaceInArray(found);

                    // save to the file
                    replaceInFile(found);

                    // close the results frame
                }
            } else if( found == null ){
                ;
            } else if( found.size() == 0 ) {
                JOptionPane.showMessageDialog(null, "No words found");
            }
        } else if( command.equals("Find All") && this.belongsTo.equals("files")){
            FinderSwingWorker worker = new FinderSwingWorker(this.find, this.replace, this.path, this.filters, this.match);
            try {
                int r = worker.doInBackground();
                System.out.println(r);
            } catch( Exception ex ){
                System.out.println(ex.toString());
            }
        }
    }
}