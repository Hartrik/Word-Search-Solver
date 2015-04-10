package cz.hartrik.wordsearch.solve;

import java.awt.Point;
import java.util.List;

/**
 * Přebírá a kontroluje řetězce nalezené v osmisměrce.
 * Sama se stará postará o další postup.
 * 
 * @author Patrik Harag
 */
public interface Comparator {
    
    /**
     * Přebírá a kontroluje řetězce nalezené v osmisměrce.
     * 
     * @param string nalezený řetězec
     * @param positions pozice řetězce
     */
    public abstract void compare(String string, List<Point> positions);
    
}