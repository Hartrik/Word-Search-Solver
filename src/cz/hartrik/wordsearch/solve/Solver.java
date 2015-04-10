package cz.hartrik.wordsearch.solve;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Prochází osmisměrku a hledá slova. O kontrolu nalezených řetězců a další
 * postup se stará objekt implementující
 * {@link cz.hartrik.wordsearch.solve.Comparator}.
 * Hledá pouze v jednom směru, obousměrné hledání zajišťuje opět
 * {@link cz.hartrik.wordsearch.solve.Comparator}.
 * 
 * @version 2013-08-20
 * @author Patrik Harag
 */
public class Solver {
    
    public static final int MIN_LENGTH = 3;
    
    private final char[][] array;
    private final Comparator comparator;

    private final int width;
    private final int height;
    
    public Solver(char[][] array, Comparator comparator) {
        this.array = array;
        this.width = array.length;
        this.height = array[0].length;
        this.comparator = comparator;
    }
    
    public void solve() {
        doHorizontal();
        doVertical();
        doAcrossRight();
        doAcrossLeft();
    }
    
    /**
     * Procházet osmisměrku horizontálně.
     */
    protected void doHorizontal() {
        for (int i = 0; i < width; i++) {
            List<Point> positions = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            
            for (int j = 0; j < height; j++) {
                builder.append(array[i][j]);
                positions.add(new Point(i, j));
            }
            nextLine(builder.toString(), positions);
        }
    }
    
    /**
     * Procházet osmisměrku vertikálně.
     */
    protected void doVertical() {
        for (int i = 0; i < height; i++) {
            List<Point> positions = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            
            for (int j = 0; j < width; j++) {
                builder.append(array[j][i]);
                positions.add(new Point(j, i));
            }
            nextLine(builder.toString(), positions);
        }
    }
    
    /**
     * Procházet osmisměrku diagonálně
     * (směrem z levého horního rohu do pravého dolního)
     */
    protected void doAcrossRight() {
        int maxSum = width + height - 2;
        
        for (int sum = 0; sum <= maxSum; sum++) {
            List<Point> positions = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (i + j - sum == 0) {
                        builder.append(array[i][j]);
                        positions.add(new Point(i, j));
                    }
                }
            }
            nextLine(builder.toString(), positions);
        }
    }
    
    /**
     * Procházet osmisměrku diagonálně
     * (směrem z pravého horního rohu do levého dolního)
     */
    protected void doAcrossLeft() {
        for (int i = 1 - width; i < height; i++) {
            List<Point> positions = new ArrayList<>();
            StringBuilder builder = new StringBuilder();

            for (int j = 0; j < width; j++) {
                if ((i + j) >= 0 && (i + j) < height) {
                    builder.append(array[j][i + j]);
                    positions.add(new Point(j, i + j));
                }
            }
            nextLine(builder.toString(), positions);
        }
    }
    
    protected void nextLine(String line, List<Point> positions) {
        final int length = line.length();
        if (length < MIN_LENGTH) return;
        
        for (int i = 0; i < length - MIN_LENGTH + 1; i++) {
            for (int j = i + MIN_LENGTH; j < length + 1; j++) {
                String substring = line.substring(i, j);
                List<Point> subpositions = positions.subList(i, j);
                comparator.compare(substring, subpositions);
            }
        }
    }
    
}