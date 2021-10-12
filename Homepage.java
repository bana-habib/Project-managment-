import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame{


    private JButton projects_btn;
    private JButton teams_btn;
    private JPanel home_pnl;



    public static void main(String[] args) {

    Homepage start = new Homepage();

    }

    public Homepage() {

        model();

        //projects button that opens Projects
        projects_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Projects newProjects = new Projects();
                dispose();

            }
        });
        // Teams button that opens Teams
        teams_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Teams newTeams = new Teams();
                dispose();
            }
        });


    }
    public void model(){
        //fills content on home page
        setContentPane(home_pnl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }


}
