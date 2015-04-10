package cz.hartrik.dictionary;

/**
 * Spravovaný slovník.
 * 
 * @version 2013-08-24
 * @author Patrik Harag
 */
public class ManagedDictionary {
    
    private Dictionary dictionary;
    private boolean disabled;

    public ManagedDictionary(Dictionary dictionary, boolean disabled) {
        this.dictionary = dictionary;
        this.disabled = disabled;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
}