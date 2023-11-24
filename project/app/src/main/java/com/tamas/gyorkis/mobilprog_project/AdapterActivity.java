package com.tamas.gyorkis.mobilprog_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AdapterActivity extends AppCompatActivity {

    List<Subject> subjects = new ArrayList<>();
    SubjectAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        getSubjects();

        adapter = new SubjectAdapter(subjects, this);

        listView = findViewById(R.id.listView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(AdapterActivity.this, SubjectViewActivity.class);
            intent.putExtra("type", ActivityOpenType.MODIFY);
            intent.putExtra("id", id);

            startActivity(intent);
        });

    }

    private void getSubjects() {
        subjects.clear();

        SubjectsOpenHelper openHelper = new SubjectsOpenHelper(this);

        SQLiteDatabase db = openHelper.getReadableDatabase();


        String[] columns = {
                BaseColumns._ID,
                SubjectContract.SubjectEntry.COLUMN_NAME_NAME,
                SubjectContract.SubjectEntry.COLUMN_NAME_CODE,
                SubjectContract.SubjectEntry.COLUMN_NAME_CREDIT
        };

        String sortOrder = SubjectContract.SubjectEntry.COLUMN_NAME_NAME;

        Cursor cursor = db.query(
                SubjectContract.SubjectEntry.TABLE_NAME,
                columns,
                "",
                null,
                sortOrder,
                "",
                ""
        );

        while(cursor.moveToNext()) {
            int index;

            index = cursor.getColumnIndexOrThrow("_id");
            Long id = cursor.getLong(index);

            index = cursor.getColumnIndexOrThrow(SubjectContract.SubjectEntry.COLUMN_NAME_NAME);
            String name = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow(SubjectContract.SubjectEntry.COLUMN_NAME_CODE);
            String code = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow(SubjectContract.SubjectEntry.COLUMN_NAME_CREDIT);
            int credit = cursor.getInt(index);

            subjects.add(new Subject(id, name, code, credit));
        }

        openHelper.close();
    }
}