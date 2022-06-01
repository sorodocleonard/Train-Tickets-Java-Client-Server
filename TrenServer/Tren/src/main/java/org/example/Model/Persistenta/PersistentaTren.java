package org.example.Model.Persistenta;

import org.example.Model.ConnectionFactory;
import org.example.Model.Tren;
import org.json.simple.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistentaTren implements Serializable{
    protected static final Logger LOGGER = Logger.getLogger(PersistentaTren.class.getName());
    private static final String insertStatementString = "INSERT INTO trenuri (nrTren, nrLocuri, statii1, statii2)" + " VALUES (?, ?, ?, ?)";
    private static final String deleteStatementString = "DELETE FROM trenuri where nrTren = ?";
    private static final String resetStatementString = "DELETE * from trenuri";
    private static final String updateStatementString = "UPDATE trenuri SET nrTren = ? , nrLocuri = ? , statii1 = ? , statii2 = ? WHERE nrTren= ?;";
    private static final String selectStatementString = "SELECT * from trenuri";
    private static final String sellStatementString = "UPDATE trenuri SET nrLocuri = nrLocuri - 1 WHERE nrTren= ?;";

    public static void insert(Tren c) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, c.getNumarTren());
            insertStatement.setInt(2, c.getLocuriLibere());
            insertStatement.setString(3, c.getStatii().get(0));
            insertStatement.setString(4, c.getStatii().get(c.getStatii().size()-1));
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaTren: insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void update(int newNrTren, String plecare, String destinatie, int nrLoc, int nrTren) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, newNrTren);
            updateStatement.setInt(2, nrLoc);
            updateStatement.setString(3, plecare);
            updateStatement.setString(4, destinatie);
            updateStatement.setInt(5, nrTren);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaTren: update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static void vinde(int nrTren) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement sellStatement = null;
        try {
            sellStatement = dbConnection.prepareStatement(sellStatementString, Statement.RETURN_GENERATED_KEYS);
            sellStatement.setInt(1, nrTren);
            sellStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaTren: vanzare " + e.getMessage());
        } finally {
            ConnectionFactory.close(sellStatement);
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
            LOGGER.log(Level.WARNING, "PersistentaTren: delete " + e.getMessage());
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
            LOGGER.log(Level.WARNING, "PersistentaTren: reset " + e.getMessage());
        } finally {
            ConnectionFactory.close(resetStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    public static List<Tren> getAll() {
        List<Tren> list = new ArrayList<Tren>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement selectStatement = null;
        ResultSet rs = null;
        Tren c = null;
        try {
            selectStatement = dbConnection.prepareStatement(selectStatementString, Statement.RETURN_GENERATED_KEYS);
            rs = selectStatement.executeQuery();
            int nrTren;
            String plecare;
            String destinatie;
            int  nrLoc;

            while(rs.next()){
                nrTren = rs.getInt("nrTren");
                plecare = rs.getString("statii1");
                destinatie = rs.getString("statii2");
                nrLoc = rs.getInt("nrLocuri");
                c = new Tren(Arrays.asList(new String[]{plecare, destinatie}),nrTren,nrLoc);
                list.add(c);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "PersistentaTren: getAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(selectStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

    public static void raportJson() throws IOException {

        JSONObject jsonObject = new JSONObject();
        PersistentaTren pers = new PersistentaTren();
        FileWriter file = new FileWriter("raport.json");
        for (Tren t: pers.getAll()) {
            jsonObject.put("NumarTren ", String.valueOf(t.getNumarTren()));
            jsonObject.put("Plecare ", t.getStatii().get(0));
            jsonObject.put("Destinatie ", t.getStatii().get(t.getStatii().size()-1));
            jsonObject.put("Locuri Libere ", String.valueOf(t.getLocuriLibere()));
            file.write(jsonObject.toJSONString());
        }
            file.close();

    }
    private static final String CSV_SEPARATOR = ",";
    public static void raportCsv() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("raport.csv"), "UTF-8"));
        PersistentaTren pers = new PersistentaTren();
        for (Tren t: pers.getAll()) {
            StringBuffer oneLine = new StringBuffer();
            oneLine.append(t.getNumarTren());
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getStatii().get(0));
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getStatii().get(t.getStatii().size()-1));
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(t.getLocuriLibere());
            bw.write(oneLine.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();


    }

    public static void raportXml() throws IOException, JAXBException {
        PersistentaTren pers = new PersistentaTren();
        JAXBContext jaxbContext = JAXBContext.newInstance(PersistentaTren.class);

        //Create Marshaller
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        jaxbMarshaller.marshal(pers.getAll(),new File("raportXml.xml"));

    }
}