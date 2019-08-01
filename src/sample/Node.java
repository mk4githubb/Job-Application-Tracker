package sample;

/*
    Class representing a Node in the Tree.
    Contains Job Application Object data and Various methods.
 */
public class Node {
    private Job job;
    private Node leftChild;
    private Node rightChild;

    public Node(Job job){
        this.job = job;
    }

    public Node getLeftChild(){
        return leftChild;
    }

    public Job getJob() {
        return job;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }


    /**
     * Checks if a Node is leaf
     *
     * @return - boolean
     */
    public boolean isLeaf() {
        if (rightChild == null && leftChild == null) {
            return true;
        }
        return false;
    }


    /**
     * Checks if a node is outer node
     *
     * @return boolean
     */
    public boolean isOuter(){
        if(rightChild == null && leftChild != null || leftChild == null && rightChild!= null){
            return true;
        }
        return false;
    }

    /**
     * Checks if a node is inner node
     *
     * @return boolean
     */
    public boolean isInner(){
        if(rightChild != null && leftChild != null){
            return true;
        }
        return false;
    }

}
