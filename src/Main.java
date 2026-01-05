import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Welcome Note App!");
        while (true) {
            try {
                System.out.println("-------------------------------------");
                System.out.println("This is a note program.");
                System.out.println("1. View the notes");
                System.out.println("2. Add new note");
                System.out.println("3. Edit note");
                System.out.println("4. Delete note");
                System.out.println("5. End of program");
                System.out.print("Please select your options: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        getNotes();
                        break;
                    case 2:
                        addNote(scanner);
                        break;
                    case 3:
                        editNote(scanner);
                        break;
                    case 4:
                        deleteNote();
                        break;
                    case 5:
                        System.out.print("Enter title: ");
                        break;
                    default:
                        throw new Exception("Invalid option");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void getNotes() {
        FileStorage fileStorage = new FileStorage();
        List<Note> notes = fileStorage.loadFromFile();
        for (Note note : notes) {
            System.out.println(note);
        }
    }

    public static void addNote(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.next();
        System.out.print("Enter content: ");
        String content = scanner.next();
        FileStorage fileStorage = new FileStorage();
        List<Note> notes = fileStorage.loadFromFile();
        int id = !notes.isEmpty() ? notes.get(notes.size() - 1).getId() + 1 : 1;
        LocalDate localDate = LocalDate.now();
        Note note = new Note(id, title, content, localDate);
        notes.add(note);
        fileStorage.saveToFile(notes);
    }

    public static void editNote(Scanner scanner) {
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
    }

    public static void deleteNote() {

    }
}