package com.emontec.examen.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emontec.examen.Modelo.Colaboradores;
import com.emontec.examen.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by CHENAO on 8/07/2017.
 */

public class ListaColaboradores extends RecyclerView.Adapter<ListaColaboradores.PersonasViewHolder> {

    ArrayList<Colaboradores> listaClientes;

    public ListaColaboradores(ArrayList<Colaboradores> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_colaboradores,null,false);
        return new PersonasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.nombre.setText(listaClientes.get(position).getNombre().toString());
        holder.correo.setText(listaClientes.get(position).getCorreo());
        holder.longitud.setText(listaClientes.get(position).getLongitud());
        holder.latitud.setText(listaClientes.get(position).getLatitud());

    }

    @Override
    public int getItemCount() {
        return listaClientes.size();
    }

    public class PersonasViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,correo,longitud,latitud;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre_colaborador);
            correo = (TextView) itemView.findViewById(R.id.nombre_correo);
            longitud = (TextView) itemView.findViewById(R.id.longitud);
            latitud = (TextView) itemView.findViewById(R.id.latitud);

        }
    }
}
