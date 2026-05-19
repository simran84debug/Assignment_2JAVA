import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentManagementSystem extends JFrame implements ActionListener {

    // Labels
    JLabel lblTitle, lblId, lblName, lblEmail;

    // Text Fields
    JTextField txtId, txtName, txtEmail;

    // Buttons
    JButton btnAdd, btnUpdate, btnDelete, btnClear, btnExit;

    // Table
    JTable table;
    DefaultTableModel model;

    public StudentManagementSystem() {

        // Frame Settings
        setTitle("Student Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(230, 240, 250));

        // ================= TITLE =================

        lblTitle = new JLabel("STUDENT MANAGEMENT SYSTEM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setForeground(Color.BLACK);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(220, 230, 240));
        titlePanel.add(lblTitle);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // ================= LEFT PANEL =================

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(6, 1, 10, 10));
        leftPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));
        leftPanel.setBackground(new Color(240, 248, 255));

        lblId = new JLabel("Student ID");
        lblName = new JLabel("Student Name");
        lblEmail = new JLabel("Email");

    txtId = new JTextField(10);
    txtName = new JTextField(10);
    txtEmail = new JTextField(10);

        leftPanel.add(lblId);
        leftPanel.add(txtId);

        leftPanel.add(lblName);
        leftPanel.add(txtName);

        leftPanel.add(lblEmail);
        leftPanel.add(txtEmail);

        // ================= TABLE =================

        model = new DefaultTableModel();

        model.addColumn("Student ID");
        model.addColumn("Student Name");
        model.addColumn("Email");

        table = new JTable(model);

        table.setRowHeight(20);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Student Database"));
        centerPanel.add(scrollPane);

        // ================= CENTER AREA =================

        JPanel middlePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        middlePanel.setBackground(new Color(230, 240, 250));

        middlePanel.add(leftPanel);
        middlePanel.add(centerPanel);

        mainPanel.add(middlePanel, BorderLayout.CENTER);

        // ================= BUTTONS =================

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(220, 230, 240));

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnExit = new JButton("Exit");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnExit);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // ================= ACTION LISTENERS =================

        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);

        // ================= TABLE CLICK =================

        table.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();

                txtId.setText(model.getValueAt(row, 0).toString());

                txtName.setText(model.getValueAt(row, 1).toString());

                txtEmail.setText(model.getValueAt(row, 2).toString());
            }
        });

        // Add Main Panel
        add(mainPanel);

        setVisible(true);
    }

    // ================= ACTION EVENTS =================

    public void actionPerformed(ActionEvent e) {

        // ADD

        if (e.getSource() == btnAdd) {

            if (txtId.getText().isEmpty() ||
                txtName.getText().isEmpty() ||
                txtEmail.getText().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please Fill All Fields");

            } else {

                model.addRow(new Object[] {

                        txtId.getText(),
                        txtName.getText(),
                        txtEmail.getText()
                });

                JOptionPane.showMessageDialog(this,
                        "Student Added Successfully");

                clearFields();
            }
        }

        // UPDATE

        else if (e.getSource() == btnUpdate) {

            int row = table.getSelectedRow();

            if (row >= 0) {

                model.setValueAt(txtId.getText(), row, 0);
                model.setValueAt(txtName.getText(), row, 1);
                model.setValueAt(txtEmail.getText(), row, 2);

                JOptionPane.showMessageDialog(this,
                        "Record Updated");

                clearFields();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Select Row To Update");
            }
        }

        // DELETE

        else if (e.getSource() == btnDelete) {

            int row = table.getSelectedRow();

            if (row >= 0) {

                model.removeRow(row);

                JOptionPane.showMessageDialog(this,
                        "Record Deleted");

                clearFields();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Select Row To Delete");
            }
        }

        // CLEAR

        else if (e.getSource() == btnClear) {

            clearFields();
        }

        // EXIT

        else if (e.getSource() == btnExit) {

            System.exit(0);
        }
    }

    // ================= CLEAR METHOD =================

    public void clearFields() {

        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
    }

    // ================= MAIN METHOD =================

    public static void main(String[] args) {

        new StudentManagementSystem();
    }
}