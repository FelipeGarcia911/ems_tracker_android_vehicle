package co.original.codigo.ems_tracker.main.view;

public interface MainActivityView {

    // Navigation Functions
    void goToGPSFragment();
    void goToHomeFragment();
    void goToAboutFragment();

    void startGPSTrackingService();
    void stopGPSTrackingService();
}
