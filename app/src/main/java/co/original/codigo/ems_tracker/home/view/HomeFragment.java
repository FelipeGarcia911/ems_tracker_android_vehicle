package co.original.codigo.ems_tracker.home.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.original.codigo.ems_tracker.R;
import co.original.codigo.ems_tracker.helpers.Contansts;

public class HomeFragment extends Fragment implements HomeFragmentView{

    public static final String FRAGMENT_NAME = Contansts.HOME_FRAGMENT_NAME;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void updateLocationText(String string) {

    }

    @Override
    public void updateLongitudeText(String string) {

    }

    @Override
    public void updateConnStatusText(String string) {

    }

    @Override
    public void updateConnStatusColor(String color) {

    }
}
