package com.example.mohsin.mynavapp;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import Clientes.SesionFragment;


public class LogoutFragment extends Fragment {
public     View vista;
public ImageButton Logout;
    private ProgressDialog pDialog;
    public LogoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    
    
     
        vista=inflater.inflate(R.layout.fragment_logout, container, false);
        
        Logout=vista.findViewById(R.id.btLogout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SesionFragment.User="";
                init();
                showPDialog1();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(getContext(), "Bienvenido Coordinador!", Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }
                },1500);
                Toast.makeText(getContext(),"Hasta pronto...!",Toast.LENGTH_LONG).show();
                toLogin();
            }
        });
        
        return vista;
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
    
    public void toLogin(){
        SesionFragment fragment = new SesionFragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "Login");
        fragmentTransaction.commit();
    }
}
