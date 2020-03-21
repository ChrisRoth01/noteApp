package Logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Class that represents a Note. A Note is consisting of a Date, the content of the Note and the size. The size is the number of chars of the content.
 *
 * @author Christopher Roth
 * @version 1.0
 */
public class Note implements Serializable {

    private static List<Integer> noteIds = new ArrayList<Integer>();
    private static int id = 0;
    private Date date;
    private int size;
    private String content;
    private String head;
    private Category category;

    /**
     * Constructor.
     *
     * @param date    Use System.currentTimeInMillis();
     * @param content the String content of the Note. Type whatever you need saved.
     * @param head    the head of the Note.
     */
    public Note(Date date, String head, String content, Category cat) {
        this.date = date;
        this.head = head;
        this.content = content;
        this.size = content.length();
        id = distributeIds();
        this.category = cat;
    }

    /**
     * Distributes Ids.
     *
     * @return
     */
    private static int distributeIds() {
        Collections.sort(noteIds);
        if (noteIds.isEmpty()) {
            id++;
            noteIds.add(id);
        }
        return noteIds.remove(noteIds.size() - 1);
    }

    @Override
    public String toString() {
        return date.toString() + ": " + head + "\n" + content;
    }

    public String getHead() {
        return this.head;
    }

    public Category getCategory() {
        return this.category;
    }
}
