package co.original.codigo.ems_tracker.services.repository;

import co.original.codigo.ems_tracker.models.TrackingGPSObject;

public interface GPSTrackingRepository {
    void updateGPSPosition(TrackingGPSObject messageObject);
}
