package sample;

/*
    Singleton class for hold Parent-Child Node reference.
    Coating getters and setters to get and set objects.
 */

public class Pair {
    private Node childNode;
    private Node parentNode;

    private static Pair ourInstance = new Pair();

    public static Pair getInstance() {
        return ourInstance;
    }

    private Pair() {
    }

    public void setChildNode(Node node){
        childNode = node;
    }

    public void setParentNode(Node node){
        parentNode = node;
    }

    public Node getChildNode() {
        return childNode;
    }

    public Node getParentNode() {
        return parentNode;
    }
}
