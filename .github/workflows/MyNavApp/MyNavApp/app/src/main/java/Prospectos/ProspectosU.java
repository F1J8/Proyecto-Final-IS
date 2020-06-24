package Prospectos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mohsin.mynavapp.HomeFragment;
import com.example.mohsin.mynavapp.R;

import Clientes.SesionFragment;
import adapters.SQLiteOpenHelper;

public class ProspectosU extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public  View vista;
    public Button Transferencia;
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    
    /**Declaraciones de variables utiles*/
   
    private TextInputLayout TI_DNI,TI_Nombre,TI_Apellido,TI_Correo, TI_Tel1, TI_Tel2, TI_Universidad, TI_Carrera, TI_NivelA;
    private EditText DNI, Nombre, Apellido, Correo, Tel1, Tel2, Universidad, Carrera, NivelA;
    private Button Registrar, Consultar, Eliminar, Modificar;
    
    
    private OnFragmentInteractionListener mListener;
    
    public ProspectosU() {
        // Required empty public constructor
    }
    
    // TODO: Rename and change types and number of parameters
    public static ProspectosU newInstance(String param1, String param2) {
        ProspectosU fragment = new ProspectosU();
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
        vista=inflater.inflate(R.layout.fragment_prospectos_u, container, false);
    
        /**Relacione TI*/
        TI_Nombre = vista.findViewById(R.id.ti_Nombre);
        TI_DNI = vista.findViewById(R.id.ti_DNI);
        TI_Apellido = vista.findViewById(R.id.ti_Apellido);
        TI_Correo = vista.findViewById(R.id.ti_Email);
        TI_Tel1 = vista.findViewById(R.id.ti_Tel1);
        TI_Tel2 = vista.findViewById(R.id.ti_Tel2);
        TI_Carrera = vista.findViewById(R.id.ti_CarreraAnterior);
        TI_Universidad= vista.findViewById(R.id.ti_NombreUniversidad);
        TI_NivelA = vista.findViewById(R.id.ti_NivelAnterior);
    
    
        /**Relaciones ET*/
        DNI = vista.findViewById(R.id.et_DNI);
        Nombre = vista.findViewById(R.id.et_Nombre);
        Apellido = vista.findViewById(R.id.et_Apellido);
        Correo = vista.findViewById(R.id.et_Email);
        Tel1 = vista.findViewById(R.id.et_Tel1);
        Tel2 = vista.findViewById(R.id.et_Tel2);
        Carrera = vista.findViewById(R.id.et_CarreraAnterior);
        Universidad= vista.findViewById(R.id.et_NombreUniversidad);
        NivelA = vista.findViewById(R.id.et_NivelAnterior);
        
        /**Relaciones de los botones*/
        Registrar = vista.findViewById(R.id.bt_Registro);
        Consultar = vista.findViewById(R.id.bt_Buscar);
        Modificar = vista.findViewById(R.id.bt_Modificar);
        Eliminar = vista.findViewById(R.id.bt_Borrar);
    
        /**Control de Permisos*/
        if( SesionFragment.User.contentEquals("admin"))
        {
            Eliminar.setVisibility(View.VISIBLE);
            Modificar.setVisibility(View.VISIBLE);
            Registrar.setVisibility(View.VISIBLE);
            Consultar.setVisibility(View.VISIBLE);
        }else{
            Eliminar.setVisibility(View.INVISIBLE);
            Modificar.setVisibility(View.INVISIBLE);
            Registrar.setVisibility(View.INVISIBLE);
            Consultar.setVisibility(View.VISIBLE);
        }
    
        /**Fin de Control de Permisos*/
    
        /**
         * Asignacion de las funciones a los botones*/
    
        /**Registro
         * Solo Admin*/
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar();
            }
        });
    
        /**Consulta
         * Todos*/
        Consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buscar();
            }
        });
    
        /**Modificar
         * Solo Admin*/
        Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modificar();
            }
        });
    
        /**Eliminar
         * Solo Admin*/
        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar();
            }
        });
        
        
        /**siguiente fragment*/
        Transferencia = vista.findViewById(R.id.btTransferencia);
        Transferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aTransferencia();
            
            }
        });
        return vista;
    }
    
    
    private void aTransferencia(){
        Transferencia fragment = new Transferencia();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "Transferencia");
        fragmentTransaction.commit();
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
    private boolean validateUniversidad() {
        String Universidad_Input = Universidad.getText().toString().trim();
        
        if (Universidad_Input.isEmpty()) {
            TI_Universidad.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Universidad.setError(null);
            return true;
        }
    }
    private boolean validateCarrera() {
        String Carrera_Input = Carrera.getText().toString().trim();
        
        if (Carrera_Input.isEmpty()) {
            TI_Carrera.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_Carrera.setError(null);
            return true;
        }
    }
    
    private boolean validateNivelA() {
        String Nivel_Input = NivelA.getText().toString().trim();
        
        if (Nivel_Input.isEmpty()) {
            TI_NivelA.setError("Los campos no pueden estar vacios!");
            return false;
        } else {
            TI_NivelA.setError(null);
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
        String universidad = Universidad.getText().toString();
        String carrera = Carrera.getText().toString();
        String nivela = NivelA.getText().toString();
        
        
        if (!validateDNI() | !validateNombre() | !validateApellido() | !validateCorreo() | !validateTel1() |
                !validateUniversidad() | !validateTel2()| !validateUniversidad() | !validateCarrera() | !validateNivelA()      ) {
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
            registro.put("Universidad", universidad);
            registro.put("Carrera", carrera);
            registro.put("Nivel", nivela);
            
            db.insert("Prospectos_U_Reingreso", null, registro);
            
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
            input += "Nombre de la Universidad: " + Universidad.getText().toString();
            input += "\n";
            input += "Carrera Anterior: " + Carrera.getText().toString();
            input += "\n";
            input += "Nivel Anterior: " + NivelA.getText().toString();
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
            Universidad.setText("");
            Carrera.setText("");
            NivelA.setText("");
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
                    ("select  Nombre, Apellido, Telefono1, Telefono2, Correo, Universidad, Carrera, Nivel  from Prospectos_U_Reingreso where DNI=" + dni, null);
            
            if(fila.moveToFirst()){
                Nombre.setText(fila.getString(0));
                Apellido.setText(fila.getString(1));
                Tel1.setText(fila.getString(2));
                Tel2.setText(fila.getString(3));
                Correo.setText(fila.getString(4));
                Universidad.setText(fila.getString(5));
                Carrera.setText(fila.getString(6));
                NivelA.setText(fila.getString(7));
                
                db.close();
            } else {
                Toast.makeText(getContext(),"No existe el achivo", Toast.LENGTH_SHORT).show();
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
            
            int cantidad = db.delete("Prospectos_U_Reingreso", "DNI=" + dni, null);
            db.close();
            
            /**Verificar el limpiado de datos*/
            Nombre.setText("");
            DNI.setText("");
            Apellido.setText("");
            Tel1.setText("");
            Correo.setText("");
            Tel2.setText("");
            Universidad.setText("");
            Carrera.setText("");
            NivelA.setText("");
            
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
        String universidad = Universidad.getText().toString();
        String carrera = Carrera.getText().toString();
        String nivela = NivelA.getText().toString();
        
        if(   !dni.isEmpty() && !nombre.isEmpty() && !apellido.isEmpty() && !correo.isEmpty()
                && !tel1.isEmpty() && !tel2.isEmpty() && !universidad.isEmpty() && !carrera.isEmpty() && !nivela.isEmpty()){
            
            ContentValues registro = new ContentValues();
            
            registro.put("DNI", dni);
            registro.put("Nombre", nombre);
            registro.put("Apellido", apellido);
            registro.put("Correo", correo);
            registro.put("Telefono1", tel1);
            registro.put("Telefono2", tel2);
            registro.put("Universidad", universidad);
            registro.put("Carrera", carrera);
            registro.put("Nivel", nivela);
            
            
            int cantidad = db.update("Prospectos_U_Reingreso", registro, "DNI=" + dni, null);
            db.close();
            /**Verificar el limpiado de datos*/
            Nombre.setText("");
            DNI.setText("");
            Apellido.setText("");
            Tel1.setText("");
            Correo.setText("");
            Tel2.setText("");
            Universidad.setText("");
            Universidad.setText("");
            Carrera.setText("");
            NivelA.setText("");
            
            
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
