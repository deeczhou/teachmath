package server.services;

import d.learn.math.MathGen;
import org.apache.commons.lang3.tuple.ImmutablePair;
import ratpack.exec.Promise;
import server.models.AdditionQuestion;
import server.models.GenerateSimpleMathResponse;
import server.models.MinusQuestion;
import server.models.MutiplyQuestion;
import server.models.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MathGenService {
    public MathGenService() {
    }

    public Promise<GenerateSimpleMathResponse> generateAdditionPair(int from, int to, int size) {
        List<Question> questionList = new ArrayList<>();
        GenerateSimpleMathResponse resp = new GenerateSimpleMathResponse();
        IntStream.rangeClosed(1, size).forEach(i -> {
            ImmutablePair<Integer, Integer> p = MathGen.generateNumberPair(from, to);
            questionList.add(new AdditionQuestion(p.left, p.right, p.left + p.right));
        });
        resp.setQuestions(questionList);
        return Promise.value(resp);
    }

    public Promise<GenerateSimpleMathResponse> generateMinusPair(int from, int to, int size) {
        List<Question> questionList = new ArrayList<>();
        GenerateSimpleMathResponse resp = new GenerateSimpleMathResponse();
        IntStream.rangeClosed(1, size).forEach(i -> {
            ImmutablePair<Integer, Integer> p = MathGen.generateSimpleMinusPair(from, to);
            questionList.add(new MinusQuestion(p.left, p.right, p.left - p.right));
        });
        resp.setQuestions(questionList);
        return Promise.value(resp);
    }

    public Promise<GenerateSimpleMathResponse> generateMultiplePair(int from, int to, int size) {
        GenerateSimpleMathResponse resp = new GenerateSimpleMathResponse();
        final int f = from;
        final int t = to;
        List<Question> questionList = new ArrayList<>();
        IntStream.rangeClosed(1, size).forEach(i -> {
            ImmutablePair<Integer, Integer> p = MathGen.generateMultiplePair(f, t);
            questionList.add(new MutiplyQuestion(p.left, p.right, p.left * p.right));
        });
        resp.setQuestions(questionList);
        return Promise.value(resp);
    }

}
