package commands;

import tasks.Task;
import utils.Storage;
import core.Ui;

import java.util.ArrayList;

public class ByeCommand extends Command {
    /**
     * This is a class for command BYE, which exit the Duke assistant.
     */
    public ByeCommand(){
    }

    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) {
        Ui.print("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}