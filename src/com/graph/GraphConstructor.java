package com.graph;
import java.io.*;
import java.util.*;

public class GraphConstructor {

    private final String FILE_NAME ="graph.txt";
    private ArrayList<String> fileLines;

    public void ConstructGraph()
    {
        fileLines = ReadFromFile();

        //first line always is special since it holds the number of vertices
        var firstLine = fileLines.get(0);
        var numberOfVertices = Integer.parseInt(firstLine);


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
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return listOfLines;
    }
}
