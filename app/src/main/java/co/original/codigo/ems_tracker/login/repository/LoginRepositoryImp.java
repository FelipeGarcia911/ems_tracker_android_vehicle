package co.original.codigo.ems_tracker.login.repository;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import co.original.codigo.ems_tracker.eventBusEvents.LoginEvent;
import co.original.codigo.ems_tracker.helpers.ApiHelper;
import co.original.codigo.ems_tracker.helpers.Constants;
import co.original.codigo.ems_tracker.helpers.HttpConnectionHelper;
import co.original.codigo.ems_tracker.helpers.eventBus.EventBus;
import co.original.codigo.ems_tracker.helpers.eventBus.GreenRobotEventBus;
import co.original.codigo.ems_tracker.helpers.httpClient.AsyncHttpClientHelper;
import co.original.codigo.ems_tracker.helpers.localStorage.VehicleLocalStorage;
import co.original.codigo.ems_tracker.models.VehicleObject;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class LoginRepositoryImp implements LoginRepository {

    private final HttpConnectionHelper connectionHelper;
    private VehicleLocalStorage vehicleLocalStorage;
    private final AsyncHttpClient asyncHttpClient;
    private ApiHelper apiHelper;
    private EventBus eventBus;

    private String LOGIN_URL_CONN = Constants.URL_API_CONN + Constants.API_LOGIN_VEHICLE;

    public LoginRepositoryImp() {
        this.apiHelper = new ApiHelper();
        this.eventBus = GreenRobotEventBus.getInstance();
        this.connectionHelper = new HttpConnectionHelper();
        this.vehicleLocalStorage = VehicleLocalStorage.getInstance();
        this.asyncHttpClient = new AsyncHttpClientHelper().getAsyncHttpClient();
    }

    //-------------------------------------------------------------------------------------------------------------------

    @Override
    public void logoutVehicle(String userId) {
        deleteVehicleObjFromLocalStorage();
    }

    //-------------------------------------------------------------------------------------------------------------------

    @Override
    public void loginVehicle(String user, String password, Context context) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("user",user);
        hashMap.put("password", password);
        StringEntity jsonRequestParams = connectionHelper.createJsonRequestParams(hashMap,HttpConnectionHelper.APPLICATION_JSON_CONTENT_TYPE);

        asyncHttpClient.addHeader("Content-Type",HttpConnectionHelper.APPLICATION_JSON_CONTENT_TYPE);
        asyncHttpClient.post(context, LOGIN_URL_CONN, jsonRequestParams, HttpConnectionHelper.APPLICATION_JSON_CONTENT_TYPE, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == HttpConnectionHelper.SUCCESS){
                    JSONObject jsonObjectResponse = connectionHelper.stringBuilderJSONObject(responseBody);
                    handleLoginSuccess(jsonObjectResponse);
                }else{
                    Log.e("loginVehicle-onSuccess","ErrorCode:"+String.valueOf(statusCode));
                    onLoginFailure("");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                connectionHelper.handleOnFailureRequest(responseBody);
                onLoginFailure("Verifique su conexión a internet.");
            }
        });
    }

    private void handleLoginSuccess(JSONObject jsonObjectResponse){
        int statusCode = apiHelper.getStatusCodeFromJsonResponse(jsonObjectResponse);
        switch (statusCode){
            case 0:
                break;
            case Constants.API_SUCCESS:
                onLoginSuccess(jsonObjectResponse);
                break;
            case Constants.API_FAILURE:
                String errorMessage = apiHelper.getMessageFromJsonResponse(jsonObjectResponse);
                onLoginFailure(errorMessage);
                break;
        }
    }

    private void onLoginSuccess(JSONObject jsonObjectResponse){
        VehicleObject vehicleObject = createVehicleObjectFromJsonResponse(jsonObjectResponse);
        if (vehicleObject != null){
            LoginEvent loginEvent = new LoginEvent();
            loginEvent.setEventType(LoginEvent.ON_LOGIN_SUCCESS_INTERACTOR);
            loginEvent.setVehicleObject(vehicleObject);
            posEvent(loginEvent);
        }else{
            onLoginFailure("Error en los datos del vehículo.");
        }
    }

    private void onLoginFailure(String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(LoginEvent.ON_LOGIN_FAILURE_INTERACTOR);
        loginEvent.setErrorMessage(errorMessage);
        posEvent(loginEvent);
    }

    //-------------------------------------------------------------------------------------------------------------------

    @Override
    public void saveVehicleObjInLocalStorage(VehicleObject vehicleObject) {
        vehicleLocalStorage.setVehicle(vehicleObject);
    }

    @Override
    public void deleteVehicleObjFromLocalStorage() {
        vehicleLocalStorage.deleteVehicle();
    }

    @Override
    public VehicleObject getVehicleObjFromLocalStorage() {
        return vehicleLocalStorage.getVehicle();
    }

    //-------------------------------------------------------------------------------------------------------------------

    private VehicleObject createVehicleObjectFromJsonResponse(JSONObject jsonResponseObject){
        JSONObject jsonVehicleData = apiHelper.getDataFromJSONObject(jsonResponseObject);
        if (jsonVehicleData != null){
            try {
                VehicleObject vehicleObject = new VehicleObject();

                String vehicleId    = jsonVehicleData.getString("_id");
                String user         = jsonVehicleData.getString("user");
                String password     = jsonVehicleData.getString("password");
                String description  = jsonVehicleData.getString("description");
                String name         = jsonVehicleData.getString("name");

                vehicleObject.setId(vehicleId);
                vehicleObject.setUser(user);
                vehicleObject.setPassword(password);
                vehicleObject.setName(name);
                vehicleObject.setDescription(description);

                return vehicleObject;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            return null;
        }
    }

    //-------------------------------------------------------------------------------------------------------------------

    private void posEvent(LoginEvent event){
        eventBus.post(event);
    }

    //-------------------------------------------------------------------------------------------------------------------
}
