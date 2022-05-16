package com.example.morsecodetranslation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityHelp extends AppCompatActivity {
    private TextView text;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().hide();
        text = (TextView) findViewById(R.id.textHelp);
        text.setText("а: .-" +"            "+ "б: -..." +"        "+ "в: .--" +"     \n"+
                "г: --." +"          "+ "д: -.." +"         "+ "е: ." +"         \n"+
                "ё: ." +"             "+ "ж: ...-" +"       "+ "з: --.." +"    \n"+
                "и: .." +"           "+ "й: .---" +"      "+ "к: -.-" +"     \n"+
                "л: .-.." +"        "+ "м: --" +"         "+ "н: -." +"      \n"+
                "о: ---" +"        "+ "п: .--." +"       "+ "р: .-." +"     \n"+
                "с: ..." +"          "+ "т: -" +"            "+ "у: ..-" +"     \n"+
                "ф: ..-." +"       "+ "x: ...." +"         "+ "ц: -.-." +"   \n"+
                "ч: ---." +"      "+ "ш: ----" +"     "+ "щ: --.-" +" \n"+
                "ъ: --.--" +"    "+ "ы: -.--" +"      "+ "ь: -..-" +"   \n"+
                "э: ..-.." +"      "+ "ю: ..--" +"       "+ "я: .-.-" +"   \n" +
                "1: .----" +"     "+ "2: ..---" +"     "+ "3: ...--" +"  \n"+
                "4: ....-" +"      "+ "5: ....." +"       "+ "6: -...." +"   \n"+
                "7: --..." +"     "+ "8: ---.." +"     "+ "9: ----." +"  \n" +
                "" + "0: -----");

        Button button_back = (Button) findViewById(R.id.button_back);

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

