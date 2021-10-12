import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TasksbyTeam extends JFrame {
    private JButton homeButton;
    private JButton newTaskButton;
    private JPanel teamTasks;
    private JComboBox task_CB;
    private JButton openTaskButton;
    private JLabel teamTitle;
    private JButton viewByTeamButton;

    public TasksbyTeam(String teamName){
        model(teamName);
        // creates home button
        homeButton.addActionListener(new ActionListener() {
            @Override
            // opens home page
            public void actionPerformed(ActionEvent e) {
                Homepage start = new Homepage();
                setVisible(false);
                dispose();
            }
        });
        // creates new task button
        newTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //doesn't work - throws null pointer
                //opens new task page
                NewTask open = new NewTask();
                setVisible(false);
                dispose();
            }
        });
    // creates o[en task button
        openTaskButton.addActionListener(new ActionListener() {
            @Override
            //opens the task selected in combo box
            public void actionPerformed(ActionEvent e) {
                String selected = task_CB.getSelectedItem().toString();
                ViewTasks open = new ViewTasks(selected);
                setVisible(false);
                dispose();
            }
        });
    }

    public void model(String teamName){

        teamTitle.setText(teamName);
        // reads tasks list
        List<String> taskList = new ArrayList<String>();
        taskList =DataClassKt.readText("tasks");
        int teamsSize = taskList.size();
        // reads teams list
        List<String> teamList = new ArrayList<String>();
        teamList =DataClassKt.readText("teams");
        int teamSize = teamList.size();

        String teamID ="0";
        // places team name
        for(int x = 0;x<teamSize;x++)
        {
            String element = teamList.get(x);
            String[]  array = element.split(",");
            if (array[1].equals(teamName)){
                teamID = array[0];
                break;
            }
        }

        // fills teams tasks in combo box
        for(int x = 0;x<teamsSize;x++)
        {
            String element = taskList.get(x);
            String[]  array = element.split(",");
            String test = array[2].trim();
            if (test.equals(teamID)){
                task_CB.addItem(array[1]);

            }

        }
        // fills content on page 
        setContentPane(teamTasks);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
