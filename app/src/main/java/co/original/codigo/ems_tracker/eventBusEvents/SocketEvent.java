package co.original.codigo.ems_tracker.eventBusEvents;

/**
 * Created by Felipe Garcia on 25/03/2017 - 3:03 PM.
 */

public class SocketEvent {

    private String eventType;
    private String errorMessage;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
