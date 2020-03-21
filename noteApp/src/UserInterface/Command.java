package UserInterface;

import Logic.NoteAdministration;

import java.util.regex.Pattern;


/**
 * Abstract Class which is a blueprint for all Commands.
 *
 * @author Christopher Roth
 * @version 1.0
 */
public abstract class Command {

    /**
     * A game.
     */
    protected NoteAdministration noteAdmin;

    /**
     * Executes the fitting command, depending on the fitting regex Pattern. Takes
     * the arguments which are set in the setArguments() method.
     */
    public abstract void execute();

    /**
     * The method which sets the arguments, depending on the fitting regex Pattern.
     * If no arguments need to be set, the method is empty. If more than one
     * argument needs to be set the arguments are either saved in a String or a Map.
     *
     * @param argument the Terminal-Input
     * @throws InputException if the Terminal-Input does not fit a regex-Pattern of
     *                        one of the Commands.
     */
    protected abstract void setArguments(String argument) throws InputException;

    /**
     * Sets the session of a command, so no NullPointerException occurs, when a
     * Command Class is executed.
     *
     * @param session the session to be set.
     */
    void setSession(Session session) {
        this.noteAdmin = session.getNoteAdmin();
    }

    /**
     * Getter for the Pattern of the commands.
     *
     * @return the regex-Pattern
     */
    protected abstract Pattern getPattern();
}