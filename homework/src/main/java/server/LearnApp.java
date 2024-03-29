package server;

import ratpack.guice.Guice;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;
import server.handlers.DownloadHwHandler;
import server.handlers.GenerateAdditionHandler;
import server.handlers.GenerateDivisionHandler;
import server.handlers.GenerateMinusHandler;
import server.handlers.GenerateMultiplyHandler;

public class LearnApp {
    public static void main(String... args) throws Exception {
        RatpackServer.start(server -> server
                .serverConfig(ServerConfig.builder()
                        .port(8989)
                        .build())
                .registry(Guice.registry(b -> b.module(ServerModule.class)))
                .handlers(chain -> chain
                    .get("add", GenerateAdditionHandler.class)
                    .get("homework", DownloadHwHandler.class)
                    .get("minus", GenerateMinusHandler.class)
                    .get("multiply", GenerateMultiplyHandler.class)
                    .get("division", GenerateDivisionHandler.class)
                )
        );
    }
}

