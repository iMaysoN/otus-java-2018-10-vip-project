package ru.otus.storage.entities;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Long timeStart;
    @Column
    private Long timeEnd;
    @Column
    private String title;
    @Column
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id", nullable = false)
    private Reader reader;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    public Event() {
    }

    public Event(Long timeStart, Long timeEnd, String title, String description, Reader reader, Room room) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.title = title;
        this.description = description;
        this.reader = reader;
        this.room = room;
    }

    public String getEventPresentation() {
        return String.format("Event `%s`. Reader - %s. Place - %s.", title, reader, room);
    }

    public void setTime(Long from, Long till) {
        setTimeStart(from);
        setTimeEnd(till);
    }

    public boolean isInProgress(Long currentTime) {
        return currentTime >= timeStart && currentTime <= timeEnd;
    }

    public boolean isEnded(Long currentTime) {
        return currentTime > timeEnd;
    }

    public boolean isNotStarted(Long currentTime) {
        return currentTime < timeStart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Long timeStart) {
        this.timeStart = timeStart;
    }

    public Long getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Long timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }
}
