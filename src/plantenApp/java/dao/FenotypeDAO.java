package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.model.data.*;
import plantenApp.java.model.data.enums.*;

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
     * @param plantIds -> The ids that need to be filtered
     * @param guiData
     * @return The filtered ids
     * @author Siebe
     */
    public ArrayList<Integer> FilterOn(ArrayList<Integer> plantIds, GUIdata guiData) throws SQLException {

        ArrayList<Integer> ids = new ArrayList<>();

        ComboBoxData bladvorm = guiData.comboBoxDEM.get(EComboBox.BLADVORM);
        RadiogroupData levensvorm = guiData.radiogroupDEM.get(ERadiogroup.LEVENSVORM);
        RadiogroupData habitus = guiData.radiogroupDEM.get(ERadiogroup.HABITUS);
        RadiogroupData bloeiwijze = guiData.radiogroupDEM.get(ERadiogroup.BLOEIWIJZE);
        ComboBoxData bladgrootte = guiData.comboBoxDEM.get(EComboBox.BLADGROOTTE);
        ComboBoxData ratiobloeiblad = guiData.comboBoxDEM.get(EComboBox.RATIOBLOEIBLAD);
        ComboBoxData spruitfenologie = guiData.comboBoxDEM.get(EComboBox.SPRUITFENOLOGIE);

        QueryBuilder QB = new QueryBuilder("plant_id", "fenotype");

        QB.AddIN("plant_id", plantIds);

        if (bladvorm.isDoSearch()) QB.AddBasicString("bladvorm", bladvorm.getValue());
        if (levensvorm.isDoSearch()) QB.AddBasicString("levensvorm", levensvorm.getActualValue());
        if (habitus.isDoSearch()) QB.AddBasicString("habitus", habitus.getActualValue());
        if (bloeiwijze.isDoSearch()) QB.AddBasicString("bloeiwijze", bloeiwijze.getActualValue());
        if (bladgrootte.isDoSearch()) QB.AddBasicString("bladgrootte", bladgrootte.getValue());
        if (ratiobloeiblad.isDoSearch()) QB.AddBasicString("ratio_bloei_blad", ratiobloeiblad.getValue());
        if (spruitfenologie.isDoSearch()) QB.AddBasicString("spruitfenologie", spruitfenologie.getValue());

        System.out.println(QB.getQuery());

        ResultSet rs = QB.PrepareStatement(dbConnection).executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //endregion

        //region Hoogtes

        MultiSpinnerData maxbloeihoogte = guiData.multiSpinnerDEM.get(EMultiSpinner.MAXBLOEIHOOGTEPERMAAND);
        MultiSpinnerData minbloeihoogte = guiData.multiSpinnerDEM.get(EMultiSpinner.MINBLOEIHOOGTEPERMAAND);
        MultiSpinnerData bloeihoogte = guiData.multiSpinnerDEM.get(EMultiSpinner.BLOEIHOOGTE);
        ids = filterOnHoogte("minbloeihoogte", "maxbloeihoogte", minbloeihoogte, maxbloeihoogte, bloeihoogte, ids);

        MultiSpinnerData maxbladhoogte = guiData.multiSpinnerDEM.get(EMultiSpinner.MAXBLADHOOGTEPERMAAND);
        MultiSpinnerData minbladhoogte = guiData.multiSpinnerDEM.get(EMultiSpinner.MINBLADHOOGTEPERMAAND);
        MultiSpinnerData bladhoogte = guiData.multiSpinnerDEM.get(EMultiSpinner.BLADHOOGTE);
        ids = filterOnHoogte("bladhoogte", "bladhoogte", minbladhoogte, maxbladhoogte, bladhoogte, ids);

        ComboBoxData bloeikleur = guiData.comboBoxDEM.get(EComboBox.BLOEIKLEUR);
        MultiComboBoxData bloeikleurpermaand = guiData.multiComboBoxDEM.get(EMultiComboBox.BLOEIKLEURPERMAAND);
        ids = filterOnKleur("bloeikleur", bloeikleurpermaand, bloeikleur, ids);

        ComboBoxData bladkleur = guiData.comboBoxDEM.get(EComboBox.BLADKLEUR);
        MultiComboBoxData bladkleurpermaand = guiData.multiComboBoxDEM.get(EMultiComboBox.BLADKLEURPERMAAND);
        ids = filterOnKleur("bladkleur", bladkleurpermaand, bladkleur, ids);

        //Output
        return ids;
    }

    private ArrayList<Integer> filterOnKleur(String eigenschap, MultiComboBoxData permaand, ComboBoxData basic, ArrayList<Integer> ids) throws SQLException {
        ResultSet rs;
        if (permaand.isDoSearch() && ids.size() > 0) {
            ArrayList<Integer> localIds = new ArrayList<>();
            QueryBuilder QBM = new QueryBuilder("plant_id", "fenotype_multi");
            String valueToIgnore = "nvt";

            QBM.AddIN("plant_id", ids);

            QBM.AddBasicString("eigenschap", eigenschap);

            if (!permaand.getValue(0).equals(valueToIgnore)) QBM.AddBasicString("jan", permaand.getValue(0));
            if (!permaand.getValue(1).equals(valueToIgnore)) QBM.AddBasicString("feb", permaand.getValue(1));
            if (!permaand.getValue(2).equals(valueToIgnore)) QBM.AddBasicString("maa", permaand.getValue(2));
            if (!permaand.getValue(3).equals(valueToIgnore)) QBM.AddBasicString("apr", permaand.getValue(3));
            if (!permaand.getValue(4).equals(valueToIgnore)) QBM.AddBasicString("mei", permaand.getValue(4));
            if (!permaand.getValue(5).equals(valueToIgnore)) QBM.AddBasicString("jun", permaand.getValue(5));
            if (!permaand.getValue(6).equals(valueToIgnore)) QBM.AddBasicString("jul", permaand.getValue(6));
            if (!permaand.getValue(7).equals(valueToIgnore)) QBM.AddBasicString("aug", permaand.getValue(7));
            if (!permaand.getValue(8).equals(valueToIgnore)) QBM.AddBasicString("sep", permaand.getValue(8));
            if (!permaand.getValue(9).equals(valueToIgnore)) QBM.AddBasicString("okt", permaand.getValue(9));
            if (!permaand.getValue(10).equals(valueToIgnore)) QBM.AddBasicString("nov", permaand.getValue(10));
            if (!permaand.getValue(11).equals(valueToIgnore)) QBM.AddBasicString("dec", permaand.getValue(11));

            System.out.println(QBM.getQuery());

            rs = QBM.PrepareStatement(dbConnection).executeQuery();
            while (rs.next()) {
                localIds.add(rs.getInt("plant_id"));
            }
            ids = localIds;
        } else if (basic.isDoSearch() && ids.size() > 0) {
            ArrayList<Integer> localIds = new ArrayList<>();
            QueryBuilder QBM = new QueryBuilder("plant_id", "fenotype_multi");
            String kleur = basic.getValue();

            QBM.AddIN("plant_id", ids);

            QBM.AddBasicString("eigenschap", eigenschap);

            QBM.StartOr();
            QBM.AddORString("jan", kleur);
            QBM.AddORString("feb", kleur);
            QBM.AddORString("maa", kleur);
            QBM.AddORString("apr", kleur);
            QBM.AddORString("mei", kleur);
            QBM.AddORString("jun", kleur);
            QBM.AddORString("jul", kleur);
            QBM.AddORString("aug", kleur);
            QBM.AddORString("sep", kleur);
            QBM.AddORString("okt", kleur);
            QBM.AddORString("nov", kleur);
            QBM.AddORString("dec", kleur);
            QBM.EndOr();

            System.out.println(QBM.getQuery());

            rs = QBM.PrepareStatement(dbConnection).executeQuery();
            while (rs.next()) {
                localIds.add(rs.getInt("plant_id"));
            }
            ids = localIds;
        }
        return ids;
    }

    public ArrayList<Integer> filterOnHoogte(String minEigenschap, String maxEigenschap, MultiSpinnerData minData, MultiSpinnerData maxData, MultiSpinnerData basicData, ArrayList<Integer> ids) throws SQLException {
        ResultSet rs;
        if (minData.isDoSearch() && ids.size() > 0) {
            ArrayList<Integer> localIds = new ArrayList<>();
            QueryBuilder QBM = new QueryBuilder("plant_id", "fenotype_multi");
            int valueToIgnore = -1;

            QBM.AddIN("plant_id", ids);

            QBM.AddBasicString("eigenschap", minEigenschap);

            if (minData.getValue(0) != valueToIgnore) QBM.AddIsBiggerThan("jan", minData.getValue(0));
            if (minData.getValue(1) != valueToIgnore) QBM.AddIsBiggerThan("feb", minData.getValue(1));
            if (minData.getValue(2) != valueToIgnore) QBM.AddIsBiggerThan("maa", minData.getValue(2));
            if (minData.getValue(3) != valueToIgnore) QBM.AddIsBiggerThan("apr", minData.getValue(3));
            if (minData.getValue(4) != valueToIgnore) QBM.AddIsBiggerThan("mei", minData.getValue(4));
            if (minData.getValue(5) != valueToIgnore) QBM.AddIsBiggerThan("jun", minData.getValue(5));
            if (minData.getValue(6) != valueToIgnore) QBM.AddIsBiggerThan("jul", minData.getValue(6));
            if (minData.getValue(7) != valueToIgnore) QBM.AddIsBiggerThan("aug", minData.getValue(7));
            if (minData.getValue(8) != valueToIgnore) QBM.AddIsBiggerThan("sep", minData.getValue(8));
            if (minData.getValue(9) != valueToIgnore) QBM.AddIsBiggerThan("okt", minData.getValue(9));
            if (minData.getValue(10) != valueToIgnore) QBM.AddIsBiggerThan("nov", minData.getValue(10));
            if (minData.getValue(11) != valueToIgnore) QBM.AddIsBiggerThan("dec", minData.getValue(11));

            System.out.println(QBM.getQuery());

            rs = QBM.PrepareStatement(dbConnection).executeQuery();
            while (rs.next()) {
                localIds.add(rs.getInt("plant_id"));
            }
            ids = localIds;
        } else if (basicData.isDoSearch() && ids.size() > 0) {
            ArrayList<Integer> localIds = new ArrayList<>();
            QueryBuilder QBM = new QueryBuilder("plant_id", "fenotype_multi");
            int valueToIgnore = -1;
            int min = basicData.getValue(0);

            QBM.AddIN("plant_id", ids);

            QBM.AddBasicString("eigenschap", minEigenschap);

            if (min != valueToIgnore) QBM.AddIsBiggerThan("jan", min);
            if (min != valueToIgnore) QBM.AddIsBiggerThan("feb", min);
            if (min != valueToIgnore) QBM.AddIsBiggerThan("maa", min);
            if (min != valueToIgnore) QBM.AddIsBiggerThan("apr", min);
            if (min != valueToIgnore) QBM.AddIsBiggerThan("mei", min);
            if (min != valueToIgnore) QBM.AddIsBiggerThan("jun", min);
            if (min != valueToIgnore) QBM.AddIsBiggerThan("jul", min);
            if (min != valueToIgnore) QBM.AddIsBiggerThan("aug", min);
            if (min != valueToIgnore) QBM.AddIsBiggerThan("sep", min);
            if (min != valueToIgnore) QBM.AddIsBiggerThan("okt", min);
            if (min != valueToIgnore) QBM.AddIsBiggerThan("nov", min);
            if (min != valueToIgnore) QBM.AddIsBiggerThan("dec", min);

            System.out.println(QBM.getQuery());

            rs = QBM.PrepareStatement(dbConnection).executeQuery();
            while (rs.next()) {
                localIds.add(rs.getInt("plant_id"));
            }
            ids = localIds;
        }
        if (maxData.isDoSearch() && ids.size() > 0) {
            ArrayList<Integer> localIds = new ArrayList<>();
            QueryBuilder QBM = new QueryBuilder("plant_id", "fenotype_multi");
            int valueToIgnore = -1;

            QBM.AddIN("plant_id", ids);

            QBM.AddBasicString("eigenschap", maxEigenschap);

            if (maxData.getValue(0) != valueToIgnore) QBM.AddIsSmallerThan("jan", maxData.getValue(0));
            if (maxData.getValue(1) != valueToIgnore) QBM.AddIsSmallerThan("feb", maxData.getValue(1));
            if (maxData.getValue(2) != valueToIgnore) QBM.AddIsSmallerThan("maa", maxData.getValue(2));
            if (maxData.getValue(3) != valueToIgnore) QBM.AddIsSmallerThan("apr", maxData.getValue(3));
            if (maxData.getValue(4) != valueToIgnore) QBM.AddIsSmallerThan("mei", maxData.getValue(4));
            if (maxData.getValue(5) != valueToIgnore) QBM.AddIsSmallerThan("jun", maxData.getValue(5));
            if (maxData.getValue(6) != valueToIgnore) QBM.AddIsSmallerThan("jul", maxData.getValue(6));
            if (maxData.getValue(7) != valueToIgnore) QBM.AddIsSmallerThan("aug", maxData.getValue(7));
            if (maxData.getValue(8) != valueToIgnore) QBM.AddIsSmallerThan("sep", maxData.getValue(8));
            if (maxData.getValue(9) != valueToIgnore) QBM.AddIsSmallerThan("okt", maxData.getValue(9));
            if (maxData.getValue(10) != valueToIgnore) QBM.AddIsSmallerThan("nov", maxData.getValue(10));
            if (maxData.getValue(11) != valueToIgnore) QBM.AddIsSmallerThan("dec", maxData.getValue(11));

            System.out.println(QBM.getQuery());

            rs = QBM.PrepareStatement(dbConnection).executeQuery();
            while (rs.next()) {
                localIds.add(rs.getInt("plant_id"));
            }
            ids = localIds;
        } else if (basicData.isDoSearch() && ids.size() > 0) {
            ArrayList<Integer> localIds = new ArrayList<>();
            QueryBuilder QBM = new QueryBuilder("plant_id", "fenotype_multi");
            int valueToIgnore = -1;
            int max = basicData.getValue(1);

            QBM.AddIN("plant_id", ids);

            QBM.AddBasicString("eigenschap", maxEigenschap);

            if (max != valueToIgnore) QBM.AddIsSmallerThan("jan", max);
            if (max != valueToIgnore) QBM.AddIsSmallerThan("feb", max);
            if (max != valueToIgnore) QBM.AddIsSmallerThan("maa", max);
            if (max != valueToIgnore) QBM.AddIsSmallerThan("apr", max);
            if (max != valueToIgnore) QBM.AddIsSmallerThan("mei", max);
            if (max != valueToIgnore) QBM.AddIsSmallerThan("jun", max);
            if (max != valueToIgnore) QBM.AddIsSmallerThan("jul", max);
            if (max != valueToIgnore) QBM.AddIsSmallerThan("aug", max);
            if (max != valueToIgnore) QBM.AddIsSmallerThan("sep", max);
            if (max != valueToIgnore) QBM.AddIsSmallerThan("okt", max);
            if (max != valueToIgnore) QBM.AddIsSmallerThan("nov", max);
            if (max != valueToIgnore) QBM.AddIsSmallerThan("dec", max);

            System.out.println(QBM.getQuery());

            rs = QBM.PrepareStatement(dbConnection).executeQuery();
            while (rs.next()) {
                localIds.add(rs.getInt("plant_id"));
            }
            ids = localIds;
        }
        return ids;
    }

    //endregion
}

