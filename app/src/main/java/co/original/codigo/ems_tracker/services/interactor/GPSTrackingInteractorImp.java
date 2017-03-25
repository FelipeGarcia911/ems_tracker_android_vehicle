package co.original.codigo.ems_tracker.services.interactor;

import co.original.codigo.ems_tracker.eventBusEvents.LocationUpdateEvent;
import co.original.codigo.ems_tracker.helpers.eventBus.EventBus;
import co.original.codigo.ems_tracker.helpers.eventBus.GreenRobotEventBus;
import co.original.codigo.ems_tracker.helpers.localStorage.VehicleLocalStorage;
import co.original.codigo.ems_tracker.models.TrackingGPSObject;
import co.original.codigo.ems_tracker.models.VehicleObject;
import co.original.codigo.ems_tracker.services.repository.GPSTrackingRepository;
import co.original.codigo.ems_tracker.services.repository.GPSTrackingRepositoryImp;
import co.original.codigo.ems_tracker.services.service.GPSTrackingService;

public class GPSTrackingInteractorImp implements GPSTrackingInteractor {

    private GPSTrackingRepository repository;
    private GPSTrackingService service;
    private final EventBus eventBus;

    private VehicleObject vehicleObject;

    public GPSTrackingInteractorImp(GPSTrackingService service) {
        this.service = service;
        this.eventBus = GreenRobotEventBus.getInstance();
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

            repository.updateGPSPosition(trackingGPSObject);
        }
    }

    @Override
    public void onUpdateSuccess(TrackingGPSObject socketMessageObject) {

    }

    @Override
    public void onUpdateFailure(TrackingGPSObject messageObject) {

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

    private void publishUpdateLocationEvent(String latitude, String logitude){
        LocationUpdateEvent event = new LocationUpdateEvent();
        event.setEventType(LocationUpdateEvent.ON_LOCATION_UPDATE);
        event.setLatitude(latitude);
        event.setLongitude(logitude);
        postEvent(event);
    }

    private void postEvent(Object event){
        eventBus.post(event);
    }
}
