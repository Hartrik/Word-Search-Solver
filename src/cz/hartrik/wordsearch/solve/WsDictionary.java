package cz.hartrik.wordsearch.solve;

/**
 * (Word Search Dictionary) - rozhraní pro slovník.
 *
 * @version 2013-08-20
 * @author Patrik Harag
 */
public interface WsDictionary {
    
    /**
     * Informuje, zda se slovo nachází ve slovníku.
     * 
     * @param word hledané slovo
     * @return existence
     */
    public abstract boolean exist(String word);
    
}