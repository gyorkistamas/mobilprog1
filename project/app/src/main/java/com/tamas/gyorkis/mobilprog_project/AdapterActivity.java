package com.tamas.gyorkis.mobilprog_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdapterActivity extends AppCompatActivity {

    List<Joke> jokes = new ArrayList<>(Arrays.asList(
            new Joke("test", "test")
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        JokeAdapter adapter = new JokeAdapter(jokes, this);

        ListView listView = findViewById(R.id.listView);

        listView.setAdapter(adapter);

    }
}