package Commands;

import Logic.LogicException;
import UserInterface.Command;
import UserInterface.InputException;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateDirectory extends Command {

    private Pattern pattern = Pattern.compile("^create dir " + "(" + "[a-zA-Z]+" + ")");

    private String argument;

    @Override
    public void setArguments(String argument) throws InputException {
        Matcher matcher = pattern.matcher(argument);
        if (matcher.matches()) {
            this.argument = matcher.group(1);


        }
    }

    @Override
    public void execute() {

        try {
            System.out.println(noteAdmin.createDirectory(argument));
        } catch (LogicException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected Pattern getPattern() {
        return this.pattern;
    }

}
