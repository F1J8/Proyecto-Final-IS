package Carreras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mohsin.mynavapp.R;

import adapters.SQLiteOpenHelper;
import repack.org.bouncycastle.openssl.PEMException;

public class AjustesCarreras extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    
    /**Declaraciones de variables utiles*/
    View vista;
    private TextInputLayout TI_ID,TI_Nombre,TI_Precio;
    private Spinner TipoSercivio;
    private String[]   serviciosOpciones = new String[] {"Seleccione una opción", "Tour", "Reserva en hotel", "Ejemplo"};
    private EditText IDservicio, NOMservicio, PREservicio;
    private CardView AgregarServicio, ModificarServicio, EliminarServicio, BuscarServicio;
    
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    
    private OnFragmentInteractionListener mListener;
    
    public AjustesCarreras() {
        // Required empty public constructor
    }
    
    
    // TODO: Rename and change types and number of parameters
    public static AjustesCarreras newInstance(String param1, String param2) {
        AjustesCarreras fragment = new AjustesCarreras();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ( getArguments() != null ) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_ajustes, container, false);

        /**Relacione TI*/
        TI_Nombre = vista.findViewById(R.id.ti_Nombre);
        TI_ID = vista.findViewById(R.id.ti_ID);
        TI_Precio = vista.findViewById(R.id.ti_Precio);

        /**EditText's*/
        IDservicio = vista.findViewById(R.id.et_ID);
        NOMservicio = vista.findViewById(R.id.et_Nombre);
        PREservicio = vista.findViewById(R.id.et_Precio);
        /**Spinner*/
        TipoSercivio = vista.findViewById(R.id.TipoServicio);
        TipoSercivio.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, serviciosOpciones));
        /**CardView's*/
        AgregarServicio = vista.findViewById(R.id.AgregarServicio);
        ModificarServicio = vista.findViewById(R.id.ModificarServicio);
        EliminarServicio = vista.findViewById(R.id.EliminarServicio);
        BuscarServicio = vista.findViewById(R.id.BuscarServicio);

        AgregarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarserv();
            }
        });

        ModificarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarserv();
            }
        });

        EliminarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarserv();
            }
        });

        BuscarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarserv();
            }
        });

        return vista;
    }

    /**Validaciones*/
    private boolean validateTipoServicio() {
        int tiposerv = TipoSercivio.getSelectedItemPosition();

        if (tiposerv == 0) {
            Toast.makeText(getContext(), "Debe seleccionar un tipo de servicio!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateIDservicio() {
        String idserv_Input = NOMservicio.getText().toString().trim();

        if (idserv_Input.isEmpty()) {
            TI_ID.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_ID.setError(null);
            return true;
        }
    }
    private boolean validateNOMservicio() {
        String nomserv_Input = NOMservicio.getText().toString().trim();

        if (nomserv_Input.isEmpty()) {
            TI_Nombre.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Nombre.setError(null);
            return true;
        }
    }
    private boolean validatePREservicio() {
        String preservicio_Input = PREservicio.getText().toString().trim();

        if (preservicio_Input.isEmpty()) {
            TI_Precio.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Precio.setError(null);
            return true;
        }
    }

    /**Fin Validaciones*/

    public void agregarserv(){
        //Validacion y registro de productos
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db= admin.getWritableDatabase();

        String tipo = TipoSercivio.getSelectedItem().toString();
        String id = IDservicio.getText().toString();
        String nombre = NOMservicio.getText().toString();
        String precio= PREservicio.getText().toString();


        if (!validateTipoServicio() | !validateNOMservicio() | !validateIDservicio() | !validatePREservicio()) {
            Toast.makeText(getContext(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            //return ;
        } else{

            ContentValues registro = new ContentValues();

            registro.put("tipo", tipo);
            registro.put("cod", id);
            registro.put("nombre", nombre);
            registro.put("precio", precio);

            db.insert("servicios", null, registro);

            Toast.makeText(getContext(),"Registro exitoso", Toast.LENGTH_SHORT).show();

            /**Variable para mostrar el Toast*/

            String input = "Tipo: " + TipoSercivio.getSelectedItem().toString();
            input += "\n";
            input += "ID: " + IDservicio.getText().toString();
            input += "\n";
            input += "Nombre: " + NOMservicio.getText().toString();
            input += "\n";
            input += "Precio: " + PREservicio.getText().toString();

            Toast.makeText(getContext(), input, Toast.LENGTH_LONG).show();

            db.close();
            //Verificar el limpiado de datos
            TipoSercivio.setId(0);
            IDservicio.setText("");
            NOMservicio.setText("");
            PREservicio.setText("");

        }
    }

    public void buscarserv(){
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id =IDservicio.getText().toString();

        if(!id.isEmpty()){
            Cursor fila = db.rawQuery
                    ("select cod, tipo, nombre, precio from servicios where cod=" +id, null);

            if(fila.moveToFirst()){
                IDservicio.setText(fila.getString(0));
                TipoSercivio.setPrompt(fila.getString(1));
                NOMservicio.setText(fila.getString(2));
                PREservicio.setText(fila.getString(3));

                db.close();
            } else {
                Toast.makeText(getContext(),"No existe el ID", Toast.LENGTH_SHORT).show();
                db.close();
                TipoSercivio.setId(0);
                IDservicio.setText("");
                NOMservicio.setText("");
                PREservicio.setText("");
            }

        } else {
            Toast.makeText(getContext(), "Debes introducir el ID...", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificarserv(){
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String tipo = TipoSercivio.getSelectedItem().toString();
        String id = IDservicio.getText().toString();
        String nombre = NOMservicio.getText().toString();
        String precio= PREservicio.getText().toString();

        if(!tipo.equals("Seleccione una opción") && !id.isEmpty() && !nombre.isEmpty() && !precio.isEmpty()){

            ContentValues registro = new ContentValues();

            registro.put("cod", id);
            registro.put("tipo", tipo);
            registro.put("nombre", nombre);
            registro.put("precio", precio);

            int cantidad = db.update("servicios", registro, "cod=" + id, null);
            db.close();
            /**Verificar el limpiado de datos*/
            TipoSercivio.setId(0);
            IDservicio.setText("");
            NOMservicio.setText("");
            PREservicio.setText("");

            if(cantidad == 1){
                Toast.makeText(getContext(), "Archivo modificado correctamente!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "El archivo no existe...", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getContext(), "Debes llenar todos los campos...", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarserv(){
        SQLiteOpenHelper admin = new SQLiteOpenHelper
                (getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = IDservicio.getText().toString();

        if(!id.isEmpty()){

            int cantidad = db.delete("servicios", "cod=" + id, null);
            db.close();

            /**Verificar el limpiado de datos*/
            TipoSercivio.setId(0);
            IDservicio.setText("");
            NOMservicio.setText("");
            PREservicio.setText("");

            if(cantidad == 1){
                Toast.makeText(getContext(), "Archivo eliminado exitosamente!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "El archivo no existe...", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getContext(), "Debes de introducir el DNI...", Toast.LENGTH_SHORT).show();
        }
    }
    
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if ( mListener != null ) {
            mListener.onFragmentInteraction(uri);
        }
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if ( context instanceof OnFragmentInteractionListener ) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    
    
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    
    
}
