package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.model.data.*;
import plantenApp.java.model.data.enums.EComboBox;
import plantenApp.java.model.data.enums.EMultiComboBox;
import plantenApp.java.model.data.enums.EMultiSpinner;
import plantenApp.java.model.data.enums.ERadiogroup;
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
     * @param guiData
     * @return The filtered ids
     */
    public ArrayList<Integer> FilterOn(ArrayList<Integer> plantIds, GUIdata guiData) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFeno = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENO, plantIds);

        //region StandaardFilter
        //bladvorm
        ComboBoxData bladvorm = guiData.comboBoxDEM.get(EComboBox.BLADVORM);
        //SearchRequest<RequestValue> bladvorm = bindingData.searchRequestData.get(ERequestData.BLADVORM);
        stmtSelectIdsByFeno.setString(plantIds.size() + 1, bladvorm.getValue());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 2, (bladvorm.isDoSearch()) ? 0 : 1);

        //levensvorm
        RadiogroupData levensvorm = guiData.radiogroupDEM.get(ERadiogroup.LEVENSVORM);
        //SearchRequest<RequestValueWBool[]> levensvorm = bindingData.searchRequestArrayData.get(ERequestArrayData.LEVENSVORM);
        stmtSelectIdsByFeno.setString(plantIds.size() + 3, levensvorm.getActualValue());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 4, (levensvorm.isDoSearch()) ? 0 : 1);

        //habitus
        RadiogroupData habitus = guiData.radiogroupDEM.get(ERadiogroup.LEVENSVORM);
        //SearchRequest<RequestValueWBool[]> habitus = bindingData.searchRequestArrayData.get(ERequestArrayData.HABITUS);
        stmtSelectIdsByFeno.setString(plantIds.size() + 5, habitus.getActualValue());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 6, (habitus.isDoSearch()) ? 0 : 1);

        //bloeiwijze
        RadiogroupData bloeiwijze = guiData.radiogroupDEM.get(ERadiogroup.LEVENSVORM);
        //SearchRequest<RequestValueWBool[]> bloeiwijze = bindingData.searchRequestArrayData.get(ERequestArrayData.BLOEIWIJZE);
        stmtSelectIdsByFeno.setString(plantIds.size() + 7, bloeiwijze.getActualValue());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 8, (bloeiwijze.isDoSearch()) ? 0 : 1);

        //bladgrootte
        ComboBoxData bladgrootte = guiData.comboBoxDEM.get(EComboBox.BLADGROOTTE);
        //SearchRequest<RequestValue> bladgrootte = bindingData.searchRequestData.get(ERequestData.BLADGROOTTE);
        stmtSelectIdsByFeno.setInt(plantIds.size() + 9, Integer.parseInt(bladgrootte.getValue()));
        stmtSelectIdsByFeno.setInt(plantIds.size() + 10, (bladgrootte.isDoSearch()) ? 0 : 1);

        //ratiobloeiblad
        ComboBoxData ratiobloeiblad = guiData.comboBoxDEM.get(EComboBox.RATIOBLOEIBLAD);
        //SearchRequest<RequestValue> ratiobloeiblad = bindingData.searchRequestData.get(ERequestData.RATIOBLOEIBLAD);
        stmtSelectIdsByFeno.setString(plantIds.size() + 11, ratiobloeiblad.getValue());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 12, (ratiobloeiblad.isDoSearch()) ? 0 : 1);

        //spruitfenologie
        ComboBoxData spruitfenologie = guiData.comboBoxDEM.get(EComboBox.SPRUITFENOLOGIE);
        //SearchRequest<RequestValue> spruitfenologie = bindingData.searchRequestData.get(ERequestData.SPRUITFENOLOGIE);
        stmtSelectIdsByFeno.setString(plantIds.size() + 13, spruitfenologie.getValue());
        stmtSelectIdsByFeno.setInt(plantIds.size() + 14, (spruitfenologie.isDoSearch()) ? 0 : 1);

        ResultSet rs = stmtSelectIdsByFeno.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //endregion

        //region Hoogtes
        MultiSpinnerData multiHoogteMin;
        MultiSpinnerData multiHoogteMax;
        MultiSpinnerData singleHoogte;

        //Bloeihoogte
        multiHoogteMin = guiData.multiSpinnerDEM.get(EMultiSpinner.MINBLOEIHOOGTEPERMAAND);
        multiHoogteMax = guiData.multiSpinnerDEM.get(EMultiSpinner.MAXBLOEIHOOGTEPERMAAND);
        singleHoogte = guiData.multiSpinnerDEM.get(EMultiSpinner.BLOEIHOOGTE);

        if (multiHoogteMin.isDoSearch()) {
            ids = FilterOnMinHoogte("bloeihoogte", multiHoogteMin, ids);
        }
        if (multiHoogteMax.isDoSearch()) {
            ids = FilterOnMaxHoogte("bloeihoogte", multiHoogteMax, ids);
        }
        if (singleHoogte.isDoSearch()) {
            ids = FilterOnHoogteSingle("bloeihoogte", singleHoogte, ids);
        }



        //Bladhoogte
        multiHoogteMin = guiData.multiSpinnerDEM.get(EMultiSpinner.MINBLADHOOGTEPERMAAND);
        multiHoogteMax = guiData.multiSpinnerDEM.get(EMultiSpinner.MAXBLADHOOGTEPERMAAND);
        singleHoogte = guiData.multiSpinnerDEM.get(EMultiSpinner.BLADHOOGTE);

        if (multiHoogteMin.isDoSearch()) {
            ids = FilterOnMinHoogte("bladhoogte", multiHoogteMin, plantIds);
        }
        if (multiHoogteMax.isDoSearch()) {
            ids = FilterOnMaxHoogte("bladhoogte", multiHoogteMax, ids);
        }
        if (singleHoogte.isDoSearch()) {
            ids = FilterOnHoogteSingle("bladhoogte", singleHoogte, ids);
        }

        //endregion

        //region Kleuren
        MultiComboBoxData multiKleur;
        ComboBoxData singleKleur;

        //bloeikleur
        multiKleur = guiData.multiComboBoxDEM.get(EMultiComboBox.BLOEIKLEURPERMAAND);
        singleKleur = guiData.comboBoxDEM.get(EComboBox.BLOEIKLEUR);
        if (multiKleur.isDoSearch()) {
            ids = FilterOnKleur("Bloeikleur", multiKleur, ids);
        } else if (singleKleur.isDoSearch()) {
            ids = FilterOnKleurSingle("Bloeikleur", singleKleur, ids);
        }

        //bladkleur
        multiKleur = guiData.multiComboBoxDEM.get(EMultiComboBox.BLADKLEURPERMAAND);
        singleKleur = guiData.comboBoxDEM.get(EComboBox.BLADKLEUR);
        if (multiKleur.isDoSearch()) {
            ids = FilterOnKleur("Bladkleur", multiKleur, ids);
        } else if (singleKleur.isDoSearch()) {
            ids = FilterOnKleurSingle("Bladkleur", singleKleur, ids);
        }

        //endregion

        //Output
        return ids;
    }

    /**
     * @author Siebe
     * @param eigenschap -> name of the property to filter on
     * @param data -> value that the property should have /month
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnMinHoogte(String eigenschap, MultiSpinnerData data, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiHoogteMin = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTIHOOGTEMIN, plantIds);

        stmtSelectIdsByFenoMultiHoogteMin.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 2, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 3, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 4, data.getValue(2));
        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 5, data.getValue(3));
        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 6, data.getValue(4));
        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 7, data.getValue(5));
        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 8, data.getValue(6));
        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 9, data.getValue(7));
        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 10, data.getValue(8));
        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 11, data.getValue(9));
        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 12, data.getValue(10));
        stmtSelectIdsByFenoMultiHoogteMin.setInt(plantIds.size() + 13, data.getValue(11));

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
     * @param data -> value that the property should have /month
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnMaxHoogte(String eigenschap, MultiSpinnerData data, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiHoogteMax = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTIHOOGTEMAX, plantIds);

        stmtSelectIdsByFenoMultiHoogteMax.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 2, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 3, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 4, data.getValue(2));
        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 5, data.getValue(3));
        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 6, data.getValue(4));
        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 7, data.getValue(5));
        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 8, data.getValue(6));
        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 9, data.getValue(7));
        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 10, data.getValue(8));
        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 11, data.getValue(9));
        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 12, data.getValue(10));
        stmtSelectIdsByFenoMultiHoogteMax.setInt(plantIds.size() + 13, data.getValue(11));

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
     * @param data -> value that the property should have in general
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnHoogteSingle(String eigenschap, MultiSpinnerData data, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiHoogteSingle = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTIHOOGTESINGLE, plantIds);

        stmtSelectIdsByFenoMultiHoogteSingle.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 2, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 3, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 4, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 5, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 6, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 7, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 8, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 9, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 10, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 11, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 12, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 13, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 14, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 15, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 16, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 17, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 18, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 19, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 20, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 21, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 22, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 23, data.getValue(1));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 24, data.getValue(0));
        stmtSelectIdsByFenoMultiHoogteSingle.setInt(plantIds.size() + 25, data.getValue(1));

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
     * @param data -> value that the property should have /month
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnKleur(String eigenschap, MultiComboBoxData data, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiKleur = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTI, plantIds);

        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 2, data.getValue(0));
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 3, data.getValue(1));
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 4, data.getValue(2));
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 5, data.getValue(3));
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 6, data.getValue(4));
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 7, data.getValue(5));
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 8, data.getValue(6));
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 9, data.getValue(7));
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 10, data.getValue(8));
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 11, data.getValue(9));
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 12, data.getValue(10));
        stmtSelectIdsByFenoMultiKleur.setString(plantIds.size() + 13, data.getValue(11));

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
     * @param data -> value that the property should have in general
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     */
    private ArrayList<Integer> FilterOnKleurSingle(String eigenschap, ComboBoxData data, ArrayList<Integer> plantIds) throws SQLException {
        //Dao

        //Items
        ArrayList<Integer> ids = new ArrayList<>();

        //SQLcommand
        PreparedStatement stmtSelectIdsByFenoMultiKleurSingle = DaoUtils.ReadyStatement(dbConnection, GETIDSBYFENOMULTISINGLE, plantIds);

        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 1, eigenschap);

        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 2, data.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 3, data.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 4, data.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 5, data.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 6, data.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 7, data.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 8, data.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 9, data.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 10, data.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 11, data.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 12, data.getValue());
        stmtSelectIdsByFenoMultiKleurSingle.setString(plantIds.size() + 13, data.getValue());

        ResultSet rs = stmtSelectIdsByFenoMultiKleurSingle.executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Output
        return ids;
    }

    //endregion
}

