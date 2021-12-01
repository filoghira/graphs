package GraphCore;

import java.util.HashSet;
import java.util.Map;

public class Graph {

    private HashSet<Node> nodi = new HashSet<>();

    public boolean addNodo(Node n) {
        if (nodi.contains(n))
            return false;
        return nodi.add(n);
    }

    void addArco(Node a, Node b, int w) {
        a.addConnection(b, w);
        b.addConnection(a, w);
    }

    public String toString() {
        String out = "";
        for (Node n : nodi)
            for (Map.Entry<Node, Integer> entry : n.getConnections().entrySet())
                out += n + " | " + entry.getKey() + " | " + entry.getValue() + "\n";
        return out;
    }

    public HashSet<Node> getNodi() {
        return nodi;
    }

    public Node getNodo(String name){
        for (Node n : nodi)
            if(n.toString().equals(name))
                return n;
        return null;
    }
}
