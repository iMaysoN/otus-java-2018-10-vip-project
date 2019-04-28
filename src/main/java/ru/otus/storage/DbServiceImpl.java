package ru.otus.storage;

import org.springframework.stereotype.Service;
import ru.otus.services.DbService;
import ru.otus.storage.entities.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DbServiceImpl implements DbService {
    private final EventsRepository eventsRepository;
    private final RoomRepository roomRepository;
    private final ReaderRepository readerRepository;

    public DbServiceImpl(EventsRepository eventsRepository,
                         RoomRepository roomRepository,
                         ReaderRepository readerRepository) {
        this.eventsRepository = eventsRepository;
        this.roomRepository = roomRepository;
        this.readerRepository = readerRepository;

        initMockEvents();
    }

    @Override
    public void saveEvent(Event event) {
        this.eventsRepository.save(event);
    }

    @Override
    public void saveReader(Reader reader) {
        this.readerRepository.save(reader);
    }

    @Override
    public void saveRoom(Room room) {
        this.roomRepository.save(room);
    }

    @Override
    public List<Event> getEvents() {
        return eventsRepository.findAll();
    }

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Reader> getReaders() {
        return readerRepository.findAll();
    }

    @Override
    public Set<Event> getEventsInTime(Long currentTime) {
        return getEvents().stream()
                .filter(event -> event.isInProgress(currentTime))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getNotStartedEvents(Long fromTime) {
        return getEvents().stream()
                .filter(event -> event.isNotStarted(fromTime))
                .collect(Collectors.toSet());
    }

    private void initMockEvents() {
        Room blueRoom = new Room("Blue room", "Синий зал, 2-ой этаж");
        Room redRoom = new Room("Red room", "Красный зал, 1-ый этаж");
        Room yellowRoom = new Room("Yellow room", "Желтый зал, фойе");
        saveRoom(blueRoom);
        saveRoom(redRoom);
        saveRoom(yellowRoom);

        Reader ivanIvanov = new Reader("Иван", "Иванов", "Супер-пупер джавист");
        Reader petrPetrov = new Reader("Петр", "Петров", "Злобный фронтендер");
        Reader babaKlava = new Reader("Клава", "Сергеевна", "Уборщица");
        saveReader(ivanIvanov);
        saveReader(petrPetrov);
        saveReader(babaKlava);

        Event event1 = new Event();
        event1.setTime(110L, 200L);
        event1.setRoom(blueRoom);
        event1.setReader(ivanIvanov);
        event1.setTitle("Джава эвент");
        event1.setDescription("Обычный джава эвент");

        Event event2 = new Event();
        event2.setTime(210L, 300L);
        event2.setRoom(blueRoom);
        event2.setReader(ivanIvanov);
        event2.setTitle("Второй Джава эвент");
        event2.setDescription("Второй джава эвент");

        Event event3 = new Event();
        event3.setTime(110L, 200L);
        event3.setRoom(redRoom);
        event3.setReader(petrPetrov);
        event3.setTitle("Фронтендовый эвент");
        event3.setDescription("Страшный фронтендовый эвент");

        Event event4 = new Event();
        event4.setTime(100L, 300L);
        event4.setRoom(yellowRoom);
        event4.setReader(babaKlava);
        event4.setTitle("Как уронить сервер если нет высшего образования");
        event4.setDescription("Исповедь самого опасного человека на свете");

        Event event5 = new Event();
        event5.setTime(310L, 400L);
        event5.setRoom(redRoom);
        event5.setReader(babaKlava);
        event5.setTitle("Клава наносит ответный удар");
        event5.setDescription("Рассвет Сергеевны");

        saveEvent(event1);
        saveEvent(event2);
        saveEvent(event3);
        saveEvent(event4);
        saveEvent(event5);
    }
}
