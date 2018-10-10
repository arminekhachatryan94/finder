import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

class FinderSwingWorker extends SwingWorker<Integer, String> {
    final static Logger logger = Logger.getLogger(FinderSwingWorker.class);

    public JTextField find;
    public JTextField replace;
    public JTextField path;
    public JTextField filters;
    public JCheckBox[] match;
    public JFrame frame;
    public JPanel panel;
    public boolean confirm_replace = false;
    public int count_lines = 0;

    public FinderSwingWorker(JTextField find, JTextField replace, JTextField path, JTextField filters, JCheckBox[] match){
        this.find = find;
        this.replace = replace;
        this.path = path;
        this.filters = filters;
        this.match = new JCheckBox[2];
        this.match[0] = match[0];
        this.match[1] = match[1];
        this.frame = new JFrame();
        this.frame.setPreferredSize(new Dimension(400, 400));
        this.panel = new JPanel();
        this.panel.setPreferredSize(new Dimension(400, 400));
        this.panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.confirm_replace = false;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        int count = countFiles(this.path.getText());

        setProgress(0);

        File f = new File(this.path.getText());
        subDirectories(f);
        done();
        return count;
    }

    @Override
    protected void process(List<String> files) {
        for(String s : files) {
            File f = new File(s);
            logger.info(s);
            ArrayList<String> founds = findWord(f.getAbsolutePath());

            BorderLayout borderLayout = new BorderLayout(10, 0);
            if(founds.size() > 0 ){
                JLabel l = new JLabel();
                l.setLayout(borderLayout);
                l.setText(s);
                l.setSize(400, 10);
                panel.add(l);
                for(String found : founds ){
                    JLabel label = new JLabel();
                    label.setLayout(borderLayout);
                    label.setText(found);
                    label.setSize(400, 10);
                    panel.add(label);
                }
            }

            if( this.replace != null && this.confirm_replace == false ){
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to replace?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION){
                    this.confirm_replace = true;
                }
            }

            if( this.confirm_replace ){
                // replace occurrences in temporary array
                replaceInArray(founds);

                // save to the file
                replaceInFile(founds, s);
            }
        }
    }

    @Override
    protected void done() {
        if(this.panel.getComponents().length > 0){
        JScrollPane scroller = new JScrollPane(this.panel);
        scroller.setAutoscrolls(true);

        this.frame.add(this.panel, BorderLayout.PAGE_END);
        this.frame.add(scroller, BorderLayout.CENTER);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if( this.confirm_replace ){
            this.confirm_replace = false;
            JOptionPane.showMessageDialog(null, "Successfully replaced everything");
        }
        } else {
            JOptionPane.showMessageDialog(null, "No results found");
        }
    }

    public ArrayList<String> findWord(String location){
        ArrayList<String> found = new ArrayList<String>();
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(location)))) {
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
                        logger.info(line);
                        found.add(new String("Line " + count + ": " + line));
                    }
                } else {
                    String _word = this.find.getText().toLowerCase();
                    String _line = line.toLowerCase();
                    if(_line.contains(_word)){
                        logger.info(line);
                        found.add(new String("Line " + count + ": " + line));
                    }
                }
            }
        } catch( Exception e ){
            logger.info(e.toString());
        }
        return found;
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
                count_lines++;
            } else if( match[0].isSelected() ){
                String regex = ".*\\b" + this.find.getText().toLowerCase() + "\\b.*";
                line = line.replaceAll(regex, this.replace.getText());
                lines.set(i, sub + line);
                count_lines++;
            } else if( match[1].isSelected() ){
                line = line.replaceAll(this.find.getText(), this.replace.getText());
                lines.set(i, sub + line);
                count_lines++;
            } else {
                line = line.replaceAll(this.find.getText().toLowerCase(), this.replace.getText());
                lines.set(i, sub + line);
                count_lines++;
            }
            logger.info(sub + line);
        }
    }

    public void replaceInFile(ArrayList<String> lines, String location) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(location));
            ArrayList<String> oldText = new ArrayList<String>();
            String line;
            while ((line = file.readLine()) != null) {
                oldText.add(line);
            }
            file.close();

            String outputString = "";
            String newText = lines.get(0);
            String newLine = newText.substring(newText.indexOf(':')+2);
            int line_num = Integer.parseInt(newText.substring(4, newText.indexOf(':')));
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

            logger.info(outputString);
            
            // write the new String with the replaced line OVER the same file
            FileOutputStream fileOut = new FileOutputStream(location);
            fileOut.write(outputString.getBytes());
            fileOut.close();
        } catch (Exception e) {
            logger.info("Problem replacing the words in the file.");
        }
    }

    void subDirectories(File d){
        File[] filesList = d.listFiles();
        List<String> l = new ArrayList<String>();
        ArrayList<String> filts = getFilters();
        for( int i = 0; i < filesList.length; i++ ){
            if( filesList[i].isDirectory()){
                subDirectories(filesList[i]);
            } else if( filesList[i].isFile()){
                int index = 0;
                int j = filesList[i].getAbsolutePath().indexOf('.');
                String sub = filesList[i].getAbsolutePath();
                while( j != -1 ){
                    index = i;
                    sub = sub.substring(index+1, sub.length());
                    j = sub.indexOf('.');
                }
                logger.info(sub);
                if( filts.contains('.' + sub) || filts.contains("*.*") || this.filters.getText().length() == 0 ){
                    l.add(filesList[i].getAbsolutePath());
                }
            }
        }
        process(l);
    }

    private ArrayList<String> getFilters(){
        ArrayList<String> ret = new ArrayList<String>();
        if( this.filters.getText().length() == 0 ){
            ;
        } else {
            int c = 0;
            for( int j = 0; j < this.filters.getText().length(); j++ ){
                if( this.filters.getText().charAt(j) == ' '){
                    c++;
                }
            }
            if( c == this.filters.getText().length()){
                ;
            } else {
                String[] sp = this.filters.getText().split("\\s+");
                for( int j = 0; j < sp.length; j++ ){
                    ret.add(sp[j]);
                }
            }
        }
        return ret;
    }

    private int countFiles(String path){
        File d = new File(path);
        if( d.exists()){
            File[] filesList = d.listFiles();
            int count = 0;
            for( int i = 0; i < filesList.length; i++ ){
                // logger.info(i);
                if( filesList[i].isDirectory()){
                    count += countFiles(filesList[i].getAbsolutePath());
                } else if( filesList[i].isFile()){
                    count += 1;
                }
            }
            return count;
        } else {
            return 0;
        }
    }
}
