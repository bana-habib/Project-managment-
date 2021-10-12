import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ViewTasks extends JFrame {
    private JTextField name_txt;
    private JComboBox team_cb;
    private JTextArea desc_text;
    private JComboBox project_cb;
    private JFormattedTextField date_txt;
    private JButton save_btn;
    private JButton home_btn;
    private JLabel taskName_lbl;
    private JProgressBar progressBar1;
    private JPanel task_pnl;
    private JLabel projectName_lbl;
    private JLabel teamName_lbl;
    private JTextField points_txt;
    private JComboBox progress_CB;

    public ViewTasks(String taskName){
        //stops the user from being able to edit the task content
        taskName_lbl.setText(taskName);
        name_txt.setEditable(false);
        team_cb.setEditable(false);
        desc_text.setEditable(false);
        project_cb.setEditable(false);
        date_txt.setEditable(false);
        points_txt.setEditable(false);

        //GUI contents
        view(taskName);
        setContentPane(task_pnl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        //takes user back to the home page
        home_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage start = new Homepage();
                setVisible(false);
                dispose();
            }
        });
        save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //if the button is clicked it allows the user to edit the contents of the boxes or stops them from editing if they click save
                if (save_btn.getText().equals("Save")){
                    //save changes to file

                    //stops user from editing again
                    name_txt.setEditable(false);
                    desc_text.setEditable(false);
                    date_txt.setEditable(false);
                    points_txt.setEditable(false);

                    project_cb.removeAllItems();
                    team_cb.removeAllItems();

                    save_btn.setText("Edit");
                }
                else{
                    //reading project text file
                    List<String> projectsList = new ArrayList<String>();
                    projectsList =DataClassKt.readText("projects");
                    int projectSize = projectsList.size();

                    //filling combobox
                    for(int x = 0;x<projectSize;x++)
                    {
                        String element = projectsList.get(x);
                        String[]  array = element.split(",");
                        project_cb.addItem(array[1]);
                    }

                    //reading team text file
                    List<String> teamList = new ArrayList<String>();
                    teamList =DataClassKt.readText("teams");
                    int teamSize = teamList.size();

                    //filling combobox
                    for(int x = 0;x<teamSize;x++)
                    {
                        String element = teamList.get(x);
                        String[]  array = element.split(",");
                        team_cb.addItem(array[1]);
                    }

                    //allows user to edit
                    name_txt.setEditable(true);
                    team_cb.setEditable(true);
                    desc_text.setEditable(true);
                    project_cb.setEditable(true);
                    date_txt.setEditable(true);
                    points_txt.setEditable(true);
                    save_btn.setText("Save");
                }
            }
        });
        progress_CB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int progress = Integer.parseInt(progress_CB.getSelectedItem().toString());

                progressBar1.setValue(progress) ;
            }
        });
    }

    public void view(String taskName){
        List<String> taskList = new ArrayList<String>();
        taskList =DataClassKt.readText("tasks");
        int taskSize = taskList.size();
        String[]  taskArray = new String[7];
        for(int x = 0;x<taskSize;x++)
        {
            String element = taskList.get(x);
            taskArray =  element.split(",");
            if (taskArray[1].equals(taskName)){
                break;
            }
        }

        name_txt.setText(taskArray[1]);
        desc_text.setText(taskArray[5]);
        date_txt.setText(taskArray[4]);
        points_txt.setText(taskArray[6]);

        List<String> projectsList = new ArrayList<String>();
        projectsList =DataClassKt.readText("projects");
        int projectSize = projectsList.size();
        String projectName="";

        List<String> teamList = new ArrayList<String>();
        teamList =DataClassKt.readText("teams");
        int teamSize = teamList.size();
        String teamName="";

        for(int x = 0;x<teamSize;x++)
        {
            String element = teamList.get(x);
            String[]  array = element.split(",");

            if (array[0].equals(taskArray[2].trim())){
                teamName=array[1];
                break;
            }
        }
        for(int x = 0;x<projectSize;x++)
        {
            String element = projectsList.get(x);
            String[]  array = element.split(",");
            if (array[0].equals(taskArray[3].trim())){
                projectName=array[1];
                break;
            }
        }

        for (int x=0; x<110;){

            progress_CB.addItem(x);
            x +=10;

        }

        projectName_lbl.setText(projectName);
        teamName_lbl.setText(teamName);

        project_cb.addItem(projectName);
        team_cb.addItem(teamName);

    }
}
