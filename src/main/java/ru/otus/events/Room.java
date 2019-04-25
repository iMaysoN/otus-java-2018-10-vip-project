package ru.otus.events;

public class Room {
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static Room getNew(String title, String description) {
        Room room = new Room();
        room.title = title;
        room.description = description;
        return room;
    }

    @Override
    public String toString() {
        return "Room{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
