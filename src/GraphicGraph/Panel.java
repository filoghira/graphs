package GraphicGraph;

import GraphCore.Dijkstra;
import GraphCore.Graph;
import GraphCore.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class Panel extends JPanel implements ActionListener, MouseListener {

    private JLabel option, dim;
    private String operation;
    private LinkedList<GraphicNode> nodes;
    private LinkedList<Arch> arches;
    private GraphicNode start = null, end = null, selected = null;
    private Graph graph = new Graph();

    public Panel(JLabel option, JLabel dim){
        this.option = option;
        nodes = new LinkedList<>();
        arches = new LinkedList<>();
        this.dim = dim;
    }

    /**
     * Al click del mouse su un bottone imposto la opzione selezionata
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        operation = e.getActionCommand();
        if(operation.equals("calc")){
            // Se ho selezionato un inizio e una fine del percorso
            if(start != null && end != null){
                Dijkstra dk = new Dijkstra(graph);
                Result percorso = dk.searchPath(start, end);
                dim.setText("Dimensione: "+percorso.w);

                // Manca da colorare il percorso

                start = null;
                end = null;
            }
        }else
            option.setText(operation);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Scorre tutti i nodi e li disegna
        for (GraphicNode n : nodes) {
            // Se lo sto selezionando è verde
            if(n.isSelected())
                g.setColor(Color.GREEN);
            // Se è il primo è rosso
            else if(start != null && n.equals(start))
                g.setColor(Color.RED);
            // Se è l'ultimo è blu
            else if(end != null && n.equals(end))
                g.setColor(Color.BLUE);
            else
                g.setColor(Color.BLACK);
            g.fillOval(n.getX(), n.getY(), GraphicNode.ray, GraphicNode.ray);
            g.drawString(n+"", n.getX(), n.getY());
        }

        g.setColor(Color.BLACK);
        // Scorre tutti gli archi e li disegna
        for (Arch a:arches) {
            // Ottengo le coordinate dei due nodi
            int x1 = a.start.getX(),
                    x2 = a.end.getX(),
                    y1 = a.start.getY(),
                    y2 = a.end.getY();

            // Disegno l'arco
            g.drawLine(x1+GraphicNode.ray/2,
                    y1+GraphicNode.ray/2,
                    x2+GraphicNode.ray/2,
                    y2+GraphicNode.ray/2);

            // Calcolo le coordinate del peso
            int x = (x1-x2)/2 + x2, y = (y1-y2)/2 + y2;
            if(x2>x1)
                x = (x2-x1)/2 + x1;
            if(y2>y1)
                y = (y2-y1)/2 + y1;

            // Lo disegno
            g.drawString(String.valueOf(a.w), x, y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Se non ho selezionato la operazione o uso il tasto destro
        if(operation==null || e.getButton() == MouseEvent.BUTTON2)
            return;

        // Ottengo le coordinate
        int x = e.getX(), y = e.getY();

        // Controllo che operazione sto eseguendo
        switch (operation){
            // Aggiungere un nodo
            case "add" : {
                // Creo un nuovo nodo
                GraphicNode n = new GraphicNode(""+(char)(nodes.size()+65), x, y);
                // Lo aggiungo alla lista
                nodes.add(n);
                // Lo aggiungo al grafo
                graph.addNodo(n);
                break;
            }
            // Aggiungi un arco
            case "addArch" : {
                // Se non ho selezionato il primo
                if(selected==null) {
                    // Lo seleziono
                    selected = findNodo(e.getX(), e.getY());
                    selected.select();
                } else {
                    // Altrimenti ottengo il secondo nodo e creo un arco
                    GraphicNode n = findNodo(e.getX(), e.getY());
                    int w = Integer.parseInt(JOptionPane.showInputDialog("Inserisci il peso dell'arco"));
                    selected.addConnection(n, w);
                    arches.add(new Arch(selected, n, w));
                    selected.unselect();
                    selected = null;
                }
                break;
            }
            // Imposto l'inizio del percorso
            case "start" : {
                // Ottengo il nodo e se esiste e non è la fine del percorso diventa l'inizio
                GraphicNode nodo = findNodo(x, y);
                if(nodo!=null && nodo != end)
                    start = nodo;
                break;
            }
            // Imposto la fine del percorso
            case "end" : {
                // Ottengo il nodo e se esiste e non è l'inizio del percorso diventa la fine
                GraphicNode nodo = findNodo(x, y);
                if(nodo!=null && nodo != start)
                    end = nodo;
            }
        }
        this.repaint();
    }

    /**
     * Trova un nodo in base a delle coordinate sullo schermo
     * @param x
     * @param y
     * @return il nodo, null se non lo trova
     */
    GraphicNode findNodo(int x, int y){
        // Scorre tutti i nodi e confronta le coordinate
        for (GraphicNode n : nodes) {
            if(
                    x >= n.getX() &&
                    x <= n.getX()+GraphicNode.ray &&
                    y >= n.getY() &&
                    y <= n.getY()+ GraphicNode.ray
            )
                return n;
        }

        return null;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

