import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

/**
 * Name: Donghun Yoo
 * Do not use techniques untaught in class. You may import and use data structures taught in this class so far, however.
 * For each recursive method, clearly describe the base and recursive cases.
 * For each non-recursive method, provide a time complexity analysis.
 */
public class BinaryTree<T extends Comparable<T>> {
	
	/*
	 * You MUST use the following 'root' to represent the binary tree.
	 */
	BTNode<T> root;
	
	/*
	 * This method switches the left and right subtree.
	 *   4                     4
	 *  / \                   / \
	 * 2   1       --->      1   2
	 *    / \               / \ 
	 *   5   6             6   5
	 * 
	 * Implement recursively.
	 */

	public void switchLR() {
		if (root == null) {
			return; //Base case: if the tree is empty, do nothing.
		}

		//recursive case: swap the left and right subtrees of the current node.
		BinaryTree<T> leftTree = new BinaryTree<>(); // make a new tree for the left subtree
		leftTree.root = root.left;
		leftTree.switchLR();

		BinaryTree<T> rightTree = new BinaryTree<>();// make a new tree for the right subtree
		rightTree.root = root.right;
		rightTree.switchLR();

		BTNode<T> temp = root.left;// swap the left and right subtrees using a temporary variable.
		root.left = root.right;
		root.right = temp;
	}
			
	
	/*
	 * Return a binary search tree that contains the same elements as the current tree.
	 * Can be non-recursive.
	 * Time complexity: inOrderTraversal() is O(n) where n is the number of nodes in the tree.
	 * 					Then, sort() is O(n^2) where n is the size of the list.
	 * 					Then, buildTree() is recursive and O(n) where n is the size of the list.
	 * 					Total time complexity is O(n) + O(n^2) + O(n) = O(n^2).
	 */
	public BinaryTree<T> makeBST() {
		if (this.root == null){ // If the tree is empty, return null.
			return null;
		}
		LinkedList<T> nodeList = new LinkedList<>(); // Create a new linked list to store the elements of the tree.
		inOrderTraversal(nodeList, this.root); // Call the inOrderTraversal method to gather the elements.
		sort(nodeList);	// Sort the elements in ascending order.
		BTNode<T> newRoot = buildTree(nodeList, 0, nodeList.size() - 1); // Build a new tree using the sorted elements.
		BinaryTree<T> BST = new BinaryTree<>();
		BST.root = newRoot;

		return BST; // Return the new binary search tree.
	}

	/*
	 * Sort the given linked list in ascending order using bubble sort.
	 * @param list The linked list to be sorted.
	 * Time complexity: O(n^2) where n is the size of the list.
	 */

	public void sort(LinkedList<T> list){
		for (int i = 0; i < list.size(); i++){ // Loop through the list n times.
			for (int j = 0; j < list.size() - (i + 1); j++){ // Loop through the list n-i-1 times.
				if (list.get(j).compareTo(list.get(j + 1)) > 0){
					T temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
				}
			}
		}
	}

	/*
	 * Recursively build a balanced binary search tree from the sorted linked list.
	 * @param dataList The sorted linked list containing the elements to be inserted into the tree.
	 * @param leftEndIndex The starting index of the sublist to be used for building the tree.
	 * @param rightEndIndex The ending index of the sublist to be used for building the tree.
	 * @return The root node of the newly created binary search tree.
	 */

	public BTNode<T> buildTree(LinkedList<T> dataList, int leftEndIndex, int rightEndIndex){
		if (leftEndIndex > rightEndIndex){
			return null; //Base case: if the left index is greater than the right index, return null.
		}
		//Recursive case: find the middle index of the sublist and create a new node with that value.
		int mid = (leftEndIndex + rightEndIndex) / 2; // Find the middle index(the Root index) of the sublist.

		BTNode<T> node = new BTNode<>(dataList.get(mid), null, null);

		node.left = buildTree(dataList, leftEndIndex, mid - 1); 
		node.right = buildTree(dataList, mid + 1, rightEndIndex);

		return node; //Return the newly created root node.
	}
	/*
	* Recursively gather the tree's elements in in-order (left->root->right) and store them in the given list.
 	* @param nodeList The list to store the elements of the tree.
 	* @param currentNode The current node being visited.
 	*/
	public void inOrderTraversal(LinkedList<T> nodeList, BTNode<T> currentNode){
		if (currentNode == null) { //Base case: if the current node is null, return.
			return; 
		}
		//Recursive case: visit the left subtree, add the current node's data to the list, and then visit the right subtree.
		inOrderTraversal(nodeList, currentNode.left);
		nodeList.add(currentNode.data);
		inOrderTraversal(nodeList, currentNode.right);

	}

	
	/*
	 * Recursively decide if the current tree is a BST or not.
	 */
	public boolean isBST() {
		if (root == null){ // If the tree is empty, it is considered a BST.
			return true;
		}
		// Base case: if the root is a leaf node, it is a BST.
		if (root.left == null && root.right == null){
			return true;
		} else if (root.left != null && root.left.data.compareTo(root.data) > 0){ // If the left child is greater than the root, it is not a BST.
			return false;
		} else if (root.right != null && root.right.data.compareTo(root.data) < 0){ // If the right child is less than the root, it is not a BST.
			return false;
		}
		BinaryTree<T> leftTree = new BinaryTree<>(); // Create a new tree for the left subtree.
		leftTree.root = root.left;
		BinaryTree<T> rightTree = new BinaryTree<>(); // Create a new tree for the right subtree.
		rightTree.root = root.right;

		if (leftTree.isBST() && rightTree.isBST()){ // Check if both left and right subtrees are BSTs.
			return true;
		}	
		return false;
	}
	
