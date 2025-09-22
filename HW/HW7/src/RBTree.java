
/**
 * Do not import anything.
 * Name: Donghun Yoo
 * 
 */

public class RBTree {
	
	/*
	 * Return true if the given tree is a valid red-black tree, and false otherwise.
	 * @param root the root of the tree
	 * @return true if the tree is a valid red-black tree, false otherwise
	 * The time complexity of this method should be O(n), where n is the number of nodes in the tree.
	 */
	public static <T extends Comparable<T>> boolean isValidRBTree(BinaryNode<T> root) {
		if (root == null){
			 return true;
		}
		if(!root.color){ // color is false color is black, if root is black (rule 1)
			if(!redNodeHasBlackChildren(root)){ // rule 2
				return false;
			} else {
				if (isSameBlackHeight(root) != -1)
				return true;
			}
		}

		return false;
	}

	/*
	 * Return true if the given red node has black children, and false otherwise.
	 * @param node the node to check
	 * @return true if the node is a red node with black children, false otherwise
	 * The time complexity of this method should be O(n), where n is the number of nodes in the tree.
	 */	
	public static <T extends Comparable<T>> boolean redNodeHasBlackChildren(BinaryNode<T> node){
		if (node == null){
			return false; 
		}

		if(node.color){
			if (node.left != null && node.right != null){
				if (node.left.color == false && node.right.color == false){
					return true;
				}
			}
		}
		boolean left = redNodeHasBlackChildren(node.left);
		boolean right = redNodeHasBlackChildren(node.right);

		return left || right;
	}

	/*
	 * Return the black height of the given node.
	 * The black height is defined as the number of black nodes from the given node to the leaf nodes.
	 * If the tree is not a valid red-black tree, return -1.
	 * @param node the node to check
	 * @return the black height of the node, or -1 if the tree is not a valid red-black tree
	 * The time complexity of this method should be O(n), where n is the number of nodes in the tree.
	 */
	public static <T extends Comparable<T>> int isSameBlackHeight(BinaryNode<T> node){
		if(node == null){
			return 1;
		}
		int left = isSameBlackHeight(node.left);
		int right= isSameBlackHeight(node.right);

		if (left == -1 || right == -1 || left != right){
			return -1;
		}
		int Black = (node.color == false) ? 1 : 0;
		return left + Black;

	}
	
	/*
	 * Perform left/right rotation around the node containing 'data' (i.e., the tree doesn't contain duplicate values).
	 * The parameter 'rotateLeft' is true for left rotation, and false for right rotation.
	 * Return true if rotation was successful, and false otherwise.
	 */
	public static <T extends Comparable<T>> boolean rotate(BinaryNode<T> root, T data, boolean rotateLeft) {
		if(rotateLeft){
			if (root != null){
				BinaryNode<T> r = root.right;
				if(r.left != null){

				}
			}
		}
		return false;
	}
}

/*
 * Do not modify this class. Doing so will result in a score of 0.
 */
class BinaryNode <T extends Comparable<T>> {
	T data;
	BinaryNode<T> left, right;
	boolean color;
	
	public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right, boolean color) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.color = color;
	}
}