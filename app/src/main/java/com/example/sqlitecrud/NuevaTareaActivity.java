package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class NuevaTareaActivity extends AppCompatActivity {
    private EditText nombre,descripcion,fasignacion,fentrega,materia;
    private RatingBar dificultad;
    private String nom,descr,fechaasign,fechaentr, mat,diff;
    private Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);
        nombre = findViewById(R.id.editTextNombreTarea);
        descripcion = findViewById(R.id.editTextDescripcionTarea);
        fasignacion = findViewById(R.id.editTextFechaAsignacion);
        fentrega = findViewById(R.id.editTextFechaEntrega);
        materia = findViewById(R.id.editTextMateria);
        dificultad = findViewById(R.id.ratingBarDificultad);
        guardar = findViewById(R.id.btnActualizar);


        fasignacion.setOnClickListener(new View.OnClickListener() {
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
                        fechaasign=simple.format(result);
                        fasignacion.setText((new SimpleDateFormat("dd/MM/yyyy")).format(result));
                    }
                });
            }

        });

        fentrega.setOnClickListener(new View.OnClickListener() {
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
                        fechaentr=simple.format(result);
                        fentrega.setText((new SimpleDateFormat("dd/MM/yyyy")).format(result));
                    }
                });
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom=nombre.getText().toString();
                descr=descripcion.getText().toString();
                mat=materia.getText().toString();
                diff=String.valueOf(Math.round(dificultad.getRating()));
                if(nom.isEmpty()||descr.isEmpty()||fechaasign.isEmpty()||fechaentr.isEmpty()||mat.isEmpty()||diff.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Favor de llenar todos los campos", Toast.LENGTH_SHORT).show();
                    System.out.println("nom "+nom+" \ndescr "+descr+" \nfechaasign "+fechaasign+" \nfechaentr "+fechaentr+" \nmat "+mat+" \ndiff "+diff);
                } else{
                    //get writable database
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                    SQLiteDatabase database = db.getWritableDatabase();
                    //create content values to insert
                    ContentValues values = new ContentValues();
                    //put values to insert
                    values.put(DatabaseHelper.COLUMN_NOMBRE, nom);
                    values.put(DatabaseHelper.COLUMN_DESCRIPCION, descr);
                    values.put(DatabaseHelper.COLUMN_FASIGNADA, fechaasign);
                    values.put(DatabaseHelper.COLUMN_FENTREGA, fechaentr);
                    values.put(DatabaseHelper.COLUMN_MATERIA, mat);
                    values.put(DatabaseHelper.COLUMN_DIFICULTAD, diff);
                    //insert row
                    long id = database.insert(DatabaseHelper.TABLE_NAME, null, values);
                    //if added successfully
                    if (id > 0) {
                        Toast.makeText(getApplicationContext(), "Tarea agregada", Toast.LENGTH_SHORT).show();
                    }   else {
                        Toast.makeText(getApplicationContext(), "Error al agregar", Toast.LENGTH_SHORT).show();
                    }
                    //close database
                    database.close();



                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(NuevaTareaActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}