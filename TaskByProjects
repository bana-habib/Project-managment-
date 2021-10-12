import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TasksbyProject extends JFrame{
    private JButton homeButton;
    private JButton newTaskButton;
    private JPanel projectTasks;
    private JButton openTaskButton;
    private JComboBox task_CB;
    private JLabel projectTitle;
    private JProgressBar progressBar1;
    private JScrollPane scrollpane;



    public TasksbyProject(String projectName){
        //sets up GUI components
        view(projectName);
        //takes back to homepage
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage start = new Homepage();
                setVisible(false);
                dispose();
            }
        });
        //opens the new task page to allow the user to add a new task to the project
        newTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewTask open = new NewTask();
                setVisible(false);
                dispose();
            }
        });

        //opens the view task page to allow the user to view all the details about the task
        openTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = task_CB.getSelectedItem().toString();
                ViewTasks open = new ViewTasks(selected);
                setVisible(false);
                dispose();
            }
        });
    }

    public void view(String projectName){

        //sets label to the current project name
        projectTitle.setText(projectName);

        //sets column names and creates 2D array to store the data for the table in
        String[] columnNames = {"ID","Task Name", "Team","Project", "Duration", "Description", "Points", "Previous Task ID"};
        String [][]tableData = new String[100][8];


        //reads the task file
        List<String> taskList = new ArrayList<String>();
        taskList =DataClassKt.readText("tasks");
        int tasksSize = taskList.size();

        //reads the projects
        List<String> projectsList = new ArrayList<String>();
        projectsList =DataClassKt.readText("projects");
        int projectSize = projectsList.size();


        String projectID = "0";

        //works out the project ID From the project name
        for(int x = 0;x<projectSize;x++)
        {
            String element = projectsList.get(x);
            String[]  array = element.split(",");
            if (array[1].equals(projectName)){
                projectID = array[0];
                break;

            }
        }

        int z = 0;

        //adds the tasks to the drop down list and to the array for the table data
        for(int x = 0;x<tasksSize;x++)
        {
            String element = taskList.get(x);
            String[]  array = element.split(",");
            String project = array[3].trim();
            if (project.equals(projectID)){
                task_CB.addItem(array[1]);
                for (int y = 0; y<array.length;y++){
                    tableData[z][y]=array[y];
                }
                z =+ 1;
            }

        }

        //adds table data to the table and to the GUI
        JTable task_tbl = new JTable(tableData,columnNames);
        scrollpane.getViewport().add(task_tbl);

        //GUI contents
        setContentPane(projectTasks);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
