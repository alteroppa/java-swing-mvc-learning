package Model;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    private ArrayList<User> userArrayList;

    public Database() {
    }

    // reads user from database file
    public ArrayList<User> loadUsers() {
        Connection c = null;
        PreparedStatement stmt = null;
        ArrayList<User> userList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/",
                            "postgres", "daa");
            System.out.println("Opened database successfully");
            stmt = c.prepareStatement("select * from Users");
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                String firstname = rs.getString(1);
                String lastname = rs.getString(2);
                userList.add(new User(firstname, lastname));
            }

            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("loaded users successfully");
        return userList;
    }

    public void createUserTable(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/",
                            "postgres", "daa");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USERS " +
                    //"(ID STRING PRIMARY KEY  NOT NULL," +
                    "(FIRSTNAME      TEXT    NOT NULL, " +
                    " LASTNAME       TEXT    NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public void saveUser(User user){
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/",
                            "postgres", "daa");
            System.out.println("Opened database successfully");
            stmt = c.prepareStatement("insert into Users values(?,?)");
            stmt.setString(1,user.getFirstname());
            stmt.setString(2,user.getLastname());
            stmt.executeUpdate();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("User saved successfully");
    }
    public void deleteUser(int selectedUser) {
        Connection c = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/",
                            "postgres", "daa");
            System.out.println("Opened database successfully");
            String sql = "DELETE FROM USERS WHERE LASTNAME =?";
            stmt = c.prepareStatement(sql);
            stmt.setString(1,this.loadUsers().get(selectedUser).getLastname());
            stmt.executeUpdate();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("deleted user successfully");
    }
}
