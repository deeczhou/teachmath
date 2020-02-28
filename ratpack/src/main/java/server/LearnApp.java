package server;

import ratpack.guice.Guice;
import ratpack.server.RatpackServer;
import server.handlers.GetHomeworkHandler;

public class LearnApp {
    public static void main(String... args) throws Exception {
        RatpackServer.start(server -> server
                .registry(Guice.registry(b -> b.module(ServerModule.class)))
            .handlers(chain -> chain
                .get("aya", GetHomeworkHandler.class)
                .get(ctx -> ctx.render("Hello World!"))
                .get(":name", ctx -> ctx.render("Hello " + ctx.getPathTokens().get("name") + "!"))
            )
        );
    }
}

