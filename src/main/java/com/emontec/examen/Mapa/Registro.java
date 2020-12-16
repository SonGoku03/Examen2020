package com.emontec.examen.Mapa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emontec.examen.Librerias.ConexionSQLiteHelper;
import com.emontec.examen.R;
import com.emontec.examen.Utilid.Utilidades;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Registro extends AppCompatActivity {

    EditText campoId,campoNombre,campoCorreo,campoLatitud,campoLongitud;
    Button Registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        campoCorreo= (EditText) findViewById(R.id.campoCorreo);
        campoNombre= (EditText) findViewById(R.id.campoNombre);
        campoLatitud= (EditText) findViewById(R.id.campoLatitud);
        campoLongitud= (EditText) findViewById(R.id.campoLongitud);
        Registro = (Button) findViewById(R.id.btnRegistro);

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuarios();
            }
        });
    }


    private void registrarUsuariosSql() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        String insert="INSERT INTO "+ Utilidades.TABLA_USUARIO
                +" ( " +Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_CORREO+")" +
                " VALUES ("+campoId.getText().toString()+", '"+campoNombre.getText().toString()+"','"
                +campoCorreo.getText().toString()+campoLatitud.getText().toString()+campoLongitud.getText().toString()+"')";

        db.execSQL(insert);


        db.close();
    }


    private void registrarUsuarios() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
       // values.put(Utilidades.CAMPO_ID,campoId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,campoNombre.getText().toString());
        values.put(Utilidades.CAMPO_CORREO,campoCorreo.getText().toString());
        values.put(Utilidades.CAMPO_LATITUD,campoLatitud.getText().toString());
        values.put(Utilidades.CAMPO_LONGITUD,campoLongitud.getText().toString());

        Long idResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Registro.this);
        // set title
        alertDialogBuilder.setTitle("Registro Exitoso");
        // set dialog message
        alertDialogBuilder
                .setMessage("Felicidades! Se ha creado un nuevo colaborador")
                .setCancelable(false)
                .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        Intent intent = new Intent(Registro.this, Datos.class);
                        startActivity(intent);
                        finish();

                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }
}
