package question1;

public class D {
    public static void main(String[] args) {
        int[][] arr1 ={
                {1,2,3},
                {4,5,6}
        };
        int[][] arr2 ={
                {1,2,3},
                {4,5,6}
        };
        print(addArrays(arr1,arr2));
    }

    public static int[][] addArrays(int[][] array1, int[][] array2){
        int[][] newArray = new int[array1.length][array1[0].length];
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[0].length; j++){
                newArray[i][j] = array1[i][j] + array2[i][j];
            }
        }
        return newArray;
    }

    public static void print(int[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++){
                System.out.println(array[i][j]);
            }
        }
    }
}
