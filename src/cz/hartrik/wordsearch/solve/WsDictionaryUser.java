package cz.hartrik.wordsearch.solve;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Uživatelský slovník.
 *
 * @version 2013-09-25
 * @author Patrik Harag
 */
public class WsDictionaryUser extends WsDictionaryWithHistory {

    private final Set<String> dictionary;

    public WsDictionaryUser(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    public WsDictionaryUser(String... words) {
        this.dictionary = new LinkedHashSet<>(Arrays.asList(words));
    }
    
    @Override
    protected boolean compare(String word) {
        return dictionary.contains(word);
    }
    
}