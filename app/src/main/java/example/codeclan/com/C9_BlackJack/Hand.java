package example.codeclan.com.C9_BlackJack;

import java.util.*;

//hand of cards

public class Hand {
  private ArrayList<Card> hand;   // The cards in the hand.

//Create a hand that empty to begin with.
  public Hand() {
    this.hand = new ArrayList<Card>();
  }

//Add a card to the hand. if there is no cards left in pack then a NULL exception will be thrown
  public void addCard(Card hcard) {
    if (hcard == null)
     throw new NullPointerException("Hand cannot contain a Null Card");
   hand.add(hcard);
 }

// Remove a card (hcard) from the hand
 public void removeCard(Card hcard) {
  hand.remove(hcard);
}

    //Remove all cards from the hand, leaving it empty.
public void clear() {
  hand.clear();
}

   //Remove a card at a specific position in the hand, will throw an Exception if the position specified does not exist
public void removeCard(int position) {
  if (position < 0 || position >= hand.size())
   throw new IllegalArgumentException("Position does not exist in hand: "
     + position);
 hand.remove(position);
}

   //Returns the number of cards in the hand.
public int getCardCount() {
  return hand.size();
}

   //Gets the card in position position in the hand will throw an Exception if the position specified does not exist
public Card getCard(int position) {
  if (position < 0 || position >= hand.size())
   throw new IllegalArgumentException("Position does not exist in hand: "
     + position);
 return hand.get(position);
}

// //Sorts the cards in the hand by suit and then value
// public void sortBySuit() {
//   ArrayList<Card> sortedHand = new ArrayList<Card>();
//   while (hand.size() > 0) {
//          int pos = 0;  // Position of minimal card.
//          Card hcard = (Card)hand.get(0);  // Minimal card.
//          for (int i = 1; i < hand.size(); i++) {
//           Card hcard1 = (Card)hand.get(i);


//           if (( hcard1.getSuit().compareTo(hcard.getSuit()) < 0) ||
//             ((hcard1.getSuit() == hcard.getSuit()) && (hcard1.getCardValue().compareTo(hcard.getCardValue())) <0)) {
//             pos = i;
//           hcard = hcard1;
//         }
//       }
//       sortedHand.add(hcard);
//       hand.remove(pos);
//     }
//     hand = sortedHand;

//   }

// //Sorts the cards in the hand by value then by suit
//   public void sortByValue() {
//     ArrayList<Card> sortedHand = new ArrayList<Card>();
//     while (hand.size() > 0) {
//          int pos = 0;  // Position of minimal card.
//          Card hcard = (Card)hand.get(0);  // Minimal card.
//          for (int i = 1; i < hand.size(); i++) {
//           Card hcard1 = (Card)hand.get(i);
//           if (( hcard1.getCardValue().compareTo(hcard.getCardValue()) <0) ||
//             ((hcard1.getCardValue() == hcard.getCardValue()) && (hcard1.getSuit().compareTo(hcard.getSuit()))<0)) {
//             pos = i;
//           hcard = hcard1;
//         }
//       }
//       //hand.remove(pos);
//       sortedHand.add(hcard);
//     }
//     hand = sortedHand;
//   }

}