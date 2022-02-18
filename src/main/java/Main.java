import Graph.ColoredGraph;
import Graph.Graph;
import POJO.FloorMapJSONParser;

public class Main {

    public static void main(String[] args)
    {
        Graph graph = FloorMapJSONParser.parseJSON("figA-2lanes.json");
        ColoredGraph coloredGraph = new ColoredGraph(graph);

        coloredGraph.ColorAndOrientGraph(graph.getStart());
    }
}
