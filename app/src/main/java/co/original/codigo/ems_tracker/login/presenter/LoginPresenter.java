package co.original.codigo.ems_tracker.login.presenter;

public interface LoginPresenter {
    void onLoginAction();
    void onLoginSuccess();
    void onLoginFailure(String errorMsg);

    void showUserError(String errorMsg);
    void showPasswordError(String errorMsg);
    void cleanErrorMsgs();

    void showLoader();
}
