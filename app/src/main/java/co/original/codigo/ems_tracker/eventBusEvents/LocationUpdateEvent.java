package co.original.codigo.ems_tracker.eventBusEvents;

/**
 * Created by Felipe Garcia on 25/03/2017 - 3:06 PM.
 */

public class LocationUpdateEvent{

    private int eventType;
    private String errorMessage;
    private String latitude;
    private String longitude;

    public static final int  ON_LOCATION_UPDATE = 0;

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
