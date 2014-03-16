package Trains;

import functor.Comparator;

import java.util.HashMap;
import java.util.Map;

public class Path extends HashMap<String, Integer>{
    public Path getSpecificPath(int stops, Comparator comparator){
        Path path = new Path();

        for (Map.Entry<String, Integer> e : entrySet()){
            if (comparator.compare(e.getKey().length(), stops)){
                path.put(e.getKey(), e.getValue());
            }
        }
        return path;
    }
}

