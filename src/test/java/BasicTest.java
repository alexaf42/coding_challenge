import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Tests for my functions
 */
public class BasicTest {

    @Test
    public void testCount() {
        try {
            wordCount.count("src/main/java/test.txt");
            File output = new File("src/test/java/output.txt");
            File expected = new File("src/test/java/expected.txt");
            Scanner outputReader = new Scanner(output);
            Scanner expectedReader = new Scanner(expected);
            while (outputReader.hasNextLine() && expectedReader.hasNextLine()) {
                String outputLine = outputReader.nextLine();
                String expectedLine = expectedReader.nextLine();
                assertEquals(outputLine,expectedLine);
            }
        }catch (FileNotFoundException e) {
                System.out.println("Make sure that all required files are in the current directory!");
                fail();
        }


    }

    @Test
    public void testWalk() {
        Node e = new Node("E");
        Node f = new Node("F");
        Node[] bChildren = new Node[]{e, f};
        Node b = new Node("B", bChildren);
        Node g = new Node("G");
        Node h = new Node("H");
        Node i = new Node("I");
        Node[] cChildren = new Node[]{g, h, i};
        Node c = new Node("C", cChildren);
        Node j = new Node("J");
        Node[] dChildren = new Node[]{j};
        Node d = new Node("D", dChildren);
        Node[] aChildren = new Node[]{b, c, d};
        Node a = new Node("A", aChildren);
        ArrayList walked = new ArrayList<>(List.of(a, b, c, d, e, f, g, h, i, j));
        ArrayList<Node> walkList  = Node.walkGraph(a);
        assertEquals(walkList, walked);
        
    }

    @Test
    public void testPaths() {
        Node e = new Node("E");
        Node f = new Node("F");
        Node[] bChildren = new Node[]{e, f};
        Node b = new Node("B", bChildren);
        Node g = new Node("G");
        Node h = new Node("H");
        Node i = new Node("I");
        Node[] cChildren = new Node[]{g, h, i};
        Node c = new Node("C", cChildren);
        Node j = new Node("J");
        Node[] dChildren = new Node[]{j};
        Node d = new Node("D", dChildren);
        Node[] aChildren = new Node[]{b, c, d};
        Node a = new Node("A", aChildren);
        ArrayList fifth = new ArrayList(List.of(a,b,e));
        ArrayList sixth = new ArrayList(List.of(a,b,f));
        ArrayList second = new ArrayList(List.of(a,c,g));
        ArrayList third = new ArrayList(List.of(a,c,h));
        ArrayList fourth = new ArrayList(List.of(a,c,i));
        ArrayList first = new ArrayList(List.of(a,d,j));
        ArrayList correctPaths = new ArrayList(List.of(first,second,third,fourth,fifth,sixth));
        ArrayList pathList  = Node.paths(a);
        assertEquals(pathList, correctPaths);

    }
}