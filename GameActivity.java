package com.example.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static final String NAME_ONE_KEY="name1";
    public static final String NAME_TWO_KEY="name2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ImageView p1IMG=findViewById(R.id.P1Img);// Player 1 imageView-both declared as rocks because I don't know how to create an empty imageView or something
        ImageView p2IMG=findViewById(R.id.P2Img);// Player 2 imageView
        p1IMG.setImageResource(0);
        p2IMG.setImageResource(0);

        String p1Name;
        String p2Name;
        if (getIntent().hasExtra("name1")) {
            p1Name = getIntent().getStringExtra(NAME_ONE_KEY);
            TextView p1NView = findViewById(R.id.p1);
            p1NView.setText(p1Name);
        }
        if (getIntent().hasExtra(NAME_TWO_KEY)) {
            p2Name = getIntent().getStringExtra("name2");
            TextView p2NView = findViewById(R.id.p2);
            p2NView.setText(p2Name);
        }

        Button NRound=findViewById(R.id.newRoundBtn);
        NRound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView score1=findViewById(R.id.score1View);// Player 1 score
                    TextView score2=findViewById(R.id.score2View);//Player 2 score
                    int p1Score = Integer.parseInt(score1.getText().toString());// Creating 2 scores variables so I can increment them
                    int p2Score = Integer.parseInt(score2.getText().toString());
                    int [] RPS = new int[]{1,2,3};//1-ROCK, 2-PAPER, 3-SCISSORS
                    Random r = new Random();
                    int p1Hand;//Stores rock/paper/scissors
                    int p2Hand;
                    p1Hand = RPS[r.nextInt(3)];
                    p2Hand = RPS[r.nextInt(3)];
                    showImages(p1Hand,p2Hand);//Showing the images according to the hand
                    String result=findWinner(p1Hand,p2Hand);
                    switch (result) {//Incrementing the score counter of the player who won.
                        case "player1":
                            p1Score++;
                            score1.setText(String.valueOf(p1Score));
                            break;
                        case "player2":
                            p2Score++;
                            score2.setText(String.valueOf(p2Score));
                            break;
                    }

                     if(score1.getText().toString().equals("3")|| score2.getText().toString().equals("3")){//Checking if one of the players had won 3 matches(won the game).
                         int winner;//what player had won the game
                         if(score2.getText().toString().equals("3")){//checking if its player 1 or player 2
                             winner=2;
                         }else {
                             winner=1;
                         }
                         Intent end=new Intent(GameActivity.this, EndScreenActivity.class);
                         TextView p1Name=findViewById(R.id.p1);
                         TextView p2Name=findViewById(R.id.p2);
                         end.putExtra(NAME_ONE_KEY,p1Name.getText().toString());//Passing both names in case playAgain button will be clicked(needs to pass the names to Game.Activity again).
                         end.putExtra(NAME_TWO_KEY,p2Name.getText().toString());
                         end.putExtra("winner",winner);//An int to identify which player won the game(player 1 or 2)
                         GameActivity.this.startActivity(end);
                     }

                }
            });

    }


    public void showImages(int hand1,int hand2){//Showing the currect pictures for the hands that was picked.
        ImageView p1IMG=findViewById(R.id.P1Img);
        ImageView p2IMG=findViewById(R.id.P2Img);
        switch (hand1){
            case 1:
                p1IMG.setImageResource(R.drawable.rock);
                break;
            case 2:
                p1IMG.setImageResource(R.drawable.paper);
                break;
            case 3:
                p1IMG.setImageResource(R.drawable.scissors);
                break;

        }
        switch (hand2){
            case 1:
                p2IMG.setImageResource(R.drawable.rock);
                break;
            case 2:
                p2IMG.setImageResource(R.drawable.paper);
                break;
            case 3:
                p2IMG.setImageResource(R.drawable.scissors);
                break;


        }




    }


    public static  String findWinner(int hand1,int hand2){//Finding the winner,returning a string of the player who won or draw if its a draw.
        if(hand1==hand2){
            return "draw";
        }
        if(hand1==hand2+1 || hand1==hand2-2){
            return "player1";
        }else{
            return "player2";
        }

    }
}
