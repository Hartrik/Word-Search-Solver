package cz.hartrik.dictionary;

/**
 * Definuje rozhraní slovníku.
 * 
 * @version 2013-05-29
 * @author Patrik Harag
 */
public abstract class Dictionary {
    
    protected boolean loaded = false;
    
    /**
     * Přidá nové slovo do slovníku.
     * 
     * @param word nové slovo
     */
    public abstract void add(String word);
    
    /**
     * Odstraní slovo ze slovníku.
     * 
     * @param word slovo k odstranění
     * @return úspěšnost operace
     */
    public abstract boolean remove(String word);
    
    /**
     * Informuje, jestli slovník obsahuje určité slovo.
     * 
     * @param word hledané slovo
     * @return existence
     */
    public abstract boolean exist(String word);

    /**
     * Metoda se stará o načítání slovníku.
     */
    public abstract void loadDictionary();
    
    /**
     * Sdělí, zda už je slovník načten.
     * 
     * @return načten
     */
    public boolean isLoaded() {
        return loaded;
    }
    
}