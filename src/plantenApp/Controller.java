package plantenApp;



import plantenApp.java.dao.Database;
import plantenApp.java.dao.InfoTablesDAO;
import plantenApp.java.model.InfoTables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;


public class Controller {
    public ComboBox cboBladkleur;
    public ComboBox cboBloeikleur;
    public ComboBox cboReactieAnta;
    public ComboBox cboOntwikkel;
    public ComboBox cboLevensduur;
    public ComboBox cboRatioBloeiBlad;
    public ComboBox cboSpruitfenologie;
    public ComboBox cboBladkleurJan;
    public ComboBox cboBladkleurFeb;
    public ComboBox cboBladkleurMaa;
    public ComboBox cboBladkleurApr;
    public ComboBox cboBladkleurMei;
    public ComboBox cboBladkleurJun;
    public ComboBox cboBladkleurJul;
    public ComboBox cboBladkleurAug;
    public ComboBox cboBladkleurSep;
    public ComboBox cboBladkleurOkt;
    public ComboBox cboBladkleurNov;
    public ComboBox cboBladkleurDec;
    public ComboBox cboBloeikleurJan;
    public ComboBox cboBloeikleurFeb;
    public ComboBox cboBloeikleurMaa;
    public ComboBox cboBloeikleurApr;
    public ComboBox cboBloeikleurMei;
    public ComboBox cboBloeikleurJun;
    public ComboBox cboBloeikleurJul;
    public ComboBox cboBloeikleurAug;
    public ComboBox cboBloeikleurSep;
    public ComboBox cboBloeikleurOkt;
    public ComboBox cboBloeikleurNov;
    public ComboBox cboBloeikleurDec;
    private InfoTables infoTables;
    private Connection dbConnection;

    public void initialize() throws SQLException {
        dbConnection = Database.getInstance().getConnection();


        /*instellen exit, minimize en resize button*/
        //ScreenControl();


        /*infotabel object aanmaken*/
        /*InfoTablesDAO infotablesDAO = new InfoTablesDAO(dbConnection);
        infoTables = infotablesDAO.getInfoTables();

        /*comboboxes vullen*/
        //FillComboboxes(infoTables);


    }
    /**
     @param infotables -> lijst van alle lijsten van gegevens uit de naakte tabellen
     @author bradley, angelo
     Functie om comboboxes te vullen met alle gegevens uit de database
     */

    public void FillComboboxes(InfoTables infotables){
        /*
        //type
        cboType.getItems().addAll(infotables.getTypes());
        //familie
        cboFamilie.getItems().addAll(infotables.getFamilies());
        //bladkleur
        cboBladkleur.getItems().addAll(infotables.getKleuren());
        //bloeikleur
        cboBloeikleur.getItems().addAll(infotables.getKleuren());
        //bladgrootte
        cboBladgrootte.getItems().addAll(infotables.getBladgroottes());
        //habitat
        cboHabitat.getItems().addAll(infotables.getHabitats());
        //bladkleuren (per maand)
        cboBladkleurJan.getItems().addAll(infotables.getKleuren());
        cboBladkleurFeb.getItems().addAll(infotables.getKleuren());
        cboBladkleurMaa.getItems().addAll(infotables.getKleuren());
        cboBladkleurApr.getItems().addAll(infotables.getKleuren());
        cboBladkleurMei.getItems().addAll(infotables.getKleuren());
        cboBladkleurJun.getItems().addAll(infotables.getKleuren());
        cboBladkleurJul.getItems().addAll(infotables.getKleuren());
        cboBladkleurAug.getItems().addAll(infotables.getKleuren());
        cboBladkleurSep.getItems().addAll(infotables.getKleuren());
        cboBladkleurOkt.getItems().addAll(infotables.getKleuren());
        cboBladkleurNov.getItems().addAll(infotables.getKleuren());
        cboBladkleurDec.getItems().addAll(infotables.getKleuren());
        //bloeikleuren (per maand)
        cboBloeikleurJan.getItems().addAll(infotables.getKleuren());
        cboBloeikleurFeb.getItems().addAll(infotables.getKleuren());
        cboBloeikleurMaa.getItems().addAll(infotables.getKleuren());
        cboBloeikleurApr.getItems().addAll(infotables.getKleuren());
        cboBloeikleurMei.getItems().addAll(infotables.getKleuren());
        cboBloeikleurJun.getItems().addAll(infotables.getKleuren());
        cboBloeikleurJul.getItems().addAll(infotables.getKleuren());
        cboBloeikleurAug.getItems().addAll(infotables.getKleuren());
        cboBloeikleurSep.getItems().addAll(infotables.getKleuren());
        cboBloeikleurOkt.getItems().addAll(infotables.getKleuren());
        cboBloeikleurNov.getItems().addAll(infotables.getKleuren());
        cboBloeikleurDec.getItems().addAll(infotables.getKleuren());
        //reactie antagonistische omgeving
        cboReactieAnta.getItems().addAll(infotables.getAntagonistischeOmgevingsReacties());
        //ontwikkelingssnelheid
        cboOntwikkel.getItems().addAll(infotables.getOnstwikkelingssnelheden());
        //levensduur/concurrentiekracht
        cboLevensduur.getItems().addAll(infotables.getConcurentiekrachten());
        //bladvorm
        cboBladvorm.getItems().addAll(infotables.getBladvormen());
        //ratio bloei/blad
        cboRatio.getItems().addAll(infotables.getBloeiBladRatios());
        //spruitfenologie
        cboSpruitFenologie.getItems().addAll(infotables.getSpruitfenologieen());
        //behandeling
        cboBehandeling.getItems().addAll(infotables.getBeheerdaden());
        //maand
        cboMaand.getItems().addAll("januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december");
    */
    }

    public void Clicked_Search(MouseEvent mouseEvent) {
    }
}


