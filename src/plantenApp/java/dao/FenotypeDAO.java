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

    private PreparedStatement stmtSelectIdsByFenoMulti;
    private PreparedStatement stmtSelectIdsBySingleFenoMulti;

    public FenotypeDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;
        stmtSelectFenoByID = dbConnection.prepareStatement(GETFENOTYPEBYPLANTID);
        stmtSelectFenoMultiByID = dbConnection.prepareStatement(GETFENOTYPEMULTIBYPLANTID);
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

    public ArrayList<Integer> FilterOn(ArrayList<Integer> plantIds, BindingData bindingData) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFeno = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENO, plantIds);

        //region StandaardFilter
        //bladvorm
        PropertyClass<Value> bladvorm = bindingData.dataBindings.get(Bindings.BLADVORM);
        stmtSelectIdsByFeno.setString(plantIds.size() + 1, bladvorm.getValue().get());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 2, (bladvorm.getDoSearch()) ? 0 : 1);

        //levensvorm
        PropertyClass<ValueWithBoolean[]> levensvorm = bindingData.arrayDataBindings.get(ArrayBindings.LEVENSVORM);
        stmtSelectIdsByFeno.setString(plantIds.size() + 3, DaoUtils.GetCheckedValue(levensvorm.getValue()));
        stmtSelectIdsByFeno.setInt(plantIds.size() + 4, (levensvorm.getDoSearch()) ? 0 : 1);

        //habitus
        PropertyClass<ValueWithBoolean[]> habitus = bindingData.arrayDataBindings.get(ArrayBindings.HABITUS);
        stmtSelectIdsByFeno.setString(plantIds.size() + 5, DaoUtils.GetCheckedValue(habitus.getValue()));
        stmtSelectIdsByFeno.setInt(plantIds.size() + 6, (habitus.getDoSearch()) ? 0 : 1);

        //bloeiwijze
        PropertyClass<ValueWithBoolean[]> bloeiwijze = bindingData.arrayDataBindings.get(ArrayBindings.BLOEIWIJZE);
        stmtSelectIdsByFeno.setString(plantIds.size() + 7, DaoUtils.GetCheckedValue(habitus.getValue()));
        stmtSelectIdsByFeno.setInt(plantIds.size() + 8, (bloeiwijze.getDoSearch()) ? 0 : 1);

        //bladgrootte
        PropertyClass<Value> bladgrootte = bindingData.dataBindings.get(Bindings.BLADGROOTTE);
        stmtSelectIdsByFeno.setInt(plantIds.size() + 9, Integer.parseInt(bladgrootte.getValue().get()));
        stmtSelectIdsByFeno.setInt(plantIds.size() + 10, (bladgrootte.getDoSearch()) ? 0 : 1);

        //ratiobloeiblad
        PropertyClass<Value> ratiobloeiblad = bindingData.dataBindings.get(Bindings.RATIOBLOEIBLAD);
        stmtSelectIdsByFeno.setString(plantIds.size() + 11, ratiobloeiblad.getValue().get());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 12, (ratiobloeiblad.getDoSearch()) ? 0 : 1);

        //spruitfenologie
        PropertyClass<Value> spruitfenologie = bindingData.dataBindings.get(Bindings.SPRUITFENOLOGIE);
        stmtSelectIdsByFeno.setString(plantIds.size() + 13, spruitfenologie.getValue().get());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 14, (spruitfenologie.getDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByFeno.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //endregion

        //region Hoogtes
        PropertyClass<ValueWithBoolean[]> multiHoogte;
        PropertyClass<ValueWithBoolean[]> singleHoogte;

        //Bloeihoogte
        multiHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MINBLOEIHOOGTEPERMAAND);
        singleHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MINGBLOEIHOOGTE);
        if (multiHoogte.getDoSearch()) {
            ids = FilterOnMinHoogte("bloeihoogte", multiHoogte.getValue(), plantIds);
        } else if (singleHoogte.getDoSearch()) {
            ids = FilterOnMinHoogteSingle("bloeihoogte", singleHoogte.getValue(), plantIds);
        }

        multiHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTEPERMAAND);
        singleHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLOEIHOOGTE);
        if (multiHoogte.getDoSearch()) {
            ids = FilterOnMaxHoogte("bloeihoogte", multiHoogte.getValue(), plantIds);
        } else if (singleHoogte.getDoSearch()) {
            ids = FilterOnMaxHoogteSingle("bloeihoogte", singleHoogte.getValue(), plantIds);
        }

        //Bladhoogte
        //TODO: voeg min bladhoogte toe in de searcher
        /*
        multiHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MINBLADHOOGTEPERMAAND);
        singleHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MINBLADHOOGTE);
        if (multiHoogte.getDoSearch()){
            ids = FilterOnMinHoogte("bladhoogte",multiHoogte.getValue(),plantIds);
        }
        else if (singleHoogte.getDoSearch()){
            ids = FilterOnMinHoogteSingle("bladhoogte",singleHoogte.getValue(),plantIds);
        }*/

        multiHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLADHOOGTEPERMAAND);
        singleHoogte = bindingData.arrayDataBindings.get(ArrayBindings.MAXBLADHOOGTE);
        if (multiHoogte.getDoSearch()) {
            ids = FilterOnMaxHoogte("bladhoogte", multiHoogte.getValue(), plantIds);
        } else if (singleHoogte.getDoSearch()) {
            ids = FilterOnMaxHoogteSingle("bladhoogte", singleHoogte.getValue(), plantIds);
        }

        //endregion

        //region Kleuren
        PropertyClass<ValueWithBoolean[]> multiKleur;
        PropertyClass<Value> singleKleur;

        //bloeikleur
        multiKleur = bindingData.arrayDataBindings.get(ArrayBindings.BLOEIKLEURPERMAAND);
        singleKleur = bindingData.dataBindings.get(Bindings.BLOEIKLEUR);
        if (multiKleur.getDoSearch()) {
            ids = FilterOnKleur("Bloeikleur", multiKleur.getValue(), ids);
        } else if (singleKleur.getDoSearch()) {
            ids = FilterOnKleurSingle("Bloeikleur", singleKleur.getValue(), ids);
        }

        //bladkleur
        multiKleur = bindingData.arrayDataBindings.get(ArrayBindings.BLADKLEURPERMAAND);
        singleKleur = bindingData.dataBindings.get(Bindings.BLADKLEUR);
        if (multiKleur.getDoSearch()) {
            ids = FilterOnKleur("Bladkleur", multiKleur.getValue(), ids);
        } else if (singleKleur.getDoSearch()) {
            ids = FilterOnKleurSingle("Bladkleur", singleKleur.getValue(), ids);
        }

        //endregion

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnMinHoogte(String eigenschap, ValueWithBoolean[] data, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiHoogteMin = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTIHOOGTEMIN, plantIds);

        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 2, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 3, data[1].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 4, data[2].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 5, data[3].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 6, data[4].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 7, data[5].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 8, data[6].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 9, data[7].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 10, data[8].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 11, data[9].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 12, data[10].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 13, data[11].get());

        ResultSet rs = stmtSelectIdsByFenoMultiHoogteMin.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnMaxHoogte(String eigenschap, ValueWithBoolean[] data, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiHoogteMax = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTIHOOGTEMAX, plantIds);

        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 2, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 3, data[1].get());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 4, data[2].get());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 5, data[3].get());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 6, data[4].get());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 7, data[5].get());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 8, data[6].get());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 9, data[7].get());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 10, data[8].get());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 11, data[9].get());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 12, data[10].get());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 13, data[11].get());

        ResultSet rs = stmtSelectIdsByFenoMultiHoogteMax.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnMaxHoogteSingle(String eigenschap, ValueWithBoolean[] data, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiHoogteSingle = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTIHOOGTESINGLE, plantIds);

        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 2, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 3, data[1].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 4, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 5, data[1].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 6, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 7, data[1].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 8, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 9, data[1].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 10, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 11, data[1].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 12, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 13, data[1].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 14, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 15, data[1].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 16, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 17, data[1].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 18, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 19, data[1].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 20, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 21, data[1].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 22, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 23, data[1].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 24, data[0].get());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 25, data[1].get());

        ResultSet rs = stmtSelectIdsByFenoMultiHoogteSingle.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnMinHoogteSingle(String eigenschap, ValueWithBoolean[] data, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiHoogteMin = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTIHOOGTEMIN, plantIds);

        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 2, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 3, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 4, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 5, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 6, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 7, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 8, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 9, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 10, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 11, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 12, data[0].get());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 13, data[0].get());

        ResultSet rs = stmtSelectIdsByFenoMultiHoogteMin.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnKleur(String eigenschap, ValueWithBoolean[] data, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiKleur = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTI, plantIds);

        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 2, data[0].get());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 3, data[1].get());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 4, data[2].get());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 5, data[3].get());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 6, data[4].get());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 7, data[5].get());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 8, data[6].get());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 9, data[7].get());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 10, data[8].get());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 11, data[9].get());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 12, data[10].get());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 13, data[11].get());

        ResultSet rs = stmtSelectIdsByFenoMultiKleur.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    private ArrayList<Integer> FilterOnKleurSingle(String eigenschap, Value data, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiKleurSingle = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTISINGLE, plantIds);

        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 2, data.get());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 3, data.get());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 4, data.get());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 5, data.get());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 6, data.get());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 7, data.get());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 8, data.get());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 9, data.get());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 10, data.get());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 11, data.get());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 12, data.get());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 13, data.get());

        ResultSet rs = stmtSelectIdsByFenoMultiKleurSingle.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    //endregion
}

