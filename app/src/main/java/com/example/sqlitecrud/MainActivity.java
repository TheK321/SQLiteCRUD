package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btnNuevaTarea);
        Button verTodo = (Button) findViewById(R.id.btnVerTodo);
        TextView tvTituloCard = (TextView) findViewById(R.id.tvTituloCard);
        TextView tvSubtituloCard = (TextView) findViewById(R.id.tvSubtituloCard);
        TextView tvDescripcionCard = (TextView) findViewById(R.id.tvDescripcionCard);

        // Create a new database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        //Get writable database
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        //check if database is created
        if (database != null) {
            //Create a toast message to show that database is created
            //Toast.makeText(this, "Database is created", Toast.LENGTH_LONG).show();
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
            verTodo.setOnClickListener(v -> {
                //Create a new intent to open the new activity
                Intent intent = new Intent(MainActivity.this, ActivityVerTodo.class);
                //Start the new activity
                startActivity(intent);
                //Close the current activity
                finish();
                    }
            );

            //get readable database
            database = databaseHelper.getReadableDatabase();
            //retrieve all rows from table tarea
            Cursor cursor = database.rawQuery("SELECT * FROM tarea where completada=0 ORDER BY fentrega asc", null);

            //check if cursor is not null
            if (cursor != null) {
                //check if cursor has any rows
                if (cursor.getCount() > 0) {
                    //move to first row
                    cursor.moveToFirst();
                    //get the value of the column name
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE));
                    @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPCION));
                    @SuppressLint("Range") String materia = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MATERIA));
                    @SuppressLint("Range") String fentrega = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FENTREGA));
                    //add the value to the dataset
                    System.out.println(name + " " + description);
                    tvTituloCard.setText(name);
                    fentrega= fentrega.length()==6?fentrega.substring(0,2)+"/"+fentrega.substring(2,4)+"/"+fentrega.substring(4,7):fentrega.substring(0,1)+"/"+fentrega.substring(1,3)+"/"+fentrega.substring(3,7);
                    tvSubtituloCard.setText(materia+" | "+fentrega);
                    tvDescripcionCard.setText(description);


                }



            }


        } else {
            //Create a toast message to show that database is not created
            Toast.makeText(this, "Database is not created", Toast.LENGTH_LONG).show();
        }

        //Close the database
        database.close();
    }
}