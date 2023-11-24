package com.tamas.gyorkis.mobilprog_project;

import android.provider.BaseColumns;

public class SubjectContract {

    private SubjectContract() {
    }


    public static class SubjectEntry implements BaseColumns {
        public static final String TABLE_NAME = "subjects";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_CREDIT = "credit";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + SubjectEntry.TABLE_NAME + " (" +
                    SubjectEntry._ID + " INTEGER PRIMARY KEY," +
                    SubjectEntry.COLUMN_NAME_NAME + " TEXT," +
                    SubjectEntry.COLUMN_NAME_CODE + " TEXT," +
                    SubjectEntry.COLUMN_NAME_CREDIT + " INTEGER)";

    public static final String SQL_FILL_TABLE =
            "INSERT INTO " + SubjectEntry.TABLE_NAME +
                    "(name, code, credit) " +
                    "VALUES ('Mobilprogramozás I.', 'NBT_PI236G2', 2), " +
                    "('Keretrendszer alapú programozás', 'NBT_PI232G3', 3)";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + SubjectEntry.TABLE_NAME;
}