	/*
	 * Non-recursively gather the tree's elements in level order (left->right), and convert to a String.
	 * Insert a hyphen "-" between each element in the String.
	 * For the left tree drawn above, it should be "4-2-1-5-6".
	 * Time complexity: O(n) because it uses levelOrderTraversal() which is O(n) and then there is a for loop which is O(n)(n is the size of level ordered list).
	 * 					Total time complexity is O(n) + O(n) = O(n).
	 * @return A String containing the elements of the tree in level order, separated by hyphens.
	 */
	public String levelOrderString() {
		LinkedList<BTNode<T>> levelOrder = levelOrderTraversal(); // Call the levelOrderTraversal method to get the elements in level order.
		String string = "";

		for (int i = 0; i < levelOrder.size(); i++){
			string += "-" + levelOrder.get(i).data;
		}

		return string.substring(1);
	}

	/*
	 * Gather the tree's elements in level order (left->right) and store them in a list.
	 * Use a queue to keep track of the nodes at each level.
	 * Return the list.
	 * Time complexity: O(n) where n is the number of nodes in the tree.
	 */
	public LinkedList<BTNode<T>> levelOrderTraversal() {
		if (this.root == null) {
			return null;
		}

		Queue<BTNode<T>> queue = new LinkedList<>();
		LinkedList<BTNode<T>> list = new LinkedList<>();

		queue.add(this.root); // Start with the root node in the queue.

		while (!queue.isEmpty()) { 
			BTNode<T> node = queue.poll(); // Remove the front node from the queue and add it to the list.
			list.add(node);

			if (node.left != null) {
				queue.add(node.left); // Add the left child to the queue.
			}

			if (node.right != null) {
				queue.add(node.right); // Add the right child to the queue.
			}
		}

		return list;
	}
	
	/*
	 * Non-recursively insert the item 'elem' under the given parent index. 
	 * The left/right child is determined by the 'left' argument, where true means you should insert 'elem' as the left child.
	 * Indexing is done in a level-order fashion. For example, in the left tree above, indexes 0 through 4 correspond to 4, 2, 1, 5, 6 in that order.
	 * The original child node (if exists) at the insertion position will be 'shifted down' while maintaining the left/right child relationship.
	 * e.g., calling insertAt(3, 2, true) on the left tree below would result in the right tree. 
	 *   4                     4
	 *  / \                   / \
	 * 2   1       --->      2   1
	 *    / \                   / \ 
	 *   5   6                 3   6
	 *                        /
	 *                       5
	 * Notice how '5', which was a left child of '1', remains a left child to the newly inserted '3'.
	 *                       
	 * An index of -1 means 'insertion at the root position', in which case the 'left' argument takes a different meaning:
	 * It indicates whether the original tree should become the left child of the new root or not.  
	 * e.g., calling insertAt(0, -1, true) on the left tree below would result in the right tree.
	 *   4                     0
	 *  / \                   / 
	 * 2   1       --->      4   
	 *    / \               / \     
	 *   5   6             2   1 
	 *                        / \
	 *                       5   6
	 */
	/*
	 * @param elem The element to be inserted.
	 * @param parentIndex The index of the parent node where the new element will be inserted.
	 * @param left If true, the new element will be inserted as the left child of the parent node; otherwise, it will be inserted as the right child.
	 * @return true if the insertion is successful, false otherwise.
	 * Time complexity: There is a while loop which is O(n) where n is the number of nodes in the tree. Other statements are simple statements which are O(1).
	 * 					Total time complexity is O(n).
	 */
	public boolean insertAt(T elem, int parentIndex, boolean left) {
		if (root == null){ // If the tree is empty, create a new root node with the given element.
			if (parentIndex == -1){
				root = new BTNode<>(elem, null, null);
				return true;
			} else {
				return false;
			}
		}
		if (duplicate(root, elem)) {
			return false; // Duplicate element, insertion fails.
		}
		if (parentIndex < -1){ // Invalid parent index, insertion fails.
			return false;
		} else if (parentIndex == -1) { // Insertion at the root position.
			if (left){
				BTNode<T> temp = new BTNode<>(elem, root, null);
				root = temp;
				return true;
			} else {
				BTNode<T> temp = new BTNode<>(elem, null, root);
				root = temp;
				return true;
			}
		} else { // Insertion at a specific index.
			ArrayList<BTNode<T>> arrayList = new ArrayList<>();
			arrayList.add(root);
			int index = 0;

			while (index < arrayList.size()){ // Traverse the tree in level order using an ArrayList.
				BTNode<T> temp = arrayList.get(index);

				if (index == parentIndex) { // If the current index matches the parent index, insert the new element.
					if (left){
						BTNode<T> newNode = new BTNode<>(elem, temp.left, null);
						temp.left = newNode;
					} else {
						BTNode<T> newNode = new BTNode<>(elem, null, temp.right);
						temp.right = newNode;
					}
					return true;
				}

				if (temp.left != null){ // Add the left child to the ArrayList if it exists.
					arrayList.add(temp.left);
				}
				if (temp.right != null){ // Add the right child to the ArrayList if it exists.
					arrayList.add(temp.right);
				}
				index += 1; 
			}
		}
		return false; // Insertion failed.
	}

