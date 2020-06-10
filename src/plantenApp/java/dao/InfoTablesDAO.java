package plantenApp.java.dao;

import javafx.scene.image.Image;
import plantenApp.java.model.InfoTables;
import plantenApp.java.model.NTFotos;
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
     * @author Siebe
     * @return -> InfoTables model met alle info van de naakte tabellen
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
                getInfoTableString(NTHABITUS, "waarde"),
                getInfoTableBlob(NTFOTOHABITUS,"afbeelding"),
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

    public ArrayList<String> selectFamiliesByType(ArrayList<String> types) throws SQLException {
        //Items
        ArrayList<String> families = new ArrayList<>();

        if (types.size() == 0){
            return families;
        }
        //Dao
        PreparedStatement stmtSelectFamilyByType = DaoUtils.ReadyStatement(dbConnection,NTFAMILIEBYTYPE,types);

        //SqlCommand
        ResultSet rs = stmtSelectFamilyByType.executeQuery();
        while (rs.next()) {
            families.add(rs.getString("familie_naam"));
        }

        //Output
        return families;
    }
    public ArrayList<String> selectGeslachtenByFamilie(ArrayList<String> families) throws SQLException {
        //Items
        ArrayList<String> geslachten = new ArrayList<>();

        if (families.size() == 0){
            return geslachten;
        }
        //Dao
        PreparedStatement stmtSelectGeslachtByFamily = DaoUtils.ReadyStatement(dbConnection,NTGESLACHTBYFAMILIE,families);

        //SqlCommand
        ResultSet rs = stmtSelectGeslachtByFamily.executeQuery();
        while (rs.next()) {
            geslachten.add(rs.getString("geslacht_naam"));
        }

        //Output
        return geslachten;
    }
    public ArrayList<String> selectSoortenByGeslacht(ArrayList<String> geslachten) throws SQLException {
        //Items
        ArrayList<String> soorten = new ArrayList<>();

        if (geslachten.size() == 0){
            return soorten;
        }
        //Dao
        PreparedStatement stmtSelectSoortByGeslacht = DaoUtils.ReadyStatement(dbConnection,NTSOORTBYGESLACHT,geslachten);

        //SqlCommand
        ResultSet rs = stmtSelectSoortByGeslacht.executeQuery();
        while (rs.next()) {
            soorten.add(rs.getString("soort_naam"));
        }

        //Output
        return soorten;
    }
    public ArrayList<String> selectVariantenBySoort(ArrayList<String> soorten) throws SQLException {
        //Items
        ArrayList<String> varianten = new ArrayList<>();

        if (soorten.size() == 0){
            return varianten;
        }
        //Dao
        PreparedStatement stmtSelectVariantBySoort = DaoUtils.ReadyStatement(dbConnection,NTVARIATIEBYSOORT,soorten);

        //SqlCommand
        ResultSet rs = stmtSelectVariantBySoort.executeQuery();
        while (rs.next()) {
            varianten.add(rs.getString("variatie_naam"));
        }

        System.out.println(varianten.toString());

        //Output
        return varianten;
    }
}
