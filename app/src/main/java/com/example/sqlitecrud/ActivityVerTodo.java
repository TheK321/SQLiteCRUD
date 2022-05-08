package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ActivityVerTodo extends AppCompatActivity {
private DatabaseHelper databaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_todo);
        databaseAdapter = new DatabaseHelper(this);
        ListView lvTareas = findViewById(R.id.lvTareas);
        final SimpleCursorAdapter simpleCursorAdapter = databaseAdapter.populateListViewFromDB(this);
        //iterate over the cursor printing the values
        for (int i = 0; i < simpleCursorAdapter.getCount(); i++) {
            Cursor cursor = simpleCursorAdapter.getCursor();
            cursor.moveToPosition(i);
            @SuppressLint("Range")   String id = cursor.getString(cursor.getColumnIndex("_id"));
            @SuppressLint("Range")  String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            @SuppressLint("Range")  String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));
            @SuppressLint("Range")  String fasignada = cursor.getString(cursor.getColumnIndex("fasignada"));
            @SuppressLint("Range")  String fentrega = cursor.getString(cursor.getColumnIndex("fentrega"));
            @SuppressLint("Range")  String materia = cursor.getString(cursor.getColumnIndex("materia"));
            @SuppressLint("Range")  String dificultad = cursor.getString(cursor.getColumnIndex("dificultad"));
            @SuppressLint("Range")  String completada = cursor.getString(cursor.getColumnIndex("completada"));
            System.out.println("id: " + id + " nombre: " + nombre + " descripcion: " + descripcion + " fasignada: " + fasignada + " fentrega: " + fentrega + " materia: " + materia + " dificultad: " + dificultad + " completada: " + completada);
        }

        lvTareas.setAdapter(simpleCursorAdapter);
        lvTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String name = cursor.getString(1);
                Toast.makeText(view.getContext(), name, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ActivityVerTodo.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}