package cardscollection;



import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aristah
 */
public class DeckOfCards {
    
   public static ArrayList<String> number_picked = new ArrayList<>();
   public static ArrayList<String> suite_picked = new ArrayList<>();
    static int count; 
      public static void main() {
		int[] deck = new int[52];
		int[] picks = new int[4];

		// Initialize the cards
		for (int i = 0; i < deck.length; i++)
			deck[i] = i;

		// Shuffle the cards
		shuffleCards(deck);

		count = 0; // number of picks
		do {
			pickCards(deck, picks);
			count++;
		} while (!isOneOFEachSuit(picks));

		// Display the four cards picked
		print(picks);

		// Display the number of picks needed to get four cards from each suit
		System.out.println("Number of picks: " + count);
	}

	/** shuffleCards randomly shuffles a deck of cards */
	public static void shuffleCards(int[] deck) {
		for (int i = 0; i < deck.length; i++) {
			// Generate an index randomly
			int index = pickCards(deck);
			int temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
	}

	/** pickCards overloaded randomly picks four cards from a deck */
	public static void pickCards(int[] deck, int[] picks) {
		for (int i = 0; i < picks.length; i++) {
			picks[i] = deck[pickCards(deck)];
		}
	}

	/** pickCards overloaded randomly picks a card from a deck */
	public static int pickCards(int[] deck) {
		return (int)(Math.random() * deck.length);
	}

	/** isOneOFEachSuit tests if one card of each suit was picked */
	public static boolean isOneOFEachSuit(int[] picks) {
		for (int i = 0; i < picks.length; i++) {
			for (int j = 0; j < picks.length; j++) {
				if (i != j && (picks[i] / 13 == picks[j] / 13))
					return false;
			}
		}
		return true;
	}

	/** print displays the four cards picked */
	public static void print(int[] picks) {
		String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
		String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9",
								"10", "Jack", "Queen", "King"};

		for (int i = 0; i < picks.length; i++) {
			 
                        number_picked.add(ranks[picks[i] % 13]);
                        suite_picked.add(suits[picks[i] / 13]);
                        //System.out.println(data);
		}
	}
    
}
