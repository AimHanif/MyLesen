import javax.swing.*;
import java.util.ArrayList;

class User extends JFrame {
    private String name = null;
    private String icNumber = null;

    public User(ArrayList<String> name, String icNumber) {
        this.name = String.valueOf(name);
        this.icNumber = String.valueOf(icNumber);
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public String getIcNumber() {
        return icNumber;
    }
    public void login(ArrayList<String> userNames, ArrayList<String> icNumbers, int adminOrStudent) {
        setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel labelUsername = new JLabel("Username:");
        labelUsername.setBounds(10, 10, 80, 25);
        panel.add(labelUsername);

        JTextField textUsername = new JTextField(20);
        textUsername.setBounds(100, 10, 160, 25);
        panel.add(textUsername);

        JLabel labelICNumber = new JLabel("IC Number:");
        labelICNumber.setBounds(10, 40, 80, 25);
        panel.add(labelICNumber);

        JTextField textICNumber = new JTextField(20);
        textICNumber.setBounds(100, 40, 160, 25);
        panel.add(textICNumber);
        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBounds(10, 80, 80, 25);
        buttonLogin.addActionListener(e -> {
            boolean validInput = false;
            String username = textUsername.getText();
            String icNumber = textICNumber.getText();
            int index = 0;
            for (int i = 0; i < userNames.size(); i++) {
                if (username.equals(userNames.get(i)) && icNumber.equals(icNumbers.get(i))) {
                    validInput = true;
                    index = i;
                    break;
                }
            }
            if (!validInput) {
                JOptionPane.showMessageDialog(null, "Invalid username or IC number. Please try again.");
            } else {
                JOptionPane.showMessageDialog(null, "Welcome, " + username + "!");
                if (adminOrStudent == 0) {
                        Main.admin();
                } else {
                        Main.menu();
                }
            }
        });
        panel.add(buttonLogin);
        add(panel);
        setTitle("User Login");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
