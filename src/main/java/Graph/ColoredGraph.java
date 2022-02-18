package Graph;

import POJO.Node;

import java.util.HashMap;

public class ColoredGraph extends Graph {

    private Color currentColor;

    public ColoredGraph(Graph graph) {
        this.adjList = graph.adjList;
        this.allEdges = graph.allEdges;
        this.setStart(graph.getStart());
        this.currentColor = Color.NONE;
    }

    @Override
    public void DFSOrient(Node v, HashMap<Edge, Boolean> visitedEdges) {
        for (int i=0; i<adjList.get(v).size(); i++) {

            Node n = adjList.get(v).get(i);

            var pair = new Edge(v, n);
            var inversePair = new Edge(n, v);

            if (!visitedEdges.containsKey(pair) && !visitedEdges.containsKey(inversePair)) {

                this.allEdges.get(allEdges.indexOf(pair)).color = currentColor;
                pair.setColor(currentColor);

                System.out.println(pair);
                visitedEdges.put(pair, true);
                RemoveDirectedEdge(n, v);

                DFSOrient(n, visitedEdges);
            }
        }
    }

    public void ColorAndOrientGraph(Node s) {
        //If it's not connected or has any bridges Then the solution is not Feasible
        if (!CheckIsConnected(s)) {
            System.out.println("Cannot orient the graph to one way street." +
                    "The graph provided is not strongly connected!");
            return;
        }
        if (HasBridges()) {
            System.out.println("Cannot orient the graph to one way street." +
                    "The graph provided has at least one bridge");
            return;
        }

        currentColor = Color.RED;
        DFS(getStart());

        currentColor = Color.GREEN;
        DFS(getStart());
    }
}
