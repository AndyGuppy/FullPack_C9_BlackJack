package example.codeclan.com.C9_BlackJack;


public class Card 
{
  
  private CardValueEnum cardValue;
  private CardSuitEnum suit;

  public Card (CardValueEnum cardValue, CardSuitEnum suit)
  {
    this.cardValue = cardValue;
    this.suit = suit;
  }
  
  public CardSuitEnum getSuit()
  {
    return this.suit;
  }


  public int getCardValue()
  {
    int retval;

    retval = 0;
    switch(cardValue) {
    case ACE:retval = 1;
    break;
    case TWO:retval = 2;
    break;
    case THREE:retval = 3;
    break;
    case FOUR:retval = 4;
    break;
    case FIVE:retval = 5;
    break;
    case SIX:retval = 6;
    break;
    case SEVEN:retval = 7;
    break;
    case EIGHT:retval = 8;
    break;
    case NINE:retval = 9;
    break;
    case TEN:retval = 10;
    break;
    case JACK:retval = 11;
    break;
    case QUEEN:retval = 12;
    break;
    case KING:retval = 13;
    break;
    }
    return retval;
  }


  public String getCardStringValue()
  {
    String valueString;

    valueString = "";
    switch(cardValue) {
      case ACE:valueString = "ace";
        break;
      case TWO:valueString = "two";
        break;
      case THREE:valueString = "three";
        break;
      case FOUR:valueString = "four";
        break;
      case FIVE:valueString = "five";
        break;
      case SIX:valueString = "six";
        break;
      case SEVEN:valueString = "seven";
        break;
      case EIGHT:valueString = "eight";
        break;
      case NINE:valueString = "nine";
        break;
      case TEN:valueString = "ten";
        break;
      case JACK:valueString = "jack";
        break;
      case QUEEN:valueString = "queen";
        break;
      case KING:valueString = "king";
        break;
    }
    return valueString;
  }

  public String getCardStringSuit()
  {
    String suitString;

    suitString = "";
    switch(suit) {
      case HEARTS:suitString = "hearts";
        break;
      case CLUBS:suitString = "clubs";
        break;
      case DIAMONDS:suitString = "diamonds";
        break;
      case SPADES:suitString = "spades";
        break;
    }
    return suitString;
  }
  @Override
  public String toString() {
    return getCardStringValue() + getCardStringSuit();
  }


  public String toPrettyString() {
    return getCardStringValue() + "  of  " + getCardStringSuit();
  }
}