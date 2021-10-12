import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Teams extends JFrame{
    private JComboBox teams_cb;
    private JButton viewT_btn;
    private JButton newT_btn;
    private JTextField newT_txt;
    private JPanel teams_pnl;
    private JButton home_btn;

    public static void main(String[] args) {

        Teams start = new Teams();

    }
    public Teams(){
        model();
        view();
        // home button that takes user to HomePage
        home_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Homepage start = new Homepage();
                setVisible(false);
            }
        });
        // creates view teams buttons
        viewT_btn.addActionListener(new ActionListener() {
            @Override
            // opens tasks for team selected in combo box
            public void actionPerformed(ActionEvent e) {
                String selected = teams_cb.getSelectedItem().toString();
                TasksbyTeam openTeam = new TasksbyTeam(selected);
                setVisible(false);
                dispose();
            }
        });
        // creates new button
        newT_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // if the text field is empty when button is pressed a pop up appears
                if (newT_txt.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please make sure all fields have been completed.");
                }
                else{
                    // text field is filled then it adds team name to Combo box
                    DataClassKt.addVariableText("teams", newT_txt.getText());
                    teams_cb.addItem(newT_txt.getText());
                    newT_txt.setText("");
                }

            }
        });
    }
    //fills content on Teams page
    public void model (){
        setContentPane(teams_pnl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void view() {

        // reads teams file
        List<String> teamList = new ArrayList<String>();

            teamList =DataClassKt.readText("teams");
            int size = teamList.size();

        for(int x = 0;x<size;x++)
        {//fills combo box
            String element = teamList.get(x);
            String[]  array = element.split(",");
            teams_cb.addItem(array[1]);
            }



    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
