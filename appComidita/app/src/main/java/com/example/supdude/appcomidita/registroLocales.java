package com.example.supdude.appcomidita;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registroLocales extends AppCompatActivity implements View.OnClickListener{
    private TextView done;
    private ImageView atrasLo,img1,img2,img3,showPass;
    private EditText nomb,corr,pass,rePass;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String pktOb;


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

        String correo= corr.getText().toString();
        String contras=pass.getText().toString().trim();

        switch ( v.getId() ){


            case R.id.hechoLO:
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
                                    String calle1="";
                                    String tel2="", sWeb="",tel1="",numInt="",numExt="",calle2="";
                                    String id_usuario = auth.getCurrentUser().getUid();
                                    String tipo1="LocalesBásicos",tipo2="LocalesPremium";
                                    int tipoCuenta=Integer.parseInt(pktOb);
                                    if(tipoCuenta==1){
                                     insertarBd(nombre,mail,direccion,calle1,calle2,tel1,tel2,numInt,numExt,sWeb,tipoCuenta,tipo1,id_usuario);
                                    }else{
                                        if(tipoCuenta==2){
                                            insertarBd(nombre,mail,direccion,calle1,calle2,tel1,tel2,numInt,numExt,sWeb,tipoCuenta,tipo2,id_usuario);
                                        }
                                    }

                                }
                            }
                        });
                break;
            case R.id.atrasRU:
                finish();
                break;


        }

    }
    private void insertarBd(String nombre, String mail, String direccion, String calle1,
                            String calle2, String tel1, String tel2, String numInt, String numExt,
                            String sWeb, int tipoCuenta,String nomTipo, String id){
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference()
                .child("Locales").child(nomTipo).child(id);
        Map nuevoMapa = new HashMap();
        nuevoMapa.put("Nombre" ,nombre);
        nuevoMapa.put("Correo" ,mail);
        nuevoMapa.put("Dirección",direccion);
        nuevoMapa.put("Calle1",calle1);
        nuevoMapa.put("Calle2",calle2);
        nuevoMapa.put("Telefono1",tel1);
        nuevoMapa.put("Telefono2",tel2);
        nuevoMapa.put("NumInt",numInt);
        nuevoMapa.put("NumExt",numExt);
        nuevoMapa.put("SitioWeb",sWeb);
        nuevoMapa.put("TipoDeCuenta",tipoCuenta);
        dbRef.setValue(nuevoMapa);
        startActivity(new Intent(registroLocales.this,login.class));

    }
}
