
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SignupForm extends JFrame{
    private JPanel signupForm;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField rollNoField;
    private JButton registerButton;
    private JLabel username;
    private JLabel password;
    private JLabel rollNumber;

    String usernameEntered, passwordEntered, rollNumberEntered, content;

    public SignupForm() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameEntered = usernameField.getText().trim();
                passwordEntered = passwordField.getText().trim();
                rollNumberEntered = rollNoField.getText().trim();
                content = usernameEntered + ";" + passwordEntered;

                signupValidation(content, rollNumberEntered);

                JOptionPane.showMessageDialog(null, "Successfully Registered");
                Login loginFrame = new Login();
                setVisible(false);
                dispose();
            }
        });


        setContentPane(signupForm);
        setTitle("Signup");
        setSize(550, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void signupValidation(String content, String rollNo){
        try {
            File loginFile = new File("Student Login.txt");
            loginFile.createNewFile();

            Scanner reader = new Scanner(loginFile);
            while (reader.hasNextLine()){
                if (reader.nextLine().equals(content)){
                    JOptionPane.showMessageDialog(null, "User Already Exists");
                    return;
                }
            }
            reader.close();
            File rollNoFile = new File("Student Login.txt");
            Scanner reader1 = new Scanner(rollNoFile);
            while (reader1.hasNextLine()){
                if (reader1.nextLine().equals(rollNo)){
                    JOptionPane.showMessageDialog(null, "Only Students can Register");
                    return;
                }
            }
            reader1.close();

            FileWriter fileW = new FileWriter(loginFile, true);
            fileW.write(content+"\n");
            fileW.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
