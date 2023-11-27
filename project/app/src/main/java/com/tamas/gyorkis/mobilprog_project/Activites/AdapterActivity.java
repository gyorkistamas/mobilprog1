package com.tamas.gyorkis.mobilprog_project.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.Button;
import android.widget.ListView;

import com.tamas.gyorkis.mobilprog_project.ActivityOpenType;
import com.tamas.gyorkis.mobilprog_project.R;
import com.tamas.gyorkis.mobilprog_project.SQLite.Subject;
import com.tamas.gyorkis.mobilprog_project.Adapter.SubjectAdapter;
import com.tamas.gyorkis.mobilprog_project.SQLite.SubjectContract;
import com.tamas.gyorkis.mobilprog_project.SQLite.SubjectsOpenHelper;

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

        //getSubjects();

        //adapter = new SubjectAdapter(subjects, this);

        listView = findViewById(R.id.listView);

        //listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(AdapterActivity.this, SubjectViewActivity.class);
            intent.putExtra("type", ActivityOpenType.MODIFY);
            intent.putExtra("id", id);

            startActivity(intent);
        });

        Button newButton = findViewById(R.id.buttonNew);

        newButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdapterActivity.this, SubjectViewActivity.class);
            intent.putExtra("type", ActivityOpenType.CREATE);
            startActivity(intent);
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        getSubjects();
        adapter = new SubjectAdapter(subjects, this);
        listView.setAdapter(adapter);
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

            index = cursor.getColumnIndexOrThrow(SubjectContract.SubjectEntry._ID);
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