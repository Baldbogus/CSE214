/**
 * Implement your Stack-based array list.
 * You should use only the 'stack' field in the Stack2AL class. That is, do not write your own class to implement a stack.
 * Do not import anything else other than the Stack provided. 
 * @param <E>
 */
/*
Name: Donghun Yoo
 */
import java.util.Stack;

public class Stack2AL<E> {
	
	private Stack<E> stack;
	
	public Stack2AL() {
		this.stack = new Stack<>();
    }
	
	/*
	 * TODO: Implement the following methods using 'stack' and provide the time complexity analysis.
	 * Use only Stack.push(), Stack.pop(), Stack.peek(). 
	 * Calling any other methods of Stack will result in a 0 for whatever method that contains it.
	 */
	/*
		@param obj Object that find the first occurrence in the stack
		@return 	the first occurrence of the given object
		The time complexity of indexOf() is O(n). First while loop is O(n) because it iterates when the stack is not empty,
		so n is the number of value in the stack.
		Also, second while loop is O(m) because it also iterates when the temp stack is not empty and it means m is
		the number of value in the temp stack.
		Two of while loop operates sequentially and rest of all(including pop(), push(), and peek()) are simple statement(O(1)),
		so the total time complexity is O(n + m) = O(n).
 	*/
	public int indexOf(E obj) {
		Stack<E> tempStack = new Stack<>(); //Make temporary stack for store value that pop out from original stack
		// Store all the values in tempStack using push. The order of tempStack is reversed with original stack.
		while (!isEmpty(stack)){
			tempStack.push(stack.pop());
		}

		// Define index and TorF.
		// Index gets +1 every single time when the values came back to original stacks from temp stack and
		// if there is the same value with obj, TorF become True.
		int index = 0;
		boolean TorF = false;
		while (!isEmpty(tempStack)){
			E value = tempStack.pop();
			stack.push(value);

			if (value.equals(obj)){
				TorF = true;
			}
			if (TorF == false){
				index ++;
			}
		}
		return index;
	}

	/*
	Firstly, move all the value in the stack to the temp stack
	and count size when the value in the temp stack came back to the original stack
	@return size of stack
	The time complexity of size() is O(n). First while loop is O(n) because it iterates when the stack is not empty,
	so n is the number of value in the stack.
	Also, second while loop is O(m) because it also iterates when the temp stack is not empty and it means m is
	the number of value in the temp stack.
	Two of while loop operates sequentially and rest of all (including pop(), push(), and peek()) are simple statement(O(1)),
	so the total time complexity is O(n + m) = O(n).
	 */
	public int size() {
		int size = 0;
		Stack<E> tempStack = new Stack<>();
		// Store all the values in tempStack using push. The order of tempStack is reversed with original stack.
		while (!isEmpty(stack)){
			tempStack.push(stack.peek());
			stack.pop();
		}
		// Get back all the materials in temp stack to original stack.
		while (!isEmpty(tempStack)){
			stack.push(tempStack.pop());
			size++;
		}

		return size;
	}

	/*
	Replaces the value at the index position in the stack with certain value.

	@param	index	the index that should be modified value in the stack.
	@param	obj		the value to be stored in index position in the stack.
	@return			the value that previously at the index position.
	The time complexity of set() is O(n). First while loop is O(n) because it iterates when the stack is not empty,
	so n is the number of value in the stack.
	Also, second while loop is O(m) because it also iterates when the temp stack is not empty and it means m is
	the number of value in the temp stack.
	Two of while loop operates sequentially and rest of all(including pop(), push(), and peek()) are simple statement(O(1)),
	so the total time complexity is O(n + m) = O(n).
	 */
	public E set(int index, E obj) {
		// Check index is usual number.
		if (index > size() || index < 0){
			return null;
		} else {
			Stack<E> tempStack = new Stack<>();
			// Store all the values in tempStack using push. The order of tempStack is reversed with original stack.
			while (!isEmpty(stack)){
				tempStack.push(stack.peek());
				stack.pop();
			}

			//Define oldvalue.
			//Returning all the values in the temp stack to the original stack.
			//In the middle of doing returning, index become -1 when the one of value go back to the original stack.
			//If the value of index become 0, the value which is obj stored in original stack and the index position value
			//become an old value.
			E oldValue = null;
			while (!isEmpty(tempStack)){
				if (index == 0){
					stack.push(obj);
					oldValue = tempStack.peek();
					tempStack.pop();
					index = -1; // declare index -1 not to used after find the value at the index position
				} else {
					stack.push(tempStack.peek());
					tempStack.pop();
					index --;
				}
			}
			return oldValue;
		}
	}

