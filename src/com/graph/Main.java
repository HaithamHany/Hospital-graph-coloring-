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
        graph.TraverseDFS(5);

        //checking for connection
        System.out.println("Is node connected? "+graph.CheckIsConnected(1));

    }
}
