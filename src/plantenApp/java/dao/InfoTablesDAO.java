package plantenApp.java.dao;

import javafx.scene.image.Image;
import plantenApp.java.model.InfoTables;
import plantenApp.java.model.NTFotos;

import java.io.InputStream;
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

     * @param Query      -> de uit te voeren query* @param colomnName -> de naam van de kolom
     * @return -> lijst van blobs met alle info van de colomn
     */
    private ArrayList<NTFotos> getInfoTableBlob(String Query) throws SQLException {

        //Dao

        //Items
        ArrayList<NTFotos> img = new ArrayList<>();

        //SqlCommand
        Statement stmt = dbConnection.createStatement();
        ResultSet rs = stmt.executeQuery(Query);
        while (rs.next()) {

                InputStream input = rs.getBinaryStream(2);
                Image image = new Image(input);
                img.add(new NTFotos(rs.getString(1), image));



        }

        //Output
        return img;
    }

    /**
     * @author bradley
     * @param type type voor op te filteren
     * @return lijst van families van meegegeven type

     */
    public ArrayList<String> selectFamiliesByType(String type) throws SQLException {
        //Dao
        PreparedStatement stmtSelectFamilyByType = dbConnection.prepareStatement(NTFAMILIEBYTYPE);


        //Items
        ArrayList<String> families = new ArrayList<>();

        //SqlCommand
       stmtSelectFamilyByType.setString(1, type);
        ResultSet rs = stmtSelectFamilyByType.executeQuery();
        while (rs.next()) {
            families.add(rs.getString("familie_naam"));
        }

        //Output
        return families;
    }

    /**
     * @author Siebe
     * @return -> InfoTables model met alle info van de naakte tabellen
     */
    public InfoTables getInfoTables() throws SQLException {
        InfoTables infoTables = new InfoTables(
                getInfoTableString(NTTYPE, "planttype_naam"),
                getInfoTableString(NTFAMILIE, "familie_naam"),
                getInfoTableString(NTKLEUREN, "kleur"),
                getInfoTableString(NTBLADGROOTTE, "waarde"),
                getInfoTableString(NTBLADVORM, "waarde"),
                getInfoTableString(NTRATIOBLOEIBLAD, "waarde"),
                getInfoTableString(NTSPRUITFENOLOGIE, "waarde"),
                getInfoTableString(NTBLOEIWIJZE, "waarde"),
                getInfoTableString(NTHABITUS, "waarde"),
                getInfoTableBlob(NTFOTOHABITUS),
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
