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

        for (int i = 1; i <= 75; i++) {
            int a = getRandomIntBetween(5, 18);
            int b = getRandomIntBetween(1, 15);
            sb.append(a);
            sb.append(" + ");
            sb.append(b);
            sb.append(" = ");
            sb.append("\t\t");

            if (i%5 == 0) {
                sb.append("\n\n");
            }

            if (i == 75) {
                sb.append("\n");
            }
        }

        for (int i = 1; i <= 18; i++) {
            int a = getRandomIntBetween(5, 9);
            int b = getRandomIntBetween(5, 15);

            while( b < a) {
                b = getRandomIntBetween(5, 15);
            }

            sb.append(a);
            sb.append(" + __ = ");
            sb.append(b);
            sb.append("\t\t\t");
            if (i%3 == 0) {
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
