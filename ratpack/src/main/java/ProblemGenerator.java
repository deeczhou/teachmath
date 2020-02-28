import com.fasterxml.jackson.databind.ObjectMapper;
import d.learn.models.Dictionary;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;

import static d.learn.chinese.ChineseGen.generateChineseWords;
import static d.learn.english.EnglishGen.generateWords;
import static d.learn.math.MathGen.*;
import org.apache.poi.xwpf.usermodel.*;

public class ProblemGenerator {
    public static void main(String[] args) {
        try {
            LocalDate ld = LocalDate.now(ZoneId.of("UTC"));

            String filename = "/home/dee/homework/hw-" + ld.toString() + ".docx";
            OutputStream os = new FileOutputStream(new File(filename));
            System.out.println(System.getProperties());
            String dictFileDir = "resources/dictionary.json";
            String chineseDicDir = "resources/dictionary-chinese.json";
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
            buildSimpleAdd(doc, 25, 10, 55);
            buildSimpleMinus(doc, 30, 5, 50);
            buildFillInAdd(doc, 25, 5, 10, 30);
            doc.createParagraph();
            generateChineseWords(doc, chineseDic.getWords(), 8);
            generateWords(doc, englishdic.getWords(), 20);
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