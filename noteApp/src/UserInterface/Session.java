package UserInterface;

import Logic.NoteAdministration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Class for a Session. A Session combines the UserInterface with the Game.
 * Handles the Terminal input and executes the fitting Command, which is
 * returned by the CommandCenter.
 *
 * @author Christopher Roth
 * @version 1.0
 */
public class Session {
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    /**
     * Boolean for the while(true) loop in the run() method. Is set to false if exit
     * is typed in the Terminal.
     */
    private boolean finished = false;
    /**
     * A ModelRailWay.
     */
    private NoteAdministration noteAdmin;

    /**
     * Constructor, which initiates a ModelRailWay.
     */
    public Session() {
        noteAdmin = new NoteAdministration();
    }

    /**
     * The method which is executed in the main-Class. Handles the Terminal input
     * and executes the fitting Command, which is returned by the CommandCenter.
     */
    public void run() {
        CommandCenter center = new CommandCenter(this);

        while (!finished) {
            try {

                String input = IN.readLine();
                Command command = center.getCommand(input);
                command.execute();
            } catch (InputException | IOException i) {
                System.out.println(i.getMessage());
            }
        }
    }

    /**
     * The method which sets finished to true, which leads to a termination of the
     * program.
     */
    public void quit() {
        finished = true;
    }

    /**
     * Getter for the Game
     *
     * @return the Game of the Session.
     */
    public NoteAdministration getNoteAdmin() {
        return this.noteAdmin;
    }
}