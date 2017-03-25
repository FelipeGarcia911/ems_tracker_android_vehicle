package co.original.codigo.ems_tracker.main.presenter;

import co.original.codigo.ems_tracker.main.interactor.MainInteractor;
import co.original.codigo.ems_tracker.main.interactor.MainInteractorImp;
import co.original.codigo.ems_tracker.main.view.MainActivity;

public class MainPresenterImp implements MainPresenter {

    private final MainActivity view;
    private MainInteractor interactor;

    public MainPresenterImp(MainActivity view) {
        this.view = view;
        this.interactor = new MainInteractorImp();
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
            interactor.logoutAction(view);
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
            view.checkGPSPermissions();
        }
    }

    @Override
    public void isGPSPermissionsGranted() {
        if (view != null){
            interactor.startGPSLocation(view);
        }
    }
}
