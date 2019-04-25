package ru.otus.telegram.models.input;

public class Chat {
    private String last_name;
    private Integer id;
    private String first_name;
    private String username;
    private String type;

    public Chat() {
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TelegramChat{" +
                "type='" + type + "'" +
                ", last_name='" + last_name + "'" +
                ", id=" + id +
                ", first_name='" + first_name + "'" +
                ", username='" + username + "'" +
                "}";
    }
}
