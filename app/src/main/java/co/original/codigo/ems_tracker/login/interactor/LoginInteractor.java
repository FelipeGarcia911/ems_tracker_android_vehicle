package co.original.codigo.ems_tracker.login.interactor;

public interface LoginInteractor {
    void doLogin(String user, String password);
    void onLoginSuccess();
    void onLoginFailure();
}
