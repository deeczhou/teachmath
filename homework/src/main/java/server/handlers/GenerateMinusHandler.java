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
public class GenerateMinusHandler implements Handler {
    private final MathGenService mathGenService;
    private final ObjectMapper objectMapper;

    @Inject
    public GenerateMinusHandler(MathGenService mathGenService, ObjectMapper objectMapper){
        this.mathGenService = mathGenService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Map<String, String> params = ctx.getRequest().getQueryParams();
        int size = 10;
        int from = 20;
        int to = 100;
        if (params.get("from") != null) {
            from = Integer.parseInt(params.get("from"));
        }
        if (params.get("to") != null) {
            to = Integer.parseInt(params.get("to"));
        }
        if (params.get("size")!= null) {
            size = Integer.parseInt(params.get("size"));
        }

        validateInput(size, from, to);
        mathGenService.generateMinusPair(from, to, size)
          .then(resp -> {
              MutableHeaders headers = ctx.getResponse().getHeaders();
              headers.add("Access-Control-Allow-Origin", "*");
              headers.add("Content-type", "application/json");
              ctx.getResponse().send(objectMapper.writeValueAsBytes(resp));
          });
    }

    private void validateInput(int size, int from, int to) throws Exception {
        if (size <= 0 || size > 250) {
            throw new BadHttpException("Sample size does not meet the requirement.");
        }

        if (from <= -1000000 || from >= 1000000) {
            throw new BadHttpException("Lower bound needs to be between +/-1 mil");
        }

        if (to <= from || to >= 2000000) {
            throw new BadHttpException("Upper bound needs to be bigger than lower bound, and smaller than 2 mil");
        }
    }
}
