package com.example.supdude.appcomidita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class local extends AppCompatActivity {
    ArrayList<datosMenu> lista;
    EditText presupuesto, direc, horario, catego, tel, sitio;
    TextView nombreLocal;
    ImageView imagenLocal, mapa;

    String local="";
    double total=0.0;
    int cantidad;
    TabHost tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        tabs = findViewById(R.id.tabhost);

        tabs.setup();
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Información");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Menú");
        tabs.addTab(spec);

        nombreLocal = findViewById(R.id.txtLocal);
        imagenLocal = findViewById(R.id.imgImagenLocal);
        presupuesto = findViewById(R.id.txtTotal);
        mapa = findViewById(R.id.imagenMapa);
        direc = findViewById(R.id.editDirec);
        horario = findViewById(R.id.editHorario);
        catego = findViewById(R.id.editCatego);
        tel = findViewById(R.id.editTelefono);
        tel.setCompoundDrawables(getResources().getDrawable(R.drawable.tel), null, null, null);
        sitio = findViewById(R.id.editSitio);
        sitio.setCompoundDrawables(getResources().getDrawable(R.drawable.world), null, null, null);

        Bundle  datos = getIntent().getExtras();
        final datosLocal objeto = (datosLocal)datos.getSerializable("objeto");

        nombreLocal.setText(objeto.getNombreLocal());
        imagenLocal.setImageResource(objeto.getLogo());
        direc.setText(objeto.getDireccion());
        horario.setText(objeto.getHorario());
        catego.setText(datos.getString("tipoComida"));
        tel.setText(objeto.getTel1());
        sitio.setText(objeto.getSitio());

        lista= new ArrayList<datosMenu>();
        llenar(lista);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler2);
        recyclerView.setAdapter(new AdaptadorMenu(lista, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(local.this, lista.get(position).getNombrePlato(), Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(listaLocales.this, local.class);
                //i.putExtra("objeto",  lista.get(position));
                //i.putExtra("posicion", position);
                //startActivity(i);

                cantidad = ((Integer) lista.get(position).getCantidad());
                Toast.makeText(local.this, String.valueOf(cantidad), Toast.LENGTH_SHORT).show();
                total = total + (cantidad * lista.get(position).getPrecio());
                presupuesto.setText(cantidad);
            }
        }));
        //VERTICAL
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void llenar(ArrayList lista) {
        lista.add(new datosMenu("Pizza", "Familiar de 8 trozos", 1, R.drawable.littlecaesars,0));
        lista.add(new datosMenu("Pizza2", "Familiar de 8 trozos", 2, R.drawable.littlecaesars,0));
        lista.add(new datosMenu("Pizza3", "Familiar de 8 trozos", 3, R.drawable.littlecaesars,0));
        lista.add(new datosMenu("Pizza4", "Familiar de 8 trozos", 4, R.drawable.littlecaesars,0));
    }
}
