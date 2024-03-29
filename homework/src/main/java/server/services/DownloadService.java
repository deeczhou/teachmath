package server.services;

import d.learn.HomeworkComposer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class DownloadService {
    public DownloadService() {
    }

    public byte[] generateHomeworkFile() {
        final String englishDictPath = "https://raw.githubusercontent.com/deeczhou/learningdict/master/dictionary.json";
        final String chineseDictPath = "https://raw.githubusercontent.com/deeczhou/learningdict/master/dictionary-chinese.json";
        HomeworkComposer hc = new HomeworkComposer(englishDictPath, chineseDictPath);
        LocalDate ld = LocalDate.now();
        ld = ld.plus(0, ChronoUnit.DAYS);
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            hc.createHomeworkDoc(ld).write(os);
            byte[] array = os.toByteArray();
            os.close();
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] generateHomeworkFileLangOnly() {
        final String englishDictPath = "https://raw.githubusercontent.com/deeczhou/learningdict/master/dictionary.json";
        final String chineseDictPath = "https://raw.githubusercontent.com/deeczhou/learningdict/master/dictionary-chinese.json";
        HomeworkComposer hc = new HomeworkComposer(englishDictPath, chineseDictPath);
        LocalDate ld = LocalDate.now();
        ld = ld.plus(0, ChronoUnit.DAYS);
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            hc.createHomeworkDocLangOnly(ld).write(os);
            byte[] array = os.toByteArray();
            os.close();
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
