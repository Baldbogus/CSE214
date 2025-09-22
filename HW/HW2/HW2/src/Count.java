public class Count {
    public static void main(String[] args) {
        int[] a = {1, 12, 3, 6, 10, -1, 5};
        int[] b = {1, 2, 3};
        System.out.println(linearCount(a, b));
        System.out.println(quadraticCount(a, b));
    }

    public static int linearCount(int[] a, int[] b){
        int sumb = 0;
        int count = 0;
        for(int i = 0; i < b.length; i++){
            sumb += b[i];
        }
        for (int i = 0; i < a.length; i++){
            if (sumb < a[i]){
                count ++;
            }
        }
        return count;
    }

    public static int quadraticCount(int[] a, int[] b){
        int count = 0;
        for (int i = 0; i < a.length; i++){
            int sumb = 0;
            for(int j = 0; j < b.length; j++){
                sumb += b[j];
            }
            if (sumb < a[i]){
                count ++;
            }
        }
        return count;
    }
}
