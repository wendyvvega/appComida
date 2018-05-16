package com.example.supdude.appcomidita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class listaLocales extends AppCompatActivity {

    ArrayList<datosLocal> lista;
    //ArrayList<datosLocal> listaDeLocales;

    String opcion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locales);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

       // listaDeLocales = new ArrayList<>();

        Bundle  datos = getIntent().getExtras();
        opcion = datos.getString("tipoComida");

        lista= new ArrayList<datosLocal>();
        llenar(lista);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new AdaptadorLocal(lista, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast toast = Toast.makeText(listaLocales.this, String.valueOf(position), Toast.LENGTH_SHORT);
                int color = android.graphics.Color.parseColor(lista.get(position).getNombreLocal());
                toast.getView().setBackgroundColor(color);
                toast.show();
            }
        }));
        //VERTICAL
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //llenarContactos(listaDeContactos);

        /*ArrayAdapter<contacto> adaptador = new ArrayAdapter<contacto>(this, R.layout.distribuyevistas,listaDeContactos);
        listView.setAdapter(adaptador);*/

        /*adaptador = new AdaptadorLocal(this, listaDeLocales);
        lista.setAdapter(adaptador);*/
    }

    private void llenar(ArrayList lista) {

        lista.add(new datosLocal("Local1", "uno@gmail.com", "Enrique segoviano","111111"));
        lista.add(new datosLocal("Local2", "uno@gmail.com", "Enrique segoviano2","111111"));
        lista.add(new datosLocal("Local3", "uno@gmail.com", "Enrique segoviano3","111111"));
    }
}
