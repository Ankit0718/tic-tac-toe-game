package com.example.crossandtickgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // 0:tick, 1:cross , 2: empty
    int active = 0;
    int[] gameState= {2,2,2,2,2,2,2,2,2};
    int[][] winningState = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = active;
            counter.setTranslationY(-1500);

            if (active == 0) {
                counter.setImageResource(R.drawable.tick);
                active = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                active = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winningState : winningState) {
                if (gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] != 2) {
                    // someone has won!
                    gameActive = false;
                    String winner = "";
                    if (active == 1) {
                        winner = "tick";
                    } else {
                        winner = "cross";
                    }
                    Button playButton = (Button) findViewById(R.id.playButton);
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText(winner + " has won!");
                    playButton.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }

            }
        }
    }

        public void playAgain(View view){
            Button playButton = (Button) findViewById(R.id.playButton);
            TextView textView = (TextView) findViewById(R.id.textView);
            playButton.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);

            }
            Arrays.fill(gameState, 2);
            active = 0;
            gameActive = true;
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
