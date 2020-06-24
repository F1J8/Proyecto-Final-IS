package Carreras;

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

import com.example.mohsin.mynavapp.LoginFragment;
import com.example.mohsin.mynavapp.R;

import Fragments.ListaPromoFragment;
import adapters.SeccionesAdapter;


public class PromOpcionesFragment extends Fragment {
    View vista;
    private OnFragmentInteractionListener mListener;
    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public PromOpcionesFragment() {
        // Required empty public constructor
    }


    public static PromOpcionesFragment newInstance(String param1, String param2) {
        PromOpcionesFragment fragment = new PromOpcionesFragment();
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
        vista=inflater.inflate(R.layout.fragment_prom_opciones, container, false);


        if(Clientes.Utilidades.Rotacion==0){
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
            Clientes.Utilidades.Rotacion=1;
        }


        return vista;
    }

    private void llenarvP(ViewPager viewPager) {
        if(LoginFragment.User.contentEquals("Encargado")){
            SeccionesAdapter adapter = new SeccionesAdapter(getFragmentManager());
            adapter.addFragments(new PromOpcionesFragment(),"Agegar Promociones");
            adapter.addFragments(new ListaPromoFragment(),"Mostrar Promociones");
            viewPager.setAdapter(adapter);
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
