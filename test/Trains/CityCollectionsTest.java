package Trains;

import junit.framework.Assert;

public class CityCollectionsTest {
    CityCollections cityCollections = new CityCollections();
    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void testAddRoute() throws Exception {
        cityCollections.addRoute("A", "B", 10);
        cityCollections.addRoute("B", "C", 10);
        cityCollections.addRoute("C", "D", 10);
        cityCollections.addRoute("B", "D", 10);

        Assert.assertTrue(cityCollections.toString().contains("City:A neighbors [B,10]"));
        Assert.assertTrue(cityCollections.toString().contains("City:B neighbors [D,10][C,10]"));
        Assert.assertTrue(cityCollections.toString().contains("City:C neighbors [D,10]"));
    }

}
