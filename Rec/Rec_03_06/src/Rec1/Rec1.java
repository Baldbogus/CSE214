package Rec1;

/**
 * Name: Donghun Yoo
 * Do not import anything.
 * Write a class that stores indefinite number of integers.
 * This exercise is meant to demonstrate designing a very simple data structure. 
 */

public class Rec1 {
	int[] data;

	public Rec1() {
		this.data = new int[0]; // Fill here
	}

	/*
	 * 'index' is a zero-based index, and is guaranteed to be valid.
	 */
	public int get(int index) {

		return data[index];
	}

	/*
	 * Return the number of items present in 'data'.
	 */
	public int size() {

		return data.length;
	}

	/*
	 * Insert 'num' into 'data'. This operation should succeed indefinitely.
	 */
	public void append(int num) {
        int[] data1 = data;
		data = new int[data.length + 1];
		for(int i = 0; data.length > i; i++){
			data[i] = data1[i];
		}
		data[data.length - 1] = num;
	}

	/*
	 * Just a helper... Don't touch this.
	 */
	public static void p(String msg) { System.out.println(msg); }

	public static void main(String[] args) {
		Rec1 r = new Rec1();
		p(r.size()+""); // Should print 0
		r.append(0);
		r.append(1);
		r.append(2);
		p(r.size()+""); // Should print 3

		// The following loop should print 0, 1, 2 in that order.
		for(int i = 0; i < r.size(); i++)
			p(r.get(i) + "");

	}
}
