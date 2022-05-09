package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityVerTodo extends AppCompatActivity {
private DatabaseHelper databaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_todo);
        databaseAdapter = new DatabaseHelper(this);
        // Create a new database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        TableLayout lvTareas = findViewById(R.id.table_main);
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM tarea ORDER BY fasignada asc", null);

        init(cursor);

        }


    public void init(Cursor cursor) {
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText("ID");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText("Nombre");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);
        TextView tv3 = new TextView(this);
        tv3.setText("Fecha");
        tv3.setTextColor(Color.WHITE);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPCION));
            @SuppressLint("Range") String materia = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MATERIA));
            @SuppressLint("Range") String fasignada = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FASIGNADA));
            @SuppressLint("Range") String fentrega = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FENTREGA));
            @SuppressLint("Range") String dificultad = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DIFICULTAD));
            @SuppressLint("Range") String completada = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COMPLETADA));
            int fondo = completada.equals("1") ? Color.GREEN : Color.RED;
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(id);
            t1v.setTextColor(fondo);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(name);
            t2v.setTextColor(fondo);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            String finalFasignada = fasignada.replace("/", "");
            String finalFentrega = fentrega.replace("/", "");;
            TextView t5v = new TextView(this);
            fasignada=fasignada.length()==8?fasignada.substring(0,2)+"/"+fasignada.substring(2,4)+"/"+fasignada.substring(4,8):fasignada.substring(0,1)+"/"+fasignada.substring(1,3)+"/"+fasignada.substring(3,7);
            fentrega=fentrega.length()==8?fentrega.substring(0,2)+"/"+fentrega.substring(2,4)+"/"+fentrega.substring(4,8):fentrega.substring(0,1)+"/"+fentrega.substring(1,3)+"/"+fentrega.substring(3,7);
            t5v.setText("Entrega el "+fentrega);
            t5v.setTextColor(fondo);
            t5v.setGravity(Gravity.CENTER);
            tbrow.addView(t5v);
            TextView t6v = new TextView(this);

            int finalI = i;

            tbrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //open new activity ActivityActualizar
                    Intent intent = new Intent(ActivityVerTodo.this, ActivityActualizar.class);
                    intent.putExtra("id", id);
                    intent.putExtra("nombre", name);
                    intent.putExtra("descripcion", description);
                    intent.putExtra("materia", materia);
                    intent.putExtra("fasignada", finalFasignada);
                    intent.putExtra("fentrega", finalFentrega);
                    intent.putExtra("dificultad", dificultad);
                    intent.putExtra("completada", completada);

                    startActivity(intent);

                }
            });
            stk.addView(tbrow);
            cursor.moveToNext();
        }
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