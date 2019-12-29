package english;

import models.Word;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EnglishGen {

    static final String fillSpace = "_________";
    static final String tab = "\t";
    static final String newLine = "\n";

    public static StringBuilder addWordToSb(StringBuilder sb, List<Word> words) {
        int index = ThreadLocalRandom.current().nextInt(0, words.size());
        Word w = words.get(index);
        sb.append(w.getWord()).append("\t\t\t").append(w.getSentence());
        return sb;
    }

    public static StringBuilder generateWords(StringBuilder sb, List<Word> words, int number) {
        while (number > 0) {
            addWordToSb(sb, words).append(newLine).append(newLine).append(newLine);
            addFillSpaces(sb).append(newLine).append(newLine).append(newLine);
            number--;
        }
        return sb;
    }

    private static StringBuilder addFillSpaces(StringBuilder sb) {
        for(int i = 0; i< 7; i++) {
            sb.append(fillSpace).append(tab);
        }
        return sb;
    }
}
