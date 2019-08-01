package sample;

import java.util.ArrayList;
import java.util.List;

/*
    Tree Structure holding the Nodes.
    Contains several relevant methods.
 */

public class Tree{
    private Node root;

    /**
     * Adds job to the tree.
     *
     * @return - void
     */
    public void add(Job job){
        if(root==null){
            root = new Node(job);
        }
        else{
            Node current = root;
            Node parent = root;

            while (current!=null){
                if(job.getLastDate().isAfter(current.getJob().getLastDate())){
                    parent = current;
                    current = current.getRightChild();
                }
                else{
                    parent = current;
                    current = current.getLeftChild();
                }
            }
            if(job.getLastDate().isAfter(parent.getJob().getLastDate())){
                parent.setRightChild(new Node(job));
            }
            else {
                parent.setLeftChild(new Node(job));
            }
        }
    }


    /**
     * Returns In Order Transversal of the Tree.
     *
     * @return - List of Jobs
     */
    public List<Job> inOrderTransverse(){
        if(root==null){
            return new ArrayList<Job>();
        }
        return inOrderTransverse(root);
    }

    /**
     * Returns In Order Transversal of the Tree.
     *
     * @param - root Node
     * @return - List of Jobs
     */
    public List<Job> inOrderTransverse(Node current){
        List<Job> toReturn = new ArrayList<>();

        if(current.getLeftChild() != null){
            toReturn.addAll(inOrderTransverse(current.getLeftChild())) ;
        }
        toReturn.add(current.getJob());

        if(current.getRightChild() != null){
            toReturn.addAll(inOrderTransverse(current.getRightChild())) ;
        }
        return toReturn;
   }

    /**
     * Removes a Job from the tree.
     *
     * @return - void
     */
   public void remove(Job job){

        Pair childParentPair = find(job);
        Node parentNode = childParentPair.getParentNode();
        Node toDeleteNode = childParentPair.getChildNode();

        Boolean isLeft = nodeDirectionFinder(parentNode, toDeleteNode);

        if(toDeleteNode.isLeaf()){

            if(isLeft==null) {
                root = null;
            }
            else if(isLeft){
                parentNode.setLeftChild(null);
            }
            else{
                parentNode.setRightChild(null);
            }
        }
        else if(toDeleteNode.isOuter()){

            if(isLeft != null && isLeft){
                if(toDeleteNode.getLeftChild()!=null){
                    parentNode.setLeftChild(toDeleteNode.getLeftChild());
                }
                else{
                    parentNode.setLeftChild(toDeleteNode.getRightChild());
                }
            }
            else{
                if(toDeleteNode.getLeftChild()!=null){
                    parentNode.setRightChild(toDeleteNode.getLeftChild());
                }
                else{
                    parentNode.setRightChild(toDeleteNode.getRightChild());
                }
            }
        }
        else{

            Pair successorPair = findSuccessor(toDeleteNode);
            Node successorParent = successorPair.getParentNode();
            Node successorNode = successorPair.getChildNode();

            if(isLeft != null && isLeft){
                parentNode.setLeftChild(successorNode);
            }
            else{
                parentNode.setRightChild(successorNode);
            }

            successorNode.setLeftChild(toDeleteNode.getLeftChild());
            successorNode.setRightChild(toDeleteNode.getRightChild());
            Boolean isSucessorNodeLeft = nodeDirectionFinder(successorParent, successorNode);
            if(isSucessorNodeLeft != null && isLeft){
                successorParent.setLeftChild(null);
            }
            else{
                successorParent.setRightChild(null);
            }
        }
   }

    /**
     * Find and returns successor node and it's parent Pair
     *
     * @return - Pair
     */
   private Pair findSuccessor(Node node){
        Node current = node.getRightChild();
        Node parent = node;
        while(current != null){
            parent = current;
            current= current.getLeftChild();
        }
        Pair successorPair = Pair.getInstance();
        successorPair.setChildNode(current);
        successorPair.setParentNode(parent);
        return successorPair;
   }

    /**
     * Finds job
     *
     * @return - Pair of the found Job and it's Parent Job (Tree)
     */
   public Pair find(Job job){

        Node current = root;
        Node parent = root;

       while (current!=null){
           if (job.equals(current.getJob())){

               Pair childParentPair = Pair.getInstance();
               childParentPair.setChildNode(current);
               childParentPair.setParentNode(parent);
               return childParentPair;
           }
           if(job.getLastDate().isAfter(current.getJob().getLastDate())){
               parent = current;
               current = current.getRightChild();
           }
           else{
               parent = current;
               current = current.getLeftChild();
           }
       }
       return null;
   }


    /**
     * Checks if a given node is to the left of a another node or not
     *
     * @return boolean
     */
   public  Boolean nodeDirectionFinder(Node parentNode, Node childNode){
       if(parentNode.getLeftChild()==childNode){
           return true;
       }
       else if(parentNode.getRightChild() == childNode){
           return false;
       }
       else{
           return null;
       }
   }
}
