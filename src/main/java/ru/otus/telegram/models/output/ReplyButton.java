package ru.otus.telegram.models.output;

public class ReplyButton implements TelegramButton {
    private String text;

    public ReplyButton() {
    }

    public ReplyButton(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "KeyboardButton{" +
                "text='" + text + '\'' +
                '}';
    }
}
