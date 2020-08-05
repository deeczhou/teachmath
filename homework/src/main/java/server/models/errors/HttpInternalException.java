package server.models.errors;

public class HttpInternalException extends Exception {
    private int status = 500;
    private String errorCode;
    private String errorMessage = "An unrecoverable internal error occurred..";
    private String errorDetail;

    public HttpInternalException() {
    }

    public HttpInternalException(String errorMessage, String errorDetail) {
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
