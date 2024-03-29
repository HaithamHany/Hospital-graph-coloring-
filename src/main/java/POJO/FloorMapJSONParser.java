package POJO;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import Graph.Graph;
import Graph.Lane;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class FloorMapJSONParser {

    public static final String JSON_DIR = "./data/";

    public static Graph parseJSON(String filename) {
        Gson g = new Gson();
        try{
            JsonReader reader = new JsonReader(new FileReader(JSON_DIR + filename));
            FloorMap floorMap = g.fromJson(reader, FloorMap.class);
            ArrayList<Node> nodes = new ArrayList<>(Arrays.asList(floorMap.nodes));

            Graph graph = new Graph();
            for (Node node : nodes) {
                Node n = new Node(node.name, node.isEntrance, node.isExit, node.hasDoubleEdge);
                if(n.isEntrance)
                    graph.setStart(n);
                for (String neighbor : node.neighbors){
                    graph.AddDirectedEdge(n, toNode(nodes, neighbor), Lane.LANE01);
                    if(floorMap.isAll2Lanes) {
                        graph.AddDirectedEdge(n, toNode(nodes, neighbor), Lane.LANE02);
                    }
                }
            }
            return graph;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }

    public static Node toNode(ArrayList<Node> nodes, String name) throws Exception {
        for (Node n: nodes) {
            if(n.name.equals(name))
                return new Node(name, n.isEntrance, n.isExit, n.hasDoubleEdge);
        }
        throw new Exception("Node " + name + " Not Found");
    }
}
