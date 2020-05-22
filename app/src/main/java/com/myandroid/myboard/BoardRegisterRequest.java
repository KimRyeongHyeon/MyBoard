package com.myandroid.myboard;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class BoardRegisterRequest extends StringRequest {
    final static private String URL = "https://soulstring94.cafe24.com/MyBoard_Insert.php";
    private Map<String, String> parameters;

    public BoardRegisterRequest(String boardTitle, String userName, String boardPassword, String boardContent, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("boardTitle", boardTitle);
        parameters.put("userName", userName);
        parameters.put("boardPassword", boardPassword);
        parameters.put("boardContent", boardContent);
    }

    @Override
    protected Map<String, String> getParams() {
        return parameters;
    }
}
