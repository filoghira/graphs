package GraphicGraph;

class Arch {
    // I due nodi dell'arco
    GraphicNode start, end;
    // Il peso dell'arco
    int w;

    public Arch(GraphicNode start, GraphicNode end, int w) {
        this.start = start;
        this.end = end;
        this.w = w;
    }
}
