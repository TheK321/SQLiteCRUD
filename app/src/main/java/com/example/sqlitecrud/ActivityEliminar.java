package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;

public class ActivityEliminar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        Button aceptar = (Button) findViewById(R.id.btnAceptar);
        Button cancelar = (Button) findViewById(R.id.btnRechazar);
        //get bundle
        Bundle bundle = getIntent().getExtras();
        //get id
        int id = bundle.getInt("id");

        // get a database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        //Get writable database
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        //check if database is created
        if (database != null) {
            //Create a toast message to show that database is created
            //Toast.makeText(this, "Database is created", Toast.LENGTH_LONG).show();
            //open a new activity
            aceptar.setOnClickListener(v -> {
                //Create a new intent to open the new activity
                Intent intent = new Intent(ActivityEliminar.this, ActivityVerTodo.class);
                //Start the new activity
                startActivity(intent);
                //Close the current activity
                finish();
            });

            //open a new activity
            cancelar.setOnClickListener(v -> {
                //Create a new intent to open the new activity
                Intent intent = new Intent(ActivityEliminar.this, ActivityActualizar.class);
                //Start the new activity
                startActivity(intent);
                //Close the current activity
                finish();
            });
        }
    }
}