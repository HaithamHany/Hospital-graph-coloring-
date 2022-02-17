package Graph;

import POJO.Node;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Graph {
    // Contains the child nodes for each vertex of the graph
    private HashMap<Node, ArrayList<Node>> adjList;
    private ArrayList<Pair<Node, Node>> allEdges;

    private Node start;

    public Graph() {
        this.adjList = new HashMap<>();
        this.allEdges = new ArrayList<Pair<Node, Node>>();
    }

    public Graph(Node start) {
        this.adjList = new HashMap<>();
        this.allEdges = new ArrayList<Pair<Node, Node>>();
        this.start = start;
    }

    public int getSize() {
        return this.adjList.size();
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    //region common
    /**
     * Adds edge between 2 nodes
     *
     * @param u src
     * @param v destination
     */
    public void AddUndirectedEdge(Node u, Node v) {
        if (!adjList.containsKey(u))
        {
            adjList.put(u, new ArrayList<Node>(List.of(v)));
            UpdateEdges(u,v);
        }
        else
        {
            adjList.get(u).add(v);
        }

        if (!adjList.containsKey(v))
        {
            adjList.put(v, new ArrayList<Node>(List.of(u)));
            UpdateEdges(v,u);
        }
        else
        {
            adjList.get(v).add(u);
        }
    }

    /**
     * Saves Edges as pairs of nodes
     *
     * @param u src
     * @param v Destination
     */
    public void UpdateEdges(Node u, Node v) {
        var edge = new Pair<Node, Node>(u, v);
        allEdges.add(edge);
    }

    /**
     * Removing Undirected edge
     *
     * @param u src node
     * @param v destination node
     */
    public void RemoveEdge(Node u, Node v) {
        if (this.adjList.containsKey(u))
            this.adjList.get(u).remove(v);
        if (this.adjList.containsKey(v))
            this.adjList.get(v).remove(u);
    }

    /**
     * Removinf Undirected Edge
     *
     * @param u
     * @param v
     */
    public void RemoveDirectedEdge(Node u, Node v) {
        this.adjList.get(u).remove(v);
    }


    /**
     * Checks if there is an edge between 2 nodes
     */
    public Boolean HasEdge(Node u, Node v) {
        return adjList.get(u).contains(v);
    }

    public ArrayList<Node> GetAdjacent(Node v) {
        return adjList.get(v);
    }


    private int CountNodesBFS(Node s) {
        HashSet<Node> visited = new HashSet<Node>();
        int count = 0;

        Queue<Node> queue = new LinkedList<>();

        visited.add(s);
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            count++;

            Iterator<Node> i = this.adjList.get(s).listIterator();
            while (i.hasNext()) {
                Node n = i.next();
                if (!visited.contains(n)) {
                    visited.add(n);
                    queue.add(n);
                }
            }
        }

        return count;
    }

    /**
     * Checks if the number of nodes counted is equal to the number of nodes of G,
     * the graph is connected, otherwise it is disconnected
     */
    public boolean CheckIsConnected(Node s) {
        return CountNodesBFS(s) == this.getSize();
    }
// endregion


    // region One way orientation problem

    public boolean HasBridges() {
        var hasBridge = false;

        for (int i = 0; i < allEdges.size(); i++) {

            var sourceVertex = allEdges.get(i).key;
            var destinationVertex = allEdges.get(i).value;

            //Removing edges from the graph
            RemoveEdge(sourceVertex, destinationVertex);

            //checking if graph is connected after removing the edge from the first node
            var isConnected = CheckIsConnected(start);

            //Adding directed edge back
            AddUndirectedEdge(sourceVertex, destinationVertex);

            if (!isConnected) {
                hasBridge = true;
                break;
            }
        }

        return hasBridge;
    }

    public void DFSOneWay(Node v, ConcurrentHashMap<Node, Boolean> visited)
    {
        visited.put(v, true);

        for (int i=0; i<adjList.get(v).size(); i++) {
            Node n = adjList.get(v).get(i);
            RemoveDirectedEdge(n, v);

            if (!visited.containsKey(n)) {
                System.out.println(v.name + " - " + n.name);
                DFSOneWay(n, visited);
            }
            else if(visited.size() == getSize() && n.isEntrance) {
                visited.put(n, false);
                System.out.println(v.name + " - " + n.name);
                DFSOneWay(n, visited);
            }
        }
    }

    /**
     * Function used to orient one way street problem
     * @param v Staring Node
     */
    public void DFSOrient(Node v)
    {
        ConcurrentHashMap<Node, Boolean> visited = new ConcurrentHashMap<>();
        visited.put(v, true);
        DFSOneWay(v, visited);
    }

    public void OneWayStreetOrientation(Node s) {
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

        DFSOrient(s);
    }

    //endregion

    /**
     * Simple traversing using BFS
     * @param start the staring node
     */
    public void Traverse(Node start) {
        Queue<Node> q = new LinkedList<>();
        HashSet<Node> visitedList = new HashSet<>();
        visitedList.add(start);
        q.add(start);

        while (!q.isEmpty()) {
            Node current = q.remove();
            ArrayList<Node> adjacentlist = adjList.get(current);
            for (Node x : adjacentlist) {
                if (!visitedList.contains(x)) {
                    visitedList.add(x);
                    q.add(x);
                    System.out.println(current.name + " - " + x.name);
                }
            }
        }
    }
}
