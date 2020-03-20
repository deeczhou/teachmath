package server;

import com.google.inject.AbstractModule;
import server.handlers.GetHomeworkHandler;

public class ServerModule extends AbstractModule {
    protected void configure() {
        bind(GetHomeworkHandler.class);
    }

}
