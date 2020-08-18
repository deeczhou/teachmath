package d.learn.chinese;

import d.learn.models.ChineseWord;
import org.apache.poi.xwpf.usermodel.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class ChineseGen {

    static final String FONT_FAMILY = "Calibri";
    static final int FONT_SIZE = 14;
    static final int FONT_SIZE_TWO = 16;

    public static ChineseWord getRandomWord(List<ChineseWord> words) {
        Random r = new Random();
        return words.get(r.ints(0, words.size()).findAny().getAsInt());
    }

    public static void generateChineseWords(XWPFDocument doc, List<ChineseWord> words, int number) {
        XWPFParagraph paragraph = doc.createParagraph();
        paragraph.setSpacingBetween(1.3);

        String space = "      ";
        while (number > 0) {
            ChineseWord w = getRandomWord(words);
            String pinyin = PinyinGen.toPinyin(w.getWord()).get();
            pinyin = pinyin.replaceAll("null", " ");
            String sentence = w.getSentence();
            List<String> pinyins = new ArrayList<>();

            if (sentence.contains(" ")) {
                String[] chars = sentence.split(" ");
                pinyins = Arrays.stream(chars).map(c -> PinyinGen.toPinyin(c).get()).collect(Collectors.toList());
            }

            StringBuilder examplePinyin = new StringBuilder();
            if (pinyins.size() > 0) {
                pinyins.forEach(p -> examplePinyin.append(p).append(" "));
            }

            XWPFRun run1 = paragraph.createRun();
            run1.setFontFamily(FONT_FAMILY);
            run1.setFontSize(FONT_SIZE);
            run1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
            run1.setText(w.getWord() + space + space + pinyin);
            run1.addBreak();

            XWPFRun run2 = paragraph.createRun();
            run2.setFontFamily(FONT_FAMILY);
            run2.setFontSize(FONT_SIZE_TWO);
            run2.setText(w.getSentence() + space + examplePinyin.toString() + space + w.getTranslation());
            run2.addBreak();
            number--;
            words.remove(w);
        }

        paragraph.createRun().addBreak(BreakType.PAGE);
    }

}
