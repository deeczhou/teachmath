package server.handlers;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;
import server.services.DownloadService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DownloadHwHandler implements Handler {
    private final DownloadService downloadService;

    @Inject
    public DownloadHwHandler(DownloadService downloadService){
        this.downloadService = downloadService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        byte[] hwBytes = downloadService.generateHomeworkFile();
        MutableHeaders headers = ctx.getResponse().getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Content-type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        ctx.getResponse().send(hwBytes);
    }
}