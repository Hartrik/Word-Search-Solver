package cz.hartrik.dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Slouží pro načítání slovníků.
 *
 * @version 2013-05-29
 * @author Patrik Harag
 */
public class DictionaryLoader {

    private final Dictionary dictionary;
    
    public DictionaryLoader(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
    
    public boolean loadFromClassPath(String path) {
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        if (inputStream == null) return false;
        
        boolean done = loadFromInputStream(inputStream);
        try {
            inputStream.close();
        } catch (IOException ex) {}
        
        return done;
    }
    
    public boolean loadFromInputStream(InputStream inputStream) {
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            
            boolean done = readByBufferedReader(bufferedReader);
            bufferedReader.close();
            
            return done;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public boolean readByBufferedReader(BufferedReader bufferedReader) {
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null){
                dictionary.add(line);
            }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
}