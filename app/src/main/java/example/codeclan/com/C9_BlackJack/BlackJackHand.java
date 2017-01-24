package example.codeclan.com.C9_BlackJack;

import java.util.*;

public class BlackJackHand extends Hand {
   

   public int getBlackjackValue() {
      
      int handval;
      boolean ace;  
      int handSize; 

      handval = 0;
      ace = false;
      handSize = getCardCount();
      
      //itterate through hand adding cards
      for ( int i = 0;  i < handSize;  i++ ) {

         Card card;  
         int cardVal;  //gamevalue of card
         card = getCard(i);
         cardVal = card.getCardValue();  // The normal value, 1 to 13.
         if (cardVal > 10) {
            cardVal = 10;   // Jack, Queen, or King.
         }
         if (cardVal == 1) {
            ace = true;     // ace in hand
         }
         handval = handval + cardVal;
      }

      // check for a second ace      
      if ( ace == true  &&  handval + 10 <= 21 )
         handval = handval + 10;
      
      return handval;
      
   } 
   
} 