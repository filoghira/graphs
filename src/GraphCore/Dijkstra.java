package GraphCore;

import java.util.HashMap;
import java.util.LinkedList;

public class Dijkstra {

    HashMap<Node, Prop> table = new HashMap<>();
    Graph graph;

    public Dijkstra(Graph g) {
        graph = g;
    }

    /**
     * Inizializza la tabella con i nodi e le prop corrispondenti (last=null, weight=Integer.MAX_VALUE)
     *
     * @param a Nodo di partenza, inizializzato: last=null, weight=0
     * @param b Nodo di arrivo, inizializzato: checked=true
     */
    void init(Node a, Node b) {
        for (Node n : graph.getNodi())
            table.put(n, new Prop(Integer.MAX_VALUE, false, null));

        // Imposto il nodo di partenza a 0 e il nodo finale come già completato
        table.get(a).setWeight(0);
        table.get(b).checked = true;
    }

    /**
     * Controlla se la tabella è stata controllata tutta
     *
     * @return true o false
     */
    boolean hasFinished() {
        for (Node key : table.keySet())
            if (!table.get(key).checked)
                return false;
        return true;
    }

    /**
     * Data una HashMap di Nodi e Prop restituisce il nodo minore comparandoli con la proprietà Weight
     *
     * @param table HashMap<Nodo, Prop>
     * @return Nodo
     */
    Node minVal(HashMap<Node, Prop> table) {
        // Nodo con la distanza minore
        Node min = null;
        // Valore di quella distanza
        int minVal = Integer.MAX_VALUE;

        // Scorro tutta la tabella alla ricerca del nodo con la distanza minore
        for (Node key : table.keySet())
            // Altrimenti confronto le distanze ed che non siano già stati controllati
            if (
                // Se è la prima chiave a essere selezionata (e non è già stata controllata)
                    (min == null && !table.get(key).checked) ||
                            // Oppure se la chiave selezionata ha un valore minore e non è già stata controllata
                            (minVal > table.get(key).getWeight() && !table.get(key).checked)
            ) {
                min = key;
                minVal = table.get(min).getWeight();
            }

        return min;
    }

    /**
     * Dato un nodo n aggiorno la tabella con le distanze dai nodi adiacenti ad n
     *
     * @param n Nodo a cui controllare gli adiacenti
     */
    void checkNear(Node n) {
        // Ottengo tutti i nodi connessi a quello selezionato
        HashMap<Node, Integer> connections = n.getConnections();

        // Scorro tutti i nodi adiacenti
        for (Node key : connections.keySet()) {
            // Ottengo la distanza di partenza, quella da aggiungere e quella del nodo precedente
            int startValue = table.get(key).getWeight();
            int addValue = connections.get(key);
            int precValue = table.get(n).getWeight();

            // Se è la prima volta che il nodo viene visitato lo modifico in ogni caso
            if (startValue == Integer.MAX_VALUE) {
                table.get(key).setWeight(precValue + addValue);
                table.get(key).last = n;
            }
            // Altrimenti lo modifico solo se ottengo una distanza minore
            else if (startValue > addValue + precValue) {
                table.get(key).setWeight(addValue + precValue);
                table.get(key).last = n;
            }
        }
        // Il nodo selezionato viene aggiornato come "controllato"
        table.get(n).checked = true;
    }

    /**
     * Cerca il percorso migliore in un grafo
     *
     * @param a Nodo di partenza
     * @param b Nodo di arrivo
     */
    public Result searchPath(Node a, Node b) {
        // Inizializzo la tabella
        init(a, b);

        // Finché non ho controllato tutti i nodi
        while (!hasFinished())
            checkNear(minVal(table));

        // Stampo il risultato
        return getPath(b);
    }

    /**
     * Data la tabella, ottengo una stringa contenente il percorso migliore e la distanza totale
     *
     * @param b Nodo di arrivo
     * @return Stringa nel formato "nodi percorso - distanza"
     */
    Result getPath(Node b) {
        LinkedList<Node> percorso = new LinkedList<>();
        // Scorro la tabella al contrario e ottengo la lista dei nodi del percorso
        Node n = b;
        while (n != null) {
            percorso.add(n);
            n = table.get(n).last;
        }
        return new Result(table.get(b).getWeight(), percorso);
    }

}
