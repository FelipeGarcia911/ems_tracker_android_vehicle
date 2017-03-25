package co.original.codigo.ems_tracker.eventBusEvents;

import co.original.codigo.ems_tracker.models.VehicleObject;

/**
 * Created by Felipe Garcia on 25/03/2017 - 12:43 PM.
 */

public class LoginEvent {

    private int eventType;
    private String errorMessage;

    private VehicleObject vehicleObject;

    public static final int ON_LOGIN_SUCCESS_PRESENTER = 00;
    public static final int ON_LOGIN_FAILURE_PRESENTER = 01;

    public static final int ON_LOGIN_SUCCESS_INTERACTOR = 10;
    public static final int ON_LOGIN_FAILURE_INTERACTOR = 11;

    public static final int ON_LOGOUT_SUCCESS_PRESENTER = 20;
    public static final int ON_LOGOUT_FAILURE_PRESENTER = 21;

    public static final int ON_LOGOUT_SUCCESS_INTERACTOR = 30;
    public static final int ON_LOGOUT_FAILURE_INTERACTOR = 41;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public VehicleObject getVehicleObject() {
        return vehicleObject;
    }

    public void setVehicleObject(VehicleObject vehicleObject) {
        this.vehicleObject = vehicleObject;
    }

}
