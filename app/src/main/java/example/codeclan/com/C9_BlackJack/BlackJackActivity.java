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


//    ImageView playerCardView1;
//    ImageView playerCardView2;
//    ImageView playerCardView3;
//    ImageView playerCardView4;
//    ImageView playerCardView5;
//    ImageView playerCardView6;
//    ImageView playerCardView7;
//    ImageView playerCardView8;
//    ImageView computerCardView1;
//    ImageView computerCardView2;
//    ImageView computerCardView3;
//    ImageView computerCardView4;
//    ImageView computerCardView5;
//    ImageView computerCardView6;
//    ImageView computerCardView7;
//    ImageView computerCardView8;

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

//       //  Players card layout
//        playerCardView1 = (ImageView) findViewById(R.id.playerCardView1);
//        playerCardView2 = (ImageView) findViewById(R.id.playerCardView2);
//        playerCardView3 = (ImageView) findViewById(R.id.playerCardView3);
//        playerCardView4 = (ImageView) findViewById(R.id.playerCardView4);
//        playerCardView5 = (ImageView) findViewById(R.id.playerCardView5);
//        playerCardView6 = (ImageView) findViewById(R.id.playerCardView6);
//
//
//        // Computers card layout
//        computerCardView1 = (ImageView) findViewById(R.id.computerCardView1);
//        computerCardView2 = (ImageView) findViewById(R.id.computerCardView2);
//        computerCardView3 = (ImageView) findViewById(R.id.computerCardView3);
//        computerCardView4 = (ImageView) findViewById(R.id.computerCardView4);
//        computerCardView5 = (ImageView) findViewById(R.id.computerCardView5);
//        computerCardView6 = (ImageView) findViewById(R.id.computerCardView6);
//        computerCardView7 = (ImageView) findViewById(R.id.computerCardView7);
//        computerCardView8 = (ImageView) findViewById(R.id.computerCardView8);

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

        //Display the first player card
//        Drawable playerCardId = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(0).toString(), "drawable", getPackageName()), null);
//        playerCardList.get(0).setImageDrawable(playerCardId);
//        playerCardList.get(0).setVisibility(View.VISIBLE);


        //Display the Second Player card
//        playerCardId = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(1).toString(), "drawable", getPackageName()), null);
//        playerCardList.get(1).setImageDrawable(playerCardId);
//        playerCardList.get(1).setVisibility(View.VISIBLE);

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

        //Display the first computer card
