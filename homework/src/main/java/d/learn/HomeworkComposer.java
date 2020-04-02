package d.learn;

import com.fasterxml.jackson.databind.ObjectMapper;
import d.learn.models.Dictionary;
import javafx.util.Pair;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static d.learn.chinese.ChineseGen.generateChineseWords;
import static d.learn.english.EnglishGen.generateWords;
import static d.learn.math.MathGen.*;
import static d.learn.math.MathGen.buildSimpleMinus;

public class HomeworkComposer {
    String englishDictPath;
    String chineseDictPath;
    final int FONT_SIZE = 13;
    final String FONT_FAMILY = "Verdana";
    int numberOfMultiply = 24;
    int numberOfAdd = 25;
    int numberOfFillAdd = 24;
    int numberOfMinus = 25;
    int numberOfChineseCharacters = 10;
    int numberOfEnglishWords = 19;
    int total = numberOfMultiply + numberOfAdd + numberOfFillAdd + numberOfMinus;
    Pair<Integer, Integer> multiRange = new Pair<>(1, 6);
    Pair<Integer, Integer> addRange = new Pair<>(10, 100);
    Pair<Integer, Integer> minusRange = new Pair<>(5, 80);
    Pair<Integer, Integer> addFillRange = new Pair<>(15, 80);

    public HomeworkComposer(String englishDictPath, String chineseDictPath) {
        this.englishDictPath = englishDictPath;
        this.chineseDictPath = chineseDictPath;
    }

    public XWPFDocument createHomeworkDoc(LocalDate ld) throws IOException {
        Dictionary englishdic = buildDictionary(englishDictPath);
        Dictionary chineseDic = buildDictionary(chineseDictPath);
        XWPFDocument doc = new XWPFDocument();
        XWPFRun headRun = doc.createHeader(HeaderFooterType.DEFAULT).createParagraph().createRun();
        headRun.setFontSize(FONT_SIZE);
        headRun.setFontFamily(FONT_FAMILY);
        //1st page
        //date header
        headRun.setText(ld.toString() + "    " + ld.getDayOfWeek().toString() + "                                  Name:____________");
        XWPFRun footerRun = doc.createFooter(HeaderFooterType.DEFAULT).createParagraph().createRun();
        footerRun.setFontSize(FONT_SIZE);
        footerRun.setFontFamily(FONT_FAMILY);
        footerRun.setText("Score: ____ / " + total);
        createParagraphHead(doc, "Fill in Addition");
        buildFillInAdd(doc, numberOfFillAdd, addFillRange.getKey(), addFillRange.getValue());
//        doc.createParagraph().createRun().addBreak();
        createParagraphHead(doc, "Multiplication");
        buildMultiply(doc, numberOfMultiply, multiRange.getKey(), multiRange.getValue());
//        doc.createParagraph().createRun().addBreak();
        createParagraphHead(doc, "Addition");
        buildSimpleAdd(doc, numberOfAdd, addRange.getKey(), addRange.getValue());
//        doc.createParagraph().createRun().addBreak();
        createParagraphHead(doc, "Subtraction");
        buildSimpleMinus(doc, numberOfMinus, minusRange.getKey(), minusRange.getValue());

        //2nd page
        doc.createParagraph();
        createParagraphHead(doc, "Chinese Words");
        generateChineseWords(doc, chineseDic.getWords(), numberOfChineseCharacters);

        //3rd, 4th page
        createParagraphHead(doc, "English Words");
        generateWords(doc, englishdic.getWords(), numberOfEnglishWords);
        return doc;
    }

    private void createParagraphHead(XWPFDocument doc, String name) {
        XWPFRun run = doc.createParagraph().createRun();
        run.setFontSize(FONT_SIZE);
        run.setFontFamily(FONT_FAMILY);
        run.setText(name);
        run.addBreak();
    }

    private Dictionary buildDictionary(String path) throws IOException {
        File f = new File(path);
        ObjectMapper om = new ObjectMapper();
        return om.readValue(f, Dictionary.class);
    }
}
