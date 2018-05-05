package com.example.supdude.appcomidita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class listaLocales extends AppCompatActivity {

    ListView lista;
    ArrayList<local> listaDeLocales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locales);

        lista = findViewById(R.id.listaLocales);
        listaDeLocales = new ArrayList<local>();

        //llenarContactos(listaDeContactos);

        /*ArrayAdapter<contacto> adaptador = new ArrayAdapter<contacto>(this, R.layout.distribuyevistas,listaDeContactos);
        listView.setAdapter(adaptador);*/

        adaptador = new AdaptadorLocal(this, listaDeLocales);
        lista.setAdapter(adaptador);
    }
}
