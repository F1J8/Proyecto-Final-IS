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

public class ProspAdaptapter extends RecyclerView.Adapter<ProspAdaptapter.ProspViewHolder> {
    private Context mcontext;
    private Cursor mcursor;


    public ProspAdaptapter(Context context, Cursor cursor){
        mcontext = context;
        mcursor = cursor;
    }


    public class ProspViewHolder extends RecyclerView.ViewHolder{

        public TextView txtnombre, txtcedula, txttelefono, txtcorreo;


        public ProspViewHolder(@NonNull View itemView) {
            super(itemView);
            txtcedula = itemView.findViewById(R.id.tvCedula);
            txtcorreo = itemView.findViewById(R.id.tvCorreo);
            txtnombre = itemView.findViewById(R.id.tvNombre);
            txttelefono = itemView.findViewById(R.id.tvTelefono);

        }
    }

    @NonNull
    @Override
    public ProspViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View v = inflater.inflate(R.layout.item_list,viewGroup,false);

        return new ProspViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProspViewHolder holder, int i) {
        if(!mcursor.moveToPosition(i)){
            return;
        }
        String cedula = mcursor.getString(0);
        String nombre = mcursor.getString(1);
        String apellido = mcursor.getString(2);
        String telefono = mcursor.getString(3);
        String correo = mcursor.getString(4);

        holder.txtnombre.setText(nombre +""+ apellido);
        holder.txttelefono.setText(telefono);
        holder.txtcorreo.setText(correo);
        holder.txtcedula.setText(cedula);
    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }
public void sCursor(Cursor ncursor){
        if(mcursor != null){
            mcursor.close();
        }mcursor=ncursor;
        if(ncursor != null){
            notifyDataSetChanged();
        }
}

    }

