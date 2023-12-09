package com.tamas.gyorkis.mobilprog_project.Activites;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.tamas.gyorkis.mobilprog_project.R;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                o -> {
                    if (o.getResultCode() == RESULT_OK) {
                        Intent intent = o.getData();
                        int age = intent.getIntExtra("age", -1);
                        Toast.makeText(MainActivity.this, String.format("%s: %s", getResources().getString(R.string.your_age), age), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, R.string.error, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        Button explicit = findViewById(R.id.btnStartExplicit);
        explicit.setOnClickListener((v) -> {
            Intent intent = new Intent(MainActivity.this, ParameterActivity.class);
            activityResultLauncher.launch(intent);
        });

        Button implicit = findViewById(R.id.btnStartImplicit);
        implicit.setOnClickListener((v) -> {
            Intent intent = new Intent("ASKBIRTHDAY");
            activityResultLauncher.launch(intent);
        });


        Button adapter = findViewById(R.id.btnStartAdapter);
        adapter.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdapterActivity.class);
            startActivity(intent);
        });
    }
}