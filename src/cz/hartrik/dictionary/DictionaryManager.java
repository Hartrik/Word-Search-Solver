package cz.hartrik.dictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * Správce slovníků.
 * 
 * @version 2013-08-30
 * @author Patrik Harag
 */
public class DictionaryManager {
    
    private static DictionaryManager instance;
    
    private static final String cs = "/cz/hartrik/dictionary/files/czech.txt";
    private static final String en = "/cz/hartrik/dictionary/files/english.txt";
    public static final  String CS = "cs";
    public static final  String EN = "en";
    
    /**
     * Vrátí instanci třídy.
     * 
     * @return instance
     */
    public static DictionaryManager getInstance() {
        if (instance == null) instance = new DictionaryManager();
        return instance;
    }
    
    private final Map<String, ManagedDictionary> dictionaries = new HashMap<>();
    
    private DictionaryManager() {
        dictionaries.put("cs", 
                new ManagedDictionary(new HashDictionaryWithLoader(cs), false));
        
        dictionaries.put("en", 
                new ManagedDictionary(new HashDictionaryWithLoader(en), true));
    }
    
    public ManagedDictionary get(String name) {
        return dictionaries.get(name);
    }
    
    public void add(String name, ManagedDictionary managedDictionary) {
        dictionaries.put(name, managedDictionary);
    }
    
    /**
     * Pokud je slovo v některém slovníku, vrátí true.
     * Prohledává pouze povolené slovníky.
     * 
     * @param word hledané slovo
     * @return existence
     */
    public boolean exist(String word) {
        for (Map.Entry<?, ?> pairs : dictionaries.entrySet()) {
            ManagedDictionary dictionary = (ManagedDictionary)pairs.getValue();
            if (!dictionary.isDisabled()) {
                if (dictionary.getDictionary().exist(word)) return true;
            }
        }
        return false;
    }
    
}