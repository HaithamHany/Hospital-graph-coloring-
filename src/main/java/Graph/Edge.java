package Graph;

import POJO.Node;

import java.util.Objects;

public class Edge {
    public final Node nodeA;
    public final Node nodeB;
    public Color color;
    public Lane lane;


    public Edge(Node nodeA, Node nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.color = Color.NONE;
        this.lane = Lane.LANE01;
    }

    public Edge(Node nodeA, Node nodeB, Color color) {
        this(nodeA, nodeB);
        this.color = color;
        this.lane = Lane.LANE01;
    }

    public Edge(Node nodeA, Node nodeB, Lane lane) {
        this(nodeA, nodeB);
        this.lane = lane;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        Edge p = (Edge)obj;
        return this.nodeA.equals(p.nodeA) && this.nodeB.equals(nodeB);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nodeA, nodeB, color, lane);
        return result;
    }

    @Override
    public String toString() {
        return nodeA.name + " - " + nodeB.name + " : " + (color.equals(Color.NONE)? "" : color);
    }

    public boolean isColored() {
        return !color.equals(Color.NONE);
    }
}
