package Clientes;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mohsin.mynavapp.R;

import adapters.Constantes;
import adapters.SQLiteOpenHelper;


public class NuevosClienteskFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    /**Declaraciones de variables utiles*/
    View vista;
    private TextInputLayout TI_DNI,TI_Nombre,TI_Apellido,TI_Correo, TI_Tel1, TI_Tel2, TI_NombreP,
            TI_APellidoP, TI_NombreM, TI_APellidoM, TI_Colegio, TI_Carrera1, TI_Carrera2, TI_Carrera3;
    private EditText DNI, Nombre, Apellido, NombreP, ApellidoP, NombreM, ApellidoM, Correo, Tel1, Tel2,
            Colegio, Carrera1, Carrera2, Carrera3;
    private Button Registrar, Consultar, Eliminar, Modificar;
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    
    private OnFragmentInteractionListener mListener;
    
    public NuevosClienteskFragment() {
        // Required empty public constructor
    }
    
    
    // TODO: Rename and change types and number of parameters
    public static NuevosClienteskFragment newInstance(String param1, String param2) {
        NuevosClienteskFragment fragment = new NuevosClienteskFragment();
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
        // Inflate the layout for getContext() fragment
        
        vista=inflater.inflate(R.layout.fragment_nuevos_clientesk, container, false);
        /**Relacione TI*/
        TI_Nombre = vista.findViewById(R.id.ti_Nombre_N);
        TI_DNI = vista.findViewById(R.id.ti_DNI_N);
        TI_Apellido = vista.findViewById(R.id.ti_Apellido_N);
        TI_Correo = vista.findViewById(R.id.ti_Email_N);
        TI_Tel1 = vista.findViewById(R.id.ti_Tel1_N);
        TI_Tel2 = vista.findViewById(R.id.ti_Tel2_N);

        
        
        /**Relaciones ET*/
        DNI = vista.findViewById(R.id.et_DNI_N);
        Nombre = vista.findViewById(R.id.et_Nombre_N);
        Apellido = vista.findViewById(R.id.et_Apellido_N);
        Correo = vista.findViewById(R.id.et_Email_N);
        Tel1 = vista.findViewById(R.id.et_Tel1_N);
        Tel2 = vista.findViewById(R.id.et_Tel2_N);


        /**Relaciones de los botones*/
        Registrar = vista.findViewById(R.id.bt_Registro);
        Consultar = vista.findViewById(R.id.bt_Buscar);
        Modificar = vista.findViewById(R.id.bt_Modificar);
        Eliminar = vista.findViewById(R.id.bt_Borrar);
        

        /**
         * Asignacion de las funciones a los botones*/
        
        /**Registro
         * Solo Admin*/


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
    private boolean validateCarreras() {
        String Carrera1_Input = Carrera1.getText().toString().trim();
        String Carrera2_Input = Carrera2.getText().toString().trim();
        String Carrera3_Input = Carrera3.getText().toString().trim();
        
        if (Carrera1_Input.isEmpty() ) {
            TI_Carrera1.setError("Los campos no pueden estar vacios!");
            return false;
        } else if(Carrera2_Input.isEmpty() ) {
            TI_Carrera2.setError("Los campos no pueden estar vacios!");
            return false;
        }else if(Carrera3_Input.isEmpty() ) {
            TI_Carrera3.setError("Los campos no pueden estar vacios!");
            return false;
        }else {
            TI_Nombre.setError(null);
            return true;
        }
    }
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
        String carrera1 = Carrera1.getText().toString();
        String carrera2 = Carrera2.getText().toString();
        String carrera3 = Carrera3.getText().toString();
        
        
        if (!validateDNI() | !validateNombre() | !validateApellido() | !validateCorreo() | !validateTel1() |
        !validateNombreP() | !validateTel2()| !validateApellidoP() | !validateApellidoM() | !validateNombreM() |
        !validateColegio() | !validateCarreras()) {
            Toast.makeText(getContext(), "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
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
            registro.put("Colegio", colegio);
            registro.put("Carrera1", carrera1);
            registro.put("Carrera2", carrera2);
            registro.put("Carrera3", carrera3);
            
           db.insert("Estudiantes_Primer_Ingreso", null, registro);
            
            Toast.makeText(getContext(),"Registro exitoso", Toast.LENGTH_SHORT).show();
            
            /**Variable para mostrar el Toast*/
            
            String input = "DNI: " + DNI.getText().toString();
            input += "\n";
            input += "Nombre: " + Nombre.getText().toString();
            input += "\n";
            input += "Apellido: " + Apellido.getText().toString();
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
           input += "Opción de Carrera 1: " + Carrera1.getText().toString();
            input += "\n";
           input += "Opción de Carrera 2: " + Carrera2.getText().toString();
            input += "\n";
           input += "Opción de Carrera 3: " + Carrera3.getText().toString();
            
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
            Carrera3.setText("");
            Carrera1.setText("");
            Carrera2.setText("");
            
        }
    }
    /**
   * Método para consultar un elemento*/
    public void Buscar(){
        SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
        SQLiteDatabase db = admin.getWritableDatabase();
    
        String dni =DNI.getText().toString();
        
        if(!dni.isEmpty()){
            Cursor fila = db.rawQuery
        ("select Nombre, Apellido, Telefono1, Telefono2,  Correo, Colegio, NombrePadre,  " +
                "ApellidoPadre, NombreMadre, ApellidoMadre, Carrera1, Carrera2, Carrera3  from Estudiantes_Primer_Ingreso where DNI=" +dni, null);
            
            if(fila.moveToFirst()){
                Nombre.setText(fila.getString(0));
                Apellido.setText(fila.getString(1));
                Tel1.setText(fila.getString(2));
                Tel2.setText(fila.getString(3));
                Correo.setText(fila.getString(4));
                Colegio.setText(fila.getString(5));
                NombreP.setText(fila.getString(6));
                ApellidoP.setText(fila.getString(7));
                NombreM.setText(fila.getString(8));
                ApellidoM.setText(fila.getString(9));
                Carrera1.setText(fila.getString(10));
                Carrera2.setText(fila.getString(11));
                Carrera3.setText(fila.getString(12));
                
                db.close();
            } else {
                Toast.makeText(getContext(),"No existe el achivo", Toast.LENGTH_SHORT).show();
                db.close();
                DNI.setText("");
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
            
            int cantidad = db.delete("Estudiantes_Primer_Ingreso", "DNI=" + dni, null);
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
            Colegio.setText("");
            Carrera3.setText("");
            Carrera1.setText("");
            Carrera2.setText("");
    
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
        String carrera1 = Carrera1.getText().toString();
        String carrera2 = Carrera2.getText().toString();
        String carrera3 = Carrera3.getText().toString();
        
        if(   !dni.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty() && !colegio.isEmpty()
              && !tel1.isEmpty() && !tel2.isEmpty() && !nombrep.isEmpty() && !apellidop.isEmpty() && !nombrem.isEmpty()
             && !apellidom.isEmpty() && !carrera1.isEmpty() && !carrera2.isEmpty() && !carrera3.isEmpty()){
            
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
            registro.put("Carrera1", carrera1);
            registro.put("Carrera2", carrera2);
            registro.put("Carrera3", carrera3);
            
            int cantidad = db.update("Estudiantes_Primer_Ingreso", registro, "DNI=" + dni, null);
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
            Colegio.setText("");
            Carrera3.setText("");
            Carrera1.setText("");
            Carrera2.setText("");
            
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
