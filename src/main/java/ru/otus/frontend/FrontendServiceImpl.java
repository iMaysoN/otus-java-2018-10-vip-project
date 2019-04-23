package ru.otus.frontend;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.springframework.stereotype.Service;
import ru.otus.services.FrontendService;
import ru.otus.telegram.ToTelegram;

import java.io.IOException;

@Service
public class FrontendServiceImpl implements FrontendService {
    //    private static String url = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
    //    private static String url = "https://localhost:8080/sendMessage?token=%s&chat_id=%s&text=%s";
    private static String url = "http://localhost:8080/sendMessage";
    private static String token = "token";

    private static OkHttpClient client = new OkHttpClient();

    @Override
    public void sendResponse(ToTelegram toTelegram) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder()
                .addEncodedQueryParameter("token", token)
                .addEncodedQueryParameter("chat_id", toTelegram.getChatId())
                .addEncodedQueryParameter("text", toTelegram.getText());
        String urlToRequest = urlBuilder.build().toString();
        System.out.println(urlToRequest);
        Request httpRequest = new Request.Builder()
                .get()
                .url(urlToRequest)
                .build();
        try {
            Response response = client.newCall(httpRequest).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
