package Commands;

import UserInterface.Command;
import UserInterface.InputException;

import java.util.regex.Pattern;

public class PrintDirectories extends Command {
    private Pattern pattern = Pattern.compile("^print directories$");

    @Override
    public void execute() {
        System.out.println(noteAdmin.directoriesToString());
    }

    @Override
    protected void setArguments(String argument) throws InputException {
        //no arguments need to be set
    }

    @Override
    protected Pattern getPattern() {
        return pattern;
    }
}
