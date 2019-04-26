package ru.otus.frontend;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import org.springframework.stereotype.Service;
import ru.otus.services.FrontendService;

import java.io.IOException;

@Service
public class FrontendServiceImpl implements FrontendService {
    //    private static String url = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
    //    private static String url = "https://localhost:8080/sendMessage?token=%s&chat_id=%s&text=%s";
    //me: chat_id=274155259
    private static String url = "http://localhost:8080/sendMessage";
    private static String token = "Abcd12E";
    private static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static Gson gson = new Gson();

    private static OkHttpClient client = new OkHttpClient();

    @Override
    public void sendResponse(String chatId, String text) {
        sendResponse(chatId, text, "");
    }

    @Override
    public void sendResponse(String chatId, String text, String keyboard) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder()
                .addEncodedQueryParameter("token", token);
        String bodyString = gson.toJson(new ToTelegram(chatId, text, keyboard.isBlank() ? null : keyboard));

        System.out.println("Body: " + bodyString);
        String urlToRequest = urlBuilder.build().toString();
        System.out.println(urlToRequest);
        RequestBody requestBody = RequestBody.create(JSON, bodyString);
        Request httpRequest = new Request.Builder()
                .post(requestBody)
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
