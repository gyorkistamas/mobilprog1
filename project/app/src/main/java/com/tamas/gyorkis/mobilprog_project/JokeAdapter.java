package com.tamas.gyorkis.mobilprog_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JokeAdapter extends BaseAdapter {

    List<Joke> jokes = new ArrayList<>();

    Context context;

    public JokeAdapter(List<Joke> jokes, Context context) {
        this.jokes = jokes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return jokes.size();
    }

    @Override
    public Object getItem(int position) {
        return jokes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.joke_list_item, parent, false);
        }

        TextView title = convertView.findViewById(R.id.jokeTitle);
        TextView preview = convertView.findViewById(R.id.jokePreview);

        title.setText(jokes.get(position).getTitle());
        preview.setText(jokes.get(position).getText());

        return convertView;
    }
}
