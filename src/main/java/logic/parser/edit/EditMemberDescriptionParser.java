package logic.parser.edit;

import common.DukeException;
import logic.command.Command;
import logic.command.RenameMemberCommand;
import logic.command.RenameTaskCommand;
import logic.command.edit.EditMemberDescriptionCommand;
import model.Model;

public class EditMemberDescriptionParser {

    public static final String EDIT_MEMBER_DESCRIPTION_USAGE =
            "usage: edit member des [Old Name] /to [New Name]";
    public static final String EMPTY_OLD_NAME_MESSAGE = "[Old Name] cannot be empty!";
    public static final String EMPTY_NEW_NAME_MESSAGE = "[NEW Name] cannot be empty!";
    public static final String TO_NOT_FOUND_MESSAGE = "Please input a /to";

    /**
     * Parses the user input and returns EditMemberDescriptionCommand
     * @param argument user input argument
     * @return EditMemberDescriptionCommand
     * @throws DukeException exception
     */
    //@@author JasonChanWQ
    public static Command parseEditMemberDescription(String argument) throws DukeException {
        if (argument.equals("")) {
            throw new DukeException(EDIT_MEMBER_DESCRIPTION_USAGE);
        } else {
            String keyword = argument.trim();
            boolean isFound = keyword.indexOf(" /to ") != -1 ? true : false;

            if (isFound == true) {
                String[]arrOfStr = keyword.split(" /to ",2);
                String oldName = arrOfStr[0];
                String newName = arrOfStr[1];
                if (oldName.equals("")) {
                    throw new DukeException(EMPTY_OLD_NAME_MESSAGE);
                } else if (newName.equals("")) {
                    throw new DukeException(EMPTY_NEW_NAME_MESSAGE);
                } else {
                    return new EditMemberDescriptionCommand(oldName, newName);
                }
            } else {
                throw new DukeException(TO_NOT_FOUND_MESSAGE);
            }
        }
    }
}
