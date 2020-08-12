package server.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import ratpack.error.internal.ErrorHandler;
import ratpack.handling.Context;
import ratpack.http.Response;
import server.models.ErrorResponse;
import server.models.errors.BadHttpException;
import server.models.errors.HttpInternalException;

public class HttpErrorHandler implements ErrorHandler {
    ObjectMapper om = new ObjectMapper();

    @Override
    public void error(Context context, int statusCode) {
        context.getResponse().status(statusCode).send();
    }

    @Override
    public void error(Context context, Throwable throwable) throws JsonProcessingException {
        if (throwable instanceof BadHttpException) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorMsg(((BadHttpException) throwable).getErrorDetail());
            errorResponse.setStatusCode(((BadHttpException) throwable).getStatus());
            errorResponse.setStackTrace(Throwables.getStackTraceAsString(throwable));
            Response resp = context.getResponse();
            resp.getHeaders().add("Content-type", "application/json");
            context.getResponse()
                    .status(errorResponse.getStatusCode())
                    .send(om.writeValueAsString(errorResponse));
        }

        if (throwable instanceof HttpInternalException) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorMsg(((HttpInternalException) throwable).getErrorDetail());
            errorResponse.setStatusCode(((HttpInternalException) throwable).getStatus());
            errorResponse.setStackTrace(Throwables.getStackTraceAsString(throwable));
            Response resp = context.getResponse();
            resp.getHeaders().add("Content-type", "application/json");
            context.getResponse()
                    .status(errorResponse.getStatusCode())
                    .send(om.writeValueAsString(errorResponse));
        }

        context.getResponse().status(500).send("Internal Server Error");
    }
}
