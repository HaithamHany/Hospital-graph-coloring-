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
        ParseGraphFileLines(fileLines, graph);
    }

    /**
     * Converts lines to node and it's adjacent nodes
     * */
    private void  ParseGraphFileLines(ArrayList<String> lines, Graph graph)
    {
        var edgesMap = new HashMap<Integer,Integer>();

        //starting from 1 because index 0 is always reserved for number of vertices
        for (int i = 1; i < lines.size(); i++) {

            var src = GetNode(lines.get(i)); //getting the first node
            var destination = ParseLine(lines.get(i));

            graph.AddEdge(src, destination);
            graph.UpdateEdges(src, destination);
        }
    }

    /**
     * Parses a single line of the file and returns list of adjacent node
     * */
    private Integer ParseLine(String line)
    {
        //first split to separate the line by the first space and adding after space as a node
        var adjacentNode = line.split(" ");
        var numbers = "";

        for (int i = 1; i < adjacentNode.length; i++) {

            numbers+=adjacentNode[i];
        }
        var value = Integer.parseInt(numbers);

        return value;
    }

    /**
     * Gets Node from line
     * */
    private int GetNode(String line)
    {
        var firstChar = line.split(" ");
        return Integer.parseInt(firstChar[0]);
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
