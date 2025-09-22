public class TimeComplexity {
    public static void main(String[] args) {
        int n = 100; // C1 = O(1) because it is a constant time operation to init an int
        for(int i = 0; i < n ; i++){ // C2 = O(n + 1) n iterations + more to check loop exit
            System.out.println(i); // C3 = O(n) n iterations
        } // O(n)

        for(int i = 0; i < n ; i+=2){
            System.out.println(i);
        } // O(n) // TODO: make sure what the time complexity is

        for(int i = 0; i < n; i*=2){
            System.out.println(i);
        } //O(log n)

        for(int i = n; i > 0; i /= 2){
            System.out.println(i);
        }//O(log n)

        for(int i = n; i > 0; i /= 2){
            for(int j = 0; j< n; j++){
                System.out.println(i + j);
            }
        }//O(nlogn)
    }

    // Pre: takes in integers a and b
    // Post: returns the addition of a + b
    public static int foo(int a, int b){
        return a + b;
    }
}
