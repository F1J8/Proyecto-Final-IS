package Prospectos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohsin.mynavapp.R;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;

import adapters.SQLiteOpenHelper;


public class ProspectosC extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
   /** Declaraciones de variables utiles*/
    View vista;
    private Spinner spCarrera1, spCarrera2, spCarrera3;
    private String[]   spOpciones=new String[] {"Seleccione una opción", "Ingeniería Aeronáutica", "Bioingeniería", "Licenciatura en Dibujo Automatizado",
            "Licenciatura en Diseño de Animación Digital", "Ingeniería Química", "Licenciatura en Comercio Inrternacional"};
    private TextInputLayout TI_DNI,TI_Nombre,TI_Apellido,TI_Correo, TI_Tel1, TI_Tel2, TI_NombreP,
            TI_APellidoP, TI_NombreM, TI_APellidoM, TI_Colegio;
    private EditText DNI, Nombre, Apellido, NombreP, ApellidoP, NombreM, ApellidoM, Correo, Tel1, Tel2, Colegio;
    private TextView tvCarrera1, tvCarrera2, tvCarrera3;
    private Button Registrar, Consultar, Eliminar, Modificar;
    private  String Carrera1="", Carrera2="", Carrera3="";

    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    
    private OnFragmentInteractionListener mListener;
    
    public ProspectosC() {
        // Required empty public constructor
    }
    
   
    // TODO: Rename and change types and number of parameters
    public static ProspectosC newInstance(String param1, String param2) {
        ProspectosC fragment = new ProspectosC();
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
        vista=inflater.inflate(R.layout.fragment_prospectos_c, container, false);

        
        return vista;
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

    
    /**Validaciones*/
    private boolean validateNombre() {
        String Nombre_Input = Nombre.getText().toString().trim();
        
        if (Nombre_Input.isEmpty()) {
            TI_Nombre.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Nombre.setError(null);
            return true;
        }
    }
    private boolean validateCarreras() {

        if (Carrera1 == Carrera2 && Carrera1 == Carrera3 | Carrera2 == Carrera1 && Carrera2 == Carrera3
                | Carrera3 == Carrera1 && Carrera3 == Carrera2 ) {
           Toast.makeText(getContext(),"Seleccione opciones diferentes...", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            //Toast.makeText(getContext(),"", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    private boolean validateDNI() {
        String DNI_Input = DNI.getText().toString().trim();
        
        if (DNI_Input.isEmpty()) {
            TI_DNI.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_DNI.setError(null);
            return true;
        }
    }
    private boolean validateApellido() {
        String Apellido_Input = Apellido.getText().toString().trim();
        
        if (Apellido_Input.isEmpty()) {
            TI_Apellido.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Apellido.setError(null);
            return true;
        }
    }
    private boolean validateTel1() {
        String Tel1_Input = Tel1.getText().toString().trim();
        
        if (Tel1_Input.isEmpty()) {
            TI_Tel1.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Tel1.setError(null);
            return true;
        }
    }
    private boolean validateTel2() {
        String Tel2_Input = Tel2.getText().toString().trim();
        
        if (Tel2_Input.isEmpty()) {
            TI_Tel2.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Tel2.setError(null);
            return true;
        }
    }
    private boolean validateNombreP() {
        String NombreP_Input = NombreP.getText().toString().trim();
        
        if (NombreP_Input.isEmpty()) {
            TI_NombreP.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_NombreP.setError(null);
            return true;
        }
    }
    private boolean validateApellidoP() {
        String ApellidoP_Input = ApellidoP.getText().toString().trim();
        
        if (ApellidoP_Input.isEmpty()) {
            TI_APellidoP.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_APellidoP.setError(null);
            return true;
        }
    }
    private boolean validateColegio() {
        String Colegio_Input = Colegio.getText().toString().trim();
        
        if (Colegio_Input.isEmpty()) {
            TI_Colegio.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Colegio.setError(null);
            return true;
        }
    }
    private boolean validateNombreM() {
        String NombreM_Input = NombreM.getText().toString().trim();
        
        if (NombreM_Input.isEmpty()) {
           TI_NombreM.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_NombreM.setError(null);
            return true;
        }
    }
    private boolean validateApellidoM() {
        String ApellidoM_Input = ApellidoM.getText().toString().trim();
        
        if (ApellidoM_Input.isEmpty()) {
            TI_APellidoM.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_APellidoM.setError(null);
            return true;
        }
    }
    private boolean validateCorreo() {
        String Correo_Input = Correo.getText().toString().trim();
        
        if (Correo_Input.isEmpty()) {
            TI_Correo.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Correo.setError(null);
            return true;
        }
    }
    /**Fin Validaciones*/
    
    /**Funciones de los Botones*/
    
    /**Metodo para el Registro*/
    public void Registrar() {
        //Validacion y registro de productos
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db= admin.getWritableDatabase();
        
        String dni =DNI.getText().toString();
        String nombre = Nombre.getText().toString();
        String apellido = Apellido.getText().toString();
        String tel1= Tel1.getText().toString();
        String correo = Correo.getText().toString();
        String tel2 = Tel2.getText().toString();
        String nombrep = NombreP.getText().toString();
        String apellidop = ApellidoP.getText().toString();
        String nombrem = NombreM.getText().toString();
        String apellidom = ApellidoM.getText().toString();
        String colegio = Colegio.getText().toString();
        
        
        if (!validateDNI() | !validateNombre() | !validateApellido() | !validateCorreo() | !validateTel1() |
                !validateNombreP() | !validateTel2()| !validateApellidoP() | !validateApellidoM() | !validateNombreM() |
                !validateColegio() | !validateCarreras()) {
            Toast.makeText(getContext(), "Debes llenar todos los campos con los datos correctos", Toast.LENGTH_SHORT).show();
            //return ;
        } else{
            

                ContentValues registro = new ContentValues();
    
                registro.put("DNI", dni);
                registro.put("Nombre", nombre);
                registro.put("Apellido", apellido);
                registro.put("Correo", correo);
                registro.put("Telefono1", tel1);
                registro.put("Telefono2", tel2);
                registro.put("NombrePadre", nombrep);
                registro.put("ApellidoPadre", apellidop);
                registro.put("NombreMadre", nombrem);
                registro.put("ApellidoMadre", apellidom);
                registro.put("Carrera1", Carrera1);
                registro.put("Carrera2", Carrera2);
                registro.put("Carrera3", Carrera3);
                registro.put("Colegio", colegio);
    
                db.insert("Prospectos_5", null, registro);
    
                Toast.makeText(getContext(),"Registro exitoso", Toast.LENGTH_SHORT).show();
    
                /**Variable para mostrar el Toast*/
    
                String input = "DNI: " + DNI.getText().toString();
                input += "\n";
                input += "Nombre: " + Nombre.getText().toString()+" "+ Apellido.getText().toString();
                input += "\n";
                input += "Correo: " + Correo.getText().toString();
                input += "\n";
                input += "Telefono 1: " + Tel1.getText().toString();
                input += "\n";
                input += "Telefono 2: " + Tel2.getText().toString();
                input += "\n";
                input += "Nombre del Padre: " + NombreP.getText().toString()+" "+ ApellidoP.getText().toString();
                input += "\n";
                input += "Nombre de la Madre: " + NombreM.getText().toString()+" "+ ApellidoM.getText().toString();
                input += "\n";
                input += "Colegio: " + Colegio.getText().toString();
                input += "\n";
                input += "Opcion de Carrera 1: " + Carrera1;
                input += "\n";
                input += "Opcion de Carrera 2: " + Carrera2;
                input += "\n";
                input += "Opcion de Carrera 3: " + Carrera3;
                input += "\n";
    
                Toast.makeText(getContext(), input, Toast.LENGTH_LONG).show();
    
                db.close();
                //Verificar el limpiado de datos
                Nombre.setText("");
                DNI.setText("");
                Apellido.setText("");
                Tel1.setText("");
                Correo.setText("");
                Tel2.setText("");
                NombreP.setText("");
                ApellidoP.setText("");
                NombreM.setText("");
                ApellidoM.setText("");
                Colegio.setText("");
                Carrera3="";
                Carrera2="";
                Carrera1="";
                spCarrera1.setPrompt("Seleccione una opción...");
            }
        }

    /**
     * Método para consultar un elemento*/
    public void Buscar(){
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();

        tvCarrera1.setVisibility(getView().VISIBLE);
        tvCarrera2.setVisibility(getView().VISIBLE);
        tvCarrera3.setVisibility(getView().VISIBLE);

        String dni =DNI.getText().toString();
        
        if(!dni.isEmpty()){
            Cursor fila = db.rawQuery
                    ("select Nombre, Apellido, Telefono1, Telefono2,  Correo , NombrePadre, ApellidoPadre, NombreMadre, ApellidoMadre, Carrera1, " +
                            "Carrera2, Carrera3, Colegio  from Prospectos_5 where DNI= " + dni, null);
            
            if(fila.moveToFirst()){
                Nombre.setText(fila.getString(0));
                Apellido.setText(fila.getString(1));
                Tel1.setText(fila.getString(2));
                Tel2.setText(fila.getString(3));
                Correo.setText(fila.getString(4));
                NombreP.setText(fila.getString(5));
                ApellidoP.setText(fila.getString(6));
                NombreM.setText(fila.getString(7));
                ApellidoM.setText(fila.getString(8));
                tvCarrera1.setText(fila.getString(9));
                tvCarrera2.setText(fila.getString(10));
                tvCarrera3.setText(fila.getString(11));
                Colegio.setText(fila.getString(12));
                db.close();

            } else {
                Toast.makeText(getContext(),"No existe el achivo", Toast.LENGTH_SHORT).show();
                /**Verificar el limpiado de datos*/
                Nombre.setText("");
                DNI.setText("");
                Apellido.setText("");
                Tel1.setText("");
                Correo.setText("");
                Tel2.setText("");
                NombreP.setText("");
                ApellidoP.setText("");
                NombreM.setText("");
                ApellidoM.setText("");
                Colegio.setText("");
                /*tvCarrera1.setText("");*/
                Carrera3="";
                Carrera2="";
                Carrera1="";
                db.close();
            }
            
        } else {
            Toast.makeText(getContext(), "Debes introducir el DNI...", Toast.LENGTH_SHORT).show();
        }
    }
    
    /**Método para eliminar un producto o Artículo*/
    public void Eliminar(){
        SQLiteOpenHelper admin = new SQLiteOpenHelper
                (getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();
        
        String dni = DNI.getText().toString();
        
        if(!dni.isEmpty()){
            
            int cantidad = db.delete("Prospectos_5", "DNI=" + dni, null);
            db.close();
            
            /**Verificar el limpiado de datos*/
            Nombre.setText("");
            DNI.setText("");
            Apellido.setText("");
            Tel1.setText("");
            Correo.setText("");
            Tel2.setText("");
            NombreP.setText("");
            ApellidoP.setText("");
            NombreM.setText("");
            ApellidoM.setText("");
            Carrera3="";
            Carrera2="";
            Carrera1="";

            spCarrera1.setPrompt("Seleccione una opción...");
            
            if(cantidad == 1){
                Toast.makeText(getContext(), "Archivo eliminado exitosamente!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "El archivo no existe...", Toast.LENGTH_SHORT).show();
            }
            
        } else {
            Toast.makeText(getContext(), "Debes de introducir el DNI...", Toast.LENGTH_SHORT).show();
        }
    }
    
    //Método para modificar un artículo o producto
    public void Modificar(){
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();
        
        String dni =DNI.getText().toString();
        String nombre = Nombre.getText().toString();
        String apellido = Apellido.getText().toString();
        String tel1= Tel1.getText().toString();
        String correo = Correo.getText().toString();
        String tel2 = Tel2.getText().toString();
        String nombrep = NombreP.getText().toString();
        String apellidop = ApellidoP.getText().toString();
        String nombrem = NombreM.getText().toString();
        String apellidom = ApellidoM.getText().toString();
        String colegio = Colegio.getText().toString();

        if(   !dni.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !colegio.isEmpty()
                && !tel1.isEmpty() && !tel2.isEmpty() && !nombrep.isEmpty() && !apellidop.isEmpty() && !nombrem.isEmpty()
                && !apellidom.isEmpty()){
            
            ContentValues registro = new ContentValues();
            
            registro.put("DNI", dni);
            registro.put("Nombre", nombre);
            registro.put("Apellido", apellido);
            registro.put("Correo", correo);
            registro.put("Telefono1", tel1);
            registro.put("Telefono2", tel2);
            registro.put("NombrePadre", nombrep);
            registro.put("ApellidoPadre", apellidop);
            registro.put("NombreMadre", nombrem);
            registro.put("ApellidoMadre", apellidom);
            registro.put("Colegio", colegio);
            registro.put("Carrera1", Carrera1);
            registro.put("Carrera2", Carrera2);
            registro.put("Carrera3", Carrera3);
            
            int cantidad = db.update("Prospectos_5", registro, "DNI=" + dni, null);
            /**Verificar el limpiado de datos*/
            Nombre.setText("");
            DNI.setText("");
            Apellido.setText("");
            Tel1.setText("");
            Correo.setText("");
            Tel2.setText("");
            NombreP.setText("");
            ApellidoP.setText("");
            NombreM.setText("");
            ApellidoM.setText("");
            Colegio.setText("");
            Carrera3="";
            Carrera2="";
            Carrera1="";

            db.close();
            if(cantidad == 1){
                Toast.makeText(getContext(), "Archivo modificado correctamente!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "El archivo no existe...", Toast.LENGTH_SHORT).show();
            }
            
        } else {
            Toast.makeText(getContext(), "Debes llenar todos los campos...", Toast.LENGTH_SHORT).show();
        }
    }
    
    /**Fin de las Funciones de los Botones*/
    
}
