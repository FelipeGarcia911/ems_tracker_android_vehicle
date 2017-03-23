package co.original.codigo.ems_tracker.services.interactor;

import java.util.ArrayList;

import co.original.codigo.ems_tracker.helpers.Contansts;
import co.original.codigo.ems_tracker.helpers.localStorage.VehicleLocalStorage;
import co.original.codigo.ems_tracker.models.SocketMessageObject;
import co.original.codigo.ems_tracker.models.TrackingGPSObject;
import co.original.codigo.ems_tracker.models.VehicleObject;
import co.original.codigo.ems_tracker.services.repository.GPSTrackingRepository;
import co.original.codigo.ems_tracker.services.repository.GPSTrackingRepositoryImp;
import co.original.codigo.ems_tracker.services.service.GPSTrackingService;

/**
 * Created by Felipe Garcia on 22/03/2017 - 10:22 PM.
 */

public class GPSTrackingInteractorImp implements GPSTrackingInteractor {

    private GPSTrackingRepository repository;
    private GPSTrackingService service;
    private ArrayList<TrackingGPSObject> pendingLocations;

    private VehicleObject vehicleObject;

    public GPSTrackingInteractorImp(GPSTrackingService service) {
        this.service = service;
        this.repository = new GPSTrackingRepositoryImp(this);
        this.vehicleObject = getVehicleData();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void updateGPSPosition(String currentLatitude, String currentLongitude) {
        if (isVehicleObjectValid()){
            TrackingGPSObject trackingGPSObject = new TrackingGPSObject();
            trackingGPSObject.setId(vehicleObject.getId());
            trackingGPSObject.setLatitude(currentLatitude);
            trackingGPSObject.setLongitude(currentLongitude);

            sendGPSPosition(trackingGPSObject);
        }
    }

    private void sendGPSPosition(TrackingGPSObject trackingGPSObject) {
        SocketMessageObject socketMessageObject = new SocketMessageObject();
        socketMessageObject.setMethod(Contansts.PUSH_GPS_POSITION);
        socketMessageObject.setData(trackingGPSObject);
        repository.updateGPSPosition(socketMessageObject);
    }

    private void addPendingPositions(TrackingGPSObject gpsObject){
        pendingLocations.add(gpsObject);
    }

    private void removePendingPosition(TrackingGPSObject gpsObject){
        pendingLocations.remove(gpsObject);
    }

    @Override
    public void onUpdateSuccess(SocketMessageObject socketMessageObject) {

    }

    @Override
    public void onUpdateFailure(SocketMessageObject messageObject) {

    }

    private VehicleObject getVehicleData(){
        VehicleLocalStorage vehicleLocalStorage = VehicleLocalStorage.getInstance();
        return vehicleLocalStorage.getVehicle();
    }

    private void vehicleDataFailure() {

    }

    private boolean isVehicleObjectValid(){
        return vehicleObject != null;
    }
}
