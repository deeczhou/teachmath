package server.models.errors;

public class BadHttpException extends Exception {
    private int status = 400;
    private String errorCode;
    private String errorMessage = "Bad request input received. please check and fix.";
    private String errorDetail;

    public BadHttpException() {
    }

    public BadHttpException(String errorDetail) {
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

