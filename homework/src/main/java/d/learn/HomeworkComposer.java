package d.learn;

import com.fasterxml.jackson.databind.ObjectMapper;
import d.learn.models.ChineseDictionary;
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
    int numberOfMultiply = 18;
    int numberOfAdd = 15;
    int numberOfFillAdd = 12;
    int numberOfMinus = 20;
    int numberOfFillMinus = 12;
    int numberOfChineseCharacters = 10;
    int numberOfEnglishWords = 19;
    int total = numberOfMultiply + numberOfAdd + numberOfFillAdd + numberOfMinus + numberOfFillMinus;
    Pair<Integer, Integer> multiRange = new Pair<>(3, 25);
    Pair<Integer, Integer> addRange = new Pair<>(50, 512);
    Pair<Integer, Integer> minusRange = new Pair<>(50, 512);
    Pair<Integer, Integer> addFillRange = new Pair<>(50, 512);
    int minusUpper = 700;

    public HomeworkComposer(String englishDictPath, String chineseDictPath) {
        this.englishDictPath = englishDictPath;
        this.chineseDictPath = chineseDictPath;
    }

    public XWPFDocument createHomeworkDoc(LocalDate ld) throws IOException {
        Dictionary englishdic = buildDictionary(englishDictPath);
        ChineseDictionary chineseDic = buildChineseDictionary(chineseDictPath);
        XWPFDocument doc = new XWPFDocument();
        XWPFRun headRun = doc.createHeader(HeaderFooterType.DEFAULT).createParagraph().createRun();
        headRun.setFontSize(FONT_SIZE);
        headRun.setFontFamily(FONT_FAMILY);
        //1st page
        //date header
        headRun.setText(ld.toString() + "    " + ld.getDayOfWeek().toString() + "                              Name:____________");
        XWPFRun footerRun = doc.createFooter(HeaderFooterType.DEFAULT).createParagraph().createRun();
        footerRun.setFontSize(FONT_SIZE);
        footerRun.setFontFamily(FONT_FAMILY);
        footerRun.setText("Score: ____ / " + total);
        createParagraphHead(doc, "Fill in Addition");
        buildFillInAdd(doc, numberOfFillAdd, addFillRange.getKey(), addFillRange.getValue());
        createParagraphHead(doc, "Fill in Minus");
        buildFillInMinus(doc, numberOfFillMinus, minusUpper);
        createParagraphHead(doc, "Multiplication");
        buildMultiply(doc, numberOfMultiply, multiRange.getKey(), multiRange.getValue());

        createParagraphHead(doc, "Fill in Multiplication");
        buildFillInMultiple(doc, 16, 10);
        
        createParagraphHead(doc, "Addition");
        buildSimpleAdd(doc, numberOfAdd, addRange.getKey(), addRange.getValue());
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
        run.setFontSize(FONT_SIZE + 1);
        run.setFontFamily(FONT_FAMILY);
        run.setText(name);
    }

    private Dictionary buildDictionary(String path) throws IOException {
        File f = new File(path);
        ObjectMapper om = new ObjectMapper();
        return om.readValue(f, Dictionary.class);
    }

    private ChineseDictionary buildChineseDictionary(String path) throws IOException {
        File f = new File(path);
        ObjectMapper om = new ObjectMapper();
        return om.readValue(f, ChineseDictionary.class);
    }
}
