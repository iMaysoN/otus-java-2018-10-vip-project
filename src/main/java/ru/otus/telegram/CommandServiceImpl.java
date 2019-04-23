package ru.otus.telegram;

import org.springframework.stereotype.Service;
import ru.otus.events.Event;
import ru.otus.events.EventsRepository;
import ru.otus.services.CommandService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommandServiceImpl implements CommandService {
    private final EventsRepository eventsRepository;

    public CommandServiceImpl(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Override
    public ToTelegram handleCommand(Update update) {
        String command = update.getMessage().getText();
        String chatId = update.getMessage().getChat().getId().toString();
        String response = "";
        if (command.startsWith("/now")) {
            response = now();
        }
        return new ToTelegram(chatId, response);
    }

    private String now() {
        Set<Event> currentEvents = eventsRepository.getCurrentEvents(240L);
        String eventsAsString = currentEvents.stream().map(Event::getTitle).collect(Collectors.joining(" - "));
        return String.format("Events list: %s", eventsAsString);
    }

    private void timetable() {

    }

    private void readers() {

    }

    private void schedule() {

    }

    private void find() {

    }
}
