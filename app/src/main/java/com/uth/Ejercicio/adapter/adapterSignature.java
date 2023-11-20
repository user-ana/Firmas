package com.uth.Ejercicio.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uth.Ejercicio.models.cSignature;
import com.uth.e24.R;


import java.util.ArrayList;

public class adapterSignature extends RecyclerView.Adapter<adapterSignature.adapterSignatureViewHolder>
{
    private ArrayList<cSignature> oSignatureList;

    public adapterSignature(ArrayList<cSignature> oSignatureList) {
        this.oSignatureList = oSignatureList;
    }

    @NonNull
    @Override
    public adapterSignatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_signature,parent,false);
        return new adapterSignatureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterSignatureViewHolder holder, int position)
    {
        holder.oTextView.setText(oSignatureList.get(position).getDescripcion());
        Log.e("FIRMA",oSignatureList.get(position).getFirma_digital().toString());
        holder.oSignatureView.setImageBitmap(oSignatureList.get(position).getFirma_digital());
    }

    @Override
    public int getItemCount() {
        return oSignatureList.size();
    }

    public class adapterSignatureViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView oSignatureView;
        private TextView oTextView;
        public adapterSignatureViewHolder(@NonNull View itemView)
        {
            super(itemView);
            oSignatureView = itemView.findViewById(R.id.signature_view_list);
            oTextView = itemView.findViewById(R.id.txtDetalleSignatureList);
        }
    }
}
