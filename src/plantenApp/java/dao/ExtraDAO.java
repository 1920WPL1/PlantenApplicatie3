package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.model.data.GUIdata;
import plantenApp.java.model.data.RadiogroupData;
import plantenApp.java.model.data.SliderLabelData;
import plantenApp.java.model.data.enums.ERadiogroup;
import plantenApp.java.model.data.enums.ESliderLabel;
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
     * @param guiData
     * @return The filtered ids
     */
    public ArrayList<Integer> FilterOn(ArrayList<Integer> plantIds, BindingData bindingData, GUIdata guiData) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByExtra = DaoUtils.ReadyStatement(dbConnection, GETIDSBYEXTRA, plantIds);


        //nectarwaarde
        SliderLabelData nectarwaarde = guiData.sliderLabelDEM.get(ESliderLabel.NECTARWAARDE);
        //SearchRequest<RequestValue> nectarwaarde = bindingData.searchRequestData.get(ERequestData.NECTARWAARDE);
        stmtSelectIdsByExtra.setInt(plantIds.size() + 1, nectarwaarde.getValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 2, (nectarwaarde.isDoSearch()) ? 0 : 1);

        //pollenwaard
        SliderLabelData pollenwaarde = guiData.sliderLabelDEM.get(ESliderLabel.POLLENWAARDE);
        //SearchRequest<RequestValue> pollenwaarde = bindingData.searchRequestData.get(ERequestData.POLLENWAARDE);
        stmtSelectIdsByExtra.setInt(plantIds.size() + 3, pollenwaarde.getValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 4, (pollenwaarde.isDoSearch()) ? 0 : 1);

        //vlindervriendelijk
        RadiogroupData vlindervriendelijk = guiData.radiogroupDEM.get(ERadiogroup.VLINDERVRIENDELIJK);
        //SearchRequest<RequestValueWBool[]> bijvriendelijk = bindingData.searchRequestArrayData.get(ERequestArrayData.BIJVRIENDELIJK);
        stmtSelectIdsByExtra.setString(plantIds.size() + 5, vlindervriendelijk.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 6, (vlindervriendelijk.isDoSearch()) ? 0 : 1);

        //bijvriendelijk
        RadiogroupData bijvriendelijk = guiData.radiogroupDEM.get(ERadiogroup.BIJVRIENDELIJK);
        //SearchRequest<RequestValueWBool[]> bijvriendelijk = bindingData.searchRequestArrayData.get(ERequestArrayData.BIJVRIENDELIJK);
        stmtSelectIdsByExtra.setString(plantIds.size() + 6, bijvriendelijk.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 7, (bijvriendelijk.isDoSearch()) ? 0 : 1);

        //Eetbaar
        RadiogroupData eetbaar = guiData.radiogroupDEM.get(ERadiogroup.EETBAAR);
        //SearchRequest<RequestValueWBool[]> eetbaar = bindingData.searchRequestArrayData.get(ERequestArrayData.EETBAAR);
        stmtSelectIdsByExtra.setString(plantIds.size() + 8, eetbaar.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 9, (eetbaar.isDoSearch()) ? 0 : 1);

        //kruidgebruik
        RadiogroupData kruidgebruik = guiData.radiogroupDEM.get(ERadiogroup.KRUIDGEBRUIK);
        //SearchRequest<RequestValueWBool[]> kruidgebruik = bindingData.searchRequestArrayData.get(ERequestArrayData.KRUIDGEBRUIK);
        stmtSelectIdsByExtra.setString(plantIds.size() + 10, kruidgebruik.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 11, (kruidgebruik.isDoSearch()) ? 0 : 1);

        //geurend
        RadiogroupData geurend = guiData.radiogroupDEM.get(ERadiogroup.GEUREND);
        //SearchRequest<RequestValueWBool[]> geurend = bindingData.searchRequestArrayData.get(ERequestArrayData.GEUREND);
        stmtSelectIdsByExtra.setString(plantIds.size() + 12, geurend.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 13, (geurend.isDoSearch()) ? 0 : 1);

        //vorstgevoelig
        RadiogroupData vorstgevoelig = guiData.radiogroupDEM.get(ERadiogroup.VORSTGEVOELIG);
        //SearchRequest<RequestValueWBool[]> vorstgevoelig = bindingData.searchRequestArrayData.get(ERequestArrayData.VORSTGEVOELIG);
        stmtSelectIdsByExtra.setString(plantIds.size() + 14, vorstgevoelig.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 15, (vorstgevoelig.isDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByExtra.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }



        //Output
        return ids;


    }

    //endregion


}
