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
        this.childNodes.get(v).add(u);
    }

    public void RemoveEdge(int u, int v)
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
        return CountNodesBFS(s) == this.size -1;
    }

}
