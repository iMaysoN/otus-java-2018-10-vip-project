package ru.otus.events;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EventsRepository {
    private Set<Event> events;
    private Set<Reader> readers;
    private Set<Room> rooms;

    public EventsRepository() {
        events = new HashSet<>();
        readers = new HashSet<>();
        rooms = new HashSet<>();

        Room blueRoom = Room.getNew("Blue room", "Синий зал, 2-ой этаж");
        Room redRoom = Room.getNew("Red room", "Красный зал, 1-ый этаж");
        Room yellowRoom = Room.getNew("Yellow room", "Желтый зал, фойе");
        rooms.addAll(Arrays.asList(blueRoom, redRoom, yellowRoom));

        Reader reader1 = Reader.getNew("Иван", "Иванов", "Супер-пупер джавист");
        Event event1 = Event.builder()
                .setTime(110L, 200L)
                .setRoom(blueRoom)
                .setReader(reader1)
                .setTitle("Джава эвент")
                .setDescription("Обычный джава эвент")
                .build();
        events.add(event1);
        readers.add(event1.getReader());

        Event event2 = Event.builder()
                .setTime(210L, 300L)
                .setRoom(blueRoom)
                .setReader(reader1)
                .setTitle("Второй Джава эвент")
                .setDescription("Второй джава эвент")
                .build();
        events.add(event2);
        readers.add(event2.getReader());

        Reader reader3 = Reader.getNew("Петр", "Петров", "Злобный фронтендер");
        Event event3 = Event.builder()
                .setTime(110L, 200L)
                .setRoom(redRoom)
                .setReader(reader3)
                .setTitle("Фронтендовый эвент")
                .setDescription("Страшный фронтендовый эвент")
                .build();
        events.add(event3);
        readers.add(event3.getReader());

        Reader reader4 = Reader.getNew("Клава", "Сергеевна", "Уборщица");
        Event event4 = Event.builder()
                .setTime(100L, 300L)
                .setRoom(yellowRoom)
                .setReader(reader1)
                .setTitle("Как уронить сервер если нет высшего образования")
                .setDescription("Исповедь самого опасного человека на свете")
                .build();
        events.add(event4);
        readers.add(event4.getReader());
    }

    public Set<Event> getCurrentEvents(Long currentTime) {
        Set<Event> currentEvents = events.stream()
                .filter(event -> event.getTimeStart() <= currentTime && event.getTimeEnd() > currentTime)
                .collect(Collectors.toSet());
        return currentEvents;
    }
}
