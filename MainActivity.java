package com.example.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String NAME_ONE_KEY="name1";//Was a recommendation from a StackOverFlow user, I probably didn't do it right though.
    public static final String NAME_TWO_KEY="name2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn;
        startBtn = findViewById(R.id.startBtn);


            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    EditText p2Name=findViewById(R.id.p2EditText);//player 1 name
                    EditText p1Name=findViewById(R.id.p1EditText);//player 2 name
                    String name1=p2Name.getText().toString();
                    String name2=p1Name.getText().toString();
                    Intent intent1 = new Intent(MainActivity.this, GameActivity.class);
                    intent1.putExtra(NAME_ONE_KEY,name1);
                    intent1.putExtra(NAME_TWO_KEY,name2);
                    MainActivity.this.startActivity(intent1);
                }

        });


    }
}