//        Drawable computerCard1Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(0).toString(), "drawable", getPackageName()), null);
//        computerCardView1.setImageDrawable(computerCard1Id);
//        computerCardView1.setVisibility(View.VISIBLE);
        // Second Computer card in view but turn over so player cannot see

    }


    public void onTwistButtonClicked(View button) {



        //Log Twist button has been clicked
        Log.d(getClass().toString(), "onTwistButtonClicked was called");

        // deal another card to player ( max 8 cards ) - Time permits will factor in Card 9
        playerhand.addCard(deck.dealCard());

        displayPlayerCards(playerhand.getCardCount());

//        //we need to dislay all players cards
//        int numPlayCards = playerhand.getCardCount();
//
//
//        //Display the next Player Card
//        switch (playerhand.getCardCount()) {
//            case 3:
//                // 3rd Card in Hand
//                Log.d(getClass().toString(), "Player 3 - " + playerhand.getCard(2).toString());
//                Toast.makeText(getApplicationContext(), playerhand.getCard(2).toPrettyString(), Toast.LENGTH_SHORT).show();
//                playerCardView3.setVisibility(View.VISIBLE);
//                Drawable playerCard3Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(2).toString(), "drawable", getPackageName()), null);
//                playerCardView3.setImageDrawable(playerCard3Id);
//
//                break;
//            case 4:
//                // 4th Card in Hand
//                Log.d(getClass().toString(), "Player 4 - " + playerhand.getCard(3).toString());
//                Toast.makeText(getApplicationContext(), playerhand.getCard(3).toPrettyString(), Toast.LENGTH_SHORT).show();
//                playerCardView4.setVisibility(View.VISIBLE);
//                Drawable playerCard4Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(3).toString(), "drawable", getPackageName()), null);
//                playerCardView4.setImageDrawable(playerCard4Id);
//
//                break;
//
//            case 5:
//                // 5th Card in Hand
//                Log.d(getClass().toString(), "Player 5 - " + playerhand.getCard(4).toString());
//                Toast.makeText(getApplicationContext(), playerhand.getCard(4).toPrettyString(), Toast.LENGTH_SHORT).show();
//                playerCardView5.setVisibility(View.VISIBLE);
//                Drawable playerCard5Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(4).toString(), "drawable", getPackageName()), null);
//                playerCardView5.setImageDrawable(playerCard5Id);
//
//                break;
//
//            case 6:
//                // 6th Card in Hand
//                Log.d(getClass().toString(), "Player 6 - " + playerhand.getCard(5).toString());
//                Toast.makeText(getApplicationContext(), playerhand.getCard(5).toPrettyString(), Toast.LENGTH_SHORT).show();
//                playerCardView6.setVisibility(View.VISIBLE);
//                Drawable playerCard6Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(5).toString(), "drawable", getPackageName()), null);
//                playerCardView6.setImageDrawable(playerCard6Id);
//
//                break;
//
//            case 7:
//                // 7th Card in Hand
//                Log.d(getClass().toString(), "Player 7 - " + playerhand.getCard(6).toString());
//                Toast.makeText(getApplicationContext(), playerhand.getCard(6).toPrettyString(), Toast.LENGTH_SHORT).show();
//                playerCardView7.setVisibility(View.VISIBLE);
//                Drawable playerCard7Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(6).toString(), "drawable", getPackageName()), null);
//                playerCardView7.setImageDrawable(playerCard7Id);
//
//                break;
//
//            case 8:
//                // 8th Card in Hand
//                Log.d(getClass().toString(), "Player 8 - " + playerhand.getCard(7).toString());
//                Toast.makeText(getApplicationContext(), playerhand.getCard(7).toPrettyString(), Toast.LENGTH_SHORT).show();
//                playerCardView8.setVisibility(View.VISIBLE);
//                Drawable playerCard8Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(7).toString(), "drawable", getPackageName()), null);
//                playerCardView8.setImageDrawable(playerCard8Id);
//
//                break;
//
//        }

        playerTotalCheck = playerhand.getBlackjackValue();
        // check total and display
        playerText.setText("Player has :- " + playerTotalCheck);
        if (playerTotalCheck > 21) {
            // Reveal Second computer card
            displayAndroidCards(computerhand.getCardCount());
//            Drawable computerCard2Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(1).toString(), "drawable", getPackageName()), null);
//            computerCardView2.setImageDrawable(computerCard2Id);
//            computerCardView2.setVisibility(View.VISIBLE);
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


//        Drawable computerCard2Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(1).toString(), "drawable", getPackageName()), null);
//        computerCardView2.setImageDrawable(computerCard2Id);
//        computerCardView2.setVisibility(View.VISIBLE);

        //Add Computer score up and display
        computerText.setText("Player has :- " + computerhand.getBlackjackValue());

        // If Player winning - computer take another card
        computerTotalCheck = computerhand.getBlackjackValue();
        playerTotalCheck = playerhand.getBlackjackValue();


        while (computerTotalCheck < playerTotalCheck) {


            // Computer deals another card;
            computerhand.addCard(deck.dealCard());

            displayAndroidCards(computerhand.getCardCount()-1);

//            switch (computerhand.getCardCount()) {
//                case 3:
//                    // 3rd Card in Hand
//                    Log.d(getClass().toString(), "computer 3 - " + computerhand.getCard(2).toString());
//                    computerCardView3.setVisibility(View.VISIBLE);
//                    Drawable computerCard3Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(2).toString(), "drawable", getPackageName()), null);
//                    computerCardView3.setImageDrawable(computerCard3Id);
//
//                    break;
//                case 4:
//                    // 4th Card in Hand
//                    Log.d(getClass().toString(), "computer 4 - " + computerhand.getCard(3).toString());
//                    computerCardView4.setVisibility(View.VISIBLE);
//                    Drawable computerCard4Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(3).toString(), "drawable", getPackageName()), null);
//                    computerCardView4.setImageDrawable(computerCard4Id);
//
//                    break;
//
//                case 5:
//                    // 5th Card in Hand
//                    Log.d(getClass().toString(), "computer 5 - " + computerhand.getCard(4).toString());
//                    computerCardView5.setVisibility(View.VISIBLE);
//                    Drawable computerCard5Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(4).toString(), "drawable", getPackageName()), null);
//                    computerCardView5.setImageDrawable(computerCard5Id);
//
//                    break;
//
//                case 6:
//                    // 6th Card in Hand
//                    Log.d(getClass().toString(), "computer 6 - " + computerhand.getCard(5).toString());
//                    computerCardView6.setVisibility(View.VISIBLE);
//                    Drawable computerCard6Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(5).toString(), "drawable", getPackageName()), null);
//                    computerCardView6.setImageDrawable(computerCard6Id);
//
//                    break;
//
//                case 7:
//                    // 7th Card in Hand
//                    Log.d(getClass().toString(), "computer 7 - " + computerhand.getCard(6).toString());
//                    computerCardView7.setVisibility(View.VISIBLE);
//                    Drawable computerCard7Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(6).toString(), "drawable", getPackageName()), null);
//                    computerCardView7.setImageDrawable(computerCard7Id);
//
//                    break;
//
//                case 8:
//                    // 8th Card in Hand
//                    Log.d(getClass().toString(), "computer 8 - " + computerhand.getCard(7).toString());
//                    computerCardView8.setVisibility(View.VISIBLE);
//                    Drawable computerCard8Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(7).toString(), "drawable", getPackageName()), null);
//                    computerCardView8.setImageDrawable(computerCard8Id);
//
//                    break;
//
//            }


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

//    public void playerWins(){
//
//        AlertDialog alertDialog = new AlertDialog.Builder(BlackJackActivity.this).create();
//        alertDialog.setTitle("We Have a Winner !!");
//        alertDialog.setMessage("Player wins with " + playerhand.getBlackjackValue());
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        alertDialog.show();
//
//    }
//    public void computerWins(){
//
//        AlertDialog alertDialog = new AlertDialog.Builder(BlackJackActivity.this).create();
//        alertDialog.setTitle("We Have a Winner !!");
//        alertDialog.setMessage("Android beat you with " + computerhand.getBlackjackValue());
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//        alertDialog.show();
//
//    }

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