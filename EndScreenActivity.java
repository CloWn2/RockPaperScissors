package com.example.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);


        String winnerName;
        if(getIntent().getIntExtra("winner",0)==1){//Checking which player won
            winnerName=getIntent().getStringExtra("name1");
        }else{
            winnerName=getIntent().getStringExtra("name2");
        }
        TextView winnerView=findViewById(R.id.winnerView);
        winnerView.setText(winnerName);

        Button playAgain=findViewById(R.id.playAgainBtn);
        playAgain.setOnClickListener(new View.OnClickListener() {//Another game(with the same names)
            @Override
            public void onClick(View view) {
                String p1Name=getIntent().getStringExtra("name1");
                String p2Name=getIntent().getStringExtra("name2");
                Intent newGame=new Intent(EndScreenActivity.this,GameActivity.class);
                newGame.putExtra("name1",p1Name);
                newGame.putExtra("name2",p2Name);
                startActivity(newGame);
            }
        });

        Button changeNames=findViewById(R.id.changeNamesBtn);//New Game with different names.
        changeNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeScreen=new Intent(EndScreenActivity.this,MainActivity.class);
                startActivity(homeScreen);
            }
        });

    }
}
