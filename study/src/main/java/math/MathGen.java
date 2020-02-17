package math;

import javafx.util.Pair;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

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

    public static void buildSimpleAdd(XWPFDocument doc, int numberOfProblems, int from, int to) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(2.5);
        XWPFRun run = paragraph.createRun();

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
            addToLine(run, p, SIMPLE_ADD);
            if (i%5 == 0) {
                run.addBreak();
            }
        }
    }

    public static void buildSimpleMinus(XWPFDocument doc, int numberOfProblems, int from, int to) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(2.5);
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

    public static void buildFillInAdd(XWPFDocument doc, int numberOfProblems, int part, int medium, int sum) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(1.5);
        XWPFRun run = paragraph.createRun();
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
            run.setText(first + " + " + second + " = " + b + "         ");
            if (i%5 == 0) {
                run.addBreak();
            }
        }
    }


    private static int getRandomIntBetween(int a, int b) {
        return ThreadLocalRandom.current().nextInt(a, b);
    }

    public static XWPFRun addToLine(XWPFRun run, Pair<Integer, Integer> p, OptType type) {
        String space = "         ";
        switch (type) {
            case SIMPLE_ADD:
                run.setText(p.getKey() + " + " + p.getValue() + " = " + space);
                break;
            case SIMPLE_MINUS:
                run.setText(p.getKey() + " - " + p.getValue() + " = " + space);
                break;
        }
        return run;
    }
}
