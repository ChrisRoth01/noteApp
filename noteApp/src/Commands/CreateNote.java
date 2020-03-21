package Commands;

import Logic.LogicException;
import UserInterface.Command;
import UserInterface.InputException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNote extends Command {
    private Pattern pattern = Pattern.compile("^create note " + "(" + "[a-zA-Z]+" + ") " + "(" + "[a-zA-Z]+" + ")" + " (" + "[a-zA-Z]+" + ")");
    private Map<Integer, String> arguments = new HashMap<Integer, String>();

    @Override
    public void execute() {
        try {
            System.out.println(noteAdmin.createNote(arguments.get(0), arguments.get(1), arguments.get(2)) + "\ncreated");
        } catch (LogicException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void setArguments(String argument) throws InputException {
        Matcher matcher = pattern.matcher(argument);
        if (matcher.matches()) {
            arguments.put(0, matcher.group(1));
            arguments.put(1, matcher.group(2));
            arguments.put(2, matcher.group(3));
        }
    }

    @Override
    protected Pattern getPattern() {
        return this.pattern;
    }
}
