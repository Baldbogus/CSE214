public class Foo <K>{
    K[] x;
    public Foo(K[] k){
        this.x = x;
    }
    public K getAt(int i){
        return x[i];
    }

    public static void main(String[] args) {
        Integer[] x = {1, 2, 3, 4, 5};
        Foo <Integer> f = new Foo<x>;

    }
}

