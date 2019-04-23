package ru.otus.telegram;

import org.springframework.stereotype.Service;
import ru.otus.events.Event;
import ru.otus.events.EventsRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service("commandHandler")
public class CommandHandler {
    private final EventsRepository eventsRepository;

    public CommandHandler(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public ToTelegram handleCommand(Update update) {
        String command = update.getMessage().getText();
        if (command.startsWith("/now")) {
            String eventsAsString = now().stream().map(Event::getTitle).collect(Collectors.joining(" - "));
            return new ToTelegram(
                    update.getMessage().getChat().getId().toString(),
                    String.format("Events list: %s", eventsAsString));
        }
        return null;
    }

    private Set<Event> now() {
        Set<Event> currentEvents = eventsRepository.getCurrentEvents(240L);
        return currentEvents;
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
