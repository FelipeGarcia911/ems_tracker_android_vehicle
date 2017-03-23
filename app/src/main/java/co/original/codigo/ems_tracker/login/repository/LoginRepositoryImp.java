package co.original.codigo.ems_tracker.login.repository;

import co.original.codigo.ems_tracker.login.interactor.LoginInteractor;

public class LoginRepositoryImp implements LoginRepository {
    private LoginInteractor interactor;

    public LoginRepositoryImp(LoginInteractor interactor) {
        this.interactor = interactor;
    }

    //---------------------------------------------------------------------------------------------

    @Override
    public void loginService(String user, String password) {
        handleLoginFailure();
    }

    private void handleLoginStatusCode(){}


    private void handleLoginSuccess(){
        interactor.onLoginSuccess();
    }
    private void handleLoginFailure(){
        interactor.onLoginFailure();
    }

    //---------------------------------------------------------------------------------------------
}
