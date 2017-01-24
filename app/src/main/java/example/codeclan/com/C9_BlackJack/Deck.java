package example.codeclan.com.C9_BlackJack;

import java.util.*;

// deck of cards
public class Deck {

  private ArrayList<Card> deck;

// Create a deck of 52 cards
  public  Deck() {
    this.deck = new ArrayList<Card>();

    // build a deck of cards (52 cards)
    for (int s=0; s<4; s++)
    {
      CardSuitEnum suit = CardSuitEnum.values()[s];
      for  (int v=0; v<13; v++)     {
        Card card = new Card(CardValueEnum.values()[v],suit);
        this.deck.add(card);
      }
    }
  }

//How many cards in the deck, decrease each time one is dealt
  public int decksize() {
    return deck.size();
  }

//Add a card back into the deck
  public void addCard(Card card) {
    deck.add(card);
  }

//Deal a card
  public Card dealCard() {
    return deck.remove(0);
  }

//Shuffle the Deck
  public void shuffleDeck() {
    Collections.shuffle(deck);
  }

}