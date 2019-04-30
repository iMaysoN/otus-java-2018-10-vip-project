package ru.otus.storage.entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
//    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
//    private Set<Event> events;

    public Room() {
    }

    public Room(String title, String description) {
        this.title = title;
        this.description = description;
//        this.events = new HashSet<>();
    }

//    public Room(String title, String description, Set<Event> events) {
//        this.title = title;
//        this.description = description;
//        this.events = events;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

//    public Collection<Event> getEvents() {
//        return events;
//    }

//    public void setEvents(Set<Event> events) {
//        this.events = events;
//    }

//    public void addEvent(Event event) {
//        this.events.add(event);
//    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Room.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("description='" + description + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equal(id, room.id) &&
                Objects.equal(title, room.title) &&
                Objects.equal(description, room.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, title, description);
    }
}
