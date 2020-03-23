package d.learn.math;

import javafx.util.Pair;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static d.learn.math.OptType.*;

public class MathGen {
    static Double LINE_SPACING = 1.4;

    public static Pair<Integer, Integer> generateNumberPair(int from, int to) {
        Integer a = getRandomIntBetween(from, to);
        Integer b = getRandomIntBetween(from, to);
        Pair<Integer, Integer> p = new Pair<>(a, b);
        return p;
    }

    public static Pair<Integer, Integer> generateMultiplePair(int multipleFor) {
        Integer a = getRandomIntBetween(1, 9);
        Pair<Integer, Integer> p = new Pair<>(multipleFor, a);
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

    public static void buildMultiply(XWPFDocument doc, int numberOfProblems, int from, int to) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(LINE_SPACING);
        XWPFRun run = paragraph.createRun();

        Map<Integer, Pair<Integer, Integer>> resmap = new HashMap<>();
        for (int i = 1; i <= numberOfProblems; i++) {
            int multipleFor = getRandomIntBetween(from, to);
            Pair<Integer, Integer> p = generateMultiplePair(multipleFor);
            int count = 0;
            while (resmap.containsValue(p)) {
                p = generateMultiplePair(multipleFor);
                count++;
                if (count == 5) {
                    break;
                }
            }
            resmap.put(i, p);
            addToLine(run, p, SIMPLE_MULTIPLY);
            if (i%6 == 0) {
                run.addBreak();
            }
        }
    }

    public static void buildSimpleAdd(XWPFDocument doc, int numberOfProblems, int from, int to) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(LINE_SPACING);
        XWPFRun run = paragraph.createRun();

        Map<Integer, Pair<Integer, Integer>> resmap = new HashMap<>();
        for (int i = 1; i <= numberOfProblems; i++) {
            Pair<Integer, Integer> p = generateNumberPair(from, to);
            int count = 0;
            while (resmap.containsValue(p)) {
                p = generateNumberPair(from, to);
                count++;
                if (count == 5) {
                    break;
                }
            }
            resmap.put(i, p);
            addToLine(run, p, SIMPLE_ADD);
            if (i%5 == 0) {
                run.addBreak();
            }
        }
    }

    public static void buildSimpleMinus(XWPFDocument doc, int numberOfProblems, int from, int to) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(LINE_SPACING);
        XWPFRun run = paragraph.createRun();

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
            addToLine(run, p, SIMPLE_MINUS);
            if (i%5 == 0) {
                run.addBreak();
            }
        }
    }

    public static void buildFillInAdd(XWPFDocument doc, int numberOfProblems, int subValueMax, int sum) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(LINE_SPACING);
        XWPFRun run = paragraph.createRun();
        for (int i = 1; i <= numberOfProblems; i++) {
            int a = getRandomIntBetween(5, subValueMax);
            int b = getRandomIntBetween(subValueMax, sum);
            while( b < a) {
                b = getRandomIntBetween(subValueMax, sum);
            }
            addToLine(run, new Pair<>(a, b), FILL_ADD);
            if (i%4 == 0) {
                run.addBreak();
            }
        }
    }


    private static int getRandomIntBetween(int a, int b) {
        return ThreadLocalRandom.current().nextInt(a, b);
    }

    public static XWPFRun addToLine(XWPFRun run, Pair<Integer, Integer> p, OptType type) {
        String space = "                   ";
        switch (type) {
            case SIMPLE_ADD:
                run.setText(p.getKey() + " + " + p.getValue() + " = " + space);
                break;
            case SIMPLE_MINUS:
                run.setText(p.getKey() + " - " + p.getValue() + " = " + space);
                break;
            case SIMPLE_MULTIPLY:
                run.setText(p.getKey() + " x " + p.getValue() + " = " + "               ");
                break;
            case FILL_ADD:
                String first = "";
                String second = "";
                if (p.getValue()%2 == 0) {
                    first = Integer.toString(p.getKey());
                    second = " ____ ";
                } else {
                    first = " ____ ";
                    second = Integer.toString(p.getKey());
                }
                run.setText(first + " + " + second + " = " + p.getValue() + "               ");
                break;
        }
        return run;
    }
}
