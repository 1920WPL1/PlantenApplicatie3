package plantenApp.java.dao;

import plantenApp.java.model.Extra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
