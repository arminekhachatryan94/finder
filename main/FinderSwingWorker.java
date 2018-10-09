import java.awt.Dimension;
import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

class FinderSwingWorker extends SwingWorker<Integer, String> {
    public JTextField find;
    public JTextField replace;
    public JTextField path;
    public JTextField filters;
    public JCheckBox[] match;
    public JFrame frame;
    public JPanel panel;

    public FinderSwingWorker(JTextField find, JTextField replace, JTextField path, JTextField filters, JCheckBox[] match){
        this.find = find;
        this.replace = replace;
        this.path = path;
        this.filters = filters;
        this.match = new JCheckBox[2];
        this.match[0] = match[0];
        this.match[1] = match[1];
        this.frame = new JFrame();
        this.frame.setPreferredSize(new Dimension(400, 500));
    }

    @Override
    protected Integer doInBackground() throws Exception {
        int count = countFiles(this.path.getText());

        setProgress(0);

        File f = new File(this.path.getText());
        subDirectories(f);
        return count;
    }


    @Override
    protected void process(List<String> files) {
        for(String f : files) {
            System.out.println(f);
        }
    }

    /*
    @Override
    protected void done() {
        boolean bStatus = false;
        try {
            bStatus = get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Finished with status " + bStatus);
    }
    */

    void subDirectories(File d){
        File[] filesList = d.listFiles();
        List<String> l = new ArrayList<String>();
        for( int i = 0; i < filesList.length; i++ ){
            if( filesList[i].isDirectory()){
                subDirectories(filesList[i]);
            } else if( filesList[i].isFile()){
                l.add(filesList[i].getAbsolutePath());
            }
        }
        process(l);
    }

    private int countFiles(String path){
        File d = new File(path);
        if( d.exists()){
            File[] filesList = d.listFiles();
            int count = 0;
            for( int i = 0; i < filesList.length; i++ ){
                // System.out.println(i);
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
