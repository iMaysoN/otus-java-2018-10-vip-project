package ru.otus.telegram;

public class ToTelegram {
    private String chat_id;
    private String text;

    public ToTelegram(String chat_id, String text) {
        this.chat_id = chat_id;
        this.text = text;
    }

    public String getChatId() {
        return chat_id;
    }

    public String getText() {
        return text;
    }
}
