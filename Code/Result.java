import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Scanner;

/**
 *This class allow teachers to generate students result card in pdf format.
 *This class is child class of JFrame class.
 **/
public class Result extends JFrame {
    //Declaring all GUI components
    private JPanel resultPanel;
    private JLabel titleLabel;
    private JTextField rollNoField;
    private JTextField englishMarksObt;
    private JTextField urduMarksObt;
    private JTextField mathMarksObt;
    private JTextField scienceMarksObt;
    private JTextField computerMarksObt;
    private JTextField islamiatMarksObt;
    private JTextField englishTotal;
    private JTextField urduTotal;
    private JTextField mathTotal;
    private JTextField scienceTotal;
    private JTextField computerTotal;
    private JTextField islamiatTotal;
    private JTextField socialMarksObt;
    private JTextField socialTotal;
    private JComboBox classSelection;
    private JLabel rollNoLabel;
    private JLabel marksObtainedLabel;
    private JLabel totalMarksLabel;
    private JLabel englishLabel;
    private JLabel urduLabel;
    private JLabel mathLabel;
    private JLabel scienceLabel;
    private JLabel computerLabel;
    private JLabel islamiatLabel;
    private JLabel socialLabel;
    private JButton generateButton;
    private JButton backButton;

    //Declaring class variables
    String rollNoGet, classNumber, studentName;
    String englishTotalGet, urduTotalGet,mathTotalGet,scienceTotalGet,computerTotalGet,islamiatTotalGet,socialTotalGet;
    String englishObtGet, urduObtGet,mathObtGet,scienceObtGet,computerObtGet,islamiatObtGet,socialObtGet;

