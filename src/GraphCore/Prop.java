package GraphCore;

public class Prop {
    private int weight;
    boolean checked;
    Node last;

    public Prop(int weight, boolean checked, Node last){
        this.weight = weight;
        this.checked = checked;
        this.last = last;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
