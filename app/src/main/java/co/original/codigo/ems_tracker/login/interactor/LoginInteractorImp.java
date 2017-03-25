package co.original.codigo.ems_tracker.login.interactor;

import android.content.Context;

import org.greenrobot.eventbus.Subscribe;

import co.original.codigo.ems_tracker.eventBusEvents.LoginEvent;
import co.original.codigo.ems_tracker.helpers.eventBus.EventBus;
import co.original.codigo.ems_tracker.helpers.eventBus.GreenRobotEventBus;
import co.original.codigo.ems_tracker.login.repository.LoginRepository;
import co.original.codigo.ems_tracker.login.repository.LoginRepositoryImp;
import co.original.codigo.ems_tracker.models.VehicleObject;

public class LoginInteractorImp implements LoginInteractor {

    private EventBus eventBus;
    private LoginRepository loginRepository;

    public LoginInteractorImp() {
        this.loginRepository = new LoginRepositoryImp();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void checkVehicleLogged() {
        VehicleObject vehicleObject = loginRepository.getVehicleObjFromLocalStorage();
        if (vehicleObject != null){
            onLoginSuccess(vehicleObject);
        }
    }

    @Override
    public void doLogin(String user, String password, Context context) {
        loginRepository.loginVehicle(user, password, context);
    }

    @Override
    public void doLogout(String vehicleId) {
        loginRepository.logoutVehicle(vehicleId);
    }

    @Subscribe
    public void onLoginEvent(LoginEvent event){
        switch (event.getEventType()){
            case LoginEvent.ON_LOGIN_SUCCESS_INTERACTOR:
                onLoginSuccess(event.getVehicleObject());
                break;
            case LoginEvent.ON_LOGIN_FAILURE_INTERACTOR:
                onLoginFailure(event.getErrorMessage());
        }
    }

    private void onLoginFailure(String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(LoginEvent.ON_LOGIN_FAILURE_PRESENTER);
        loginEvent.setErrorMessage(errorMessage);
        posEvent(loginEvent);
    }

    private void onLoginSuccess(VehicleObject vehicleObject) {
        loginRepository.saveVehicleObjInLocalStorage(vehicleObject);

        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(LoginEvent.ON_LOGIN_SUCCESS_PRESENTER);
        posEvent(loginEvent);
    }

    @Override
    public void onCreate(){
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    //-------------------------------------------------------------------------------------------------------------------

    private void posEvent(LoginEvent event){
        eventBus.post(event);
    }

    //-------------------------------------------------------------------------------------------------------------------
}
