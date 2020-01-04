package english;

import models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EnglishGen {

    static final String fillSpace = "________________";
    static final String tab = "\t";
    static final String newLine = "\n";
    static List<Word> collection = new ArrayList<>();

    public static StringBuilder addWordToSb(StringBuilder sb, List<Word> words) {
        Word w = getRandomWord(words);
        int count = 0;
        while(collection.contains(w)) {
            w = getRandomWord(words);
            count ++;
            if (count == 5) {
                break;
            }
        }

        sb.append(w.getWord()).append("\t\t").append(w.getSentence());
        return sb;
    }

    public static Word getRandomWord(List<Word> words) {
        int index = ThreadLocalRandom.current().nextInt(0, words.size());
        return words.get(index);
    }

    public static StringBuilder generateWords(StringBuilder sb, List<Word> words, int number) {
        while (number > 0) {
            addWordToSb(sb, words).append(newLine).append(newLine).append(newLine);
            addFillSpaces(sb).append(newLine).append(newLine);
            number--;
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
