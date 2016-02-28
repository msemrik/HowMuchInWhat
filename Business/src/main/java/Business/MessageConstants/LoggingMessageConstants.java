package Business.MessageConstants;

import Business.Business;

public class LoggingMessageConstants {

    public static String GET_OBJECT_BY_ID_BEFORE = "Loading %s by Id: %d.";
    public static String GET_OBJECT_BY_ID_SUCCESS = "Successfully Loaded %s: %s.";
    public static String GET_OBJECT_BY_ID_ERROR = "Error Loading %s, id: %d.";

    public static String GET_OBJECT_BY_NAME_BEFORE = "Loading %s by Name: %s.";
    public static String GET_OBJECT_BY_NAME_SUCCESS = "Successfully Loaded %s: %s.";
    public static String GET_OBJECT_BY_NAME_ERROR = "Error Loading %s, Name: %d.";

    public static String GET_EVERY_OBJECT_BEFORE = "Loading every %s.";
    public static String GET_EVERY_OBJECT_SUCCESS = "Successfully Loaded every %s. Row Count: %d.";
    public static String GET_EVERY_OBJECT_ERROR = "Error Loading every %s.";

    public static String SAVE_OBJECT_BEFORE = "Saving %s: %s.";
    public static String SAVE_OBJECT_SUCCESS = "Successfully Saved %s: %s.";
    public static String SAVE_OBJECT_ERROR = "Error Saving %s: %s.";

    public static String REST_SERVICE_BEFORE = "REST Request Init: %s.";
    public static String REST_SERVICE_SUCCESS = "REST Request Success: %s. Processing Time: %s.";
    public static String REST_SERVICE_LOG_ERROR = "REST Request Error: %s. Processing Time: %s.";
}
