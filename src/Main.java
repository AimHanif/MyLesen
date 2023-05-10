import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<String> licenseTypes = new ArrayList<>();
    public static ArrayList<String> fullNames = new ArrayList<>();
    public static ArrayList<String> userNames = new ArrayList<>();
    public static ArrayList<String> icNumbers = new ArrayList<>();
    public static ArrayList<String> instructors = new ArrayList<>();
    public static ArrayList<String> times = new ArrayList<>();
    public static ArrayList<String> phases = new ArrayList<>();
    public static ArrayList<String> booking = new ArrayList<>();
    static ArrayList<String> vehicleNumbers = new ArrayList<>(Arrays.asList("JLD802", "JMK801", "JKR702"));
    static ArrayList<String> vehicleTypes = new ArrayList<>(Arrays.asList("Axia", "Axia", "Honda Wave"));
    static ArrayList<String> licenseExpirations = new ArrayList<>(Arrays.asList("2023-01-20", "2023-02-20", "2023-03-20"));
    public static void main(String[] args) {
        licenseTypes.add("DA");
        licenseTypes.add("D");
        licenseTypes.add("B2");
        licenseTypes.add("B");

        userNames.add("aihanif");
        userNames.add("wafii");
        userNames.add("pawi");
        userNames.add("rashina");

        fullNames.add("Aiman Hanif");
        fullNames.add("Adam Wafi");
        fullNames.add("Farisha");
        fullNames.add("Rashina");

        icNumbers.add("030811060187");
        icNumbers.add("030104100381");
        icNumbers.add("0304050105822");
        icNumbers.add("030123013456");

        instructors.add("Nurin Akma");
        instructors.add("Ahza Muhzahffar");
        instructors.add("Ganesh Raaj");
        instructors.add("Vasanthavanan");

        times.add("Morning (8am - 10am)");
        times.add("Noon (10am - 12pm)");
        times.add("Afternoon (12pm - 2pm)");
        times.add("Evening (2pm - 4pm)");

        phases.add("Theory");
        phases.add("Circuit");
        phases.add("Road");

        booking.add("Driving Lesson");
        booking.add("JPJ Test");

        welcome();
    }
    public static void welcome(){
        User u = new User();
        Student s = new Student(fullNames, icNumbers, licenseTypes);
        int adminOrStudent = showOptionDialog(new Object[]{"Admin", "Student"}, "Welcome");
        if (adminOrStudent == 0) {
            int logins = showOptionDialog(new Object[]{"Log In"}, "Welcome");
            if(logins == 0) {
                u.login(userNames,icNumbers, adminOrStudent);
            }
        }
        else {
            int loginOrRegister = showOptionDialog(new Object[]{"Log In", "Register"}, "Welcome");
            if(loginOrRegister == 0) {
                u.login(userNames,icNumbers, adminOrStudent);
            } else {
                // code for registering goes here
                s.register(userNames,icNumbers,fullNames,licenseTypes);
                menu();
            }
        }
    }
    public static void menu () {
        Progress p = new Progress();
        Booking b = new Booking();
        boolean running = true;
        while (running) {
            int userChoice;
            userChoice = showOptionDialog(new Object[]{"CHECK PROGRESS", "BOOKING", "SUBMIT ATTENDANCE","LOGOUT", "EXIT"}, "Choose an option");
            switch (userChoice) {
                case 0:
                    p.displayProgress(licenseTypes);
                    break;
                case 1:
                    b.booking(icNumbers, fullNames, licenseTypes, instructors);
                    break;
                case 2:
                    p.insertAttendance();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "You have successfully logged out.");
                    new User();
                    welcome();
                    break;
                case 4:
                case default:
                    running = false;
                    break;
            }
        }
    }

    public static void admin() {
        Student s = new Student(fullNames, icNumbers, licenseTypes);
        Booking b = new Booking();
        Manage m = new Manage();
        Record r = new Record();
        new Progress();
        boolean running = true;
        while (running) {
            int userChoice = showOptionDialog(new Object[]{"REGISTER NEW STUDENT", "BOOKING", "MANAGE STUDENT","MANAGE VEHICLES","VIEW RECORDS","LOGOUT", "EXIT"}, "Choose an option");
            switch (userChoice) {
                case 0:
                    s.register(userNames, icNumbers, fullNames, licenseTypes);
                    break;
                case 1:
                    b.booking(icNumbers, fullNames, licenseTypes, instructors);
                    break;
                case 2:
                    m.manageStudent(icNumbers, fullNames, licenseTypes);
                    break;
                case 3:
                    m.manageVehicle(vehicleNumbers, vehicleTypes, licenseExpirations);
                    break;
                case 4:
                    int choice = showOptionDialog(new Object[]{"Student", "Vehicles"}, "Welcome");
                    if (choice == 0) {
                        r.recordStudent(userNames, icNumbers, fullNames, licenseTypes);
                    } else if (choice == 1) {
                        r.recordVehicles(vehicleTypes, vehicleNumbers, licenseExpirations);
                    }
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "You have successfully logged out.");
                    welcome();
                    break;
                case 6:
                case default:
                    JOptionPane.showMessageDialog(null, "Thanks for using MyLesen");
                    running = false;
                    break;
            }
        }
    }
    public static int showOptionDialog(Object[] options, String title) {
        ImageIcon icon = new ImageIcon("src/mylesen.png");
        int userChoice = -1;
        boolean running = true;
        while (running) {
            try {
                userChoice = JOptionPane.showOptionDialog(null, "Select an option", title, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);
                running = false;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An error occurred. Please try again.");
            }
        }
        return userChoice;
    }

    public static String getInput(String prompt) {
        String input = "";
        boolean valid = false;
        while (!valid) {
            try {
                input = JOptionPane.showInputDialog(prompt);
                if (input == null || input.trim().equals("") || input.trim().equalsIgnoreCase("cancel")) {
                    return input;
                }
                valid = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return input;
    }
}