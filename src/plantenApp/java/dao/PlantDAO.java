package plantenApp.java.dao;

import plantenApp.java.model.BindingData;
import plantenApp.java.model.Plant;
import plantenApp.java.model.SearchRequest;
import plantenApp.java.model.RequestValue;
import plantenApp.java.model.data.ComboBoxData;
import plantenApp.java.model.data.GUIdata;
import plantenApp.java.model.data.TextfieldData;
import plantenApp.java.model.data.enums.EComboBox;
import plantenApp.java.model.data.enums.ETextfield;
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
public class PlantDAO implements Queries {

    private Connection dbConnection;
    private PreparedStatement stmtSelectById;
    private PreparedStatement stmtSelectIdsByPlant;

    public PlantDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectById = dbConnection.prepareStatement(GETPLANTBYID);
        stmtSelectIdsByPlant = dbConnection.prepareStatement(GETIDSBYPLANT);
    }

    //region GET

    /**
     * @param plantIds -> plant_ids
     * @return -> alleen de basis gegevens van een plant
     * @author Siebe
     */
    public ArrayList<Plant> GetPlantList(ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Plant> plants = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectByIds = DaoUtils.ReadyStatement(dbConnection, GETPLANTSBYIDS, plantIds);

        //SqlCommand
        ResultSet rs = stmtSelectByIds.executeQuery();
        while (rs.next()) {
            plants.add(new Plant(
                    rs.getInt("plant_id"),
                    rs.getString("type"),
                    rs.getString("familie"),
                    rs.getString("geslacht"),
                    rs.getString("soort"),
                    rs.getString("variatie"),
                    rs.getInt("plantdichtheid_min"),
                    rs.getInt("plantdichtheid_max")
            ));
        }

        //Output
        return plants;
    }

    /**
     * @param id -> plant_id
     * @return -> alleen de basis gegevens van een plant
     * @author Siebe
     */
    public Plant getPlantById(int id) throws SQLException {
        //Dao

        //Items
        Plant plant = null;

        //SqlCommand
        stmtSelectById.setInt(1, id);
        ResultSet rs = stmtSelectById.executeQuery();
        if (rs.next()) {
            plant = new Plant(
                    rs.getInt("plant_id"),
                    rs.getString("type"),
                    rs.getString("familie"),
                    rs.getString("geslacht"),
                    rs.getString("soort"),
                    rs.getString("variatie"),
                    rs.getInt("plantdichtheid_min"),
                    rs.getInt("plantdichtheid_max")
            );
        }

        //Output
        return plant;
    }

    /**
     * @param id -> plant_id
     * @return -> alles van een Plant
     * @author Siebe
     */
    public Plant getFullPlantById(int id) throws SQLException {
        //Dao
        AbiotischeFactorenDAO abiotischeFactorenDAO = new AbiotischeFactorenDAO(dbConnection);
        BeheerDAO beheerDAO = new BeheerDAO(dbConnection);
        CommensalismeDAO commensalismeDAO = new CommensalismeDAO(dbConnection);
        FenotypeDAO fenotypeDAO = new FenotypeDAO(dbConnection);
        ExtraDAO extraDAO = new ExtraDAO(dbConnection);
        FotoDAO fotoDAO = new FotoDAO(dbConnection);

        //Items
        Plant plant = null;

        //SqlCommand
        stmtSelectById.setInt(1, id);
        ResultSet rs = stmtSelectById.executeQuery();
        if (rs.next()) {
            plant = new Plant(
                    rs.getInt("plant_id"),
                    rs.getString("type"),
                    rs.getString("familie"),
                    rs.getString("geslacht"),
                    rs.getString("soort"),
                    rs.getString("variatie"),
                    rs.getInt("plantdichtheid_min"),
                    rs.getInt("plantdichtheid_max"),
                    fotoDAO.getFotoById(id),
                    beheerDAO.getById(id),
                    abiotischeFactorenDAO.getById(id),
                    commensalismeDAO.getById(id),
                    fenotypeDAO.getById(id),
                    extraDAO.getExtraById(id)
            );
        }

        //Output
        return plant;
    }

    //endregion

    //region FILTER

    public ArrayList<Integer> FilterOn(BindingData bindingData, GUIdata guiData) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //type
        ComboBoxData type = guiData.comboBoxDEM.get(EComboBox.TYPE);
        //SearchRequest<RequestValue> type = bindingData.searchRequestData.get(ERequestData.TYPE);
        stmtSelectIdsByPlant.setString(1, type.getValue());
        stmtSelectIdsByPlant.setInt(2, (type.isDoSearch()) ? 0 : 1);

        //familie
        ComboBoxData familie = guiData.comboBoxDEM.get(EComboBox.TYPE);
        //SearchRequest<RequestValue> familie = bindingData.searchRequestData.get(ERequestData.FAMILIE);
        stmtSelectIdsByPlant.setString(3, familie.getValue());
        stmtSelectIdsByPlant.setInt(4, (familie.isDoSearch()) ? 0 : 1);

        //fgsv
        TextfieldData fgsv = guiData.textFieldDEM.get(ETextfield.SEARCH);
        //SearchRequest<RequestValue> fgsv = bindingData.searchRequestData.get(ERequestData.SEARCH);
        stmtSelectIdsByPlant.setString(5, fgsv.getValue());
        stmtSelectIdsByPlant.setInt(6, (fgsv.isDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByPlant.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    //endregion
}
