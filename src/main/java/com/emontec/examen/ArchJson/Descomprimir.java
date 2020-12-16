package com.emontec.examen.ArchJson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.emontec.examen.Librerias.ConexionSQLiteHelper;
import com.emontec.examen.Librerias.DecompressFast;
import com.emontec.examen.Librerias.DescargarArchivo;
import com.emontec.examen.Mapa.Datos;
import com.emontec.examen.R;
import com.emontec.examen.Utilid.Utilidades;

public class Descomprimir extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descomprimir);

        String zipFile = Environment.getExternalStorageDirectory() + "/Download/Archivo.zip"; //your zip file location
        String unzipLocation = Environment.getExternalStorageDirectory() + "/Download/"; // destination folder location
        DecompressFast df= new DecompressFast(zipFile, unzipLocation);
        df.unzip();

        SharedPreferences misPreferencias =getSharedPreferences("BanderaZip", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = misPreferencias.edit();
        final String descomprimido = "1";
        //final String id_codigo = codig;
        editor.putString("Descomprimido", descomprimido);
        //editor.putString("id_codigo", id_codigo);
        editor.commit();

        Intent intent = new Intent(Descomprimir.this, Datos.class);
        startActivity(intent);



registrarUsuarios();
    }

    private void registrarUsuarios() {
        ConexionSQLiteHelper conn=new ConexionSQLiteHelper(this,"bd_usuarios",null,1);

        SQLiteDatabase db=conn.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_ID,"124");
        values.put(Utilidades.CAMPO_NOMBRE,"Daniel Alejandro Catañeda");
        values.put(Utilidades.CAMPO_CORREO,"alejandro@upaxer.com");
        values.put(Utilidades.CAMPO_LATITUD,"19.300378");
        values.put(Utilidades.CAMPO_LONGITUD,"99.2009822");
        Long idResultante=db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();


        SQLiteDatabase db2=conn.getWritableDatabase();

        ContentValues values2=new ContentValues();
        values2.put(Utilidades.CAMPO_ID,"145");
        values2.put(Utilidades.CAMPO_NOMBRE,"Mariela Duran");
        values2.put(Utilidades.CAMPO_CORREO,"mariela@upaxer.com");
        values2.put(Utilidades.CAMPO_LATITUD,"19.300378");
        values2.put(Utilidades.CAMPO_LONGITUD,"99.2009822");
        Long idResultante2=db2.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values2);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante2,Toast.LENGTH_SHORT).show();
        db2.close();

        SQLiteDatabase db3=conn.getWritableDatabase();

        ContentValues values3=new ContentValues();
        values3.put(Utilidades.CAMPO_ID,"175");
        values3.put(Utilidades.CAMPO_NOMBRE,"David Campos");
        values3.put(Utilidades.CAMPO_CORREO,"david@upaxer.com");
        values3.put(Utilidades.CAMPO_LATITUD,"19.300378");
        values3.put(Utilidades.CAMPO_LONGITUD,"99.2009822");
        Long idResultante3=db3.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values3);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante3,Toast.LENGTH_SHORT).show();
        db3.close();

        SQLiteDatabase db4=conn.getWritableDatabase();

        ContentValues values4=new ContentValues();
        values4.put(Utilidades.CAMPO_ID,"198");
        values4.put(Utilidades.CAMPO_NOMBRE,"Jesus Alejandro Damian");
        values4.put(Utilidades.CAMPO_CORREO,"jesus@upaxer.com");
        values4.put(Utilidades.CAMPO_LATITUD,"19.300378");
        values4.put(Utilidades.CAMPO_LONGITUD,"99.2009822");
        Long idResultante4=db4.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values4);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante4,Toast.LENGTH_SHORT).show();
        db4.close();

    }

}
