package adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SeccionesAdapter extends FragmentStatePagerAdapter {
    private  final List<Fragment> ListaFragments=new ArrayList<>();
    private  final List<String> ListaTitulos=new ArrayList<>();
    
    public SeccionesAdapter(FragmentManager fm) {
        super(fm);
    }
    
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return ListaTitulos.get(position);
    }
    
    @Override
    public Fragment getItem(int position) {
        return ListaFragments.get(position);
    }
    
    @Override
    public int getCount() {
        return ListaFragments.size();
    }
    public void addFragments(Fragment fragment, String Titulo){
        ListaFragments.add(fragment);
        ListaTitulos.add(Titulo);
    }
}