	/*
	Return the value at the index position in the stack

	@param	index	the index of the value should be returned.
	@return			The value at the index position in the stack.
	The time complexity of get() is O(n). First while loop is O(n) because it iterates when the stack is not empty,
	so n is the number of value in the stack.
	Also, second while loop is O(m) because it also iterates when the temp stack is not empty and it means m is
	the number of value in the temp stack.
	Two of while loop operates sequentially and rest of all(including pop(), push(), and peek()) are simple statement(O(1)),
	so the total time complexity is O(n + m) = O(n).
	 */
	public E get(int index) {
		// Check index is usual number.
		if (index > size() || index < 0){
			return null;
		} else {
			Stack<E> tempStack = new Stack<>();
			// Store all the values in tempStack using push. The order of tempStack is reversed with original stack.
			while (!isEmpty(stack)){
				tempStack.push(stack.peek());
				stack.pop();
			}
			// Define i, i is counting value that the index of current value returning from temp stack to  original stack.
			// Define value, value will be a value from index position.
			// Returning all the values from temp stack to original stack.
			// If i is the same with index, value from the temp stack become the value in index position.
			int i = 0;
			E value = null;
			while (!isEmpty(tempStack)){
				if (i == index){
					value = tempStack.peek();
				}
				stack.push(tempStack.peek());
				tempStack.pop();
				i++;
			}
			return value;
		}
	}

	/*
	Adding a new value to the index position.
	@param	index	the position that the new value should be added.
	@param	obj		the new value added at the index position
	@return			if it operates successfully, return true. if not, return false.
	The time complexity of add(int index, E obj) is O(n). First while loop is O(n) because it iterates when the stack is not empty,
	so n is the number of value in the stack.
	Also, second while loop is O(m) because it also iterates when the temp stack is not empty and it means m is
	the number of value in the temp stack.
	Two of while loop operates sequentially and rest of all(including pop(), push(), and peek()) are simple statement(O(1)),
	so the total time complexity is O(n + m) = O(n).
	 */
	public boolean add(int index, E obj) {
		// Check index is usual number.
		if (index > size() || index < 0){
			return false;
		} else {
			Stack<E> tempStack = new Stack<>();
			// Store all the values in tempStack using push. The order of tempStack is reversed with original stack.
			while (!isEmpty(stack)){
				tempStack.push(stack.pop());
			}

			// Returning all the value in the temp stack to the original stack.
			// in the middle of that, index become -1 when a value of return to the original stack.
			while (!isEmpty(tempStack)){
				if (index == 0){
					stack.push(obj);
					index = -1; // declare index -1 not to used after find the value at the index position
				} else {
					stack.push(tempStack.pop());
					index--;
				}
			}
			return true;
		}
	}

	/*
	Adding a new value at the top of stack
	@param	obj		the value that should add at the end of stack
	@return			return true after adding new value to the stack
	The time complexity of add(E obj) is O(1). Because push() is O(1), so all the statement are O(1).
	Thus, the total time complexity of add() is O(1).
	 */
	public boolean add(E obj) {
		stack.push(obj); // add new value, obj at the top of stack.
		return true;
	}

