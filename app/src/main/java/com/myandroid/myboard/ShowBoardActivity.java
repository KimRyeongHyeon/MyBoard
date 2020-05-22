package com.myandroid.myboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowBoardActivity extends AppCompatActivity {

    private TextView titleTextView, userTextView, dateTextView, contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_board);

        Intent intent = getIntent();

        titleTextView = (TextView)findViewById(R.id.titleTextView);
        userTextView = (TextView)findViewById(R.id.userTextView);
        dateTextView = (TextView)findViewById(R.id.dateTextView);
        contentTextView = (TextView)findViewById(R.id.contentTextView);

        String title = intent.getStringExtra("title");
        titleTextView.setText(title);
        String user = intent.getStringExtra("user");
        userTextView.setText(user);
        String date = intent.getStringExtra("date");
        dateTextView.setText(date);
        String content = intent.getStringExtra("content");
        contentTextView.setText(content);
    }
}
