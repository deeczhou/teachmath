package chinese;

import models.Word;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import chinese.PinyinGen;

public class ChineseGen {

    static final String fillSpace = "____________";
    static final String tab = "\t";
    static final String newLine = "\n";

    public static Word getRandomWord(List<Word> words) {
        Random r = new Random();
        return words.get(r.ints(0, words.size()).findAny().getAsInt());
    }

    public static StringBuilder generateChineseWords(StringBuilder sb, List<Word> words, int number) {
        while (number > 0) {
            Word w = getRandomWord(words);

            sb.append(w.getWord()).append("\t")
                    .append(PinyinGen.toPinyin(w.getWord()).get())
                    .append("\t\t")
                    .append(w.getSentence())
                    .append(newLine).append(newLine).append(newLine).append(newLine).append(newLine);
            addFillSpaces(sb).append(newLine).append(newLine).append(newLine).append(newLine);

            number--;
            words.remove(w);
        }
        return sb;
    }

    private static StringBuilder addFillSpaces(StringBuilder sb) {
        for(int i = 0; i< 5; i++) {
            sb.append(fillSpace).append(tab);
        }
        return sb;
    }
}
