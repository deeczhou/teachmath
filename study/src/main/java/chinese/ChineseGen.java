package chinese;

import models.Word;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ChineseGen {

    static final String fillSpace = "____________";
    static final String tab = "\t";
    static final String newLine = "\n";

    public static Word getRandomWord(List<Word> words) {
        int index = ThreadLocalRandom.current().nextInt(0, words.size());
        return words.get(index);
    }

    public static StringBuilder generateChineseWords(StringBuilder sb, List<Word> words, int number) {
        while (number > 0) {
            Word w = getRandomWord(words);

            sb.append(w.getWord()).append("\t\t\t").append(w.getSentence())
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
