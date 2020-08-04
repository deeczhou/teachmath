package d.learn.english;

import com.fasterxml.jackson.databind.ObjectMapper;
import d.learn.models.ChineseDictionary;
import d.learn.models.Dictionary;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

public class DictionaryBuilder {

    private final OkHttpClient httpClient;
    private final ObjectMapper om;

    public DictionaryBuilder(){
        this.httpClient = new OkHttpClient();
        this.om = new ObjectMapper();
    }

    public Dictionary buildDictionaryFromUrl(String url) {
        Dictionary dict = new Dictionary();
        String bearertoken = "cd519a32112ad9980cdc3ffb18e997411e89ab89";
        Request req = new Request.Builder().url(url).header("Authorization", "Bearer " + bearertoken).build();

        try (Response response = httpClient.newCall(req).execute()) {
            String body = Objects.requireNonNull(response.body()).string();
            dict = om.readValue(body, Dictionary.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dict;
    }

    public ChineseDictionary buildChineseDictionaryFromUrl(String url) {
        ChineseDictionary dict = new ChineseDictionary();
        String bearertoken = "cd519a32112ad9980cdc3ffb18e997411e89ab89";
        Request req = new Request.Builder().url(url).header("Authorization", "Bearer " + bearertoken).build();

        try (Response response = httpClient.newCall(req).execute()) {
            String body = Objects.requireNonNull(response.body()).string();
            dict = om.readValue(body, ChineseDictionary.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dict;
    }
}
