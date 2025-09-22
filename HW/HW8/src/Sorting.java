
/**
 * You are to implement merge/quick sort algorithms in the following methods.
 * You may not use the obvious strategy of collecting all items into a List before applying the 'standard' merge/quick sorts on that List.
 * The implementations you provide MUST operate directly on the linked list given.
 * Provide the time complexity analysis for each method.
 * Do not import anything else.
 * Name: Donghun Yoo
 */
public class Sorting<T extends Comparable<T>> {

	/*
	 * @param arr
	 * @return sorted linked list
	 * Time complexity: O(n log n), because we are dividing the list into two halves at each step and merging them back together. Also,finding the middle of the list takes O(n) time.
	 */
	public LLNode<T> mergeSort(LLNode<T> arr) {
		if (arr == null || arr.next == null){
			return arr;
		}

		LLNode<T> mid = findMid(arr);
		LLNode<T> secondArr = mid.next;
		mid.next = null;
		LLNode<T> first = mergeSort(arr);
		LLNode<T> second = mergeSort(secondArr);

		return merge(first, second);
	}
	/*
	 * @param first
	 * @param second
	 * @return merged linked list
	 * Time complexity: O(n), because we are iterating through both lists once to merge them together.
	 */

	public LLNode<T> merge (LLNode<T> first, LLNode<T> second){
		LLNode<T> merged = null;
		LLNode<T> tail = null;

		while (first != null && second != null) {
			LLNode<T> nodeToAdd;
			if (first.data.compareTo(second.data) <= 0) {
				nodeToAdd = first;
				first = first.next;
			} else {
				nodeToAdd = second;
				second = second.next;
			}
			if (merged == null) {
				merged = tail = nodeToAdd;
			} else {
				tail.next = nodeToAdd;
				tail = tail.next;
			}
		}

		LLNode<T> remaining;
		if (first != null) {
			remaining = first;
		} else {
			remaining = second;
		}

		if (tail != null) {
			tail.next = remaining;
		} else {
			merged = remaining;
		}

		return merged;
	}
	/*
	 * @param head
	 * @return middle node of the linked list
	 * Time complexity: O(n), because we are iterating through the list once to find the size and then again to find the middle node.
	 */
	public LLNode<T> findMid(LLNode<T> head){
		if (head == null || head.next == null){
			return head;
		}

		LLNode<T> curr = head;
		int size = 0;
		while(curr != null){
			size ++;
			curr = curr.next;
		}
		curr = head;
		for (int i = 0; i < (size-1)/2; i++){
			curr = curr.next;
		}
		return curr;
	}

	/*
	 * @param arr
	 * @return sorted linked list
	 * Time complexity: O(n^2), because we are iterating through the list once to partition it and then again to sort the two partitions.
	 */
	
	public LLNode<T> quickSort(LLNode<T> arr) {
		if (arr == null || arr.next == null){
			return arr;
		}

		LLNode<T> pivot = arr;

		LLNode<T> largeHead = null;
		LLNode<T> largeTail = null;
		LLNode<T> smallHead = null;
		LLNode<T> smallTail = null;
		LLNode<T> equalHead = null;
		LLNode<T> equalTail = null;

		LLNode<T> pointer = arr;

		while (pointer != null){
			LLNode<T> next = pointer.next;
			pointer.next = null;

			if (pointer.data.compareTo(pivot.data) < 0) {
				if (smallHead == null) {
					smallHead = pointer;
					smallTail = pointer;
				}else {
					smallTail.next = pointer;
					smallTail = pointer;
				}
			} else if (pointer.data.compareTo(pivot.data) == 0){
				if (equalHead == null){
					equalHead = pointer;
					equalTail = pointer;
				} else {
					equalTail.next = pointer;
					equalTail = pointer;
				}
			} else {
				if (largeHead == null) {
					largeHead = pointer;
					largeTail = pointer;
				}else {
				largeTail.next = pointer;
				largeTail = pointer;
				}
			}
			pointer = next;
		}

		LLNode<T> first = quickSort(smallHead);
		LLNode<T> second = equalHead;
		LLNode<T> third = quickSort(largeHead);

		LLNode<T> merged = null;
		if (first == null) {
			merged = second;
		} else {
			merged = first;
			LLNode<T> firstTail = first;
			while (firstTail.next != null){
				firstTail = firstTail.next;
			}
			firstTail.next = second;
		}
		if (merged == null) {
			merged = third;
		} else {
			LLNode<T> secondTail = merged;
			while (secondTail.next != null){
				secondTail = secondTail.next;
			}
			secondTail.next = third;
		}

		return merged;
	}

	public static void main(String[] args) {
		LLNode<Integer> head = new LLNode<>();
		head.data = 4;
		head.next = new LLNode<>();
		head.next.data = 2;
		head.next.next = new LLNode<>();
		head.next.next.data = 5;
		head.next.next.next = new LLNode<>();
		head.next.next.next.data = 1;
		head.next.next.next.next = new LLNode<>();
		head.next.next.next.next.data = 3;

		Sorting<Integer> sorter = new Sorting<>();
		LLNode<Integer> sorted = sorter.mergeSort(head);
		System.out.printf("Merge sort: ");

		printList(sorted);

		LLNode<Integer> head2 = new LLNode<>();
		head2.data = 4;
		head2.next = new LLNode<>();
		head2.next.data = 2;
		head2.next.next = new LLNode<>();
		head2.next.next.data = 5;
		head2.next.next.next = new LLNode<>();
		head2.next.next.next.data = 1;
		head2.next.next.next.next = new LLNode<>();
		head2.next.next.next.next.data = 3;

		Sorting<Integer> sorter2 = new Sorting<>();
		LLNode<Integer> sorted2 = sorter2.quickSort(head2);

		System.out.printf("Quick sort: ");
		printList(sorted2);
	}
	
	public static <T extends Comparable<T>> void printList(LLNode<T> node) {
		while (node != null) {
			System.out.print(node.data + " ");
			node = node.next;
		}
		System.out.println();
	}
}

/*
 * Do NOT modify this class. Doing so will result in a 0 for correctness.
 */
class LLNode<T extends Comparable<T>> {
	LLNode<T> next;
	T data;
}