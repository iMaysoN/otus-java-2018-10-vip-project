package ru.otus.telegram;

public class Message {
    private Long date;
    private Chat chat;
    private Integer message_id;
    private Chat from;
    private String text;

    public Message() {
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public Chat getFrom() {
        return from;
    }

    public void setFrom(Chat from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TelegramMessage{" +
                "date=" + date +
                ", chat=" + chat +
                ", message_id=" + message_id +
                ", from=" + from +
                ", text='" + text + '\'' +
                '}';
    }
}
