package d.learn.chinese;

import d.learn.models.Word;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.List;
import java.util.Random;


public class ChineseGen {

    static final String FILL_SPACE = "___________";
    static final String TAB = "\t";
    static final String FONT_FAMILY = "Yu Gothic";
    static final int FONT_SIZE = 15;

    public static Word getRandomWord(List<Word> words) {
        Random r = new Random();
        return words.get(r.ints(0, words.size()).findAny().getAsInt());
    }

    public static void generateChineseWords(XWPFDocument doc, List<Word> words, int number) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(1.7);
        XWPFRun run = paragraph.createRun();
//        run.setBold(true);
        run.setFontFamily(FONT_FAMILY);
        run.setFontSize(FONT_SIZE);
        String space = "  ";
        while (number > 0) {
            Word w = getRandomWord(words);
            String pinyin = PinyinGen.toPinyin(w.getWord()).get();
            pinyin = pinyin.replaceAll("null", " ");

            run.setText(w.getWord() + space + space + pinyin + space + space + w.getSentence());
            run.addBreak();
            run.setText(FILL_SPACE + space);
            run.setText(FILL_SPACE + space);
            run.setText(FILL_SPACE + space);
            run.setText(FILL_SPACE + space);
            run.setText(FILL_SPACE + space);
            run.addBreak();
            number--;
            words.remove(w);
        }
    }

}
