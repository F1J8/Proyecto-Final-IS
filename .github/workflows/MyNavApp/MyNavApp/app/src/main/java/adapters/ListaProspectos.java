package adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohsin.mynavapp.R;

import java.util.ArrayList;

public class ListaProspectos extends RecyclerView.Adapter<ListaProspectos.ProspectosViewHolder> {


    ArrayList<Usuarios> listaProsp;

        public ListaProspectos(ArrayList<Usuarios> listaProsp){
            this.listaProsp = listaProsp;
        }

    @NonNull
    @Override
    public ListaProspectos.ProspectosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null, false);
        return new ProspectosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaProspectos.ProspectosViewHolder holder, int i) {
        holder.tvCedula.setText(listaProsp.get(i).getDNI());
        holder.tvNombre.setText(listaProsp.get(i).getNombreE() + " " + listaProsp.get(i).getApellido());
        holder.tvTelefono.setText(listaProsp.get(i).getTelefono());
    }

    @Override
    public int getItemCount() {
        return listaProsp.size();
    }

    public class ProspectosViewHolder extends RecyclerView.ViewHolder {
            TextView tvNombre, tvTelefono, tvCedula;

        public ProspectosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvCedula = itemView.findViewById(R.id.tvCedula);

        }
    }
}
