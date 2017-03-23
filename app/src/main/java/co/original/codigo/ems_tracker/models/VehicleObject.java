package co.original.codigo.ems_tracker.models;

public class VehicleObject {

    private String id;
    private String name;
    private String description;
    private String currentLatitude;
    private String currentLongitude;
    private String speed;
    private String lasTimeUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(String currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public String getCurrentLongitude() {
        return currentLongitude;
    }

    public void setCurrentLongitude(String currentLongitude) {
        this.currentLongitude = currentLongitude;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getLasTimeUpdate() {
        return lasTimeUpdate;
    }

    public void setLasTimeUpdate(String lasTimeUpdate) {
        this.lasTimeUpdate = lasTimeUpdate;
    }
}
