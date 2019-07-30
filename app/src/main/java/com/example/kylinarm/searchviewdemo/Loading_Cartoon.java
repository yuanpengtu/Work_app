package com.example.kylinarm.searchviewdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Loading_Cartoon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading__cartoon);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent main=new Intent(Loading_Cartoon.this,MainActivity.class);
                main.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(main);
                overridePendingTransition(0, 0);
            }
        }, 5000);
    }
}
