package co.original.codigo.ems_tracker.login.view;

public interface LoginActivityView {

    void showMessage(String message);
    void hideProgressDialog();
    void showProgressDialog(String message);

    // Login Functions
    void onLoginSuccess();
    String getUserLogin();
    String getUserPassword();

    void showUserErrorMsg(String msgError);

    void showPasswordErrorMsg(String msgError);

    void cleanUserErrorMsg();

    void cleanPasswordErrorMsg();
}
