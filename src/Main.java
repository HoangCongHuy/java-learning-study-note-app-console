import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileStorage fileStorage = new FileStorage();

        System.out.println("Welcome Note App!");
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
                scanner.nextLine();
                switch (option) {
                    case 1:
                        getNotes(fileStorage);
                        break;
                    case 2:
                        addNote(fileStorage, scanner);
                        break;
                    case 3:
                        editNote(fileStorage, scanner);
                        break;
                    case 4:
                        deleteNote(fileStorage, scanner);
                        break;
                    case 5:
                        System.exit(0);
                        scanner.close();
                        break;
                    default:
                        throw new Exception("Invalid option");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void getNotes(FileStorage fileStorage) {
        List<Note> notes = fileStorage.loadFromFile();
        for (Note note : notes) {
            System.out.println(note);
        }
    }

    public static void addNote(FileStorage fileStorage, Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter content: ");
        String content = scanner.nextLine();
        List<Note> notes = fileStorage.loadFromFile();
        int id = !notes.isEmpty() ? notes.get(notes.size() - 1).getId() + 1 : 1;
        LocalDate localDate = LocalDate.now();
        Note note = new Note(id, title, content, localDate);
        notes.add(note);
        fileStorage.saveToFile(notes);
    }

    public static void editNote(FileStorage fileStorage, Scanner scanner) {
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        List<Note> notes = fileStorage.loadFromFile();
        Note note = findNoteById(notes, id);
        if (note == null) {
            System.out.println("Note not found");
            return;
        }
        int index = findNoteIndexById(notes, id);
        LocalDate localDate = LocalDate.now();

        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter content: ");
        String content = scanner.nextLine();
        notes.set(index, new Note(id, title, content, localDate));
        fileStorage.saveToFile(notes);
    }

    public static void deleteNote(FileStorage fileStorage, Scanner scanner) {
        System.out.print("Enter id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            List<Note> notes = fileStorage.loadFromFile();
            notes.remove(id - 1);
            fileStorage.saveToFile(notes);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Can not find note with id " + id);
        }
    }

    private static Note findNoteById(List<Note> notes, int id) {
        for (Note note: notes) {
            if (note.getId() == id) {
                return note;
            }
        }

        return null;
    }

    private static int findNoteIndexById(List<Note> notes, int id) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == id) {
                return i;
            }
        }

        return -1;
    }
}