package logic.command;

import common.DukeException;
import common.LoggerController;
import model.MemberManager;
import model.Member;
import model.Model;
import model.Task;
import model.TasksManager;

import java.util.ArrayList;
import java.util.logging.Logger;

public class MatchCommand extends Command {
    private String taskName;

    public MatchCommand(String name) {
        taskName = name;
    }

    @Override
    public CommandOutput execute(Model model) throws DukeException {
        TasksManager tasksManager = model.getTasksManager();
        MemberManager memberManager = model.getMemberManager();
        ArrayList<Member> memberList = memberManager.getMemberList();
        Task task = tasksManager.getTaskByName(taskName);
        ArrayList<String> reqSkills = task.getReqSkills();
        ArrayList<String> matchedMembers = new ArrayList<>();
        for (int i = 0; i < memberList.size(); i += 1) {
            String matchedMemberName = "";
            matchedMemberName = getMemberWithSkill(memberList.get(i), reqSkills);
            if (matchedMemberName != null) {
                matchedMembers.add(matchedMemberName);
                LoggerController.logDebug(this.getClass(), matchedMemberName);
            }
        }

        String userOutput = "These are the members with the relevant skills: \n";
        for (int i = 0; i < matchedMembers.size(); i += 1) {
            userOutput += (i + 1) + ": " + matchedMembers.get(i) + "\n";
        }
        if(matchedMembers.size()==0){
            userOutput = "No matching members who have the skills or task has required no skills assigned";
        }
        return new CommandOutput(userOutput);
    }

    private String getMemberWithSkill(Member member, ArrayList<String> reqSkills) {
        for (int j = 0; j < reqSkills.size(); j += 1) {
            if (member.hasSkill(reqSkills.get(j))) {
                return member.getName();
            }
        }
        return null;
    }
}
