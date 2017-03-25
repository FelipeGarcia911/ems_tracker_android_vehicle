package co.original.codigo.ems_tracker.services.interactor;

import co.original.codigo.ems_tracker.models.TrackingGPSObject;

public interface GPSTrackingInteractor {
    // Service Life Methods
    void onStart();
    void onStop();

    //Update Position Methos
    void updateGPSPosition(String currentLatitude, String currentLongitude);
    void onUpdateSuccess(TrackingGPSObject socketMessageObject);
    void onUpdateFailure(TrackingGPSObject messageObject);
}
