package ru.otus.telegram.models.output;

public class InlineButton implements TelegramButton {
    private String text;
    private String callback_data;

    public InlineButton() {
    }

    public InlineButton(String text) {
        this.text = text;
        this.callback_data = null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCallback_data() {
        return callback_data;
    }

    public void setCallback_data(String callback_data) {
        this.callback_data = callback_data;
    }
}
