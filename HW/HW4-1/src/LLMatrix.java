/**
 * Do *NOT* import or use anything from the outside. 
 * Your code should not contain anything (commands or methods or variables, etc.) that is unnecessary. 
 * This is to prevent you from blindly copying code from the lecture slides.
 * 
 * In addition to the code, you should also write header comments that describe the 
 * time complexities of the required methods. You *must* justify your answers. 
 * Don't just say "It's linear because there's a loop".
 * Remember to identify the variable of the time complexity.
 * 
 * Name: Donghun Yoo
 *
 */
public class LLMatrix {
    private Node head; // You must use this variable to internally represent a matrix.
    private int numRows, numCols;

    /*
    Time complexity is O(n^2). Because there are for loops and outside for loop is O(n) and n = numRow, inside for loop is O(m) and m = numCols.
    So, first for loops are O(n * m) = O(n^2).
    And in the Second for loops, outside for loops is O(o) and o = numRows and inside for loop is O(p) and p = numCols
    So, second for loops are O(o * p) = O(n^2). Two loops are sequential, so LLMatrix() is O(n^2)
     */


    /*
        this constructor initializes an empty 2d matrix based on the number of rows and columns provided
        it takes in the formal parameters numRows and numCols and has no return type

        this method has a time complexity of O(...) because of ...
     */
    public LLMatrix(int numRows, int numCols) {
    	this.numRows = numRows;
        this.numCols = numCols;
        Node[][] nodeArray = new Node[numRows][numCols];

        // initializes all elements in nodeArray to 0
        // has a O(n^2) time complexity due to nested for loop (iffy) -> what is n
        // O(m*n) where n is the number of rows and m is the number of columns
        for(int i = 0; i < numRows; i++){
            for (int j = 0; j < numCols; j++){
                nodeArray[i][j] = new Node(0,null);
            }
        }

        // iterates over all cells in the matrix and connects them togeter
        // has a ... complexity because of ...
        for(int i = 0; i < numRows; i++){ // O(n) due to ..
            for (int j = 0; j < numCols; j++){ // O(m) due to ...
                if(j + 1 < numCols) {
                    nodeArray[i][j].next = nodeArray[i][j + 1];
                } else if (i + 1 < numRows) {
                    nodeArray[i][j].next = nodeArray[i + 1][0];
                } else {
                    nodeArray[i][j].next = null;
                }
            }
        }
        head = nodeArray[0][0];
    }
    
    /*
     * Perform the operation: current_matrix (+) m, where 'm' is the matrix given,
     * and '(+)' is the operation determined by 'op' in the following manner (case insensitive):
     * "a": addition
     * "s": subtraction
     * "m": element-wise multiplication
     * Any other string is an invalid operation.
     * Return true if the operation is a success, and false otherwise.
     */
    /*
    Time complexity is O(n) and n is capacity = numCols + numRows. All the three loops are sequential, so Time complexity of
    arith() is O(n).
     */
    public boolean arith(LLMatrix m, String op) {
        if (numRows == m.numRows && numCols == m.numCols) {
            int capacity = numCols + numRows;
            Node originalMatrix = this.head;
            Node newMatrix = m.head;
            if(op.equals("a")){
                for (int i = 0; i < capacity; i ++){
                    originalMatrix.data += newMatrix.data;
                    originalMatrix = originalMatrix.next;
                    newMatrix = newMatrix.next;
                }
                return true;
            } else if (op.equals("s")){
                for (int i = 0; i < capacity; i ++){
                    originalMatrix.data -= newMatrix.data;
                    originalMatrix = originalMatrix.next;
                    newMatrix = newMatrix.next;
                }
                return true;
            } else if (op.equals("m")){
                for (int i = 0; i < capacity; i ++){
                    originalMatrix.data *= newMatrix.data;
                    originalMatrix = originalMatrix.next;
                    newMatrix = newMatrix.next;
                }
                return true;
            } else {
                return false; // op is not "a","s" or "m" return false
            }
        } else {
            return false; // number of cols and rows are not same return false
        }
    }

