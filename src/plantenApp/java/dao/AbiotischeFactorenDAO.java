package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.utils.ERequestArrayData;
import plantenApp.java.utils.ERequestData;
import plantenApp.java.utils.DaoUtils;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Siebe
 */
public class AbiotischeFactorenDAO implements Queries {
    private Connection dbConnection;
    private PreparedStatement stmtSelectAbioByID;
    private PreparedStatement stmtSelectAbioMultiByID;
    private PreparedStatement stmtSelectIdsByAbioMulti;

    public AbiotischeFactorenDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectAbioByID = dbConnection.prepareStatement(GETABIOTISCHBYPLANTID);
        stmtSelectAbioMultiByID = dbConnection.prepareStatement(GETABIOTISCHBMULTIYPLANTID);
        stmtSelectIdsByAbioMulti = dbConnection.prepareStatement(GETIDSBYABIOMULTI);
    }

    //region GET

    /**
     * @param id -> plant_id
     * @return -> alle abiotische factoren van de specifieke plant
     * @author Siebe
     */
    public AbiotischeFactoren getById(int id) throws SQLException {
        //Dao

        //Items
        AbiotischeFactoren abio = null;

        //SqlCommand
        stmtSelectAbioByID.setInt(1, id);
        ResultSet rs = stmtSelectAbioByID.executeQuery();
        if (rs.next()) {
            abio = new AbiotischeFactoren(
                    rs.getInt("abiotische_id"),
                    rs.getInt("plant_id"),
                    rs.getString("bezonning"),
                    rs.getString("grondsoort"),
                    rs.getString("vochtbehoefte"),
                    rs.getString("voedingsbehoefte"),
                    rs.getString("reactie_antagonistische_omg"),
                    getByIdMulti(id)
            );
        }

        //Output
        return abio;
    }

    /**
     * @param id -> plant_id
     * @return -> alle abiotische_multi factoren van de specifieke plant
     * @author Siebe
     * word alleen gebruikt in getById
     */
    private ArrayList<AbioMulti_Eigenschap> getByIdMulti(int id) throws SQLException {
        //Dao

        //Items
        ArrayList<AbioMulti_Eigenschap> abioMulti = new ArrayList<>();

        //SqlCommand
        stmtSelectAbioMultiByID.setInt(1, id);
        ResultSet rs = stmtSelectAbioMultiByID.executeQuery();
        while (rs.next()) {
            AbioMulti_Eigenschap abioEigenschap = new AbioMulti_Eigenschap(
                    rs.getInt("abiotische_id"),
                    rs.getString("eigenschap"),
                    rs.getString("waarde")
            );
            abioMulti.add(abioEigenschap);
        }

        //Output
        return abioMulti;
    }

    //endregion

    //region FILTER

    /**
     * @author Siebe
     * @param plantIds -> The ids that need to be filtered
     * @param bindingData -> dataClass that consist of all the data of the bindings
     * @return The filtered ids
     */
    public ArrayList<Integer> FilterOn(ArrayList<Integer> plantIds, BindingData bindingData) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //makes the prepared statement en fills in de IN (?)
        PreparedStatement stmtSelectIdsByAbio = DaoUtils.ReadyStatement(dbConnection, GETIDSBYABIO, plantIds);

        //Bezonning
        SearchRequest<RequestValue> bezonning = bindingData.searchRequestData.get(ERequestData.BEZONNING);
        stmtSelectIdsByAbio.setString(plantIds.size() + 1, bezonning.Value().getValue());
        stmtSelectIdsByAbio.setInt(plantIds.size() + 2, (bezonning.getDoSearch()) ? 0 : 1);

        //Grondsoort
        SearchRequest<RequestValueWBool[]> grondsoort = bindingData.searchRequestArrayData.get(ERequestArrayData.GRONDSOORT);
        StringBuilder grondsoortValue = new StringBuilder();
        for (int i = 0; i < grondsoort.Value().length; i++) {
            if (grondsoort.Value()[i].getIsSelected()) {
                grondsoortValue.append(grondsoort.Value()[i].getValue());
            }
        }
        stmtSelectIdsByAbio.setString(plantIds.size() + 3, grondsoortValue.toString());
        stmtSelectIdsByAbio.setInt(plantIds.size() + 4, (grondsoort.getDoSearch()) ? 0 : 1);

        //Vochtbehoefte
        SearchRequest<RequestValue> vochtbehoefte = bindingData.searchRequestData.get(ERequestData.VOCHTBEHOEFTE);
        stmtSelectIdsByAbio.setString(plantIds.size() + 5, vochtbehoefte.Value().getValue());
        stmtSelectIdsByAbio.setInt(plantIds.size() + 6, (vochtbehoefte.getDoSearch()) ? 0 : 1);

        //voedingsbehoefte
        SearchRequest<RequestValue> voedingsbehoefte = bindingData.searchRequestData.get(ERequestData.VOEDINGSBEHOEFTE);
        stmtSelectIdsByAbio.setString(plantIds.size() + 7, voedingsbehoefte.Value().getValue());
        stmtSelectIdsByAbio.setInt(plantIds.size() + 8, (voedingsbehoefte.getDoSearch()) ? 0 : 1);

        //reactieantaomgeving
        SearchRequest<RequestValue> reactieantaomgeving = bindingData.searchRequestData.get(ERequestData.REACTIEANTAGONISTISCHEOMGEVING);
        stmtSelectIdsByAbio.setString(plantIds.size() + 9, reactieantaomgeving.Value().getValue());
        stmtSelectIdsByAbio.setInt(plantIds.size() + 10, (reactieantaomgeving.getDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByAbio.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //habitat
        SearchRequest<RequestValue> habitat = bindingData.searchRequestData.get(ERequestData.HABITAT);
        if (habitat.getDoSearch()) {
            ids = FilterOnMulti("Habitat", habitat.Value().getValue(), ids);
        }

        //Output
        return ids;
    }

    /**
     * @author Siebe
     * @param eigenschap -> name of the property to filter on
     * @param value -> value that the property should have
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnMulti(String eigenschap, String value, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //makes the prepared statement en fills in de IN (?)
        PreparedStatement stmtSelectIdsByAbioMulti = DaoUtils.ReadyStatement(dbConnection, GETIDSBYABIOMULTI, plantIds);

        //SQLcommand
        stmtSelectIdsByAbioMulti.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByAbioMulti.setString(plantIds.size() + 2, value);

        ResultSet rs = stmtSelectIdsByAbioMulti.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    //endregion

}
