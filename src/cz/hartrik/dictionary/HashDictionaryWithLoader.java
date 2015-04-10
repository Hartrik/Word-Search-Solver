package cz.hartrik.dictionary;

/**
 * Slovník automaticky načte data z textového souboru.
 * Při vytvoření je jen potřeba zadat cestu.
 *
 * @version 2013-08-30
 * @author Patrik Harag
 */
public class HashDictionaryWithLoader extends HashDictionary {
    
    protected final String path;

    public HashDictionaryWithLoader(String path) {
        this.path = path;
    }
    
    @Override
    public void loadDictionary() {
        DictionaryLoader loader = new DictionaryLoader(this);
        loader.loadFromClassPath(path);
    }
    
}