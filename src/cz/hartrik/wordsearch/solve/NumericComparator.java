package cz.hartrik.wordsearch.solve;

import java.awt.Point;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Porovnává nalezené řetězce a nálezy zapisuje do pole.
 * Čím větší hodnota na pozici v poli, tím víc se tam vyskytuje slov.
 * Nalezená slova ukládá do množiny.
 *
 * @version 2013-09-21
 * @author Patrik Harag
 */
public class NumericComparator implements Comparator {

    private final WsDictionary dictionary;
    private boolean stringReverse = true;
    
    private final int[][] result;
    private final Set<String> matches = new LinkedHashSet<>();
    
    public NumericComparator(int width, int height, WsDictionary dictionary) {
        this.result = new int[width][height];
        this.dictionary = dictionary;
    }

    @Override
    public void compare(String string, List<Point> positions) {   
        if (dictionary.exist(string)) comp(string, positions);
        
        if (stringReverse) {
            String reversed = new StringBuilder(string).reverse().toString();
            comp(reversed, positions);
        }
    }
    
    private void comp(String string, List<Point> positions) {
        if (dictionary.exist(string)) {
            addToArray(positions);
            matches.add(string);
        }
    }
    
    private void addToArray(List<Point> positions) {
        for (Point point : positions) {
            result[point.x][point.y]++;
        }
    }
    
    public int[][] getResult() {
        return result;
    }
    
    public void disableStringReverse(boolean reverse) {
        stringReverse = !reverse;
    }
    
    public boolean isStringReverse() {
        return stringReverse;
    }

    public Set<String> getMatches() {
        return matches;
    }
    
}