    /*
     * Perform matrix multiplication (https://en.wikipedia.org/wiki/Matrix_multiplication): current_mtx * m
     * This is different from the "m" operation given in the arith() method above.
     * Return the resulting matrix.
     */
    /*
    There are three for loops in mm() and first for loop is O(n) and n = num Rows, second for loop is O(m) and m = m.numCols, and
    third for loop is O(o) and o = numCols. Three for loops are nested.
    And two for loops are created sequential with three nested for loops.
    first for loop is O(p) and p = newMatrix.numRows, and second for loop is O(q) and q = newMatrix.numCols.
    thus, time complexity of mm() is O(n * m * o) + O(p * q) = O(n^3).
     */
    public LLMatrix mm(LLMatrix m) {
        if (numCols == m.numRows){
            int[][] originArray = this.toArray();
            int[][] anotherArray = m.toArray();
            int[][] newArray = new int[this.numRows][m.numCols];
            for (int i = 0; i < numRows; i ++){
                for(int j = 0; j < m.numCols; j ++){
                    for (int k = 0; k < numCols; k++){
                        newArray[i][j] += originArray[i][k] * anotherArray[k][j];
                    }
                }
            }

            LLMatrix newMatrix = new LLMatrix(this.numRows, m.numCols);
            Node newNode = newMatrix.head;
            for (int i = 0; i < newMatrix.numRows; i ++){
                for (int j = 0; j < newMatrix.numCols; j ++){
                    newNode.data = newArray[i][j];
                    newNode = newNode.next;
                }
            }
            return newMatrix;
        } else {
            return null; // if numCols of original matrix != numRows of m, return null
        }
    }
    
    /*
     * Return a 2D representation of the current matrix.
     */
    /*
    In toArray(), there are two nested for loops. Outside for loop is O(n) and n = numRows, and inside for loop is O(m) and m = numCols.
    Two of for loops are nested, so time complexity of toArray() is O(n * m) = O(n^2).
     */
    public int[][] toArray() {
        int[][] intArray = new int[numRows][numCols];
        Node newNode = head;
        for (int i = 0; i < numRows; i ++){
            for (int j = 0; j < numCols; j ++){
                intArray[i][j] = newNode.data;
                newNode = newNode.next;
            }
        }
    	return intArray;
    }
    
    /*
     * Return a transposed matrix of the current matrix.
     */
    /*
    In transpose(), there are two sets of nested for loops.
    For first for loops, outside for loop is O(n) and n = numRows, and inside for loop is O(m) and m = numCols. Thus, it is O(n * m).
    For second for loops, outside for loop is O(p) and p = numRows, and inside for loop is O(q) and q = numCols. Thus, it is O(p * q).
    Total time complexity of transpose() is O(n * m) + O(p * q) = O(n^2).
     */
    public LLMatrix transpose() {
        int[][] originArray = this.toArray();
        int[][] newArray = new int[numCols][numRows];

        for (int i = 0; i < numRows; i ++){
            for (int j = 0; j < numCols; j ++){
                newArray[j][i] = originArray[i][j];
            }
        }

        LLMatrix newMatrix = new LLMatrix(numCols, numRows);
        Node newnode = newMatrix.head;

        for (int i = 0; i < numCols; i++){
            for (int j = 0; j < numRows; j++){
                newnode.data = newArray[i][j];
                newnode = newnode.next;
            }
        }

    	return newMatrix;
    }
    
    /*
     * TODO: Implement get() and set() which retrieves and sets, respectively,
     * the value at the given location. The indexes are all zero-based.
     * Consider what the code should do if the given location is invalid.
     */

    /*
    In get(), there is a for loop and it is O(n) and n = index = col + row * numCols.
    Time complexity is O(n).
     */
    public int get(int row, int col) {
        Node newNode = head;
        int index = col + row * numCols;
        for (int i = 0; i < index; i ++){
            newNode = newNode.next;
        }
    	return newNode.data;
    }

