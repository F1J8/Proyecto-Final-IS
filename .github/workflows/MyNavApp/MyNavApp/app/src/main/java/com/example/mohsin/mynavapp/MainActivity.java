package com.example.mohsin.mynavapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import Carreras.AjustesCarreras;
import Carreras.Carreras;
import Carreras.CarrerasFragment;
import Clientes.AntiguosClientesFragment;
import Clientes.ClientesFragment;
import Clientes.NuevosClienteskFragment;
import Clientes.SesionFragment;
import Clientes.Utilidades;
import Fragments.ClientesFragmentF;
import Fragments.ListaPromoFragment;
import Fragments.MenuFragment;
import Fragments.PromocionesFragment;
import Fragments.ProveedorFragment;
import Prospectos.Prospectos6;
import Prospectos.ProspectosC;
import Prospectos.ProspectosU;
import Prospectos.Transferencia;
import Seguimiento.Seguimiento;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
, AntiguosClientesFragment.OnFragmentInteractionListener, NuevosClienteskFragment.OnFragmentInteractionListener,
        ClientesFragment.OnFragmentInteractionListener, SesionFragment.OnFragmentInteractionListener,
        CarrerasFragment.OnFragmentInteractionListener, ProspectosC.OnFragmentInteractionListener,
        ProspectosU.OnFragmentInteractionListener, Transferencia.OnFragmentInteractionListener,
        Carreras.OnFragmentInteractionListener, AjustesCarreras.OnFragmentInteractionListener,
        Prospectos6.OnFragmentInteractionListener, Seguimiento.OnFragmentInteractionListener,
        MenuFragment.OnFragmentInteractionListener, ClientesFragmentF.OnFragmentInteractionListener,
        ProveedorFragment.OnFragmentInteractionListener, PromocionesFragment.OnFragmentInteractionListener,
        ListaPromoFragment.OnFragmentInteractionListener {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    public Button Clientes, Activos, Inform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        // friends for toolbar we use v7 package

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        
        Clientes = findViewById(R.id.Clientes);
        Activos = findViewById(R.id.Activos);
        Inform = findViewById(R.id.setting);
        
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

       if( Utilidades.vPaantalla==true ){
           Fragment Menu= new MenuFragment();
           getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,Menu, "Menu").commit();
           Utilidades.vPaantalla=false;
       }
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // now create fragments
/*
        HomeFragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
        fragmentTransaction.commit();*/

        // so now implement onNavigationItemselected
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
/*Fragment miFragment=null;
boolean fragmentSeleccionado=false;*/
        int id = menuItem.getItemId();


                if (id == R.id.home) {
                MenuFragment fragment = new MenuFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
                fragmentTransaction.commit();
            }
            else if (id == R.id.Clientes) {
                ClientesFragment fragment = new ClientesFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Clientes");
                fragmentTransaction.commit();
            }
            else if (id == R.id.Activos) {
                SchoolFragment fragment = new SchoolFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Activos");
                fragmentTransaction.commit();
            }
            else if (id == R.id.Promos) {
                ClientesFragmentF fragment = new ClientesFragmentF();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Promociones");
                fragmentTransaction.commit();
            }
            else if (id == R.id.Servicios) {
                CarrerasFragment fragment = new CarrerasFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Servicios");
                fragmentTransaction.commit();
            }
            else if (id == R.id.Proveedor) {
                ProveedorFragment fragment = new ProveedorFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Proveedores");
                fragmentTransaction.commit();
            }

            else if (id == R.id.setting) {
                SettingFragment fragment = new SettingFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Informe");
                fragmentTransaction.commit();
            }
            else if (id == R.id.logout) {
                LogoutFragment fragment = new LogoutFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment, "Logout");
                fragmentTransaction.commit();
            }

        
       
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    
    @Override
    public void onFragmentInteraction(Uri uri) {
    
    }
}
