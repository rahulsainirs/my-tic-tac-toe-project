package com.freecodecamp.tictactoe;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    ImageView counter;
    Button playAgain;
    GridLayout gridLayout;
    TextView winnerTv;
    TextView developer;
    // 1 for zero , 2 for cross , 0 for empty
    int[] gameState = {0, 0, 0,
            0, 0, 0,
            0, 0, 0};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 1;
    boolean gameActive = true;

    int gameStateCounter = 0;

    public void dropIn(View view) {

        counter = (ImageView) view;
        Log.i("Tag", counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        gameStateCounter++;

        if (gameState[tappedCounter] == 0 & gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.finalosymbol);
                activePlayer = 2;
            } else {
                counter.setImageResource(R.drawable.finalcross);
//                counter.setClickable(false); // it must be false after getting a click on gridViewChild to prevent from more than one clicks by user
                activePlayer = 1;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);
            counter.setClickable(false);  // Whenever user click on Grid-View child that grid-layout child i.e., image view(counter) will be no longer clickable   *it must be done to prevent from mistakenly extra clicks of user on respective Gridlayout items/child
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] & gameState[winningPosition[1]] == gameState[winningPosition[2]] & (gameState[winningPosition[0]] != 0 & gameState[winningPosition[1]] != 0 & gameState[winningPosition[2]] != 0)) {
                    // some-one won
                    String winner;
                    if (activePlayer == 2) {
                        winner = "Player:1 (0) You WIN!\nPlayer:2 (X) You LOOSE!";
                    } else {
                        winner = "Player:2 (X) You WIN!\nPlayer:1 (0) You LOOSE!";
                    }
                    playAgain = findViewById(R.id.btnPlayAgain);
                    winnerTv = findViewById(R.id.tvWinner);
                    winnerTv.setText(winner);
                    winnerTv.animate().rotationYBy(3600).setDuration(1500);
                    playAgain.setVisibility(View.VISIBLE);
                    playAgain.setAlpha(0.1f);
                    playAgain.setTranslationX(-300);
                    playAgain.animate().translationXBy(300).rotationYBy(360).alphaBy(1).setDuration(1500);
                    gameActive = false;
                    return;
                } else if ((gameState[winningPosition[0]] != gameState[winningPosition[1]] & gameState[winningPosition[1]] != gameState[winningPosition[2]])  /* & (gameState[winningPosition[0]] != 0 & gameState[winningPosition[1]] != 0 & gameState[winningPosition[2]] != 0) */ & (gameStateCounter > 8)) {
                    winnerTv = findViewById(R.id.tvWinner);
                    winnerTv.setText("***Drow!***");
                    winnerTv.animate().rotationYBy(360).setDuration(1500);
                    playAgain = findViewById(R.id.btnPlayAgain);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgainFunc(View view) {
        gameStateCounter = 0;
        playAgain = findViewById(R.id.btnPlayAgain);
        winnerTv = findViewById(R.id.tvWinner);
        winnerTv.setText("***Welcome to Tic Tac Toe***");
        winnerTv.animate().rotationYBy(360).setDuration(1500);
        playAgain.setVisibility(View.INVISIBLE);
        gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            counter = (ImageView) gridLayout.getChildAt(i);
            counter.setClickable(true);
            counter.setImageDrawable(null);
        }
        activePlayer = 1;
        gameActive = true;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 0;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        developer = findViewById(R.id.tvDeveloper);
        winnerTv = findViewById(R.id.tvWinner);
        winnerTv.animate().rotationYBy(360).setDuration(1500);
        developer.setTranslationY(-300);
        developer.animate().rotationYBy(3600).translationYBy(300).setDuration(2000);


    }
}