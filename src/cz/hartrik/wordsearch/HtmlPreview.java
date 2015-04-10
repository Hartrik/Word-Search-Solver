package cz.hartrik.wordsearch;

import cz.hartrik.wordsearch.solve.NumericComparator;
import cz.hartrik.wordsearch.solve.Solver;
import cz.hartrik.wordsearch.solve.WsDictionaryUser;

/**
 * Vytváří náhled vyřešené osmisměrky.
 *
 * @version 2013-09-25
 * @author Patrik Harag
 */
public class HtmlPreview {
    
    private final char[][] array;
    private final int[][] values;
    
    public static String startDocument = "<html style=\""
            + "background-color: rgb(50, 50, 50);"
            + "font-family: Courier New;"
            + "letter-spacing: 3px\">";
    public static String endDocument = "</html>";
    
    public String colorSelected = "Yellow";
    public String colorMatch = "DarkCyan";
    public String colorOther = "Red";


    public HtmlPreview(char[][] array, int[][] values) {
        this.array = array;
        this.values = values;
    }
    
    /**
     * Vrátí náhled vyřešené osmisměrky.
     * 
     * @return náhled v HTML
     */
    public String createPreview() {
        return write(null);
    }
    
    /**
     * Vrátí náhled vyřešené osmisměrky s označeným slovem.
     * 
     * @param selected označené slovo
     * @return náhled v HTML
     */
    public String createPreview(String selected) {
        final NumericComparator comparator = new NumericComparator(array.length,
                array[0].length, new WsDictionaryUser(selected));
        new Solver(array, comparator).solve();
        
        return write(comparator.getResult());
    }
    
    protected String write(int[][] selected) {
        boolean isSelected = selected != null;
        StringBuilder builder = new StringBuilder(startDocument);
        
        final int columns = array.length;
        final int rows = array[0].length;
        
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                int value = values[i][j];
                boolean match = value > 0;
                
                String color;
                if (isSelected && selected[i][j] > 0) color = colorSelected;
                else if (match) color = colorMatch;
                else color = colorOther;
                
                addChar(builder, array[i][j], color, value);
            }
            builder.append("<br>");
        }
        return builder.append(endDocument).toString();
    }
    
    protected void addChar(StringBuilder builder, char character, String color,
            int value) {
        
        builder.append("<font color=\"")
                    .append(color)
                    .append("\">")
                    .append((character + "").toUpperCase())
                .append("</font>")
                .append("<sup>")
                    .append(value)
                .append("</sup>");
    }
    
}