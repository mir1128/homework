package Trains;

import functor.Comparator;

import java.util.Collections;
import java.util.Map;
import java.util.Vector;

class CityTreeNode{
    public String           cityName;
    public CityTreeNode     parent;
    public int              length;

    CityTreeNode(String cityName, CityTreeNode parent, int length) {
        this.cityName = cityName;
        this.parent = parent;
        this.length = length;
    }
}

class CityTreeBuilder{
    public static final int T_MAX_LENGTH = 1;
    public static final int T_MAX_STOPS  = 2;

    private int maxValue = 0;
    private int type = T_MAX_LENGTH;

    CityTreeBuilder(int maxValue, int type) {
        this.maxValue = maxValue;
        this.type = type;
    }

    public  Vector<CityTreeNode> buildPathTree(CityCollections cities, String start){
        if (type == T_MAX_STOPS){
            return buildPathTreeWithMaxStops(cities, start, maxValue);
        }else {
            return buildPathTreeWithMaxLength(cities, start, maxValue);
        }
    }


    private Vector<CityTreeNode> buildPathTreeWithMaxStops(CityCollections cities, String start, int maxStops) {
        Vector<CityTreeNode> layerNodes1 = new Vector<CityTreeNode>();
        Vector<CityTreeNode> layerNodes2 = new Vector<CityTreeNode>();
        Vector<CityTreeNode> allNodes = new Vector<CityTreeNode>();

        CityTreeNode root = new CityTreeNode(start, null, 0);
        layerNodes1.add(root);

        for (int layers = 0; layers < maxStops; ++layers){
            if (layerNodes1.isEmpty()){
                break;
            }

            while (!layerNodes1.isEmpty()){
                CityTreeNode cityNode = layerNodes1.get(0);
                layerNodes1.remove(0);

                for (Map.Entry<String, Integer> e : cities.get(cityNode.cityName).entrySet()){
                    layerNodes2.add(new CityTreeNode(e.getKey(), cityNode, 0));
                }
            }
            layerNodes1.addAll(layerNodes2);
            allNodes.addAll(layerNodes2);
            layerNodes2.clear();
        }

        return allNodes;
    }

    private Vector<CityTreeNode> buildPathTreeWithMaxLength(CityCollections cities, String start, int maxLength) {
        Vector<CityTreeNode> layerNodes1 = new Vector<CityTreeNode>();
        Vector<CityTreeNode> layerNodes2 = new Vector<CityTreeNode>();
        Vector<CityTreeNode> allNodes = new Vector<CityTreeNode>();

        CityTreeNode root = new CityTreeNode(start, null, 0);
        layerNodes1.add(root);

        while (true){
            while (!layerNodes1.isEmpty()) {
                CityTreeNode cityNode = layerNodes1.get(0);
                layerNodes1.remove(0);

                for (Map.Entry<String, Integer> e : cities.get(cityNode.cityName).entrySet()) {
                    if (e.getValue() + cityNode.length >= maxLength){
                        continue;
                    }
                    layerNodes2.add(new CityTreeNode(e.getKey(), cityNode, e.getValue()+cityNode.length));
                }
            }
            if (layerNodes2.isEmpty()){
                break;
            }
            layerNodes1.addAll(layerNodes2);
            allNodes.addAll(layerNodes2);
            layerNodes2.clear();
        }

        return allNodes;
    }
}

public class PathComputer {

    public String getPathLength(CityCollections allCities, String path){

        String [] citiesInPath = path.split("-");
        City currentCity = allCities.get(citiesInPath[0]);
        int length = 0;
        for (int i = 1; i < citiesInPath.length; ++i){
            if (!currentCity.containsKey(citiesInPath[i])){
                return "NO SUCH ROUTE";
            }
            length += currentCity.get(citiesInPath[i]);

            currentCity = allCities.get(citiesInPath[i]);
        }
        return "" + length;
    }

    public Path findShortestPath(CityCollections cities, String start, String end){
        Path path = findPath(cities, start, end, cities.size()-1, CityTreeBuilder.T_MAX_STOPS);

        Path result = new Path();
        if (path.size() == 0){
            return result;
        }

        int shortest = Collections.min(path.values());

        for (Map.Entry<String, Integer> e : path.entrySet()){
            if (e.getValue() == shortest){
                result.put(e.getKey(), shortest);
            }
        }
        return result;
    }

    public Path findPath(CityCollections cities, String start, String end, int maxStops, Comparator comparator){
        Path path = findPath(cities, start, end, maxStops, CityTreeBuilder.T_MAX_STOPS);

        Path result = new Path();
        for (Map.Entry<String, Integer> e : path.entrySet()){
            if (comparator.compare(e.getKey().length()-1, maxStops)){
                result.put(e.getKey(), e.getValue());
            }
        }
        return result;
    }

    public Path findPath(CityCollections cities, String start, String end, int maxLength){
        Path path = findPath(cities, start, end, maxLength, CityTreeBuilder.T_MAX_LENGTH);
        return path;
    }

    private Path findPath(CityCollections cities, String start, String end, int maxValue, int findType){

        Path path = new Path();
        CityTreeBuilder cityTreeBuilder = new CityTreeBuilder(maxValue, findType);
        Vector<CityTreeNode> pathTree = cityTreeBuilder.buildPathTree(cities, start);

        for (CityTreeNode node : pathTree){
            if (node.cityName.equals(end)){
                getOnePath(path, node, cities);
            }
        }

        return path;
    }

    private void getOnePath(Path path, CityTreeNode node, CityCollections cities) {
        CityTreeNode parent = node.parent;
        String pathInfo = "";
        int length = 0;
        while (parent != null){
            pathInfo = node.cityName + pathInfo;
            length += cities.get(parent.cityName).get(node.cityName);
            node = parent;
            parent = node.parent;
        }

        pathInfo = node.cityName + pathInfo;
        path.put(pathInfo, length);
    }
}
