package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.model.data.ComboBoxData;
import plantenApp.java.model.data.GUIdata;
import plantenApp.java.model.data.SpinnerData;
import plantenApp.java.model.data.enums.EComboBox;
import plantenApp.java.model.data.enums.ESpinner;
import plantenApp.java.utils.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    /**
     * @author Siebe
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    public ArrayList<Integer> FilterOn(ArrayList<Integer> plantIds, GUIdata guiData) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByBeheer = DaoUtils.ReadyStatement(dbConnection,GETIDSBYBEHEER,plantIds);

        //Behandeling
        ComboBoxData behandeling = guiData.comboBoxDEM.get(EComboBox.BEHANDELING);
        //SearchRequest<RequestValue> behandeling = bindingData.searchRequestData.get(ERequestData.BEHANDELING);
        stmtSelectIdsByBeheer.setString(plantIds.size()+1 , behandeling.getValue());
        stmtSelectIdsByBeheer.setInt(plantIds.size() + 2, (behandeling.isDoSearch()) ? 0 : 1);

        //maand
        ComboBoxData maand = guiData.comboBoxDEM.get(EComboBox.MAAND);
        //SearchRequest<RequestValue> maand = bindingData.searchRequestData.get(ERequestData.MAAND);
        stmtSelectIdsByBeheer.setString(plantIds.size() + 3, maand.getValue());
        stmtSelectIdsByBeheer.setInt(plantIds.size() + 4, (maand.isDoSearch()) ? 0 : 1);

        //perxjaar
        SpinnerData perXjaar = guiData.spinnerDEM.get(ESpinner.PERXJAAR);
        //SearchRequest<RequestValue> perXjaar = bindingData.searchRequestData.get(ERequestData.PERXJAAR);
        stmtSelectIdsByBeheer.setInt(plantIds.size() + 5, perXjaar.getValue());
        stmtSelectIdsByBeheer.setInt(plantIds.size() + 6, (perXjaar.isDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByBeheer.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    //endregion
}
