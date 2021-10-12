import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Projects extends JFrame{
    private JComboBox projects_cb;
    private JButton newP_btn;
    private JButton openP_btn;
    private JPanel projectTasks_btn;
    private JButton home_btn;

    public Projects() {
        start();
        //creates home button that takes user to home page
        home_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage start = new Homepage();
                setVisible(false);
                dispose();
            }
        });
        // creates New Projects button
        newP_btn.addActionListener(new ActionListener() {
            @Override
            //opens the NewProject page
            public void actionPerformed(ActionEvent e) {
                NewProject start = new NewProject();
                setVisible(false);
                dispose();
            }
        });
        // creates the open projects button
        openP_btn.addActionListener(new ActionListener() {
            @Override
            //takes the user to Tasksbyproject page
            public void actionPerformed(ActionEvent e) {
                String selected = projects_cb.getSelectedItem().toString();
                TasksbyProject start = new TasksbyProject(selected);
                setVisible(false);
                dispose();
            }
        });
    }
    public void start(){
    // reads projects file
        List<String> projectsList = new ArrayList<String>();
        projectsList =DataClassKt.readText("projects");
        int size = projectsList.size();

        List<String> taskList = new ArrayList<String>();
    //fills combo box
        for(int x = 0;x<size;x++)
        {
            String element = projectsList.get(x);
            String[]  array = element.split(",");
            projects_cb.addItem(array[1]);

        }
        //fills content on projects page

        setContentPane(projectTasks_btn);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }

}
