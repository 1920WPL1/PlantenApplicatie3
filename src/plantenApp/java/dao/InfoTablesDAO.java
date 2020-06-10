package plantenApp.java.dao;

import javafx.scene.image.Image;
import plantenApp.java.model.InfoTables;
import plantenApp.java.utils.DaoUtils;

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
     * @param Query      -> de uit te voeren query
     * @param colomnName -> de naam van de kolom
     * @return -> lijst van strings met alle info van de colomn
     * @author Siebe
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
     * @param Query      -> de uit te voeren query
     * @param colomnName -> de naam van de kolom
     * @return -> lijst van ints met alle info van de colomn
     * @author Siebe
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
     * @param Query -> de uit te voeren query* @param colomnName -> de naam van de kolom
     * @return -> lijst van blobs met alle info van de colomn
     * @author Siebe
     */
    private ArrayList<Image> getInfoTableBlob(String Query, String colomnName) throws SQLException {

        //Dao

        //Items
        ArrayList<Image> img = new ArrayList<>();

        //SqlCommand
        Statement stmt = dbConnection.createStatement();
        ResultSet rs = stmt.executeQuery(Query);
        while (rs.next()) {
            InputStream input = rs.getBinaryStream(colomnName);
            Image image = new Image(input);
            img.add(image);
        }

        //Output
        return img;
    }

    /**
     * @return -> InfoTables model met alle info van de naakte tabellen
     * @author Siebe
     */
    public InfoTables getInfoTables() throws SQLException {
        InfoTables infoTables = new InfoTables(
                getInfoTableString(NTTYPE, "planttype_naam"),
                getInfoTableString(NTFAMILIE, "familie_naam"),
                getInfoTableString(NTGESLACHT, "geslacht_naam"),
                getInfoTableString(NTSOORT, "soort_naam"),
                getInfoTableString(NTVARIATIE, "variatie_naam"),
                getInfoTableString(NTKLEUREN, "kleur"),
                getInfoTableString(NTBLADGROOTTE, "waarde"),
                getInfoTableString(NTBLADVORM, "waarde"),
                getInfoTableString(NTRATIOBLOEIBLAD, "waarde"),
                getInfoTableString(NTSPRUITFENOLOGIE, "waarde"),
                getInfoTableString(NTBLOEIWIJZE, "waarde"),
                getInfoTableBlob(NTFOTOBLOEIWIJZE,"afbeelding"),
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
