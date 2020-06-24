package com.example.mohsin.mynavapp;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import Clientes.ClientesFragment;
import Clientes.Utilidades;
import Prospectos.Prospectos6;
import Prospectos.ProspectosC;
import Prospectos.ProspectosU;
import adapters.SeccionesAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFragment extends Fragment {
    View vista;
    private ClientesFragment.OnFragmentInteractionListener mListener;
    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;

    public SchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_school, container, false);
    
    
        if( Utilidades.Rotacion==0){
            View parent = (View) container.getParent();
            if (appBar==null){
                appBar= (AppBarLayout) parent.findViewById(R.id.appBar);
                pestanas= new TabLayout(getActivity());
                appBar.addView(pestanas);
            
                viewPager = vista.findViewById(R.id.OpcionesEstudiantes);
                llenarvP (viewPager);
            
                viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                });
                pestanas.setupWithViewPager(viewPager);
            }
            pestanas.setTabGravity(TabLayout.GRAVITY_FILL);
        
        }else{
            Utilidades.Rotacion=1;
        }
    
    
        return vista;
    }
    private void llenarvP(ViewPager viewPager) {
        SeccionesAdapter adapter = new SeccionesAdapter(getFragmentManager());
        adapter.addFragments(new ProspectosC(),"Lista de Activos");
        adapter.addFragments(new Prospectos6(),"Activos");
        viewPager.setAdapter(adapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(Utilidades.Rotacion==0) {
            appBar.removeView(pestanas);
        }
    }
}