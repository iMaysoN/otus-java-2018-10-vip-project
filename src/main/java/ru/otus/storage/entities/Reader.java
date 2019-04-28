package ru.otus.storage.entities;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column
    private String regalia;
//    @OneToMany(mappedBy = "reader", fetch = FetchType.EAGER)
//    private Set<Event> events;

    public Reader() {
    }

    public Reader(String firstName, String secondName, String regalia) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.regalia = regalia;
//        this.events = new HashSet<>();
    }

//    public Reader(String firstName, String secondName, String regalia, Set<Event> events) {
//        this.firstName = firstName;
//        this.secondName = secondName;
//        this.regalia = regalia;
//        this.events = events;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getRegalia() {
        return regalia;
    }

    public void setRegalia(String regalia) {
        this.regalia = regalia;
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
        return new StringJoiner(", ", Reader.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("secondName='" + secondName + "'")
                .add("regalia='" + regalia + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equal(id, reader.id) &&
                Objects.equal(firstName, reader.firstName) &&
                Objects.equal(secondName, reader.secondName) &&
                Objects.equal(regalia, reader.regalia);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstName, secondName, regalia);
    }
}
