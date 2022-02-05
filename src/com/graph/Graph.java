package com.graph;

import java.util.*;

public class Graph
{
    // Contains the child nodes for each vertex of the graph
    private ArrayList<ArrayList<Integer>> childNodes;

    public Graph(Integer size)
    {
        this.childNodes = new ArrayList<ArrayList<Integer>>(size);
        for (int i = 0; i < size; i++)
        {
            //Assigning an empty list of adjacent nodes for each vertex
            var childNode = childNodes.get(i);
            childNode = new ArrayList<Integer>();
        }
    }

    /**
     * Returns the number of nodes in the graph
     * */
    public Integer GetSize()
    {
        return this.childNodes.size();
    }

    /**
     * Adds edge between 2 nodes
     * */
    public void AddEdge(int u, int v)
    {
        this.childNodes.get(u).add(v);
    }

    /**
     * Checks if there is an edge between 2 nodes
     * */
    public Boolean HasEdge(int u, int v)
    {
        var hasEdge = childNodes.get(u).contains(v);
        return hasEdge;
    }

}
