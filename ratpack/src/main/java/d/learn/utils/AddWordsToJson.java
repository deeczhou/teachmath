package d.learn.utils;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import d.learn.models.Dictionary;
import d.learn.models.Word;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AddWordsToJson {

    public static void main(String[] values) {
        String dictFileDir = "resources/dictionary.json";
        String toAddFileDir = "resources/common_words.json";
        File dictFile = new File(dictFileDir);
        File toAddFile = new File(toAddFileDir);
        ObjectMapper om = new ObjectMapper();

        try {
            Dictionary dictionary = om.readValue(dictFile, Dictionary.class);
            long wordId = dictionary.getWords().size()  + 1;
            JsonNode toAddTree = om.readTree(toAddFile);

            Iterator<JsonNode> wordsToAdd;
            wordsToAdd = toAddTree.get("commonWords").elements();
            List<String> dictionaryWords = dictionary.getWords().stream().map(Word::getWord).collect(Collectors.toList());
            List<String> missingWords = new ArrayList<>();

            while(wordsToAdd.hasNext()){
                JsonNode wordToAdd = wordsToAdd.next();
                String w = wordToAdd.asText();
                System.out.println(w);
                if(!dictionaryWords.contains(w)) {
                    if(!missingWords.contains(w))
                    {
                        missingWords.add(w);
                    }
                }
            }

            List<Word> newWords = new ArrayList<>();
            for (int i = 0; i < missingWords.size(); i++) {
                Word nWord = new Word();
                nWord.setId(wordId + i);
                nWord.setWord(missingWords.get(i));
                nWord.setDate(Instant.now().toEpochMilli());
                nWord.setSentence("");
                newWords.add(nWord);
            }

            if (newWords.size() > 0) {
                List<Word> dictWords = dictionary.getWords();
                dictWords.addAll(newWords);
                dictionary.setWords(dictWords
                        .stream()
                        .sorted(Comparator.comparing(Word::getId))
                        .collect(Collectors.toList()));
            }

            ObjectWriter writer = om.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File("resources/dictionary-updated.json"), dictionary);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
