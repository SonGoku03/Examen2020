package com.emontec.examen.Mapa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.emontec.examen.Adapter.ListaColaboradores;
import com.emontec.examen.Librerias.ConexionSQLiteHelper;
import com.emontec.examen.Modelo.Colaboradores;
import com.emontec.examen.R;
import com.emontec.examen.Utilid.Utilidades;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    ArrayList<Colaboradores> listaUsuario;
    RecyclerView recyclerViewUsuarios;

    ConexionSQLiteHelper conn;

    ArrayList<Colaboradores> listaColecciones;
    RecyclerView recyclerViewColecciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaColecciones=new ArrayList<>();
        //recyclerViewColecciones.setAdapter(adapter1);
        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        listaUsuario=new ArrayList<>();

        recyclerViewUsuarios= (RecyclerView) findViewById(R.id.recicler_colaboradores);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaColaboradores adapter=new ListaColaboradores(listaUsuario);
        recyclerViewUsuarios.setAdapter(adapter);

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();

        Colaboradores usuario=null;
        // listaUsuarios=new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            usuario=new Colaboradores();
            usuario.setId(cursor.getString(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setCorreo(cursor.getString(2));
            usuario.setLatitud(cursor.getString(3));
            usuario.setLongitud(cursor.getString(4));

            listaUsuario.add(usuario);
        }
    }

}