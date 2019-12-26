import javafx.util.Pair;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MathProblemGenerator {

    enum OptType {
        SIMPLE_ADD,
        SIMPLE_MINUS,
        FILL_ADD
    }

    public static void main(String[] args) {
        try {
            String filename = "/home/dee/Dropbox/pdf/addition-" + new Date().toString() + ".doc";
            OutputStream os = new FileOutputStream(new File(filename));

            StringBuilder sb = new StringBuilder();
            buildSimpleAdd(sb, 40, 10, 30);
            buildFillInAdd(sb, 40, 5, 10, 21);
            buildSimpleMinus(sb, 60, 1, 20);
            writeToOutput(os, sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static StringBuilder addToThisStringBuilder(StringBuilder sb, Pair<Integer, Integer> p, OptType type) {
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

    private static Pair<Integer, Integer> generateSimpleAddPair(int from, int to) {
        Integer a = getRandomIntBetween(from, to);
        Integer b = getRandomIntBetween(from, to);
        Pair<Integer, Integer> p = new Pair<>(a, b);
        return p;
    }

    private static Pair<Integer, Integer> generateSimpleMinusPair(int from, int to) {
        Integer a = getRandomIntBetween(from, to);
        Integer b = getRandomIntBetween(from, to);
        Pair<Integer, Integer> p = new Pair<>(a, b);
        int count = 0;

        while (b >= a) {
            a = getRandomIntBetween(from, to);
            b = getRandomIntBetween(from, to);
            p = new Pair<>(a, b);
            count ++;
            if (count == 5) {
                break;
            }
        }

        return p;
    }

    private static StringBuilder buildSimpleAdd(StringBuilder sb, int numberOfProblems, int from, int to) {
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
            addToThisStringBuilder(sb, p, OptType.SIMPLE_ADD);
            if (i%5 == 0) {
                sb.append("\n\n");
            }
        }
        sb.append("\n");
        return sb;
    }

    private static StringBuilder buildSimpleMinus(StringBuilder sb, int numberOfProblems, int from, int to) {
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
            addToThisStringBuilder(sb, p, OptType.SIMPLE_MINUS);

            if (i%5 == 0) {
                sb.append("\n\n");
            }
        }

        sb.append("\n");
        return sb;
    }

    private static StringBuilder buildFillInAdd(StringBuilder sb, int numberOfProblems, int part, int medium, int sum) {
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
                sb.append("\n\n");
            }
        }

        sb.append("\n");
        return sb;
    }

    private static void writeToOutput(OutputStream output, StringBuilder sb){
        try {
            output.write(sb.toString().getBytes());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());

    }

    private static int getRandomIntBetween(int a, int b) {
        return ThreadLocalRandom.current().nextInt(a, b);

    }



}
