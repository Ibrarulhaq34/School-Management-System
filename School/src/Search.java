import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

public class Search extends JFrame{
    private JPanel seachPanel;
    private JTextField searchField;
    private JList searchResult;
    private JButton searchButton;
    private JLabel searchLabel;
    private JComboBox classSelection;
    private JButton backButton;

    public Search() {

        setContentPane(seachPanel);
        setTitle("Search");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        classSelection.addItem("Class 1");
        classSelection.addItem("Class 2");
        classSelection.addItem("Class 3");
        classSelection.addItem("Class 4");
        classSelection.addItem("Class 5");
        classSelection.addItem("Class 6");
        classSelection.addItem("Class 7");
        classSelection.addItem("Class 8");


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rollNo = searchField.getText();
                String classNumber = classSelection.getSelectedItem().toString();
                String fileName = classNumber.charAt(classNumber.length() - 1) + ".txt";
                if (AdmissionForm.isNumeric(rollNo)){
                    String[] record = search(fileName, rollNo);
                    searchResult.setModel(new DefaultComboBoxModel(record));
                }else {
                    JOptionPane.showMessageDialog(null, "Invalid Roll Number");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

    public static String[] search(String fileName, String rollNo){
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                String[] record = reader.nextLine().split(";");

                if (record[0].equals(rollNo)){
                    String[] recordView = {"Roll No: "+record[0], "Name: "+record[1], "Father Name: "+record[2]
                            , "Phone Number: "+record[3], "Date of Birth: "+record[4], "Address: "+record[5]
                            , "Previous School: "+record[6], "CNIC/Form-B: "+record[7], "Fee: "+record[8],
                            "Gender: "+record[9]};
                    return recordView;
                }else {
                    JOptionPane.showMessageDialog(null, "Roll Number NOT Found");
                }
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        String[] recordView = {};
        return recordView;
    }
}
