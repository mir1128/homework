package Trains;

import junit.framework.Assert;
import org.junit.Test;

public class InputParserTest {
    @Test
    public void testParser() throws Exception {
        InputParser inputParser = new InputParser();
        CityCollections cityCollections = inputParser.parser("AB5, BC4, CD8");

        Assert.assertTrue(cityCollections.toString().contains("City:A neighbors [B,5]"));
        Assert.assertTrue(cityCollections.toString().contains("City:B neighbors [C,4]"));
        Assert.assertTrue(cityCollections.toString().contains("City:C neighbors [D,8]"));

    }

    @Test
    public void testParser2() throws Exception {
        InputParser inputParser = new InputParser();
        CityCollections cityCollections = inputParser.parser("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");

        System.out.println(cityCollections.toString());
    }


}
