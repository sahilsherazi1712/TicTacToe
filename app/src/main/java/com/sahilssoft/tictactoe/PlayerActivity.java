package com.sahilssoft.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlayerActivity extends AppCompatActivity {

    EditText playerOne, playerTwo;
    Button startGameBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playerOne = findViewById(R.id.playerOne);
        playerTwo = findViewById(R.id.playerTwo);
        startGameBtn = findViewById(R.id.startGameBtn);

        startGameBtn.setOnClickListener(view -> {
            String fName = playerOne.getText().toString();
            String sName = playerTwo.getText().toString();

            if (fName.isEmpty() || sName.isEmpty()){
                Toast.makeText(this, "Please Enter Player Names", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(PlayerActivity.this, MainActivity.class);
                intent.putExtra("playerOne", fName);
                intent.putExtra("playerTwo",sName);
                startActivity(intent);
            }
        });
    }
}