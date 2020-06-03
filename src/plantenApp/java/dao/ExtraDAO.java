package plantenApp.java.dao;

import plantenApp.java.model.Extra;
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

    public ArrayList<Integer> FilterOn(List<Integer> plantIds, EnumMap) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByExtra.setString(1, sPlantIds);


        stmtSelectIdsByExtra.setString(2, nectarwaarde);
        stmtSelectIdsByExtra.setString(3, DoSearch);

        stmtSelectIdsByExtra.setString(4, pollenwaarde);
        stmtSelectIdsByExtra.setString(5, DoSearch);

        stmtSelectIdsByExtra.setString(6, bijvriendelijk);
        stmtSelectIdsByExtra.setString(7, DoSearch);

        stmtSelectIdsByExtra.setString(8, eetbaar);
        stmtSelectIdsByExtra.setString(9, DoSearch);

        stmtSelectIdsByExtra.setString(10, kruidgebruik);
        stmtSelectIdsByExtra.setString(11, DoSearch);

        stmtSelectIdsByExtra.setString(12, geurend);
        stmtSelectIdsByExtra.setString(13, DoSearch);

        stmtSelectIdsByExtra.setString(14, vorstgevoelig);
        stmtSelectIdsByExtra.setString(15, DoSearch);

        ResultSet rs = stmtSelectIdsByExtra.executeQuery();
        while (rs.next()){
            ids.add(rs.getInt(0));
        }

        //Output
        return ids;
    }

    //endregion
}
