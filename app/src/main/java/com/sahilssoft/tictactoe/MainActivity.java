package com.sahilssoft.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout playerOneLayout, playerTwoLayout;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;

    List<int[]> combinationList;
    int[] boxPositions;
    int totalSelectedBoxes;
    int playerTurn;
    TextView playerOneName, playerTwoName;

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    String b1, b2, b3, b4, b5, b6, b7, b8, b9;
    int flag = 0;
    int count = 0;
    String playerOne, playerTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneLayout = findViewById(R.id.playerOneLayout);
        playerTwoLayout = findViewById(R.id.playerTwoLayout);
        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);

        combinationList = new ArrayList<>();
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        playerTurn = 1;
        totalSelectedBoxes = 1;

        combinationList.add(new int[]{0,1,2});
        combinationList.add(new int[]{3,4,5});
        combinationList.add(new int[]{6,7,8});
        combinationList.add(new int[]{0,3,6});
        combinationList.add(new int[]{1,4,7});
        combinationList.add(new int[]{2,5,8});
        combinationList.add(new int[]{2,4,6});
        combinationList.add(new int[]{0,4,8});


//        btn1 = findViewById(R.id.btn1);
//        btn2 = findViewById(R.id.btn2);
//        btn3 = findViewById(R.id.btn3);
//        btn4 = findViewById(R.id.btn4);
//        btn5 = findViewById(R.id.btn5);
//        btn6 = findViewById(R.id.btn6);
//        btn7 = findViewById(R.id.btn7);
//        btn8 = findViewById(R.id.btn8);
//        btn9 = findViewById(R.id.btn9);

        playerOne = getIntent().getStringExtra("playerOne");
        playerTwo = getIntent().getStringExtra("playerTwo");
        playerOneName.setText(playerOne);
        playerTwoName.setText(playerTwo);

        img1.setOnClickListener(view -> {
            if (isBoxSelected(0)){
                performAction((ImageView)view, 0);
            }
        });
        img2.setOnClickListener(view -> {
            if (isBoxSelected(1)){
                performAction((ImageView)view, 1);
            }
        });
        img3.setOnClickListener(view -> {
            if (isBoxSelected(2)){
                performAction((ImageView)view, 2);
            }
        });
        img4.setOnClickListener(view -> {
            if (isBoxSelected(3)){
                performAction((ImageView)view, 3);
            }
        });
        img5.setOnClickListener(view -> {
            if (isBoxSelected(4)){
                performAction((ImageView)view, 4);
            }
        });
        img6.setOnClickListener(view -> {
            if (isBoxSelected(5)){
                performAction((ImageView)view, 5);
            }
        });
        img7.setOnClickListener(view -> {
            if (isBoxSelected(6)){
                performAction((ImageView)view, 6);
            }
        });
        img8.setOnClickListener(view -> {
            if (isBoxSelected(7)){
                performAction((ImageView)view, 7);
            }
        });
        img9.setOnClickListener(view -> {
            if (isBoxSelected(8)){
                performAction((ImageView)view, 8);
            }
        });
    }

    private boolean isBoxSelected(int boxPosition){
        if (boxPositions[boxPosition] == 0){
            return true;
        }
        return false;
    }

    private void performAction(ImageView imageView, int selectedPosition){

        boxPositions[selectedPosition] = playerTurn;

        if (playerTurn == 1){
            imageView.setImageResource(R.drawable.cross);
            if (checkPlayerWin()){
                WinDialog winDialog = new WinDialog(MainActivity.this,playerOneName.getText().toString()+" has won the match");
                winDialog.setCancelable(false);
                winDialog.show();
                restartMatch();
            }else if (totalSelectedBoxes == 9){
                WinDialog winDialog = new WinDialog(MainActivity.this,"It is draw!");
                winDialog.setCancelable(false);
                winDialog.show();
                restartMatch();
            }else{
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        }else{
            imageView.setImageResource(R.drawable.zero);
            if (checkPlayerWin()){
                WinDialog winDialog = new WinDialog(MainActivity.this,playerTwoName.getText().toString()+" has won the match");
                winDialog.setCancelable(false);
                winDialog.show();
                restartMatch();
            }else if (totalSelectedBoxes == 9){
                WinDialog winDialog = new WinDialog(MainActivity.this,"It is draw!");
                winDialog.setCancelable(false);
                winDialog.show();
                restartMatch();
            }else{
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1){
            playerOneLayout.setBackgroundResource(R.drawable.round_white_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_dark_blue_bg);
        }else{
            playerTwoLayout.setBackgroundResource(R.drawable.round_white_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_dark_blue_bg);
        }
    }

    private boolean checkPlayerWin(){
        for (int i=0;i<combinationList.size();i++){
            final int[] combination = combinationList.get(i);
            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn && boxPositions[combination[2]] == playerTurn){
                return true;
            }
        }
        return false;
    }

    private void restartMatch(){
        boxPositions = new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBoxes = 1;

        img1.setImageResource(R.drawable.blank);
        img2.setImageResource(R.drawable.blank);
        img3.setImageResource(R.drawable.blank);
        img4.setImageResource(R.drawable.blank);
        img5.setImageResource(R.drawable.blank);
        img6.setImageResource(R.drawable.blank);
        img7.setImageResource(R.drawable.blank);
        img8.setImageResource(R.drawable.blank);
        img9.setImageResource(R.drawable.blank);
    }
//    public void Check(View view){
//        Button btnCurrent = (Button) view;
//        count++;
//
//        if (btnCurrent.getText().toString().equals("")){
//
//            if (flag == 0){
//                btnCurrent.setText("x");
//                flag = 1;
//            }else{
//                btnCurrent.setText("o");
//                flag = 0;
//            }
//
//            if (count>4){
//                b1 = btn1.getText().toString();
//                b2 = btn2.getText().toString();
//                b3 = btn3.getText().toString();
//                b4 = btn4.getText().toString();
//                b5 = btn5.getText().toString();
//                b6 = btn6.getText().toString();
//                b7 = btn7.getText().toString();
//                b8 = btn8.getText().toString();
//                b9 = btn9.getText().toString();
//
//                //Conditions;
//                if (b1.equals(b2) && b2.equals(b3) && !b1.equals("")){
//                    //1
////                    Toast.makeText(this, "Winner is:"+b1, Toast.LENGTH_SHORT).show();
//                    customToast(b1);
//                    newGame();
//                }else if (b4.equals(b5) && b5.equals(b6) && !b4.equals("")){
//                    //2
//                    customToast(b4);
//                    newGame();
//                }else if (b7.equals(b8) && b8.equals(b9) && !b7.equals("")){
//                    //3
//                    customToast(b7);
//                    newGame();
//                }else if (b1.equals(b4) && b4.equals(b7) && !b1.equals("")){
//                    //4
//                    customToast(b1);
//                    newGame();
//                }else if (b2.equals(b5) && b5.equals(b8) && !b2.equals("")){
//                    //5
//                    customToast(b2);
//                    newGame();
//                }else if (b3.equals(b6) && b6.equals(b9) && !b3.equals("")){
//                    //6
//                    customToast(b3);
//                    newGame();
//                }else if (b1.equals(b5) && b5.equals(b9) && !b1.equals("")){
//                    //7
//                    customToast(b1);
//                    newGame();
//                }else if (b3.equals(b5) && b5.equals(b7) && !b3.equals("")){
//                    //8
//                    customToast(b3);
//                    newGame();
//                }else if (!b1.equals("") && !b2.equals("") && !b3.equals("") &&
//                        !b4.equals("") && !b5.equals("") && !b6.equals("") &&
//                        !b7.equals("") && !b8.equals("") && !b9.equals("")){ //count == 9
//                    Toast.makeText(this, "Game is Drawn", Toast.LENGTH_SHORT).show();
//                    newGame();
//                }
//            }
//        }
//
//    }
//
//    private void newGame() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                btn1.setText("");
//                btn2.setText("");
//                btn3.setText("");
//                btn4.setText("");
//                btn5.setText("");
//                btn6.setText("");
//                btn7.setText("");
//                btn8.setText("");
//                btn9.setText("");
//                flag = 0;
//                count=0;
//            }
//        },5000);
//    }
//
//    private void customToast(String btn){
//        Toast toast = new Toast(this);
//        View view = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.llToast));
//        toast.setView(view);
//        TextView txtMsg = view.findViewById(R.id.tvMsg);
//        txtMsg.setText("Winner is: "+btn);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setGravity(Gravity.BOTTOM,0,0);
//        toast.show();
//    }

}