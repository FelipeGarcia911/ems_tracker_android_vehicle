package co.original.codigo.ems_tracker.services.service;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;

import co.original.codigo.ems_tracker.helpers.Contansts;
import co.original.codigo.ems_tracker.helpers.LocationHelper;
import co.original.codigo.ems_tracker.services.interactor.GPSTrackingInteractor;
import co.original.codigo.ems_tracker.services.interactor.GPSTrackingInteractorImp;

public class GPSTrackingService extends Service {

    private Handler handler;
    private LocationHelper locationHelper;
    private GPSTrackingInteractor interactor;

    private String currentLatitude;
    private String currentLongitude;

    private String TAG = "LocationHelper";

    public GPSTrackingService() {
        interactor = new GPSTrackingInteractorImp();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initRepetitiveGPSTask();
        return super.onStartCommand(intent, flags, startId);
    }

    private void initRepetitiveGPSTask(){
        locationHelper = new LocationHelper(getApplicationContext(), Contansts.GPS_TIME_UPDATE,Contansts.GPS_DISTANCE_UPDATE);
        handler = new Handler();
        handler.post(runnableGPSTracker);
    }

    private Runnable runnableGPSTracker = new Runnable() {
        @Override
        public void run() {
            Location lastKnownLocation = locationHelper.getLastKnownLocation();
            if (lastKnownLocation != null){
                currentLatitude = String.valueOf(lastKnownLocation.getLatitude());
                currentLongitude = String.valueOf(lastKnownLocation.getLongitude());
                interactor.updateGPSPosition(currentLatitude, currentLongitude);
            }
            handler.postDelayed(runnableGPSTracker, Contansts.GPS_TIME_UPDATE);
        }
    };

    @Override
    public void onDestroy() {
        handler.removeCallbacks(runnableGPSTracker);
        super.onDestroy();
    }
}
