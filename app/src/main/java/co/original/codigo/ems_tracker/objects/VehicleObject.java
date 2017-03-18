package co.original.codigo.ems_tracker.objects;

public class VehicleObject {

    private String id;
    private String name;
    private String description;
    private String latitude;
    private String longitude;
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
