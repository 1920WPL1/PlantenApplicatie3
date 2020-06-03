package plantenApp.java.dao;

import plantenApp.java.model.AbioMulti_Eigenschap;
import plantenApp.java.model.AbiotischeFactoren;
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
public class AbiotischeFactorenDAO implements Queries {
    private Connection dbConnection;
    private PreparedStatement stmtSelectAbioByID;
    private PreparedStatement stmtSelectAbioMultiByID;
    private PreparedStatement stmtSelectIdsByAbio;
    private PreparedStatement stmtSelectIdsByAbioMulti;

    public AbiotischeFactorenDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectAbioByID = dbConnection.prepareStatement(GETABIOTISCHBYPLANTID);
        stmtSelectAbioMultiByID = dbConnection.prepareStatement(GETABIOTISCHBMULTIYPLANTID);
        stmtSelectIdsByAbio = dbConnection.prepareStatement(GETIDSBYABIO);
        stmtSelectIdsByAbioMulti = dbConnection.prepareStatement(GETIDSBYABIOMULTI);
    }

    //region GET

    /**
     * @param id -> plant_id
     * @return -> alle abiotische factoren van de specifieke plant
     * @author Siebe
     */
    public AbiotischeFactoren getById(int id) throws SQLException {
        //Dao

        //Items
        AbiotischeFactoren abio = null;

        //SqlCommand
        stmtSelectAbioByID.setInt(1, id);
        ResultSet rs = stmtSelectAbioByID.executeQuery();
        if (rs.next()) {
            abio = new AbiotischeFactoren(
                    rs.getInt("abiotische_id"),
                    rs.getInt("plant_id"),
                    rs.getString("bezonning"),
                    rs.getString("grondsoort"),
                    rs.getString("vochtbehoefte"),
                    rs.getString("voedingsbehoefte"),
                    rs.getString("reactie_antagonistische_omg"),
                    getByIdMulti(id)
            );
        }

        //Output
        return abio;
    }

    /**
     * @param id -> plant_id
     * @return -> alle abiotische_multi factoren van de specifieke plant
     * @author Siebe
     * word alleen gebruikt in getById
     */
    private ArrayList<AbioMulti_Eigenschap> getByIdMulti(int id) throws SQLException {
        //Dao

        //Items
        ArrayList<AbioMulti_Eigenschap> abioMulti = new ArrayList<>();

        //SqlCommand
        stmtSelectAbioMultiByID.setInt(1, id);
        ResultSet rs = stmtSelectAbioMultiByID.executeQuery();
        while (rs.next()) {
            AbioMulti_Eigenschap abioEigenschap = new AbioMulti_Eigenschap(
                    rs.getInt("abiotische_id"),
                    rs.getString("eigenschap"),
                    rs.getString("waarde")
            );
            abioMulti.add(abioEigenschap);
        }

        //Output
        return abioMulti;
    }

    //endregion

    //region FILTER

    public ArrayList<Integer> FilterOn(List<Integer> plantIds, EnumMap) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByAbio.setString(1, sPlantIds);

        stmtSelectIdsByAbio.setString(2, bezonning);
        stmtSelectIdsByAbio.setString(3, DoSearch);

        stmtSelectIdsByAbio.setString(4, grondsoort);
        stmtSelectIdsByAbio.setString(5, DoSearch);

        stmtSelectIdsByAbio.setString(6, vochtbehoefte);
        stmtSelectIdsByAbio.setString(7, DoSearch);

        stmtSelectIdsByAbio.setString(8, voedingsbehoefte);
        stmtSelectIdsByAbio.setString(9, DoSearch);

        stmtSelectIdsByAbio.setString(10, reactie_antagonistische_omg);
        stmtSelectIdsByAbio.setInt(11, DoSearch);

        ResultSet rs = stmtSelectIdsByAbio.executeQuery();
        while (rs.next()){
            ids.add(rs.getInt(0));
        }

        ids = FilterOnMulti("habitat",ids,habitat);

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnMulti(String eigenschap,List<Integer> plantIds, EnumMap) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByAbioMulti.setString(1, sPlantIds);

        stmtSelectIdsByAbioMulti.setString(2, eigenschap);

        stmtSelectIdsByAbioMulti.setInt(3, waarde);
        stmtSelectIdsByAbioMulti.setInt(4, DoSearch);

        ResultSet rs = stmtSelectIdsByAbioMulti.executeQuery();
        while (rs.next()){
            ids.add(rs.getInt(0));
        }

        //Output
        return ids;
    }

    //endregion

}
