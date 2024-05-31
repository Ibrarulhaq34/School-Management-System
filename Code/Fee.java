

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

/**
 *This class generate fee chalan in PDF format.
 *This class is child class of JFrame class.
 **/
public class Fee extends JFrame {
    //Declaring all GUI components
    private JList studentList;
    private JComboBox classSelect;
    private JComboBox monthSelect;
    private JButton selectButton;
    private JButton backButton;
    private JButton generateButton;
    private JTextField dueDateField;
    private JLabel feeTitle;
    private JLabel dueDateLabel;
    private JLabel month;
    private JLabel classLabel;
    private JPanel feePanel;

    //Declaring class variables
    String monthSelected, classSelected, dueDate;
    boolean validator = false;

    //Constructor of class Fee
    public Fee() {

        //Windows frame
        setContentPane(feePanel);
        setTitle("Fee");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        //Adding components to ComboBox
        classSelect.addItem("1");
        classSelect.addItem("2");
        classSelect.addItem("3");
        classSelect.addItem("4");
        classSelect.addItem("5");
        classSelect.addItem("6");
        classSelect.addItem("7");
        classSelect.addItem("8");

        //Adding components to ComboBox
        monthSelect.addItem("1");
        monthSelect.addItem("2");
        monthSelect.addItem("3");
        monthSelect.addItem("4");
        monthSelect.addItem("5");
        monthSelect.addItem("6");
        monthSelect.addItem("7");
        monthSelect.addItem("8");
        monthSelect.addItem("9");
        monthSelect.addItem("10");
        monthSelect.addItem("11");
        monthSelect.addItem("12");

        //By clicking get fee month and class, then add students selected class to list
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                monthSelected = (String) monthSelect.getSelectedItem();
                classSelected = (String) classSelect.getSelectedItem();
                if (monthSelected.equals("") || classSelected.equals("")){
                    JOptionPane.showMessageDialog(null, "Please Select Class and Month");
                }else {
                    String fileName = classSelected + ".txt";
                    String[] names = studentNameList(fileName);
                    studentList.setModel(new DefaultComboBoxModel(names));

                    validator = true;
                }
            }
        });

        //Get selected student from list, gets its fee from record and generates chalan
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dueDateField.getText().equals("") || !validator){
                    JOptionPane.showMessageDialog(null, "Please Enter Due Date");
                }else {
                    dueDate = dueDateField.getText();
                    String fileName = classSelected + ".txt";
                    String[] names = studentNameList(fileName);

                    String[] rollNoTemp = studentList.getSelectedValue().toString().split(":");
                    String feeOri = feeGet(fileName, rollNoTemp[0]);
                    String[] pdfInput = {rollNoTemp[0], rollNoTemp[1], feeOri, dueDate, classSelected, monthSelected};
                    generatePdf(pdfInput);
                    JOptionPane.showMessageDialog(null, "Fee Challan Successfully Generated");

                }
            }
        });

        ////By clicking goes back to Admin Menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMenu adminMenu = new AdminMenu();
                setVisible(false);
                dispose();
            }
        });
    }

    //Get fee of student from saved record
    public static String feeGet(String fileName, String rollNumber){
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                String[] temp = reader.nextLine().split(";");
                if (temp[0].equals(rollNumber)){
                    return temp[8];
                }
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return "";
    }

    //Get students of a class and stores them in an array
    public static String[] studentNameList(String fileName){
        int totalLines = lineCounter(fileName);
        int startIndex, endIndex;
        String[] records = new String[totalLines];
        String[] names = new String[totalLines];
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            for (int i=0; i<records.length; i++){
                records[i] = reader.nextLine();
            }
            reader.close();

            for (int i=0; i< names.length; i++){
                startIndex = indexOf(records[i], ';', 0);
                endIndex = indexOf(records[i], ';', startIndex+1);
                String name = records[i].substring(startIndex+1, endIndex);
                String rollNo = records[i].substring(0, startIndex);
                names[i] = rollNo + ": " + name;
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        return names;
    }

    //Counts number of lines in an array
    public static int lineCounter(String fileName){
        int counter = 0;
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                reader.nextLine();
                counter++;
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return counter;
    }

    //Gets index of a provided character
    public static int indexOf(String str, char c, int start){
        int i;
        for (i=start; i<str.length(); i++){
            if (str.charAt(i) == c){
                break;
            }
        }
        return i;
    }

    //Generate a fee chalan in PDF format
    public static void generatePdf(String[] details) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Roll No "+details[0]+" Fee.pdf"));
            document.open();
            document.add(new Paragraph("Fee Challan", FontFactory.getFont(FontFactory.TIMES_BOLD, 28, Font.BOLD, BaseColor.RED)));
            document.add(new Paragraph(new Date().toString()));
            document.add(new Paragraph("-------------------------------------------------------------------------------------"));
            document.add(new Paragraph("Ali Public School", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.RED)));
            document.add(new Paragraph("-------------------------------------------------------------------------------------"));
            document.add(new Paragraph("Roll No: "+details[0], FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
            document.add(new Paragraph("Name: "+details[1], FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
            document.add(new Paragraph("Class: "+details[4], FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
            document.add(new Paragraph("Fee Month: "+monthGet(details[5]), FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
            document.add(new Paragraph("--------------------------------------------------------------------------------------"));
            document.add(new Paragraph("--------------------------------------------------------------------------------------"));
            document.add(new Paragraph("--------------------------------------------------------------------------------------"));
            PdfPTable table = new PdfPTable(2);
            table.addCell("Fee");
            table.addCell(details[2]);
            table.addCell("Due Date");
            table.addCell(details[3]);
            document.add(table);

            document.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //Get month name to corresponding month number
    public static String monthGet(String monthName){
        switch (monthName){
            case "1":return "January";
            case "2":return "February";
            case "3":return "March";
            case "4":return "April";
            case "5":return "May";
            case "6":return "June";
            case "7":return "July";
            case "8":return "August";
            case "9":return "September";
            case "10":return "October";
            case "11":return "November";
            case "12":return "December";
        }
        return "";
    }

}
