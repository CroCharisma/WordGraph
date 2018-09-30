
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordGraph extends JPanel {

    /**
     * Width, in pixels, of the gap between bars
     */
    public static final int BAR_GAP = 20;

    /**
     * Horizontal position, in pixels, of where the bars should start
     */
    public static final int BAR_START = 100;

    private Map<String, Integer> words;

    public WordGraph(File fi) throws FileNotFoundException {
        words = new TreeMap<String , Integer>();
        Scanner endme = new Scanner( fi );
        Scanner f;
        while( endme.hasNextLine() ){
            
            f = new Scanner( endme.nextLine() );
            
            while( f.hasNext() ){
                
                addWord( f.next() );
                
            }
            
        }
    }

    /**
     * Add a word to the map, or increment the count if that word is already
     * there.
     *
     * Comparisons should be case insensitive. Only add the all lower cased
     * version of a string to the map.
     *
     * @param 
     */
    private void addWord(String w) {
        if (words.containsKey(w.toLowerCase())) {
            words.put(w.toLowerCase(), words.get(w.toLowerCase())+1);
        } else {
            words.put(w.toLowerCase(), 1);
        }

    }

    /**
     * Get the height of a bar.
     *
     * All bars are the same height based on the number of words and the gap
     * between each word.
     *
     * @return
     */
    private int getBarHeight() {
        return ( 600 - ( words.size() - 1 ) * 20 ) / words.size();
    }

    /**
     * Get the width of a bar.
     *
     * Each bar can be a different width depending on how many times that word
     * occurs in the data set.
     *
     * @param w
     * @return
     */
    private int getBarWidth( String w ) {
        double a = ( 800 - BAR_START ) * ( 1.0 * words.get( w ) / getMaxCount() );
        return (int) a;
    }

    /**
     * Get the count of the most common word.
     *
     * @return
     */
    private int getMaxCount(){
        
        int big = -1;
        for( int a : words.values() ){
            if( a > big ){
                big = a;
            }
        }
        return big;
        
    }

    /**
     * Get the number of distinct words that are in the map
     *
     * @return
     */
    private int getWordCount() {
        return words.size();
    }

    public void update(Graphics window) {
        paint(window);
    }

    public void paint(Graphics window) {

        window.setColor( Color.BLACK );
        window.setFont( new Font ( "Comic Sans MS" , Font.PLAIN , 12 ) );
        int yPos = 0; // hint, hint
        for( Map.Entry <String,Integer> ent : words.entrySet()  ){
            window.drawString( ent.getKey() , 7 , (yPos += BAR_GAP * 2) );
            window.setColor( getRandomColor() );
            window.fillRect( BAR_START , yPos -15 , getBarWidth(ent.getKey()) , getBarHeight() );
            window.setColor(Color.BLACK);
        }
        

    }

    /**
     * Return a random color to use when drawing a bar
     *
     * @return
     */
    private Color getRandomColor() {
        return new Color( (int)(Math.random() * 255 ) , (int)(Math.random() * 255 ) , (int)(Math.random() * 255 ));
    }

}