package example.codeclan.com.C9_BlackJack;

import static org.junit.Assert.*;
import org.junit.*;



public class CardTest {

 private Card twoOfHearts;
 private Card threeOfSpades;

 @Before 
 public void before(){
  twoOfHearts = new Card(CardValueEnum.TWO, CardSuitEnum.HEARTS);
  threeOfSpades = new Card(CardValueEnum.THREE, CardSuitEnum.SPADES);
}

@Test
public void canGetCardValue(){
 assertEquals(2,twoOfHearts.getCardValue());
} 

@Test
public void canGetSuit() {
 assertEquals(CardSuitEnum.HEARTS, twoOfHearts.getSuit());
}


@Test
public void canGetDifferentSuit() {
 assertEquals(CardSuitEnum.SPADES, threeOfSpades.getSuit());
}
}