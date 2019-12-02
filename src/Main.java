import java.io.*;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        try {
            String filename = "/home/dee/homework/math/addition-" + new Date().toString();
            OutputStream os = new FileOutputStream(new File(filename));
            generateAddition(os);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void generateAddition(OutputStream output){
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 80; i++) {
            int a = getRandomIntBetween(1, 20);
            int b = getRandomIntBetween(1, 20);
            sb.append(a);
            sb.append(" + ");
            sb.append(b);
            sb.append(" = ");
            sb.append("\t\t");

            if (i%5 == 0) {
                sb.append("\n\n");
            }

            if (i == 80) {
                sb.append("\n");
            }
        }

        for (int i = 1; i <= 28; i++) {
            int a = getRandomIntBetween(1, 9);
            int b = getRandomIntBetween(9, 19);
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
            sb.append("\t\t\t");
            if (i%4 == 0) {
                sb.append("\n\n");
            }
        }
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
