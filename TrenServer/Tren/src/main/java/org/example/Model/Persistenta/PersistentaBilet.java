package org.example.Model.Persistenta;

import org.example.Model.Bilet;
import org.example.Model.ConnectionFactory;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PersistentaBilet implements Serializable {
    protected static final Logger LOGGER = Logger.getLogger(PersistentaBilet.class.getName());
    private static final String insertStatementString = "INSERT INTO bilete (nrTren, plecare, destinatie, pret)" + " VALUES (?, ?, ?, ?)";
    private static final String deleteStatementString = "DELETE FROM bilete where nrTren = ?";
    private static final String resetStatementString = "DELETE * from bilete";
    private static final String updateStatementString = "UPDATE bilete SET nrTren = ? , plecare = ? , destiantie = ? , pret = ? WHERE nrTren= ?;";
    private static final String selectStatementString = "SELECT * from bilete";

    public static void insert(Bilet c) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, c.getNumarTren());
            insertStatement.setString(2, c.getPlecare());
            insertStatement.setString(3, c.getDestinatie());
            insertStatement.setFloat(4, c.getPret());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaBilet: insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void update(int newNrTren, String plecare, String destinatie, float pret, int nrTren) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, newNrTren);
            updateStatement.setString(2, plecare);
            updateStatement.setString(3, destinatie);
            updateStatement.setFloat(4, pret);
            updateStatement.setInt(5, nrTren);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaBilet: update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void delete(int nrTren) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, nrTren);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaBilet: delete " + e.getMessage());
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
            LOGGER.log(Level.WARNING, "PersistentaBilet: reset " + e.getMessage());
        } finally {
            ConnectionFactory.close(resetStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static List<Bilet> getAll() {
        List<Bilet> list = new ArrayList<Bilet>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        Bilet c = null;
        try {
            selectStatement = dbConnection.prepareStatement(selectStatementString, Statement.RETURN_GENERATED_KEYS);
            rs = selectStatement.executeQuery();
            int nrTren;
            String plecare;
            String destinatie;
            float  pret;

            while(rs.next()){
                nrTren = rs.getInt("nrTren");
                plecare = rs.getString("plecare");
                destinatie = rs.getString("destinatie");
                pret = rs.getFloat("pret");
                c = new Bilet(plecare,destinatie,nrTren,pret);
                list.add(c);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaBilet: getAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(selectStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }
}