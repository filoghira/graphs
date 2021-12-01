package GraphicGraph;

import GraphCore.Node;

class GraphicNode extends Node {

    // Raggio del cerchio del nodo
    static final int ray = 40;
    // Coordinate
    private int x, y;
    // Indica se è stato selezionato o meno
    private boolean selected;

    public GraphicNode(String value, int x, int y) {
        super(value);
        this.x = x;
        this.y = y;
        selected = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        selected = true;
    }

    public void unselect() {
        selected = false;
    }
}
