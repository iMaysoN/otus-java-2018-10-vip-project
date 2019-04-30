package ru.otus.storage.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "start")
    private Long timeStart;
    @Column(name = "end")
    private Long timeEnd;
    @Column
    private String title;
    @Column
    private String description;
    @Column(name = "room_id")
    private Long roomId;
    @Column(name = "reader_ids")
    private String readerIds;
    @Transient
    private Set<Long> readerIdsSet = new HashSet<>();

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "reader_id", nullable = false)
//    private Reader reader;
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "room_id", nullable = false)
//    private Room room;

    public Event() {
    }

    public Event(Long timeStart, Long timeEnd, String title, String description, Reader reader, Room room) {
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.title = title;
        this.description = description;
//        this.reader = reader;
//        this.room = room;
        this.roomId = room.getId();
        this.readerIdsSet.add(reader.getId());
        setReaderIds(this.readerIdsSet);
    }

    public String getReaderIds() {
        return readerIds;
    }

    public void setReaderIds(String readerIds) {
        this.readerIds = readerIds;
    }

    public void setReaderIds(Set<Long> readerIdsSet) {
        setReaderIds(String.join(",",
                readerIdsSet.stream().map(Object::toString).collect(Collectors.toSet()))
        );
    }

    public String getEventPresentation() {
        return String.format("Event `%s`. Reader - %s. Place - %s.", title, "reader", "room");
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

    public boolean isEventInThisRoom(Room room) {
        return roomId.equals(room.getId());
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

    public Set<Long> getReaderIdsSet() {
        return readerIdsSet;
    }

    public void setReaderIdsSet(Set<Long> readerIdsSet) {
        this.readerIdsSet = readerIdsSet;
    }

    public void addReader(Reader reader) {
        this.readerIdsSet.add(reader.getId());
        setReaderIds(readerIdsSet);
    }

    public void setReader(Reader reader) {
        addReader(reader);
    }

    public void setRoom(Room room) {
        this.roomId = room.getId();
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
