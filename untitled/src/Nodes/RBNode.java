package Nodes;

public class RBNode extends Node {
    public enum Color  {RED, BLACK}
    public Color color;
    public RBNode(int value, Color color) {
        super(value);
        this.color = color;
    }
}
