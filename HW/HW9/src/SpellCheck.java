import java.io.*;
import java.util.*;

/**
 * 
 * Name: Donghun Yoo
 * Your implementation should be as efficient as possible in terms of the time complexity. 
 * You're free to import and use any of the data structures we learned in class so far.
 *
 */
public class SpellCheck {
	final String path = "dictionary.txt"; // Don't change this path in your final submission.
	HashMap<String,ArrayList<String>> correction = new HashMap<>();
	HashSet<String> dictionarySet = new HashSet<>();
	ArrayList<String> dictionaryList = new ArrayList<>();

	public SpellCheck() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null) {
				line = line.trim();
				// 'line' is a valid word
				dictionaryList.add(line); // add dictionary word to the ArrayList
				dictionarySet.add(line); // add dictionary word to the HashSet
			}
			br.close();
		} catch(Exception e) {
			System.err.println("File error: " + e.getMessage());
			System.exit(-1);
		}

		for (int i = 0; i < dictionaryList.size(); i++){
			removal(dictionaryList.get(i));
			addition(dictionaryList.get(i));
			swapping(dictionaryList.get(i));
			replacement(dictionaryList.get(i));
		}
	}
	
	/*
	 * The following method is removal of a character from the word.
	 * @param s: a word in the dictionary
	 * The time complexity of this method is O(n). Because we have to iterate through the word and remove one character at a time. Other operations are O(1).
	 */
	public void removal (String s){

		for (int i = 0; i < s.length(); i++){
			String newWord = s.substring(0, i) + s.substring(i + 1, s.length());
			if (correction.containsKey(newWord)){
				correction.get(newWord).add(s);
			} else {
				ArrayList<String> list = new ArrayList<>();

				list.add(s);
				correction.put(newWord, list);
			}
		}
	}
	/*
	 * The following method is addition of a character to the word.
	 * @param s: a word in the dictionary
	 * The time complexity of this method is O(n^2). Because we have to iterate through the word and the each character in the alphabet. Other operations are O(1).
	 */
	public void addition (String s){

		for (int i = 0; i < s.length(); i++){
			for (char c = 'a'; c < ('z' + 1); c++){
				String newWord = s.substring(0, i) + c + s.substring(i, s.length());
				if (correction.containsKey(newWord)){
					correction.get(newWord).add(s);
				} else {
					ArrayList<String> list = new ArrayList<>();

					list.add(s);
					correction.put(newWord, list);
				}
			}
		}
	}
	/*
	 * The following method is replacement of a character in the word.
	 * @param s: a word in the dictionary
	 * The time complexity of this method is O(n^2). Because we have to iterate through the word and the each character in the alphabet. Other operations are O(1).
	 */
	public void replacement (String s){

		for (int i = 0; i < s.length(); i++){
			for (char c = 'a'; c < ('z' + 1); c++){
				if(s.charAt(i) != c){
					String newWord = s.substring(0, i) + c + s.substring(i + 1, s.length());
					if (correction.containsKey(newWord)){
						correction.get(newWord).add(s);
					} else {
						ArrayList<String> list = new ArrayList<>();

						list.add(s);
						correction.put(newWord, list);
					}
				}
			}
		}
	}
	/*
	 * The following method is swapping of two adjacent characters in the word.
	 * @param s: a word in the dictionary
	 * The time complexity of this method is O(n). Because we have to iterate through the word and swap two adjacent characters. Other operations are O(1).
	 */
	public void swapping (String s){

		for (int i = 0; i < s.length() - 1; i++){
			char[] chars = s.toCharArray();

			char temp = chars[i];
			chars[i] = chars[i + 1];
			chars[i + 1] = temp;

			String newWord = new String(chars);
			if (correction.containsKey(newWord)){
				correction.get(newWord).add(s);
			} else {
				ArrayList<String> list = new ArrayList<>();

				list.add(s);
				correction.put(newWord, list);
			}
		}
	}
	/*
	 * The length of the return array should be the same as the number of words in 'sentence'.
	 * The i-th element of the return array is the substitute candidate for the i-th word in the sentence.
	 * If the i-th word is a valid word (i.e., not a typo), then the array should be empty.
	 */
	/*
	 * @param sentence: a string of words
	 * @return: an array of ArrayList<String> that contains the substitute candidates for each word in the sentence
	 * The time complexity of this method is O(n). Because we have to iterate through the sentence and check if the word is in the dictionary. Other operations are O(1).
	 */
	public ArrayList<String>[] spellCheck(String sentence) {
		String[] words = sentence.split("\\s"); // 'words' is the list of words in 'sentence'
		ArrayList<String>[] candidates = new ArrayList[words.length];

		for (int i = 0; i < words.length; i++){
			if (!dictionarySet.contains(words[i])){
				candidates[i] = correction.get(words[i]);
			}
		}
		return candidates;
	}
	
	public static void main(String[] args) {
		SpellCheck sc = new SpellCheck();
		String[] sentences = {"I ate an x", "paint the banel", "shee is a riend", "kangaru"};
		// Feel free to change the following printout routine
		for(String sent : sentences) {
			String[] words = sent.split("\\s");
			ArrayList<String>[] ret = sc.spellCheck(sent);
			if(ret == null) continue;
			String cand = "";
			for(int i = 0; i < ret.length; i++) {
				if(ret[i] == null) {
					System.out.print(words[i] + " ");
					continue;
				}
				Iterator<String> it = ret[i].iterator();
				while(it.hasNext())
					cand += (it.next() + ",");
				System.out.print("(" + cand + ") ");
			}
			System.out.println();
		}
	}

}
