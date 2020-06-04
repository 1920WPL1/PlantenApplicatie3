package plantenApp.java.dao;

import plantenApp.java.model.*;
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
public class BeheerDAO implements Queries {
    private Connection dbConnection;
    private PreparedStatement stmtSelectBeheerByID;

    public BeheerDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectBeheerByID = dbConnection.prepareStatement(GETBEHEERBYPLANTID);
    }

    //region GET

    /**
     * @param id -> plant_id
     * @return -> beheer van de specifieke plant
     * @author Siebe
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
     * @param id -> plant_id
     * @return -> alle beheerdaden van de specifieke plant
     * @author Siebe
     * word alleen gebruikt in getById
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

    public ArrayList<Integer> FilterOn(ArrayList<Integer> plantIds, BindingData bindingData) throws SQLException {
        //Dao
        System.out.println(plantIds.toString());
        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByBeheer = DaoUtils.ReadyStatement(dbConnection,GETIDSBYBEHEER,plantIds);

        //Behandeling
        PropertyClass<Value> behandeling = bindingData.dataBindings.get(Bindings.BEHANDELING);
        stmtSelectIdsByBeheer.setString(plantIds.size()+1 , behandeling.getValue().get());
        stmtSelectIdsByBeheer.setInt(plantIds.size() + 2, (behandeling.getDoSearch()) ? 0 : 1);

        //maand
        PropertyClass<Value> maand = bindingData.dataBindings.get(Bindings.MAAND);
        stmtSelectIdsByBeheer.setString(plantIds.size() + 3, maand.getValue().get());
        stmtSelectIdsByBeheer.setInt(plantIds.size() + 4, (maand.getDoSearch()) ? 0 : 1);

        //perxjaar
        PropertyClass<Value> perXjaar = bindingData.dataBindings.get(Bindings.PERXJAAR);
        stmtSelectIdsByBeheer.setInt(plantIds.size() + 5, Integer.parseInt(perXjaar.getValue().get()));
        stmtSelectIdsByBeheer.setInt(plantIds.size() + 6, (perXjaar.getDoSearch()) ? 0 : 1);

        System.out.println(stmtSelectIdsByBeheer);
        ResultSet rs = stmtSelectIdsByBeheer.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    //endregion
}
