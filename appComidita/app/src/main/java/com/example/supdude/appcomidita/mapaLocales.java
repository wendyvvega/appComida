package com.example.supdude.appcomidita;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class mapaLocales extends FragmentActivity implements OnMapReadyCallback, LocationListener, View.OnClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private static final String TAG = "MapActivity";
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference rootRef=FirebaseDatabase.getInstance().getReference();
    private DatabaseReference refLocales=rootRef.child("Locales");
    private String userID;
    private LocationManager locationManager;
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    private LocationRequest locRequ;

    private LocationSource.OnLocationChangedListener mListener;
    private GoogleApiClient mGoogleApiClient;
    private Marker marcador;
    private ChildEventListener mChildEventListener;
    private LatLng currentLocationLatLong;
    private LatLng mochis = new LatLng(25.792806, -108.990188);
    private LatLngBounds LIMITE_LOSMOCHIS = new LatLngBounds(
            new LatLng(25.747227, -109.044912), new LatLng(25.837238, -108.941891)
);



    //widgets
    private AutoCompleteTextView barraBusqueda;
    private ImageView atrasM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_locales);

        barraBusqueda = findViewById(R.id.txt_busqueda);
        atrasM = findViewById(R.id.atrasMapa);
        atrasM.setOnClickListener(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        inicializarMapa();
        startGettingLocations();
        sendLocal();



    }


    private void inicializarMapa() {
        SupportMapFragment fragmentoMapa = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fragmentoMapa.getMapAsync(mapaLocales.this);
    }
private void sendLocal(){
    barraBusqueda.setOnEditorActionListener(new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId==EditorInfo.IME_ACTION_SEARCH){
                String nombreLugar=barraBusqueda.getText().toString();
               buscarPunto(nombreLugar);
            }return false;
        }
    });
}
 private void buscarPunto(String lugarB){
      DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
      DatabaseReference refNom=ref.child("Locales");


    refNom.orderByChild("Nombre").equalTo(lugarB).addListenerForSingleValueEvent(new ValueEventListener() {
    String lon,lat;
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        for(DataSnapshot nodo : dataSnapshot.getChildren() ) {
            lon= nodo.child("Ubicacion").child("Longitud").getValue(String.class);
           lat = nodo.child("Ubicacion").child("Latitud").getValue(String.class);
        }
        LatLng goUbicacion = new LatLng(Double.parseDouble(lat),Double.parseDouble(lon));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(goUbicacion)      // Sets the center of the map to Mountain View
                .zoom(18)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        }


    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});


 }

 public void acompletar(){


     barraBusqueda.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

                 consultaNombresLocales(s.toString());


         }


         @Override
         public void afterTextChanged(Editable s) {

         }
     });


 }

 public void consultaNombresLocales(final String texto ){
     final  ArrayList<String> nombresLoc = new ArrayList <> ();
    refLocales.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                String nombreLocal = ds.child("Nombre").getValue(String.class);
                ArrayList <String> llenadodeLista = new ArrayList<>();
                llenadodeLista.add(nombreLocal);
                for (int i = 0; i < llenadodeLista.size(); i++) {
                    for (int j=0; j < texto.length(); j++) {
                        if (llenadodeLista.get(i).charAt(0) == texto.charAt(j)) {
                            nombresLoc.add(nombreLocal);
                        }
                    }
                }

            }
            ArrayAdapter autoComplete = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,nombresLoc);
            barraBusqueda.setAdapter(autoComplete);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });


 }

    private void centrarMapa() {

        Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        if(location==null){
            Toast.makeText(this, "KHEB ERGA", Toast.LENGTH_SHORT).show();
        }
        LatLng coordinate = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(location.getLatitude(), location.getLongitude()))
                .title("Ubicación Actual"));
        mMap.setMinZoomPreference(13.0f);
        mMap.setLatLngBoundsForCameraTarget(LIMITE_LOSMOCHIS);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(coordinate)      // Sets the center of the map to Mountain View
                .zoom(18)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



    }



    private void hideSoftKeyboard() {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {

            return;
        }


        refLocales.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot nodo : dataSnapshot.getChildren() ){
                    String lon= nodo.child("Ubicacion").child("Longitud").getValue(String.class);
                    String lat = nodo.child("Ubicacion").child("Latitud").getValue(String.class);
                    String nomLocal= nodo.child("Nombre").getValue(String.class);
                    Log.d("TA",""+lon);
                    LatLng paraMarcador = new LatLng(Double.parseDouble(lat),Double.parseDouble(lon));
                    mMap.addMarker(new MarkerOptions().position(paraMarcador).title(nomLocal));

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(mapaLocales.this,
                         "Ocurrió un problema, por favor, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        });

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().isRotateGesturesEnabled();
        mMap.getUiSettings().isZoomGesturesEnabled();
        centrarMapa();
        acompletar();

    }




    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("GPS desactivado!");
        alertDialog.setMessage("Activar GPS?");
        alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }


    private void startGettingLocations() {

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        boolean isGPS = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetwork = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean canGetLocation = true;
        int ALL_PERMISSIONS_RESULT = 101;
        long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;// Distance in meters
        long MIN_TIME_BW_UPDATES = 1000 * 10;// Time in milliseconds

        ArrayList<String> permissions = new ArrayList<>();
        ArrayList<String> permissionsToRequest;

        permissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);

        //Check if GPS and Network are on, if not asks the user to turn on
        if (!isGPS && !isNetwork) {
            showSettingsAlert();
        } else {
            // check permissions

            // check permissions for later versions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest.size() > 0) {
                    requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                            ALL_PERMISSIONS_RESULT);
                    canGetLocation = false;
                }
            }
        }


        //Checks if FINE LOCATION and COARSE Location were granted
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(this, "Permiso Denegado", Toast.LENGTH_SHORT).show();
            return;
        }

        //Starts requesting location updates
        if (canGetLocation) {
            if (isGPS) {
                lm.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            } else if (isNetwork) {
                // from Network Provider

                lm.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

            }
        } else {
            Toast.makeText(this, "No se posible obtener la localización", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.atrasMapa:
                finish();
                break;
        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}