	/*
	Remove the object at the give index
	@param	 index	the index position of the value should be removed
	@return			if the remove operates successfully, return true, if not, return false.
	The time complexity of remove() is O(n). First while loop is O(n) because it iterates when the stack is not empty,
	so n is the number of value in the stack.
	Also, second while loop is O(m) because it also iterates when the temp stack is not empty and it means m is
	the number of value in the temp stack.
	Two of while loop operates sequentially and rest of all(including pop(), push(), and peek()) are simple statement(O(1)),
	so the total time complexity is O(n + m) = O(n).
	 */
	public boolean remove(int index) {
		// Check index is usual number.
		if (index > size() || index < 0){
			return false;
		} else {
			Stack<E> tempStack = new Stack<>();
			// Store all the values in tempStack using push. The order of tempStack is reversed with original stack.
			while (!isEmpty(stack)){
				tempStack.push(stack.peek());
				stack.pop();
			}

			//returning all the values from tempstack to the original stack.
			// index become -1 when every one of value move to the original stack.
			//if index become 0, the value in the tempstack should be removed.
			// and do rest of returning.
			while (!isEmpty(tempStack)){
				if (index == 0){
					tempStack.pop();
					index = -1; // declare index -1 not to used after find the value at the index position
				} else {
					stack.push(tempStack.peek());
					tempStack.pop();
					index --;
				}
			}
			return true;
		}
	}

	/*
	Clear all the value in the stack
	Use while loop to pop all the value in the stack
	The time complexity of clear() is O(n). Because there is a while loop that iterates when the stack is not empty.
	And it means, the times it iterates is the number of value in stack. Thus, n is the number of value in stack.
	Thus, the time complexity is O(n).
	 */
	public void clear() {
		while (!isEmpty(stack)){
			stack.pop();
		}
	}

	/*
	print all the values in the stack
	The time complexity of print is O(n). First while loop is O(n) because it iterates when the stack is not empty,
	so n is the number of value in the stack.
	Also, second while loop is O(m) because it also iterates when the temp stack is not empty and it means m is
	the number of value in the temp stack.
	Two of while loop operates sequentially and rest of all(including pop(), push(), and peek()) are simple statement(O(1)),
	so the total time complexity is O(n + m) = O(n).
	 */
	public void print() {
		Stack<E> tempStack = new Stack<>();
		// Store all the values in tempStack using push. The order of tempStack is reversed with original stack.
		while (!isEmpty(stack)){
			tempStack.push(stack.pop());
		}

		// Print all the element when the value returning from temp stack to original stack.
		System.out.printf("[");
		while (!isEmpty(tempStack)){
			System.out.printf(" " + tempStack.peek() + " ");
			stack.push(tempStack.peek());
			tempStack.pop();
		}
		System.out.println("]");
	}

	/*
	Return true when the stack is empty, or return false.
	@param	stack	the stack that should be checked it is empty or not.
	@return 		if stack is empty, return true, if not, return false.
	The time complexity of isEmpty is O(1). the time complexity of peek is O(1)
	and all the rest of statements are simple statement.
	Thus, the total time complexity is O(1).
	 */
	public boolean isEmpty(Stack<E> stack){
		try{
			stack.peek(); // if peek() is exist, it returns false.
			return  false;
		} catch (Exception e){//if peek occur EmptyStackException, it returns true.
			return  true;
		}
	}

	public static void main(String[] args) {
		Stack2AL<Integer> integerStack = new Stack2AL<>();
		integerStack.add(1);
		integerStack.add(2);
		integerStack.add(3);
		integerStack.add(4);
		integerStack.add(5);
		integerStack.add(8);
		integerStack.add(9);
		integerStack.print();

		System.out.println("integerStack.size() = " + integerStack.size());

		integerStack.add(5, 7);
		integerStack.print();
		integerStack.add(6, 6);
		integerStack.print();

		System.out.println("integerStack.size() = " + integerStack.size());

		System.out.println("integerStack.indexOf(5) = " + integerStack.indexOf(5));
		System.out.println("integerStack.indexOf(9) = " + integerStack.indexOf(9));

		integerStack.set(5, 6);
		integerStack.print();
		integerStack.set(6, 7);
		integerStack.print();

		System.out.println("integerStack.get(5) = " + integerStack.get(5));
		System.out.println("integerStack.get(0) = " + integerStack.get(0));
		System.out.println("integerStack.get(8) = " + integerStack.get(8));

		integerStack.remove(4);
		integerStack.print();
		integerStack.remove(7);
		integerStack.print();

		integerStack.clear();
		integerStack.print();



	}
}
