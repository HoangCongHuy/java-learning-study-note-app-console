import java.time.LocalDate;

public class Note {
    private int id;
    private String title;
    private String content;
    private LocalDate creationDate;

    public Note(int id, String title, String content, LocalDate creationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String toFileString() {
        return id + "|" + title + "|" + content + "|" + creationDate;
    }

    public String toString() {
        return id + "|" + title + "|" + content + "|" + creationDate;
    }
}


