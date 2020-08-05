package server.handlers;

import ratpack.error.internal.ErrorHandler;
import ratpack.handling.Context;

public class UserErrorHandler implements ErrorHandler {

    @Override
    public void error(Context context, int statusCode) throws Exception {
        context.getResponse().status(statusCode).send();
    }

    @Override
    public void error(Context context, Throwable throwable) throws Exception {
        context.getResponse().status(400);
        context.getResponse().send(throwable.getMessage());
    }
}
