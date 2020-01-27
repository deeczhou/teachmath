import com.fasterxml.jackson.databind.ObjectMapper;
import models.Dictionary;
import java.io.*;
import java.time.LocalDate;

import static english.EnglishGen.generateWords;
import static chinese.ChineseGen.generateChineseWords;
import static math.MathGen.*;

public class ProblemGenerator {
    public static void main(String[] args) {
        try {
            LocalDate ld = LocalDate.now();
            String newline = "\n";

            String filename = "/home/dee/homework/hw-" + ld.toString() + ".doc";
            OutputStream os = new FileOutputStream(new File(filename));

            String dictFileDir = "resources/dictionary.json";
            String chineseDicDir = "resources/dictionary-chinese.json";
            File f = new File(dictFileDir);
            File c = new File(chineseDicDir);

            ObjectMapper om = new ObjectMapper();
            Dictionary englishdic = om.readValue(f, Dictionary.class);
            Dictionary chineseDic = om.readValue(c, Dictionary.class);

            StringBuilder sb = new StringBuilder();
            sb.append(ld.toString()).append("    ").append(ld.getDayOfWeek().toString()).append("\n\n");

            buildSimpleAdd(sb, 40, 5, 40);
            buildFillInAdd(sb, 40, 2, 10, 25);
            buildSimpleMinus(sb, 60, 1, 25);
            generateChineseWords(sb, chineseDic.getWords(), 5)
                    .append(newline).append(newline);
            generateWords(sb, englishdic.getWords(), 22);
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
