package org.example.Model.Persistenta;

import org.example.Model.Angajat;
import org.example.Model.ConnectionFactory;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistentaAngajat implements Serializable {
    protected static final Logger LOGGER = Logger.getLogger(PersistentaAngajat.class.getName());
    private static final String insertStatementString = "INSERT INTO angajati (username, pass, rol, tel, email)" + " VALUES (?, ?, ?, ?, ?)";
    private static final String deleteStatementString = "DELETE FROM angajati where username = ?";
    private static final String resetStatementString = "DELETE * from angajati";
    private static final String updateStatementString = "UPDATE angajati SET username = ? , pass = ? WHERE username= ?;";
    private static final String selectStatementString = "SELECT * from angajati";

    public static void insert(Angajat c) {

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
            LOGGER.log(Level.WARNING, "PersistentaAngajat: insert " + e.getMessage());
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
            LOGGER.log(Level.WARNING, "PersistentaAngajat: update " + e.getMessage());
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
            LOGGER.log(Level.WARNING, "PersistentaAngajat: delete " + e.getMessage());
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
            LOGGER.log(Level.WARNING, "PersistentaAngajat: reset " + e.getMessage());
        } finally {
            ConnectionFactory.close(resetStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static List<Angajat> getAll() {
        List<Angajat> list = new ArrayList<Angajat>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        Angajat c = null;
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
                c = new Angajat();
                c.setUsername(username);
                c.setRol(rol);
                c.setPass(pass);
                c.setTel(tel);
                c.setEmail(email);
                list.add(c);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaAngajat: getAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(selectStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

    public boolean cautaN(String usrN){
        PersistentaAngajat lista = new PersistentaAngajat();
        for (Angajat x: lista.getAll()) {
            if(usrN.equals(x.getUsername()))
                return true;

        }
        return false;
    }

    public boolean cautaP(String pass){
        PersistentaAngajat lista = new PersistentaAngajat();
        for (Angajat x: lista.getAll()) {
            if(pass.equals(x.getPass()))
                return true;

        }
        return false;
    }
}
