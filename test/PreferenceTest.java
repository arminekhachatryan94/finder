import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import javax.swing.JLabel;

public class PreferenceTest {
    public Preference prefs = new Preference();

    @Test
    public void setFind() {
        this.prefs.setFind("Hello");
        assertTrue(this.prefs.getFind().equals("Hello"));
    }

    @Test
    public void setReplace() {
        this.prefs.setReplace("World");
        assertTrue(this.prefs.getReplace().equals("World"));
    }

    @Test
    public void setFilePath() {
        this.prefs.setFilePath("desktop/test");
        assertTrue(this.prefs.getFilePath().equals("desktop/test"));
    }

    @Test
    public void setFindPath() {
        this.prefs.setFindPath("desktop/test/Test.java");
        assertTrue(this.prefs.getFindPath().equals("desktop/test/Test.java"));
    }

    @Test
    public void setFilters() {
        this.prefs.setFilters("*.* .java .css .html .cfg .txt");
        assertTrue(this.prefs.getFilters().equals("*.* .java .css .html .cfg .txt"));
    }

    @Test
    public void setWholeWord() {
        this.prefs.setWholeWord(true);
        assertTrue(this.prefs.getWholeWord().equals(true));
    }

    @Test
    public void setCaseSensitive() {
        this.prefs.setCaseSensitive(false);
        assertTrue(this.prefs.getCaseSensitive().equals(false));
    }
}