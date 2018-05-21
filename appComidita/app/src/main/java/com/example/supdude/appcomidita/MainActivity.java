package com.example.supdude.appcomidita;

import android.app.Application;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button sesion, registrarse;
    LinearLayout opcionesReg;
    ImageView regUs,regLoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseOptions.Builder builder = new FirebaseOptions.Builder()
                .setApplicationId("1:783313982016:android:ca6cfd1819cf1b77")
                .setApiKey("AIzaSyBnzisdF-UeKRAw7Ef3hHi7Y8gc4N1ZP1o")
                .setDatabaseUrl("https://foodseeker-1.firebaseio.com/")
                .setStorageBucket("foodseeker-1.appspot.com");
        FirebaseApp.initializeApp(this, builder.build());
        setContentView(R.layout.activity_main);

        sesion = findViewById(R.id.btnSesion);
        registrarse = findViewById(R.id.btnRegistrarse);
        opcionesReg=findViewById(R.id.opcRegistro);
        regUs=findViewById(R.id.regUsuario);
        regLoc=findViewById(R.id.regLocal);
        sesion.setOnClickListener(this);
        registrarse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSesion:
                startActivity(new Intent(MainActivity.this,login.class));
                break;
            case R.id.btnRegistrarse:
                opcionesReg.setVisibility(View.VISIBLE);
                break;
            case R.id.regUsuario:
                startActivity(new Intent (MainActivity.this,registroUsuarios.class));
                break;
            case R.id.regLocal:
                startActivity(new Intent (MainActivity.this,paquetes.class));
                break;
        }
    }
}
