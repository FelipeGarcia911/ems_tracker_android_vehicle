package co.original.codigo.ems_tracker.services;

import co.original.codigo.ems_tracker.socket_io.SocketConnection;

public class ServiceRepositoryImp implements ServiceRepository {
    private SocketConnection socketConnection;

    public ServiceRepositoryImp() {
        this.socketConnection = SocketConnection.getInstance();
    }

    @Override
    public void updateGPSPosition() {

    }
}
