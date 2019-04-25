package ru.otus.events;

import static com.google.common.base.Preconditions.checkNotNull;

public class Event {
    private Long timeStart;
    private Long timeEnd;
    private Reader reader;
    private String title;
    private String description;
    private Room room;

    public static EventBuilder builder() {
        return new EventBuilder();
    }

    public Long getTimeStart() {
        return timeStart;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }

    public Reader getReader() {
        return reader;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getEventPresentation() {
        return String.format("Event `%s`. Reader - %s. Place - %s.", title, reader, room);
    }

    public Room getRoom() {
        return room;
    }

    static class EventBuilder {
        private Event event;

        EventBuilder() {
            this.event = new Event();
        }

        public EventBuilder setTime(Long from, Long till) {
            event.timeStart = from;
            event.timeEnd = till;
            return this;
        }

        public EventBuilder setReader(Reader reader) {
            event.reader = reader;
            return this;
        }

        public EventBuilder setTitle(String title) {
            event.title = title;
            return this;
        }

        public EventBuilder setDescription(String description) {
            event.description = description;
            return this;
        }

        public EventBuilder setRoom(Room room) {
            event.room = room;
            return this;
        }

        public Event build() {
            checkNotNull(event.timeEnd);
            checkNotNull(event.timeStart);
            checkNotNull(event.reader);
            checkNotNull(event.title);
            checkNotNull(event.room);
            if (event.description == null) {
                event.description = "";
            }
            return event;
        }
    }
}
