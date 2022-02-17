package Graph;

import POJO.Node;

import java.util.concurrent.ConcurrentHashMap;

public interface DfsConsumer {
    void accept(Node n, ConcurrentHashMap<Node, Boolean> visited);
}
