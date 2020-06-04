package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.utils.ArrayBindings;
import plantenApp.java.utils.Bindings;
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
public class CommensalismeDAO implements Queries {
    private Connection dbConnection;
    private PreparedStatement stmtSelectCommeByID;
    private PreparedStatement stmtSelectCommeMultiByID;
    private PreparedStatement stmtSelectIdsByComm;
    private PreparedStatement stmtSelectIdsByCommMulti;

    public CommensalismeDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectCommeByID = dbConnection.prepareStatement(GETCOMMENSALISMEBYPLANTID);
        stmtSelectCommeMultiByID = dbConnection.prepareStatement(GETCOMMENSALISMEMULTIBYPLANTID);
        stmtSelectIdsByComm = dbConnection.prepareStatement(GETIDSBYCOMM);
        stmtSelectIdsByCommMulti = dbConnection.prepareStatement(GETIDSBYCOMMMULTI);
    }

    //region GET

    /**
     * @param id -> plant_id
     * @return alle abiotische factoren van de specifieke plant
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
                    rs.getInt("commensialisme_id"),
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
     * @return -> alle commensalisme_multi van de specifieke plant
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
                    rs.getInt("commensialisme_id"),
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

    public ArrayList<Integer> FilterOn(List<Integer> plantIds, BindingData bindingData) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByComm.setString(1, sPlantIds);

        //TODO: convert to array
        //stmtSelectIdsByComm.setString(2, bindingData.dataBindings.get(Bindings.STRATEGIE).getValue().get());
        //stmtSelectIdsByComm.setInt(3, (bindingData.dataBindings.get(Bindings.STRATEGIE).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByComm.setString(4, bindingData.dataBindings.get(Bindings.ONTWIKKELINGSSNELHEID).getValue().get());
        stmtSelectIdsByComm.setInt(5, (bindingData.dataBindings.get(Bindings.ONTWIKKELINGSSNELHEID).getDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByComm.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt(1));
        }

        //Multi
        PropertyClass<ValueWithBoolean[]> sociabiliteitData = bindingData.arrayDataBindings.get(ArrayBindings.SOCIABILITEIT);
        if (sociabiliteitData.getDoSearch()) {
            for (int i = 0; i < sociabiliteitData.getValue().length; i++) {
                if (sociabiliteitData.getValue()[i].getBool()) {
                    ids = FilterOnMulti("sociabiliteit",sociabiliteitData.getValue()[i].get(),ids);
                }

            }
        }

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnMulti(String eigenschap, String value, List<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByCommMulti.setString(1, sPlantIds);

        stmtSelectIdsByCommMulti.setString(2, eigenschap);

        stmtSelectIdsByCommMulti.setString(3, value);
        //.setInt(4, DoSearch);

        ResultSet rs = stmtSelectIdsByCommMulti.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt(1));
        }

        //Output
        return ids;
    }

    //endregion
}

