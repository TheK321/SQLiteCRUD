package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btnNuevaTarea);
        RecyclerView tareas = (RecyclerView) findViewById(R.id.recyclerViewTareas);

        // Create a new database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        //Get writable database
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        //check if database is created
        if (database != null) {
            //Create a toast message to show that database is created
            Toast.makeText(this, "Database is created", Toast.LENGTH_LONG).show();
            //open a new activity
            btn.setOnClickListener(v -> {
                //Create a new intent to open the new activity
                Intent intent = new Intent(MainActivity.this, NuevaTareaActivity.class);
                //Start the new activity
                startActivity(intent);
                //Close the current activity
                finish();
            }
            );

            //retrieve all rows from table tarea
            Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

            //check if cursor is not null
            if (cursor != null) {
                //move to first row
                cursor.moveToFirst();
                //create a new adapter for the recycler view
                TareasAdapter adapter;
                //create a new dataset for the recycler view
                String[] dataset = new String[cursor.getCount()];
                //check if cursor is not at the end of the table
                while (!cursor.isAfterLast()) {
                    //get the value of the column name
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE));
                    //add the value to the dataset
                    dataset[cursor.getPosition()] = name;
                }
                //define the adapter
                adapter = new TareasAdapter(dataset);
                //set the adapter to the recycler view
                tareas.setAdapter(adapter);
            }


        } else {
            //Create a toast message to show that database is not created
            Toast.makeText(this, "Database is not created", Toast.LENGTH_LONG).show();
        }

        //Close the database
        database.close();
    }
}