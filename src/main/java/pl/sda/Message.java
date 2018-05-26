package pl.sda;

import pl.sda.users.User;

import java.time.LocalDateTime;

public class Message {
    private String content;

    private User autor;
    private LocalDateTime created;

    public Message(){}

    public Message(String content, User autor, LocalDateTime created) {
        this.content = content;
        this.autor = autor;
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getMessage(){

        String userName = (autor != null) ? autor.getUsername() : "Anonymous";

        return created.toString() + " " + userName + " " + content;
    }
}