    //Constructor of class Result
    public Result() {

        //Setting up main window
        setContentPane(resultPanel);
        setTitle("Result");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        //Adding components to ComboBox
        classSelection.addItem("Class 1");
        classSelection.addItem("Class 2");
        classSelection.addItem("Class 3");
        classSelection.addItem("Class 4");
        classSelection.addItem("Class 5");
        classSelection.addItem("Class 6");
        classSelection.addItem("Class 7");
        classSelection.addItem("Class 8");

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollNoGet = rollNoField.getText();
                classNumber = (String) classSelection.getSelectedItem().toString();
                classNumber = classNumber.charAt(classNumber.length()-1)+"";

                englishObtGet = englishMarksObt.getText();
                urduObtGet = urduMarksObt.getText();
                mathObtGet = mathMarksObt.getText();
                scienceObtGet = scienceMarksObt.getText();
                computerObtGet = computerMarksObt.getText();
                islamiatObtGet = islamiatMarksObt.getText();
                socialObtGet = socialMarksObt.getText();

                englishTotalGet = englishTotal.getText();
                urduTotalGet = urduTotal.getText();
                mathTotalGet = mathTotal.getText();
                scienceTotalGet = scienceTotal.getText();
                computerTotalGet = computerTotal.getText();
                islamiatTotalGet = islamiatTotal.getText();
                socialTotalGet = socialTotal.getText();

                if (isNumeric(rollNoGet) && isNumeric(englishObtGet) && isNumeric(urduObtGet) && isNumeric(mathObtGet)
                        && isNumeric(scienceObtGet) && isNumeric(computerObtGet) && isNumeric(urduTotalGet)
                        && isNumeric(socialObtGet) && isNumeric(englishTotalGet) && isNumeric(englishObtGet)
                        && isNumeric(mathTotalGet) && isNumeric(scienceTotalGet) && isNumeric(computerTotalGet)
                        && isNumeric(islamiatTotalGet) && isNumeric(socialTotalGet)
                        && Integer.parseInt(englishObtGet)<=Integer.parseInt(englishTotalGet) && Integer.parseInt(urduObtGet)<=Integer.parseInt(urduTotalGet)
                        && Integer.parseInt(mathObtGet)<=Integer.parseInt(mathTotalGet) && Integer.parseInt(scienceObtGet)<=Integer.parseInt(scienceTotalGet)
                        && Integer.parseInt(islamiatObtGet)<=Integer.parseInt(islamiatTotalGet) && Integer.parseInt(socialObtGet)<=Integer.parseInt(socialTotalGet)){

                    studentName = getStdName(classNumber, rollNoGet);
                    if (studentName.equals("")){
                        JOptionPane.showMessageDialog(null, "Student NOT Found");
                    }else {
                        String[] data = {rollNoGet,studentName,classNumber,englishObtGet,englishTotalGet,
                                urduObtGet,urduTotalGet,mathObtGet,mathTotalGet,scienceObtGet,scienceTotalGet,
                                computerObtGet,computerTotalGet,islamiatObtGet,islamiatTotalGet,socialObtGet,socialTotalGet};

                        generatePdf(data);
                        JOptionPane.showMessageDialog(null, "Result PDF is Saved Successfully");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Wrong Inputs");
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

    public static String getStdName(String classNumber, String rollNo){
        File file = new File(classNumber+".txt");

        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                String[] record = reader.nextLine().split(";");
                if (record[0].equals(rollNo)){
                 return record[1];
                }
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static void generatePdf(String[] data){
        try {
            String fileName = "Roll No "+data[0]+" Result.pdf";
            File file = new File(fileName);
            if (file.exists()){
                file.delete();
            }

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Roll No " + data[0] + " Result.pdf"));
            document.open();
            document.add(new Paragraph("Result Card", FontFactory.getFont(FontFactory.TIMES_BOLD, 28, Font.BOLD, BaseColor.RED)));
            document.add(new Paragraph(new Date().toString()));
            document.add(new Paragraph("-------------------------------------------------------------------------------------"));
            document.add(new Paragraph("Ali Public School", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD, BaseColor.RED)));
            document.add(new Paragraph("-------------------------------------------------------------------------------------"));
            document.add(new Paragraph("Roll No: " + data[0], FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
            document.add(new Paragraph("Name: " + data[1], FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
            document.add(new Paragraph("Class: " + data[2], FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
            document.add(new Paragraph("--------------------------------------------------------------------------------------"));
            document.add(new Paragraph("--------------------------------------------------------------------------------------"));
            document.add(new Paragraph("--------------------------------------------------------------------------------------"));
            PdfPTable table = new PdfPTable(3);
            table.addCell("Subject");
            table.addCell("Marks Obtained");
            table.addCell("Total Marks");
            table.addCell("English");
            table.addCell(data[3]);
            table.addCell(data[4]);
            table.addCell("Urdu");
            table.addCell(data[5]);
            table.addCell(data[6]);
            table.addCell("Math");
            table.addCell(data[7]);
            table.addCell(data[8]);
            table.addCell("Science");
            table.addCell(data[9]);
            table.addCell(data[10]);
            table.addCell("Computer");
            table.addCell(data[11]);
            table.addCell(data[12]);
            table.addCell("Islamiat");
            table.addCell(data[13]);
            table.addCell(data[14]);
            table.addCell("Social Studies");
            table.addCell(data[15]);
            table.addCell(data[16]);
            table.addCell("Total");
            table.addCell(marksCal(data)[0]);
            table.addCell(marksCal(data)[1]);
            document.add(table);

            document.add(new Paragraph("Percentage: " + marksCal(data)[2]+"%", FontFactory.getFont(FontFactory.TIMES_BOLD, 18, Font.BOLD)));
            document.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String[] marksCal(String[] data){
        double sumObt = 0;
        double sumTotal = 0;
        double percentage;
        for (int i=3; i<data.length; i++){
            if (i%2 != 0){
                sumObt += Integer.parseInt(data[i]);
            }else {
                sumTotal += Integer.parseInt(data[i]);
            }
        }
        percentage = (sumObt / sumTotal) * 100;
        String[] marks = {sumObt+"", sumTotal+"", Math.round(percentage*100.0)/100.0+""};
        return marks;
    }

}
