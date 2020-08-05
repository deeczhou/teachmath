package server;

import com.google.inject.AbstractModule;
import server.handlers.DownloadHwHandler;
import server.handlers.GenerateAdditionHandler;
import server.handlers.UserErrorHandler;
import server.services.MathGenService;

public class ServerModule extends AbstractModule {
    protected void configure() {
        bind(GenerateAdditionHandler.class);
        bind(MathGenService.class);
        bind(DownloadHwHandler.class);
        bind(UserErrorHandler.class);
    }

}
