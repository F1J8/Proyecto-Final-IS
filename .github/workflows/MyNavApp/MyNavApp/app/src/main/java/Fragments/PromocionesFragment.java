package Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mohsin.mynavapp.R;

import adapters.SQLiteOpenHelper;

import static java.lang.Boolean.FALSE;


public class PromocionesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /**Declaraciones de variables utiles*/
    View vista;
    private TextInputLayout TI_ID,TI_Titulo,TI_Duracion, TI_UInicial, TI_UFinal, TI_Fecha, TI_Precio;
    private Spinner TipoPromocion;
    private String[]   promocionesOpciones = new String[] {"Seleccione una opción", "Evento especial", "Gira", "Gira educativa"};
    private String Tipo="";
    private EditText IDpromocion, TITpromocion, DURpromocion, UINIpromocion, UFINpromocion, FECHApromocion, PRECIOpromocion;
    private CardView AgregarPromocion, ModificarPromocion, EliminarPromocion, BuscarPromocion;
    private CheckBox espanol, ingles;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PromocionesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PromocionesFragment newInstance(String param1, String param2) {
        PromocionesFragment fragment = new PromocionesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_promociones, container, false);

        /**Relacione TI*/
        TI_Titulo = vista.findViewById(R.id.ti_Titulo);
        TI_ID = vista.findViewById(R.id.ti_IDP);
        TI_Duracion = vista.findViewById(R.id.ti_Duracion);
        TI_UInicial = vista.findViewById(R.id.ti_UbicacionI);
        TI_UFinal = vista.findViewById(R.id.ti_UbicacionF);
        TI_Fecha = vista.findViewById(R.id.ti_Fecha);
        TI_Precio = vista.findViewById(R.id.ti_Precio);

        /**EditText's*/
        TITpromocion = vista.findViewById(R.id.et_Titulo);
        IDpromocion = vista.findViewById(R.id.et_IDP);
        DURpromocion = vista.findViewById(R.id.et_Duracion);
        UINIpromocion = vista.findViewById(R.id.et_UbicacionI);
        UFINpromocion = vista.findViewById(R.id.et_UbicacionF);
        FECHApromocion = vista.findViewById(R.id.et_Fecha);
        PRECIOpromocion = vista.findViewById(R.id.et_Precio);
        /**Spinner*/
        TipoPromocion = vista.findViewById(R.id.TipoPromocion);
        TipoPromocion.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, promocionesOpciones));
        TipoPromocion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                Tipo = (String)adapterView.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /**CardView's*/
        AgregarPromocion = vista.findViewById(R.id.AgregarPromocion);
        ModificarPromocion = vista.findViewById(R.id.ModificarPromocion);
        EliminarPromocion = vista.findViewById(R.id.EliminarPromocion);
        BuscarPromocion = vista.findViewById(R.id.BuscarPromocion);
        /**CheckBox's*/
        espanol = vista.findViewById(R.id.esp);
        ingles = vista.findViewById(R.id.ing);

        AgregarPromocion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarpromo();
            }
        });

        ModificarPromocion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarpromo();
            }
        });

        EliminarPromocion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarpromo();
            }
        });

        BuscarPromocion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarpromo();
            }
        });

        return vista;
    }

    /**Validaciones*/
    private boolean validateTipoPromocion() {
       if (Tipo.equals("Seleccione una opción")) {
            Toast.makeText(getContext(), "Seleccione una opción...", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateIDpromocion() {
        String idpromocion_Input = IDpromocion.getText().toString().trim();

        if (idpromocion_Input.isEmpty()) {
            TI_ID.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_ID.setError(null);
            return true;
        }
    }
    private boolean validateTITpromocion() {
        String tittulopromocion_Input = TITpromocion.getText().toString().trim();

        if (tittulopromocion_Input.isEmpty()) {
            TI_Titulo.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Titulo.setError(null);
            return true;
        }
    }
    private boolean validateDURpromocion() {
        String duracionpromocion_Input = DURpromocion.getText().toString().trim();

        if (duracionpromocion_Input.isEmpty()) {
            TI_Duracion.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Duracion.setError(null);
            return true;
        }
    }
    private boolean validateUINIpromocion() {
        String uinipromocion_Input = UINIpromocion.getText().toString().trim();

        if (uinipromocion_Input.isEmpty()) {
            TI_UInicial.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_UInicial.setError(null);
            return true;
        }
    }
    private boolean validateUFINpromocion() {
        String ufinpromocion_Input = UFINpromocion.getText().toString().trim();

        if (ufinpromocion_Input.isEmpty()) {
            TI_UFinal.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_UFinal.setError(null);
            return true;
        }
    }
    private boolean validateFECHApromocion() {
        String fechapromocion_Input = FECHApromocion.getText().toString().trim();

        if (fechapromocion_Input.isEmpty()) {
            TI_Fecha.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Fecha.setError(null);
            return true;
        }
    }
    private boolean validatePRECIOpromocion() {
        String preciopromocion_Input = PRECIOpromocion.getText().toString().trim();

        if (preciopromocion_Input.isEmpty()) {
            TI_Precio.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Precio.setError(null);
            return true;
        }
    }
    private boolean validateESPpromocion() {
        Boolean esppromocion_Input = espanol.isChecked();

        if (esppromocion_Input == FALSE){
            espanol.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            espanol.setError(null);
            return true;
        }
    }
    private boolean validateINGpromocion() {
        Boolean ingpromocion_Input = ingles.isChecked();

        if (ingpromocion_Input == FALSE){
            ingles.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            ingles.setError(null);
            return true;
        }
    }
    /**Fin Validaciones*/

    public void agregarpromo(){
        //Validacion y registro de productos
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db= admin.getWritableDatabase();


        String id = IDpromocion.getText().toString();
        String titulo = TITpromocion.getText().toString();
        String duracion= DURpromocion.getText().toString();
        String inicial= UINIpromocion.getText().toString();
        String finall = UFINpromocion.getText().toString();
        String fecha= FECHApromocion.getText().toString();
        String precio = PRECIOpromocion.getText().toString();
        String idioma="";

        if (!validateTipoPromocion() | !validateIDpromocion() | !validateTITpromocion() | !validateDURpromocion() | !validateUINIpromocion() | !validateUFINpromocion()
                | !validateFECHApromocion() | !validatePRECIOpromocion()) {
            Toast.makeText(getContext(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            //return ;
        } else{

            ContentValues registro = new ContentValues();

            if(espanol.isChecked()){
                idioma = "español";
            }
            else if(ingles.isChecked()){
                idioma = "inglés";
            }

            registro.put("cod", id);
            registro.put("tipo", Tipo);
            registro.put("titulo", titulo);
            registro.put("duracion", duracion);
            registro.put("ubic_inicial", inicial);
            registro.put("ubic_final", finall);
            registro.put("fecha", fecha);
            registro.put("precio", precio);
            registro.put("idioma", idioma);

            db.insert("promociones", null, registro);

            Toast.makeText(getContext(),"Registro exitoso", Toast.LENGTH_SHORT).show();

            /**Variable para mostrar el Toast*/

            String input = "ID: " + IDpromocion.getText().toString();
            input += "\n";
            input = "Tipo: " + Tipo;
            input += "\n";
            input += "Titulo: " + TITpromocion.getText().toString();
            input += "\n";
            input += "Duración: " + DURpromocion.getText().toString();
            input += "\n";
            input += "Ub. Inicial: " + UINIpromocion.getText().toString();
            input += "\n";
            input += "Ub. Final: " + UFINpromocion.getText().toString();
            input += "\n";
            input += "Fecha: " + FECHApromocion.getText().toString();
            input += "\n";
            input += "precio: " + PRECIOpromocion.getText().toString();
            input += "\n";
            input += "Idioma: " + idioma;

            Toast.makeText(getContext(), input, Toast.LENGTH_LONG).show();

            db.close();
            //Verificar el limpiado de datos
            TipoPromocion.setSelection(0);
            IDpromocion.setText("");
            TITpromocion.setText("");
            DURpromocion.setText("");
            UINIpromocion.setText("");
            UFINpromocion.setText("");
            FECHApromocion.setText("");
            PRECIOpromocion.setText("");
            if(espanol.isChecked()){
                espanol.setChecked(false);
            }
            else if(ingles.isChecked()){
                ingles.setChecked(false);
            }
        }
    }

    public void buscarpromo(){
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id =IDpromocion.getText().toString();

        if(!id.isEmpty()){
            Cursor fila = db.rawQuery
                    ("select tipo, titulo, duracion, ubic_inicial, ubic_final, fecha, precio from promociones where cod = " +id, null);

            if(fila.moveToFirst()){
                Tipo = fila.getString(0);
                for(int i=0; i<4; i++){
                    if(Tipo.contentEquals(promocionesOpciones[i]));{
                        TipoPromocion.setSelection(i);
                    }
                }
                TITpromocion.setText(fila.getString(1));
                DURpromocion.setText(fila.getString(2));
                UINIpromocion.setText(fila.getString(3));
                UFINpromocion.setText(fila.getString(4));
                FECHApromocion.setText(fila.getString(5));
                PRECIOpromocion.setText(fila.getString(6));
                /*if(fila.getString(8).equals("español")){
                    espanol.setChecked(true);
                }
                else if(fila.getString(8).equals("ingles")){
                    ingles.setChecked(true);
                }*/

                db.close();
            } else {
                Toast.makeText(getContext(),"No existe el ID", Toast.LENGTH_SHORT).show();
                db.close();
                TipoPromocion.setSelection(0);
                IDpromocion.setText("");
                TITpromocion.setText("");
                DURpromocion.setText("");
                UINIpromocion.setText("");
                UFINpromocion.setText("");
                FECHApromocion.setText("");
                PRECIOpromocion.setText("");
                if(espanol.isChecked()){
                    espanol.setChecked(false);
                }
                else if(ingles.isChecked()){
                    ingles.setChecked(false);
                }
            }

        } else {
            Toast.makeText(getContext(), "Debes introducir el ID...", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificarpromo(){
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = IDpromocion.getText().toString();
        String titulo = TITpromocion.getText().toString();
        String duracion= DURpromocion.getText().toString();
        String inicial= UINIpromocion.getText().toString();
        String finall = UFINpromocion.getText().toString();
        String fecha= FECHApromocion.getText().toString();
        String precio = PRECIOpromocion.getText().toString();
        String idioma="";

        if(!Tipo.contentEquals("Seleccione una opción") && !id.isEmpty() && !titulo.isEmpty() && !duracion.isEmpty() && !inicial.isEmpty()
                && !finall.isEmpty() && !fecha.isEmpty() && !precio.isEmpty() && (!espanol.isChecked() | !ingles.isChecked())){

            ContentValues registro = new ContentValues();

            if(espanol.isChecked()){
                idioma = "español";
            }
            else if(ingles.isChecked()){
                idioma = "inglés";
            }

            registro.put("cod", id);
            registro.put("tipo", Tipo);
            registro.put("titulo", titulo);
            registro.put("duracion", duracion);
            registro.put("ubic_inicial", inicial);
            registro.put("ubic_final", finall);
            registro.put("fecha", fecha);
            registro.put("precio", precio);
            registro.put("idioma", idioma);

            int cantidad = db.update("promociones", registro, "cod=" + id, null);
            db.close();
            /**Verificar el limpiado de datos*/
            TipoPromocion.setSelection(0);
            IDpromocion.setText("");
            TITpromocion.setText("");
            DURpromocion.setText("");
            UINIpromocion.setText("");
            UFINpromocion.setText("");
            FECHApromocion.setText("");
            PRECIOpromocion.setText("");
            if(espanol.isChecked()){
                espanol.setChecked(false);
            }
            else if(ingles.isChecked()){
                ingles.setChecked(false);
            }

            if(cantidad == 1){
                Toast.makeText(getContext(), "Archivo modificado correctamente!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "El archivo no existe...", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(getContext(), "Debes llenar todos los campos...", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarpromo(){
        SQLiteOpenHelper admin = new SQLiteOpenHelper
                (getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = IDpromocion.getText().toString();

        if(!id.isEmpty()){

            int cantidad = db.delete("promociones", "cod=" + id, null);
            db.close();

            /**Verificar el limpiado de datos*/
            TipoPromocion.setSelection(0);
            IDpromocion.setText("");
            TITpromocion.setText("");
            DURpromocion.setText("");
            UINIpromocion.setText("");
            UFINpromocion.setText("");
            FECHApromocion.setText("");
            PRECIOpromocion.setText("");
            if(espanol.isChecked()){
                espanol.setChecked(false);
            }
            else if(ingles.isChecked()){
                ingles.setChecked(false);
            }

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
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
