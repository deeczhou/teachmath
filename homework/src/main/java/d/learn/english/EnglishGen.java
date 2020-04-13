package d.learn.english;

import d.learn.models.Word;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.List;
import java.util.Random;

public class EnglishGen {

    static final String FILL_SPACE = "__________";
    static final int FONT_SIZE = 16;
    static final String FONT_FAMILY = "Calibri";
    static final double LINE_SPACEING = 1.6;

    public static Word getRandomWord(List<Word> words) {
        Random r  = new Random();
        return words.get(r.ints(0, words.size()).findAny().getAsInt());
    }

    public static void generateWords(XWPFDocument doc, List<Word> words, int number) {
        XWPFParagraph para = doc.createParagraph();
        para.setSpacingBetween(LINE_SPACEING);
        XWPFRun run = para.createRun();
        run.setFontSize(FONT_SIZE);
        run.setFontFamily(FONT_FAMILY);
        while (number > 0) {
            Word w = getRandomWord(words);
            while(w.getWord().length() > 15 && w.getWord().length() < 4) {
                w = getRandomWord(words);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(w.getWord()).append("          ").append(w.getSentence());
            run.setText(sb.toString());
            run.addBreak();
            run.setText(addFillSpaces());
            run.addBreak();
            number--;
            words.remove(w);
        }
    }

    private static String addFillSpaces() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< 5; i++) {
            sb.append(FILL_SPACE).append("  ");
        }
        return sb.toString();
    }
}
