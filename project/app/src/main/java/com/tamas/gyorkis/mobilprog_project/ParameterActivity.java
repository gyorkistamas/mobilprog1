package com.tamas.gyorkis.mobilprog_project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Calendar;
import java.util.Date;

public class ParameterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter);

        Button btn = findViewById(R.id.btnSend);
        EditText input = findViewById(R.id.birthInput);
        input.setFocusable(false);

        input.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, y, m, d) -> {
                        input.setText(y + "-" + (m + 1) + "-" + d);
                    },
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });

        btn.setOnClickListener(v -> {
            Intent intent = getIntent();
            Calendar birthDate = Calendar.getInstance();
            Calendar today = Calendar.getInstance();

            today.setTime(Calendar.getInstance().getTime());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
            try {
                Date date = dateFormat.parse(input.getText().toString());
                birthDate.setTime(date);
            } catch (ParseException e) {
                setResult(RESULT_CANCELED);
                finish();
            }

            int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

            if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) || (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
                age--;
            }

            intent.putExtra("age", age);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}