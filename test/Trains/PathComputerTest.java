package Trains;

import functor.Comparator;
import functor.Equal;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

public class PathComputerTest {
    InputParser inputParser = null;
    CityCollections cities = null;
    @Before
    public void setUp() throws Exception {
        inputParser= new InputParser();
        cities = inputParser.parser("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
    }

    @Test
    public void testFindPathWithComparatorEqual() throws Exception {
        PathComputer pathComputer = new PathComputer();
        Path path = pathComputer.findPath(cities, "C", "C", 2, new Equal());

        Assert.assertTrue(path.containsKey("CDC"));

        path = pathComputer.findPath(cities, "C", "C", 3, new Equal());
        Assert.assertTrue(path.containsKey("CEBC"));

        path = pathComputer.findPath(cities, "A", "C", 4, new Equal());
        Assert.assertTrue(path.size() == 3);
        Assert.assertTrue(path.containsKey("ABCDC"));
        Assert.assertTrue(path.containsKey("ADCDC"));
        Assert.assertTrue(path.containsKey("ADEBC"));
    }


    @Test
    public void testShortestPath() throws Exception {
        PathComputer pathComputer = new PathComputer();
        Path path = pathComputer.findShortestPath(cities, "A", "C");

        Assert.assertTrue(path.size() == 1);
        Assert.assertTrue(path.containsKey("ABC"));
        Assert.assertTrue(path.get("ABC") == 9);

        path = pathComputer.findShortestPath(cities, "B", "B");

        Assert.assertTrue(Collections.min(path.values()) == 9);
    }

    @Test
    public void testGetPathLength() throws Exception {
        PathComputer pathComputer = new PathComputer();
        Assert.assertTrue(pathComputer.getPathLength(cities, "A-B-C").equals("9"));
        Assert.assertTrue(pathComputer.getPathLength(cities, "A-D").equals("5"));
        Assert.assertTrue(pathComputer.getPathLength(cities, "A-D-C").equals("13"));
        Assert.assertTrue(pathComputer.getPathLength(cities, "A-E-B-C-D").equals("22"));
        Assert.assertTrue(pathComputer.getPathLength(cities, "A-E-D").equals("NO SUCH ROUTE"));
    }

    @Test
    public void testDifferentRouteLessThanMaxLength() throws Exception {
        PathComputer pathComputer = new PathComputer();
        Path path = pathComputer.findPath(cities, "C", "C", 30);

        Assert.assertTrue(path.containsKey("CEBCEBCEBC"));
        Assert.assertTrue(path.containsKey("CDEBC"));
        Assert.assertTrue(path.containsKey("CEBC"));
        Assert.assertTrue(path.containsKey("CEBCEBC"));
        Assert.assertTrue(path.containsKey("CDC"));
        Assert.assertTrue(path.containsKey("CDCEBC"));
        Assert.assertTrue(path.containsKey("CEBCDC"));
    }
}
