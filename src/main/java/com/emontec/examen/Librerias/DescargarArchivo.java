package com.emontec.examen.Librerias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.widget.Toast;

import com.emontec.examen.ArchJson.Descomprimir;
import com.emontec.examen.R;

public class DescargarArchivo extends AppCompatActivity {
    private static final int PERMISOS_STORAGE_CODE = 1000 ;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descargar_archivo);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        if(isOnline(getApplicationContext())){
            Toast.makeText(getApplicationContext(),"Tiene conexion a internet",Toast.LENGTH_LONG).show();
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                    //permisos denegados
                    String[] permisos = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permisos,PERMISOS_STORAGE_CODE);
                }else{
                    //persmisos de escritura
                    Descargar();


                }
            }else{
                //sistema empieza la descarga
                Descargar();
            }
        }
        Toast.makeText(getApplicationContext(),"No tiene conexion a internet",Toast.LENGTH_LONG).show();
    }

    private void Descargar() {
String url = "https://firebasestorage.googleapis.com/v0/b/example-e6943.appspot.com/o/employees_data.json.zip?alt=media&token=02daec6d-cd37-48eb-bfa5-da5862f40b97";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Descargando...");
        request.setDescription("Descargando archivo");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Archivo.zip");

        DownloadManager manager= (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(DescargarArchivo.this, Descomprimir.class);
                startActivity(intent);
            }
        }, 3700);

    }

    //Función para validar conexión a una red de internet
    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permisos,@NonNull int[] grantResults){
         switch (requestCode){
             case  PERMISOS_STORAGE_CODE:{
                 if(grantResults.length> 0 && grantResults [0] == PackageManager.PERMISSION_GRANTED){
                     Descargar();
                 }
                 else{
                     //Permisos denegados
                     Toast.makeText(DescargarArchivo.this,"Permisos Denegados",Toast.LENGTH_LONG).show();

                 }
             }
         }
    }
}
