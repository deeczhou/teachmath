import com.fasterxml.jackson.databind.ObjectMapper;
import models.Dictionary;
import java.io.*;
import java.util.Date;
import static english.EnglishGen.generateWords;
import static math.MathGen.*;

public class ProblemGenerator {
    public static void main(String[] args) {
        try {
            String filename = "/home/dee/homework/math/addition-" + new Date().toString() + ".doc";
            OutputStream os = new FileOutputStream(new File(filename));

            String dictFileDir = "resources/dictionary.json";
            File f = new File(dictFileDir);
            ObjectMapper om = new ObjectMapper();
            Dictionary dictionary = om.readValue(f, Dictionary.class);

            StringBuilder sb = new StringBuilder();
            buildSimpleAdd(sb, 40, 5, 40);
            buildFillInAdd(sb, 40, 2, 10, 25);
            buildSimpleMinus(sb, 60, 1, 25);
            sb.append("\n");
            generateWords(sb, dictionary.getWords(), 22);
            writeToOutput(os, sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

}
