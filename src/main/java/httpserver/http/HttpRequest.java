package httpserver.http;

public class HttpRequest extends HttpMessage{
    private HttpMethod method;
    private String requestTarget;
    private String originalHttpVersion; //literal from the request
    private HttpVersion bestCompatibleHttpVersion;

    public HttpRequest(){}

    public HttpMethod getMethod() {
        return method;
    }
    public String getRequestTarget() {
        return requestTarget;
    }
    void setMethod(String methodName) throws HttpParsingException{
        for (HttpMethod method: HttpMethod.values()) {
            if(methodName.equals(method.name())){
                this.method = method;
                return;
            }
        }
        throw new HttpParsingException(
                HttpStatusCode.CLIENT_ERROR_501_NOT_IMPLEMENTED
        );
    }
    public void setRequestTarget(String requestTarget) throws HttpParsingException{
        if (requestTarget == null || requestTarget.equals("")){
            throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_500_BAD_REQUEST);
        }
        this.requestTarget = requestTarget;
    }
    public void setHttpVersion(String originalHttpVersion) throws BadHttpVersionException, HttpParsingException {
        this.originalHttpVersion = originalHttpVersion;
        this.bestCompatibleHttpVersion = HttpVersion.getBestCompatibleVersion(originalHttpVersion);
        if (this.bestCompatibleHttpVersion == null){
            throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_505_VERSION_NOT_SUPPORTED);
        }
    }
    public HttpVersion getHttpVersion(){
        return bestCompatibleHttpVersion;
    }
}
