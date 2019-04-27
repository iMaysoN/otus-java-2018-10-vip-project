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
    public void addEvent(Event event) {
        this.eventsRepository.save(event);
    }

    @Override
    public void addReader(Reader reader) {
        this.readerRepository.save(reader);
    }

    @Override
    public void addRoom(Room room) {
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
        addRoom(blueRoom);
        addRoom(redRoom);
        addRoom(yellowRoom);

        Reader ivanIvanov = new Reader("Иван", "Иванов", "Супер-пупер джавист");
        Reader petrPetrov = new Reader("Петр", "Петров", "Злобный фронтендер");
        Reader babaKlava = new Reader("Клава", "Сергеевна", "Уборщица");
        addReader(ivanIvanov);
        addReader(petrPetrov);
        addReader(babaKlava);

        Event event1 = new Event() {{
            setTime(110L, 200L);
            setRoom(blueRoom);
            setReader(ivanIvanov);
            setTitle("Джава эвент");
            setDescription("Обычный джава эвент");
        }};

        Event event2 = new Event() {{
            setTime(210L, 300L);
            setRoom(blueRoom);
            setReader(ivanIvanov);
            setTitle("Второй Джава эвент");
            setDescription("Второй джава эвент");
        }};

        Event event3 = new Event() {{
            setTime(110L, 200L);
            setRoom(redRoom);
            setReader(petrPetrov);
            setTitle("Фронтендовый эвент");
            setDescription("Страшный фронтендовый эвент");
        }};

        Event event4 = new Event() {{
            setTime(100L, 300L);
            setRoom(yellowRoom);
            setReader(babaKlava);
            setTitle("Как уронить сервер если нет высшего образования");
            setDescription("Исповедь самого опасного человека на свете");
        }};

        Event event5 = new Event() {{
            setTime(310L, 400L);
            setRoom(redRoom);
            setReader(babaKlava);
            setTitle("Клава наносит ответный удар");
            setDescription("Рассвет Сергеевны");
        }};

        addEvent(event1);
        addEvent(event2);
        addEvent(event3);
        addEvent(event4);
        addEvent(event5);
    }
}
