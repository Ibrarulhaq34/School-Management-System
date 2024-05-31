

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 *This class is Student menu. Have all button and their listeners which invokes all its functionalities.
 *This class is child class of JFrame class.
 **/
public class MainMenu extends JFrame{
    //Declaring all GUI components
    private JPanel studentMenu;
    private JButton attendanceButton;
    private JButton resultButton;
    private JButton signoutButton;
    private JPanel menuPanel;
    private JPanel photoPanel;
    private JLabel photoLabel;
    private JTextPane quoteText;
    private JTextField rollNoField;
    private JLabel rollNoLabel;

    //Declaring class variables
    String rollNo;

    //Constructor of class MainMenu
    public MainMenu() {

        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollNo = rollNoField.getText();
                if (AdmissionForm.isNumeric(rollNo)){
                    AttendanceView attendanceView = new AttendanceView();
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid Roll Number");
                }
            }
        });

        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollNo = rollNoField.getText();
                if (AdmissionForm.isNumeric(rollNo)){
                    try {
                        String fileName = "Roll No "+rollNo+" Result.pdf";
                        File file = new File(fileName);
                        if (file.exists()){
                            if (Desktop.isDesktopSupported()){
                                Desktop.getDesktop().open(file);
                            }else {
                                JOptionPane.showMessageDialog(null, "Result Not Found");
                            }
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid Roll Number");
                }
            }
        });

        signoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login loginFrame = new Login();
                setVisible(false);
                dispose();
            }
        });

        setContentPane(studentMenu);
        setTitle("Student Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }

}
