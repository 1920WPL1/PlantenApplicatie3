package plantenApp.java.dao;

import plantenApp.java.model.Beheer;
import plantenApp.java.model.Beheerdaad_Eigenschap;
import plantenApp.java.utils.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**@author Siebe*/
public class BeheerDAO implements Queries {
    private Connection dbConnection;
    private PreparedStatement stmtSelectBeheerByID;
    private PreparedStatement stmtSelectIdsByBeheer;

    public BeheerDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectBeheerByID = dbConnection.prepareStatement(GETBEHEERBYPLANTID);
        stmtSelectIdsByBeheer = dbConnection.prepareStatement(GETIDSBYBEHEER);
    }

    //region GET

    /**
     * @author Siebe
     * @param id -> plant_id
     * @return -> beheer van de specifieke plant
     */
    public Beheer getById(int id) throws SQLException {
        //Dao

        //Items
        Beheer beheer = null;

        //SqlCommand
        beheer = new Beheer(
                id,
                getBeheerdaden(id)
        );

        //Output
        return beheer;
    }

    /**
     * @author Siebe
     * word alleen gebruikt in getById
     * @param id -> plant_id
     * @return -> alle beheerdaden van de specifieke plant
     */
    private ArrayList<Beheerdaad_Eigenschap> getBeheerdaden(int id) throws SQLException {
        //Dao

        //Items
        ArrayList<Beheerdaad_Eigenschap> abioMulti = new ArrayList<>();

        //SqlCommand
        stmtSelectBeheerByID.setInt(1, id);
        ResultSet rs = stmtSelectBeheerByID.executeQuery();
        while (rs.next()) {
            Beheerdaad_Eigenschap beheerdaad = new Beheerdaad_Eigenschap(
                    rs.getInt("beheer_id"),
                    rs.getString("beheerdaad"),
                    rs.getString("opmerking"),
                    rs.getString("maand"),
                    rs.getInt("frequentie_jaar")
            );
            abioMulti.add(beheerdaad);
        }

        //Output
        return abioMulti;
    }

    //endregion

    //region FILTER

    public ArrayList<Integer> FilterOn(List<Integer> plantIds, EnumMap) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByBeheer.setString(1, sPlantIds);

        stmtSelectIdsByBeheer.setString(2, beheerdaad);
        stmtSelectIdsByBeheer.setInt(3, DoSearch);

        stmtSelectIdsByBeheer.setString(4, maand);
        stmtSelectIdsByBeheer.setInt(5, DoSearch);

        stmtSelectIdsByBeheer.setString(6, frequentie_jaar);
        stmtSelectIdsByBeheer.setInt(7, DoSearch);

        ResultSet rs = stmtSelectIdsByBeheer.executeQuery();
        while (rs.next()){
            ids.add(rs.getInt(0));
        }

        //Output
        return ids;
    }

    //endregion
}
