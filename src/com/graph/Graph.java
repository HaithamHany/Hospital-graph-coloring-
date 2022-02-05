package com.graph;

import java.util.*;

public class Graph
{
    // Contains the child nodes for each vertex of the graph
    private ArrayList<ArrayList<Integer>> childNodes;
    private Integer size;

    public Graph(Integer size)
    {
        this.size = size + 1;
        this.childNodes = new ArrayList<ArrayList<Integer>>();

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

    public ArrayList<Integer> GetAdjacent(int v)
    {
        return childNodes.get(v);
    }

    public void TraverseDFS(Integer v)
    {
        var visited = new boolean[this.size];
        DFS(v, visited);
    }

    private void DFS(Integer v, boolean [] visited)
    {
        if (!visited[v])
        {
            System.out.print(v + " ");
            visited[v] = true;
            for (Integer child : GetAdjacent(v))
            {
                DFS(child, visited);
            }
        }
    }


}
