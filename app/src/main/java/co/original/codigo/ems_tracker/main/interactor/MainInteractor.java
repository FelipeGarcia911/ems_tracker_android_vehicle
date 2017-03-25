package co.original.codigo.ems_tracker.main.interactor;

import android.app.Activity;

public interface MainInteractor {

    void onCreate();

    void startGPSLocation(Activity activity);
    void stopGPSLocation(Activity activity);
    void logoutAction(Activity view);
}
