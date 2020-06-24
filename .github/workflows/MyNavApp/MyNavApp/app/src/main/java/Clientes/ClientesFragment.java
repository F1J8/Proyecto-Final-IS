package Clientes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohsin.mynavapp.R;

import Fragments.ClientesFragmentF;
import adapters.SeccionesAdapter;


public class ClientesFragment extends Fragment {
   
    View vista;
    private OnFragmentInteractionListener mListener;
    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;
    
    public ClientesFragment() {
        // Required empty public constructor
    }
        // TODO: Rename and change types and number of parameters
    public static ClientesFragment newInstance(String param1, String param2) {
        ClientesFragment fragment = new ClientesFragment();
        
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista=inflater.inflate(R.layout.fragment_clientes, container, false);
       
        
        if(Utilidades.Rotacion==0){
            View parent = (View) container.getParent();
            if (appBar==null){
                appBar= (AppBarLayout) parent.findViewById(R.id.appBar);
                pestanas= new TabLayout(getActivity());
                appBar.addView(pestanas);
        
                viewPager = vista.findViewById(R.id.OpcionesClientes);
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
           adapter.addFragments(new NuevosClienteskFragment(),"Clientes");

           viewPager.setAdapter(adapter);
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
    public void onDestroyView() {
        super.onDestroyView();
        if(Utilidades.Rotacion==0) {
            appBar.removeView(pestanas);
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
