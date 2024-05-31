import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Manage extends JFrame {
    private JPanel managePanel;
    private JTextField nameField;
    private JComboBox classSelection;
    private JTextField subjectField;
    private JTextField rollNoField;
    private JButton addTeacherButton;
    private JList studentList;
    private JButton searchButton;
    private JLabel titleLabel;
    private JLabel addLabel;
    private JLabel nameLabel;
    private JLabel subjectLabel;
    private JLabel searchLabel;
    private JButton updateRecordButton;
    private JLabel classLabel;
    private JLabel rollNoLabel;
    private JButton backButton;

    public Manage() {

        setContentPane(managePanel);
        setTitle("Management");
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

        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerFun(nameField.getText(), subjectField.getText());
                JOptionPane.showMessageDialog(null, "Successfully Registered");
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = (String) classSelection.getSelectedItem();
                String rollNo = rollNoField.getText();
                if (!value.equals("") && AdmissionForm.isNumeric(rollNo)) {
                    String fileName = value.charAt(value.length() - 1) + ".txt";
                    String[] recordView = search(fileName, rollNo);
                    studentList.setModel(new DefaultComboBoxModel(recordView));
                }else {
                    JOptionPane.showMessageDialog(null, "Please Select a Class or Enter Roll No");
                }
            }
        });
        updateRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String record = studentList.getSelectedValue().toString();

                if (!record.equals("")) {
                    String[] options = {"Update", "Delete"};

                    int opt = JOptionPane.showOptionDialog(null, "You want to update or delete record",
                            "Select Option", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                            options, options[0]);

                    String value = (String) classSelection.getSelectedItem();
                    String rollNo = rollNoField.getText();
                    String fileName="";
                    if (!value.equals("") && AdmissionForm.isNumeric(rollNo)) {
                        fileName = value.charAt(value.length() - 1) + ".txt";
                        String[] recordView = search(fileName, rollNo);
                        studentList.setModel(new DefaultComboBoxModel(recordView));
                    }else {
                        JOptionPane.showMessageDialog(null, "Please Select a Class or Enter Roll No");
                    }

                    if (opt==0){
                        updateFun(fileName, rollNo);
                    }else {
                        deleteFun(fileName, rollNo);
                        JOptionPane.showMessageDialog(null, "Record Successfully Deleted");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Please Select a Record");
                }
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMenu adminMenu = new AdminMenu();
                setVisible(false);
                dispose();
            }
        });
    }

    public static void registerFun(String nameGet, String subjectGet){
        try {
            File file = new File("Teacher Login.txt");
            file.createNewFile();

            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(nameGet+";"+subjectGet+"\n");
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String[] search(String fileName, String rollNo){
       try {
           File file = new File(fileName);
           Scanner reader = new Scanner(file);
           while (reader.hasNextLine()){
               String[] record = reader.nextLine().split(";");

               if (record[0].equals(rollNo)){
                   String[] recordView = {record[0]+": "+record[1]+"   Fee: "+record[8]};
                   return recordView;
               }
           }
           reader.close();
       }catch (Exception e){
           e.printStackTrace();
       }
       String[] recordView = {};
       return recordView;
    }

    public static void updateFun(String fileName, String rollNo){
        String newFee = JOptionPane.showInputDialog("Enter new Fee");
        if (AdmissionForm.isNumeric(newFee)) {
            try {
                File file = new File(fileName);
                File tempFile = new File("temp.txt");
                Scanner reader = new Scanner(file);
                FileWriter fileWriter = new FileWriter(tempFile, true);

                while (reader.hasNextLine()) {
                    String[] str = reader.nextLine().split(";");
                    if (str[0].equals(rollNo)) {
                        fileWriter.write(str[0] + ";" + str[1] + ";" + str[2] + ";" + str[3] + ";" + str[4] + ";" + str[5] + ";" + str[6] + ";" +
                                str[7] + ";" + newFee + ";" + str[9] + "\n");
                    }else {
                        fileWriter.write(str[0] + ";" + str[1] + ";" + str[2] + ";" + str[3] + ";" + str[4] + ";" + str[5] + ";" + str[6] + ";" +
                                str[7] + ";" + str[8]+ ";" + str[9] + "\n");
                    }
                }
                reader.close();
                fileWriter.close();
                FileWriter fileWriter1 = new FileWriter(file);
                fileWriter1.write("");
                fileWriter1.close();

                Scanner reader1 = new Scanner(tempFile);
                FileWriter fileWriter2 = new FileWriter(file, true);
                while (reader1.hasNextLine()){
                    String[] str = reader1.nextLine().split(";");
                    fileWriter2.write(str[0] + ";" + str[1] + ";" + str[2] + ";" + str[3] + ";" + str[4] + ";" + str[5] + ";" + str[6] + ";" +
                            str[7] + ";" + str[8]+ ";" + str[9] + "\n");
                }
                reader1.close();
                fileWriter2.close();
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Fee SuccessFully Updated");
        }else {
            JOptionPane.showMessageDialog(null, "Unknown Input");
        }

    }

    public static void deleteFun(String fileName, String rollNo){
        try {
            File file = new File(fileName);
            File tempFile = new File("temp.txt");
            Scanner reader = new Scanner(file);
            FileWriter fileWriter = new FileWriter(tempFile, true);

            while (reader.hasNextLine()){
                String[] str = reader.nextLine().split(";");
                if (!str[0].equals(rollNo)){
                    fileWriter.write(str[0]+";"+str[1]+";"+str[2]+";"+str[3]+";"+str[4]+";"+str[5]+";"+str[6]+";"+
                            str[7]+";"+str[8]+";"+str[9]+"\n");
                }
            }
            reader.close();
            fileWriter.close();

            Scanner reader1 = new Scanner(tempFile);
            FileWriter fileWriter2 = new FileWriter(file, true);
            while (reader1.hasNextLine()){
                String[] str = reader1.nextLine().split(";");
                fileWriter2.write(str[0] + ";" + str[1] + ";" + str[2] + ";" + str[3] + ";" + str[4] + ";" + str[5] + ";" + str[6] + ";" +
                        str[7] + ";" + str[8]+ ";" + str[9] + "\n");
            }
            reader1.close();
            fileWriter2.close();
            tempFile.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
