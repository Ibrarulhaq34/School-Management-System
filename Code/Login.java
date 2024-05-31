
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *This class allow students, teachers and admin to login. Students can signup also.
 *This class is child class of JFrame class.
 **/
public class Login extends JFrame{
    private JLabel name;
    private JLabel password;
    private JTextField nameField;
    private JTextField passwordField;
    private JButton signupButton;
    private JButton loginButton;
    private JPanel loginPanel;
    private JRadioButton teacherSelection;
    private JRadioButton adminSection;
    private JRadioButton studentSelection;

    //Declaring class variables
    boolean studentSelectionRadio = false, teacherSelectionRadio = false, adminSelectionRadio = false;
    public String nameText, passwordText, content;

    //Constructor of class Login
    public Login() {

        //Validate credentials and log user in to his account
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameText = nameField.getText();
                passwordText = passwordField.getText();

                if ((studentSelectionRadio && teacherSelectionRadio && adminSelectionRadio) || (!studentSelectionRadio && !teacherSelectionRadio && !adminSelectionRadio) ||
                        (studentSelectionRadio && teacherSelectionRadio) || (teacherSelectionRadio && adminSelectionRadio) || (studentSelectionRadio && adminSelectionRadio)){
                    JOptionPane.showMessageDialog(null, "Incorrect Option Selection");
                }else if (adminSelectionRadio){
                    if (adminVer(nameText, passwordText)){
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        AdminMenu adminMenu = new AdminMenu();
                        setVisible(false);
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "Wrong Username or Password");
                    }
                }else if (teacherSelectionRadio){
                    if (teacherVer(nameText, passwordText)){
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        TeacherMenu teacherMenu = new TeacherMenu();
                        setVisible(false);
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "Wrong Username or Password");
                    }
                }else{
                    if (studentVer(nameText, passwordText)){
                        JOptionPane.showMessageDialog(null, "Login Successful");
                        MainMenu mainMenu = new MainMenu();
                        setVisible(false);
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "Wrong Username or Password");
                    }
                }


            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignupForm signupForm = new SignupForm();
                setVisible(false);
                dispose();
            }
        });

        //Setting up main window
        setContentPane(loginPanel);
        setTitle("Login");
        setSize(800, 350);
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);


        adminSection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSelectionRadio = adminSection.isSelected();
            }
        });
        teacherSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherSelectionRadio = teacherSelection.isSelected();
            }
        });
        studentSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentSelectionRadio = studentSelection.isSelected();
            }
        });
    }

    //main method where program starts execution
    public static void main(String[] args){
        Login loginFrame = new Login();
    }

    //Verify student credentials
    public static boolean studentVer(String name, String password){
        try {
            File stdLoginFile = new File("Student Login.txt");
            Scanner reader = new Scanner(stdLoginFile);

            while (reader.hasNextLine()) {
                String[] temp = reader.nextLine().split(";");
                if (temp[0].equalsIgnoreCase(name) && temp[1].equals(password)){
                    return true;
                }
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    //Verify student credentials
    public static boolean adminVer(String name, String password){
        try {
            File adminLoginFile = new File("Admin Login.txt");
            if (adminLoginFile.createNewFile()){
                FileWriter fileW = new FileWriter(adminLoginFile);
                fileW.write("admin;admin123");
                fileW.close();
            }

            Scanner reader = new Scanner(adminLoginFile);
            while (reader.hasNextLine()) {
                String[] temp = reader.nextLine().split(";");
                if (temp[0].equalsIgnoreCase(name) && temp[1].equals(password)){
                    return true;
                }
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    //Verify student credentials
    public static boolean teacherVer(String name, String password){
        try {
            File stdLoginFile = new File("Teacher Login.txt");
            Scanner reader = new Scanner(stdLoginFile);

            while (reader.hasNextLine()) {
                String[] temp = reader.nextLine().split(";");
                if (temp[0].equalsIgnoreCase(name) && temp[1].equals(password)){
                    return true;
                }
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
            return false;
    }
}
