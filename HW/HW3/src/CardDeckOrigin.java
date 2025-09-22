/**
 * Name:
 * Do not import anything else. Don't forget the time complexity analysis for each required method.
 * 
 */
import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
	/*
	 * This 'deck' is the main data structure you should use to implement a card deck.
	 * You must not maintain or acquire an object array to represent a deck. Use only the following.
	 */
	private ArrayList<Card> deck;
	
	private Random rnd; // To be used in shuffle() and cutDeck().
	
	/*
	 * TODO: Implement the following methods. You may add helper methods and classes whenever necessary.
	 */
	public CardDeck() {
		
	}
	
	/*
	 * Splits the deck into two sub-decks, where the first deck has size 'firstSize' and the second has 'secondSize'.
	 * firstSize+secondSize must be less than or equal to the current deck size. The split should happen randomly. 
	 * The cut decks are to be returned in an array of length 2.
	 */
	public CardDeck[] cutDeck(int firstSize, int secondSize) {
		return null;
	}
	
	/*
	 * Randomly shuffle (permute) the cards in this deck.
	 */
	public void shuffle() {

	}
	
	/*
	 * Return the card at the given (zero-based) index.
	 */
	public Card getCardAt(int index) {
		return null;
	}
	
	/*
	 * Reverse the order of the current deck.
	 */
	public void reverseDeck() {
		
	}
	
	/*
	 * Merge all cards in the given deck into the current deck.
	 */
	public void mergeDeck(CardDeck d) {
		
	}
	
	/*
	 * Add a new card 'c' on top of the current deck. Return true for a successful add and false otherwise.
	 * Use only ArrayList.add(int) when adding.
	 * A deck does not admit duplicate cards.
	 */
	public boolean addCard(Card c) {
		return false;
	}
	
	/*
	 * Remove a card 'c' from the deck. Return true for a successful removal and false otherwise.
	 * Use only ArrayList.remove(int) when removing.
	 * When will removal fail?
	 */
	public boolean removeCard(Card c) {
		return false;
	}
	
	/*
	 * Return all cards in order, from top to bottom, in an array.
	 */
	public Card[] getFromToptoBottom() {
		return null;
	}
	/*
	 * The following method(s) are only meant to be used during grading, and you must NEVER remove or modify them.
	 */
	public ArrayList<Card> getDeck() { return deck; }
	public int size() { return deck.size(); }
	
	public static void main(String[] args) {
		/*
		 * The following is just a simple test code, and you may add or remove in any way you want.
		 */
		CardDeck d = new CardDeck();
		d.addCard(new Card("H", "Q")); // Queen of heart
		d.addCard(new Card("S", "A")); // Spade of ace
		d.addCard(new Card("C", "1")); // Should fail since '1' is not a valid number.
		System.out.println(d.size()); // Should print 2.
	}
	
}

/*
 * You can add more methods and fields, but the ones given must remain unaltered.
 */
class Card {
	String suit, number;
	/*
	 * 'suit' should be one of 'H', 'C', 'S', 'D'
	 * 'number' should be one of 'A', '2', '3', ..., 'J', 'Q', 'K'.
	 */
	public Card(String suit, String number) {
	}
	
	public String getSuit() { return suit; }
	public String getNumber() { return number; }
}