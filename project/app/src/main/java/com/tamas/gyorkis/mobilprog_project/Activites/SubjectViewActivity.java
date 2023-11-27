package com.tamas.gyorkis.mobilprog_project.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.Button;
import android.widget.EditText;

import com.tamas.gyorkis.mobilprog_project.R;
import com.tamas.gyorkis.mobilprog_project.SQLite.Subject;
import com.tamas.gyorkis.mobilprog_project.SQLite.SubjectContract;
import com.tamas.gyorkis.mobilprog_project.SQLite.SubjectsOpenHelper;

public class SubjectViewActivity extends AppCompatActivity {

    int openType;

    EditText name;
    EditText code;
    EditText credit;
    Button save;
    Button cancel;
    Button delete;

    Subject instance = new Subject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_view);
        name = findViewById(R.id.editTextName);
        code = findViewById(R.id.editTextCode);
        credit = findViewById(R.id.editTextCredit);
        save = findViewById(R.id.buttonSave);
        cancel = findViewById(R.id.buttonCancel);
        delete = findViewById(R.id.buttonDelete);

        Intent intent = getIntent();

        openType = intent.getIntExtra("type", 0);

        save.setOnClickListener(v -> {
            instance.setName(name.getText().toString());
            instance.setCode(code.getText().toString());
            instance.setCredit(Integer.parseInt(credit.getText().toString()));
            if (openType == 0) saveNew();
            else saveUpdated();

            finish();
        });

        cancel.setOnClickListener(v -> {
            finish();
        });

        delete.setOnClickListener(v -> {
            if (openType == 1) {
                delete();
                finish();
            }
        });

        if (openType == 1) {
            Long id = intent.getLongExtra("id", 0L);
            getSubject(id);
            name.setText(instance.getName());
            code.setText(instance.getCode());
            credit.setText(String.valueOf(instance.getCredit()));
        }

    }

    private void saveNew() {
        SubjectsOpenHelper helper = new SubjectsOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SubjectContract.SubjectEntry.COLUMN_NAME_NAME, instance.getName());
        values.put(SubjectContract.SubjectEntry.COLUMN_NAME_CODE, instance.getCode());
        values.put(SubjectContract.SubjectEntry.COLUMN_NAME_CREDIT, instance.getCredit());

        db.insert(SubjectContract.SubjectEntry.TABLE_NAME, null, values);

        helper.close();
    }


    private void saveUpdated() {
        SubjectsOpenHelper helper = new SubjectsOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String selection = SubjectContract.SubjectEntry._ID + "=?";

        String[] selectionArgs = new String[] {String.valueOf(instance.getId())};

        values.put(SubjectContract.SubjectEntry.COLUMN_NAME_NAME, instance.getName());
        values.put(SubjectContract.SubjectEntry.COLUMN_NAME_CODE, instance.getCode());
        values.put(SubjectContract.SubjectEntry.COLUMN_NAME_CREDIT, instance.getCredit());

        db.update(
                SubjectContract.SubjectEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        helper.close();
    }

    private void delete() {
        SubjectsOpenHelper helper = new SubjectsOpenHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String selection = SubjectContract.SubjectEntry._ID + "=?";

        String[] selectionArgs = new String[] {String.valueOf(instance.getId())};

        db.delete(
                SubjectContract.SubjectEntry.TABLE_NAME,
                selection,
                selectionArgs
        );

        helper.close();
    }


    private void getSubject(Long id) {
        SubjectsOpenHelper openHelper = new SubjectsOpenHelper(this);

        SQLiteDatabase db = openHelper.getReadableDatabase();


        String[] columns = {
                BaseColumns._ID,
                SubjectContract.SubjectEntry.COLUMN_NAME_NAME,
                SubjectContract.SubjectEntry.COLUMN_NAME_CODE,
                SubjectContract.SubjectEntry.COLUMN_NAME_CREDIT
        };

        String selection = SubjectContract.SubjectEntry._ID + "=?";

        String[] selectionArgs = new String[]{String.valueOf(id)};

        String sortOrder = SubjectContract.SubjectEntry.COLUMN_NAME_NAME;

        Cursor cursor = db.query(
                SubjectContract.SubjectEntry.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                sortOrder,
                "",
                ""
        );

        while(cursor.moveToNext()) {
            int index;

            index = cursor.getColumnIndexOrThrow("_id");
            Long selectId = cursor.getLong(index);

            index = cursor.getColumnIndexOrThrow(SubjectContract.SubjectEntry.COLUMN_NAME_NAME);
            String name = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow(SubjectContract.SubjectEntry.COLUMN_NAME_CODE);
            String code = cursor.getString(index);

            index = cursor.getColumnIndexOrThrow(SubjectContract.SubjectEntry.COLUMN_NAME_CREDIT);
            int credit = cursor.getInt(index);

            instance = new Subject(selectId, name, code, credit);
        }

        openHelper.close();
    }
}