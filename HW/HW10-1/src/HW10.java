//Name: Donghun Yoo
//ID: 114000660

import java.util.*;

public class HW10 implements Connectable {
    HashMap<Integer, Node> nodes;
    int edgeCount;

    /*
     * Constructor for HW10 class.
     * Initializes the nodes map and edgeCount.
     */
    public HW10() {
        nodes = new HashMap<>();
        edgeCount = 0;
    }

    /*
     * Returns a string representation of the graph.
     * @return String representation of the graph.
     * Time complexity: O(1), because it simply returns the set of node keys.
     */
    @Override
    public Set<Integer> nodeSet() {
        return nodes.keySet();
    }

    /*
     * Returns a set of neighbors for a given node.
     * @param node The node for which to get neighbors.
     * @return Set of neighbors for the specified node.
     * Time complexity: O(1), because it simply returns the neighbors of the node if it exists.
     */
    @Override
    public Set<Integer> getNeighbors(int node) {
        if (nodes.containsKey(node)){
            return nodes.get(node).neighbors.keySet();
        }
        return new HashSet<>();
    }

    /*
     * Returns a set of edges in the graph.
     * @return Set of edges in the graph.
     * Time complexity: O(n), where n is the number of nodes, because it iterates through all nodes to collect edges.
     */
    @Override
    public Iterator<Integer> breadthFirstIterator(int src) {
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> bfs = new LinkedList<>();

        if (!nodes.containsKey(src)) {
            return visited.iterator();
        }

        bfs.add(src);

        while(!bfs.isEmpty()){
            int curr = bfs.poll();
            if (!visited.contains(curr)){
                visited.add(curr);
                bfs.addAll(nodes.get(curr).neighbors.keySet());
            }
        }

        return visited.iterator();
    }

    /*
     * Returns an iterator for depth-first traversal starting from a given source node.
     * @param src The source node to start the traversal.
     * @return An iterator for depth-first traversal.
     * Time complexity: O(n), where n is the number of nodes, because it visits each node once.
     */
    @Override
    public Iterator<Integer> depthFirstIterator(int src) {
        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> dfs = new Stack<>();

        if (!nodes.containsKey(src)){
            return visited.iterator();
        }

        dfs.add(src);

        while (!dfs.isEmpty()){
            int curr = dfs.pop();
            if (!visited.contains(curr)){
                visited.add(curr);
                dfs.addAll(nodes.get(curr).neighbors.keySet());
            }
        }
        return visited.iterator();
    }

    /*
     * Returns a string representation of the graph.
     * @return String representation of the graph.
     * Time complexity: O(1), because it simply adds the node keys to hashSet.
     */
    @Override
    public void addNode(int node) {
        if (!nodes.containsKey(node)) {
            nodes.put(node, new Node(node));
        }
    }

    /*
     * Adds an edge between two nodes with a specified weight.
     * @param source The source node.
     * @param target The target node.
     * @param w The weight of the edge.
     * @return true if the edge was added successfully, false otherwise.
     * Time complexity: O(1), because it simply adds the edge to the neighbors map if both nodes exist.
     */
    @Override
    public boolean addEdge(int source, int target, double w) {
        if (nodes.containsKey(source) && nodes.get(source).neighbors.containsKey(target)){
            nodes.get(source).neighbors.put(target,w);
            nodes.get(target).neighbors.put(source,w);
            edgeCount++;
            return true;
        }
        return false;
    }


    /*
     * Removes a node from the graph.
     * @param node The node to be removed.
     * @return true if the node was removed successfully, false otherwise.
     * Time complexity: O(1), because it simply removes node from the nodes map.
     */
    @Override
    public boolean removeNode(int node) {
        if (!nodes.containsKey(node)) {
            return false;
        }
        nodes.remove(node);
        return true;
    }
    /*
     * Removes an edge between two nodes.
     * @param source The source node.
     * @param target The target node.
     * @return true if the edge was removed successfully, false otherwise.
     * Time complexity: O(1), because it simply removes the edge from the neighbors map if it exists.
     */
    @Override
    public boolean removeEdge(int source, int target) {
        if (nodes.get(source).neighbors.containsKey(target) && nodes.containsKey(source)){
            nodes.get(source).neighbors.remove(target);
            edgeCount--;
            return true;
        }
        return false;
    }

    /*
     * Checks if there is an edge between two nodes.
     * @param source The source node.
     * @param target The target node.
     * @return true if there is an edge, false otherwise.
     * Time complexity: O(1), because it simply checks if the target node exists in the neighbors map of the source node.
     */
    @Override
    public boolean isEdge(int source, int target) {
        if (nodes.containsKey(source) && nodes.get(source).neighbors.containsKey(target)){
            return true;
        }
        return false;
    }

    /*
     * Checks if a node exists in the graph.
     * @param node The node to check.
     * @return true if the node exists, false otherwise.
     * Time complexity: O(1), because it simply returns weight of the edge if it exists.
     */
    @Override
    public double getWeight(int source, int target) {
        if (isEdge(source, target)){
            return nodes.get(source).neighbors.get(target);
        }
        return -1;
    }

    /*
     * Sets the weight of an edge between two nodes.
     * @param source The source node.
     * @param target The target node.
     * @param w The weight to set.
     * @return true if the weight was set successfully, false otherwise.
     * Time complexity: O(1), because it simply updates the weight in the neighbors map if the edge exists.
     */
    @Override
    public boolean setWeight(int source, int target, double w) {
        if (isEdge(source,target)){
            nodes.get(source).neighbors.put(target,w);
            nodes.get(target).neighbors.put(source, w);
            return true;
        }
        return false;
    }

    /*
     * Returns the number of nodes in the graph.
     * @return The number of nodes.
     * Time complexity: O(1), because it simply returns the size of the nodes map.
     */
    @Override
    public int numNodes() {
        return nodes.size();
    }

    /*
     * Returns the number of edges in the graph.
     * @return The number of edges.
     * Time complexity: O(1), because it simply returns the edgeCount variable.
     */
    @Override
    public int numEdges() {
        return edgeCount;
    }


    @Override
    public Connectable getMST() {
        HW10 mst = new HW10();
        if (nodes.isEmpty()) {
            return mst;
        }
        
        return null;
    }

}

class Node{
    int id;
    HashMap<Integer, Double> neighbors;

    public Node(int id) {
        this.id = id;
        this.neighbors = new HashMap<>();
    }
}
