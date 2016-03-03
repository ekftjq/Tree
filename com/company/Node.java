package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moons on 2/28/2016.
 */
public class Node<T> {

    //a node has string data
    //a node has a parent node
    //a node has children nodes
    //I can instantiate the object like Node<String> newnode = new Node<String>() to show that T is type string

    private T data;
    private Node<T> parent;
    private List<Node<T>> children = new ArrayList<>(); //List of Nodes

    public Node(T data) { //constructor
        this.data = data;
    }

    public Node(T data, Node<T> parent) { //constructor with two arguments
        this.data = data;
        this.parent = parent;
    }

    public List<Node<T>> getChildren() { //get children function, can only access children this way because children is private
        return children;
    }

    public void setParent(Node<T> parent) { //sets the current node as the child of the given node
        this.parent = parent;
    }

    /*a
    b  c
     */

    public void addChild(Node<T> child) { //adds the given child to the current node
        child.setParent(this);
        this.children.add(child);

    }

    public void addChild(T data) { //adds a new child to the current node with the given data
        Node<T> child = new Node(data);
        child.setParent(this);
        this.children.add(child);
    }

    public boolean isRoot() {
        return this.parent == null;
    }

    public boolean isLeaf() {
        if(this.children.size() == 0) {
            return true;
        }
        return false;
    }

    public T getData() {
        return data;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setData(T data) {
        this.data = data;
    }

}
