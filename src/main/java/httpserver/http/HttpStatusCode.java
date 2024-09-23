package httpserver.http;

public enum HttpStatusCode {

    /* --- Client errors --- */
    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad Request"),
    CLIENT_ERROR_401_BAD_REQUEST(401, "Method Not Allowed"),
    CLIENT_ERROR_414_BAD_REQUEST(414, "URI Too Long"),

    /* --- Server errors --- */
    CLIENT_ERROR_500_BAD_REQUEST(500, "Internal Server Error"),
    CLIENT_ERROR_501_NOT_IMPLEMENTED(501, "Not Implemented"),
    CLIENT_ERROR_505_VERSION_NOT_SUPPORTED(505, "Version not supported");
    public final int STATUS_CODE;
    private final String MESSAGE;

    HttpStatusCode(int STATUS_CODE, String MESSAGE) {
        this.STATUS_CODE = STATUS_CODE;
        this.MESSAGE = MESSAGE;
    }
}
