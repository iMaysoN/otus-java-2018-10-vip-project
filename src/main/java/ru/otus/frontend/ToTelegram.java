package ru.otus.frontend;

public class ToTelegram {
    private String chat_id;
    private String text;
    private String reply_markup;

    //empty constructor needed for test for sendMessage input
    public ToTelegram() {
    }

    public ToTelegram(String chat_id, String text) {
        this.chat_id = chat_id;
        this.text = text;
        this.reply_markup = null;
    }

    public ToTelegram(String chat_id, String text, String keyboardMarkup) {
        this.chat_id = chat_id;
        this.text = text;
        this.reply_markup = keyboardMarkup;
    }

    public String getChat_id() {
        return chat_id;
    }

    public String getText() {
        return text;
    }

    public String getReply_markup() {
        return reply_markup;
    }
}
