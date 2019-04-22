import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A class for the Graph Node object using the given interface, I've added setter methods in addition to
 * the getter methods described in the interface
 */
public class Node implements GNode{
    private String name;
    private Node[] children;

    /**
     * A constructor for a Node
     * @param name the Node's name
     * @param children an Array of nodes that are it's children
     */
    public Node(String name, Node[] children) {
        this.name = name;
        this.children = children;
    }

    /**
     * A name-only constructor for the class
     */
    public Node(String name) {
        this.name = name;
        this.children = new Node[0];
    }

    /**
     * A getter for the Node's name
     * @return the Node's name
     */
    public String getName() {
        return this.name;
    }
    /**
     * A getter for the Node's children
     * @return an Array of nodes that are it's children
     */
    public Node[] getChildren() {
        return this.children;
    }

    /**
     * A setter for the Node's name
     * @param name the new name of the Node
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A setter for the Node's Children
     * @param children an array of children
     */
    public void setChildren(Node[] children) {
        this.children = children;
    }

    /**
     * A method to find all the node in a graph from a start node, using
     * Breath First Search implemented with a queue.
     * @param start the starting node
     * @return an Arraylist of all the nodes in the graph
     */
    public static ArrayList<Node> walkGraph(Node start) {
        ArrayList<Node> walked = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        if (start == null) {
            return walked;
        }
        walked.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            Node popped = queue.remove();
            Node[] children = popped.getChildren();
            for (Node child : children) {
                if (!walked.contains(child)) {
                    walked.add(child);
                    queue.add(child);
                }
            }
        }
        return walked;
    }

    /**
     * A method to find all the paths in a graph from a start node, using
     * Depth First Search implemented with a stack.
     * @param node the start node
     * @return an ArrayList of ArrayList<Nodes> containing all possible paths
     */
    public static ArrayList<ArrayList<Node>> paths(Node node) {
        ArrayList<ArrayList<Node>> pathList = new ArrayList<>();
        Stack<ArrayList<Node>> stack = new Stack<>();
        ArrayList<Node> startList = new ArrayList<>();
        if (node == null) {
            pathList.add(startList);
            return pathList;
        }
        startList.add(node);
        stack.push(startList);
        while (!stack.isEmpty()) {
            ArrayList<Node> popped = stack.pop();
            Node curr = popped.get(popped.size()-1);
            Node[] children = curr.getChildren();
            for (Node child : children) {
                if (child.getChildren().length == 0) {
                    ArrayList<Node> newPath = (ArrayList<Node>) popped.clone();
                    newPath.add(child);
                    pathList.add(newPath);
                } else {
                    ArrayList<Node> newPath = (ArrayList<Node>) popped.clone();
                    newPath.add(child);
                    stack.push(newPath);
                }
            }
        }
        return pathList;
    }

}