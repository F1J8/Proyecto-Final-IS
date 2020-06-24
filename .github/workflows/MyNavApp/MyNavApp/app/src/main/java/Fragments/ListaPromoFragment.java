package Fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohsin.mynavapp.R;

import java.util.ArrayList;

import adapters.SQLiteOpenHelper;


public class ListaPromoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View vista;
    ListView La_ListaP;
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

    public ListaPromoFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ListaPromoFragment newInstance(String param1, String param2) {
        ListaPromoFragment fragment = new ListaPromoFragment();
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
        vista = inflater.inflate(R.layout.fragment_lista_promo, container, false);

        La_ListaP = vista.findViewById(R.id.La_ListaP);
        listCarreras = new ArrayList<>();
        viewData();
        Refresh = vista.findViewById(R.id.promociones);
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
            Cursor Cur = admin.ViewDataPromociones();
            if(Cur.getCount()==0){
                Toast.makeText(getContext(),"Tabla vacía...", Toast.LENGTH_SHORT).show();
                adapter = new ArrayAdapter<>(getContext(), R.layout.elementos_listas, listCarreras);
                La_ListaP.setAdapter(adapter);
            }else {
                while(Cur.moveToNext())
                {
                    listCarreras.add(Cur.getInt(0)+" - " +Cur.getString(2));
                }
                adapter = new ArrayAdapter<>(getContext(), R.layout.elementos_listas, listCarreras);
                La_ListaP.setAdapter(adapter);
            }
        } catch ( Exception e ) {
            e.printStackTrace();
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
