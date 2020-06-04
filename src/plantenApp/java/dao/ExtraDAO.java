package plantenApp.java.dao;

import plantenApp.java.model.BindingData;
import plantenApp.java.model.Extra;
import plantenApp.java.model.PropertyClass;
import plantenApp.java.model.Value;
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
                    rs.getString("eetbaar_kruidgebruik"),
                    rs.getString("eetbaar_kruidgebruik"),
                    rs.getString("geurend"),
                    rs.getString("vorstgevoelig")
            );
        }

        //Output
        return extra;
    }

    //endregion

    //region FILTER

    public ArrayList<Integer> FilterOn(ArrayList<Integer> plantIds, BindingData bindingData) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByExtra = DaoUtils.ReadyStatement(dbConnection, GETIDSBYEXTRA, plantIds);

        //nectarwaarde
        PropertyClass<Value> nectarwaarde = bindingData.dataBindings.get(Bindings.NECTARWAARDE);
        stmtSelectIdsByExtra.setString(plantIds.size() + 1, DaoUtils.GetCheckedValue(nectarwaarde.getValue()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 2, (nectarwaarde.getDoSearch()) ? 0 : 1);

        //nectarwaarde
        PropertyClass<Value> pollenwaarde = bindingData.dataBindings.get(Bindings.POLLENWAARDE);
        stmtSelectIdsByExtra.setString(plantIds.size() + 3, DaoUtils.GetCheckedValue(pollenwaarde.getValue()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 4, (pollenwaarde.getDoSearch()) ? 0 : 1);

        /*
        //bijvriendelijk
        PropertyClass<Value> bijvriendelijk = bindingData.arrayDataBindings.get(ArrayBindings.BIJVRIENDELIJK);
        stmtSelectIdsByExtra.setString(plantIds.size() + 5, DaoUtils.GetCheckedValue(bijvriendelijk.getValue()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 6, (bijvriendelijk.getDoSearch()) ? 0 : 1);

        //Eetbaar
        PropertyClass<Value> eetbaar = bindingData.arrayDataBindings.get(ArrayBindings.EETBAAR);
        stmtSelectIdsByExtra.setString(plantIds.size() + 7, DaoUtils.GetCheckedValue(eetbaar.getValue()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 8, (eetbaar.getDoSearch()) ? 0 : 1);

        //kruidgebruik
        PropertyClass<Value> kruidgebruik = bindingData.arrayDataBindings.get(ArrayBindings.KRUIDGEBRUIK);
        stmtSelectIdsByExtra.setString(plantIds.size() + 9, DaoUtils.GetCheckedValue(kruidgebruik.getValue()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 10, (kruidgebruik.getDoSearch()) ? 0 : 1);

        //geurend
        PropertyClass<Value> geurend = bindingData.arrayDataBindings.get(ArrayBindings.GEUREND);
        stmtSelectIdsByExtra.setString(plantIds.size() + 11, DaoUtils.GetCheckedValue(geurend.getValue()));
        stmtSelectIdsByExtra.setInt(plantIds.size() + 12, (geurend.getDoSearch()) ? 0 : 1);

        //vorstgevoelig
        PropertyClass<Value> vorstgevoelig = bindingData.arrayDataBindings.get(ArrayBindings.VORSTGEVOELIG);
        stmtSelectIdsByExtra.setString(plantIds.size() + 13, DaoUtils.GetCheckedValue(vorstgevoelig.getValue()));
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
