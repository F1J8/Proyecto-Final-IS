package Carreras;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohsin.mynavapp.R;

import java.sql.Ref;
import java.util.ArrayList;

import adapters.SQLiteOpenHelper;
import adapters.Usuarios;


public class Carreras extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    /**Declaraciones Importantes*/
   // Spinner spcarreras;
    //TextView txtNombre, txtFacultad, txtDuracion;
    //Button Refresh;
    View vista;
    ListView La_Lista;
    TextView Refresh;
    
    /**HELPER*/
    SQLiteOpenHelper admin = new SQLiteOpenHelper(getContext(), "db", null, 2);
   /**Fin HELPER*/
   
   ArrayList<String> listCarreras;
   ArrayAdapter adapter;
    
  
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    
    private OnFragmentInteractionListener mListener;
    
    public Carreras() {
        // Required empty public constructor
    }
    
    
    // TODO: Rename and change types and number of parameters
    public static Carreras newInstance(String param1, String param2) {
        Carreras fragment = new Carreras();
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
        
        vista=inflater.inflate(R.layout.fragment_carreras2, container, false);
        
        
        La_Lista = vista.findViewById(R.id.La_Lista);
        listCarreras = new ArrayList<>();
        viewData();
        Refresh = vista.findViewById(R.id.servicios);
        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                listCarreras.clear();
                viewData();
            }
        });
        

        /**Coneccion a la base de datos*/
        admin = new SQLiteOpenHelper(getContext(),"db",null,2);
    
    
        
        return vista;
    }
    
    private void viewData() {
        
       try{
           Cursor Cur = admin.ViewDataServicios();
           if(Cur.getCount()==0){
               Toast.makeText(getContext(),"Tabla vacía...", Toast.LENGTH_SHORT).show();
               adapter = new ArrayAdapter<>(getContext(), R.layout.elementos_listas, listCarreras);
               La_Lista.setAdapter(adapter);
           }else {
               while(Cur.moveToNext())
               {
                   listCarreras.add(Cur.getInt(0)+" - " +Cur.getString(2));
               }
               adapter = new ArrayAdapter<>(getContext(), R.layout.elementos_listas, listCarreras);
               La_Lista.setAdapter(adapter);
           }
       } catch ( Exception e ) {
           e.printStackTrace();
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
