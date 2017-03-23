package co.original.codigo.ems_tracker.login.presenter;

import co.original.codigo.ems_tracker.login.interactor.LoginInteractor;
import co.original.codigo.ems_tracker.login.interactor.LoginInteractorImp;
import co.original.codigo.ems_tracker.login.view.LoginActivity;

public class LoginPresenterImp implements LoginPresenter {

    private LoginActivity view;
    private LoginInteractor interactor;

    public LoginPresenterImp(LoginActivity view) {
        this.view = view;
        this.interactor = new LoginInteractorImp(this);
    }

    @Override
    public void onLoginAction() {
        if (view != null){
            String user = view.getUserLogin();
            String password = view.getUserPassword();
            interactor.doLogin(user, password);
        }
    }

    @Override
    public void onLoginSuccess() {
        if (view != null){
            view.hideProgressDialog();
            view.onLoginSuccess();
        }
    }

    @Override
    public void onLoginFailure(String errorMsg) {
        if (view != null){
            view.hideProgressDialog();
            view.showMessage(errorMsg);
        }
    }

    @Override
    public void showUserError(String errorMsg) {
        if (view != null){
            view.showUserErrorMsg(errorMsg);
        }
    }

    @Override
    public void showPasswordError(String errorMsg) {
        if (view != null){
            view.showPasswordErrorMsg(errorMsg);
        }
    }

    @Override
    public void cleanErrorMsgs() {
        if (view != null){
            view.cleanUserErrorMsg();
            view.cleanPasswordErrorMsg();
        }
    }

    @Override
    public void showLoader() {
        if (view != null){
            view.showProgressDialog("Iniciando sesión...");
        }
    }
}
