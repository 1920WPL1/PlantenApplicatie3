package plantenApp.java.dao;

import plantenApp.java.model.BindingData;
import plantenApp.java.model.Extra;
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
    private PreparedStatement stmtSelectIdsByExtra;

    public ExtraDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectExtraByID = dbConnection.prepareStatement(GETEXTRABYPLANTID);
        stmtSelectIdsByExtra = dbConnection.prepareStatement(GETIDSBYEXTRA);
    }

    //region GET

    /**
     * @author Siebe
     * @param id -> plant_id
     * @return -> alle extra kenmerken van de specifieke plant
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

    public ArrayList<Integer> FilterOn(List<Integer> plantIds, BindingData bindingData) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByExtra.setString(1, sPlantIds);


        stmtSelectIdsByExtra.setString(2, bindingData.dataBindings.get(Bindings.NECTARWAARDE).getValue().get());
        stmtSelectIdsByExtra.setInt(3, (bindingData.dataBindings.get(Bindings.NECTARWAARDE).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByExtra.setString(4, bindingData.dataBindings.get(Bindings.POLLENWAARDE).getValue().get());
        stmtSelectIdsByExtra.setInt(5, (bindingData.dataBindings.get(Bindings.POLLENWAARDE).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByExtra.setString(6, bindingData.dataBindings.get(Bindings.BIJVRIENDELIJK).getValue().get());
        stmtSelectIdsByExtra.setInt(7, (bindingData.dataBindings.get(Bindings.BIJVRIENDELIJK).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByExtra.setString(8, bindingData.dataBindings.get(Bindings.EETBAAR).getValue().get());
        stmtSelectIdsByExtra.setInt(9, (bindingData.dataBindings.get(Bindings.EETBAAR).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByExtra.setString(10, bindingData.dataBindings.get(Bindings.KRUIDGEBRUIK).getValue().get());
        stmtSelectIdsByExtra.setInt(11, (bindingData.dataBindings.get(Bindings.KRUIDGEBRUIK).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByExtra.setString(12, bindingData.dataBindings.get(Bindings.GEUREND).getValue().get());
        stmtSelectIdsByExtra.setInt(13, (bindingData.dataBindings.get(Bindings.GEUREND).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByExtra.setString(14, bindingData.dataBindings.get(Bindings.VORSTGEVOELIG).getValue().get());
        stmtSelectIdsByExtra.setInt(15, (bindingData.dataBindings.get(Bindings.VORSTGEVOELIG).getDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByExtra.executeQuery();
        while (rs.next()){
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    //endregion
}
