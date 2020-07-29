package server;

import ratpack.guice.Guice;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;
import ratpack.server.ServerConfigBuilder;
import server.handlers.GenerateAdditionHandler;

public class LearnApp {
    public static void main(String... args) throws Exception {
        RatpackServer.start(server -> server
                .serverConfig(ServerConfig.builder()
                        .port(8989)
                        .build())
                .registry(Guice.registry(b -> b.module(ServerModule.class)))
                .handlers(chain -> chain
                    .get("add", GenerateAdditionHandler.class)
            )
        );
    }
}

