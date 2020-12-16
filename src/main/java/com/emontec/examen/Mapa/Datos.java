package com.emontec.examen.Mapa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.emontec.examen.ArchJson.Utils;
import com.emontec.examen.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Datos extends AppCompatActivity {

     EditText etFile;
     Button btnColab;
     Button btnAgregar;
    private static final String FILE_NAME = "/Download/employees_data.json";
private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        etFile = findViewById(R.id.etFile);
        btnColab =(Button) findViewById(R.id.btn_colaborador);
        btnAgregar = (Button) findViewById(R.id.btn_nuevo_colab);
  //      readFile();
        String jsonFileContent = null;
        try {
            jsonFileContent = Utils.leerJson(getApplicationContext(),"employees_data.json");
            JSONArray jsonArray = new JSONArray(jsonFileContent);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
               // int id= jsonObject.getInt("data");
                String name= jsonObject.getString("employees");
                Log.d("Nombre: ",name);
            }
        }catch (IOException | JSONException e){
            Log.e("error","");
            e.printStackTrace();
        }


        btnColab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Datos.this, Ubicaciones.class);
                startActivity(intent);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Datos.this, Registro.class);
                startActivity(intent);
            }
        });
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
              //  Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        myRef.setValue("Hello, World!");  */
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        SubirFireBase();

    }

    private void SubirFireBase() {
        fusedLocationProviderClient.getLastLocation().
                addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null ){
                Log.e("latitud",+ location.getLatitude()+" longitud "+ location.getLongitude());
                }
            }
        });
    }
/*
    private void readFile(){
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String lineaTexto;
            StringBuilder stringBuilder = new StringBuilder();
            while((lineaTexto = bufferedReader.readLine())!=null){
                stringBuilder.append(lineaTexto).append("\n");
            }
            etFile.setText(stringBuilder);
        }catch (Exception e){

        }finally {
            if(fileInputStream !=null){
                try {
                    fileInputStream.close();
                }catch (Exception e){

                }
            }
        }
    }
*/
}