package com.example.morsecodetranslation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityHowPlay extends AppCompatActivity {
    private TextView text_rules;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_play);
        getSupportActionBar().hide();
        Button button_back = (Button) findViewById(R.id.button_back);
        text_rules = (TextView) findViewById(R.id.textRules);
        text_rules.setText("Ваша задача переводить слова в азубку морзе и из азбуки морзе обратно в слова."
        +"\n\n"+ "Когда вы переводите слова в азбуку морзе, между отдельными буквами ставьте пробел."
        +"\n\n"+ "Таким же образом, когда вам даётся слово для первода из морзе, отельные буквы разделены пробелом."
        +"\n\n"+ "При нажатии кнопки 'Азбука морзе' в главном меню, вам откроется азбука морзе."
        +"\n\n"+ "Желаю удачи в игре!");

        View.OnClickListener listener_settings = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                switch (v.getId()) {
                    case R.id.button_back:
                        finish();
                        break;
                    default:
                        return;
                }
            }
        };
        button_back.setOnClickListener(listener_settings);
    }
}
