package co.original.codigo.ems_tracker.main.interactor;

import android.app.Activity;
import android.content.Intent;

import co.original.codigo.ems_tracker.login.repository.LoginRepository;
import co.original.codigo.ems_tracker.login.repository.LoginRepositoryImp;
import co.original.codigo.ems_tracker.login.view.LoginActivity;
import co.original.codigo.ems_tracker.models.VehicleObject;
import co.original.codigo.ems_tracker.services.service.GPSTrackingService;

/**
 * Created by Felipe Garcia on 25/03/2017 - 3:38 PM.
 */

public class MainInteractorImp implements MainInteractor {

    private Intent gpsTrackingService;
    private LoginRepository loginRepository;

    public MainInteractorImp() {
        loginRepository = new LoginRepositoryImp();

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void startGPSLocation(Activity activity) {
        VehicleObject vehicleObject = loginRepository.getVehicleObjFromLocalStorage();
        if (vehicleObject != null ){
            startGPSService(activity, vehicleObject.getId(), vehicleObject.getName());
        }
    }

    @Override
    public void stopGPSLocation(Activity activity) {
        if (gpsTrackingService != null){
            activity.stopService(gpsTrackingService);
        }
    }

    @Override
    public void logoutAction(Activity activity) {
        stopGPSLocation(activity);
        loginRepository.deleteVehicleObjFromLocalStorage();
        navToLoginActivity(activity);
    }

    private void startGPSService(Activity activity, String vehicleId, String vehicleName){
        gpsTrackingService = new Intent(activity, GPSTrackingService.class);

        gpsTrackingService.putExtra(GPSTrackingService.VEHICLE_ID, vehicleId);
        gpsTrackingService.putExtra(GPSTrackingService.VEHICLE_NAME, vehicleName);

        activity.startService(gpsTrackingService);
    }

    private void navToLoginActivity(Activity activity){
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
