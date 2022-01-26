package JGame.Objects.Components;

import JGame.Objects.Components.Collision.Collider;

import java.util.LinkedList;

public class Node {

    private LinkedList<Node> nodes = new LinkedList<>();
    private Node parent;

    /**
     * @param node is for the type to be return new Squarecollider will return the first Sqaurecollider in the list
     * @return first object with the same type as the node inserted
     * */
    public <T extends Node> Component getChild(T node)
    {
        for(Node n : nodes){
            if(n.getClass().equals(node.getClass())){
                return (Component) n;
            }
        }
        return null;
    }
    public Component getChild(Collider node)
    {
        for(Node n : nodes){
            if(n.getClass().equals(node.getClass())){
                return (Component) n;
            }
        }
        return null;
    }
    /**
     * return a linkedlist with all children
     *
     * @return*/
    public <T extends Component> LinkedList<Component> getChildren(T node){
         LinkedList<Component> components = new LinkedList<>();
         if(node.getClass().equals(Component.class)){
             for(Node n : nodes){
                     components.add((Component) n);
             }
             return components;
         }
         for(Node n : nodes){
            if(n.getClass().equals(node.getClass())){

                components.add((Component) n);
            }
        }
        return components;
    }
    public LinkedList<Collider> getChildren(Collider node){
        LinkedList<Collider> components = new LinkedList<>();
        for(Node n : nodes){
            if(n.getClass().equals(node.getClass())){
                components.add((Collider) n);
            }
        }
        return components;
    }
    public void addChild(Node newNode) {

         nodes.add(newNode);
    }

    public Component getParent() {
        return (Component) parent;
    }
    public void setParent(Component parent){
        this.parent = parent;
    }
}
