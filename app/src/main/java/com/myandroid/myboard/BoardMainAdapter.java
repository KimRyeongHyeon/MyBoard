package com.myandroid.myboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BoardMainAdapter extends BaseAdapter {

    private Context context;
    private List<BoardMain> boardMainList;

    public BoardMainAdapter(Context context, List<BoardMain> boardMainList) {
        this.context = context;
        this.boardMainList = boardMainList;
    }

    @Override
    public int getCount() {
        return boardMainList.size();
    }

    @Override
    public Object getItem(int position) {
        return boardMainList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.list_main, null);
        TextView titleTextView = (TextView)view.findViewById(R.id.titleTextView);
        TextView userTextView = (TextView)view.findViewById(R.id.userTextView);
        TextView dateTextView = (TextView)view.findViewById(R.id.dateTextView);

        titleTextView.setText(boardMainList.get(position).getBoardTitle());
        userTextView.setText(boardMainList.get(position).getUserName());
        dateTextView.setText(boardMainList.get(position).getBoardTS());

        view.setTag(boardMainList.get(position).getBoardTitle());
        return view;
    }
}
