import java.io.*;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class MathProblemGenerator {

    public static void main(String[] args) {
        try {
            String filename = "/home/dee/Dropbox/pdf/addition-" + new Date().toString() + ".doc";
            OutputStream os = new FileOutputStream(new File(filename));

            StringBuilder sb = new StringBuilder();
            buildSimpleAdd(sb, 40, 10, 20);
            buildFillInAdd(sb, 40, 5, 10, 20);
            buildSimpleMinus(sb, 60, 1, 15);
            writeToOutput(os, sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static StringBuilder buildSimpleAdd(StringBuilder sb, int numberOfProblems, int from, int to) {
        for (int i = 1; i <= numberOfProblems; i++) {
            int a = getRandomIntBetween(from, to);
            int b = getRandomIntBetween(from, to);
            sb.append(a);
            sb.append(" + ");
            sb.append(b);
            sb.append(" = ");
            sb.append("\t\t");

            if (i%5 == 0) {
                sb.append("\n\n");
            }
        }

        sb.append("\n");
        return sb;
    }

    private static StringBuilder buildSimpleMinus(StringBuilder sb, int numberOfProblems, int from, int to) {
        for (int i = 1; i <= numberOfProblems; i++) {
            int a = getRandomIntBetween(from, to);
            int b = getRandomIntBetween(from, to);

            while (b > a) {
                a = getRandomIntBetween(from, to);
                b = getRandomIntBetween(from, to);
            }

            sb.append(a);
            sb.append(" - ");
            sb.append(b);
            sb.append(" = ");
            sb.append("\t\t");

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
