package Carreras;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;



import com.example.mohsin.mynavapp.LoginFragment;
import com.example.mohsin.mynavapp.R;

import Fragments.MenuFragment;

public class Contenedor2 extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener {
    private static final String TAG = "Contenedor2";

    private adapters.SeccionesAdapter msectionsStatePagerAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor2);
        Log.d(TAG, "onCreate: Started.");

       // msectionsStatePagerAdapter = new Adapters.SectionsStatePagerAdapter(getSupportFragmentManager());
        if( Clientes.Utilidades.vPaantalla==true ){
            Fragment Menu = new MenuFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.Contenedor2,Menu, "Menu").commit();
           Clientes.Utilidades.vPaantalla=false;
        }
      /*  mViewPager = (ViewPager) findViewById(R.id.Contenedor2);
        *//**setup the pager*//*
        setupViewPager(mViewPager);*/
    }

    private void setupViewPager(ViewPager viewPager){
        adapters.SeccionesAdapter adapter = new adapters.SeccionesAdapter(getSupportFragmentManager());
        adapter.addFragments(new LoginFragment(), "Menu");

        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
