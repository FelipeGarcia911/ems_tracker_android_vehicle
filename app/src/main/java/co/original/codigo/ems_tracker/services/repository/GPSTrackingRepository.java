package co.original.codigo.ems_tracker.services.repository;

import co.original.codigo.ems_tracker.models.SocketMessageObject;

public interface GPSTrackingRepository {
    void updateGPSPosition(SocketMessageObject messageObject);
}
