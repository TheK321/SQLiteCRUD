package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        } else {
            //Create a toast message to show that database is not created
            Toast.makeText(this, "Database is not created", Toast.LENGTH_LONG).show();
        }

        //Close the database
        database.close();
    }
}