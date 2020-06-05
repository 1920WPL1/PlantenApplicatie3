package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.utils.ERequestArrayData;
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

        //SQLcommand
        PreparedStatement stmtSelectIdsByFeno = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENO, plantIds);

        //region StandaardFilter
        //bladvorm
        SearchRequest<RequestValue> bladvorm = bindingData.searchRequestData.get(ERequestData.BLADVORM);
        stmtSelectIdsByFeno.setString(plantIds.size() + 1, bladvorm.Value().getValue());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 2, (bladvorm.getDoSearch()) ? 0 : 1);

        //levensvorm
        SearchRequest<RequestValueWBool[]> levensvorm = bindingData.searchRequestArrayData.get(ERequestArrayData.LEVENSVORM);
        stmtSelectIdsByFeno.setString(plantIds.size() + 3, DaoUtils.GetCheckedValue(levensvorm.Value()));
        stmtSelectIdsByFeno.setInt(plantIds.size() + 4, (levensvorm.getDoSearch()) ? 0 : 1);

        //habitus
        SearchRequest<RequestValueWBool[]> habitus = bindingData.searchRequestArrayData.get(ERequestArrayData.HABITUS);
        stmtSelectIdsByFeno.setString(plantIds.size() + 5, DaoUtils.GetCheckedValue(habitus.Value()));
        stmtSelectIdsByFeno.setInt(plantIds.size() + 6, (habitus.getDoSearch()) ? 0 : 1);

        //bloeiwijze
        SearchRequest<RequestValueWBool[]> bloeiwijze = bindingData.searchRequestArrayData.get(ERequestArrayData.BLOEIWIJZE);
        stmtSelectIdsByFeno.setString(plantIds.size() + 7, DaoUtils.GetCheckedValue(habitus.Value()));
        stmtSelectIdsByFeno.setInt(plantIds.size() + 8, (bloeiwijze.getDoSearch()) ? 0 : 1);

        //bladgrootte
        SearchRequest<RequestValue> bladgrootte = bindingData.searchRequestData.get(ERequestData.BLADGROOTTE);
        System.out.println("hier kijken "+Integer.parseInt(bladgrootte.Value().getValue()));
        stmtSelectIdsByFeno.setInt(plantIds.size() + 9, Integer.parseInt(bladgrootte.Value().getValue()));
        stmtSelectIdsByFeno.setInt(plantIds.size() + 10, (bladgrootte.getDoSearch()) ? 0 : 1);

        //ratiobloeiblad
        SearchRequest<RequestValue> ratiobloeiblad = bindingData.searchRequestData.get(ERequestData.RATIOBLOEIBLAD);
        stmtSelectIdsByFeno.setString(plantIds.size() + 11, ratiobloeiblad.Value().getValue());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 12, (ratiobloeiblad.getDoSearch()) ? 0 : 1);

        //spruitfenologie
        SearchRequest<RequestValue> spruitfenologie = bindingData.searchRequestData.get(ERequestData.SPRUITFENOLOGIE);
        stmtSelectIdsByFeno.setString(plantIds.size() + 13, spruitfenologie.Value().getValue());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 14, (spruitfenologie.getDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByFeno.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //endregion

        //region Hoogtes
        SearchRequest<RequestValueWBool[]> multiHoogteMin;
        SearchRequest<RequestValueWBool[]> multiHoogteMax;
        SearchRequest<RequestValueWBool[]> singleHoogte;

        //Bloeihoogte
        multiHoogteMin = bindingData.searchRequestArrayData.get(ERequestArrayData.MINBLOEIHOOGTEPERMAAND);
        multiHoogteMax = bindingData.searchRequestArrayData.get(ERequestArrayData.MAXBLOEIHOOGTEPERMAAND);
        singleHoogte = bindingData.searchRequestArrayData.get(ERequestArrayData.BLOEIHOOGTE);

        if (multiHoogteMin.getDoSearch()) {
            ids = FilterOnMinHoogte("bloeihoogte", multiHoogteMin.Value(), plantIds);
        }
        if (multiHoogteMax.getDoSearch()) {
            ids = FilterOnMaxHoogte("bloeihoogte", multiHoogteMax.Value(), plantIds);
        }
        if (singleHoogte.getDoSearch()) {
            ids = FilterOnHoogteSingle("bloeihoogte", singleHoogte.Value(), plantIds);
        }



        //Bladhoogte
        //TODO: voeg min bladhoogte toe in de searcher
        //multiHoogteMin = bindingData.searchRequestArrayData.get(ERequestArrayData.MINBLADHOOGTEPERMAAND);
        multiHoogteMax = bindingData.searchRequestArrayData.get(ERequestArrayData.MAXBLADHOOGTEPERMAAND);
        singleHoogte = bindingData.searchRequestArrayData.get(ERequestArrayData.BLADHOOGTE);

        //if (multiHoogteMin.getDoSearch()) {
        //    ids = FilterOnMinHoogte("bladhoogte", multiHoogteMin.Value(), plantIds);
        //}
        if (multiHoogteMax.getDoSearch()) {
            ids = FilterOnMaxHoogte("bladhoogte", multiHoogteMax.Value(), plantIds);
        }
        if (singleHoogte.getDoSearch()) {
            ids = FilterOnHoogteSingle("bladhoogte", singleHoogte.Value(), plantIds);
        }

        //endregion

        //region Kleuren
        SearchRequest<RequestValueWBool[]> multiKleur;
        SearchRequest<RequestValue> singleKleur;

        //bloeikleur
        multiKleur = bindingData.searchRequestArrayData.get(ERequestArrayData.BLOEIKLEURPERMAAND);
        singleKleur = bindingData.searchRequestData.get(ERequestData.BLOEIKLEUR);
        if (multiKleur.getDoSearch()) {
            ids = FilterOnKleur("Bloeikleur", multiKleur.Value(), ids);
        } else if (singleKleur.getDoSearch()) {
            ids = FilterOnKleurSingle("Bloeikleur", singleKleur.Value(), ids);
        }

        //bladkleur
        multiKleur = bindingData.searchRequestArrayData.get(ERequestArrayData.BLADKLEURPERMAAND);
        singleKleur = bindingData.searchRequestData.get(ERequestData.BLADKLEUR);
        if (multiKleur.getDoSearch()) {
            ids = FilterOnKleur("Bladkleur", multiKleur.Value(), ids);
        } else if (singleKleur.getDoSearch()) {
            ids = FilterOnKleurSingle("Bladkleur", singleKleur.Value(), ids);
        }

        //endregion

        //Output
        return ids;
    }

    /**
     * @author Siebe
     * @param eigenschap -> name of the property to filter on
     * @param value -> value that the property should have /month
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnMinHoogte(String eigenschap, RequestValueWBool[] value, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiHoogteMin = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTIHOOGTEMIN, plantIds);

        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 2, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 3, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 4, value[2].getValue());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 5, value[3].getValue());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 6, value[4].getValue());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 7, value[5].getValue());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 8, value[6].getValue());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 9, value[7].getValue());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 10, value[8].getValue());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 11, value[9].getValue());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 12, value[10].getValue());
        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 13, value[11].getValue());

        ResultSet rs = stmtSelectIdsByFenoMultiHoogteMin.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    /**
     * @author Siebe
     * @param eigenschap  -> name of the property to filter on
     * @param value -> value that the property should have /month
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnMaxHoogte(String eigenschap, RequestValueWBool[] value, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiHoogteMax = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTIHOOGTEMAX, plantIds);

        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 2, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 3, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 4, value[2].getValue());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 5, value[3].getValue());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 6, value[4].getValue());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 7, value[5].getValue());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 8, value[6].getValue());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 9, value[7].getValue());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 10, value[8].getValue());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 11, value[9].getValue());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 12, value[10].getValue());
        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 13, value[11].getValue());

        ResultSet rs = stmtSelectIdsByFenoMultiHoogteMax.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    /**
     * @author Siebe
     * @param eigenschap -> name of the property to filter on
     * @param value -> value that the property should have in general
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnHoogteSingle(String eigenschap, RequestValueWBool[] value, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiHoogteSingle = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTIHOOGTESINGLE, plantIds);

        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 2, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 3, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 4, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 5, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 6, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 7, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 8, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 9, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 10, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 11, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 12, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 13, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 14, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 15, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 16, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 17, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 18, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 19, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 20, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 21, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 22, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 23, value[1].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 24, value[0].getValue());
        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 25, value[1].getValue());

        ResultSet rs = stmtSelectIdsByFenoMultiHoogteSingle.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    /**
     * @author Siebe
     * @param eigenschap -> name of the property to filter on
     * @param value -> value that the property should have /month
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnKleur(String eigenschap, RequestValueWBool[] value, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiKleur = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTI, plantIds);

        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 2, value[0].getValue());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 3, value[1].getValue());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 4, value[2].getValue());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 5, value[3].getValue());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 6, value[4].getValue());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 7, value[5].getValue());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 8, value[6].getValue());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 9, value[7].getValue());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 10, value[8].getValue());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 11, value[9].getValue());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 12, value[10].getValue());
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 13, value[11].getValue());

        ResultSet rs = stmtSelectIdsByFenoMultiKleur.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    /**
     * @author Siebe
     * @param eigenschap -> name of the property to filter on
     * @param value -> value that the property should have in general
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnKleurSingle(String eigenschap, RequestValue value, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiKleurSingle = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTISINGLE, plantIds);

        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 2, value.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 3, value.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 4, value.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 5, value.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 6, value.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 7, value.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 8, value.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 9, value.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 10, value.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 11, value.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 12, value.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 13, value.getValue());

        ResultSet rs = stmtSelectIdsByFenoMultiKleurSingle.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    //endregion
}

