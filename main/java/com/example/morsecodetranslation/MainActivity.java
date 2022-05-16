package com.example.morsecodetranslation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.main_menu);
        Button button_play = (Button) findViewById(R.id.button_play);
        Button button_how_play = (Button) findViewById(R.id.button_how_play);
        Button button_help = (Button) findViewById(R.id.button_help);
        Button button_exit = (Button) findViewById(R.id.button_exit);
        TextView heading = (TextView) findViewById(R.id.heading);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                switch (v.getId()) {
                    case R.id.button_play:
                        i = new Intent(MainActivity.this, ActivityPlay.class);
                        startActivity(i);
                        break;
                    case R.id.button_help:
                        i = new Intent(MainActivity.this, ActivityHelp.class);
                        startActivity(i);
                        break;
                    case R.id.button_how_play:
                        i = new Intent(MainActivity.this, ActivityHowPlay.class);
                        startActivity(i);
                        break;
                    case R.id.button_exit:
                        finish();
                        break;
                    default:
                        return;
                }
            }
        };
        button_play.setOnClickListener(listener);
        button_how_play.setOnClickListener(listener);
        button_exit.setOnClickListener(listener);
        button_help.setOnClickListener(listener);
    }
}
