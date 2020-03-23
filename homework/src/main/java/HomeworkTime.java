import d.learn.HomeworkComposer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.apache.poi.xwpf.usermodel.*;

public class HomeworkTime {
    final static String ZONE_ID = "UTC";
    static String outputDir = "/Users/dzhou/homework/";
    static String filePrefix = "hw-";
    static String fileExtension = ".docx";
    final static String englishDictPath = "homework/resources/dictionary.json";
    final static String chineseDictPath = "homework/resources/dictionary-chinese.json";

    public static void main(String[] args) {
        try {
            HomeworkComposer hc = new HomeworkComposer(englishDictPath, chineseDictPath);
            LocalDate ld = LocalDate.now(ZoneId.of(ZONE_ID));
            ld = ld.plus(0, ChronoUnit.DAYS);
            String outPutFilePath = outputDir + filePrefix + ld.toString() + fileExtension;
            OutputStream os = new FileOutputStream(new File(outPutFilePath));
            writeToOutput(os, hc.createHomeworkDoc(ld));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToOutput(OutputStream output,XWPFDocument doc){
        try {
            doc.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
