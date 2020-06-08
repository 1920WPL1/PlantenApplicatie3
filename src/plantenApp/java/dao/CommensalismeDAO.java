package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.utils.ERequestArrayData;
import plantenApp.java.utils.ERequestData;
import plantenApp.java.utils.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Siebe
 */
public class CommensalismeDAO implements Queries {
    private Connection dbConnection;
    private PreparedStatement stmtSelectCommeByID;
    private PreparedStatement stmtSelectCommeMultiByID;
    private PreparedStatement stmtSelectIdsByCommMulti;

    public CommensalismeDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectCommeByID = dbConnection.prepareStatement(GETCOMMENSALISMEBYPLANTID);
        stmtSelectCommeMultiByID = dbConnection.prepareStatement(GETCOMMENSALISMEMULTIBYPLANTID);
        stmtSelectIdsByCommMulti = dbConnection.prepareStatement(GETIDSBYCOMMMULTI);
    }

    //region GET

    /**
     * @param id -> plant_id
     * @return alle Commensalisme van de specifieke plant
     * @author Siebe
     */
    public Commensalisme getById(int id) throws SQLException {
        //Dao

        //Items
        Commensalisme comm = null;

        //SqlCommand
        stmtSelectCommeByID.setInt(1, id);
        ResultSet rs = stmtSelectCommeByID.executeQuery();
        if (rs.next()) {
            comm = new Commensalisme(
                    rs.getInt("commensalisme_id"),
                    rs.getInt("plant_id"),
                    rs.getString("strategie"),
                    rs.getString("ontwikkelingssnelheid"),
                    getByIdMulti(id)
            );
        }

        //Output
        return comm;
    }

    /**
     * @param id -> plant_id
     * @return alle commensalisme_multi van de specifieke plant
     * @author Siebe
     * word alleen gebruikt in getById
     */
    private ArrayList<CommMulti_Eigenschap> getByIdMulti(int id) throws SQLException {
        //Dao

        //Items
        ArrayList<CommMulti_Eigenschap> commMulti = new ArrayList<>();

        //SqlCommand
        stmtSelectCommeMultiByID.setInt(1, id);
        ResultSet rs = stmtSelectCommeMultiByID.executeQuery();
        while (rs.next()) {
            CommMulti_Eigenschap commEigenschap = new CommMulti_Eigenschap(
                    rs.getInt("commensalisme_id"),
                    rs.getString("eigenschap"),
                    rs.getString("waarde")
            );
            commMulti.add(commEigenschap);
        }

        //Output
        return commMulti;
    }

    //endregion

    //region FILTER

    /**
     * @author Siebe
     * @param plantIds -> The ids that need to be filtered
     * @param bindingData -> dataClass that consist of all the data of the bindings
     * @return The filtered ids
     */
    public ArrayList<Integer> FilterOn(ArrayList<Integer> plantIds, BindingData bindingData) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByComm = DaoUtils.ReadyStatement(dbConnection, GETIDSBYCOMM, plantIds);

        //Strategie
        SearchRequest<RequestValueWBool[]> strategie = bindingData.searchRequestArrayData.get(ERequestArrayData.STRATEGIE);
        stmtSelectIdsByComm.setString(plantIds.size() + 1, DaoUtils.GetCheckedValue(strategie.Value()));
        stmtSelectIdsByComm.setInt(plantIds.size() + 2, (strategie.getDoSearch()) ? 0 : 1);

        //ontwikkelingssnelheid
        SearchRequest<RequestValue> ontwikkelingssnelheid = bindingData.searchRequestData.get(ERequestData.ONTWIKKELINGSSNELHEID);
        stmtSelectIdsByComm.setString(plantIds.size() + 3, ontwikkelingssnelheid.Value().getValue());
        stmtSelectIdsByComm.setInt(plantIds.size() + 4, (ontwikkelingssnelheid.getDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByComm.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Multi
        SearchRequest<RequestValueWBool[]> sociabiliteit = bindingData.searchRequestArrayData.get(ERequestArrayData.SOCIABILITEIT);
        if (sociabiliteit.getDoSearch()) {
            ids = FilterOnMulti("sociabiliteit", DaoUtils.GetCheckedValue(sociabiliteit.Value()), ids);
        }

        //Output
        return ids;
    }

    /**
     * @author Siebe
     * @param eigenschap -> name of the property to filter on
     * @param value -> value that the property should have
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnMulti(String eigenschap, String value, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //makes the prepared statement en fills in de IN (?)
        PreparedStatement stmtSelectIdsByCommMulti = DaoUtils.ReadyStatement(dbConnection, GETIDSBYCOMMMULTI, plantIds);

        //SQLcommand
        stmtSelectIdsByCommMulti.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByCommMulti.setString(plantIds.size() + 2, value);

        ResultSet rs = stmtSelectIdsByCommMulti.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    //endregion
}

