package d.learn.english;

import com.fasterxml.jackson.databind.ObjectMapper;
import d.learn.models.ChineseDictionary;
import d.learn.models.Dictionary;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class DictionaryBuilder {
    Logger logger = LoggerFactory.getLogger(DictionaryBuilder.class);
    private final OkHttpClient httpClient;
    private final ObjectMapper om;

    public DictionaryBuilder(){
        this.httpClient = new OkHttpClient();
        this.om = new ObjectMapper();
    }

    public Dictionary buildDictionaryFromUrl(String url) {
        try {
            return om.readValue(fetchDictResponse(url), Dictionary.class);
        } catch (IOException e) {
            logger.error("Failed to parse english dictionary, error: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public ChineseDictionary buildChineseDictionaryFromUrl(String url) {
        try {
            return om.readValue(fetchDictResponse(url), ChineseDictionary.class);
        } catch (IOException e) {
            logger.error("Failed to parse chinese dictionary, error: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private String fetchDictResponse(String url) {
        Request req = new Request.Builder().url(url).build();
        try (Response response = httpClient.newCall(req).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
