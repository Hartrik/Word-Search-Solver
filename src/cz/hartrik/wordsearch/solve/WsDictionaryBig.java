package cz.hartrik.wordsearch.solve;

import cz.hartrik.dictionary.Dictionary;

/**
 * Slovník velkého obsahu.
 *
 * @version 2013-08-20
 * @author Patrik Harag
 */
public class WsDictionaryBig extends WsDictionaryWithHistory {
    
    private final Dictionary dictionary;

    public WsDictionaryBig(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    protected boolean compare(String word) {
        return dictionary.exist(word);
    }
    
}