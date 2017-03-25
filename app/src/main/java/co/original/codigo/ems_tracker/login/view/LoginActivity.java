package co.original.codigo.ems_tracker.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import co.original.codigo.ems_tracker.R;
import co.original.codigo.ems_tracker.helpers.SimpleProgressDialogHelper;
import co.original.codigo.ems_tracker.helpers.localStorage.SharedPreferencesHelper;
import co.original.codigo.ems_tracker.helpers.localStorage.VehicleLocalStorage;
import co.original.codigo.ems_tracker.login.presenter.LoginPresenter;
import co.original.codigo.ems_tracker.login.presenter.LoginPresenterImp;
import co.original.codigo.ems_tracker.main.view.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginActivityView{

    private Unbinder unbinder;
    private LoginPresenter presenter;
    private SimpleProgressDialogHelper progressDialogHelper;

    private VehicleLocalStorage vehicleLocalStorage;
    private SharedPreferencesHelper sharedPreferencesHelper;

    @BindView(R.id.signUpButton) Button signUpButton;

    @BindView(R.id.inputUser) EditText inputUser;
    @BindView(R.id.inputPassword) EditText inputPassword;

    @BindView(R.id.layoutInputUser) TextInputLayout layoutInputUser;
    @BindView(R.id.layoutInputPassword) TextInputLayout layoutInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initInterface();
        initSingletons();
        initClasses();

        presenter.onCreate();
    }

    private void initSingletons() {
        sharedPreferencesHelper = SharedPreferencesHelper.getInstance();
        sharedPreferencesHelper.initialize(this);

        vehicleLocalStorage = VehicleLocalStorage.getInstance();
        vehicleLocalStorage.initialize();
    }

    private void initInterface(){
        unbinder = ButterKnife.bind(this);
    }

    private void initClasses(){
        progressDialogHelper = new SimpleProgressDialogHelper(this);
        presenter = new LoginPresenterImp(this);
    }

    //----------------------------------------------------------------------------------------------

    @OnClick(R.id.signUpButton)
    void onSignInButtonClick(){
        presenter.onLoginAction();
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void showProgressDialog(String message) {
        if (progressDialogHelper != null) {
            progressDialogHelper.setCancelable(false);
            progressDialogHelper.setMessage(message);
            progressDialogHelper.showProgressDialog();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialogHelper != null) {
            progressDialogHelper.hideProgressDialog();
        }
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public String getUserLogin() {
        return inputUser.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return inputPassword.getText().toString();
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void showUserErrorMsg(String msgError) {
        layoutInputUser.setError(msgError);
    }

    @Override
    public void showPasswordErrorMsg(String msgError) {
        layoutInputPassword.setError(msgError);
    }

    @Override
    public void cleanUserErrorMsg() {
        layoutInputUser.setError(null);
    }

    @Override
    public void cleanPasswordErrorMsg() {
        layoutInputPassword.setError(null);
    }

    //----------------------------------------------------------------------------------------------


    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
