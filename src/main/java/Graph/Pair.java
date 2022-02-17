package Graph;

import POJO.Node;

import java.util.Arrays;
import java.util.Objects;

public class Pair {
    public final Node nodeA;
    public final Node nodeB;

    public Pair(Node nodeA, Node nodeB) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    @Override
    public boolean equals(Object obj) {

        Pair p = (Pair)obj;
        return this.nodeA.equals(p.nodeA) && this.nodeB.equals(nodeB);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nodeA, nodeB);
        return result;
    }
}
