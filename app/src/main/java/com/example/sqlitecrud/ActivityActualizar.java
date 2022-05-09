package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

public class ActivityActualizar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        Button eliminar = (Button) findViewById(R.id.fab);
        Button actualizar = (Button) findViewById(R.id.btnActualizar);

        // get a database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        //Get writable database
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        //check if database is created
        if (database != null) {
            //Create a toast message to show that database is created
            //Toast.makeText(this, "Database is created", Toast.LENGTH_LONG).show();
            //open a new activity
            eliminar.setOnClickListener(v -> {
                //Create a new intent to open the new activity
                Intent intent = new Intent(ActivityActualizar.this, ActivityEliminar.class);
                //Start the new activity
                startActivity(intent);
                //Close the current activity
                finish();
            });
        }

        //get readable database
        database = databaseHelper.getReadableDatabase();
        //retrieve all rows from table tarea
        Cursor cursor = database.rawQuery("SELECT * FROM tarea where completada=0", null);

    }
}