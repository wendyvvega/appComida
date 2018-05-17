package com.example.supdude.appcomidita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class listaLocales extends AppCompatActivity {

    ArrayList<datosLocal> lista;
    TextView tipoComida;

    String opcion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_locales);

        tipoComida = findViewById(R.id.txtComida);

        Bundle  datos = getIntent().getExtras();
        opcion = datos.getString("tipoComida");
        tipoComida.setText(opcion);

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

        //HORIZONTAL
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this));

    }

    private void llenar(ArrayList lista) {
        int [] arregloBandera = {R.drawable.cena};
        lista.add(new datosLocal("Little Caesars", "www.littlecaesars.com", "Enrique segoviano", R.drawable.littlecaesars));
        lista.add(new datosLocal("Local2", "uno@gmail.com", "Enrique segoviano2","111111"));
        lista.add(new datosLocal("Local3", "uno@gmail.com", "Enrique segoviano3","111111"));
    }
}
