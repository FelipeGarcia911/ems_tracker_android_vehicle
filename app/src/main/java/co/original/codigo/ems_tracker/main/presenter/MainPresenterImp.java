package co.original.codigo.ems_tracker.main.presenter;

import co.original.codigo.ems_tracker.main.view.MainActivity;

public class MainPresenterImp implements MainPresenter {

    private final MainActivity view;

    public MainPresenterImp(MainActivity view) {
        this.view = view;
    }

    @Override
    public void onNavToHomeButtonClick() {
        if (view != null){
            view.goToHomeFragment();
        }
    }

    @Override
    public void onNavToGPSButtonClick() {
        if (view != null){
            view.goToGPSFragment();
        }
    }

    @Override
    public void onLogoutButtonClick() {
        if (view != null){
            view.stopGPSTrackingService();
        }
    }

    @Override
    public void onNavToAboutButtonClick() {
        if (view != null){
            view.goToAboutFragment();
        }
    }

    @Override
    public void onCreate() {
        if (view != null){
            view.goToHomeFragment();
            view.startGPSTrackingService();
        }
    }
}
