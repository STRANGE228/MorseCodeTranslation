package com.example.morsecodetranslation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Locale;


public class ActivityGame extends AppCompatActivity {
    private EditText answer;
    private TextView quest_word;
    private String game_mode;
    private DataBaseHelper mDBHelper;
    private SQLiteDatabase mDb;
    private String answer_word;
    private TextView status;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().hide();
        TextView gm = (TextView) findViewById(R.id.gm);
        quest_word = (TextView) findViewById(R.id.quest_word);
        Button button_back = (Button) findViewById(R.id.button_back);
        Button check_button = (Button) findViewById(R.id.check_button);
        answer = (EditText) findViewById(R.id.answer_word);
        status = (TextView) findViewById(R.id.status);
        game_mode = getIntent().getStringExtra("gm");
        if (game_mode.equals("Letters")) gm.setText("Перевод букв");
        if (game_mode.equals("InMorse")) gm.setText("Перевод в морзе");
        if (game_mode.equals("OutMorse")) gm.setText("Перевод из морзе");
        if (game_mode.equals("Mix")) gm.setText("Микс");

        mDBHelper = new DataBaseHelper(this);
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }

        View.OnClickListener listener_game = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.setText("");
                Intent i;
                switch (v.getId()) {
                    case R.id.button_back:
                        finish();
                        break;
                    case R.id.check_button:
                        check_answer();
                    default:
                        return;
                }
            }
        };
        button_back.setOnClickListener(listener_game);
        check_button.setOnClickListener(listener_game);
        new_word();
    }

    private void new_word() {
        String product = "";

        if (game_mode.equals("Letters")){
            Cursor cursor = mDb.query("progress",
                    new String[] {"progress"},
                    "mode = ?",
                    new String[] {"letters"},
                    null, null, null);
            cursor.moveToFirst();
            product = cursor.getString(0);
            cursor = mDb.query("letters",
                    new String[] {"letter", "morse_letter"},
                    "_id = ?",
                    new String[] {product},
                    null, null, null);
            cursor.moveToFirst();
            String word = cursor.getString(0);
            answer_word = cursor.getString(1);
            quest_word.setText(word);
            cursor.close();
        }
        if (game_mode.equals("InMorse")) {
            Cursor cursor = mDb.query("progress",
                    new String[] {"progress"},
                    "mode = ?",
                    new String[] {"words"},
                    null, null, null);
            cursor.moveToFirst();
            product = cursor.getString(0);
            cursor = mDb.query("words",
                    new String[] {"word", "morse_word"},
                    "_id = ?",
                    new String[] {product},
                    null, null, null);
            cursor.moveToFirst();
            String word = cursor.getString(0);
            answer_word = cursor.getString(1);
            quest_word.setText(word);
            cursor.close();

        }
        if (game_mode.equals("OutMorse")) {
            Cursor cursor = mDb.query("progress",
                    new String[] {"progress"},
                    "mode = ?",
                    new String[] {"morse_words"},
                    null, null, null);
            cursor.moveToFirst();
            product = cursor.getString(0);
            cursor = mDb.query("morse_words",
                    new String[] {"morse_word", "word"},
                    "_id = ?",
                    new String[] {product},
                    null, null, null);
            cursor.moveToFirst();
            String word = cursor.getString(0);
            answer_word = cursor.getString(1);
            quest_word.setText(word);
            cursor.close();

        }
        if (game_mode.equals("Mix")) {
            Cursor cursor = mDb.query("progress",
                    new String[]{"progress"},
                    "mode = ?",
                    new String[]{"mix"},
                    null, null, null);
            cursor.moveToFirst();
            product = cursor.getString(0);
            cursor = mDb.query("mix",
                    new String[]{"code", "encode"},
                    "_id = ?",
                    new String[]{product},
                    null, null, null);
            cursor.moveToFirst();
            String word = cursor.getString(0);
            answer_word = cursor.getString(1);
            quest_word.setText(word);
            cursor.close();
        }
    }

    private void check_answer() {
        try {
            String word_check = answer.getText().toString().trim().toLowerCase(Locale.ROOT).replace("_", "-");
            String answer_check = answer_word.toString();
            if (word_check.toLowerCase().equals(answer_check.toLowerCase())) {
                int product;
                String mode = "";
                if (game_mode.equals("Letters")) mode = "letters";
                if (game_mode.equals("InMorse")) mode = "words";
                if (game_mode.equals("OutMorse")) mode = "morse_words";
                if (game_mode.equals("Mix")) mode = "mix";
                long count = DatabaseUtils.queryNumEntries(mDb, mode);
                Cursor cursor = mDb.query("progress",
                        new String[]{"progress"},
                        "mode = ?",
                        new String[]{mode},
                        null, null, null);
                cursor.moveToFirst();
                product = (int) cursor.getInt(0);
                ContentValues value = new ContentValues();
                if (product >= count) {
                    status.setText("Вы расшифровали все слова!" + "\n" + "Игра началась заново");
                    value.put("progress", 1);
                } else value.put("progress", product + 1);
                mDb.update("progress",
                        value,
                        "mode = ?",
                        new String[]{mode});
                cursor.close();
                answer.setText("");
                new_word();
            } else {
                status.setText("Неверно");
                answer.setText(word_check);
            }
        } catch (Exception e) {
            status.setText(e.toString());
        }
    }
}


