package d.learn.english;

import d.learn.models.Word;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EnglishGen {

    static final String fillSpace = "________________";
    static final String tab = "\t";
    static final String newLine = "\n";

    public static Word getRandomWord(List<Word> words) {
        int index = ThreadLocalRandom.current().nextInt(0, words.size());
        Random r  = new Random();
        return words.get(r.ints(0, words.size()).findAny().getAsInt());
    }

    public static void generateWords(XWPFDocument doc, List<Word> words, int number) {
        XWPFParagraph para = doc.createParagraph();
        para.setSpacingBetween(1.85);
        XWPFRun run = para.createRun();
        run.setFontSize(14);
        run.setFontFamily("Verdana");
        while (number > 0) {
            Word w = getRandomWord(words);
            while(w.getWord().length() > 10) {
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
        for(int i = 0; i< 4; i++) {
            sb.append(fillSpace).append(" ");
        }
        return sb.toString();
    }
}
