package example.codeclan.com.C9_BlackJack;

import static org.junit.Assert.*;
import org.junit.*;

public class HandTest {
  Hand hand;

  @Before
  public void setup() {
    hand = new Hand();
  }

  @Test
  public void canCreateHand() {
    assertNotNull(hand);
  }

  @Test
  public void canGetCard() {
    Card testCard = new Card(CardValueEnum.NINE,CardSuitEnum.HEARTS);
    hand.addCard(testCard);
    assertEquals(testCard, hand.getCard(0));
  }

  @Test
  public void canGetCardCount() {
    Card testCard = new Card(CardValueEnum.NINE,CardSuitEnum.HEARTS);
    Card testCard2 = new Card(CardValueEnum.FOUR,CardSuitEnum.CLUBS);
    hand.addCard(testCard);
    hand.addCard(testCard2);
    assertEquals(2,hand.getCardCount() );
  }

  @Test
  public void canClearhand() {
    Card testCard = new Card(CardValueEnum.NINE,CardSuitEnum.HEARTS);
    Card testCard2 = new Card(CardValueEnum.FOUR,CardSuitEnum.CLUBS);
    hand.addCard(testCard);
    hand.addCard(testCard2);
    hand.clear();
    assertEquals(0,hand.getCardCount() );
  }

  @Test
  public void canGetCardatPos() {
    Card testCard = new Card(CardValueEnum.NINE,CardSuitEnum.HEARTS);
    Card testCard2 = new Card(CardValueEnum.FOUR,CardSuitEnum.CLUBS);
    hand.addCard(testCard);
    hand.addCard(testCard2);
    Card result = hand.getCard(1);
    assertEquals(testCard2, result);
  }

  @Test
  public void canRemoveCardatPos() {
    Card testCard = new Card(CardValueEnum.NINE,CardSuitEnum.HEARTS);
    Card testCard2 = new Card(CardValueEnum.FOUR,CardSuitEnum.CLUBS);
    hand.addCard(testCard);
    hand.addCard(testCard2);
    hand.removeCard(1); // Second card
    Card result = hand.getCard(0);
    assertEquals(testCard, result);
  }

  @Test
  public void canRemoveCard() {
    Card testCard = new Card(CardValueEnum.NINE,CardSuitEnum.HEARTS);
    Card testCard2 = new Card(CardValueEnum.FOUR,CardSuitEnum.CLUBS);
    hand.addCard(testCard);
    hand.addCard(testCard2);
    hand.removeCard(testCard); // First card
    Card result = hand.getCard(0);
    assertEquals(testCard2, result);
  }


// @Test
// public void cansortBySuit() {
//   Card testCard = new Card(CardValueEnum.NINE,CardSuitEnum.HEARTS);
//   Card testCard2 = new Card(CardValueEnum.FOUR,CardSuitEnum.CLUBS);
//   Card testCard3 = new Card(CardValueEnum.SEVEN,CardSuitEnum.DIAMONDS);
//   hand.sortBySuit();
//   // Card result = hand.getCard(1);
//   assertEquals(3, hand.getCardCount());
// }


// @Test
// public void cansortByValue() {
//   Card testCard = new Card(CardValueEnum.NINE,CardSuitEnum.HEARTS);
//   Card testCard2 = new Card(CardValueEnum.FOUR,CardSuitEnum.CLUBS);
//   Card testCard3 = new Card(CardValueEnum.SEVEN,CardSuitEnum.DIAMONDS);
//   hand.sortByValue();
//   Card result = hand.getCard(0);
//   assertEquals(testCard2, result);
// }

}