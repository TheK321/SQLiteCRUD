package com.example.sqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //constants for db name and version
    public static final String DATABASE_NAME = "crud.db";
    public static final int DATABASE_VERSION = 1;

    //constants for identifying table and columns
    public static final String TABLE_NAME = "tarea";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_FASIGNADA = "fasignada";
    public static final String COLUMN_FENTREGA = "fentrega";
    public static final String COLUMN_MATERIA = "materia";
    public static final String COLUMN_DIFICULTAD = "dificultad";

    //create table sql query
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOMBRE + " TEXT,"
            + COLUMN_DESCRIPCION + " TEXT,"
            + COLUMN_FASIGNADA + " INTEGER,"
            + COLUMN_FENTREGA + " INTEGER,"
            + COLUMN_MATERIA + " TEXT,"
            + COLUMN_DIFICULTAD + " INTEGER"
            + ")";

    //db version
    public static final String DATABASE_VERSION_KEY = "DATABASE_VERSION";

    //constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}






