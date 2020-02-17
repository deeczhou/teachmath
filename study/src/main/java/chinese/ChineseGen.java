package chinese;

import models.Word;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.awt.*;
import java.util.List;
import java.util.Random;


public class ChineseGen {

    static final String fillSpace = "_________";
    static final String tab = "\t";

    public static Word getRandomWord(List<Word> words) {
        Random r = new Random();
        return words.get(r.ints(0, words.size()).findAny().getAsInt());
    }

    public static void generateChineseWords(XWPFDocument doc, List<Word> words, int number) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(2);
        XWPFRun run = paragraph.createRun();
        run.setBold(true);
        run.setFontFamily("MS Mincho");
        run.setFontSize(14);
        String space = "  ";
        while (number > 0) {
            Word w = getRandomWord(words);
            String pinyin = PinyinGen.toPinyin(w.getWord()).get();
            pinyin = pinyin.replaceAll("null", " ");

            run.setText(w.getWord() + space + space + pinyin + space + space + w.getSentence());
            run.addBreak();
            run.setText(fillSpace + space);
            run.setText(fillSpace + space);
            run.setText(fillSpace + space);
            run.setText(fillSpace + space);
            run.setText(fillSpace + space);
            run.addBreak();
            number--;
            words.remove(w);
        }
    }

}
