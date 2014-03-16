package Trains;

public class InputParser {
    public CityCollections parser(String inputMap){
        CityCollections cityCollections = new CityCollections();

        for (String s : inputMap.split(",")){
            s = s.trim();
            cityCollections.addRoute(s.substring(0,1), s.substring(1,2), Integer.parseInt(s.substring(2)));
        }

        return cityCollections;
    }
}


