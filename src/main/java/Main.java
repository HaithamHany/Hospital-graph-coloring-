import Graph.ColoredGraph;
import Graph.Graph;
import POJO.FloorMapJSONParser;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\nWelcome to our hospital planner! If you want to exit at anytime, type \"exit\" \n");
        while(true) {
            System.out.println("\nBelow is the sample of some floor map input files: ");
            List<String> inputs = listFilesInDir("./data");
            System.out.println("\nEnter the number of one of the floor map input json files from above: ");

            String fileName = sc.nextLine();
            if(fileName.equals("exit"))
                return;

            Graph graph = FloorMapJSONParser.parseJSON(inputs.get(Integer.parseInt(fileName)));

            System.out.print("Do you want to only orient the floor map? (yes/no): ");
            String option = sc.nextLine();
            switch (option.toLowerCase()) {
                case "yes":
                    System.out.println("\nOne way orienting now..");
                    graph.OneWayStreetOrientation(graph.getStart());
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("\nColoring and one way orienting now..");
                    ColoredGraph coloredGraph = new ColoredGraph(graph);
                    coloredGraph.ColorAndOrientGraph(graph.getStart());
                    break;
            }
        }
    }

    public static List<String> listFilesInDir(String dir) {
        int i = 0;
        List<String> files = Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName).collect(Collectors.toList());
        for (String file : files) {
            System.out.println(i + ": " + file);
            i++;
        }
        return files;
    }
}
