package server;

import com.google.inject.AbstractModule;
import server.handlers.DownloadHwHandler;
import server.handlers.GenerateAdditionHandler;
import server.handlers.GenerateDivisionHandler;
import server.handlers.GenerateMinusHandler;
import server.handlers.GenerateMultiplyHandler;
import server.handlers.HttpErrorHandler;
import server.services.MathGenService;

public class ServerModule extends AbstractModule {
    protected void configure() {
        bind(GenerateAdditionHandler.class);
        bind(MathGenService.class);
        bind(DownloadHwHandler.class);
        bind(HttpErrorHandler.class);
        bind(GenerateMinusHandler.class);
        bind(GenerateMultiplyHandler.class);
        bind(GenerateDivisionHandler.class);
    }

}
