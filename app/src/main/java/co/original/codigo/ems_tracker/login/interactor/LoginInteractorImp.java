package co.original.codigo.ems_tracker.login.interactor;

import co.original.codigo.ems_tracker.login.presenter.LoginPresenter;
import co.original.codigo.ems_tracker.login.repository.LoginRepository;
import co.original.codigo.ems_tracker.login.repository.LoginRepositoryImp;

public class LoginInteractorImp implements LoginInteractor {

    private LoginPresenter presenter;
    private LoginRepository repository;

    public LoginInteractorImp(LoginPresenter presenter) {
        this.presenter = presenter;
        this.repository = new LoginRepositoryImp(this);
    }

    @Override
    public void doLogin(String user, String password) {
        boolean isUserValidate = false;
        boolean isPasswordValidate = false;

        if (user != null && !user.isEmpty()){
            isUserValidate = true;
        }else{
            presenter.showUserError("Usuario inválido");
        }

        if (password != null && !password.isEmpty()){
            isPasswordValidate = true;
        }else{
            presenter.showPasswordError("Contraseña inválida");
        }

        if (isUserValidate && isPasswordValidate){
            presenter.cleanErrorMsgs();
            presenter.showLoader();
            repository.loginService(user, password);
        }
    }

    @Override
    public void onLoginSuccess() {
        presenter.onLoginSuccess();
    }

    @Override
    public void onLoginFailure() {
        presenter.onLoginFailure("Error iniciando sesión, intentelo de nuevo.");
    }
}
