package com.example.supdude.appcomidita;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registroUsuarios extends AppCompatActivity implements View.OnClickListener {
    private EditText nomb,apell,corr,contra,tel,repContra;
    private TextView done;
    private RadioButton sexoM,sexoH,sexoO;
    private RadioGroup grupoRadio;
    private ImageView showPass,atras;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro_usuarios);

        nomb= findViewById(R.id.nomRU);
        apell=findViewById(R.id.apRU);
        corr=findViewById(R.id.emailRU);
        contra=findViewById(R.id.passRU);
        repContra=findViewById(R.id.rePassRu);
        tel=findViewById(R.id.telRU);
        sexoH=findViewById(R.id.sxH);
        sexoM=findViewById(R.id.sxM);
        sexoO=findViewById(R.id.sxO);
        atras=findViewById(R.id.atrasRU);
       grupoRadio= findViewById(R.id.grupo);
        done=findViewById(R.id.hecho_RU);
        done.setOnClickListener(this);
        showPass=findViewById(R.id.muestraPassRU);
        showPass.setOnClickListener(this);
        auth= FirebaseAuth.getInstance();
            mFirebaseInstance = FirebaseDatabase.getInstance();
            // get reference to 'users' node
            mFirebaseDatabase = mFirebaseInstance.getReference("usuarios");


        }

    @Override
    public void onClick(View v) {



        String correo= corr.getText().toString();
        String pass=contra.getText().toString().trim();

                    switch ( v.getId() ){


                        case R.id.hecho_RU:
                            auth.createUserWithEmailAndPassword(correo,pass).addOnCompleteListener
                                    (registroUsuarios.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                   if(!task.isSuccessful()){
                                       FirebaseAuthException e = (FirebaseAuthException)task.getException();
                                       Toast.makeText(registroUsuarios.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                       return;
                                    }else{
                                       RadioButton seleccionado= findViewById(grupoRadio.getCheckedRadioButtonId());
                                       String nombre= nomb.getText().toString();
                                       String apellido=apell.getText().toString();
                                       String mail=corr.getText().toString();
                                       String telefono=tel.getText().toString();
                                       String sexo= seleccionado.getText().toString();
                                       String id_usuario = auth.getCurrentUser().getUid();
                                       DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(id_usuario);
                                       Map nuevoMapa = new HashMap();
                                       nuevoMapa.put("Nombre" ,nombre);
                                       nuevoMapa.put("Apellido" ,apellido);
                                       nuevoMapa.put("Correo" ,mail);
                                       nuevoMapa.put("Telefono" ,telefono);
                                       nuevoMapa.put("Sexo" ,sexo);
                                       dbRef.setValue(nuevoMapa);
                                       startActivity(new Intent (registroUsuarios.this,login.class));
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
