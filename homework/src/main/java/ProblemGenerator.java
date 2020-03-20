import com.fasterxml.jackson.databind.ObjectMapper;
import d.learn.models.Dictionary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static d.learn.chinese.ChineseGen.generateChineseWords;
import static d.learn.english.EnglishGen.generateWords;
import static d.learn.math.MathGen.*;

import javafx.util.Pair;
import org.apache.poi.xwpf.usermodel.*;

public class ProblemGenerator {
    public static void main(String[] args) {
        try {
            LocalDate ld = LocalDate.now(ZoneId.of("UTC"));
            ld = ld.plus(0, ChronoUnit.DAYS);

            String filename = "/home/dee/homework/hw-" + ld.toString() + ".docx";
            OutputStream os = new FileOutputStream(new File(filename));
            System.out.println(System.getProperties());
            String dictFileDir = "src/resources/dictionary.json";
            String chineseDicDir = "src/resources/dictionary-chinese.json";

            int numberOfMultiply = 18;
            int numberOfAdd = 30;
            int numberOfFillAdd = 25;
            int numberOfMinus = 30;

            int numberOfChineseCharacters = 8;
            int numberOfEnglishWords = 19;

            Pair<Integer, Integer> multiRange = new Pair<>(1, 4);
            Pair<Integer, Integer> addRange = new Pair<>(10, 100);
            Pair<Integer, Integer> minusRange = new Pair<>(5, 80);
            Pair<Integer, Integer> addFillRange = new Pair<>(15, 80);

            File f = new File(dictFileDir);
            File c = new File(chineseDicDir);

            ObjectMapper om = new ObjectMapper();
            Dictionary englishdic = om.readValue(f, Dictionary.class);
            Dictionary chineseDic = om.readValue(c, Dictionary.class);

            StringBuilder sb = new StringBuilder();
            sb.append(ld.toString()).append("    ").append(ld.getDayOfWeek().toString()).append("\n\n");
            XWPFDocument doc = new XWPFDocument();
            XWPFRun headRun = doc.createParagraph().createRun();
            headRun.setText(sb.toString());
            headRun.addBreak();
            buildFillInAdd(doc, numberOfFillAdd, addFillRange.getKey(), addFillRange.getValue());
            buildMultiply(doc, numberOfMultiply, multiRange.getKey(), multiRange.getValue());
            buildSimpleAdd(doc, numberOfAdd, addRange.getKey(), addRange.getValue());
            buildSimpleMinus(doc, numberOfMinus, minusRange.getKey(), minusRange.getValue());
            doc.createParagraph();
            generateChineseWords(doc, chineseDic.getWords(), numberOfChineseCharacters);
            generateWords(doc, englishdic.getWords(), numberOfEnglishWords);
            writeToOutput(os, doc);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToOutput(OutputStream output,XWPFDocument doc){
        try {
            doc.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
