package com.example.supdude.appcomidita;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class listaLocales extends AppCompatActivity implements Serializable, View.OnClickListener {

    ArrayList<datosLocal> lista;
    TextView tipoComida;
    ImageView mapa;

    String opcion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locales);

        tipoComida = findViewById(R.id.txtComida);
        mapa =findViewById(R.id.imagenMapa);
        mapa.setOnClickListener(this);

        Bundle  datos = getIntent().getExtras();
        opcion = datos.getString("tipoComida");
        tipoComida.setText(opcion);

        lista= new ArrayList<datosLocal>();
        llenar(lista);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new AdaptadorLocal(lista, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(listaLocales.this, lista.get(position).getNombreLocal(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(listaLocales.this, local.class);
                i.putExtra("objeto",  lista.get(position));
                //i.putExtra("posicion", position);
                i.putExtra("tipoComida", opcion);
                startActivity(i);
            }
        }));
        //VERTICAL
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //HORIZONTAL
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this));

    }

    private void llenar(ArrayList lista) {
        int [] arregloBandera = {R.drawable.cena};
        lista.add(new datosLocal("Little Caesars", "www.littlecaesars.com",
                "Enrique segoviano", R.drawable.littlecaesars, "8129000", "Lunes-Domingo 11AM-10PM"));
        lista.add(new datosLocal("Local2", "uno@gmail.com", "Enrique segoviano2","111111"));
        lista.add(new datosLocal("Local3", "uno@gmail.com", "Enrique segoviano3","111111"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imagenMapa:
                break;
        }
    }
}
