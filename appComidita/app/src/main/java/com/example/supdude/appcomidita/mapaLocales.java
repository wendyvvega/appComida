package com.example.supdude.appcomidita;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class mapaLocales extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION=Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION=Manifest.permission.ACCESS_FINE_LOCATION;
    private static final int CODIGO_REQUEST=1234;
    private static final float DEFAULT_ZOOM=15;
    private Boolean permisoOtorgado=false;
    private FusedLocationProviderClient mFuseLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_locales);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private  void inicializarMapa(){
        SupportMapFragment fragmentoMapa= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fragmentoMapa.getMapAsync(mapaLocales.this);
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
    private void obtenerLocDispositivo(){
        mFuseLocation = LocationServices.getFusedLocationProviderClient(this);
        try{
            if(permisoOtorgado){
                final Task localizacion = mFuseLocation.getLastLocation();
                localizacion.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "Se encontró la localización");
                            Location localActual= (Location) task.getResult();
                                moverCamara(new LatLng(localActual.getAltitude(),localActual.getLongitude()),DEFAULT_ZOOM);
                        }else{
                            Log.d(TAG, "No se pudo enontrar la localización");
                        }

                    }
                });
            }

        }catch(SecurityException e){
            Log.e( TAG , "obtenerLocalizaciónDelDispositivo: Excepción de seguridad: "+ e.getMessage());
        }
    }


    private void moverCamara (LatLng latlng, float zoom){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,zoom));
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(permisoOtorgado){
            obtenerLocDispositivo();
            if(ActivityCompat.checkSelfPermission
                    (this,Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

        }

            }





    private void permisoLocalizacion(){
        String [] permisos = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == getPackageManager().PERMISSION_GRANTED){
            inicializarMapa();
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    FINE_LOCATION) == getPackageManager().PERMISSION_GRANTED){
                    permisoOtorgado=true;
            }else{
                ActivityCompat.requestPermissions(this,permisos,CODIGO_REQUEST);
            }
        }else{
            ActivityCompat.requestPermissions(this,permisos,CODIGO_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permisoOtorgado=false;
        switch (requestCode){


            case CODIGO_REQUEST:
                if(grantResults.length>0){
                    for (int i = 0; i< grantResults.length;i++){
                        permisoOtorgado=false;
                        return;
                    }
                }
                permisoOtorgado=true;
                inicializarMapa();

        }
    }
}
