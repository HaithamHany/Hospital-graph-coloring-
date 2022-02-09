package com.graph;

import java.util.*;

public class Graph
{
    // Contains the child nodes for each vertex of the graph
    private ArrayList<ArrayList<Integer>> childNodes;
    private Integer size;
    private ArrayList<Pair<Integer, Integer>> allEdges;

    public Graph(Integer size)
    {
        this.size = size;
        this.childNodes = new ArrayList<ArrayList<Integer>>();
        this.allEdges = new ArrayList<Pair<Integer, Integer>>();

        for (int i = 0; i < this.size; i++) {

            //Assigning an empty list of adjacent nodes for each vertex
            childNodes.add(new ArrayList<Integer>());
        }
    }

    /**
     * Returns the number of nodes in the graph
     * */
    public Integer GetSize()
    {
        return size;
    }

    /**
     * Adds edge between 2 nodes
     * @param u src
     * @param v destination
     * */
    public void AddEdge(int u, int v)
    {
        this.childNodes.get(u).add(v);
        this.childNodes.get(v).add(u);
    }

    /**
     * Saves Edges as pairs
     * @param u src
     * @param v Destination
     */
    public void UpdateEdges(int u, int v)
    {
        var edge = new Pair<Integer, Integer>(u, v);
        allEdges.add(edge);
    }

    /**
     * Removing Undirected edge
     * @param u src node
     * @param v destination node
     */
    public void RemoveEdge(Integer u, Integer v)
    {
        this.childNodes.get(u).remove(v);
        this.childNodes.get(v).remove(u);
    }

    /**
     * Removinf Undirected Edge
     * @param u
     * @param v
     */
    public void RemoveDirectedEdge(Integer u, Integer v)
    {
        this.childNodes.get(u).remove(v);
    }


    /**
     * Checks if there is an edge between 2 nodes
     * */
    public Boolean HasEdge(int u, int v)
    {
        var hasEdge = childNodes.get(u).contains(v);
        return hasEdge;
    }

    public ArrayList<Integer> GetAdjacent(int v)
    {
        return childNodes.get(v);
    }


    private int CountNodesBFS(int s)
    {
        boolean visited[] = new boolean[this.size];
        int count = 0;

        Queue <Integer> queue = new LinkedList<Integer>();

        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0)
        {
            s = queue.poll();
            count++;

            Iterator<Integer> i = this.childNodes.get(s).listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
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
    public boolean CheckIsConnected(int s)
    {
        return CountNodesBFS(s) == this.size;
    }

    public boolean HasBridges()
    {
        var hasBridge = false;

        for (int i = 0; i < allEdges.size(); i++) {

            var sourceVertex = allEdges.get(i).key;
            var destinationVertex = allEdges.get(i).value;

            //Removing edges from the graph
            RemoveEdge(sourceVertex, destinationVertex);

            //checking if graph is connected after removing the edge from the first node
            var isConnected = CheckIsConnected(0);

            //Adding directed edge back
            AddEdge(sourceVertex, destinationVertex);

            if(!isConnected) {
                hasBridge = true;
                break;
            }
        }

        return hasBridge;
    }

    public void OneWayStreetOrientation(Integer s)
    {
        //If it's not connected or has any bridges Then the solution is not Feasible
        if(!CheckIsConnected(s) || HasBridges())
        {
            System.out.println("Cannot orient the graph to one way street." +
                    "The graph provided is either not strongly connected or has a bridge");
            return;
        }

        boolean visited[] = new boolean[this.size];
        int count = 0;

        Queue <Integer> queue = new LinkedList<Integer>();

        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0)
        {
            s = queue.poll();
            count++;

            Iterator<Integer> i = this.childNodes.get(s).listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                RemoveDirectedEdge(n, s);
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

    }


}
