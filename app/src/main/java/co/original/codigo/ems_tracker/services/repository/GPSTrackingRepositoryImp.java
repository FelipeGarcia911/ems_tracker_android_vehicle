package co.original.codigo.ems_tracker.services.repository;

import co.original.codigo.ems_tracker.helpers.Constants;
import co.original.codigo.ems_tracker.helpers.socket_io.SocketConnection;
import co.original.codigo.ems_tracker.models.SocketMessageObject;
import co.original.codigo.ems_tracker.models.TrackingGPSObject;
import co.original.codigo.ems_tracker.services.interactor.GPSTrackingInteractor;
import co.original.codigo.ems_tracker.services.interactor.GPSTrackingInteractorImp;

public class GPSTrackingRepositoryImp implements GPSTrackingRepository {

    private GPSTrackingInteractor interactor;
    private SocketConnection socketConnection;

    public GPSTrackingRepositoryImp(GPSTrackingInteractorImp interactor) {
        this.interactor = interactor;
        this.socketConnection = SocketConnection.getInstance();
        socketConnection.initSocketConn();
    }

    @Override
    public void updateGPSPosition(TrackingGPSObject trackingGPSObject) {
        if (socketConnection.isSocketConnected()){

            SocketMessageObject socketMessageObject = new SocketMessageObject();
            socketMessageObject.setMethod(Constants.UPDATE_VEHICLE_POSITION_REQUEST);
            socketMessageObject.setData(trackingGPSObject.toJsonObject());

            boolean socketMessage = socketConnection.emitSocketMessage(socketMessageObject.getMethod(),socketMessageObject.toJsonObject());
            if (socketMessage){
                onUpdateGPSPositionSuccess(trackingGPSObject);
            }else{
                onUpdateGPSPositionFailure(trackingGPSObject);
            }
        }else{
            onUpdateGPSPositionFailure(trackingGPSObject);
        }
    }

    private void onUpdateGPSPositionSuccess(TrackingGPSObject trackingGPSObject) {
        interactor.onUpdateSuccess(trackingGPSObject);
    }

    private void onUpdateGPSPositionFailure(TrackingGPSObject trackingGPSObject) {
        interactor.onUpdateFailure(trackingGPSObject);
    }

}
