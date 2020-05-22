package com.myandroid.myboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button writeBtn;

    private ListView listView;
    private BoardMainAdapter adapter;
    private List<BoardMain> boardMainList;

    private SwipeRefreshLayout swipeMain;

    private String titleText, userText, dateText, contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        boardMainList = new ArrayList<>();
        adapter = new BoardMainAdapter(getApplicationContext(), boardMainList);

        swipeMain = (SwipeRefreshLayout)findViewById(R.id.swipeMain);

        writeBtn = (Button)findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WriteBoardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        swipeMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                boardMainList.clear();
                new BackgroundTask().execute();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeMain.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new SelectBoard().execute();
                Intent intent = new Intent(MainActivity.this, ShowBoardActivity.class);

                if(adapter.getItem(position) != null) {
                    intent.putExtra("title", titleText);
                    System.out.println("title : " + titleText);
                    intent.putExtra("user", userText);
                    System.out.println("user : " + userText);
                    intent.putExtra("date", dateText);
                    System.out.println("date : " + dateText);
                    intent.putExtra("content", contentText);
                    System.out.println("content : " + contentText);
                } else {
                    Toast.makeText(getApplicationContext(), "데이터 가져오기 실패", Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
            }
        });

        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "https://soulstring94.cafe24.com/MyBoard_MainSelect.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();

                while((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            listView.setAdapter(adapter);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String title, user, ts;

                while(count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    title = object.getString("boardTitle");
                    user = object.getString("userName");
                    ts = object.getString("boardTS");
                    BoardMain boardMain = new BoardMain(title, user, ts);
                    boardMainList.add(boardMain);
                    count++;
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    class SelectBoard extends AsyncTask<Void, Void, String> {
        String target;

        @Override
        protected void onPreExecute() {
            target = "https://soulstring94.cafe24.com/MyBoard_Select.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();

                while((temp = bufferedReader.readLine()) != null) {
                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;

                while(count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);
                    titleText = object.getString("boardTitle");
                    userText = object.getString("userName");
                    dateText = object.getString("boardTS");
                    contentText = object.getString("boardContent");
                    count++;
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
