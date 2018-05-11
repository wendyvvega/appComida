package com.example.supdude.appcomidita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorLocal extends BaseAdapter {
    Context contexto;

    public AdaptadorLocal(Context contexto, ArrayList<local> listaLocales) {
        this.contexto = contexto;
        this.listaLocales = listaLocales;
    }

    ArrayList<local> listaLocales;


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista = convertView;
        if(vista == null){
            LayoutInflater inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflador.inflate(R.layout.distribuyelocales, parent, false);
        }
        TextView tvNombre, tvDireccion;
        ImageView imagenLocal;

        tvNombre = vista.findViewById(R.id.txtNombreLocal);
        tvDireccion = vista.findViewById(R.id.txtDireccion);
        imagenLocal = vista.findViewById(R.id.imgLocal);

        tvNombre.setText(listaLocales.get(position).getNombre());
       /* tvTelefono.setText(listaLocales.get(position).getTelefono());
        tvEmail.setText(listaLocales.get(position).get());*/
        return vista;
    }
}
