package server.handlers;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;
import server.services.DownloadService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class DownloadHwHandler implements Handler {
    private final DownloadService downloadService;

    @Inject
    public DownloadHwHandler(DownloadService downloadService){
        this.downloadService = downloadService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        boolean isLangOnly = false;
        Map<String, String> params = ctx.getRequest().getQueryParams();
        if (params.get("langOnly") != null) {
            isLangOnly = Boolean.parseBoolean(params.get("langOnly"));
        }
        byte[] hwBytes;

        if (!isLangOnly) {
            hwBytes = downloadService.generateHomeworkFile();
        } else {
            hwBytes = downloadService.generateHomeworkFileLangOnly();
        }

        MutableHeaders headers = ctx.getResponse().getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Content-type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        ctx.getResponse().send(hwBytes);
    }
}