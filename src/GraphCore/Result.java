package GraphCore;

public class Result<weight, nodes> {
    public final weight w;
    public final nodes nodes;
    public Result(weight w, nodes nodes){
        this.w = w;
        this.nodes = nodes;
    }
}