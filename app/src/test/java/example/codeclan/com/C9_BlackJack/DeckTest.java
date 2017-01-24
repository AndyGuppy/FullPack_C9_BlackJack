package example.codeclan.com.C9_BlackJack;

import static org.junit.Assert.*;
import org.junit.*;



public class DeckTest {

  Deck deck;
  Card cardOne, cardFourteen;


  @Before
  public void before() {

    deck = new Deck();
    cardOne = new Card(CardValueEnum.ACE, CardSuitEnum.HEARTS); // First card in Deck will alway be TWO of HEARTS until initial deck is shuffled
    cardFourteen = new Card(CardValueEnum.ACE, CardSuitEnum.SPADES); // First card in Deck will alway be TWO of HEARTS until initial deck is shuffled
  }

  @Test
  public void canCreateaDeck() {
    assertNotNull(deck);
  }

  @Test
  public void canDealaCard() {
    Card result = deck.dealCard();
    for (int s=0; s<13; s++)
    {
      result = deck.dealCard();
    }
    assertEquals(cardFourteen.getSuit(), result.getSuit());
    assertEquals(cardFourteen.getCardValue(), result.getCardValue());
  }
// the below code proves the shuffle deck works but i need to devise a mock test for card at a later date
  // @Test
  // public void ShuffledCards() {
  //   deck.shuffleDeck();
  //   Card result = deck.dealCard();
  //   for (int s=0; s<13; s++)
  //   {
  //     result = deck.dealCard();
  //   }
  //   assertEquals(cardFourteen.getSuit(), result.getSuit());
  //   assertEquals(cardFourteen.getCardValue(), result.getCardValue());
  // }

  @Test
  public void checkCardsarenotallthesame() {
    Card result = deck.dealCard();
    assertEquals(cardOne.getSuit(), result.getSuit());
    assertEquals(cardOne.getCardValue(), result.getCardValue());
  }

  @Test
  public void canGetSize() {
    assertEquals(52, deck.decksize());
  }

  @Test
  public void checkIfDeckReducesAfterDealingACard() {
    deck.dealCard();
    assertEquals(51, deck.decksize());
  }

}