package Graph;

import POJO.Node;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ColoredGraph extends Graph {

    private Color currentColor;
    private Lane currentLane;

    public ColoredGraph(Graph graph) {
        this.adjList = graph.adjList;
        this.allEdges = graph.allEdges;
        this.setStart(graph.getStart());
        this.currentColor = Color.NONE;
    }


    public void DFSOrientOneLane(Node v, HashMap<Edge, Boolean> visitedEdges) {
        for (int i=0; i<adjList.get(v).size(); i++) {

            Node n = adjList.get(v).get(i);

            Edge edge = getEdgeFromEndpoints(v, n, currentLane);
            Edge inverseEdge = getEdgeFromEndpoints(n, v, currentLane);

            if (isLaneNotVisited(visitedEdges, edge, inverseEdge)) {
                this.allEdges.get(allEdges.indexOf(edge)).color = currentColor;
                edge.setColor(currentColor);

                System.out.println(edge);
                visitedEdges.put(edge, true);
                RemoveDirectedEdge(n, v);

                DFSOrientOneLane(n, visitedEdges);
            }
        }
    }

    private Edge getEdgeFromEndpoints(Node a, Node b, Lane lane) {
        return this.allEdges
                .stream()
                .filter((Edge e) -> e.nodeA.equals(a) && e.nodeB.equals(b))
                .collect(Collectors.toList())
                .stream()
                .filter((Edge e) -> e.lane.equals(lane))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    private boolean isLaneNotVisited(HashMap<Edge, Boolean> visitedEdges, Edge edge, Edge inverseEdge) {
        return !visitedEdges.containsKey(edge) && !visitedEdges.containsKey(inverseEdge);
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

        HashMap<Edge, Boolean> visitedEdges = new HashMap<Edge, Boolean>();

        System.out.println("\n---------------\n Coloring COVID RED lanes\n---------------\n");
        currentLane = Lane.LANE01;
        currentColor = Color.RED;
        DFSOrientOneLane(getStart(), visitedEdges);

        System.out.println("\n---------------\n Coloring Non-COVID GREEN lanes\n---------------\n");
        currentLane = Lane.LANE02;
        currentColor = Color.GREEN;
        DFSOrientOneLane(getStart(), visitedEdges);
    }
}
