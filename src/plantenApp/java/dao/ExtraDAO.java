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
public class ExtraDAO implements Queries {
    private Connection dbConnection;
    private PreparedStatement stmtSelectExtraByID;

    public ExtraDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectExtraByID = dbConnection.prepareStatement(GETEXTRABYPLANTID);
    }

    //region GET

    /**
     * @param id -> plant_id
     * @return -> alle extra kenmerken van de specifieke plant
     * @author Siebe
     */
    public Extra getExtraById(int id) throws SQLException {
        //Dao

        //Items
        Extra extra = null;

        //SqlCommand
        stmtSelectExtraByID.setInt(1, id);
        ResultSet rs = stmtSelectExtraByID.executeQuery();
        if (rs.next()) {
            extra = new Extra(
                    rs.getInt("extra_id"),
                    rs.getInt("plant_id"),
                    rs.getInt("nectarwaarde"),
                    rs.getInt("pollenwaarde"),
                    rs.getString("bijvriendelijk"),
                    rs.getString("eetbaar"),
                    rs.getString("kruidgebruik"),
                    rs.getString("geurend"),
                    rs.getString("vorstgevoelig"),
                    rs.getString("vlindervriendelijk")
            );
        }

        //Output
        return extra;
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
        PreparedStatement stmtSelectIdsByExtra = DaoUtils.ReadyStatement(dbConnection, GETIDSBYEXTRA, plantIds);


        //nectarwaarde
        SearchRequest<RequestValue> nectarwaarde = bindingData.searchRequestData.get(ERequestData.NECTARWAARDE);
        stmtSelectIdsByExtra.setInt(plantIds.size() + 1, (int) Double.parseDouble(nectarwaarde.Value().getValue()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 2, (nectarwaarde.getDoSearch()) ? 0 : 1);

        //nectarwaarde
        SearchRequest<RequestValue> pollenwaarde = bindingData.searchRequestData.get(ERequestData.POLLENWAARDE);
        stmtSelectIdsByExtra.setInt(plantIds.size() + 3, (int) Double.parseDouble(pollenwaarde.Value().getValue()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 4, (pollenwaarde.getDoSearch()) ? 0 : 1);



        //bijvriendelijk
        SearchRequest<RequestValueWBool[]> bijvriendelijk = bindingData.searchRequestArrayData.get(ERequestArrayData.BIJVRIENDELIJK);
        stmtSelectIdsByExtra.setString(plantIds.size() + 5, DaoUtils.GetCheckedValue(bijvriendelijk.Value()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 6, (bijvriendelijk.getDoSearch()) ? 0 : 1);

        //Eetbaar
        SearchRequest<RequestValueWBool[]> eetbaar = bindingData.searchRequestArrayData.get(ERequestArrayData.EETBAAR);
        stmtSelectIdsByExtra.setString(plantIds.size() + 7, DaoUtils.GetCheckedValue(eetbaar.Value()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 8, (eetbaar.getDoSearch()) ? 0 : 1);

        //kruidgebruik
        SearchRequest<RequestValueWBool[]> kruidgebruik = bindingData.searchRequestArrayData.get(ERequestArrayData.KRUIDGEBRUIK);
        stmtSelectIdsByExtra.setString(plantIds.size() + 9, DaoUtils.GetCheckedValue(kruidgebruik.Value()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 10, (kruidgebruik.getDoSearch()) ? 0 : 1);

        //geurend
        SearchRequest<RequestValueWBool[]> geurend = bindingData.searchRequestArrayData.get(ERequestArrayData.GEUREND);
        stmtSelectIdsByExtra.setString(plantIds.size() + 11, DaoUtils.GetCheckedValue(geurend.Value()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 12, (geurend.getDoSearch()) ? 0 : 1);

        //vorstgevoelig
        SearchRequest<RequestValueWBool[]> vorstgevoelig = bindingData.searchRequestArrayData.get(ERequestArrayData.VORSTGEVOELIG);
        stmtSelectIdsByExtra.setString(plantIds.size() + 13, DaoUtils.GetCheckedValue(vorstgevoelig.Value()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 14, (vorstgevoelig.getDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByExtra.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }



        //Output
        return ids;


    }

    //endregion


}
