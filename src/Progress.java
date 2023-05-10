import javax.swing.*;
import java.util.ArrayList;

public class Progress {

    private final ArrayList<String> codes;
    private final ArrayList<String> phases;
    private final ArrayList<String> licenseTypes;
    private final ArrayList<String> progress;
    private final int[] attendanceCount;

    public Progress() {
        codes = new ArrayList<>();
        phases = new ArrayList<>();
        licenseTypes = new ArrayList<>();
        progress = new ArrayList<>();
        attendanceCount = new int[12];

        // generate 4 random codes for each licenseType
        for (String licenseType : new String[]{"DA", "D", "B2", "B"}) {
            for (String phase : new String[]{"Theory", "Circuit", "Road"}) {
                String code = generateCode();
                codes.add(code);
                phases.add(phase);
                licenseTypes.add(licenseType);
                progress.add("0");
            }
        }
    }
    public void displayCodes() {
        for (int i = 0; i < codes.size(); i++) {
            System.out.println("Code for " + licenseTypes.get(i) + " " + phases.get(i) + " class: " + codes.get(i));
        }
    }


    private String generateCode() {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append((int) (Math.random() * 10));
        }
        return code.toString();
    }

    public void displayProgress(ArrayList<String> licenseTypes) {
        ImageIcon icon = new ImageIcon("src/mylesen.png");
        Object[] phase = {"Theory", "Circuit", "Road"};
        int classOption = JOptionPane.showOptionDialog(null, "Select class phase to see progress", "Progress",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, phase, phase[0]);
        Object[] classType = {"D", "DA", "B", "B2"};
        int typeOption = JOptionPane.showOptionDialog(null, "Select license type to see progress", "Progress",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, licenseTypes.toArray(), licenseTypes.get(0));
        int index = (3 * typeOption) + classOption;
        JOptionPane.showMessageDialog(null, "Your progress for " + classType[typeOption] + " " + phase[classOption] + " is: " + progress.get(index) + "%");
    }


    public void insertAttendance() {
        displayCodes();
        ImageIcon icon = new ImageIcon("src/mylesen.png");
        Object[] phase = {"Theory", "Circuit", "Road"};
        int classOption = JOptionPane.showOptionDialog(null, "Select class phase to insert progress", "Progress",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, phase, phase[0]);
        Object[] licenseType = {"DA", "D", "B2", "B"};
        int typeOption = JOptionPane.showOptionDialog(null, "Select license type to see progress", "Progress",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, licenseType, licenseType[0]);
        int index = (3 * typeOption) + classOption;
        // check the number of times the user has inserted attendance
        int attendanceCount = this.attendanceCount[index];
        if (phase[classOption].equals("Theory") && attendanceCount >= 1) {
            JOptionPane.showMessageDialog(null, "Theory class can only be attended once. Attendance not inserted.");
            return;
        }
        String code = JOptionPane.showInputDialog(null, "Enter code for " + licenseType[typeOption] + " " + phase[classOption] + " class", "Progress", JOptionPane.INFORMATION_MESSAGE);
        verifyAttendance(code, index);
    }

    public void verifyAttendance(String code, int index) {
        int increment = 0;
        boolean verified = code.equals(codes.get(index));
        if (verified) {
            String phase = phases.get(index);
            if (phases.get(index).equals("Theory")) {
                increment = 100;
            }
            if (licenseTypes.get(index).equals("B") || licenseTypes.get(index).equals("B2")) {
                if (phase.equals("Circuit")) {
                    increment = 100 / 9;
                } else if (phase.equals("Road")) {
                    increment = 100 / 6;
                }
            } else if (licenseTypes.get(index).equals("D") || licenseTypes.get(index).equals("DA")) {
                if (phases.get(index).equals("Circuit")) {
                    increment = 100 / 6;
                } else if (phases.get(index).equals("Road")) {
                    increment = 100 / 10;
                }
            }
            int currentProgress = Integer.parseInt(progress.get(index));
            currentProgress += increment;
            progress.set(index, Integer.toString(currentProgress));
            attendanceCount[index]++; // increment the attendance count
            JOptionPane.showMessageDialog(null, "Attendance verified. Progress incremented by " + increment + ".");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid code. Attendance not verified.");
        }
    }
}
