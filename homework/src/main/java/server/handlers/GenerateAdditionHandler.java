package server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.MutableHeaders;
import ratpack.path.PathTokens;
import server.models.AdditionQuestion;
import server.models.GenerateAddResponse;
import server.models.errors.BadHttpException;
import server.services.MathGenService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static ratpack.jackson.Jackson.json;

@Singleton
public class GenerateAdditionHandler implements Handler {
    private final MathGenService mathGenService;
    private final ObjectMapper objectMapper;

    @Inject
    public GenerateAdditionHandler(MathGenService mathGenService, ObjectMapper objectMapper){
        this.mathGenService = mathGenService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Map<String, String> params = ctx.getRequest().getQueryParams();
        int size = 10;
        int from = 50;
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

        List<AdditionQuestion> questionList = new ArrayList<>();
        GenerateAddResponse resp = new GenerateAddResponse();
        final int f = from;
        final int t = to;
        IntStream.rangeClosed(1, size).forEach(i -> {
            ImmutablePair<Integer, Integer> p = mathGenService.generateAdditionPair(f, t);
            questionList.add(new AdditionQuestion(p.left, p.right, p.left + p.right));
        });
        resp.setQuestions(questionList);
        MutableHeaders headers = ctx.getResponse().getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Content-type", "application/json");

        ctx.getResponse().send(objectMapper.writeValueAsBytes(resp));
    }

    private void validateInput(int size, int from, int to) throws Exception {
        if (size <= 0 || size > 250) {
            throw new BadHttpException("Sample size does not meet the requirement.");
        }

        if (from <= -1000000 || from >= 1000000) {
            throw new BadHttpException("Lower bound does not meet the requirement.");
        }

        if (to <= from || to >= 2000000) {
            throw new BadHttpException("Upper bound does not meet the requirement.");
        }
    }
}
