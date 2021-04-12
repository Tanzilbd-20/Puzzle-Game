package com.example.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winingPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};
    int activePlayer = 0;
    boolean gameActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameActive) {
            if(gameState[tappedCounter]==2){
            gameState[tappedCounter] = activePlayer;
            


            counter.setScaleY(0f);
            counter.setRotation(-360);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.circle);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }
            counter.animate().scaleYBy(1f).rotation(360).setDuration(300);

            for (int[] winingPosition : winingPositions) {
                if (gameState[winingPosition[0]] == gameState[winingPosition[1]] && gameState[winingPosition[1]] == gameState[winingPosition[2]]
                        && gameState[winingPosition[2]] != 2) {
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 0) {
                        winner = "Cross";
                    } else {
                        winner = "Circle";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerName = (TextView) findViewById(R.id.winnerName);
                    winnerName.setText(winner + " has own");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerName.setVisibility(View.VISIBLE);

                }else if(gameState[0] != 2
                        && gameState[1] != 2 && gameState[2] != 2 && gameState[3] != 2 && gameState[4] != 2
                        && gameState[5] != 2 && gameState[6] != 2 && gameState[7] != 2 && gameState[8] != 2) {
                    gameActive = false;
                    Button clearButton = (Button) findViewById(R.id.clearButton);
                    TextView lastmsg = (TextView) findViewById(R.id.lastMsg);
                    lastmsg.setVisibility(View.VISIBLE);
                    clearButton.setVisibility(View.VISIBLE);

                }
             }
            }else{
                Toast.makeText(this, "This box already used", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Game Over click on Play Again", Toast.LENGTH_SHORT).show();
        }
    }
    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerName = (TextView) findViewById(R.id.winnerName);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerName.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
        for(int i =0; i<gameState.length;i++){
            gameState[i] =2;
        }
        gameActive = true;
        activePlayer = 0;
    }
    public void clearButton(View view){
        Button clearButton = (Button) findViewById(R.id.clearButton);
        TextView lastmsg = (TextView) findViewById(R.id.lastMsg);
        lastmsg.setVisibility(View.INVISIBLE);
        clearButton.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView) gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
        for(int i =0; i<gameState.length;i++){
            gameState[i] =2;
        }
        gameActive = true;
        activePlayer = 0;


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}