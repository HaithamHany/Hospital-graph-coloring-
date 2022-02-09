package com.graph;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        GraphConstructor graphConstructor = new GraphConstructor();
        var verticesCount = graphConstructor.GetNumberOfVertices();

        Graph graph = new Graph(verticesCount);
        graphConstructor.ConstructGraph(graph);

        //Traversing starting from index 0
        graph.TraverseDFS(0);
         System.out.println();
        //checking for connection
        System.out.println("Is connected? "+graph.CheckIsConnected(0));
        System.out.println("Has bridges? "+graph.HasBridges());
    }
}
