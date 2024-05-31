
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *This class is admin menu. Have all button and their listeners which invokes all its functionalities.
 *This class is child class of JFrame class.
 **/
public class AdminMenu extends JFrame{
    //Declaring all GUI components
    private JPanel adminMenuSub;
    private JPanel menuPanel;
    private JButton attendanceButton;
    private JButton signoutButton;
    private JPanel photoPanel;
    private JLabel photoLabel;
    private JTextPane quoteText;
    private JButton feeChalanButton;
    private JButton admissionButton;
    private JButton searchButton;
    private JPanel adminMenu;
    private JButton manage;
    
    //Constructor of class Admin menu
    public AdminMenu() {
        admissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdmissionForm admissionForm = new AdmissionForm();
                setVisible(false);
                dispose();
            }
        });
        feeChalanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fee fee = new Fee();
            }
        });
        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Atttendance attendanceMenu = new Atttendance();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search search = new Search();
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
        manage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manage manage = new Manage();
            }
        });

        //Setting up main window
        setContentPane(adminMenu);
        setTitle("Admin Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

}
