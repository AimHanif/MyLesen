import javax.swing.*;
import java.util.ArrayList;

class Manage {

    public Manage() {
    }

    public void manageStudent(ArrayList<String> icNumbers, ArrayList<String> fullNames, ArrayList<String> licenseTypes) {
        ImageIcon icon = new ImageIcon("src/mylesen.png");
        boolean running = true;
        while (running) {
            String chosenStudent = (String) JOptionPane.showInputDialog(null, "Select a Student", "Driving Lesson", JOptionPane.QUESTION_MESSAGE, icon, fullNames.toArray(), fullNames.get(0));
            if (chosenStudent == null) {
                running = false;
            } else {
                int ind = fullNames.indexOf(chosenStudent);
                JOptionPane.showMessageDialog(null, "Full Name: " + fullNames.get(ind) + "\nIC Number: " + icNumbers.get(ind) + "\nLicense Type: " + licenseTypes.get(ind), "Student Information", JOptionPane.INFORMATION_MESSAGE);
                int choice = JOptionPane.showOptionDialog(null, "Let's Update It!", "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"UPDATE", "REMOVE","BACK"}, "UPDATE");
                if (choice == 0) {
                    // Update
                    String[] options = {"Full Name", "IC Number", "License Type"};
                    int updateChoice = JOptionPane.showOptionDialog(null, "Which information do you want to update?", "Update Student", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (updateChoice == 0) {
                        String newFullname = Main.getInput("Enter new full name:");
                        fullNames.set(ind, newFullname);
                    } else if (updateChoice == 1) {
                        String newICNumber = Main.getInput("Enter new IC number:");
                        icNumbers.set(ind, newICNumber);
                    } else if (updateChoice == 2) {
                        String newLicenseType = Main.getInput("Enter new License Type:");
                        licenseTypes.set(ind, newLicenseType);
                    }
                } else {
                    running = isRunning(icNumbers, fullNames, licenseTypes, true, ind, choice);
                }
            }
        }
    }

    private boolean isRunning(ArrayList<String> icNumbers, ArrayList<String> fullNames, ArrayList<String> licenseTypes, boolean running, int ind, int choice) {
        if (choice == 1) {
            int option = Main.showOptionDialog(new Object[]{"Yes", "No"}, "Choose an option");
            // Remove
            if (option == 0) {
                fullNames.remove(ind);
                icNumbers.remove(ind);
                licenseTypes.remove(ind);
            }else {
                running = false;
            }

        }
        else if (choice == 2) {
            running = false;
        }
        return running;
    }

    public void manageVehicle(ArrayList<String> vehicleTypes, ArrayList<String> vehicleNumbers, ArrayList<String> licenseExpirations) {
        int userChoice = Main.showOptionDialog(new Object[]{"ADD NEW VEHICLE","MANAGE CURRENT VEHICLE"}, "Choose an option");
        if (userChoice == 0) {// Add New Vehicle
            String newVehicleType = Main.getInput("Enter new Vehicle Type:");
            String newVehicleNumber = Main.getInput("Enter new Vehicle Number:");
            String newLicenseExpiration = Main.getInput("Enter new License Expiration:");
            vehicleTypes.add(newVehicleNumber);
            vehicleNumbers.add(newVehicleType);
            licenseExpirations.add(newLicenseExpiration);
        }
        if (userChoice == 1) {// Manage Current Vehicle
            ImageIcon icon = new ImageIcon("src/mylesen.png");
            boolean running = true;
            while (running) {
                String chosenVehicle = (String) JOptionPane.showInputDialog(null, "Select a Vehicle", "Driving Lesson", JOptionPane.QUESTION_MESSAGE, icon, vehicleNumbers.toArray(), vehicleNumbers.get(0));
                if (chosenVehicle == null) {
                    running = false;
                } else {
                    int ind = vehicleNumbers.indexOf(chosenVehicle);
                    JOptionPane.showMessageDialog(null, "Vehicle Plate Number: " + vehicleNumbers.get(ind) + "\nVehicle Type: " + vehicleTypes.get(ind) + "\nLicense Expiration: " + licenseExpirations.get(ind), "Vehicle Information", JOptionPane.INFORMATION_MESSAGE);
                    int choice = JOptionPane.showOptionDialog(null, "Let's Update It!", "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"UPDATE", "REMOVE", "BACK"}, "UPDATE");
                    if (choice == 0) {
                        // Update
                        String[] options = {"Vehicle Plate Number", "Vehicle Type", "License Expiration"};
                        int updateChoice = JOptionPane.showOptionDialog(null, "Which information do you want to update?", "Update Vehicle", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if (updateChoice == 0) {
                            String newVehicleNumber = Main.getInput("Enter new vehicle plate number:");
                            vehicleNumbers.set(ind, newVehicleNumber);
                        } else if (updateChoice == 1) {
                            String newVehicleType = Main.getInput("Enter new vehicle type:");
                            vehicleTypes.set(ind, newVehicleType);
                        } else if (updateChoice == 2) {
                            String newLicenseExpiration = Main.getInput("Enter new license expiration date (yyyy-mm-dd):");
                            licenseExpirations.set(ind, newLicenseExpiration);
                        }
                    } else running = isRunning(vehicleTypes, vehicleNumbers, licenseExpirations, true, ind, choice);
                }
            }
        }
    }
}