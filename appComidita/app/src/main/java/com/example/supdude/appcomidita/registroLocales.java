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
        mFirebaseDatabase = mFirebaseInstance.getReference("Clientes");

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
                                    int tipoCuenta;
                                    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Locales").child("L");
                                    Map nuevoMapa = new HashMap();
                                    nuevoMapa.put("Nombre" ,nombre);

                                    nuevoMapa.put("Correo" ,mail);
                                    dbRef.setValue(nuevoMapa);
                                    startActivity(new Intent(registroLocales.this,login.class));
                                }
                            }
                        });
                break;
            case R.id.atrasRU:
                finish();
                break;


        }

    }
}
