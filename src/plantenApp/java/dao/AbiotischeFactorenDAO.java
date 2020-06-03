package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.utils.ArrayBindings;
import plantenApp.java.utils.Bindings;
import plantenApp.java.utils.DaoUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * @author Siebe
 */
public class AbiotischeFactorenDAO implements Queries {
    private Connection dbConnection;
    private PreparedStatement stmtSelectAbioByID;
    private PreparedStatement stmtSelectAbioMultiByID;
    private PreparedStatement stmtSelectIdsByAbio;
    private PreparedStatement stmtSelectIdsByAbioMulti;

    public AbiotischeFactorenDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectAbioByID = dbConnection.prepareStatement(GETABIOTISCHBYPLANTID);
        stmtSelectAbioMultiByID = dbConnection.prepareStatement(GETABIOTISCHBMULTIYPLANTID);
        stmtSelectIdsByAbio = dbConnection.prepareStatement(GETIDSBYABIO);
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

    public ArrayList<Integer> FilterOn(List<Integer> plantIds, BindingData bindingData) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByAbio.setString(1, sPlantIds);

        stmtSelectIdsByAbio.setString(2, bindingData.dataBindings.get(Bindings.BEZONNING).getValue().get());
        stmtSelectIdsByAbio.setInt(3, (bindingData.dataBindings.get(Bindings.STRATEGIE).getDoSearch()) ? 0 : 1);

        ArrayList<ValueWithBoolean> grondsoortData = bindingData.arrayDataBindings.get(ArrayBindings.GRONDSOORT).getValue();
        StringBuilder grondsoortValue = new StringBuilder();
        for (int i=0;i<grondsoortData.size();i++)
        {
            if (grondsoortData.get(0).getBool()) {
                grondsoortValue.append(grondsoortData.get(0).get());
            }
        }


        stmtSelectIdsByAbio.setString(4, grondsoortValue.toString());
        stmtSelectIdsByAbio.setInt(5, (bindingData.arrayDataBindings.get(ArrayBindings.GRONDSOORT).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByAbio.setString(6, bindingData.dataBindings.get(Bindings.VOCHTBEHOEFTE).getValue().get());
        stmtSelectIdsByAbio.setInt(7, (bindingData.dataBindings.get(Bindings.STRATEGIE).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByAbio.setString(8, bindingData.dataBindings.get(Bindings.VOEDINGSBEHOEFTE).getValue().get());
        stmtSelectIdsByAbio.setInt(9, (bindingData.dataBindings.get(Bindings.STRATEGIE).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByAbio.setString(10, bindingData.dataBindings.get(Bindings.REACTIEANTAGONISTISCHEOMGEVING).getValue().get());
        stmtSelectIdsByAbio.setInt(11, (bindingData.dataBindings.get(Bindings.STRATEGIE).getDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByAbio.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt(0));
        }

        if (bindingData.dataBindings.get(Bindings.HABITAT).getDoSearch()){
            ids = FilterOnMulti("Habitat", bindingData.dataBindings.get(Bindings.HABITAT).getValue().get(), ids);
        }

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnMulti(String eigenschap, String value, List<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByAbioMulti.setString(1, sPlantIds);

        stmtSelectIdsByAbioMulti.setString(2, eigenschap);

        stmtSelectIdsByAbioMulti.setString(3, value);

        ResultSet rs = stmtSelectIdsByAbioMulti.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt(0));
        }

        //Output
        return ids;
    }

    //endregion

}
