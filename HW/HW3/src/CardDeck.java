/**
 * Name: Donghun Yoo
 * ID: 114000660
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
		this.deck = new ArrayList<>();
		this.rnd = new Random();
	}
	
	/*
	 * Splits the deck into two sub-decks, where the first deck has size 'firstSize' and the second has 'secondSize'.
	 * firstSize+secondSize must be less than or equal to the current deck size. The split should happen randomly. brian
	 * The cut decks are to be returned in an array of length 2.
	 */
	// Time complexity of cutDeck is O(n^2), because there are two for loop and it used sequentially and use array list add method which is O(n).
	public CardDeck[] cutDeck(int firstSize, int secondSize) {
		if((firstSize + secondSize) > deck.size()){
			return null;
		}

		CardDeck deck1 = new CardDeck();
		CardDeck deck2 = new CardDeck();

		for(int i = 0; i < firstSize; i++){
			deck1.deck.add(deck.get(i));
		}
		for(int i = firstSize; i < firstSize + secondSize; i++){
			deck2.deck.add(deck.get(i));
		}



		CardDeck[] cuttedDeck = {deck1, deck2};
		return cuttedDeck;
	}
	
	/*
	 * Randomly shuffle (permute) the cards in this deck.
	 */
	// Time complexity of suffle is O(n), because there is a for loop and use  array list get()  which is O(1)
	public void shuffle() {
		for(int i = 0; i < deck.size(); i++){
			int random1 = rnd.nextInt(deck.size());
			int random2 = rnd.nextInt(deck.size());

			Card newCard = deck.get(random1);
			deck.set(random1, deck.get(random2));
			deck.set(random2, newCard);
		}
	}
	
	/*
	 * Return the card at the given (zero-based) index.
	 */
	//  Time complexity os getCardAt is O(1) and uses  array list get which is O(1)
	public Card getCardAt(int index) {
		if (index > deck.size() - 1 || index < 0){
			return null;
		}else{
			return deck.get(index);
		}
	}
	
	/*
	 * Reverse the order of the current deck.
	 */
	//Time complexity of reverseDeck is O(n^2), because it uses a for loop O(n) and array list add which is O(n)
	public void reverseDeck() {
		ArrayList<Card> oldDeck = new ArrayList<>();
		for (int i = 0; i < deck.size(); i ++) {
			oldDeck.add(i, deck.get(i));
		}
	}
	
	/*	 * Merge all cards in the given deck into the current deck.
	 */
	// Time complexity of mergeDeck is O(n^2), because it uses a for loop O(n) and array list add which is O(n)
	public void mergeDeck(CardDeck d) {
		for (int i = 0; i < d.size(); i ++){
			deck.add(d.deck.get(i));
		}
	}
	
	/*
	 * Add a new card 'c' on top of the current deck. Return true for a successful add and false otherwise.
	 * Use only ArrayList.add(int) when adding.
	 * A deck does not admit duplicate cards.
	 */
	// Time complexity of addCard is O(n), it uses array list add which is O(n)
	public boolean addCard(Card c) {
		if (c.isCard(c)){
			if (isContain(deck, c)){
				return false;
			} else {
				deck.add(deck.size(),c);
				return true;
			}
		} else {
			return false;
		}

	}
	
	/*
	 * Remove a card 'c' from the deck. Return true for a successful removal and false otherwise.
	 * Use only ArrayList.remove(int) when removing.
	 * When will removal fail?
	 */
	// Time complexity of removeCard is O(n), because it uses array list remove which is O(n)
	public boolean removeCard(Card c) {
		if (isContain(deck, c)){
			deck.remove(deck.indexOf(c) + 2);
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Return all cards in order, from top to bottom, in an array.
	 */
	//Time complexity of getFromToptoBottom is O(n) because it uses a for loop and arraylist get which is O(1)
	public Card[] getFromToptoBottom() {
		Card[] cards = new Card[deck.size()];
		for(int i = 0; i < cards.length; i++){
			cards[i] = deck.get(i);
		}

		return cards;
	}
	/*
	 * The following method(s) are only meant to be used during grading, and you must NEVER remove or modify them.
	 */
	public ArrayList<Card> getDeck() { return deck; }
	public int size() { return deck.size(); }

	// Time complexity of print is O(1), because it uses arraylist get which is O(1)
	public void print() {
		for(int i = 0; i < deck.size(); i++){
			System.out.printf('[' + deck.get(i).suit + " " + deck.get(i).number + ']');
		}
		System.out.println();
	}

// Time complexity of inContain is O(1), because it uses array list get which is O(1)
	public boolean isContain(ArrayList<Card> deck, Card card){
		boolean iscontain = false;
		for (int i = 0; i < deck.size(); i++){
			if(deck.get(i).number.equals(card.number) && deck.get(i).suit.equals(card.suit)){
				iscontain = true;
				break;
			}
		}
		return iscontain;
	}


	public static void main(String[] args) {
		/*
		 * The following is just a simple test code, and you may add or remove in any way you want.
		 */
		CardDeck d = new CardDeck();
		d.addCard(new Card("H", "Q")); // Queen of heart
		d.addCard(new Card("S", "A")); // Spade of ace
		d.addCard(new Card("C", "1"));// Should fail since '1' is not a valid number.
		System.out.println(d.size()); // Should print 2.
		d.print(); // print deck
		d.removeCard(new Card("H", "Q")); //Remove Queen of heart
		d.print(); // print deck
		System.out.println(d.addCard(new Card("H", "A"))); // add Heart of Ace successfully
		System.out.println(d.addCard(new Card("D", "10"))); // add Diamond of 10 successfully
		System.out.println(d.addCard(new Card("H", "A"))); // Heart of Ace already in deck
		System.out.println(d.addCard(new Card("B", "5"))); // Is not a card
		System.out.println(d.addCard(new Card("C", "Q")));
		d.print();
		System.out.println(d.removeCard(new Card("D", "10"))); // remove Diamond of 10 successfully
		System.out.println(d.removeCard(new Card("S", "5")));  // is not in the deck
		d.print();
		System.out.println(d.getCardAt(0).getSuit() + " " + d.getCardAt(0).getNumber());
		System.out.println(d.getCardAt(2).getSuit() + " " + d.getCardAt(2).getNumber());
		d.reverseDeck(); // reverse deck
		d.print();

		d.addCard(new Card("C", "K"));
		d.addCard(new Card("S", "2"));
		d.print();
		d.shuffle(); // shuffle randomly
		d.print();

		Card[] cards = d.getFromToptoBottom();
		for (int i = 0; i < cards.length; i++) {
			System.out.print("[" + cards[i].suit + " " + cards[i].number + "] ");
		}
		System.out.println();


		CardDeck[] cut = d.cutDeck(2, 2);
		System.out.printf("First cut: ");
		cut[0].print();
		System.out.printf("Second cut: ");
		cut[1].print();

		CardDeck deck2 = new CardDeck();
		deck2.addCard(new Card("S", "A"));
		deck2.addCard(new Card("D", "3"));
		d.mergeDeck(deck2); //merge deck and deck 2
		d.print();

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
		this.number = number;
		this.suit = suit;
	}
// show card is a trump card
	public boolean isCard (Card card) {
		boolean iscard = false;
		if (card.suit.equals("H") || card.suit.equals("C") || card.suit.equals("S") || card.suit.equals("D")){
			if (card.number.equals("A") || card.number.equals("2") || card.number.equals("3") || card.number.equals("4") ||
					card.number.equals("5") ||  card.number.equals("6") ||  card.number.equals("7") ||  card.number.equals("8") ||
					card.number.equals("9") ||  card.number.equals("10") ||  card.number.equals("J") ||  card.number.equals("Q") ||
					card.number.equals("K")){
				iscard = true;
			}
		}
		return iscard;
	}
	
	public String getSuit() { return suit; }
	public String getNumber() { return number; }
}