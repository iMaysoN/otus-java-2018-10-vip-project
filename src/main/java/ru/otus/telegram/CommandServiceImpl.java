package ru.otus.telegram;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import ru.otus.services.CommandService;
import ru.otus.services.DbService;
import ru.otus.services.FrontendService;
import ru.otus.storage.entities.Event;
import ru.otus.storage.entities.Reader;
import ru.otus.storage.entities.Room;
import ru.otus.telegram.models.input.Update;
import ru.otus.telegram.models.output.InlineButton;
import ru.otus.telegram.models.output.InlineKeyboard;
import ru.otus.telegram.models.output.ReplyButton;
import ru.otus.telegram.models.output.ReplyKeyboard;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommandServiceImpl implements CommandService {
    private final DbService dbService;
    private final FrontendService frontendService;
    private static Gson gson = new Gson();

    public CommandServiceImpl(DbService dbService,
                              FrontendService frontendService) {
        this.dbService = dbService;
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
    //Должна выводить список всех доступных команд (нужно потестить - вдруг удобней одноразово ее выводить)
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
    //Нужно для того, чтобы узнать, что идет сейчас, если человек хочет поменять что-то или проголосовать за текущее
    private void findEventAtNow(String chatId) {
        long currentTime = getCurrentTime();
        Set<Event> currentEvents = dbService.getEventsInTime(currentTime);
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

    //Добавить команду "Следующее"
    //Выводит только список следующих эвентов, т.е. тех, которые минимально стартуют от текущего времени
    //Каждый эвент как inline_button, чтобы можно было добавить в schedule
    private void whatNext(String chatId) {

    }

    //Список событий, которые еще не начинались, с сортировкой по комнатам
    private void getEventsTimetable(String chatId) {
        Set<Event> notStarted = dbService.getNotStartedEvents(getCurrentTime());
        Map<Room, Set<Event>> eventsByRoom = new HashMap<>();
        dbService.getRooms().forEach(room -> {
                    Set<Event> eventsInRoom = notStarted.stream()
                            .filter(event -> event.isEventInThisRoom(room))
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
    //Нажатие на спикера вывоводит полную информацию по нему, а также список лекций
    //При нажатии на конкретную лекцию - выводится информация по ней и если она уже стартовала, то
    //возможно проголосовать.
    private void getReaders(String chatId) {
        List<Reader> readers = dbService.getReaders();
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

    //Голосовалка
    //Каждый пользователь может поставить лекции от 1 до 10 звезд
    //Оценку можно менять в течении получаса после окончания
    //Голосовать можно не раньше, чем лекция стартует
    private void vote(String chatId) {

    }
}
