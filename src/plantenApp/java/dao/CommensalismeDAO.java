package plantenApp.java.dao;

import plantenApp.java.model.CommMulti_Eigenschap;
import plantenApp.java.model.Commensalisme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**@author Siebe*/
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
     * @author Siebe
     * @param id -> plant_id
     * @return alle abiotische factoren van de specifieke plant
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
     * @author Siebe
     * word alleen gebruikt in getById
     * @param id -> plant_id
     * @return -> alle commensalisme_multi van de specifieke plant
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
}

