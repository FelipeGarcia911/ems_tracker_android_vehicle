package co.original.codigo.ems_tracker.main.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.original.codigo.ems_tracker.R;
import co.original.codigo.ems_tracker.about.AboutFragment;
import co.original.codigo.ems_tracker.gps.GPSFragment;
import co.original.codigo.ems_tracker.helpers.PermissionsHelper;
import co.original.codigo.ems_tracker.helpers.localStorage.SharedPreferencesHelper;
import co.original.codigo.ems_tracker.helpers.localStorage.VehicleLocalStorage;
import co.original.codigo.ems_tracker.home.view.HomeFragment;
import co.original.codigo.ems_tracker.main.presenter.MainPresenter;
import co.original.codigo.ems_tracker.main.presenter.MainPresenterImp;

public class MainActivity extends AppCompatActivity implements MainActivityView, NavigationView.OnNavigationItemSelectedListener {

    private MainPresenter presenter;
    private FragmentManager fragmentManager;

    private NavigationView navigationView;

    private VehicleLocalStorage vehicleLocalStorage;
    private SharedPreferencesHelper sharedPreferencesHelper;

    private Fragment homeFragment;
    private Fragment gpsFragment;
    private Fragment aboutFragment;
    private Fragment currentFragment;

    private Unbinder unbinder;

    private PermissionsHelper permissionsHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initClasses();
        initializeSingletons();
        initFragments();
        initPresenter();
    }

    //----------------------------------------------------------------------------------------------

    private void initPresenter(){
        presenter = new MainPresenterImp(this);
        presenter.onCreate();
    }

    private void initFragments() {
        homeFragment = HomeFragment.newInstance();
        gpsFragment = GPSFragment.newInstance();
        aboutFragment = AboutFragment.newInstance();
    }

    private void initClasses(){
        unbinder = ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
    }

    private void initializeSingletons(){
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance();
        sharedPreferencesHelper.initialize(this);

        vehicleLocalStorage = VehicleLocalStorage.getInstance();
        vehicleLocalStorage.initialize();

        permissionsHelper = PermissionsHelper.getInstance();
        permissionsHelper.initialize(this);


    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void checkGPSPermissions() {
        if (permissionsHelper.isShowPermissionRequired()){
            if (permissionsHelper.isGPSPermissionsGranted()) {
                presenter.isGPSPermissionsGranted();
            }else{
                requireGPSPermissions();
            }
        }else{
            presenter.isGPSPermissionsGranted();
        }
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            presenter.onNavToHomeButtonClick();
        } else if (id == R.id.nav_gps) {
            presenter.onNavToGPSButtonClick();
        } else if (id == R.id.nav_logout) {
            presenter.onLogoutButtonClick();
        } else if (id == R.id.nav_about) {
            presenter.onNavToAboutButtonClick();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void goToHomeFragment() {
        executeFragmentTransaction(homeFragment, HomeFragment.FRAGMENT_NAME);
        navigationView.setCheckedItem(R.id.nav_home);
        setTitle(HomeFragment.FRAGMENT_NAME);
    }

    @Override
    public void goToGPSFragment() {
        executeFragmentTransaction(gpsFragment,GPSFragment.FRAGMENT_NAME);
        navigationView.setCheckedItem(R.id.nav_gps);
        setTitle(GPSFragment.FRAGMENT_NAME);
    }

    @Override
    public void goToAboutFragment() {
        executeFragmentTransaction(gpsFragment,GPSFragment.FRAGMENT_NAME);
        navigationView.setCheckedItem(R.id.nav_about);
        setTitle(GPSFragment.FRAGMENT_NAME);
    }

    private void executeFragmentTransaction(final Fragment fragment, final String fragmentName){
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.container_all, fragment, fragmentName)
                .commit();
        fragmentManager.executePendingTransactions();
    }

    //----------------------------------------------------------------------------------------------

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requireGPSPermissions(){
        List<String> listPermissionRequired = permissionsHelper.getListGPSPermissionRequired();
        if (listPermissionRequired.size() > 0) {
            String[] permission = listPermissionRequired.toArray(new String[0]);
            requestPermissions(permission,PermissionsHelper.GPS_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == permissionsHelper.GPS_PERMISSIONS){
            if (permissionsHelper.isGPSPermissionsGranted()) {
                presenter.isGPSPermissionsGranted();
            }else{
                Toast.makeText(this,"Rastreo GPS no Iniciado!!",Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //----------------------------------------------------------------------------------------------

}
