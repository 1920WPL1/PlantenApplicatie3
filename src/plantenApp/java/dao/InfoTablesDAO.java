package plantenApp.java.dao;

import plantenApp.java.model.InfoTables;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Siebe
 */
public class InfoTablesDAO implements Queries {
    private Connection dbConnection;

    public InfoTablesDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;
    }

    //region GET

    /**
     * @author Siebe
     * @param Query      -> de uit te voeren query
     * @param colomnName -> de naam van de kolom
     * @return -> lijst van strings met alle info van de colomn
     */
    private ArrayList<String> getInfoTableString(String Query, String colomnName) throws SQLException {
        //Dao

        //Items
        ArrayList<String> strings = new ArrayList<>();

        //SqlCommand
        Statement stmt = dbConnection.createStatement();
        ResultSet rs = stmt.executeQuery(Query);
        while (rs.next()) {
            strings.add(rs.getString(colomnName));
        }

        //Output
        return strings;
    }

    /**
     * @author Siebe
     * @param Query      -> de uit te voeren query
     * @param colomnName -> de naam van de kolom
     * @return -> lijst van ints met alle info van de colomn
     */
    private ArrayList<Integer> getInfoTableInt(String Query, String colomnName) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ints = new ArrayList<>();

        //SqlCommand
        Statement stmt = dbConnection.createStatement();
        ResultSet rs = stmt.executeQuery(Query);
        while (rs.next()) {
            ints.add(rs.getInt(colomnName));
        }

        //Output
        return ints;
    }

    /**
     * @author Siebe
     * @param Query      -> de uit te voeren query
     * @param colomnName -> de naam van de kolom
     * @return -> lijst van blobs met alle info van de colomn
     */
    private ArrayList<Blob> getInfoTableBlob(String Query, String colomnName) throws SQLException {

        //Dao

        //Items
        ArrayList<Blob> blobs = new ArrayList<>();

        //SqlCommand
        Statement stmt = dbConnection.createStatement();
        ResultSet rs = stmt.executeQuery(Query);
        while (rs.next()) {
            blobs.add(rs.getBlob(colomnName));
        }

        //Output
        return blobs;
    }

    /**
     * @author bradley
     * @param id type id voor alle families
     * @return lijst van families van meegegeven type

     */
    public ArrayList<String> selectFamilyByType( int id) throws SQLException {
        //Dao
        PreparedStatement stmtSelectFamilyByType = dbConnection.prepareStatement(NTFAMILIEBYTYPE);


        //Items
        ArrayList<String> strings = new ArrayList<>();

        //SqlCommand
       stmtSelectFamilyByType.setInt(1, id);
        ResultSet rs = stmtSelectFamilyByType.executeQuery();
        while (rs.next()) {
            strings.add(rs.getString("familie_naam"));
        }

        //Output
        return strings;
    }

    /**
     * @author Siebe
     * @return -> InfoTables model met alle info van de naakte tabellen
     */
    public InfoTables getInfoTables() throws SQLException {
        InfoTables infoTables = new InfoTables(
                getInfoTableString(NTTYPE, "type_naam"),
                getInfoTableString(NTFAMILIE, "familie_naam"),
                getInfoTableString(NTKLEUREN, "kleur"),
                getInfoTableString(NTBLADGROOTTE, "waarde"),
                getInfoTableString(NTBLADVORM, "waarde"),
                getInfoTableString(NTRATIOBLOEIBLAD, "waarde"),
                getInfoTableString(NTSPRUITFENOLOGIE, "waarde"),
                getInfoTableString(NTBLOEIWIJZE, "waarde"),
                getInfoTableString(NTHABITUS, "waarde"),
                getInfoTableBlob(NTFOTOHABITUS, "afbeelding"),
                getInfoTableString(NTBEZONNING, "waarde"),
                getInfoTableString(NTGRONDSOORT, "waarde"),
                getInfoTableString(NTVOCHTBEHOEFTE, "waarde"),
                getInfoTableString(NTVOEDINGSBEHOEFTE, "waarde"),
                getInfoTableString(NTREACTIEOMGEVING, "waarde"),
                getInfoTableString(NTHABITAT, "waarde"),
                getInfoTableString(NTONTWIKKELINGSSNELHEID, "waarde"),
                getInfoTableString(NTLEVENSDUURCONCURRENTIEKRACHT, "waarde"),
                getInfoTableInt(NTSOCIABILITEIT, "waarde"),
                getInfoTableString(NTSTRATEGIE, "waarde"),
                getInfoTableString(NTBEHEERDAAD, "waarde"),
                getInfoTableInt(NTNECTARWAARDE, "waarde"),
                getInfoTableInt(NTPOLLENWAARDE, "waarde")
        );
        return infoTables;
    }

    //endregion
}
