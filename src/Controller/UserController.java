package Controller;

import Model.Database;
import Model.User;
import View.Form;
import View.UserDetails;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class UserController {
    // database
    private Database database;
    private Form form;
    private UserDetails userDetails;


    public UserController(Form form, UserDetails userDetails) {
        this.database = new Database();
        database.createUserTable();
        this.form = form;
        this.userDetails = userDetails;

        // submit user
        this.form.submitUsers(e -> {
            String firstname = this.form.getFirstname().trim();
            String lastname = this.form.getLastname().trim();

            // simple validations
            if(firstname.isEmpty()) {
                JOptionPane.showMessageDialog(this.form, "First Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if(lastname.isEmpty()) {
                JOptionPane.showMessageDialog(this.form, "Last Name Required.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            User newUser = new User(firstname, lastname);
            this.database.saveUser(newUser);
            this.form.reset(true);
        });

        this.userDetails.deleteButton(e -> {
            int selectedRow = this.userDetails.userTable.getSelectedRow();
            database.deleteUser(selectedRow);
            this.userDetails.getUsers(database.loadUsers());
        });

        // load users
        this.form.viewUsers(e -> {
            this.userDetails.getUsers(this.database.loadUsers());
        });

    }
}
