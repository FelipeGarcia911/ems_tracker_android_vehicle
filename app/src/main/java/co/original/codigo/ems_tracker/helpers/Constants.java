package co.original.codigo.ems_tracker.helpers;

public class Constants {

    private static final String URL_SERVER = "http://e1a52608.ngrok.io/";
    public static final String URL_API_CONN = URL_SERVER + "api/";
    public static final String URL_SOCKET_CONN = URL_SERVER;

    //GPS
    public static final int GPS_TIME_UPDATE = 1000;
    public static final int GPS_DISTANCE_UPDATE = 100;

    //Socket Methods
    public static final String UPDATE_VEHICLE_POSITION_REQUEST = "UPDATE_VEHICLE_POSITION_REQUEST";
    public static final String UPDATE_VEHICLE_POSITION_RESPONSE = "UPDATE_VEHICLE_POSITION_RESPONSE";

    public static final String LOGIN_VEHICLE = "";
    public static final String LOGOUT_VEHICLE = "";

    // API Methods
    public static final String API_LOGIN_VEHICLE = "vehicles/login/";
    public static final String API_LOGOUT_VEHICLE = "vehicles/logout/";

    // Fragment Names
    public static final String HOME_FRAGMENT_NAME = "Home";
    public static final String GPS_FRAGMENT_NAME = "GPS Tracker";
    public static final String ABOUT_FRAGMENT = "Acerca de";

    // API
    public static final String JSON_RESPONSE_DATA_OBJECT = "data";
    public static final String JSON_RESPONSE_STATUS_CODE = "status";
    public static final String JSON_RESPONSE_MESSAGE     = "message";

    // API RESPONSE STATUS
    public static final int API_SUCCESS = 200;
    public static final int API_FAILURE = 500;
}
