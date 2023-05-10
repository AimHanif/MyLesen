import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
class Student extends User {
    private String icNumber;
    private String fullName;
    private String licenseType;
    private String userName;

    public Student(String user, String name, String ic, String license)
    {
        setUserName(user);
        setFullName(name);
        setIcNumber(ic);
        setLicenseType(license);
    }

    @Override
    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public Student(ArrayList<String> name, ArrayList<String> icNumber, ArrayList<String> licenseType) {
        super(name, String.valueOf(icNumber));
        this.licenseType = String.valueOf(licenseType);
    }

    private String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getLicenseType() {
        return licenseType;
    }
    
    public void register(ArrayList<String>userNames,ArrayList<String> icNumbers, ArrayList<String> fullNames, ArrayList<String> licenseTypes) {
        ImageIcon icon = new ImageIcon("src/mylesen.png");
        String userName = Main.getInput("Enter student's username:");
        if (userName == null) {
            return;
        }
        // Prompt the user for input to create a new student
        String icNumber = Main.getInput("Enter student's IC number:");
        if (icNumber == null) {
            return;
        }
        icNumber = icNumber.replaceAll(" +", "");
        if (icNumber.length() != 12) {
            JOptionPane.showMessageDialog(null, "Invalid IC number! It should be 12 digits long.");
            return;
        }
        try {
            int age = getAgeFromICNumber(icNumber);
            if (age < 18) {
                JOptionPane.showMessageDialog(null, "Student must be above 18 years old.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid IC number! " + e.getMessage());
            return;
        }
        String fullName = Main.getInput("Enter student's full name:");
        if (fullName == null) {
            return;
        }
        String licenseType = (String) JOptionPane.showInputDialog(null, "Select student licenseType", "Driving Lesson", JOptionPane.QUESTION_MESSAGE, icon, licenseTypes.toArray(), licenseTypes.get(0));
        if (licenseType == null) {
            return;
        }
        fullName = fullName.toUpperCase();
        // Add the new student to the list of students
        Student student = new Student(userName,fullName,licenseType,icNumber);
        icNumbers.add(icNumber);
        fullNames.add(fullName);
        licenseTypes.add(licenseType);
        userNames.add(userName);
        registerCompleted(student);
    }

    public void registerCompleted(Student s) {
        JOptionPane.showMessageDialog(null, "Thanks for registering! " + s.getFullName() + ", we now shall call you " + s.getUserName() + " for " + s.getIcNumber() + " with ic numbers : " + s.getLicenseType());
    }

    private int getAgeFromICNumber(String icNumber) throws Exception {
// check the format of ic number
        if (!icNumber.matches("^[0-9]{12}$"))
            throw new Exception("Invalid format.");
// get the birth year from ic number
        int birthYear = Integer.parseInt(icNumber.substring(0,2));
        if (birthYear < 0 || birthYear > 99)
            throw new Exception("Invalid birth year.");
        if (birthYear < 18) birthYear += 2000;
        else birthYear += 1900;
// calculate the age
        return LocalDate.now().getYear() - birthYear;
    }
}