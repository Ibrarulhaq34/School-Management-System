import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.spec.ECField;
import java.util.Scanner;

/**
 *This class allow student view attendance.
 *This class is child class of JFrame class.
 **/
public class AttendanceView extends JFrame {
    //Declaring all GUI components
    private JPanel attendanceView;
    private JList attendanceList;
    private JButton backButton;
    private JButton showButton;
    private JTextField rollNoField;
    private JLabel rollNoLabel;
    private JComboBox classSelection;

    //Constructor of class AttendanceView
    public AttendanceView() {

        //Adding components to ComboBox
        classSelection.addItem("1");
        classSelection.addItem("2");
        classSelection.addItem("3");
        classSelection.addItem("4");
        classSelection.addItem("5");
        classSelection.addItem("6");
        classSelection.addItem("7");
        classSelection.addItem("8");

        //Windows frame
        setContentPane(attendanceView);
        setTitle("Attendance");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });


        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Getting data from text file and showing it in List
                String className = classSelection.getSelectedItem().toString();
                String rollNo = rollNoField.getText();
                String fileName = "Attendance "+className+".txt";
                String[] records = new String[50];
                try {
                    File file = new File(fileName);
                    Scanner reader = new Scanner(file);
                    int count = 0;
                    while (reader.hasNextLine()){
                        String[] temp = reader.nextLine().split(":");
                        if (!AdmissionForm.isNumeric(temp[0])){
                            records[count] = temp[0];
                        }
                        if (temp[0].equals(rollNo)){
                            records[count] = temp[0]+": "+temp[1]+": "+temp[2];
                        }
                        count++;
                    }
                    reader.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                }

                attendanceList.setModel(new DefaultComboBoxModel(records));
            }
        });
    }
}
