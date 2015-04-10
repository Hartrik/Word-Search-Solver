package cz.hartrik.dictionary;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Třída implenentující slovník.
 * Slovník je rozdělen na několik kolekcí podle počtu písmen - rychlejší výběr.
 * 
 * @version 2013-06-01
 * @author Patrik Harag
 */
public abstract class HashDictionary extends Dictionary {
    
    private final Set<String> dictionaryN  = new LinkedHashSet<>();
    private final Set<String> dictionary3  = new LinkedHashSet<>();
    private final Set<String> dictionary4  = new LinkedHashSet<>();
    private final Set<String> dictionary5  = new LinkedHashSet<>();
    private final Set<String> dictionary6  = new LinkedHashSet<>();
    private final Set<String> dictionary7  = new LinkedHashSet<>();
    private final Set<String> dictionary8  = new LinkedHashSet<>();
    private final Set<String> dictionary9  = new LinkedHashSet<>();
    private final Set<String> dictionary10 = new LinkedHashSet<>();
    private final Set<String> dictionary11 = new LinkedHashSet<>();
    private final Set<String> dictionary12 = new LinkedHashSet<>();
    private final Set<String> dictionaryE  = new LinkedHashSet<>();

    public Set<String> getDictionary(int length) {
        switch (length) {
            case 0:
            case 1:
            case 2:  return dictionaryN;
            case 3:  return dictionary3;
            case 4:  return dictionary4;
            case 5:  return dictionary5;
            case 6:  return dictionary6;
            case 7:  return dictionary7;
            case 8:  return dictionary8;
            case 9:  return dictionary9;
            case 10: return dictionary10;
            case 11: return dictionary11;
            case 12: return dictionary12;
            default: return dictionaryE;
        }
    }
    
    @Override
    public void add(String word) {
        Set<String> dictionary = getDictionary(word.length());
        dictionary.add(word);
    }

    @Override
    public boolean remove(String word) {
        Set<String> dictionary = getDictionary(word.length());
        return dictionary.remove(word);
    }

    @Override
    public boolean exist(String word) {
        if (!isLoaded()) {
            loadDictionary();
            loaded = true;
        }
        
        Set<String> dictionary = getDictionary(word.length());
        return dictionary.contains(word);
    }
    
    /**
     * Vrátí velikost slovníku.
     * 
     * @return počet slov
     */
    public int size() {
        int size = 0;
        for (int i = 3; i < 14; i++) {
            size += getDictionary(i).size();
        }
        return size;
    }
    
    /**
     * Vytiskne informace o velikosti slovníku a jeho částí.
     */
    void printInfo() {
        System.out.println("- Obsah slovníku -");
        for (int i = 3; i < 13; i++) {
            System.out.println(i + " = " + getDictionary(i).size());
        }
        System.out.println("E  = " + dictionaryE.size());
        System.out.println("------------------");
        System.out.println("Celkem = " + size() + "\n");
    }
    
}