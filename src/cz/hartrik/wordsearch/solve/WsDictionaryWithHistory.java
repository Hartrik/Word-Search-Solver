package cz.hartrik.wordsearch.solve;

import java.util.ArrayList;
import java.util.List;

/**
 * Slovník zaznamenávající pozitivní hledání.
 *
 * @version 2013-08-20
 * @author Patrik Harag
 */
public abstract class WsDictionaryWithHistory implements WsDictionary {

    private final List<String> history;
    
    public WsDictionaryWithHistory() {
        history = new ArrayList<>();
    }
    
    @Override
    public final boolean exist(String word) {
        boolean exist = compare(word);
        if (exist) history.add(word);
        return exist;
    }

    /**
     * Vrátí seznam pozitivních hledání.
     * 
     * @return seznam
     */
    public final List<String> getHistory() {
        return history;
    }
    
    protected abstract boolean compare(String word);
    
}