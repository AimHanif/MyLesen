import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class Record {

    public void recordStudent(ArrayList<String>userNames,ArrayList<String> icNumbers, ArrayList<String> fullNames, ArrayList<String> licenseTypes) {
        // Create a new table model
        DefaultTableModel model = new DefaultTableModel();

        // Add columns to the table model
        model.addColumn("Index Number");
        model.addColumn("User Name");
        model.addColumn("Full Name");
        model.addColumn("IC Number");
        model.addColumn("License Type");

        // Add data to the table model
        for (int i = 0; i < fullNames.size(); i++) {
            Object[] row = {i + 1, userNames.get(i), fullNames.get(i), icNumbers.get(i), licenseTypes.get(i)};
            model.addRow(row);
        }

        // Create a new table with the table model
        JTable table = new JTable(model);

        // Create a table row sorter for the table
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);

        // Set the table's row sorter
        table.setRowSorter(sorter);

        // Set custom font for table headers
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Set custom font for table cells
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        // Set custom background color for table cells
        table.setBackground(Color.LIGHT_GRAY);

        // Set custom foreground color for table cells
        table.setForeground(Color.BLACK);

        // Set custom row height for table cells
        table.setRowHeight(30);

        // Create a scroll pane to hold the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to a frame
        JFrame frame = new JFrame();
        frame.add(scrollPane);
        frame.setSize(720, 200);
        frame.setVisible(true);
    }
    public void recordVehicles(ArrayList<String>vehicleTypes,ArrayList<String> vehicleNumbers, ArrayList<String> licenseExpirations) {
        // Create a new table model
        DefaultTableModel model = new DefaultTableModel();

        // Add columns to the table model
        model.addColumn("Index Number");
        model.addColumn("Vehicle Models");
        model.addColumn("Plate Numbers");
        model.addColumn("License Expirations Dates");

        // Add data to the table model
        for (int i = 0; i < vehicleNumbers.size(); i++) {
            Object[] row = {i + 1, vehicleTypes.get(i), vehicleNumbers.get(i), licenseExpirations.get(i)};
            model.addRow(row);
        }

        // Create a new table with the table model
        JTable table = new JTable(model);

        // Create a table row sorter for the table
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);

        // Set the table's row sorter
        table.setRowSorter(sorter);

        // Set custom font for table headers
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Set custom font for table cells
        table.setFont(new Font("Arial", Font.PLAIN, 12));

        // Set custom background color for table cells
        table.setBackground(Color.LIGHT_GRAY);

        // Set custom foreground color for table cells
        table.setForeground(Color.BLACK);

        // Set custom row height for table cells
        table.setRowHeight(30);

        // Create a scroll pane to hold the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to a frame
        JFrame frame = new JFrame();
        frame.add(scrollPane);
        frame.setSize(720, 200);
        frame.setVisible(true);
    }
}
