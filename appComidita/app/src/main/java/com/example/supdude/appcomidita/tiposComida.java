package com.example.supdude.appcomidita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class tiposComida extends AppCompatActivity implements View.OnClickListener{

    ImageView desayuno, comida, cena, cafebares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipos_comida);

        desayuno = findViewById(R.id.imgDesayuno);
        comida = findViewById(R.id.imgComida);
        cena = findViewById(R.id.imgCena);
        cafebares = findViewById(R.id.imgCafeBares);

        desayuno.setOnClickListener(this);
        comida.setOnClickListener(this);
        cena.setOnClickListener(this);
        cafebares.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(tiposComida.this, listaLocales.class);
        switch (v.getId()){
            case R.id.imgDesayuno:
                intent.putExtra("tipoComida", "desayuno");
                break;
            case R.id.imgComida:
                intent.putExtra("tipoComida", "comida");
                break;
            case R.id.imgCena:
                intent.putExtra("tipoComida", "cena");
                break;
            case R.id.imgCafeBares:
                intent.putExtra("tipoComida", "cafebares");
                break;
        }
        startActivity(intent);
    }
}
