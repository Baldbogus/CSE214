
public class Complexity {

	public static void main(String[] s) {
		int IN_SIZE = 200000;// Change this value from 10 to 200000 with an appropriate step-size to clearly draw the plot.
		int[] x = new int[IN_SIZE];
		System.out.println(Complexity.a(x));
		System.out.println(Complexity.b(x));
	}
	
	
	public static long a(int[] arr) {
		int REP_LEN = 1000; 
		long start = System.nanoTime();
		int sum = 0;
		for(int i = 0; i < REP_LEN; i++) {
			for(int j = arr.length - 1; j > 0; j /= 2)
				for(int k = 0; k < arr.length; k++) {
					sum += (arr[j] * arr[k]);
				}
		}
		long end = System.nanoTime();
		long elapsed_time = (end - start) / 1000000; // In 'ms'
		return elapsed_time;
	}
	
	public static long b(int[] arr) {
		long start = System.nanoTime();
		int dSum = 0;
		for(int i = 0; i < Math.max(0, arr.length - 10); i+=20) {
			for(int j = 0; j < arr.length; j+=40) 
				dSum += c(arr);
		}
		long end = System.nanoTime();
		long elapsed_time = (end - start) / 1000000; // In 'ms'
		return elapsed_time;
	}

	private static long c(int[] arr) {
		long accum = 0;
		for(int i = 0; i < arr.length; i += 10) {
			accum += arr[i];
		}
		return accum;
	}
	
	
}
