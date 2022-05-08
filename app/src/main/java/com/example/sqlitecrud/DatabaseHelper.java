package com.example.sqlitecrud;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.cursoradapter.widget.SimpleCursorAdapter;

public class DatabaseHelper extends SQLiteOpenHelper {
    //constants for db name and version
    public static final String DATABASE_NAME = "crud.db";
    public static final int DATABASE_VERSION = 2;

    //constants for identifying table and columns
    public static final String TABLE_NAME = "tarea";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_FASIGNADA = "fasignada";
    public static final String COLUMN_FENTREGA = "fentrega";
    public static final String COLUMN_MATERIA = "materia";
    public static final String COLUMN_DIFICULTAD = "dificultad";
    public static final String COLUMN_COMPLETADA = "completada";

    //create table sql query
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOMBRE + " TEXT,"
            + COLUMN_DESCRIPCION + " TEXT,"
            + COLUMN_FASIGNADA + " INTEGER,"
            + COLUMN_FENTREGA + " INTEGER,"
            + COLUMN_MATERIA + " TEXT,"
            + COLUMN_DIFICULTAD + " INTEGER,"
            + COLUMN_COMPLETADA + " INTEGER DEFAULT 0"
            + ")";

    //db version
    public static final String DATABASE_VERSION_KEY = "DATABASE_VERSION";

    //constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SimpleCursorAdapter populateListViewFromDB(Context context) {
        String columns[] = {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NOMBRE, DatabaseHelper.COLUMN_DESCRIPCION, DatabaseHelper.COLUMN_FASIGNADA, DatabaseHelper.COLUMN_FENTREGA, DatabaseHelper.COLUMN_MATERIA, DatabaseHelper.COLUMN_DIFICULTAD, DatabaseHelper.COLUMN_COMPLETADA};
        Cursor cursor = new DatabaseHelper(context).getWritableDatabase().rawQuery("SELECT rowid _id,* FROM " + TABLE_NAME, null);
        String[] fromFieldNames = new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NOMBRE, DatabaseHelper.COLUMN_DESCRIPCION, DatabaseHelper.COLUMN_FASIGNADA, DatabaseHelper.COLUMN_FENTREGA, DatabaseHelper.COLUMN_MATERIA, DatabaseHelper.COLUMN_DIFICULTAD, DatabaseHelper.COLUMN_COMPLETADA};
        int[] toViewIDs = new int[]{R.id.tareaview_id, R.id.tareaview_nombre, R.id.tareaview_fechas, R.id.tareaview_materia, R.id.tareaview_dificultad};
        SimpleCursorAdapter contactAdapter = new SimpleCursorAdapter(
                context,
                R.layout.tareaview,
                cursor,
                fromFieldNames,
                toViewIDs
        );
        return contactAdapter;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}






