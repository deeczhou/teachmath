package server.handlers;

import org.apache.commons.lang3.tuple.ImmutablePair;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.path.PathTokens;
import server.models.GenerateAddResponse;
import server.services.MathGenService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

import static ratpack.jackson.Jackson.json;

@Singleton
public class GenerateAdditionHandler implements Handler {
    private MathGenService mathGenService;

    @Inject
    public GenerateAdditionHandler(MathGenService mathGenService){
        this.mathGenService = mathGenService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        Map<String, String> params = ctx.getRequest().getQueryParams();
        Integer from = Integer.valueOf(params.get("from"));
        Integer to = Integer.valueOf(params.get("to"));
        ImmutablePair<Integer, Integer> p = mathGenService.generateAdditionPair(from, to);
        GenerateAddResponse resp = new GenerateAddResponse();
        resp.setNum1(p.left);
        resp.setNum2(p.right);
        resp.setSum(p.left+p.right);
        ctx.render(json(resp));
    }
}
