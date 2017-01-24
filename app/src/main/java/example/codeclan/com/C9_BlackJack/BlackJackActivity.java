package example.codeclan.com.C9_BlackJack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

/**
 * Created by Andy Guppy on 23/01/2017.
 */

public class BlackJackActivity extends AppCompatActivity {

    TextView playerText;
    TextView computerText;

    Button dealButton;

    ImageView playerCard1View;
    ImageView playerCard2View;
    ImageView playerCard3View;
    ImageView playerCard4View;
    ImageView computerCard1View;
    ImageView computerCard2View;
    ImageView computerCard3View;
    ImageView computerCard4View;
    TextView resultText;

    Card playercard ;
    Card computercard;

    Deck deck = new Deck();
    BlackJackHand playerhand = new BlackJackHand();
    BlackJackHand computerhand = new BlackJackHand();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerText = (TextView)findViewById(R.id.playerText);
        computerText = (TextView)findViewById(R.id.computerText);

        dealButton = (Button)findViewById(R.id.dealButton);
        // Players card layout
        playerCard1View =(ImageView)findViewById(R.id.playerCard1View);
        playerCard2View =(ImageView)findViewById(R.id.playerCard2View);
        playerCard3View =(ImageView)findViewById(R.id.playerCard3View);
        playerCard4View =(ImageView)findViewById(R.id.playerCard4View);
        // Computers card layout
        computerCard1View =(ImageView)findViewById(R.id.computerCard1View);
        computerCard2View =(ImageView)findViewById(R.id.computerCard2View);
        computerCard3View =(ImageView)findViewById(R.id.computerCard3View);
        computerCard4View =(ImageView)findViewById(R.id.computerCard4View);
        // visible messages to player
        resultText = (TextView)findViewById(R.id.resultText);

    }

    public void onDealButtonClicked(View button) {
        Log.d(getClass().toString(), "onDealButtonClicked was called");
        // shuffle deck
        deck.shuffleDeck();
        //deal 2 cards to player hand
        playerhand.addCard(deck.dealCard());
        playerhand.addCard(deck.dealCard());
        Log.d(getClass().toString(), "Player 1 - " + playerhand.getCard(0).toString());
        Log.d(getClass().toString(), "Player 2 - " + playerhand.getCard(1).toString());
        int playerCard1Id = getResources().getIdentifier(playerhand.getCard(0).toString(),"drawable","example.codeclan.com.C9_BlackJack");
//        int playerCard1Id = getResources().getIdentifier("example.codeclan.com.C9_BlackJack:drawable/" + playerhand.getCard(0).toString(), null, null);

        Log.d(getClass().toString(), "Card1 ID -- " + playerCard1Id);
        playerCard1View.setImageResource(playerCard1Id);
        playerCard1View.setVisibility(View.VISIBLE);
        int playerCard2Id = getResources().getIdentifier("example.codeclan.com.C9_BlackJack:drawable/" + playerhand.getCard(1).toString(), null, null);
        Log.d(getClass().toString(), "Card1 ID 2 -- " + playerCard2Id);
        playerCard2View.setImageResource(playerCard2Id);
        playerCard2View.setVisibility(View.VISIBLE);
        playerCard3View.setVisibility(View.INVISIBLE);
        playerCard4View.setVisibility(View.INVISIBLE);
        playerText.setText("Player has :- " + playerhand.getBlackjackValue());


        // deal 2 cards to computer hand ( only one visible to player )
        computerhand.addCard(deck.dealCard());
        computerhand.addCard(deck.dealCard());
        Log.d(getClass().toString(), "Computer 1 - " + computerhand.getCard(0).toString());
        Log.d(getClass().toString(), "Computer 2 - " + computerhand.getCard(1).toString());
        int computerCard1Id = getResources().getIdentifier("example.codeclan.com.C9_BlackJack:drawable/" + computerhand.getCard(0).toString(), null, null);
        computerCard1View.setImageResource(computerCard1Id);
        computerCard1View.setVisibility(View.VISIBLE);
        computerCard3View.setVisibility(View.INVISIBLE);
        computerCard4View.setVisibility(View.INVISIBLE);
        // check total and display

        // Message to player asking to stick or twist

    }

    public void onTwistButtonClicked(View button) {
        Log.d(getClass().toString(), "onTwistButtonClicked was called");

        // deal another card to player ( max 4 cards -- Guppy's Rules )
        playerhand.addCard(deck.dealCard());
        switch (playerhand.getCardCount()) {
            case 3:
                Log.d(getClass().toString(), "Player 3 - " + playerhand.getCard(2).toString());
                playerCard3View.setVisibility(View.VISIBLE);
                int playerCard3Id = getResources().getIdentifier("example.codeclan.com.C9_BlackJack:drawable/" + playerhand.getCard(2).toString(), null, null);
                playerCard3View.setImageResource(playerCard3Id);
                playerText.setText("Player has :- " + playerhand.getBlackjackValue());
                break;
            case 4:
                Log.d(getClass().toString(), "Player 3 - " + playerhand.getCard(3).toString());
                playerCard4View.setVisibility(View.VISIBLE);
                int playerCard4Id = getResources().getIdentifier("example.codeclan.com.C9_BlackJack:drawable/" + playerhand.getCard(3).toString(), null, null);
                playerCard4View.setImageResource(playerCard4Id);
                playerText.setText("Player has :- " + playerhand.getBlackjackValue());
                // check total and display
                break;
        }

    }

    public void onStickButtonClicked(View button) {
        Log.d(getClass().toString(), "onStickButtonClicked was called");

        // Reveal Second computer card

        // Check if computer has won and display total

        // If Player winning - computer take another card

        // else computer wins

        // Display winner


    }
//    public void onDealButtonClicked(View button) {
//        Log.d(getClass().toString(), "onDealButtonClicked was called");
//
//        deck.shuffleDeck();
//
//        playercard = deck.dealCard();
//        int playerCardId = getResources().getIdentifier("example.codeclan.com.C9_BlackJack:drawable/" + playercard.toString(), null, null);
//        playerCardView.setImageResource(playerCardId);
//
//        computercard = deck.dealCard();
//        int computerCardId = getResources().getIdentifier("example.codeclan.com.C9_BlackJack:drawable/" + computercard.toString(), null, null);
//        playerCardView.setImageResource(computerCardId);
//
//        deck.addCard(playercard);
//        deck.addCard(computercard);
//
//        if(playercard.getCardValue() > computercard.getCardValue()){
//            resultText.setText("Player Wins");
//        }else if(computercard.getCardValue() > playercard.getCardValue()){
//            resultText.setText("Computer Wins");
//        }else{
//            resultText.setText("DRAW");
//        }
//
//
//    }
}
