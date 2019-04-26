package ru.otus.telegram;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import ru.otus.events.Event;
import ru.otus.events.EventsRepository;
import ru.otus.events.Reader;
import ru.otus.events.Room;
import ru.otus.services.CommandService;
import ru.otus.services.FrontendService;
import ru.otus.telegram.models.input.Update;
import ru.otus.telegram.models.output.InlineButton;
import ru.otus.telegram.models.output.InlineKeyboard;
import ru.otus.telegram.models.output.ReplyButton;
import ru.otus.telegram.models.output.ReplyKeyboard;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommandServiceImpl implements CommandService {
    private final EventsRepository eventsRepository;
    private final FrontendService frontendService;
    private static Gson gson = new Gson();

    public CommandServiceImpl(EventsRepository eventsRepository,
                              FrontendService frontendService) {
        this.eventsRepository = eventsRepository;
        this.frontendService = frontendService;
    }

    @Override
    public void handleCommand(Update update) {
        String command = update.getMessage().getText();
        String chatId = update.getMessage().getChat().getId().toString();
        if (command.startsWith("/start")) {
            getAllCommand(chatId);
        } else if (command.startsWith("/now")) {
            findEventAtNow(chatId);
        } else if (command.startsWith("/timetable")) {
            getEventsTimetable(chatId);
        } else if (command.startsWith("/readers")) {
            getReaders(chatId);
        } else if (command.startsWith("/schedule")) {
            schedule(chatId);
        } else if (command.startsWith("/find")) {
            find(chatId);
        } else {
            frontendService.sendResponse(chatId, "Here will be better in future");
        }
    }

    private long getCurrentTime() {
        return 120L;
    }

    //Стартовая команда
    private void getAllCommand(String chatId) {
//    Здесь будет добавление пользователя в базу для различных вещей
        ReplyKeyboard replyKeyboard = new ReplyKeyboard();
        replyKeyboard.addButton(new ReplyButton("/now"));
        replyKeyboard.addButton(new ReplyButton("/timetable"));
        replyKeyboard.addButton(new ReplyButton("/readers"));
        replyKeyboard.addButton(new ReplyButton("/schedule"));
        replyKeyboard.addButton(new ReplyButton("/find"));
        String keyboardAsString = gson.toJson(replyKeyboard);
        String responseText = "Here list of command";
        frontendService.sendResponse(chatId, responseText, keyboardAsString);
    }

    //Список событий, которые уже начались, но еще не закончились
    private void findEventAtNow(String chatId) {
        long currentTime = getCurrentTime();
        Set<Event> currentEvents = eventsRepository.getEventsInTime(currentTime);
        List<String> eventsList = currentEvents.stream()
                .map(Event::getEventPresentation)
                .collect(Collectors.toList());
        String responseText;
        if (eventsList.size() > 0) {
            responseText = String.format("Events list: %s%n", String.join("\n", eventsList));
        } else {
            responseText = "No events at this time.";
        }

        frontendService.sendResponse(chatId, responseText);
    }

    //Список событий, которые еще не начинались, с сортировкой по комнатам
    private void getEventsTimetable(String chatId) {
        Set<Event> notStarted = eventsRepository.getNotStartedEvents(getCurrentTime());
        Map<Room, Set<Event>> eventsByRoom = new HashMap<>();
        eventsRepository.getRooms().forEach(room -> {
                    Set<Event> eventsInRoom = notStarted.stream()
                            .filter(event -> event.getRoom().equals(room))
                            .collect(Collectors.toSet());
                    if (eventsInRoom.size() > 0) {
                        eventsByRoom.put(room, eventsInRoom);
                    }
                }
        );
        StringBuilder message = new StringBuilder();
        for (Room room : eventsByRoom.keySet()) {
            message.append(String.format("Events in `%s`%n", room.getTitle()));
            TreeMap<Long, String> timeToTitle = new TreeMap<>();
            eventsByRoom.get(room).forEach(event -> timeToTitle.put(event.getTimeStart(), event.getTitle()));
            timeToTitle.forEach((timeToStart, title) -> message.append(String.format("%s - %s%n", timeToStart, title)));
        }

        frontendService.sendResponse(chatId, message.toString());
    }

    //Список спикеров
    private void getReaders(String chatId) {
        Set<Reader> readers = eventsRepository.getReaders();
        if (readers.size() > 0) {
            InlineKeyboard inlineKeyboard = new InlineKeyboard();
            readers.stream().map(reader -> reader.getFirstName() + " " + reader.getSecondName())
                    .sorted()
                    .collect(Collectors.toList())
                    .forEach(readerFullName -> inlineKeyboard.addButton(new InlineButton(readerFullName)));
            frontendService.sendResponse(chatId, "Readers list:", gson.toJson(inlineKeyboard));
        } else {
            frontendService.sendResponse(chatId, "No readers found");
        }
    }

    //Запланировать событие в "мой календарь" - нужно с базой
    private void schedule(String chatId) {
        frontendService.sendResponse(chatId, "Not implemented");
    }

    //Найти по имени/описанию - спикера, событие, стенд
    private void find(String chatId) {
        frontendService.sendResponse(chatId, "Not implemented");
    }
}
