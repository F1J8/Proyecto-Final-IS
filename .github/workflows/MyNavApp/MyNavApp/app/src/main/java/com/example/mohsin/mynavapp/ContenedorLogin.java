package com.example.mohsin.mynavapp;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ContenedorLogin extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {
    private static final String TAG = "Contenedor1";
    private adapters.SeccionesAdapter msectionsStatePagerAdapter;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_login);

        msectionsStatePagerAdapter = new adapters.SeccionesAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.Contenedor1);
        //setup the pager
        setupViewPager(mViewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private void setupViewPager(ViewPager viewPager){
        adapters.SeccionesAdapter adapter = new adapters.SeccionesAdapter(getSupportFragmentManager());
        adapter.addFragments(new LoginFragment(), "Login");

        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
