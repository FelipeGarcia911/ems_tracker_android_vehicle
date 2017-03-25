package co.original.codigo.ems_tracker.login.repository;

import android.content.Context;

import co.original.codigo.ems_tracker.models.VehicleObject;

public interface LoginRepository {

    // Login Functions
    void logoutVehicle(String userId);
    void loginVehicle(String user, String password, Context context);

    // Vehicle Object Functions
    void saveVehicleObjInLocalStorage(VehicleObject vehicleObject);
    void deleteVehicleObjFromLocalStorage();
    VehicleObject getVehicleObjFromLocalStorage();
}
