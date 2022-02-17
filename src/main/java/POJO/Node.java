package POJO;

import java.util.Arrays;
import java.util.Objects;

public class Node {
    public String name;
    public String[] neighbors;
    public boolean isEntrance;
    public boolean isExit;
    public boolean hasDoubleEdge;


    public Node() {
    }


    public Node(String name, String[] neighbors, boolean isEntrance, boolean isExit, boolean hasDoubleEdge) {
        this.name = name;
        this.neighbors = neighbors;
        this.isEntrance = isEntrance;
        this.isExit = isExit;
        this.hasDoubleEdge = hasDoubleEdge;
    }

    public Node(String name, boolean isEntrance, boolean isExit, boolean hasDoubleEdge) {
        this.name = name;
        this.neighbors = neighbors;
        this.isEntrance = isEntrance;
        this.isExit = isExit;
        this.hasDoubleEdge = hasDoubleEdge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return isEntrance == node.isEntrance &&
                hasDoubleEdge == node.hasDoubleEdge &&
                isExit == node.isExit &&
                name.equals(node.name) &&
                Arrays.equals(neighbors, node.neighbors);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, isEntrance, isExit, hasDoubleEdge);
        result = 31 * result + Arrays.hashCode(neighbors);
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", isEntrance=" + isEntrance +
                ", isExit=" + isExit +
                ", hasDoubleEdge=" + hasDoubleEdge +
                '}';
    }
}


