import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private static final String FILENAME = "notes.txt";

    public void saveToFile(List<Note> notes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Note note : notes) {
                writer.write(note.toFileString());
                writer.newLine();
            }
        } catch (IOException ignored) {
        }
    }

    public List<Note> loadFromFile() {
        List<Note> notes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String content = parts[2];
                    LocalDate date = LocalDate.parse(parts[3], formatter);

                    notes.add(new Note(id, title, content, date));
                }
            }
        } catch (IOException ignored) {
        }
        return notes;
    }
}
