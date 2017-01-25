package example.codeclan.com.C9_BlackJack;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

/**
 * Created by Andy Guppy on 23/01/2017.
 */

public class BlackJackActivity extends AppCompatActivity {


    // If time permits refactor the Imageview into array to make code dry-er ;-)

    TextView playerText;
    TextView computerText;

    Button dealButton;
    Button stickButton;

    ImageView playerCard1View;
    ImageView playerCard2View;
    ImageView playerCard3View;
    ImageView playerCard4View;
    ImageView playerCard5View;
    ImageView playerCard6View;
    ImageView playerCard7View;
    ImageView playerCard8View;
    ImageView computerCard1View;
    ImageView computerCard2View;
    ImageView computerCard3View;
    ImageView computerCard4View;
    ImageView computerCard5View;
    ImageView computerCard6View;
    ImageView computerCard7View;
    ImageView computerCard8View;

    TextView resultText;

    LinearLayout dealButtonLayout;
    LinearLayout twistnstickButtonLayout;
    LinearLayout newgameButtonLayout;

    int playerTotalCheck;
    int computerTotalCheck;

    Card playercard;
    Card computercard;

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
        // Players card layout
        playerCard1View = (ImageView) findViewById(R.id.playerCard1View);
        playerCard2View = (ImageView) findViewById(R.id.playerCard2View);
        playerCard3View = (ImageView) findViewById(R.id.playerCard3View);
        playerCard4View = (ImageView) findViewById(R.id.playerCard4View);
        playerCard5View = (ImageView) findViewById(R.id.playerCard5View);
        playerCard6View = (ImageView) findViewById(R.id.playerCard6View);
        // Computers card layout
        computerCard1View = (ImageView) findViewById(R.id.computerCard1View);
        computerCard2View = (ImageView) findViewById(R.id.computerCard2View);
        computerCard3View = (ImageView) findViewById(R.id.computerCard3View);
        computerCard4View = (ImageView) findViewById(R.id.computerCard4View);
        computerCard5View = (ImageView) findViewById(R.id.computerCard5View);
        computerCard6View = (ImageView) findViewById(R.id.computerCard6View);
        computerCard7View = (ImageView) findViewById(R.id.computerCard7View);
        computerCard8View = (ImageView) findViewById(R.id.computerCard8View);

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
        Toast.makeText(getApplicationContext(), playerhand.getCard(0).toPrettyString(), Toast.LENGTH_LONG).show();
        Log.d(getClass().toString(), "Player 2 - " + playerhand.getCard(1).toString());
        Toast.makeText(getApplicationContext(), playerhand.getCard(1).toPrettyString(), Toast.LENGTH_LONG).show();
        //Display the first player card
        Drawable playerCard1Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(0).toString(), "drawable", getPackageName()), null);
        playerCard1View.setImageDrawable(playerCard1Id);
        playerCard1View.setVisibility(View.VISIBLE);

        //Display the Second Player card
        Drawable playerCard2Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(1).toString(), "drawable", getPackageName()), null);
        playerCard2View.setImageDrawable(playerCard2Id);
        playerCard2View.setVisibility(View.VISIBLE);

        //Add Player score up and display
        playerText.setText("Player has :- " + playerhand.getBlackjackValue());

        // deal 2 cards to computer hand ( only one visible to player )
        computerhand.addCard(deck.dealCard());
        computerhand.addCard(deck.dealCard());

        // Display Drawn cards in Log
        Log.d(getClass().toString(), "Computer 1 - " + computerhand.getCard(0).toString());
        Log.d(getClass().toString(), "Computer 2 - " + computerhand.getCard(1).toString());

        //Display the first computer card
        Drawable computerCard1Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(0).toString(), "drawable", getPackageName()), null);
        computerCard1View.setImageDrawable(computerCard1Id);
        computerCard1View.setVisibility(View.VISIBLE);
        // Second Computer card in view but turn over so player cannot see

    }

    public void onTwistButtonClicked(View button) {



        //Log Twist button has been clicked
        Log.d(getClass().toString(), "onTwistButtonClicked was called");

        // deal another card to player ( max 8 cards ) - Time permits will factor in Card 9
        playerhand.addCard(deck.dealCard());

        //Display the next Player Card
        switch (playerhand.getCardCount()) {
            case 3:
                // 3rd Card in Hand
                Log.d(getClass().toString(), "Player 3 - " + playerhand.getCard(2).toString());
                Toast.makeText(getApplicationContext(), playerhand.getCard(2).toPrettyString(), Toast.LENGTH_LONG).show();
                playerCard3View.setVisibility(View.VISIBLE);
                Drawable playerCard3Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(2).toString(), "drawable", getPackageName()), null);
                playerCard3View.setImageDrawable(playerCard3Id);

                break;
            case 4:
                // 4th Card in Hand
                Log.d(getClass().toString(), "Player 4 - " + playerhand.getCard(3).toString());
                Toast.makeText(getApplicationContext(), playerhand.getCard(3).toPrettyString(), Toast.LENGTH_LONG).show();
                playerCard4View.setVisibility(View.VISIBLE);
                Drawable playerCard4Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(3).toString(), "drawable", getPackageName()), null);
                playerCard4View.setImageDrawable(playerCard4Id);

                break;

            case 5:
                // 5th Card in Hand
                Log.d(getClass().toString(), "Player 5 - " + playerhand.getCard(4).toString());
                Toast.makeText(getApplicationContext(), playerhand.getCard(4).toPrettyString(), Toast.LENGTH_LONG).show();
                playerCard5View.setVisibility(View.VISIBLE);
                Drawable playerCard5Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(4).toString(), "drawable", getPackageName()), null);
                playerCard5View.setImageDrawable(playerCard5Id);

                break;

            case 6:
                // 6th Card in Hand
                Log.d(getClass().toString(), "Player 6 - " + playerhand.getCard(5).toString());
                Toast.makeText(getApplicationContext(), playerhand.getCard(5).toPrettyString(), Toast.LENGTH_LONG).show();
                playerCard6View.setVisibility(View.VISIBLE);
                Drawable playerCard6Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(5).toString(), "drawable", getPackageName()), null);
                playerCard6View.setImageDrawable(playerCard6Id);

                break;

            case 7:
                // 7th Card in Hand
                Log.d(getClass().toString(), "Player 7 - " + playerhand.getCard(6).toString());
                Toast.makeText(getApplicationContext(), playerhand.getCard(6).toPrettyString(), Toast.LENGTH_LONG).show();
                playerCard7View.setVisibility(View.VISIBLE);
                Drawable playerCard7Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(6).toString(), "drawable", getPackageName()), null);
                playerCard7View.setImageDrawable(playerCard7Id);

                break;

            case 8:
                // 8th Card in Hand
                Log.d(getClass().toString(), "Player 8 - " + playerhand.getCard(7).toString());
                Toast.makeText(getApplicationContext(), playerhand.getCard(7).toPrettyString(), Toast.LENGTH_LONG).show();
                playerCard8View.setVisibility(View.VISIBLE);
                Drawable playerCard8Id = getResources().getDrawable(getResources().getIdentifier(playerhand.getCard(7).toString(), "drawable", getPackageName()), null);
                playerCard8View.setImageDrawable(playerCard8Id);

                break;

        }

        playerTotalCheck = playerhand.getBlackjackValue();
        // check total and display
        playerText.setText("Player has :- " + playerTotalCheck);
        if (playerTotalCheck > 21) {
            // Reveal Second computer card
            Drawable computerCard2Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(1).toString(), "drawable", getPackageName()), null);
            computerCard2View.setImageDrawable(computerCard2Id);
            computerCard2View.setVisibility(View.VISIBLE);
            dealButtonLayout.setVisibility(View.INVISIBLE);
            twistnstickButtonLayout.setVisibility(View.INVISIBLE);
            newgameButtonLayout.setVisibility(View.VISIBLE);
            computerWins();
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
        Drawable computerCard2Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(1).toString(), "drawable", getPackageName()), null);
        computerCard2View.setImageDrawable(computerCard2Id);
        computerCard2View.setVisibility(View.VISIBLE);

        //Add Computer score up and display
        computerText.setText("Player has :- " + computerhand.getBlackjackValue());

        // If Player winning - computer take another card
        computerTotalCheck = computerhand.getBlackjackValue();
        playerTotalCheck = playerhand.getBlackjackValue();


        while (computerTotalCheck < playerTotalCheck) {


            // Computer deals another card;
            computerhand.addCard(deck.dealCard());

            switch (computerhand.getCardCount()) {
                case 3:
                    // 3rd Card in Hand
                    Log.d(getClass().toString(), "computer 3 - " + computerhand.getCard(2).toString());
                    computerCard3View.setVisibility(View.VISIBLE);
                    Drawable computerCard3Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(2).toString(), "drawable", getPackageName()), null);
                    computerCard3View.setImageDrawable(computerCard3Id);

                    break;
                case 4:
                    // 4th Card in Hand
                    Log.d(getClass().toString(), "computer 4 - " + computerhand.getCard(3).toString());
                    computerCard4View.setVisibility(View.VISIBLE);
                    Drawable computerCard4Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(3).toString(), "drawable", getPackageName()), null);
                    computerCard4View.setImageDrawable(computerCard4Id);

                    break;

                case 5:
                    // 5th Card in Hand
                    Log.d(getClass().toString(), "computer 5 - " + computerhand.getCard(4).toString());
                    computerCard5View.setVisibility(View.VISIBLE);
                    Drawable computerCard5Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(4).toString(), "drawable", getPackageName()), null);
                    computerCard5View.setImageDrawable(computerCard5Id);

                    break;

                case 6:
                    // 6th Card in Hand
                    Log.d(getClass().toString(), "computer 6 - " + computerhand.getCard(5).toString());
                    computerCard6View.setVisibility(View.VISIBLE);
                    Drawable computerCard6Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(5).toString(), "drawable", getPackageName()), null);
                    computerCard6View.setImageDrawable(computerCard6Id);

                    break;

                case 7:
                    // 7th Card in Hand
                    Log.d(getClass().toString(), "computer 7 - " + computerhand.getCard(6).toString());
                    computerCard7View.setVisibility(View.VISIBLE);
                    Drawable computerCard7Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(6).toString(), "drawable", getPackageName()), null);
                    computerCard7View.setImageDrawable(computerCard7Id);

                    break;

                case 8:
                    // 8th Card in Hand
                    Log.d(getClass().toString(), "computer 8 - " + computerhand.getCard(7).toString());
                    computerCard8View.setVisibility(View.VISIBLE);
                    Drawable computerCard8Id = getResources().getDrawable(getResources().getIdentifier(computerhand.getCard(7).toString(), "drawable", getPackageName()), null);
                    computerCard8View.setImageDrawable(computerCard8Id);

                    break;

            }


            // get Computer hand score
            computerTotalCheck = computerhand.getBlackjackValue();

            //display computer score
            computerText.setText("Cmputer has :- " + computerTotalCheck);
        }

        // who is the winner
        if (((computerTotalCheck >= playerTotalCheck) && (computerTotalCheck <= 21) ) || (playerTotalCheck > 21)) {
           computerWins();

        } else if (computerTotalCheck > 21) {
            playerWins();
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

    public void playerWins(){

        AlertDialog alertDialog = new AlertDialog.Builder(BlackJackActivity.this).create();
        alertDialog.setTitle("We Have a Winner !!");
        alertDialog.setMessage("Player wins with " + playerhand.getBlackjackValue());
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }
    public void computerWins(){

        AlertDialog alertDialog = new AlertDialog.Builder(BlackJackActivity.this).create();
        alertDialog.setTitle("We Have a Winner !!");
        alertDialog.setMessage("Android beat you with " + computerhand.getBlackjackValue());
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

}