package server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;
import server.models.errors.BadHttpException;
import server.services.MathGenService;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GenerateMultiplyHandler implements Handler {
    private final MathGenService mathGenService;
    private final ObjectMapper objectMapper;

    @Inject
    public GenerateMultiplyHandler(MathGenService mathGenService, ObjectMapper objectMapper){
        this.mathGenService = mathGenService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Map<String, String> params = ctx.getRequest().getQueryParams();
        int size = 10;
        int a = 1;
        int b = 20;
        if (params.get("a") != null) {
            a = Integer.parseInt(params.get("a"));
        }
        if (params.get("b") != null) {
            b = Integer.parseInt(params.get("b"));
        }
        if (params.get("size")!= null) {
            size = Integer.parseInt(params.get("size"));
        }

        validateInput(size, a, b);

        mathGenService.generateMultiplePair(a, b, size)
          .then(resp -> {
              MutableHeaders headers = ctx.getResponse().getHeaders();
              headers.add("Access-Control-Allow-Origin", "*");
              headers.add("Content-type", "application/json");
              ctx.getResponse().send(objectMapper.writeValueAsBytes(resp));
          });

    }

    private void validateInput(int size, int a, int b) throws Exception {
        if (size <= 0 || size > 250) {
            throw new BadHttpException("Sample size does not meet the requirement.");
        }

        if (a < 0 || a > 500) {
            throw new BadHttpException("Number out of bound. need to be smaller than 500");
        }

        if (b <0 || b > 500) {
            throw new BadHttpException("Number out of bound. need to be smaller than 500");
        }
    }
}
