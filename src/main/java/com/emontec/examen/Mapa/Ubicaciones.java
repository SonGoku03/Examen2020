package com.emontec.examen.Mapa;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.emontec.examen.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class Ubicaciones extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;
    String mensaje;
    String direccion = "Gardenias 13";

    Button Lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicaciones);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Lista =(Button) findViewById(R.id.lista);

insert();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();
        Lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //               Toast.makeText(getApplicationContext(), direccion, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(Ubicaciones.this, Lista.class);
                intent.putExtra("direccion", (String) direccion);

                DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
                simbolo.setDecimalSeparator('.');
                simbolo.setGroupingSeparator(',');
                DecimalFormat formateador = new DecimalFormat("###,###.00",simbolo);

                String lati2=(formateador.format(lat));
                String lgn2=(formateador.format(lng));
                intent.putExtra("latitud", (String) lati2);
                intent.putExtra("longitud", (String) lgn2);
                startActivity(intent);
            }
        });
        // Add a marker in Sydney and move the camera
    }

    //activar los servicio delñ gps cuando este apagado
    private void locationStart() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }

    }

    public void setLocation(Location loc) {
        //obtiene la direccion de la calle apartir de la latitud y longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);

                    // Toast.makeText(getApplicationContext(), direccion, Toast.LENGTH_LONG).show();
                    direccion = (DirCalle.getAddressLine(0));
                    Lista.setEnabled(true);
                    Lista.setText("Continuar");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //agregar marcador al mama
    private void AgregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate MiUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Direccion" + direccion)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map)));
        mMap.animateCamera(MiUbicacion);
    }

    //actualizar marcador en el mapa
    private void ActualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            AgregarMarcador(lat, lng);
        }
    }

    //control delGPS
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            ActualizarUbicacion(location);
            setLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            mensaje = ("GPS Activado");
            Mensaje();
        }

        @Override
        public void onProviderDisabled(String provider) {
            mensaje = ("GPS Desactivado");
            locationStart();
            Mensaje();
        }
    };

    private static int PETICION_PERMISO_LOCALIZACION = 101;

    private void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PETICION_PERMISO_LOCALIZACION);
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location locatiom = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        ActualizarUbicacion(locatiom);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1200,0,locationListener);
        // if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ))
    }

    public void Mensaje(){
        Toast toast= Toast.makeText(this,mensaje,Toast.LENGTH_LONG);
        toast.show();
    }

    public void insert(){
        SharedPreferences misPreferencias = getSharedPreferences("Regis", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =  misPreferencias.edit();
        editor.putString("bandera","0");
        editor.commit();

    }

}
