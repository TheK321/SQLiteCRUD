package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class NuevaTareaActivity extends AppCompatActivity {
    private EditText nombre,descripcion,fasignacion,fentrega,materia,dificultad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);
        nombre = findViewById(R.id.editTextNombreTarea);
        descripcion = findViewById(R.id.editTextDescripcionTarea);
        fasignacion = findViewById(R.id.editTextFechaAsignacion);

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
                        System.out.println(fechaAsignacion);
                        simple.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
                        System.out.println(simple.format(result));

                    }
                });
            }

        });
    }
}