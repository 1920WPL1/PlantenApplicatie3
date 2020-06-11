package plantenApp.java.dao;

import plantenApp.java.model.*;
import plantenApp.java.model.data.*;
import plantenApp.java.model.data.enums.EComboBox;
import plantenApp.java.model.data.enums.EMultiCheckbox;
import plantenApp.java.model.data.enums.ERadiogroup;
import plantenApp.java.model.data.enums.ESliderLabel;
import plantenApp.java.utils.DaoUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Siebe
 */
public class CommensalismeDAO implements Queries {
    private Connection dbConnection;
    private PreparedStatement stmtSelectCommeByID;
    private PreparedStatement stmtSelectCommeMultiByID;

    public CommensalismeDAO(Connection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;

        stmtSelectCommeByID = dbConnection.prepareStatement(GETCOMMENSALISMEBYPLANTID);
        stmtSelectCommeMultiByID = dbConnection.prepareStatement(GETCOMMENSALISMEMULTIBYPLANTID);
    }

    //region GET

    /**
     * @param id -> plant_id
     * @return alle Commensalisme van de specifieke plant
     * @author Siebe
     */
    public Commensalisme getById(int id) throws SQLException {
        //Dao

        //Items
        Commensalisme comm = null;

        //SqlCommand
        stmtSelectCommeByID.setInt(1, id);
        ResultSet rs = stmtSelectCommeByID.executeQuery();
        if (rs.next()) {
            comm = new Commensalisme(
                    rs.getInt("commensalisme_id"),
                    rs.getInt("plant_id"),
                    rs.getString("strategie"),
                    rs.getString("ontwikkelingssnelheid"),
                    getByIdMulti(id)
            );
        }

        //Output
        return comm;
    }

    /**
     * @param id -> plant_id
     * @return alle commensalisme_multi van de specifieke plant
     * @author Siebe
     * word alleen gebruikt in getById
     */
    private ArrayList<CommMulti_Eigenschap> getByIdMulti(int id) throws SQLException {
        //Dao

        //Items
        ArrayList<CommMulti_Eigenschap> commMulti = new ArrayList<>();

        //SqlCommand
        stmtSelectCommeMultiByID.setInt(1, id);
        ResultSet rs = stmtSelectCommeMultiByID.executeQuery();
        while (rs.next()) {
            CommMulti_Eigenschap commEigenschap = new CommMulti_Eigenschap(
                    rs.getInt("commensalisme_id"),
                    rs.getString("eigenschap"),
                    rs.getString("waarde")
            );
            commMulti.add(commEigenschap);
        }

        //Output
        return commMulti;
    }

    //endregion

    //region FILTER

    /**
     * @param plantIds -> The ids that need to be filtered
     * @return The filtered ids
     * @author Siebe
     */
    public ArrayList<Integer> FilterOn(ArrayList<Integer> plantIds, GUIdata guIdata) throws SQLException {
        ArrayList<Integer> ids = new ArrayList<>();

        RadiogroupData strategie = guIdata.radiogroupDEM.get(ERadiogroup.STRATEGIE);
        ComboBoxData ontwikkelingssnelheid = guIdata.comboBoxDEM.get(EComboBox.ONTWIKKELINGSSNELHEID);

        QueryBuilder QB = new QueryBuilder("plant_id", "commensalisme");

        QB.AddIN("plant_id", plantIds);

        if (strategie.isDoSearch()) QB.AddBasicString("strategie", strategie.getActualValue());
        if (ontwikkelingssnelheid.isDoSearch())
            QB.AddBasicString("ontwikkelingssnelheid", ontwikkelingssnelheid.getValue());

        System.out.println(QB.getQuery());

        ResultSet rs = QB.PrepareStatement(dbConnection).executeQuery();
        while (rs.next()) {
            ids.add(rs.getInt("plant_id"));
        }

        //Multi
        MultiCheckboxData sociabiliteit = guIdata.multiCheckboxDEM.get(EMultiCheckbox.SOCIABILITEIT);
        if (sociabiliteit.isDoSearch() && ids.size() > 0) {
            for (int i = 0; i < sociabiliteit.Length(); i++) {
                if (sociabiliteit.getValue(i)) {
                    ArrayList<Integer> localIds = new ArrayList<>();
                    QueryBuilder QBM = new QueryBuilder("plant_id", "commensalisme_multi");

                    QBM.AddIN("plant_id", plantIds);

                    QBM.AddBasicString("eigenschap", "sociabiliteit");
                    QBM.AddBasicInt("waarde", i + 1);

                    System.out.println(QBM.getQuery());

                    rs = QBM.PrepareStatement(dbConnection).executeQuery();
                    while (rs.next()) {
                        localIds.add(rs.getInt("plant_id"));
                    }
                    ids = localIds;
                }
            }
        }
        ComboBoxData levensduur = guIdata.comboBoxDEM.get(EComboBox.LEVENSDUUR);
        if (levensduur.isDoSearch() && ids.size() > 0) {
            ArrayList<Integer> localIds = new ArrayList<>();
            QueryBuilder QBM = new QueryBuilder("plant_id", "commensalisme_multi");

            QBM.AddIN("plant_id", plantIds);

            QBM.AddBasicString("eigenschap", "levensduur");
            QBM.AddBasicString("waarde", levensduur.getValue());

            System.out.println(QBM.getQuery());

            rs = QBM.PrepareStatement(dbConnection).executeQuery();
            while (rs.next()) {
                localIds.add(rs.getInt("plant_id"));
            }
            ids = localIds;
        }

        //Output
        return ids;
    }
}
