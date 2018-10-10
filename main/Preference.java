import java.util.prefs.Preferences;

class Preference {
    private Preferences prefs;

    public Preference(){
        prefs = Preferences.userRoot().node(this.getClass().getName());
    }

    public String getFilePath(){
        return prefs.get("filesPath", "");
    }

    public String getFindPath(){
        return prefs.get("findPath", "");
    }

    public String getFind(){
        return prefs.get("find", "");
    }

    public String getReplace(){
        return prefs.get("replace", "");
    }

    public String getFilters() {
        return prefs.get("filters", "");
    }

    public Boolean getWholeWord(){
        return prefs.getBoolean("wholeWord", false);
    }

    public Boolean getCaseSensitive(){
        return prefs.getBoolean("caseSensitive", false);
    }

    public void setFilePath(String path){
        prefs.put("filesPath", path);
    }

    public void setFindPath(String path){
        prefs.put("findPath", path);
    }

    public void setFind(String word){
        prefs.put("find", word);
    }

    public void setReplace(String word){
        prefs.put("replace", word);
    }

    public void setFilters(String filters) {
        prefs.put("filters", filters);
    }

    public void setWholeWord(Boolean val){
        prefs.putBoolean("wholeWord", val);
    }

    public void setCaseSensitive(Boolan val){
        prefs.putBoolean("caseSensitive", val);
    }

}