package co.original.codigo.ems_tracker.services.repository;

import co.original.codigo.ems_tracker.helpers.socket_io.SocketConnection;
import co.original.codigo.ems_tracker.models.SocketMessageObject;
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
    public void updateGPSPosition(SocketMessageObject socketMessageObject) {
        if (socketConnection.isSocketConnected()){
            boolean socketMessage = socketConnection.emitSocketMessage(socketMessageObject.getMethod(), socketMessageObject.serializeObject());
            if (socketMessage){
                onUpdateGPSPositionSuccess(socketMessageObject);
            }else{
                onUpdateGPSPositionFailure(socketMessageObject);
            }
        }else{
            onUpdateGPSPositionFailure(socketMessageObject);
        }
    }

    private void onUpdateGPSPositionSuccess(SocketMessageObject socketMessageObject) {
        interactor.onUpdateSuccess(socketMessageObject);
    }

    private void onUpdateGPSPositionFailure(SocketMessageObject socketMessageObject) {
        interactor.onUpdateFailure(socketMessageObject);
    }

}
