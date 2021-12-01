package GraphCore;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private String value;
    private HashMap<Node, Integer> connections = new HashMap<>();

    public Node(String value) {
        this.value = value;
    }

    public void addConnection(Node n, Integer w) {
        connections.put(n, w);
    }

    public String toString() {
        return value;
    }

    void setValue(String value) {
        this.value = value;
    }

    HashMap<Node, Integer> getConnections() {
        return connections;
    }

    public boolean hasConnection(Node b){
        for (Map.Entry<Node, Integer> connection:connections.entrySet()) {
            if(connection.getKey().equals(b))
                return true;

        }
        return false;
    }

}
