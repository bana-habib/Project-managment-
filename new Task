import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NewTask extends JFrame {
    private JButton cancel_btn;
    private JTextField name_txt;
    private JComboBox team_cb;
    private JComboBox project_cb;
    private JFormattedTextField duration_txt;
    private JTextArea desc_textArea;
    private JSpinner point_spin;
    private JButton save_btn;
    private JPanel newTask_pnl;
    private JComboBox task_CB;

    public NewTask() {

        view("");

        setContentPane(newTask_pnl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        //button actions
        save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //stops user from being able to enter an empty field
                if(name_txt.getText().equals("")||duration_txt.equals("")||desc_textArea.equals("")){
                    JOptionPane.showMessageDialog(null, "Please make sure all fields have been completed.");
                }
                else {
                    //adds all the data from the GUI to a list which is then passed to the database
                    List<String> dataList = new ArrayList<String>();
                    dataList.add(name_txt.getText());

                    List<String> teamsList = new ArrayList<String>();
                    teamsList = DataClassKt.readText("teams");
                    int teamSize = teamsList.size();

                    String teamID = "0";


                    //from team name works out team ID
                    for (int x = 0; x < teamSize; x++) {
                        String element = teamsList.get(x);
                        String[] array = element.split(",");
                        if (array[1].equals(team_cb.getSelectedItem().toString())) {
                            teamID = array[0];
                            break;
                        }
                    }

                    List<String> projectsList = new ArrayList<String>();
                    projectsList = DataClassKt.readText("projects");
                    int projectSize = projectsList.size();

                    //from project name works out project ID
                    String projectID = "0";
                    for (int x = 0; x < projectSize; x++) {
                        String element = projectsList.get(x);
                        String[] array = element.split(",");
                        if (array[1].equals(project_cb.getSelectedItem().toString())) {
                            projectID = array[0];
                            break;
                        }
                    }


                    //depending on where in the order of tasks it is adds the dependencies to the list
                    //A will be the first task, Z the last and the other task IDs will be used to link them
                    String previousTask = "A";
                    List<String> taskList = new ArrayList<String>();
                    taskList = DataClassKt.readText("tasks");
                    int tasksSize = taskList.size();

                    for (int x = 0; x < tasksSize; x++) {
                        String element = taskList.get(x);
                        String[] array = element.split(",");
                        if (array[1].equals(task_CB.getSelectedItem().toString())) {
                            previousTask = array[0];
                            break;
                        }
                        else if ((task_CB.getSelectedItem().toString()).equals("Final task")) {
                            previousTask = "Z";
                            break;
                        }
                    }

                    //code to save to database
                    dataList.add(teamID);
                    dataList.add(projectID);
                    dataList.add(duration_txt.getText());
                    dataList.add(desc_textArea.getText());
                    dataList.add(point_spin.getValue().toString());
                    dataList.add(previousTask);
                    DataClassKt.addListText("tasks", dataList);
                    setVisible(false);
                    ViewTasks start = new ViewTasks(dataList.get(1));
                    dispose();
                }
            }
        });
        //takes back to homepage
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage start = new Homepage();
                setVisible(false);
                dispose();
            }
        });
        project_cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //if the project is changed, the tasks combobox will be updated with the correct tasks
                fillTaskCB(project_cb.getSelectedItem().toString());
            }
        });
    }
        public void view(String projectName){

            //reads projects file
            List<String> projectsList = new ArrayList<String>();
            projectsList = DataClassKt.readText("projects");
            int projectSize = projectsList.size();

            //adds all the project names to a combobox
            for (int x = 0; x < projectSize; x++) {
                String element = projectsList.get(x);
                String[] array = element.split(",");
                project_cb.addItem(array[1]);
            }

            //same as above but teams
            List<String> teamList = new ArrayList<String>();
            teamList = DataClassKt.readText("teams");
            int teamSize = teamList.size();

            for (int x = 0; x < teamSize; x++) {
                String element = teamList.get(x);
                String[] array = element.split(",");
                team_cb.addItem(array[1]);
            }

            //fills the task combobox with the first projects tasks
            fillTaskCB(projectName);


    }

    //used to fill the task combobox
    private void fillTaskCB(String projectName){

        //starts by removing all the items so items do not just keep being added
        task_CB.removeAllItems();


        //reads projects file
        List<String> projectsList = new ArrayList<String>();
        projectsList = DataClassKt.readText("projects");
        int projectSize = projectsList.size();


        //reads task file
        List<String> taskList = new ArrayList<String>();
        taskList =DataClassKt.readText("tasks");
        int tasksSize = taskList.size();


        String projectID = "0";

        //works out project ID for selected project
            for(int x = 0;x<projectSize;x++)
            {
                String element = projectsList.get(x);
                String[]  array = element.split(",");
                if (array[1].equals(projectName)){
                    projectID = array[0];
                    break;
                }
            }

            //adds tasks assocaited with that project to the combobox
            for(int x = 0;x<tasksSize;x++)
            {
                String element = taskList.get(x);
                String[]  array = element.split(",");
                String test = array[3].trim();
                if (test.equals(projectID)){
                    task_CB.addItem(array[1]);
                }

                }

        task_CB.addItem("Final Task");

            //if the combobox only currently contains one item (final task)
        //know that this is the first task for this project
            if (task_CB.getItemCount()==1) {
                task_CB.removeAllItems();
                task_CB.addItem("First Task");
            }
        }




        }




