package server.handlers;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GetHomeworkHandler implements Handler {

    @Inject
    public GetHomeworkHandler(){

    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.render("hhhhhh");
    }
}
