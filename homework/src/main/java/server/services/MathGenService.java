package server.services;

import d.learn.math.MathGen;
import org.apache.commons.lang3.tuple.ImmutablePair;


public class MathGenService {
    public MathGenService() {
    }

    public ImmutablePair<Integer, Integer> generateAdditionPair(int from, int to) {
        return MathGen.generateNumberPair(from, to);
    }

    public ImmutablePair<Integer, Integer> generataMinusPair(int from, int to) {
        return MathGen.generateSimpleMinusPair(from, to);
    }

}
