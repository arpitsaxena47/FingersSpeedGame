package com.arpit.fingersspeedgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtTapped;
    TextView txtTimer;
    Button btnTap;
    Button btnStart;
    private  int tappedTime = 500;
    private int timer =60;
    private CountDownTimer countDownTimer;
    private long initialCountDownInMillies= 60000;
    private int timerInterval = 1000;
    private int remainingTime = 60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTapped = findViewById(R.id.txtTapped);
        txtTimer = findViewById(R.id.txtTimer);

        btnTap = findViewById(R.id.btnTap);
        btnStart = findViewById(R.id.btnStartTimer);

        txtTapped.setText(tappedTime+"");

        txtTimer.setText(timer+"");

        btnTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerDecrement();
                isWinner();
            }
        });


         countDownTimer = new CountDownTimer(initialCountDownInMillies,timerInterval) {
             @Override
             public void onTick(long millisUntilFinished) {

                 remainingTime = (int) millisUntilFinished/1000;
                 txtTimer.setText(remainingTime+"");
             }

             @Override
             public void onFinish() {
                 AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                 builder1.setTitle(R.string.alertLoseTitle);
                 builder1.setMessage(R.string.alertMessageTitle);
                 builder1.setCancelable(false);

                 builder1.setPositiveButton(
                         "Play Again",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 resetTheGame();
                             }
                         });

                 builder1.setNegativeButton(
                         "No , Exit The Game",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 finish();
                                 System.exit(0);
                             }
                         });

                 AlertDialog alert11 = builder1.create();
                 alert11.show();

             }
         };

         btnStart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 countDownTimer.start();
             }
         });


    }

    private void isWinner() {
        if(remainingTime >=0 && tappedTime <=0)
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setTitle(R.string.alertWinTitle);
            builder1.setMessage(R.string.alertMessageTitle);
            builder1.setCancelable(false);

            builder1.setPositiveButton(
                    R.string.setPositiveButtonTitle,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                           resetTheGame();
                        }
                    });

            builder1.setNegativeButton(
                    R.string.setNegativeButtonTitle,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            System.exit(0);
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    private void resetTheGame() {


    }

    private void timerDecrement() {
        tappedTime--;
        txtTapped.setText(tappedTime+"");
    }
}
