package com.example.supdude.appcomidita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity implements View.OnClickListener{

    EditText usuario, contraseña;
    Button inicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.txtUsuario);
        contraseña = findViewById(R.id.txtContra);
        inicio = findViewById(R.id.btnIniciarSesion);
        inicio.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnIniciarSesion:
                break;
        }
    }
}
