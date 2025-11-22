package ru.bank.star.postgresql.model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "chats")
public class Chats {
    @Id
    private Long id;

    private String userName;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "chats")
    @JsonManagedReference
    @JsonIgnore
    private List<Messages> messages;

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Chats{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Chats chats = (Chats) o;
        return Objects.equals(id, chats.id) && Objects.equals(userName, chats.userName) && Objects.equals(firstName, chats.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, firstName);
    }
}
