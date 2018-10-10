import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import static org.junit.Assert.assertFalse;
import org.junit.Test;

import javax.swing.JTextArea;

public class FindReplaceTest {    
    @Test
    public void FindWordTest() {
        File f = new File(System.getProperty("user.home"));
        assertTrue(f.exists());
        assertTrue(f.isDirectory());
    }
}