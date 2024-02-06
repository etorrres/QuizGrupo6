package com.uth.personasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Configuracion.SQLiteConexion;
import Configuracion.Transacciones;

public class MainActivity extends AppCompatActivity {

    EditText nombres;
    EditText apellidos;
    EditText edad;
    EditText correo;
    EditText direccion;
    Button salvar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombres = (EditText) findViewById(R.id.nombre_txt);
        apellidos = (EditText) findViewById(R.id.apellido_txt);
        edad = (EditText) findViewById(R.id.edad_txt);
        correo = (EditText) findViewById(R.id.email_txt);
        direccion = (EditText) findViewById(R.id.direccion_txt);
        salvar = (Button) findViewById(R.id.salvar_btn);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                salvarData();
            }

        });

    }

    public void salvarData(){
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.DBName, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues datos = new ContentValues();
        datos.put(Transacciones.nombres, nombres.getText().toString());
        datos.put(Transacciones.apellidos, apellidos.getText().toString());
        datos.put(Transacciones.edad, edad.getText().toString());
        datos.put(Transacciones.correo, correo.getText().toString());
        datos.put(Transacciones.direccion, direccion.getText().toString());

        Long resultado = db.insert(Transacciones.TablePersonas, Transacciones.id, datos);

        Toast.makeText(getApplicationContext(), "Persona ingresada correctamente " + resultado.toString(),
                Toast.LENGTH_LONG).show();

    }
}