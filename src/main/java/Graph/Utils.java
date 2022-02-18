package Graph;

import POJO.Node;

import java.util.HashMap;

public class Utils {

    public static void DFS(Graph graph, Node v)
    {
        HashMap<Edge, Boolean> visited = new HashMap<Edge, Boolean>();
        DFSOrient(graph, v, visited);
    }


    public static void DFSOrient(Graph graph, Node v, HashMap<Edge, Boolean> visitedEdges)
    {
        for (int i=0; i<graph.adjList.get(v).size(); i++) {

            Node n = graph.adjList.get(v).get(i);

            var pair = new Edge(v, n);

            if (!visitedEdges.containsKey(pair)) {
                System.out.println(v.name + " - " + n.name);
                visitedEdges.put(pair, true);
                graph.RemoveDirectedEdge(n, v);

                DFSOrient(graph, n, visitedEdges);
            }

        }
    }
}
