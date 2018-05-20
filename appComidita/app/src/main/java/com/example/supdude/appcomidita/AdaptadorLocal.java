package com.example.supdude.appcomidita;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorLocal extends RecyclerView.Adapter<AdaptadorLocal.PaletteViewHolder> {
    private ArrayList<datosLocal> data;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public AdaptadorLocal(@NonNull ArrayList<datosLocal> data,
                          @NonNull RecyclerViewOnItemClickListener
                                          recyclerViewOnItemClickListener) {
        this.data = data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public PaletteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.distribuyelocales, parent, false);
        return new PaletteViewHolder(row);
    }

    @Override
    public void onBindViewHolder(PaletteViewHolder holder, int position) {
        datosLocal local = data.get(position);
        holder.getTitleTextView().setText(local.getNombreLocal());
        holder.getSubtitleTextView().setText(local.getDireccion());
        holder.getCircleView().setImageResource(local.getLogo());

        /*GradientDrawable gradientDrawable = (GradientDrawable) holder.getCircleView().getBackground();
        int colorId = android.graphics.Color.parseColor(local.getHex());
        gradientDrawable.setColor(colorId);*/
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


        public PaletteViewHolder(View itemView) {
            super(itemView);
            circleView = (ImageView) itemView.findViewById(R.id.imgLocal);
            titleTextView = (TextView) itemView.findViewById(R.id.txtNombreLocal);
            subtitleTextView = (TextView) itemView.findViewById(R.id.txtDireccion);
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

        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
