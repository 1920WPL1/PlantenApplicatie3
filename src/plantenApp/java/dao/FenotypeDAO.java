package plantenApp.java.dao;

import plantenApp.java.model.FenoMulti_Eigenschap;
import plantenApp.java.model.Fenotype;
import plantenApp.java.utils.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * @author Siebe
 */
public class FenotypeDAO implements Queries {

    private Connection dbConnection;
    private PreparedStatement stmtSelectFenoByID;
    private PreparedStatement stmtSelectFenoMultiByID;
    private PreparedStatement stmtSelectIdsByFeno;
    private PreparedStatement stmtSelectIdsByFenoMulti;

    public FenotypeDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;
        stmtSelectFenoByID = dbConnection.prepareStatement(GETFENOTYPEBYPLANTID);
        stmtSelectFenoMultiByID = dbConnection.prepareStatement(GETFENOTYPEMULTIBYPLANTID);
        stmtSelectIdsByFeno = dbConnection.prepareStatement(GETIDSBYFENO);
        stmtSelectIdsByFenoMulti = dbConnection.prepareStatement(GETIDSBYFENOMULTI);
    }

    //region GET

    /**
     * @param id -> plant_id
     * @return alle fenotipsche factoren van de specifieke plant
     * @author Siebe
     */
    public Fenotype getById(int id) throws SQLException {
        //Dao

        //Items
        Fenotype feno = null;

        //SqlCommand
        stmtSelectFenoByID.setInt(1, id);
        ResultSet rs = stmtSelectFenoByID.executeQuery();
        if (rs.next()) {
            feno = new Fenotype(
                    rs.getInt("fenotype_id"),
                    rs.getInt("plant_id"),
                    rs.getString("bladvorm"),
                    rs.getString("levensvorm"),
                    rs.getString("habitus"),
                    rs.getString("bloeiwijze"),
                    rs.getInt("bladgrootte"),
                    rs.getString("ratio_bloei_blad"),
                    rs.getString("spruitfenologie"),
                    getByIdMulti(id)
            );
        }

        //Output
        return feno;
    }

    /**
     * @param id -> plant_id
     * @return -> alle fenotype_multi van de specifieke plant
     * @author Siebe
     * word alleen gebruikt in getById
     */
    private ArrayList<FenoMulti_Eigenschap> getByIdMulti(int id) throws SQLException {
        //Dao

        //Items
        ArrayList<FenoMulti_Eigenschap> commMulti = new ArrayList<>();

        //SqlCommand
        stmtSelectFenoMultiByID.setInt(1, id);
        ResultSet rs = stmtSelectFenoMultiByID.executeQuery();
        while (rs.next()) {
            FenoMulti_Eigenschap fenoEigenschap = new FenoMulti_Eigenschap(
                    rs.getInt("fenotype_id"),
                    rs.getString("eigenschap"),
                    rs.getString("jan"),
                    rs.getString("feb"),
                    rs.getString("maa"),
                    rs.getString("apr"),
                    rs.getString("mei"),
                    rs.getString("jun"),
                    rs.getString("jul"),
                    rs.getString("aug"),
                    rs.getString("sep"),
                    rs.getString("okt"),
                    rs.getString("nov"),
                    rs.getString("dec")
            );
            commMulti.add(fenoEigenschap);
        }

        //Output
        return commMulti;
    }

    //endregion

    //region FILTER

    public ArrayList<Integer> FilterOn(List<Integer> plantIds, EnumMap) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByFeno.setString(1, sPlantIds);


        stmtSelectIdsByFeno.setString(2, bladvorm);
        stmtSelectIdsByFeno.setInt(3, DoSearch);

        stmtSelectIdsByFeno.setString(4, levensvorm);
        stmtSelectIdsByFeno.setInt(5, DoSearch);

        stmtSelectIdsByFeno.setString(6, habitus);
        stmtSelectIdsByFeno.setInt(7, DoSearch);

        stmtSelectIdsByFeno.setString(8, bloeiwijze);
        stmtSelectIdsByFeno.setInt(9, DoSearch);

        stmtSelectIdsByFeno.setString(10, bladgrootte);
        stmtSelectIdsByFeno.setInt(11, DoSearch);

        stmtSelectIdsByFeno.setString(12, ratio bloei blad);
        stmtSelectIdsByFeno.setInt(13, DoSearch);

        stmtSelectIdsByFeno.setString(14, spruitfenologie);
        stmtSelectIdsByFeno.setInt(15, DoSearch);

        ResultSet rs = stmtSelectIdsByFeno.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt(0));
        }

        //MultiFilter
        ids = FilterOnMulti("BloeiHoogte", ids);
        ids = FilterOnMulti("BloeiKleur", ids);
        ids = FilterOnMulti("BladKleur", ids);
        ids = FilterOnMulti("Bladhoogte", ids);

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnMulti(String eigenschap, List<Integer> plantIds, EnumMap) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByFenoMulti.setString(1, sPlantIds);

        stmtSelectIdsByFenoMulti.setString(2, eigenschap);

        stmtSelectIdsByFenoMulti.setInt(3, jan);
        stmtSelectIdsByFenoMulti.setInt(4, DoSearch);

        stmtSelectIdsByFenoMulti.setInt(5, feb);
        stmtSelectIdsByFenoMulti.setInt(6, DoSearch);

        stmtSelectIdsByFenoMulti.setInt(7, maa);
        stmtSelectIdsByFenoMulti.setInt(8, DoSearch);

        stmtSelectIdsByFenoMulti.setInt(9, apr);
        stmtSelectIdsByFenoMulti.setInt(10, DoSearch);

        stmtSelectIdsByFenoMulti.setInt(11, mei);
        stmtSelectIdsByFenoMulti.setInt(12, DoSearch);

        stmtSelectIdsByFenoMulti.setInt(13, jun);
        stmtSelectIdsByFenoMulti.setInt(14, DoSearch);

        stmtSelectIdsByFenoMulti.setInt(15, jul);
        stmtSelectIdsByFenoMulti.setInt(16, DoSearch);

        stmtSelectIdsByFenoMulti.setInt(17, aug);
        stmtSelectIdsByFenoMulti.setInt(18, DoSearch);

        stmtSelectIdsByFenoMulti.setInt(19, sept);
        stmtSelectIdsByFenoMulti.setInt(20, DoSearch);

        stmtSelectIdsByFenoMulti.setInt(21, okt);
        stmtSelectIdsByFenoMulti.setInt(22, DoSearch);

        stmtSelectIdsByFenoMulti.setInt(23, nov);
        stmtSelectIdsByFenoMulti.setInt(24, DoSearch);

        stmtSelectIdsByFenoMulti.setInt(25, dec);
        stmtSelectIdsByFenoMulti.setInt(26, DoSearch);

        ResultSet rs = stmtSelectIdsByFenoMulti.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt(0));
        }

        //Output
        return ids;
    }

    //endregion
}

