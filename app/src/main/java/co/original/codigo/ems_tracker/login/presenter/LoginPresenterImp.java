package co.original.codigo.ems_tracker.login.presenter;

import org.greenrobot.eventbus.Subscribe;

import co.original.codigo.ems_tracker.eventBusEvents.LoginEvent;
import co.original.codigo.ems_tracker.helpers.eventBus.EventBus;
import co.original.codigo.ems_tracker.helpers.eventBus.GreenRobotEventBus;
import co.original.codigo.ems_tracker.login.interactor.LoginInteractor;
import co.original.codigo.ems_tracker.login.interactor.LoginInteractorImp;
import co.original.codigo.ems_tracker.login.view.LoginActivity;

public class LoginPresenterImp implements LoginPresenter {

    private LoginActivity view;
    private final EventBus eventBus;
    private LoginInteractor interactor;

    public LoginPresenterImp(LoginActivity view) {
        this.view       = view;
        this.eventBus   = GreenRobotEventBus.getInstance();
        this.interactor = new LoginInteractorImp();
    }

    @Subscribe
    public void onLoginEvent(LoginEvent event){
        switch (event.getEventType()){
            case LoginEvent.ON_LOGIN_SUCCESS_PRESENTER:
                onLoginSuccess();
                break;
            case LoginEvent.ON_LOGIN_FAILURE_PRESENTER:
                onLoginFailure(event.getErrorMessage());
        }
    }

    @Override
    public void onLoginAction() {
        if (view != null){
            String user = view.getUserLogin();
            String password = view.getUserPassword();

            cleanErrorMsgs();
            if (validateUser(user) && validatePassword(password)) {
                showLoader();
                interactor.doLogin(user, password, view.getApplicationContext());
            }
        }
    }

    private boolean validateUser(String string){
        boolean validate = string != null && !string.isEmpty();
        if (!validate) showUserError("Usuario inválido");
        return validate;
    }

    private boolean validatePassword(String string){
        boolean validate = string != null && !string.isEmpty();
        if (!validate) showPasswordError("Contraseña inválida");
        return validate;
    }

    private void onLoginSuccess() {
        if (view != null){
            view.hideProgressDialog();
            view.onLoginSuccess();
        }
    }

    private void onLoginFailure(String errorMsg) {
        if (view != null){
            view.hideProgressDialog();
            view.showMessage(errorMsg);
        }
    }

    private void showUserError(String errorMsg) {
        if (view != null){
            view.showUserErrorMsg(errorMsg);
        }
    }

    private void showPasswordError(String errorMsg) {
        if (view != null){
            view.showPasswordErrorMsg(errorMsg);
        }
    }

    private void cleanErrorMsgs() {
        if (view != null){
            view.cleanUserErrorMsg();
            view.cleanPasswordErrorMsg();
        }
    }

    private void showLoader() {
        if (view != null){
            view.showProgressDialog("Iniciando sesión...");
        }
    }

    @Override
    public void onCreate(){
        eventBus.register(this);

        interactor.onCreate();
        interactor.checkVehicleLogged();
    }

    @Override
    public void onDestroy() {
        interactor.onDestroy();
        eventBus.unregister(this);
    }
}
