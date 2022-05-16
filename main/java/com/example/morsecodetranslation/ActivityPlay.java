package com.example.morsecodetranslation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityPlay extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getSupportActionBar().hide();
        Button play_letters = (Button) findViewById(R.id.play_letters);
        Button playInMorse = (Button) findViewById(R.id.playInMorse);
        Button playOutMorse = (Button) findViewById(R.id.playOutMorse);
        Button play_mix = (Button) findViewById(R.id.play_mix);
        Button back = (Button) findViewById(R.id.button_back);
        TextView choice = (TextView) findViewById(R.id.textChoice);
        View.OnClickListener listener_play = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                switch (v.getId()) {
                    case R.id.play_letters:
                        i = new Intent(ActivityPlay.this, ActivityGame.class);
                        i.putExtra("gm", "Letters");
                        startActivity(i);
                        break;
                    case R.id.playInMorse:
                        i = new Intent(ActivityPlay.this, ActivityGame.class);
                        i.putExtra("gm", "InMorse");
                        startActivity(i);
                        break;
                    case R.id.playOutMorse:
                        i = new Intent(ActivityPlay.this, ActivityGame.class);
                        i.putExtra("gm", "OutMorse");
                        startActivity(i);
                        break;
                    case R.id.play_mix:
                        i = new Intent(ActivityPlay.this, ActivityGame.class);
                        i.putExtra("gm", "Mix");
                        startActivity(i);
                        break;
                    case R.id.button_back:
                        finish();
                        break;
                    default:
                        return;
                }
            }
        };
        play_letters.setOnClickListener(listener_play);
        playInMorse.setOnClickListener(listener_play);
        playOutMorse.setOnClickListener(listener_play);
        play_mix.setOnClickListener(listener_play);
        back.setOnClickListener(listener_play);
    }
}
