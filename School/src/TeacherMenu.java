
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMenu extends JFrame{
    private JPanel teacherMenuSub;
    private JPanel menuPanel;
    private JButton attendanceButton;
    private JButton signoutButton;
    private JButton resultButton;
    private JPanel photoPanel;
    private JLabel photoLabel;
    private JTextPane quoteText;
    private JPanel teacherMenu;

    public TeacherMenu() {

        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Atttendance attendanceMenu = new Atttendance();
            }
        });
        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Result result = new Result();
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

        setContentPane(teacherMenu);
        setTitle("Teacher Menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

}
