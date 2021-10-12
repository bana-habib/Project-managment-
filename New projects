mport javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class NewProject extends JFrame{
    //declares all GUI elements
    private JTextField name_txt;
    private JButton save_btn;
    private JPanel newProject_pnl;
    private JButton cancel_btn;
    private JTextArea desc_textArea;
    private JFormattedTextField duration_txt;

    public NewProject(){

        view();

        //add actions to button
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //takes user back to previous page
                Projects newProjects = new Projects();
                setVisible(false);
                dispose();
            }
        });
        save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //validation to stop user from being able to add anything blank to the database
                if (name_txt.getText().equals("")||duration_txt.getText().equals("")||desc_textArea.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please make sure all fields have been completed.");
                }
                else {
                    //takes all data from GUI and adds to list which is then passed to the dataclass to be added to the textfile database
                    List<String> dataList = new ArrayList<String>();
                    dataList.add(name_txt.getText());
                    dataList.add(duration_txt.getText());
                    dataList.add(desc_textArea.getText());
                    DataClassKt.addListText("projects", dataList);
                    setVisible(false);
                    Projects newProjects = new Projects();
                    dispose();
                }

            }
        });
    }


    public void view(){

        //fills content pannel and makes visible to
        setContentPane(newProject_pnl);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
