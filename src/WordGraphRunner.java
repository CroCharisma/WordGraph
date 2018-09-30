import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;


public class WordGraphRunner extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static void main(String[] args) throws FileNotFoundException {
        new WordGraphRunner(); 
    }

    public WordGraphRunner() throws FileNotFoundException {
        super("WordGraph");

        setSize(WIDTH, HEIGHT);

        WordGraph graph = new WordGraph(new File("src//words.dat"));
        
        /* Once you get it working, swap out graph for the following two
         * files to double check your work. 
         */
        
        //WordGraph graph = new WordGraph(new File("src//moreWords.dat")); 
        //WordGraph graph = new WordGraph(new File("src//meat.dat")); 
        
        ((Component) graph).setFocusable(true);

        getContentPane().add(graph);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
