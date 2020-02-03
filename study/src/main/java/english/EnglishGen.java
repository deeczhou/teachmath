package english;

import models.Word;

import java.util.ArrayList;
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

    public static StringBuilder generateWords(StringBuilder sb, List<Word> words, int number) {
        while (number > 0) {
            Word w = getRandomWord(words);
            while(w.getWord().length() > 6) {
                w = getRandomWord(words);
            }

            sb.append(w.getWord()).append("\t\t").append(w.getSentence())
                    .append(newLine).append(newLine).append(newLine);
            addFillSpaces(sb).append(newLine).append(newLine);

            number--;
            words.remove(w);
        }
        return sb;
    }

    private static StringBuilder addFillSpaces(StringBuilder sb) {
        for(int i = 0; i< 4; i++) {
            sb.append(fillSpace).append(tab);
        }
        return sb;
    }
}
