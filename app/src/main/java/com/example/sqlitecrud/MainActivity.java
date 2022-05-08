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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btnNuevaTarea);
        ScrollView svTareas = (ScrollView) findViewById(R.id.scrollViewTareas);
        LinearLayout llTareas = (LinearLayout) findViewById(R.id.linearLayoutTareas);

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
            //get readable database
            database = databaseHelper.getReadableDatabase();
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
                //loop through all rows
                for (int i = 0; i < cursor.getCount(); i++) {
                    //get the value of the column name
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE));
                    @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPCION));
                    //add the value to the dataset
                    dataset[cursor.getPosition()] = name;

                    CardView card = new CardView(new ContextThemeWrapper(MainActivity.this, R.style.Theme_SQLiteCRUD), null, 0);
                    LinearLayout cardInner = new LinearLayout(new ContextThemeWrapper(MainActivity.this, R.style.Theme_SQLiteCRUD));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    int margin = 8;
                    params.setMargins(margin, margin, margin, margin);
                    card.setLayoutParams(params);
                    TextView tv_title = new TextView(this);
                    tv_title.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    tv_title.setTextAppearance(this, androidx.appcompat.R.style.TextAppearance_AppCompat_Title);
                    tv_title.setText(name);

                    TextView tv_caption = new TextView(this);
                    tv_caption.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    ));
                    tv_caption.setText(description);
                    tv_caption.setTextAppearance(this, androidx.appcompat.R.style.TextAppearance_AppCompat_Caption);

                    cardInner.addView(tv_title);
                    cardInner.addView(tv_caption);
                    card.addView(cardInner);

                    llTareas.addView(card);

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