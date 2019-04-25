package ru.otus.telegram;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import ru.otus.events.Event;
import ru.otus.events.EventsRepository;
import ru.otus.events.Room;
import ru.otus.services.CommandService;
import ru.otus.services.FrontendService;
import ru.otus.telegram.models.input.Update;
import ru.otus.telegram.models.output.ReplyButton;
import ru.otus.telegram.models.output.ReplyKeyboardMarkup;

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
        String responseText = "";
        String keyboard = "";
        if (command.startsWith("/start")) {
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            keyboardMarkup.addButton(new ReplyButton("test"));
            responseText = "Here list of command";
            keyboard = gson.toJson(keyboardMarkup);
        }
        if (command.startsWith("/now")) {
            responseText = now();
        }
        if (command.startsWith("/timetable")) {
            responseText = timetable();
        }
        frontendService.sendResponse(chatId, responseText, keyboard);
    }

    private long getCurrentTime() {
        return 120L;
    }

    //Стартовая команда
//    private String start() {
    //Здесь будет добавление пользователя в базу для различных вещей
//    }

    //Список событий, которые уже начались, но еще не закончились
    private String now() {
        long currentTime = getCurrentTime();
        Set<Event> currentEvents = eventsRepository.getCurrentEvents(currentTime);
        List<String> eventsList = currentEvents.stream()
                .map(Event::getEventPresentation)
                .collect(Collectors.toList());

        if (eventsList.size() > 0) {
            return String.format("Events list: %s%n", String.join("\n", eventsList));
        } else {
            return "No events at this time.";
        }
    }

    //Список событий, которые еще не начинались, с сортировкой по комнатам
    private String timetable() {
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
        return message.toString();
    }

    //Список спикеров
    private void readers() {

    }

    //Запланировать событие в "мой календарь" - нужно с базой
    private void schedule() {

    }

    //Найти по имени/описанию - спикера, событие, стенд
    private void find() {

    }
}
