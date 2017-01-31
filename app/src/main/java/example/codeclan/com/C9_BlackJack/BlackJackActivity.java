package example.codeclan.com.C9_BlackJack;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by Andy Guppy on 23/01/2017.
 */

public class BlackJackActivity extends AppCompatActivity {


    // If time permits refactor the Imageview into array to make code dry-er ;-)

    TextView playerText;
    TextView computerText;

    Button dealButton;
    Button stickButton;

    ArrayList<ImageView> playerCardList= new ArrayList<>();
    ArrayList<ImageView> androidCardList= new ArrayList<>();

    LinearLayout dealButtonLayout;
    LinearLayout twistnstickButtonLayout;
    LinearLayout newgameButtonLayout;

    int playerTotalCheck;
    int computerTotalCheck;

    Deck deck = new Deck();
    BlackJackHand playerhand = new BlackJackHand();
    BlackJackHand computerhand = new BlackJackHand();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerText = (TextView) findViewById(R.id.playerText);
        computerText = (TextView) findViewById(R.id.computerText);

        dealButton = (Button) findViewById(R.id.dealButton);


        // Layouts
        for(int i=1; i<9; i++){
            //players cards layouts
            Integer id = getResources().getIdentifier("playerCardView" + i, "id", getPackageName());
            playerCardList.add((ImageView) findViewById(id));


            //computer cards layouts
            id = getResources().getIdentifier("computerCardView" + i, "id", getPackageName());
            androidCardList.add((ImageView) findViewById(id));
        }

        dealButtonLayout = (LinearLayout) findViewById(R.id.buttonLayout01);
        twistnstickButtonLayout  = (LinearLayout) findViewById(R.id.buttonLayout02);
        newgameButtonLayout  = (LinearLayout) findViewById(R.id.buttonLayout03);

          dealButton.performClick();
    }

    public void onDealButtonClicked(View button) {

        // Hide Deal Button and reveal Stick n Twist
        dealButtonLayout.setVisibility(View.INVISIBLE);
        twistnstickButtonLayout.setVisibility(View.VISIBLE);

        //Log Deal Button has been Clicked
        Log.d(getClass().toString(), "onDealButtonClicked was called");

        // shuffle deck
        deck.shuffleDeck();

        //deal 2 cards to player hand
        playerhand.addCard(deck.dealCard());
        playerhand.addCard(deck.dealCard());

        //Display in Log and to user as toast
        Log.d(getClass().toString(), "Player 1 - " + playerhand.getCard(0).toString());
        Toast.makeText(getApplicationContext(), playerhand.getCard(0).toPrettyString(), Toast.LENGTH_SHORT).show();
        Log.d(getClass().toString(), "Player 2 - " + playerhand.getCard(1).toString());
        Toast.makeText(getApplicationContext(), playerhand.getCard(1).toPrettyString(), Toast.LENGTH_SHORT).show();

        //Display the players cards
        displayPlayerCards(playerhand.getCardCount());

        //Add Player score up and display
        playerText.setText("Player has :- " + (int) playerhand.getBlackjackValue());

        // deal 2 cards to computer hand ( only one visible to player )
        computerhand.addCard(deck.dealCard());
        computerhand.addCard(deck.dealCard());

        // Display Drawn cards in Log
        Log.d(getClass().toString(), "Computer 1 - " + computerhand.getCard(0).toString());
        Log.d(getClass().toString(), "Computer 2 - " + computerhand.getCard(1).toString());

        //Display the players cards
        displayAndroidCards(computerhand.getCardCount()-1);

    }


    public void onTwistButtonClicked(View button) {



        //Log Twist button has been clicked
        Log.d(getClass().toString(), "onTwistButtonClicked was called");

        // deal another card to player ( max 8 cards ) - Time permits will factor in Card 9
        playerhand.addCard(deck.dealCard());

        displayPlayerCards(playerhand.getCardCount());


        playerTotalCheck = playerhand.getBlackjackValue();
        // check total and display
        playerText.setText("Player has :- " + playerTotalCheck);
        if (playerTotalCheck > 21) {
            // Reveal Second computer card
            displayAndroidCards(computerhand.getCardCount());
            dealButtonLayout.setVisibility(View.INVISIBLE);
            twistnstickButtonLayout.setVisibility(View.INVISIBLE);
            newgameButtonLayout.setVisibility(View.VISIBLE);
            playerText.setText("Player has :- " + playerhand.getBlackjackValue() + " -- BUST");
            winner(false);
        } else if (playerTotalCheck == 21) {
            stickButton.performClick();
        }
    }


    public void onStickButtonClicked(View button) {
        Log.d(getClass().toString(), "onStickButtonClicked was called");

        dealButtonLayout.setVisibility(View.INVISIBLE);
        twistnstickButtonLayout.setVisibility(View.INVISIBLE);
        newgameButtonLayout.setVisibility(View.VISIBLE);

        // Reveal Second computer card
        displayAndroidCards(computerhand.getCardCount());


        //Add Computer score up and display
        computerText.setText("Player has :- " + computerhand.getBlackjackValue());

        // If Player winning - computer take another card
        computerTotalCheck = computerhand.getBlackjackValue();
        playerTotalCheck = playerhand.getBlackjackValue();


        while (computerTotalCheck < playerTotalCheck) {


            // Computer deals another card;
            computerhand.addCard(deck.dealCard());

            displayAndroidCards(computerhand.getCardCount()-1);


            // get Computer hand score
            computerTotalCheck = computerhand.getBlackjackValue();

            //display computer score
            computerText.setText("Computer has :- " + computerTotalCheck);
        }

        // who is the winner
        if (((computerTotalCheck >= playerTotalCheck) && (computerTotalCheck <= 21) ) || (playerTotalCheck > 21)) {
           winner(false);
        } else if (computerTotalCheck > 21) {
            winner(true);
        }

    }

    public void onResetButtonClicked(View button) {

        if (android.os.Build.VERSION.SDK_INT >= 11){
            //Code for recreate, newer than android revision 11
            //resets the game as if it was just started
            recreate();

        }else{
            //Code for Intent
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }



    private void winner(Boolean player){

        AlertDialog alertDialog = new AlertDialog.Builder(BlackJackActivity.this).create();
        alertDialog.setTitle("We Have a Winner !!");
        if (player){
            alertDialog.setMessage("Player wins with ..  " + playerhand.getBlackjackValue());}
        else {
            alertDialog.setMessage("Android beat you with " + computerhand.getBlackjackValue());
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        alertDialog.show();

    }

    private void displayPlayerCards(int numCards) {

        Drawable playerCardId;
        for (int i = 0; i < numCards; i++) {
            playerCardId = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(i).toString(), "drawable", getPackageName()), null);
            playerCardList.get(i).setImageDrawable(playerCardId);
            playerCardList.get(i).setVisibility(View.VISIBLE);
        }
        Toast.makeText(getApplicationContext(), playerhand.getCard(numCards-1).toPrettyString(), Toast.LENGTH_SHORT).show();
    }

    private void displayAndroidCards(int numCards) {

        Drawable androidCardId;
        for (int i = 0; i < numCards; i++) {
            androidCardId = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(i).toString(), "drawable", getPackageName()), null);
            androidCardList.get(i).setImageDrawable(androidCardId);
            androidCardList.get(i).setVisibility(View.VISIBLE);
        }

    }
}