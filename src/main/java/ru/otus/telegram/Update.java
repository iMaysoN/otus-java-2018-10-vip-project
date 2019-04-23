package ru.otus.telegram;

public class Update {
    private Integer update_id;
    private Message message;
    private Long forward_date;

    public Update() {
    }

    public Integer getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(Integer update_id) {
        this.update_id = update_id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Long getForward_date() {
        return forward_date;
    }

    public void setForward_date(Long forward_date) {
        this.forward_date = forward_date;
    }

    @Override
    public String toString() {
        return "TelegramUpdate{" +
                "update_id=" + update_id +
                ", message=" + message +
                ", forward_date=" + forward_date +
                '}';
    }
}