    /*
    If row and col is in the bounds, set() uses a for loop. it is O(n) and n = index = col + row * numCols.
    Thus, the time complexity is O(n)
     */

    public int set(int row, int col, int data) {
        if (row >= numRows || row < 0){
            throw new ArrayIndexOutOfBoundsException(row);
        } else if (col >= numCols || row < 0) {
            throw new ArrayIndexOutOfBoundsException(col);
        } else {
            Node newNode = getHead();
            int index = col + row * numCols;
            for (int i = 0; i < index; i ++) {
                newNode = newNode.next;
            }
            newNode.data = data;
            return 0;
        }
    }

    /*
     * TODO: Implement toString() that builds a String representation of this
     * matrix. The matrix is represented as a sequence of numbers laid out in
     * a numRows-by-numCols grid.
     */
    /*
    In toString(), there are two for loops which are nested. Outside for loop is O(n) and n = numRows, and inside for loop is
    O(m) and m = numCols. Thus, total time complexity is O(n * m) = O(n^2).4
     */
    public String toString() {
        String s = "";
        Node newNode = getHead();
        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numCols; j ++){
                s = s + "[" + newNode.data + "] ";
                newNode = newNode.next;
            }
            if (i + 1 != numRows){
                s = s + "\n";
            }
        }
    	return "{" + s + "}";
    }

    /*
     * Don't modify or remove the following methods.
     */
    public int getNumRows() { return numRows; }
    public int getNumCols() { return numCols; }
    public Node getHead() { return head; }

    public static void main(String[] args) {
        LLMatrix matrix = new LLMatrix(2,2);
        matrix.set(0,0,1);
        matrix.set(0,1,2);
        matrix.set(1,0,3);
        matrix.set(1,1,4);
        String s = matrix.toString();
        System.out.println(s);

        LLMatrix matrix2 = new LLMatrix(2,2);
        matrix2.set(0,0,1);
        matrix2.set(0,1,2);
        matrix2.set(1,0,3);
        matrix2.set(1,1,4);
        String s2 = matrix2.toString();
        System.out.println(s2);

        matrix.arith(matrix2,"a");
        String s3 = matrix.toString();
        System.out.println(s3);

        matrix.arith(matrix2,"s");
        String s4 = matrix.toString();
        System.out.println(s4);

        matrix.arith(matrix2,"m");
        String s5 = matrix.toString();
        System.out.println(s5);

        System.out.println(matrix.arith(matrix2,"o")); //"o" is invalid string.

        LLMatrix matrix3 = matrix.mm(matrix2);
        String s6 = matrix3.toString();
        System.out.println(s6);

        System.out.println(matrix3.get(0,0));


        LLMatrix matrix4 = new LLMatrix(2,3);
        matrix4.set(0,0,1);
        matrix4.set(0,1,2);
        matrix4.set(0,2,3);
        matrix4.set(1,0,4);
        matrix4.set(1,1,5);
        matrix4.set(1,2,6);
        String s7 = matrix4.toString();
        System.out.println(s7);

        LLMatrix matrix5 = new LLMatrix(3,2);
        matrix5.set(0,0,1);
        matrix5.set(0,1,2);
        matrix5.set(1,0,3);
        matrix5.set(1,1,4);
        matrix5.set(2,0,5);
        matrix5.set(2,1,6);
        String s8 = matrix5.toString();
        System.out.println(s8);

        System.out.println(matrix4.arith(matrix5,"a"));
        System.out.println(matrix4.arith(matrix5,"s"));
        System.out.println(matrix4.arith(matrix5,"m"));

        LLMatrix matrix6 =matrix4.mm(matrix5);
        String s9 = matrix6.toString();
        System.out.println(s9);

        LLMatrix matrix7 = matrix4.transpose();
        String s10 = matrix7.toString();
        System.out.println(s10);

    }
}

/*
 * Do not modify the following Node class.
 */
class Node {
    int data;
    Node next;

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}