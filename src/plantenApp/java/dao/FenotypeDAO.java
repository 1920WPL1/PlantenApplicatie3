package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.utils.ArrayBindings;
import plantenApp.java.utils.Bindings;
import plantenApp.java.utils.DaoUtils;
import plantenApp.java.utils.Utils;

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
    private PreparedStatement stmtSelectIdsBySingleFenoMulti;

    public FenotypeDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;
        stmtSelectFenoByID = dbConnection.prepareStatement(GETFENOTYPEBYPLANTID);
        stmtSelectFenoMultiByID = dbConnection.prepareStatement(GETFENOTYPEMULTIBYPLANTID);

        stmtSelectIdsByFeno = dbConnection.prepareStatement(GETIDSBYFENO);
        stmtSelectIdsByFenoMulti = dbConnection.prepareStatement(GETIDSBYFENOMULTI);
        stmtSelectIdsBySingleFenoMulti = dbConnection.prepareStatement(GETIDSBYSINGLEFENOMULTI);
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

        //region SQLcommand
        stmtSelectIdsByFeno.setString(1, sPlantIds);

        //bladvorm
        PropertyClass<Value> bladvorm = bindingData.dataBindings.get(Bindings.BLADVORM);
        stmtSelectIdsByFeno.setString(2, bladvorm.getValue().get());
        stmtSelectIdsByFeno.setInt(3, (bladvorm.getDoSearch()) ? 0 : 1);

        //levensvorm
        PropertyClass<ValueWithBoolean[]> levensvorm = bindingData.arrayDataBindings.get(ArrayBindings.LEVENSVORM);
        stmtSelectIdsByFeno.setString(4, Utils.GetCheckedValue(levensvorm.getValue()));
        stmtSelectIdsByFeno.setInt(5, (levensvorm.getDoSearch()) ? 0 : 1);

        //habitus
        PropertyClass<ValueWithBoolean[]> habitus = bindingData.arrayDataBindings.get(ArrayBindings.HABITUS);
        stmtSelectIdsByFeno.setString(6, Utils.GetCheckedValue(habitus.getValue()));
        stmtSelectIdsByFeno.setInt(7, (habitus.getDoSearch()) ? 0 : 1);

        //bloeiwijze
        PropertyClass<ValueWithBoolean[]> bloeiwijze = bindingData.arrayDataBindings.get(ArrayBindings.BLOEIWIJZE);
        stmtSelectIdsByFeno.setString(8, Utils.GetCheckedValue(habitus.getValue()));
        stmtSelectIdsByFeno.setInt(9, (bloeiwijze.getDoSearch()) ? 0 : 1);

        //bladgrootte
        PropertyClass<Value> bladgrootte = bindingData.dataBindings.get(Bindings.BLADGROOTTE);
        stmtSelectIdsByFeno.setString(10, bladgrootte.getValue().get());
        stmtSelectIdsByFeno.setInt(11, (bladgrootte.getDoSearch()) ? 0 : 1);

        //ratiobloeiblad
        PropertyClass<Value> ratiobloeiblad = bindingData.dataBindings.get(Bindings.RATIOBLOEIBLAD);
        stmtSelectIdsByFeno.setString(12, ratiobloeiblad.getValue().get());
        stmtSelectIdsByFeno.setInt(13, (ratiobloeiblad.getDoSearch()) ? 0 : 1);

        //spruitfenologie
        PropertyClass<Value> spruitfenologie = bindingData.dataBindings.get(Bindings.SPRUITFENOLOGIE);
        stmtSelectIdsByFeno.setString(14, spruitfenologie.getValue().get());
        stmtSelectIdsByFeno.setInt(15, (spruitfenologie.getDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByFeno.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //endregion

        //region Multi_Eigenschappen

        //Hoogtes
        PropertyClass<ValueWithBoolean[]> multiHoogte;
        PropertyClass<ValueWithBoolean[]> singleHoogte;

        //region Bloeihoogte

        multiHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTEPERMAAND);
        singleHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTE);
        if (multiHoogte.getDoSearch()) {
            ids = FilterOnMulti("BloeiHoogte", multiHoogte.getValue(), ids);
        } else if (singleHoogte.getDoSearch()) {
            ids = FilterBetweenValues("Bloeihoogte", singleHoogte.getValue(), ids);
        }

        //endregion

        //region Bladhoogte

        multiHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLADHOOGTEPERMAAND);
        singleHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLADHOOGTE);
        if (multiHoogte.getDoSearch()) {
            ids = FilterOnMulti("Bladhoogte", multiHoogte.getValue(), ids);
        } else if (singleHoogte.getDoSearch()) {
            ids = FilterBetweenValues("Bladhoogte", singleHoogte.getValue(), ids);
        }

        //endregion

        //Kleuren
        PropertyClass<ValueWithBoolean[]> multiKleur;
        PropertyClass<Value> singleKleur;

        //region bloeikleur

        multiKleur = bindingData.arrayDataBindings.get(ArrayBindings.BLOEIKLEURPERMAAND);
        singleKleur = bindingData.dataBindings.get(Bindings.BLOEIKLEUR);
        if (multiKleur.getDoSearch()) {
            ids = FilterOnMulti("Bloeikleur", multiKleur.getValue(), ids);
        } else if (singleKleur.getDoSearch()) {
            ids = FilterOnSingleMulti("Bloeikleur", singleKleur.getValue(), ids);
        }

        //endregion

        //region bladkleur

        multiKleur = bindingData.arrayDataBindings.get(ArrayBindings.BLADKLEURPERMAAND);
        singleKleur = bindingData.dataBindings.get(Bindings.BLADKLEUR);
        if (multiKleur.getDoSearch()) {
            ids = FilterOnMulti("Bladkleur", multiKleur.getValue(), ids);
        } else if (singleKleur.getDoSearch()) {
            ids = FilterOnSingleMulti("Bladkleur", singleKleur.getValue(), ids);
        }

        //endregion

        //endregion

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
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterBetweenValues(String eigenschap, ValueWithBoolean[] data, List<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsBySingleFenoMulti.setString(1, sPlantIds);

        stmtSelectIdsBySingleFenoMulti.setString(2, eigenschap);

        stmtSelectIdsBySingleFenoMulti.setInt(3, Integer.parseInt(data[0].get()));
        stmtSelectIdsBySingleFenoMulti.setInt(4, Integer.parseInt(data[1].get()));
        stmtSelectIdsBySingleFenoMulti.setInt(5, Integer.parseInt(data[0].get()));
        stmtSelectIdsBySingleFenoMulti.setInt(6, Integer.parseInt(data[1].get()));
        stmtSelectIdsBySingleFenoMulti.setInt(7, Integer.parseInt(data[0].get()));
        stmtSelectIdsBySingleFenoMulti.setInt(8, Integer.parseInt(data[1].get()));
        stmtSelectIdsBySingleFenoMulti.setInt(9, Integer.parseInt(data[0].get()));
        stmtSelectIdsBySingleFenoMulti.setInt(10, Integer.parseInt(data[1].get()));
        stmtSelectIdsBySingleFenoMulti.setInt(11, Integer.parseInt(data[0].get()));
        stmtSelectIdsBySingleFenoMulti.setInt(12, Integer.parseInt(data[1].get()));
        stmtSelectIdsBySingleFenoMulti.setInt(13, Integer.parseInt(data[0].get()));
        stmtSelectIdsBySingleFenoMulti.setInt(14, Integer.parseInt(data[1].get()));

        ResultSet rs = stmtSelectIdsBySingleFenoMulti.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnSingleMulti(String eigenschap, Value data, List<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        String sPlantIds = DaoUtils.sqlFormatedList(plantIds);
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        stmtSelectIdsBySingleFenoMulti.setString(1, sPlantIds);

        stmtSelectIdsBySingleFenoMulti.setString(2, eigenschap);

        stmtSelectIdsBySingleFenoMulti.setInt(3, Integer.parseInt(data.get()));
        stmtSelectIdsBySingleFenoMulti.setInt(4, Integer.parseInt(data.get()));
        stmtSelectIdsBySingleFenoMulti.setInt(5, Integer.parseInt(data.get()));
        stmtSelectIdsBySingleFenoMulti.setInt(6, Integer.parseInt(data.get()));
        stmtSelectIdsBySingleFenoMulti.setInt(7, Integer.parseInt(data.get()));
        stmtSelectIdsBySingleFenoMulti.setInt(8, Integer.parseInt(data.get()));
        stmtSelectIdsBySingleFenoMulti.setInt(9, Integer.parseInt(data.get()));
        stmtSelectIdsBySingleFenoMulti.setInt(10, Integer.parseInt(data.get()));
        stmtSelectIdsBySingleFenoMulti.setInt(11, Integer.parseInt(data.get()));
        stmtSelectIdsBySingleFenoMulti.setInt(12, Integer.parseInt(data.get()));
        stmtSelectIdsBySingleFenoMulti.setInt(13, Integer.parseInt(data.get()));
        stmtSelectIdsBySingleFenoMulti.setInt(14, Integer.parseInt(data.get()));

        ResultSet rs = stmtSelectIdsBySingleFenoMulti.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    //endregion
}

