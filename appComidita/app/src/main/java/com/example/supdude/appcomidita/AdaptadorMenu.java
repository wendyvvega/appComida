package com.example.supdude.appcomidita;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorMenu extends RecyclerView.Adapter<AdaptadorMenu.PaletteViewHolder>{
    private ArrayList<datosMenu> data;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;
    public int posicion;

    public AdaptadorMenu(@NonNull ArrayList<datosMenu> data,
                         @NonNull RecyclerViewOnItemClickListener
                                  recyclerViewOnItemClickListener) {
        this.data = data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public AdaptadorMenu.PaletteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.distribuyecomida, parent, false);
        return new AdaptadorMenu.PaletteViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull PaletteViewHolder holder, int position) {
        datosMenu menu = data.get(position);
        posicion = position;
        holder.getTitleTextView().setText(menu.getNombrePlato());
        holder.getSubtitleTextView().setText(menu.getDescripcion());
        holder.getCircleView().setImageResource(menu.getImagenComida());
        holder.getPriceTextView().setText(String.valueOf(menu.getPrecio()));
        holder.getCantidadEditText().setText(String.valueOf(menu.getCantidad()));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class PaletteViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private ImageView circleView;
        private TextView titleTextView;
        private TextView subtitleTextView;
        private TextView priceTextView;
        private EditText cantidadEditText;
        public ImageView mas;
        public ImageView menos;
        public int cantidad;



        public PaletteViewHolder(View itemView) {
            super(itemView);
            circleView = (ImageView) itemView.findViewById(R.id.imgPlato);
            titleTextView = (TextView) itemView.findViewById(R.id.txtNombrePlato);
            subtitleTextView = (TextView) itemView.findViewById(R.id.txtDescripcionComida);
            priceTextView = (TextView) itemView.findViewById(R.id.txtPrecio);
            cantidadEditText = (EditText) itemView.findViewById(R.id.txtCantidad);
            mas = (ImageView) itemView.findViewById(R.id.imgMas);
            menos = (ImageView) itemView.findViewById(R.id.imgMenos);

            mas.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    cantidad = Integer.parseInt(cantidadEditText.getText().toString());
                    cantidad++;
                    data.get(posicion).setCantidad(cantidad);
                    cantidadEditText.setText(""+String.valueOf(cantidad));

                }
            });
            menos.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    cantidad = Integer.parseInt(cantidadEditText.getText().toString());
                    if(cantidad>0){
                        cantidad--;
                        data.get(posicion).setCantidad(cantidad);
                        cantidadEditText.setText(""+String.valueOf(cantidad));
                    }
                }
            });

            itemView.setOnClickListener(this);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getSubtitleTextView() {
            return subtitleTextView;
        }

        public ImageView getCircleView() {
            return circleView;
        }

        public TextView getPriceTextView() {
            return priceTextView;
        }

        public EditText getCantidadEditText() {
            return cantidadEditText;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        @Override
        public void onClick(View v) {
            try {
                recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
            }
            catch (Exception e){}
        }
    }
}
