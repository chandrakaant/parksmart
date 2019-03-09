package com.highpeak.parksmart.util;

public class Constants {

    //role id
    public static final Integer ADMIN_ROLE = 1;
    public static final Integer VALIDAOTR_ROLE = 2;


    //errors and exceptions
    public static final String EXCEPTION = "Exception";

    //user error and exceptions
    public static final String EMPTY_ID_FIELD = "idp.error.empty.id.field";
    public static final String EMPTY_EMAIL_FIELD = "idp.error.empty.email.field";
    public static final String USER_EXISTS = "idp.error.user.exists";
    public static final String USER_DOES_NOT_EXIST = "idp.error.user.does.not.exists";
    public static final String INVALID_ROLE = "idp.error.invalid.role";
    public static final String INVALID_USER_DETAILS = "idp.error.invalid.user.details";
    public static final String INTERNAL_SERVER_ERROR = "idp.error.internal.server.error";
    public static final String USER_DELETED_SUCCESSFULLY = "User has been deleted successfully";
    public static final String USER_ID_NULL = "idp.error.user.id";

    //password token constants
    public static final String INVALID_TOKEN = "idp.error.invalid.token";
    public static final String TOKEN_EXPIRED = "idp.error.token.expired";
    public static final String DIFFERENT_PASSWORD = "idp.error.password.mismatch";

    //search constants
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    //mail constants
    public static final String PASSWORD_RESET_URL_HAS_BEEN_SENT = "Password set url has been sent";
    public static final String PASSWORD_HAS_BEEN_SET_SUCCESSFULLY = "Password has been set successfully";
    public static final String FORGOT_PASSWORD_URL_IS_SENT = "forgot password mail has been sent";

    //file success and error constatns
    public static final String UPLOADED_FILE = "Uploaded the file";
    public static final String DELETED_FILE_SUCCESSFULLY = "File/Folder has been deleted successfully";
    public static final String DOWNLOADED_FILE = "Downloaded the file";
    public static final String CREATED_FOLDER = "Created the folder";
    public static final String DELETED_FOLDER = "Deleted the folder";
    public static final String SENT_TO_ASSET = "Sent to asset ";
    public static final String RENAMED_FILE = "Renamed";
    public static final String COPIED_FILE = "Copied";
    public static final String MOVED_FILE = "Moved";
    public static final String FOLDER_TYPE = "Folder";
    public static final String CACHE_CONTROL = "no-cache, no-store, must-revalidate";
    public static final String PRAGMA = "no-cache";
    public static final String EXPIRES = "0";
    public static final String MEDIATYPE_APPLICATIONOROCTETSTREAM = "application/octet-stream";
    public static final String APPLICATION_JSON = "application/json";


    public static final String FILE_NOT_FOUND = "idp.error.file.not.found";
    public static final String FOLDER_NOT_FOUND = "idp.error.folder.not.found";
    public static final String UNABLE_TO_DELETE = "idp.error.unable.to.delete.file";
    public static final String INVALID_ACTION = "idp.error.invalid.file.action";
    public static final String NOT_FILE = "idp.error.not.file";
    public static final String UNABLE_TO_CREATE_FOLDER = "idp.error.unable.to.create.folder";
    public static final String UNABLE_TO_RENAME = "idp.error.unable.to.rename";
    public static final String EMPTY_FILE = "idp.error.empty.file";

    //Configuration Errors
    public static final String EMTPY_SOURCE_PATH = "idp.error.empty.source.path";
    public static final String EMTPY_DESTINATION_PATH = "idp.error.empty.destination.path";
    public static final String CONFIGURATION_DOES_NOT_EXIST = "idp.error.configuration.does.not.exist";

    //image error messages
    public static final String DOCUMENT_ID_NULL = "idp.error.document.id.found";
    public static final String DOCUMENT_NOT_FOUND = "idp.error.document.not.found";
    public static final String PAGE_ID_NULL = "idp.error.page.number.found";

    public static final String[] CONFIDENCE_COLORS = new String[]{"#FF0000", "#FF3300", "#ff6600", "#ff9900",
            "#FFCC00", "#FFFF00", "#ccff00", "#99ff00", "#66ff00", "#28cc00"};

    //Table elements
    public static final String COLUMN_LABEL = "column";
    public static final String TABLE_LABEL = "table";
    public static final String HEADER_LABEL = "header";
    public static final String REDIRECTION = "/fs/";

    //rabbit
    public static final String RABBIT_HOST = "localhost";

    public static final String REEXTRACTED_SUCCESSFULLY="Re Extracted Document Successfully";
    public static final String PARKING_AREA_DOESNT_EXIST ="parksmart.error.parking.area.doesnt.exist";
    public static final String EMPTY_FIELD = "parksmart.error.empty.field";
}