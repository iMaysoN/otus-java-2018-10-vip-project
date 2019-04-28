package ru.otus.services;

import ru.otus.storage.entities.Event;
import ru.otus.storage.entities.Reader;
import ru.otus.storage.entities.Room;

import java.util.List;
import java.util.Set;

public interface DbService {
    void saveEvent(Event event);

    void saveReader(Reader reader);

    void saveRoom(Room room);

    List<Event> getEvents();

    List<Room> getRooms();

    List<Reader> getReaders();

    Set<Event> getEventsInTime(Long currentTime);

    Set<Event> getNotStartedEvents(Long fromTime);
}
