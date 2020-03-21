package Logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteDirectory {

    private Path path;
    private Category category;
    private List<Note> notes;

    public NoteDirectory(Category category, Path path) {
        this.category = category;
        this.notes = new ArrayList<Note>();
        this.path = path;

    }

    /**
     * Creates a Note out of a head String and a content String.
     *
     * @param head
     * @param content
     * @return
     * @throws IOException
     * @throws LogicException
     */
    public String createNote(String head, String content) throws IOException, LogicException {
        Note note = this.checkIfNoteExists(head);
        if (note != null) {
            throw new LogicException("Note with this head already existing!");
        }
        note = new Note(new Date(System.currentTimeMillis()), head, content, this.category);
        notes.add(note);
        Path path2 = Paths.get(path + "//" + head + ".txt");
        File file = new File(String.valueOf(path2));
        file.createNewFile();
        FileOutputStream fileOut = new FileOutputStream(String.valueOf(path2));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(note);
        objectOut.close();
        return note.toString();
    }


    private Note checkIfNoteExists(String head) {
        return this.notes.stream().filter(s -> s.getHead().equals(head)).findAny().orElse(null);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return this.category.toString() + ": " + this.path.toString() + " " + this.notes.size();
    }

    public Note findNote(String head) {
        return this.notes.stream().filter(s -> s.getHead().equals(head)).findAny().orElse(null);
    }

    public void add(Note note) {
        this.notes.add(note);
    }

    public void removeNote(Note note) {
        this.notes.remove(note);
    }
}
