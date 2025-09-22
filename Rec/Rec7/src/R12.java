/**
 *  Name: Donghun Yoo
 *  Write a program that will count the frequencies of all adjacent alphabet pairs in the given file.
 *  (The file reading part is already implemented for you)
 *  Only count *English* alphabet pairs, meaning you should ignore all whitespaces, punctuation marks, numbers, and non-English characters.
 *  For example, if the file contains the text "This is a test", then you should count the frequencies
 *  of "Th", "hi", "is", "te", "es", and "st". Notice how 'a' is ignored since it's a stand-alone, length-1 word. 
 *  You must use a hash-based data structure to implement this program, although you're free to use other data structures as "helpers".
 */

import java.io.*;
import java.util.*;

public class R12 {

	Hashtable<String, Integer> store = new Hashtable<>();

	public R12(String filePath) {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while((line = br.readLine()) != null) {
				String[] tokens = line.split("\\s");
				// 'tokens' is now an array of Strings.
				for(String s : tokens) {
					String cleaned = s.replaceAll("[^a-zA-Z]", "").toLowerCase(); // Remove all non-alphabet characters
                    // System.out.println(cleaned); // 'cleaned' is a cleaned word
                    // TODO: Do your stuff here
					if(cleaned.length() != 1){
						for (int i = 0; i < cleaned.length(); i ++){
							String firsChar = String.valueOf(cleaned.charAt(i));
							String secChar = String.valueOf(cleaned.charAt(i + 1));
							String pair = firsChar + secChar;
							if (store.containsKey(pair)){
								store.put(pair, store.get(pair) + 1);
							}
							store.put(pair, 1);
						}
					}
				}
			}
			br.close();
		} catch(Exception e) {
			System.err.println("File error: " + e.getMessage());
			System.exit(-1);
		}
	}
	
	public void printSortedResult() {
		// TODO: Add your code to print the pair frequency counts in ascending order. 
		// The printout format is your choice (just make sure it's readable).
		// Hint: Use Arrays.sort() instead of implementing the sorting algorithm yourself.
		String[][] arr = new String[store.size()][2];
		int i = 0;
		
		for (String key : store.keySet()){
			arr[i][0] = key;
			arr[i][1] = String.valueOf(store.get(key));
			i++;
		}

	}
	
	public static void main(String[] args) {
		R12 r = new R12("foo.txt");
		r.printSortedResult();

	}

	
	public static int foo(ArrayList<Integer> a) {
		int r = a.get(0);
		for(int i = 1; i < 10; i += 2) {
			if(r < a.get(i)) r = a.get(i);
		}
		return r;
	}
	
	
}
