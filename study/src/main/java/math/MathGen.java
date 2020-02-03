package math;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static math.OptType.SIMPLE_ADD;
import static math.OptType.SIMPLE_MINUS;

public class MathGen {

    public static Pair<Integer, Integer> generateSimpleAddPair(int from, int to) {
        Integer a = getRandomIntBetween(from, to);
        Integer b = getRandomIntBetween(from, to);
        Pair<Integer, Integer> p = new Pair<>(a, b);
        return p;
    }

    public static Pair<Integer, Integer> generateSimpleMinusPair(int from, int to) {
        Integer a = getRandomIntBetween(from, to);
        Integer b = getRandomIntBetween(from, to);
        Pair<Integer, Integer> p = new Pair<>(a, b);
        int count = 0;

        while (b >= a) {
            a = getRandomIntBetween(from, to);
            b = getRandomIntBetween(from, to);
            p = new Pair<>(a, b);
            count ++;
            if (count == 10) {
                break;
            }
        }

        return p;
    }

    public static StringBuilder buildSimpleAdd(StringBuilder sb, int numberOfProblems, int from, int to) {
        Map<Integer, Pair<Integer, Integer>> resmap = new HashMap<>();
        for (int i = 1; i <= numberOfProblems; i++) {
            Pair<Integer, Integer> p = generateSimpleAddPair(from, to);
            int count = 0;
            while (resmap.containsValue(p)) {
                p = generateSimpleAddPair(from, to);
                count++;
                if (count == 5) {
                    break;
                }
            }
            resmap.put(i, p);
            addToThisStringBuilder(sb, p, SIMPLE_ADD);
            if (i%5 == 0) {
                sb.append("\n\n\n");
            }
        }
        sb.append("\n\n");
        return sb;
    }

    public static StringBuilder buildSimpleMinus(StringBuilder sb, int numberOfProblems, int from, int to) {
        Map<Integer, Pair<Integer, Integer>> resMap = new HashMap<>();
        for (int i = 1; i <= numberOfProblems; i++) {
            Pair<Integer, Integer> p = generateSimpleMinusPair(from, to);
            Integer count = 0;
            while (resMap.containsValue(p)) {
                p = generateSimpleMinusPair(from, to);
                count++;
                if (count == 5) {
                    break;
                }
            }
            resMap.put(i, p);
            addToThisStringBuilder(sb, p, SIMPLE_MINUS);

            if (i%5 == 0) {
                sb.append("\n\n\n");
            }
        }

        sb.append("\n\n");
        return sb;
    }

    public static StringBuilder buildFillInAdd(StringBuilder sb, int numberOfProblems, int part, int medium, int sum) {
        for (int i = 1; i <= numberOfProblems; i++) {
            int a = getRandomIntBetween(part, medium);
            int b = getRandomIntBetween(medium, sum);
            String first = "";
            String second = "";

            if (b%2 == 0) {
                first = Integer.toString(a);
                second = "__";
            } else {
                first = "__";
                second = Integer.toString(a);
            }

            while( b < a) {
                b = getRandomIntBetween(5, 19);
            }

            sb.append(first);
            sb.append(" + ");
            sb.append(second);
            sb.append(" = ");
            sb.append(b);
            sb.append("\t\t");
            if (i%5 == 0) {
                sb.append("\n\n\n");
            }
        }

        sb.append("\n\n");
        return sb;
    }


    private static int getRandomIntBetween(int a, int b) {
        return ThreadLocalRandom.current().nextInt(a, b);
    }

    public static StringBuilder addToThisStringBuilder(StringBuilder sb, Pair<Integer, Integer> p, OptType type) {
        switch (type) {
            case SIMPLE_ADD:
                sb.append(p.getKey());
                sb.append(" + ");
                sb.append(p.getValue());
                sb.append(" = ");
                sb.append("\t\t");
                break;
            case SIMPLE_MINUS:
                sb.append(p.getKey());
                sb.append(" - ");
                sb.append(p.getValue());
                sb.append(" = ");
                sb.append("\t\t");
                break;
        }

        return sb;
    }
}
