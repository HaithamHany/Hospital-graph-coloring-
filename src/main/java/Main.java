import Graph.Graph;
import POJO.FloorMapJSONParser;

public class Main {

    public static void main(String[] args)
    {
        Graph graph = FloorMapJSONParser.parseJSON("MixedLanesHospital.json");
        //Traversing starting from index 0
        graph.OneWayStreetOrientation(graph.getStart());
    }
}
