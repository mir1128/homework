package Trains;

import java.util.HashMap;
import java.util.Map;

public class City extends HashMap<String, Integer> {
    private String name;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> e : entrySet()){
            sb.append("[" + e.getKey() + "," + e.getValue() + "]");
        }
        return sb.toString();
    }
}
