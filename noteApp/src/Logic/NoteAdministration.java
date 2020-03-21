package Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NoteAdministration {

    private static final String highestPath = "C:\\Users\\Christopher Roth\\NotesJava";

    private final List<NoteDirectory> noteDirectories;

    public NoteAdministration() {
        noteDirectories = new ArrayList<NoteDirectory>();
        this.fillNoteAdministration();
    }

    public void fillNoteAdministration() {
        File file = new File(highestPath);
        this.listFilesForFolder(file);
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                NoteDirectory dir = new NoteDirectory(new Category(fileEntry.getName()), Paths.get(fileEntry.getPath()));

                noteDirectories.add(dir);
                listFilesForFolder(fileEntry);
            } else {
                Note note = (Note) this.readObjectFromFile(fileEntry.getPath());

                this.directoryExists(note.getCategory()).add(note);
            }
        }
    }

    private Object readObjectFromFile(String path) {
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Creates a new Directory. Checks if Directory with given Category already exists.
     *
     * @param argument
     */
    public String createDirectory(String argument) throws LogicException, IOException {
        Category category = new Category(argument);

        NoteDirectory noteDirectory;
        noteDirectory = this.directoryExists(category);
        if (noteDirectory != null) {
            throw new LogicException("Category already exists");
        } else {
            Path path = Paths.get(highestPath + "\\" + category.getName());
            Files.createDirectories(path);
            noteDirectory = new NoteDirectory(category, path);

        }
        System.out.println(noteDirectory.toString());
        this.noteDirectories.add(noteDirectory);
        return "created";
    }

    /**
     * Checks if a directory with a given category exists.
     *
     * @param category
     * @return the category or null.
     */
    private NoteDirectory directoryExists(Category category) {
        return this.noteDirectories.stream().filter(s -> s.getCategory().equals(category)).findAny().orElse(null);
    }

    /**
     * Creates a Note in a given category, with a specific head and content
     *
     * @param category
     * @param head     the head of the note.
     * @param note     the content of the note.
     * @return
     * @throws LogicException
     * @throws IOException
     */
    public String createNote(String category, String head, String note) throws LogicException, IOException {
        NoteDirectory dir = this.directoryExists(new Category(category));
        if (dir == null) {
            throw new LogicException("Category not existing!");
        }

        return dir.createNote(head, note);
    }

    /**
     * Returns a note of a specific category with a specific head as a String.
     *
     * @param category
     * @param head
     * @return
     * @throws LogicException
     */
    public String returnNoteToString(String category, String head) throws LogicException {

        NoteDirectory dir = this.noteDirectories.stream().filter(s -> s.getCategory().equals(new Category(category))).findAny().orElse(null);
        if (dir == null) {
            throw new LogicException("Category not existing!");
        }
        Note note = dir.findNote(head);
        if (note == null) {
            throw new LogicException("Note not existing in this Category!");
        }
        return note.toString();

    }

    /**
     * Returns all created Directories as String.
     */
    public String directoriesToString() {
        String toReturn = "";
        for (NoteDirectory dir : this.noteDirectories) {
            toReturn += dir.toString() + "\n";
        }
        return toReturn.trim();
    }

    public void deleteNote(String category, String head) throws LogicException {
        NoteDirectory dir = this.directoryExists(new Category(category));
        if(dir == null) {
            throw new LogicException("Category not existing!");
        }
        Note note =  dir.findNote(head);
        if(note == null) {
            throw new LogicException("Note not existing!");
        }
        dir.removeNote(note);
        System.out.println(highestPath + "\\" + dir.getCategory() + "\\" + note.getHead() + ".txt");
        File file = new File(highestPath + "//" + dir.getCategory() + "//" + note.getHead() + ".txt");
        file.delete();
    }
}
