package question1;



public class C {
    public static void main(String[] args) {
        int[] array1 = {3,4,5,1,3,2,8,9,2,5,43,0};

    }

    public static int secondSmallest(int[] array){
        int value;
        for (int i = 0; i < array.length - 1; i ++){
            for (int j = i + 1; j < array.length; j ++){
                if (array[i] > array[j]){
                    value = array[i];
                    array[i] = array[j];
                    array[j] = value;
                }
            }
        }
        return array[2];
    }

    public static void print(int[] a){
        for (int i = 0; i < a.length; i ++){
            System.out.println(a[i]);
        }
    }

}
