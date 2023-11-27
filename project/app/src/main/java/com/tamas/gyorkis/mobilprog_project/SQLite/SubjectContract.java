package com.tamas.gyorkis.mobilprog_project.SQLite;

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
                    "('Keretrendszer alapú programozás', 'NBT_PI232G3', 3), " +
                    "('AFP III.', 'NBT_PI215G2', 2), " +
                    "('Statisztika', 'NBT_IM724G3', 3), " +
                    "('Grafika', 'NBT_IM736G2', 2), " +
                    "('Neurális háló', 'NBT_PI254G2', 2), " +
                    "('Operációkutatás', 'NBT_IM846G2', 2), " +
                    "('C++', 'NBT_PI221G2', 2), " +
                    "('Kiadványszerkesztés', 'NBT_IM872G2', 2), " +
                    "('RTT', 'NBT_PI240G2', 2), " +
                    "('Mesterséges intelligencia', 'NBT_PI100K3', 3), " +
                    "('Szakdolgozati szeminárium', 'NBT_PI2453G8', 8), " +
                    "('Számításelmélet', 'NBT_PI156K3', 3)";

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + SubjectEntry.TABLE_NAME;
}
