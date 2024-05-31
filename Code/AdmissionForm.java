

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

/**
 *This class add student record. Gets input data from admin and validates entered values.
 *This class is child class of JFrame class.
 **/
public class AdmissionForm extends JFrame{
    //Declaring all GUI components
    private JTextField nameField;
    private JTextField fatherField;
    private JTextField contactNoField;
    private JTextField dobField;
    private JTextField addressField;
    private JTextField classField;
    private JTextField instititeField;
    private JTextField cnicField;
    private JTextField feeField;
    private JButton submitButton;
    private JRadioButton male;
    private JRadioButton female;
    private JLabel name;
    private JLabel fatherName;
    private JLabel contactNo;
    private JLabel dateOfBirth;
    private JLabel address;
    private JLabel classNumber;
    private JLabel preInstitute;
    private JLabel gender;
    private JLabel cnic;
    private JLabel fee;
    private JPanel admissionForm;

    //Declaring class variables
    boolean maleVar = false, femaleVar = false;
    String nameEntered, fatherNameEntered, dateOfBirthEntered, addressEntered, classNumberEntered, preInstituteEntered;
    String genderEntered, cnicEntered, feeEntered, contactNoEntered;

    //Constructor of class AdmissionForm
    public AdmissionForm() {

        //Submit button listener
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Getting text from text fields and validating entries
                if (maleVar && femaleVar){
                    JOptionPane.showMessageDialog(null, "Incorrect Gender Selection");

                }else if (maleVar){
                    genderEntered = "male";
                    nameEntered = nameField.getText().trim();
                    fatherNameEntered = fatherField.getText().trim();
                    dateOfBirthEntered = dobField.getText().trim();
                    addressEntered = addressField.getText().trim();
                    classNumberEntered = classField.getText().trim();
                    preInstituteEntered = instititeField.getText().trim();
                    cnicEntered = cnicField.getText().trim();
                    contactNoEntered = contactNoField.getText().trim();
                    feeEntered = feeField.getText().trim();

                }else if (femaleVar){
                    genderEntered = "female";
                    nameEntered = nameField.getText().trim();
                    fatherNameEntered = fatherField.getText().trim();
                    dateOfBirthEntered = dobField.getText().trim();
                    addressEntered = addressField.getText().trim();
                    classNumberEntered = classField.getText().trim();
                    preInstituteEntered = instititeField.getText().trim();
                    cnicEntered = cnicField.getText().trim();
                    contactNoEntered = contactNoField.getText().trim();
                    feeEntered = feeField.getText().trim();

                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Gender Selection");
                }

                if (nameEntered.equals("") || fatherNameEntered.equals("") || dateOfBirthEntered.equals("") ||
                        addressEntered.equals("") || classNumberEntered.equals("") || preInstituteEntered.equals("") ||
                        genderEntered.equals("") || cnicEntered.equals("") || contactNoEntered.equals("")
                        || feeEntered.equals("")){

                    JOptionPane.showMessageDialog(null, "Fill all Fields");

                }

                if (!(nameEntered.equals("") || fatherNameEntered.equals("") || dateOfBirthEntered.equals("") ||
                        addressEntered.equals("") || classNumberEntered.equals("") || preInstituteEntered.equals("") ||
                        genderEntered.equals("") || cnicEntered.equals("")|| contactNoEntered.equals("")
                        || feeEntered.equals(""))){

                    if (!(isNumeric(contactNoEntered) && isNumeric(cnicEntered) && isNumeric(classNumberEntered)
                        && isNumeric(feeEntered)) || Long.parseLong(cnicEntered)<1000000000000l || Integer.parseInt(classNumberEntered)>9
                        || Integer.parseInt(classNumberEntered)<1){
                        JOptionPane.showMessageDialog(null, "Invalid CNIC or Phone Number");

                    }else{
                        //Storing data in text file
                        try {
                            String rollNumber = rollNumberGen();
                            File classFile = new File(classNumberEntered+".txt");
                            classFile.createNewFile();

                            FileWriter fileWriter = new FileWriter(classFile, true);
                            String content1 = rollNumber+";"+ nameEntered+";"+fatherNameEntered+";"+contactNoEntered+";"+dateOfBirthEntered+";"+addressEntered+";";
                            String content2 = preInstituteEntered+";"+cnicEntered+";"+feeEntered+";"+genderEntered + "\n";
                            fileWriter.write(content1+content2);
                            fileWriter.close();
                        }catch (IOException ex){
                            ex.printStackTrace();
                        }
                        AdminMenu adminMenu = new AdminMenu();
                        setVisible(false);
                        dispose();
                    }

                }

            }
        });//End of submit button listener


        //Windows frame
        setContentPane(admissionForm);
        setTitle("Admission Form");
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        //Check button listeners
        male.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maleVar = male.isSelected();
            }
        });
        female.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                femaleVar = female.isSelected();
            }
        });
    }

    //Check a String is numeric
    public static boolean isNumeric(String number){
        if (number.equals("")){
            return false;
        }

        try {
            Long.parseLong(number);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }

    }

    //Generates roll no by adding 1 to the roll number of latest student entered
    public static String rollNumberGen(){
        int arrSize = 0;
        try {
            File rollNumberFile = new File("Roll Numbers.txt");
            rollNumberFile.createNewFile();
            Scanner reader = new Scanner(rollNumberFile);
            while (reader.hasNextLine()){
                arrSize = Integer.parseInt(reader.nextLine()) + 1;
            }

            reader.close();

            FileWriter fileW = new FileWriter(rollNumberFile, true);
            fileW.write("\n"+arrSize);
            fileW.close();

        }catch(IOException e){
            e.printStackTrace();
        }

        return arrSize + "";
    }


}
