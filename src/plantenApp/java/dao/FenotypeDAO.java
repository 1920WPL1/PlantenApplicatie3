package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.utils.ArrayBindings;
import plantenApp.java.utils.Bindings;
import plantenApp.java.utils.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Siebe
 */
public class FenotypeDAO implements Queries {

    private Connection dbConnection;
    private PreparedStatement stmtSelectFenoByID;
    private PreparedStatement stmtSelectFenoMultiByID;
    private PreparedStatement stmtSelectIdsByFeno;
    private PreparedStatement stmtSelectIdsByFenoMulti;

    public FenotypeDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;
        stmtSelectFenoByID = dbConnection.prepareStatement(GETFENOTYPEBYPLANTID);
        stmtSelectFenoMultiByID = dbConnection.prepareStatement(GETFENOTYPEMULTIBYPLANTID);
        stmtSelectIdsByFeno = dbConnection.prepareStatement(GETIDSBYFENO);
        stmtSelectIdsByFenoMulti = dbConnection.prepareStatement(GETIDSBYFENOMULTI);
    }

    //region GET

    /**
     * @param id -> plant_id
     * @return alle fenotipsche factoren van de specifieke plant
     * @author Siebe
     */
    public Fenotype getById(int id) throws SQLException {
        //Dao

        //Items
        Fenotype feno = null;

        //SqlCommand
        stmtSelectFenoByID.setInt(1, id);
        ResultSet rs = stmtSelectFenoByID.executeQuery();
        if (rs.next()) {
            feno = new Fenotype(
                    rs.getInt("fenotype_id"),
                    rs.getInt("plant_id"),
                    rs.getString("bladvorm"),
                    rs.getString("levensvorm"),
                    rs.getString("habitus"),
                    rs.getString("bloeiwijze"),
                    rs.getInt("bladgrootte"),
                    rs.getString("ratio_bloei_blad"),
                    rs.getString("spruitfenologie"),
                    getByIdMulti(id)
            );
        }

        //Output
        return feno;
    }

    /**
     * @param id -> plant_id
     * @return -> alle fenotype_multi van de specifieke plant
     * @author Siebe
     * word alleen gebruikt in getById
     */
    private ArrayList<FenoMulti_Eigenschap> getByIdMulti(int id) throws SQLException {
        //Dao

        //Items
        ArrayList<FenoMulti_Eigenschap> commMulti = new ArrayList<>();

        //SqlCommand
        stmtSelectFenoMultiByID.setInt(1, id);
        ResultSet rs = stmtSelectFenoMultiByID.executeQuery();
        while (rs.next()) {
            FenoMulti_Eigenschap fenoEigenschap = new FenoMulti_Eigenschap(
                    rs.getInt("fenotype_id"),
                    rs.getString("eigenschap"),
                    rs.getString("jan"),
                    rs.getString("feb"),
                    rs.getString("maa"),
                    rs.getString("apr"),
                    rs.getString("mei"),
                    rs.getString("jun"),
                    rs.getString("jul"),
                    rs.getString("aug"),
                    rs.getString("sep"),
                    rs.getString("okt"),
                    rs.getString("nov"),
                    rs.getString("dec")
            );
            commMulti.add(fenoEigenschap);
        }

        //Output
        return commMulti;
    }

    //endregion

    //region FILTER

    public ArrayList<Integer> FilterOn(List<Integer> plantIds, BindingData bindingData) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByFeno.setString(1, sPlantIds);


        stmtSelectIdsByFeno.setString(2, bindingData.dataBindings.get(Bindings.BLADVORM).getValue().get());
        stmtSelectIdsByFeno.setInt(3, (bindingData.dataBindings.get(Bindings.BLADVORM).getDoSearch()) ? 0 : 1);

        //TODO: fix to array
        //stmtSelectIdsByFeno.setString(4, bindingData.dataBindings.get(Bindings.LEVENSVORM).getValue().get());
        //stmtSelectIdsByFeno.setInt(5, (bindingData.dataBindings.get(Bindings.LEVENSVORM).getDoSearch()) ? 0 : 1);

        //stmtSelectIdsByFeno.setString(6, bindingData.dataBindings.get(Bindings.HABITUS).getValue().get());
        //stmtSelectIdsByFeno.setInt(7, (bindingData.dataBindings.get(Bindings.HABITUS).getDoSearch()) ? 0 : 1);

        //stmtSelectIdsByFeno.setString(8, bindingData.dataBindings.get(Bindings.BLOEIWIJZE).getValue().get());
        //stmtSelectIdsByFeno.setInt(9, (bindingData.dataBindings.get(Bindings.BLOEIWIJZE).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByFeno.setString(10, bindingData.dataBindings.get(Bindings.BLADGROOTTE).getValue().get());
        stmtSelectIdsByFeno.setInt(11, (bindingData.dataBindings.get(Bindings.BLADGROOTTE).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByFeno.setString(12, bindingData.dataBindings.get(Bindings.RATIOBLOEIBLAD).getValue().get());
        stmtSelectIdsByFeno.setInt(13, (bindingData.dataBindings.get(Bindings.RATIOBLOEIBLAD).getDoSearch()) ? 0 : 1);

        stmtSelectIdsByFeno.setString(14, bindingData.dataBindings.get(Bindings.SPRUITFENOLOGIE).getValue().get());
        stmtSelectIdsByFeno.setInt(15, (bindingData.dataBindings.get(Bindings.SPRUITFENOLOGIE).getDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByFeno.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt(0));
        }

        //MultiFilter
        //TODO:Aanpassen aan de hand van de keuze van min-max hoogtes
        PropertyClass<ValueWithBoolean[]> bloeihoogteData = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTEPERMAAND);
        if (bloeihoogteData.getDoSearch()) {
            ids = FilterOnMulti("BloeiHoogte", bloeihoogteData.getValue(), ids);
        }
        PropertyClass<ValueWithBoolean[]> bloeikleurData = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTEPERMAAND);
        if (bloeikleurData.getDoSearch()) {
            ids = FilterOnMulti("BloeiKleur", bloeikleurData.getValue(), ids);
        }
        PropertyClass<ValueWithBoolean[]> bladkleurData = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTEPERMAAND);
        if (bladkleurData.getDoSearch()) {
            ids = FilterOnMulti("BladKleur", bladkleurData.getValue(), ids);
        }
        PropertyClass<ValueWithBoolean[]> bladhoogteData = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTEPERMAAND);
        if (bladhoogteData.getDoSearch()) {
            ids = FilterOnMulti("Bladhoogte", bladhoogteData.getValue(), ids);
        }

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnMulti(String eigenschap, ValueWithBoolean[] data, List<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsByFenoMulti.setString(1, sPlantIds);

        stmtSelectIdsByFenoMulti.setString(2, eigenschap);

        stmtSelectIdsByFenoMulti.setInt(4, Integer.parseInt(data[0].get()));
        stmtSelectIdsByFenoMulti.setInt(5, Integer.parseInt(data[1].get()));
        stmtSelectIdsByFenoMulti.setInt(6, Integer.parseInt(data[2].get()));
        stmtSelectIdsByFenoMulti.setInt(7, Integer.parseInt(data[3].get()));
        stmtSelectIdsByFenoMulti.setInt(8, Integer.parseInt(data[4].get()));
        stmtSelectIdsByFenoMulti.setInt(9, Integer.parseInt(data[5].get()));
        stmtSelectIdsByFenoMulti.setInt(10, Integer.parseInt(data[6].get()));
        stmtSelectIdsByFenoMulti.setInt(11, Integer.parseInt(data[7].get()));
        stmtSelectIdsByFenoMulti.setInt(12, Integer.parseInt(data[8].get()));
        stmtSelectIdsByFenoMulti.setInt(13, Integer.parseInt(data[9].get()));
        stmtSelectIdsByFenoMulti.setInt(14, Integer.parseInt(data[10].get()));
        stmtSelectIdsByFenoMulti.setInt(15, Integer.parseInt(data[11].get()));

        ResultSet rs = stmtSelectIdsByFenoMulti.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt(0));
        }

        //Output
        return ids;
    }

    //endregion
}

