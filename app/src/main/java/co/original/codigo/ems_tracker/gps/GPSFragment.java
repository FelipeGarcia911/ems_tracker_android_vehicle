package co.original.codigo.ems_tracker.gps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.original.codigo.ems_tracker.R;
import co.original.codigo.ems_tracker.helpers.Constants;

public class GPSFragment extends Fragment implements OnMapReadyCallback{

    public static final String FRAGMENT_NAME = Constants.GPS_FRAGMENT_NAME;

    private GoogleMap mMap;
    private Unbinder unbinder;

    public GPSFragment() {
        // Required empty public constructor
    }

    public static GPSFragment newInstance() {
        return new GPSFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gps, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this,view);
        setupGoogleMap();
    }

    private void setupGoogleMap(){
        SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapGPS);
        mapFrag.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

}
