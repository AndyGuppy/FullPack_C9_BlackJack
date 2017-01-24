package example.codeclan.com.C9_BlackJack;

import static org.junit.Assert.*;
import org.junit.*;

public class BlackJackTest {
  BlackJackHand blackjackhand;


  @Before
  public void setup() {
    blackjackhand = new BlackJackHand();
  }

  @Test
  public void canGetBlackJackValue() {
  Card testCard = new Card(CardValueEnum.ACE,CardSuitEnum.HEARTS);
  Card testCard2 = new Card(CardValueEnum.NINE,CardSuitEnum.CLUBS);
  blackjackhand.addCard(testCard);
  blackjackhand.addCard(testCard2);
  assertEquals(20, blackjackhand.getBlackjackValue());

  }
}