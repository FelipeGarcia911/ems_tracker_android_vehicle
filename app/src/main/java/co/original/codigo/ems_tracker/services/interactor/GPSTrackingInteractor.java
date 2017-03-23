package co.original.codigo.ems_tracker.services.interactor;

import co.original.codigo.ems_tracker.models.SocketMessageObject;

/**
 * Created by Felipe Garcia on 22/03/2017 - 10:22 PM.
 */

public interface GPSTrackingInteractor {
    // Service Life Methods
    void onStart();
    void onStop();

    //Update Position Methos
    void updateGPSPosition(String currentLatitude, String currentLongitude);
    void onUpdateSuccess(SocketMessageObject socketMessageObject);
    void onUpdateFailure(SocketMessageObject messageObject);
}
