package Rec2;

/**
 * Name: Donghun Yoo
 * 
 * An interface definition for a simple integer collection.
 * You are allowed to create your own class and methods, but do not import anything.
 * See main() for instructions.
 */
interface Listable{
	public int getNth(int n); // Return the n-th element in the collection.
	public boolean exists(int x); // Returns true if 'x' exists in the collection, and false otherwise.
	public void append(int x); // Insert the element 'x' at the end of the collection.
	public int size(); //Returns the length of the collection.
	public boolean isSame(Listable s); // Returns true if 's' contains the same sequence of elements. (i.e., functionally the same as .equals())
	public void clear(); // Remove everything from the collection.
}

public class InterfaceRec {


	public void p(String s) { System.out.println(s); }
	
	public void printInfo(Listable l, Listable l2) {
		p("Size: " + l.size());
		p("Has 1: " + l.exists(1));
		l.append(0);
		l.append(1);
		l.append(2);
		p("New size: " + l.size()); // Should be 3 more than the first line's output
		for(int i = 0; i < l.size(); i++)
			p(i + "-th element: " + l.getNth(i));
		
		l2.clear();
		l.clear();
		p("l.size = " + l.size()); // 0
		p("l2.size = " + l2.size()); // 0
		
		for(int i = 0; i < 10; i++) l.append(i);
		p("l == l2? " + l.isSame(l2)); // False
		
		for(int i = 0; i < 10; i++) l2.append(i);
		p("l == l2? " + l.isSame(l2)); // True
	}
	
	public static void main(String[] args) {
		Listable l = new A();
		Listable l2 = new A();
		// TODO: Provide implementation so that the following line (when uncommented) will work as intended.
		//  You should also provide code that initializes l and l2.
		(new InterfaceRec()).printInfo(l, l2);
	}

}

class A implements Listable{

	int[] a;

	public A() {
		a = new int[0];
	}

	public int getNth(int n){
		return a[n];
	}// Return the n-th element in the collection.

	public boolean exists(int x){
		boolean TorF = false;
		for(int i = 0; i < a.length; i++){
			if(a[i] == x){
				TorF = true;
			}
		}
		return TorF;
	} // Returns true if 'x' exists in the collection, and false otherwise.
	public void append(int x){
		int[] b = a;
		a = new int[a.length + 1];
		for(int i = 0; a.length > i; i++){
			a[i] = b[i];
		}
		a[a.length - 1] = x;
	} // Insert the element 'x' at the end of the collection.
	public int size(){
		return a.length;
	} //Returns the length of the collection.
	public boolean isSame(Listable s){
		boolean same = false;
		for(int i = 0; i < a.length; i++){
			if(a[i] != s[i]){

			}
		}
		return same;
	}

    public void clear(){
        a = new int[0];
    }
} // Returns true if 's' contains the same sequence of elements. (i.e., functionally the same as .equals())

