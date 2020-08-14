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
public class GenerateDivisionHandler implements Handler {
    private final MathGenService mathGenService;
    private final ObjectMapper objectMapper;

    @Inject
    public GenerateDivisionHandler(MathGenService mathGenService, ObjectMapper objectMapper){
        this.mathGenService = mathGenService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Map<String, String> params = ctx.getRequest().getQueryParams();
        int size = 10;
        int denominator = 10;
        if (params.get("denominator") != null) {
            denominator = Integer.parseInt(params.get("denominator"));
        }
        if (params.get("size")!= null) {
            size = Integer.parseInt(params.get("size"));
        }

        validateInput(size, denominator);

        mathGenService.generateDivisionPair(denominator, size)
          .then(resp -> {
              MutableHeaders headers = ctx.getResponse().getHeaders();
              headers.add("Access-Control-Allow-Origin", "*");
              headers.add("Content-type", "application/json");
              ctx.getResponse().send(objectMapper.writeValueAsBytes(resp));
          });

    }

    private void validateInput(int size, int denominator) throws Exception {
        if (size <= 0 || size > 250) {
            throw new BadHttpException("Sample size does not meet the requirement.");
        }

        if (denominator < 0 || denominator > 100) {
            throw new BadHttpException("Denominator out of bound. need to be smaller than 500");
        }
    }
}
