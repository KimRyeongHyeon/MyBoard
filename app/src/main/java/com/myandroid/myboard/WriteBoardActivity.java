package com.myandroid.myboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class WriteBoardActivity extends AppCompatActivity {

    private EditText titleEditText, userEditText, passwordEditText, contentEditText;
    private Button writeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_board);

        titleEditText = (EditText)findViewById(R.id.titleEditText);
        userEditText = (EditText)findViewById(R.id.userEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        contentEditText = (EditText)findViewById(R.id.contentEditText);

        writeBtn = (Button)findViewById(R.id.writeBtn);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String user = userEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String content = contentEditText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success) {
                                Toast.makeText(getApplicationContext(), "글 작성이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                finish();

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                BoardRegisterRequest boardRegisterRequest = new BoardRegisterRequest(title, user, password, content, responseListener);
                RequestQueue queue = Volley.newRequestQueue(WriteBoardActivity.this);
                queue.add(boardRegisterRequest);
            }
        });
    }
}
