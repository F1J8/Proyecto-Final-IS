package Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mohsin.mynavapp.MainActivity;
import com.example.mohsin.mynavapp.R;
import com.example.mohsin.mynavapp.SchoolFragment;

import Carreras.CarrerasFragment;
import Clientes.ClientesFragment;


public class MenuFragment extends Fragment implements ClientesFragmentF.OnFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private CardView card_Clientes, card_Activos, card_Servicios, card_Promos, card_Proveedor;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View vista;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MenuFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        vista= inflater.inflate(R.layout.fragment_menu, container, false);

        card_Clientes= vista.findViewById(R.id.Clienctes_Card);
        card_Servicios= vista.findViewById(R.id.Servicios_Card);
        card_Activos= vista.findViewById(R.id.Activos_Card);
        card_Promos= vista.findViewById(R.id.Promo_Card);
        card_Proveedor= vista.findViewById(R.id.Proveedor_Card);

        card_Clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientesFragment fragment = new ClientesFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Clientes");
                fragmentTransaction.commit();            }
        });

        card_Activos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SchoolFragment fragment = new SchoolFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Activos");
                fragmentTransaction.commit();
            }
        });

        card_Proveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProveedorFragment fragment = new ProveedorFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Proveedor");
                fragmentTransaction.commit();
            }
        });

        card_Promos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientesFragmentF fragment = new ClientesFragmentF();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Promociones");
                fragmentTransaction.commit();
            }
        });

        card_Servicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarrerasFragment fragment = new CarrerasFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Servicios");
                fragmentTransaction.commit();
            }
        });



        return vista;
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
