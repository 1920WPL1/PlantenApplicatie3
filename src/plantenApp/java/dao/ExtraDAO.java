package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.model.data.ComboBoxData;
import plantenApp.java.model.data.GUIdata;
import plantenApp.java.model.data.RadiogroupData;
import plantenApp.java.model.data.SliderLabelData;
import plantenApp.java.model.data.enums.EComboBox;
import plantenApp.java.model.data.enums.ERadiogroup;
import plantenApp.java.model.data.enums.ESliderLabel;
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
     * @param guiData
     * @return The filtered ids
     */
    public ArrayList<Integer> FilterOn(ArrayList<Integer> plantIds, GUIdata guiData) throws SQLException {
        ArrayList<Integer> ids = new ArrayList<>();

        SliderLabelData nectarwaarde = guiData.sliderLabelDEM.get(ESliderLabel.NECTARWAARDE);
        SliderLabelData pollenwaarde = guiData.sliderLabelDEM.get(ESliderLabel.POLLENWAARDE);
        RadiogroupData vlindervriendelijk = guiData.radiogroupDEM.get(ERadiogroup.VLINDERVRIENDELIJK);
        RadiogroupData bijvriendelijk = guiData.radiogroupDEM.get(ERadiogroup.BIJVRIENDELIJK);
        RadiogroupData eetbaar = guiData.radiogroupDEM.get(ERadiogroup.EETBAAR);
        RadiogroupData kruidgebruik = guiData.radiogroupDEM.get(ERadiogroup.KRUIDGEBRUIK);
        RadiogroupData geurend = guiData.radiogroupDEM.get(ERadiogroup.GEUREND);
        RadiogroupData vorstgevoelig = guiData.radiogroupDEM.get(ERadiogroup.VORSTGEVOELIG);

        QueryBuilder QB = new QueryBuilder("plant_id", "extra");

        QB.AddIN("plant_id",plantIds);

        if (nectarwaarde.isDoSearch()) QB.AddBasicInt("nectarwaarde", nectarwaarde.getValue());
        if (pollenwaarde.isDoSearch()) QB.AddBasicInt("pollenwaarde", pollenwaarde.getValue());
        if (vlindervriendelijk.isDoSearch()) QB.AddBasicString("vlindervriendelijk", vlindervriendelijk.getActualValue());
        if (bijvriendelijk.isDoSearch()) QB.AddBasicString("bijvriendelijk", bijvriendelijk.getActualValue());
        if (eetbaar.isDoSearch()) QB.AddBasicString("eetbaar", eetbaar.getActualValue());
        if (kruidgebruik.isDoSearch()) QB.AddBasicString("kruidgebruik", kruidgebruik.getActualValue());
        if (geurend.isDoSearch()) QB.AddBasicString("geurend", geurend.getActualValue());
        if (vorstgevoelig.isDoSearch()) QB.AddBasicString("vorstgevoelig", vorstgevoelig.getActualValue());

        System.out.println(QB.getQuery());

        ResultSet rs = QB.PrepareStatement(dbConnection).executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        /*
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByExtra = DaoUtils.ReadyStatement(dbConnection, GETIDSBYEXTRA, plantIds);


        //nectarwaarde
        SliderLabelData nectarwaarde = guiData.sliderLabelDEM.get(ESliderLabel.NECTARWAARDE);
        stmtSelectIdsByExtra.setInt(plantIds.size() + 1, nectarwaarde.getValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 2, (nectarwaarde.isDoSearch()) ? 0 : 1);

        //pollenwaard
        SliderLabelData pollenwaarde = guiData.sliderLabelDEM.get(ESliderLabel.POLLENWAARDE);
        stmtSelectIdsByExtra.setInt(plantIds.size() + 3, pollenwaarde.getValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 4, (pollenwaarde.isDoSearch()) ? 0 : 1);

        //vlindervriendelijk
        RadiogroupData vlindervriendelijk = guiData.radiogroupDEM.get(ERadiogroup.VLINDERVRIENDELIJK);
        stmtSelectIdsByExtra.setString(plantIds.size() + 5, vlindervriendelijk.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 6, (vlindervriendelijk.isDoSearch()) ? 0 : 1);

        //bijvriendelijk
        RadiogroupData bijvriendelijk = guiData.radiogroupDEM.get(ERadiogroup.BIJVRIENDELIJK);
        stmtSelectIdsByExtra.setString(plantIds.size() + 7, bijvriendelijk.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 8, (bijvriendelijk.isDoSearch()) ? 0 : 1);

        //Eetbaar
        RadiogroupData eetbaar = guiData.radiogroupDEM.get(ERadiogroup.EETBAAR);
        stmtSelectIdsByExtra.setString(plantIds.size() + 9, eetbaar.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 10, (eetbaar.isDoSearch()) ? 0 : 1);

        //kruidgebruik
        RadiogroupData kruidgebruik = guiData.radiogroupDEM.get(ERadiogroup.KRUIDGEBRUIK);
        stmtSelectIdsByExtra.setString(plantIds.size() + 11, kruidgebruik.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 12, (kruidgebruik.isDoSearch()) ? 0 : 1);

        //geurend
        RadiogroupData geurend = guiData.radiogroupDEM.get(ERadiogroup.GEUREND);
        stmtSelectIdsByExtra.setString(plantIds.size() + 13, geurend.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 14, (geurend.isDoSearch()) ? 0 : 1);

        //vorstgevoelig
        RadiogroupData vorstgevoelig = guiData.radiogroupDEM.get(ERadiogroup.VORSTGEVOELIG);
        stmtSelectIdsByExtra.setString(plantIds.size() + 15, vorstgevoelig.getActualValue());
        stmtSelectIdsByExtra.setInt(plantIds.size() + 16, (vorstgevoelig.isDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByExtra.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

         */



        //Output
        return ids;


    }

    //endregion


}
