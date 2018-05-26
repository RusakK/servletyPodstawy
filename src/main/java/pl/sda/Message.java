package pl.sda;

import java.time.LocalDateTime;

public class Message {
    private String content;

    private String autor;
    private LocalDateTime created;

    public Message(){}

    public Message(String content, String autor, LocalDateTime created) {
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getMessage(){

        return created.toString() + " " + autor + " " + content;
    }
}
