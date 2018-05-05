package com.example.supdude.appcomidita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button sesion, registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sesion = findViewById(R.id.btnSesion);
        registrarse = findViewById(R.id.btnRegistrarse);
        sesion.setOnClickListener(this);
        registrarse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSesion:
                break;
            case R.id.btnRegistrarse:
                break;
        }
    }
}
