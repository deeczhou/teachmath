package d.learn.math;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static d.learn.math.OptType.*;

public class MathGen {
    static Double LINE_SPACING = 1.15;
    static String FONT_FAMILY = "Calibri";

    public static ImmutablePair<Integer, Integer> generateNumberPair(int from, int to) {
        Integer a = getRandomIntBetween(from, to);
        Integer b = getRandomIntBetween(from, to);
        ImmutablePair<Integer, Integer> p = new ImmutablePair<>(a, b);
        return p;
    }

    public static ImmutablePair<Integer, Integer> generateMultiplePair(int multipleFor) {
        Integer a = getRandomIntBetween(1, 9);
        ImmutablePair<Integer, Integer> p = new ImmutablePair<>(multipleFor, a);
        return p;
    }

    public static ImmutablePair<Integer, Integer> generateMultiplePair(int a, int b) {
        Integer one = getRandomIntBetween(1, a);
        Integer two = getRandomIntBetween(1, b);
        ImmutablePair<Integer, Integer> p = new ImmutablePair<>(one, two);
        return p;
    }

    public static ImmutablePair<Integer, Integer> generateDivisionPair(int a) {
        Integer one = getRandomIntBetween(1, a);

        Integer numerator = one*getRandomPrime()*getRandomIntBetween(1, 10);

        ImmutablePair<Integer, Integer> p = new ImmutablePair<>(numerator, one);
        return p;
    }

    public static ImmutablePair<Integer, Integer> generateSimpleMinusPair(int from, int to) {
        Integer a = getRandomIntBetween(from, to);
        Integer b = getRandomIntBetween(from, to);
        ImmutablePair<Integer, Integer> p = new ImmutablePair<>(a, b);
        int count = 0;

        while (b >= a) {
            a = getRandomIntBetween(from, to);
            b = getRandomIntBetween(from, to);
            p = new ImmutablePair<>(a, b);
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
        run.setFontFamily(FONT_FAMILY);
        Map<Integer, ImmutablePair<Integer, Integer>> resmap = new HashMap<>();
        for (int i = 1; i <= numberOfProblems; i++) {
            int multipleFor = getRandomIntBetween(from, to + 1);
            ImmutablePair<Integer, Integer> p = generateMultiplePair(multipleFor);
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
        run.setFontFamily(FONT_FAMILY);

        Map<Integer, ImmutablePair<Integer, Integer>> resmap = new HashMap<>();
        for (int i = 1; i <= numberOfProblems; i++) {
            ImmutablePair<Integer, Integer> p = generateNumberPair(from, to);
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
        run.setFontFamily(FONT_FAMILY);

        Map<Integer, ImmutablePair<Integer, Integer>> resMap = new HashMap<>();
        for (int i = 1; i <= numberOfProblems; i++) {
            ImmutablePair<Integer, Integer> p = generateSimpleMinusPair(from, to);
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
        run.setFontFamily(FONT_FAMILY);

        for (int i = 1; i <= numberOfProblems; i++) {
            int a = getRandomIntBetween(5, subValueMax);
            int b = getRandomIntBetween(subValueMax, sum);
            while( b < a) {
                b = getRandomIntBetween(subValueMax, sum);
            }
            addToLine(run, new ImmutablePair<>(a, b), FILL_ADD);
            if (i%4 == 0) {
                run.addBreak();
            }
        }
    }

    public static void buildFillInMinus(XWPFDocument doc, int numberOfProblems, int upper) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(LINE_SPACING);
        XWPFRun run = paragraph.createRun();
        run.setFontFamily(FONT_FAMILY);

        for (int i = 1; i <= numberOfProblems; i++) {
            int a = getRandomIntBetween(5, upper);
            int b = getRandomIntBetween(1, a);
            while( b == a) {
                b = getRandomIntBetween(1, a);
            }
            addToLine(run, new ImmutablePair<>(a, b), FILL_MINUS);
            if (i%4 == 0) {
                run.addBreak();
            }
        }
    }

    public static void buildFillInMultiple(XWPFDocument doc, int numberOfProblems, int upper) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(LINE_SPACING);
        XWPFRun run = paragraph.createRun();
        run.setFontFamily(FONT_FAMILY);

        for (int i = 1; i <= numberOfProblems; i++) {
            int a = getRandomIntBetween(1, upper);
            int b = getRandomIntBetween(a, upper);
            addToLine(run, new ImmutablePair<>(a, a*b), Fill_MULTIPLE);
            if (i%4 == 0) {
                run.addBreak();
            }
        }
    }

    public static void buildDivision(XWPFDocument doc, int numberOfProblems) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(LINE_SPACING);
        XWPFRun run = paragraph.createRun();
        run.setFontFamily(FONT_FAMILY);

        for (int i = 1; i <= numberOfProblems; i++) {        
            int a = getRandomIntBetween(1, 9);
            int b = getRandomIntBetween(1, 9);
            addToLine(run, new ImmutablePair<>(a, b), DIVISION);
            if (i%5 == 0) {
                run.addBreak();
            }
        }
    }

    private static int getRandomIntBetween(int a, int b) {
        return ThreadLocalRandom.current().nextInt(a, b);
    }

    private static int getRandomPrime() {
        int[] primes = {1,3,5,7};
        return primes[ThreadLocalRandom.current().nextInt(0,4)];
    }

    public static XWPFRun addToLine(XWPFRun run, ImmutablePair<Integer, Integer> p, OptType type) {
        String space = "              ";
        switch (type) {
            case SIMPLE_ADD:
                run.setText(p.getKey() + " + " + p.getValue() + " = " + space);
                break;
            case SIMPLE_MINUS:
                run.setText(p.getKey() + " - " + p.getValue() + " = " + space);
                break;
            case SIMPLE_MULTIPLY:
                run.setText(p.getKey() + " x " + p.getValue() + " = " + "              ");
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
                run.setText(first + " + " + second + " = " + p.getValue() + "             ");
                break;
            case FILL_MINUS:
                run.setText(p.getKey() + " - " + " ____ " + " = " + p.getValue() + "             ");
                break;
            case Fill_MULTIPLE:
                String f = "";
                String s = "";
                if (p.getValue()%2 == 0) {
                    f = Integer.toString(p.getKey());
                    s = " ____ ";
                } else {
                    f = " ____ ";
                    s = Integer.toString(p.getKey());
                }
                run.setText(f + " * " + s + " = " + p.getValue() + "               ");
                break;    
            case DIVISION:
                int a = p.getKey();
                int b = p.getValue();
                int prime = getRandomPrime();
                run.setText(a*b*prime + " / " + a + " = ___" + space);
                break;
            }
        return run;
    }
}
