package View;

import Controller.UserController;
import Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserDetails extends JPanel {

    // Table for user data
    public JTable userTable;
    // table column
    private String[] userTableColumn = {"FIRST NAME", "LAST NAME"};

    // back button
    private JButton backButton;

    //delete user button
    private JButton deleteButton;

    public UserDetails() {
        // uses box layout
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // toolbar for buttons
        JToolBar toolBar = new JToolBar();
        userTable = new JTable();
        // scroll bar for table
        JScrollPane userTableScroll = new JScrollPane(userTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        backButton = new JButton("Go Back");
        deleteButton = new JButton("delete User");

        add(toolBar);
        toolBar.add(backButton);
        toolBar.add(deleteButton);
        toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getMinimumSize().height));
        add(userTableScroll);

    }

    // gets users from database and loads to table
    public void getUsers(ArrayList<User> users) {
        DefaultTableModel defaultTableModel = (DefaultTableModel) userTable.getModel();
        defaultTableModel.setColumnIdentifiers(userTableColumn);
        int i = 0;
        defaultTableModel.setRowCount(0);
        while(i < users.size()) {
            String[] rows = {users.get(i).getFirstname(), users.get(i).getLastname()};
            defaultTableModel.addRow(rows);
            i++;
        }
    }


    // event listener for delete button
    public void deleteButton(ActionListener actionListener) {
        deleteButton.addActionListener(actionListener);
    }
    // event listener for back button
    public void backButton(ActionListener actionListener) {
        backButton.addActionListener(actionListener);
    }


}
