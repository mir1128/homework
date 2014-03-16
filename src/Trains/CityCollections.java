package Trains;

import java.util.HashMap;
import java.util.Map;

public class CityCollections extends HashMap<String, City>{
    public void addRoute(String start, String end, Integer length){
        if (containsKey(start)){
            get(start).put(end, length);
        }else {
            City city = new City(start);
            city.put(end, length);
            put(start, city);
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, City> e : entrySet()){
            sb.append("City:" + e.getKey() + " neighbors " + e.getValue().toString() + "\n");
        }
        return sb.toString();
    }
}

