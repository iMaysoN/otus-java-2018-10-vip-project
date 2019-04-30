package ru.otus.frontend;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.services.FrontendService;

import java.io.IOException;

@Service
public class FrontendServiceImpl implements FrontendService {
    private static final Logger logger = LoggerFactory.getLogger(FrontendService.class);
    //    private static String url = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";
    //    private static String url = "https://localhost:8080/sendMessage?token=%s&chat_id=%s&text=%s";
    //me: chat_id=274155259
//    {"chat_id":"274155259","text":"Rooms\n list:","reply_markup":"{\"inline_keyboard\":[[{\"text\":\"Red room\",\"url\":\"https://www.google.com/sendMessage\"},{\"text\":\"Green room\",\"url\":\"https://www.google.com/sendMessage\"},{\"text\":\"Yellow room\",\"url\":\"https://www.google.com/sendMessage\"}]]}"}
    private static final String url = "http://localhost:8080/sendMessage";
    private static final String token = "Abcd12E";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final Gson gson = new Gson();

    private static final OkHttpClient client = new OkHttpClient();

    @Override
    public void sendResponse(String chatId, String text) {
        sendResponse(chatId, text, "");
    }

    @Override
    public void sendResponse(String chatId, String text, String keyboard) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder()
                .addEncodedQueryParameter("token", token);

        String urlToRequest = urlBuilder.build().toString();
        logger.info("DEMO: Send be url: " + urlToRequest);
        String bodyString = gson.toJson(new ToTelegram(chatId, text, keyboard.isBlank() ? null : keyboard));
        logger.info("DEMO: with body: " + bodyString);
        RequestBody requestBody = RequestBody.create(JSON, bodyString);
        Request httpRequest = new Request.Builder()
                .post(requestBody)
                .url(urlToRequest)
                .build();
        try {
            Response response = client.newCall(httpRequest).execute();
            logger.info("DEMO: Response status code - " + response.code());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
