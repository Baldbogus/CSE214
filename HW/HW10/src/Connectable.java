import java.util.*;

/*
 * We will use an Integer type to represent each node's ID.
 */
interface Connectable {
	public Set<Integer> nodeSet(); // return the set of nodes
	public Set<Integer> getNeighbors(int node); // return the set of neighbors connected to 'node'
	
	public Iterator<Integer> breadthFirstIterator(int src); // return the iterator on nodes in a breadth-first manner
	public Iterator<Integer> depthFirstIterator(int src); // same as above in a depth-first manner
	
	public void addNode(int node); // add a new vertex into the current graph.
	public boolean addEdge(int source, int target, double w); // add a new edge. also add non-existing nodes. return false if edge already exists. 
	public boolean removeNode(int node); // remove node. return false if node doesn't exist.
	public boolean removeEdge(int source, int target); // remove edge. return false if edge doesn't exist.
	public boolean isEdge(int source, int target); // Returns true if source-target is a valid edge. The source-target order doesn't matter in undirected graphs.
	
	public double getWeight(int source, int target); // return weight of edge (what if edge doesn't exist?).
	public boolean setWeight(int source, int target, double w); // set the weight. existing weights are overwritten. if edge doesn't exist, return false;
	public int numNodes(); // return the total number of nodes. should have O(1) time complexity.
	public int numEdges(); // return the total number of edges. should have O(1) time complexity.
	
	public Connectable getMST(); // return a minimum spanning tree using either Prim's or Kruskal's algorithm.
}

class WeightUndirectedGraph implements Connectable{
	HashMap<Integer, Node> nodes;
	int edgeCount;

	public WeightUndirectedGraph() {
		nodes = new HashMap<>();
		edgeCount = 0;
	}

	@Override
	public Set<Integer> nodeSet() {
		return nodes.keySet();
	}

	@Override
	public Set<Integer> getNeighbors(int node) {
		if (nodes.containsKey(node)){
			return nodes.get(node).neighbors.keySet();
		}
		return new HashSet<>();
	}

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

	@Override
	public void addNode(int node) {
		if (!nodes.containsKey(node)) {
			nodes.put(node, new Node(node));
		}
	}

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

	@Override
	public boolean removeNode(int node) {
		if (!nodes.containsKey(node)) {
			return false;
		}
		nodes.remove(node);
		return false;
	}

	@Override
	public boolean removeEdge(int source, int target) {
		if (nodes.get(source).neighbors.containsKey(target) && nodes.containsKey(source)){
			nodes.get(source).neighbors.remove(target);
			edgeCount--;
			return true;
		}
		return false;
	}

	@Override
	public boolean isEdge(int source, int target) {
		if (nodes.containsKey(source) && nodes.get(source).neighbors.containsKey(target)){
			return true;
		}
		return false;
	}

	@Override
	public double getWeight(int source, int target) {
		if (isEdge(source, target)){
			return nodes.get(source).neighbors.get(target);
		}
		return -1;
	}

	@Override
	public boolean setWeight(int source, int target, double w) {
		if (isEdge(source,target)){
			nodes.get(source).neighbors.put(target,w);
			nodes.get(target).neighbors.put(source, w);
			return true;
		}
		return false;
	}

	@Override
	public int numNodes() {
		return nodes.size();
	}

	@Override
	public int numEdges() {
		return edgeCount;
	}

	@Override
	public Connectable getMST() {
		WeightUndirectedGraph graph = new WeightUndirectedGraph();
		if (nodes.)
		return null;
	}

	public static void main(String[] args) {

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



