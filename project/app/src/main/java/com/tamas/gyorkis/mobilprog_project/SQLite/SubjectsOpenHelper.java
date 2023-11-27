package com.tamas.gyorkis.mobilprog_project.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tamas.gyorkis.mobilprog_project.SQLite.SubjectContract;

public class SubjectsOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "SubjectsDatabse.db";
    public static final int DB_VERSION = 6;

    public SubjectsOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SubjectContract.SQL_CREATE_TABLE);
        db.execSQL(SubjectContract.SQL_FILL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SubjectContract.SQL_DELETE_TABLE);
        onCreate(db);
    }
}
