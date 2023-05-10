import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Booking {
    String chosenStudent;
    String classType;
    String phase;
    LocalDate chosenDate;
    String chosenInstructor;
    String[] phases = {
            "Theory",
            "Circuit",
            "Road"
    };
    String[] time = {
            "Morning (8am - 10am)",
            "Noon (10am - 12pm)",
            "Afternoon (12pm - 2pm)",
            "Evening (2pm - 4pm)"
    };
    String[] booking = {
            "Driving Lesson","JPJ TEST"
    };
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public Booking() {

    }

    public void booking(ArrayList<String> icNumbers, ArrayList<String> fullNames, ArrayList<String> licenseTypes,ArrayList<String> instructors) {
        ImageIcon icon = new ImageIcon("src/mylesen.png");
        String chosenBooking = (String) JOptionPane.showInputDialog(null, "Lesson or JPJ Test?", "Driving Lesson", JOptionPane.QUESTION_MESSAGE, icon, booking, booking[0]);
        chosenStudent = (String) JOptionPane.showInputDialog(null, "Select a Student", "Driving Lesson", JOptionPane.QUESTION_MESSAGE, icon, fullNames.toArray(), fullNames.get(0));
        int index = fullNames.indexOf(chosenStudent);
        classType = "";
        do {
            classType = (String) JOptionPane.showInputDialog(null, "Select student licenseType", "Driving Lesson", JOptionPane.QUESTION_MESSAGE, icon, licenseTypes.toArray(), licenseTypes.get(0));
            if (classType == null) {
                return;
            }
            if (!chosenBooking.equalsIgnoreCase("JPJ")) {
                phase = (String) JOptionPane.showInputDialog(null, "Select class phase to book", "Driving Lesson", JOptionPane.QUESTION_MESSAGE, icon, phases, phases[0]);
                if (phase == null) {
                    return;
                }
                if (phase.equalsIgnoreCase("Circuit") || phase.equalsIgnoreCase("Road")) {
                    phases = new String[phases.length - 1];
                }
            }
        } while (classType == null);
        LocalDate today = LocalDate.now();
        LocalDate[] availableDates = new LocalDate[14];
        for (int i = 0; i < 14; i++) {
            availableDates[i] = today.plusDays(i);
            if (i == 13) {
                break;
            }
            if (availableDates[i].getDayOfWeek().toString().equals("MONDAY")) {
                availableDates[i + 1] = today.plusDays(i + 1);
                i++;
                if (i == 13) {
                    break;
                }
                if (availableDates[i].getDayOfWeek().toString().equals("MONDAY")) {
                    availableDates[i + 1] = today.plusDays(i + 1);
                    i++;
                    if (i == 13) {
                        break;
                    }
                }
            }
        }

        chosenDate = (LocalDate) JOptionPane.showInputDialog(null, "Select date to book", "Driving Lesson", JOptionPane.QUESTION_MESSAGE, icon, availableDates, availableDates[0]);
        if (chosenDate == null) {
            return;
        }
        if (!chosenBooking.equalsIgnoreCase("JPJ")) {
            chosenInstructor = (String) JOptionPane.showInputDialog(null, "Assign an Instructor", "Driving Lesson", JOptionPane.QUESTION_MESSAGE, icon, instructors.toArray(), instructors.get(0));
        }
        if (chosenInstructor == null) {
            return;
        }
        if (!phase.equalsIgnoreCase("Theory")) {
            int timeOption = JOptionPane.showOptionDialog(null, "Select time for " + classType + " " + phase + " class", "Driving Lesson", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, time, time[0]);
            if (timeOption == JOptionPane.CLOSED_OPTION) {
                return;
            }
            String dateString = chosenDate.format(dateFormat);
            JOptionPane.showMessageDialog(null, "You booked " + chosenBooking + " for " + chosenStudent + " for " + classType + " during " + phase + " on " + dateString + " at " + time[timeOption]);
            receipt(index,icNumbers,fullNames,licenseTypes,phase,dateString, chosenInstructor, chosenBooking);
        } else {
            String dateString = chosenDate.format(dateFormat);
            JOptionPane.showMessageDialog(null, "You booked " + chosenBooking + " for " + chosenStudent + " for " + classType + " during " + phase + " on " + dateString);
            receipt(index,icNumbers,fullNames,licenseTypes,phase, dateString, chosenInstructor,chosenBooking);
        }
    }


    public void receipt(int index, ArrayList<String> icNumbers, ArrayList<String> fullNames, ArrayList<String> licenseTypes, String dateString, String phase, String chosenInstructor, String chosenBooking){
        try {
            FileWriter fileWriter = new FileWriter("receipt.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("MyLesen SDN BHD");
            bufferedWriter.newLine();
            bufferedWriter.write("==============================");
            bufferedWriter.newLine();
            bufferedWriter.write(chosenBooking+" BOOKING");
            bufferedWriter.newLine();
            bufferedWriter.write("==============================");
            bufferedWriter.newLine();
            bufferedWriter.write("Identification Numbers : "+ icNumbers.get(index));
            bufferedWriter.newLine();
            bufferedWriter.write("Full Name : "+ fullNames.get(index).toUpperCase());
            bufferedWriter.newLine();
            bufferedWriter.write("License Type : "+ licenseTypes.get(index));
            bufferedWriter.newLine();
            bufferedWriter.write("Date : "+ dateString);
            bufferedWriter.newLine();
            bufferedWriter.write("Phase : "+ phase);
            bufferedWriter.newLine();
            bufferedWriter.write("Instructor : "+ chosenInstructor);
            bufferedWriter.newLine();
            bufferedWriter.write("==============================");
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
