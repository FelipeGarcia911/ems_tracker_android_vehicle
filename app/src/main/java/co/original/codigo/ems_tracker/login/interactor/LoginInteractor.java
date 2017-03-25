package co.original.codigo.ems_tracker.login.interactor;

import android.content.Context;

public interface LoginInteractor {

    void checkVehicleLogged();
    void doLogin(String user, String password, Context context);
    void doLogout(String vehicleId);

    void onCreate();
    void onDestroy();
}