	/*
	 * Recursively check if the element 'elem' is duplicated in the subtree of root.
	 * @param root The root of the subtree to search in.
	 * @param elem The element to search for.
	 * @return true if elem is duplicated in the subtree of root, false otherwise.
	 */
	public boolean duplicate(BTNode<T> root, T elem){
		if(root == null){ // if the root is null, return false.
			return false;
		}
		if(root.data.compareTo(elem) == 0){ // Base case: if the root's data is equal to elem, return true.
			return true;
		}
		return duplicate(root.left, elem) || duplicate(root.right, elem); // Recursive case: check the left and right subtrees.
	}
	
	/*
	 * Recursively replace 'org' in the tree with 'replacement'.
	 * The tree structure shouldn't change.
	 */
	public boolean replace(T org, T replacement) {
		if (this.root == null) { // If the tree is empty, return false.
			return false;
		}
		if (duplicate(root, replacement)){ // If the replacement value already exists in the tree, return false.
			return false;
		}
		if (root.data.compareTo(org) == 0){ // Base case: if the root's data is equal to org, replace it with replacement.
			root.data = replacement;
			return true;
		}
		BinaryTree<T> leftTree = new BinaryTree<>();
		leftTree.root = root.left;
		BinaryTree<T> rightTree = new BinaryTree<>();
		rightTree.root = root.right;

		// Recursive case: check the left and right subtrees for the org value.
		if (leftTree.replace(org, replacement) || rightTree.replace(org, replacement)){
			return true;
		}
		return false;
	}

	/* 
	 * Number of elements in the tree.
	 * Time complexity: inOrderTraversal() is O(n) where n is the number of nodes in the tree. Other statements are simple statements which are O(1).
	 * 					Total time complexity is O(n).
	 */
	public int size() {
		LinkedList<T> nodeList = new LinkedList<>();
		inOrderTraversal(nodeList, this.root);

		return nodeList.size();
	}
	
	/*
	 * Do not change the following methods. We will use them for grading.
	 */
	public BTNode<T> getRoot() { return root; }
	
	public static void main(String[] args) {
		BinaryTree<Integer> t = new BinaryTree<Integer>();
		System.out.println(t.insertAt(0, 0, false)); // Should print out false
		t.insertAt(0, -1, false);
		t.insertAt(1, 0, false);
		t.insertAt(2, 0, true);
		t.insertAt(1, 1, false); // fails (duplicate)
		System.out.println(t.size()); // 3
		System.out.println(t.levelOrderString()); // 0-2-1
		t.replace(2, 3);
		System.out.println(t.levelOrderString()); // 0-3-1
		t.replace(0, 2); // 2-3-1
		t.replace(3, 1); // fail (duplicate)
		BinaryTree<Integer> bst = t.makeBST();
		System.out.println(bst.isBST()); // true
		System.out.println(bst.levelOrderString()); // 2-1-3 
		
		t.switchLR(); // 2-1-3
		System.out.println(t.isBST()); // true
		
		
	}
}

/*
 * Do not remove or modify this class. You must use this class to represent your tree.
 */
class BTNode<T extends Comparable<T>> {
	T data;
	BTNode<T> left, right;
	
	public BTNode(T data, BTNode<T> left, BTNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
}