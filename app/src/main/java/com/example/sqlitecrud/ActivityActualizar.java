package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ActivityActualizar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        FloatingActionButton eliminar =  findViewById(R.id.fab);
        Button actualizar = (Button) findViewById(R.id.btnActualizar);
        //get intent safely
        Intent intent = getIntent();
        //get the data
        String id = intent.getStringExtra("id");
        String nombre = intent.getStringExtra("nombre");
        String materia = intent.getStringExtra("materia");
        String descripcion = intent.getStringExtra("descripcion");
        String fentrega = intent.getStringExtra("fentrega");
        String fasignada = intent.getStringExtra("fasignada");
        String dificultad = intent.getStringExtra("dificultad");
        String completada = intent.getStringExtra("completada");

        EditText  ednombre, edmateria, eddescripcion, edfentrega, edfasignada;
        RatingBar rdificultad;
        ednombre = (EditText) findViewById(R.id.editTextNombreTarea);
        edmateria = (EditText) findViewById(R.id.editTextMateria);
        eddescripcion = (EditText) findViewById(R.id.editTextDescripcionTarea);
        edfentrega = (EditText) findViewById(R.id.editTextFechaEntrega);
        edfasignada = (EditText) findViewById(R.id.editTextFechaAsignacion);
        rdificultad = (RatingBar) findViewById(R.id.ratingBarDificultad);

        ednombre.setText(nombre);
        edmateria.setText(materia);
        eddescripcion.setText(descripcion);
        edfentrega.setText(fentrega);
        edfasignada.setText(fasignada);
        rdificultad.setRating(Float.parseFloat(dificultad));


        System.out.println("id: " + id+" nombre: " + nombre+" materia: " + materia+" descripcion: " + descripcion+" fentrega: " + fentrega+" fasignada: " + fasignada+" dificultad: " + dificultad+" completada: " + completada);

        // get a database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        //Get writable database
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        //check if database is created
        if (database != null) {
            //Create a toast message to show that database is created
            //Toast.makeText(this, "Database is created", Toast.LENGTH_LONG).show();
            //open a new activity
            SQLiteDatabase finalDatabase = database;
            actualizar.setOnClickListener(v -> {
                //get the data
                String nombreTarea = ednombre.getText().toString();
                String materiaTarea = edmateria.getText().toString();
                String descripcionTarea = eddescripcion.getText().toString();
                String fentregaTarea = edfentrega.getText().toString().replace("/", "");
                String fasignadaTarea = edfasignada.getText().toString().replace("/", "");;
                String dificultadTarea = String.valueOf(rdificultad.getRating());
                finalDatabase.execSQL("UPDATE tarea SET nombre = '"+nombreTarea+"', materia = '"+materiaTarea+"', descripcion = '"+descripcionTarea+"', fentrega = '"+fentregaTarea+"', fasignada = '"+fasignadaTarea+"', dificultad = '"+dificultadTarea+"' WHERE id = "+id);
                Toast.makeText(this, "Tarea actualizada", Toast.LENGTH_LONG).show();
            }
            );

            eliminar.setOnClickListener(v -> {
                //Create a new intent to open the new activity
                Intent intent2 = new Intent(ActivityActualizar.this, ActivityEliminar.class);
                //Start the new activity
                startActivity(intent2);
                //Close the current activity
                finish();
            });

            EditText etfasignada=findViewById(R.id.editTextFechaAsignacion);
                    etfasignada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                            .setTitleText("Fecha de asignación")
                            .build();
                    //show the datepicker
                    datePicker.show(getSupportFragmentManager(), "Fecha de asignacion");
                    datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            String fechaAsignacion= datePicker.getSelection().toString();
                            DateFormat simple = new SimpleDateFormat("ddMMyyyy");
                            Date result = new Date(Long.parseLong(fechaAsignacion)+ (1000 * 60 * 60 * 24));
                            //System.out.println(fechaAsignacion);
                            simple.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
                            //edfasignada=simple.format(result);
                            edfasignada.setText((new SimpleDateFormat("dd/MM/yyyy")).format(result));
                        }
                    });
                }

            });

            EditText etfentrega=findViewById(R.id.editTextFechaEntrega);
            etfentrega.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                            .setTitleText("Fecha de entrega")
                            .build();
                    //show the datepicker
                    datePicker.show(getSupportFragmentManager(), "Fecha de entrega");
                    datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            String fechaEntrega= datePicker.getSelection().toString();
                            DateFormat simple = new SimpleDateFormat("ddMMyyyy");
                            Date result = new Date(Long.parseLong(fechaEntrega)+ (1000 * 60 * 60 * 24));
                            //System.out.println(fechaAsignacion);
                            simple.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
                            String fechaentrega=simple.format(result);
                            etfentrega.setText((new SimpleDateFormat("dd/MM/yyyy")).format(result));
                        }
                    });
                }
            });
        }

        //get readable database
        database = databaseHelper.getReadableDatabase();
        //retrieve all rows from table tarea
        Cursor cursor = database.rawQuery("SELECT * FROM tarea where completada=0", null);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ActivityActualizar.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}