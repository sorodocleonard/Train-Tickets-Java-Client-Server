package org.example.Model.Persistenta;

import org.example.Model.Administrator;
import org.example.Model.ConnectionFactory;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistentaAdmin implements Serializable {

    protected static final Logger LOGGER = Logger.getLogger(PersistentaAdmin.class.getName());
    private static final String insertStatementString = "INSERT INTO administratori (username, pass, rol, tel, email)" + " VALUES (?, ?, ?, ?, ?)";
    private static final String deleteStatementString = "DELETE FROM administratori where username = ?";
    private static final String resetStatementString = "DELETE * from administratori";
    private static final String updateStatementString = "UPDATE administratori SET username = ? , pass = ? WHERE username= ?;";
    private static final String selectStatementString = "SELECT * from administratori";

    public static void insert(Administrator c) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, c.getUsername());
            insertStatement.setString(2, c.getPass());
            insertStatement.setString(3, c.getRol());
            insertStatement.setString(4, c.getTel());
            insertStatement.setString(5, c.getEmail());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaAdmin: insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void update(String newUsername, String pass, String username) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, newUsername);
            updateStatement.setString(2, pass);
            updateStatement.setString(3, username);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaAdmin: update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void delete(String username) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setString(1, username);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaAdmin: delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void reset() {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement resetStatement = null;
        try {
            resetStatement = dbConnection.prepareStatement(resetStatementString, Statement.RETURN_GENERATED_KEYS);
            resetStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaAdmin: reset " + e.getMessage());
        } finally {
            ConnectionFactory.close(resetStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static List<Administrator> getAll() {
        List<Administrator> list = new ArrayList<Administrator>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        Administrator c = null;
        try {
            selectStatement = dbConnection.prepareStatement(selectStatementString, Statement.RETURN_GENERATED_KEYS);
            rs = selectStatement.executeQuery();
            String username;
            String pass;
            String  rol;
            String tel;
            String email;

            while(rs.next()){
                username = rs.getString("username");
                pass = rs.getString("pass");
                rol = rs.getString("rol");
                tel = rs.getString("tel");
                email = rs.getString("email");
                c = new Administrator();
                c.setUsername(username);
                c.setRol(rol);
                c.setPass(pass);
                c.setTel(tel);
                c.setEmail(email);
                list.add(c);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaAdmin: getAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(selectStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

    public boolean cautaN(String usrN) {

        PersistentaAdmin pers =  new PersistentaAdmin();
        for (Administrator x: pers.getAll()) {
            if(usrN.equals(x.getUsername()))
                return true;

        }
        return false;
    }

    public boolean cautaP(String pass){
        PersistentaAdmin lista = new PersistentaAdmin();
        for (Administrator x: lista.getAll()) {
            if(pass.equals(x.getPass()))
                return true;

        }
        return false;
    }
}