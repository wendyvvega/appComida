package com.example.supdude.appcomidita;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class registroLocales extends AppCompatActivity implements View.OnClickListener{
    private TextView done;
    private ImageView atrasLo,img1,img2,img3,showPass;
    private EditText nomb,corr,pass,rePass,dir;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String pktOb;
    public static final int USE_ADDRESS_LOCATION = 2;
    private static final String TAG = "tag";

    int fetchType = USE_ADDRESS_LOCATION;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_locales);
        done =  findViewById(R.id.hechoLO);
        done.setOnClickListener(this);
        atrasLo= findViewById(R.id.atrasLO);
        atrasLo.setOnClickListener(this);
        img1=findViewById(R.id.addImgLO);
        img1.setOnClickListener(this);
        img2=findViewById(R.id.addImgLO1);
        img2.setOnClickListener(this);
        img3=findViewById(R.id.addImgLO2);
        img3.setOnClickListener(this);
        showPass=findViewById(R.id.muestra_passLO);
        showPass.setOnClickListener(this);
        nomb=findViewById(R.id.nomLO);
        corr=findViewById(R.id.emailLo);
        dir=findViewById(R.id.direccion);
        pass=findViewById(R.id.passLo);
        rePass=findViewById(R.id.rePassLo);

        auth= FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("Locales");
        Bundle obtenido=getIntent().getExtras();
        pktOb=obtenido.getString("paqueteElegido");


    }

    @Override
    public void onClick(View v) {


        final String correo= corr.getText().toString();
        String contras=pass.getText().toString().trim();

        switch ( v.getId() ){


            case R.id.hechoLO:
                final metodos m = new metodos();
                auth.createUserWithEmailAndPassword(correo,contras).addOnCompleteListener
                        (registroLocales.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    FirebaseAuthException e = (FirebaseAuthException)task.getException();
                                    Toast.makeText(registroLocales.this, "Registro fallido: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    return;
                                }else{
                                    String nombre= nomb.getText().toString();
                                    String mail=corr.getText().toString();
                                    String direccion="";

                                    String tel2="", sWeb="",tel1="",numInt="",numExt="",calle2="";
                                    String id_usuario = auth.getCurrentUser().getUid();
                                    String tipo1="LocalesBásicos",tipo2="LocalesPremium";
<<<<<<< HEAD
=======
                                    Context contexto = registroLocales.this;
                                    String direGeo= dir.getText().toString();
                                    String longitud = String.valueOf(m.getLocationFromAddress(contexto,direGeo).longitude);
                                    longitud=longitud.replace("\"","");
                                    String latitud =  String.valueOf(m.getLocationFromAddress(contexto,direGeo).latitude);
                                    latitud=latitud.replace("\"","");


>>>>>>> 41a510c63d64205ccb672075e1b5e944e048e50f
                                    int tipoCuenta=Integer.parseInt(pktOb);

                                     insertarBd(nombre,mail,direccion,tel1,tel2,sWeb,tipoCuenta,id_usuario,latitud,longitud);

                                    }

                                }
                            }
                        );
                break;
            case R.id.atrasRU:
                finish();
                break;


        }

    }
    private void insertarBd(String nombre, String mail, String direccion,  String tel1, String tel2,
                            String sWeb, int tipoCuenta, String id, String lat,String longitud){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference()
                .child("Locales").child(id);
        Map nuevoMapa = new HashMap();
        nuevoMapa.put("Nombre" ,nombre);
        nuevoMapa.put("Correo" ,mail);
        nuevoMapa.put("Dirección",direccion);
        nuevoMapa.put("Telefono1",tel1);
        nuevoMapa.put("Telefono2",tel2);
        nuevoMapa.put("SitioWeb",sWeb);
        nuevoMapa.put("TipoDeCuenta",tipoCuenta);
        dbRef.setValue(nuevoMapa);
        DatabaseReference dbUbi = FirebaseDatabase.getInstance().getReference().child("Locales")
                .child(id).child("Ubicacion");
        Map mUbi = new HashMap();
        mUbi.put("Latitud",lat);
        mUbi.put("Longitud",longitud);
        dbUbi.setValue(mUbi);

        startActivity(new Intent(registroLocales.this,login.class));

    }

}
