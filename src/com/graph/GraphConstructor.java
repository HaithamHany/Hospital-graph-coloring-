package com.graph;
import java.io.*;
import java.util.*;

public class GraphConstructor {

    private final String FILE_NAME ="graph.txt";
    private ArrayList<String> fileLines;

    public GraphConstructor()
    {
        fileLines = ReadFromFile();
    }

    public Integer GetNumberOfVertices()
    {
        //first line always is special since it holds the number of vertices
        var firstLine = fileLines.get(0);
        var numberOfVertices = Integer.parseInt(firstLine);
        return numberOfVertices;
    }
    public void ConstructGraph(Graph graph)
    {
        var nodesMap = ParseGraphFileLines(fileLines);

        for (Map.Entry<Integer, ArrayList<Integer>> pair: nodesMap.entrySet()) {

            var node = pair.getKey();
            var adjacentNodes = pair.getValue();

            for (var adjNode :  adjacentNodes) {
                graph.AddEdge(node, adjNode);
            }
        }
    }

    /**
     * Converts lines to node and it's adjacent nodes
     * */
    private HashMap<Integer, ArrayList<Integer>>  ParseGraphFileLines(ArrayList<String> lines)
    {
        var nodesMap = new HashMap<Integer,ArrayList<Integer>>();

        //starting from 1 because index 0 is always reserved for number of vertices
        for (int i = 1; i < lines.size(); i++) {

            var node = GetNode(lines.get(i)); //getting the first node
            var adjacentNodes = ParseLine(lines.get(i));

            nodesMap.put(node, adjacentNodes);
        }

        return nodesMap;
    }

    /**
     * Parses a single line of the file and returns list of adjacent node
     * */
    private ArrayList<Integer> ParseLine(String line)
    {
        var adjacentNodes = new ArrayList<Integer>();

                //getting adjacent nodes
        //first split to separate the line by the first space i.e 1 2,3
        var firstSplit = line.split(" ");

        //after removing 1 now its 2,3 and these are the adjacent nodes
        var secondSplit = firstSplit[1].split(",");

        for (var adjacentNode : secondSplit)
        {
            var value = Integer.parseInt(adjacentNode);
            adjacentNodes.add(value);
        }

        return adjacentNodes;
    }

    /**
     * Gets Node from line
     * */
    private int GetNode(String line)
    {
        var firstChar = line.substring(0,1);
        return Integer.parseInt(firstChar);
    }

    /**
     * Reads from file and returns a list of every line
     * */
    private ArrayList<String> ReadFromFile()
    {
        var listOfLines = new ArrayList<String>();

        try {
            var file = new File(FILE_NAME);
            Scanner myReader = new Scanner(file);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine() ;
                listOfLines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return listOfLines;
    }
}
