package Clientes;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohsin.mynavapp.HomeFragment;
import com.example.mohsin.mynavapp.MainActivity;
import com.example.mohsin.mynavapp.R;


public class SesionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    /***Esto es necesario
    *Aqui defino las variables y esas cosas*/
    private EditText et_User, et_Passwd;
    private TextInputLayout TI_IDUser,TI_Passwd;
    private Button Login;
    TextView UserN, Des;
    private ProgressDialog pDialog;
    public static String User="";
    
    
    RelativeLayout rellay1;
    View vista;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
       
        @Override
        public void run() {
        rellay1.setVisibility(getView().VISIBLE);
        }
    };
    private OnFragmentInteractionListener mListener;
    
    public SesionFragment() {
        // Required empty public constructor
    }
    
    // TODO: Rename and change types and number of parameters
    public static SesionFragment newInstance(String param1, String param2) {
        SesionFragment fragment = new SesionFragment();
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
        vista=inflater.inflate(R.layout.fragment_sesion, container, false);
        
        rellay1 =  vista.findViewById(R.id.RelLay1);
        handler.postDelayed(runnable,3000);
        
        TI_IDUser = vista.findViewById(R.id.ti_IDUser);
        TI_Passwd = vista.findViewById(R.id.ti_passwd);
        
        et_Passwd = vista.findViewById(R.id.et_Passwd);
        et_User = vista.findViewById(R.id.et_IDUser);
        
        UserN = vista.findViewById(R.id.username);
        
        Login = vista.findViewById(R.id.btLogin);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateIDUser() ) {
                    Toast.makeText(getContext(), "Debes llenar todos los campos con los datos correctos!", Toast.LENGTH_SHORT).show();
                    //return ;
                }else {
                    if(User.contentEquals("admin")){
                        
                        init();
                        showPDialog1();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                              
                                pDialog.dismiss();
                            }
                        },1500);
                        Toast.makeText(getContext(), "Bienvenido Coordinador!", Toast.LENGTH_SHORT).show();
                      /*  UserN.setText(R.string.username3);
                        Des.setText(R.string.usermail3);*/
                        siguiente();
                    }else{
                        init();
                        showPDialog1();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
//
                                pDialog.dismiss();
                            }
                        },1500);
                        Toast.makeText(getContext(), "Bienvenido "+User+"!", Toast.LENGTH_SHORT).show();
                        /*UserN.setText(R.string.username2);
                        Des.setText(R.string.usermail2);*/
                        siguiente();
                    }
                    
                }
                
                }
        });
        
        return vista ;
    }
    
    private void init() {
        this.pDialog = new ProgressDialog(getContext());
    }
    private void  showPDialog1(){
        pDialog.setCancelable(false);
        pDialog.setTitle("Cargando...");
        pDialog.setMessage("Espere un momento...");
        pDialog.show();
        
    
    }
    
    //Validaciones
    private boolean validateIDUser() {
        String iduser_Input = et_User.getText().toString().trim();
        String passwd_Input = et_Passwd.getText().toString().trim();
        if (iduser_Input.isEmpty() & passwd_Input.isEmpty()) {
            TI_IDUser.setError("Los campos no pueden estar vacios!");
            TI_Passwd.setError("Los campos no pueden estar vacios!");
            return false;
        }else if(!et_User.getText().toString().equals("Usuario1") && !et_Passwd.getText().toString().equals("cajafuerte") |
                !et_User.getText().toString().equals("Usuario2") && !et_Passwd.getText().toString().equals("cajafuerte") |
                !et_User.getText().toString().equals("Admin") && !et_Passwd.getText().toString().equals(".cajafuerte.") ){
            TI_IDUser.setError("Usuario Incorrecto!");
            TI_Passwd.setError("Contraseña Incorrecta!");
            et_User.setText("");
            et_Passwd.setText("");
             return false;
        }else if(et_User.getText().toString().equals("Usuario1") && !et_Passwd.getText().toString().equals("cajafuerte") ||
                et_User.getText().toString().equals("Usuario2") && !et_Passwd.getText().toString().equals("cajafuerte") ||
                et_User.getText().toString().equals("Admin") && !et_Passwd.getText().toString().equals(".cajafuerte.") ){
            
            TI_Passwd.setError("Contraseña Incorrecta!");
            et_Passwd.setText("");
            return false;
        }
        else {
            TI_IDUser.setError(null);
            TI_Passwd.setError(null);
           User= et_User.getText().toString();
            return true;
        }
    }
    private void siguiente(){
        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
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
}
