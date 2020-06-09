package plantenApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import plantenApp.java.dao.Database;
import plantenApp.java.dao.InfoTablesDAO;
import plantenApp.java.model.InfoTables;
import plantenApp.java.model.Plant;
import plantenApp.java.model.SearchHandler;
import plantenApp.java.model.data.GUIdata;
import plantenApp.java.model.data.enums.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {
    public ComboBox<String> cboBladkleur;
    public ComboBox<String> cboBloeikleur;
    public ComboBox<String> cboReactieAnta;
    public ComboBox<String> cboOntwikkel;
    public ComboBox<String> cboLevensduur;
    public ComboBox<String> cboRatioBloeiBlad;
    public ComboBox<String> cboSpruitfenologie;
    public ComboBox<String> cboBladkleurJan;
    public ComboBox<String> cboBladkleurFeb;
    public ComboBox<String> cboBladkleurMaa;
    public ComboBox<String> cboBladkleurApr;
    public ComboBox<String> cboBladkleurMei;
    public ComboBox<String> cboBladkleurJun;
    public ComboBox<String> cboBladkleurJul;
    public ComboBox<String> cboBladkleurAug;
    public ComboBox<String> cboBladkleurSep;
    public ComboBox<String> cboBladkleurOkt;
    public ComboBox<String> cboBladkleurNov;
    public ComboBox<String> cboBladkleurDec;
    public ComboBox<String> cboBloeikleurJan;
    public ComboBox<String> cboBloeikleurFeb;
    public ComboBox<String> cboBloeikleurMaa;
    public ComboBox<String> cboBloeikleurApr;
    public ComboBox<String> cboBloeikleurMei;
    public ComboBox<String> cboBloeikleurJun;
    public ComboBox<String> cboBloeikleurJul;
    public ComboBox<String> cboBloeikleurAug;
    public ComboBox<String> cboBloeikleurSep;
    public ComboBox<String> cboBloeikleurOkt;
    public ComboBox<String> cboBloeikleurNov;
    public ComboBox<String> cboBloeikleurDec;
    public TextField txtSearch;
    public Spinner<Integer> nudMinBladhoogte;
    public Spinner<Integer> nudMaxBladhoogte;
    public Spinner<Integer> nudMinBloeihoogte;
    public Spinner<Integer> nudMaxBloeihoogte;
    public Slider sldBezonning;
    public Slider sldVochtbehoefte;
    public CheckBox chkGrondsoort_Z;
    public CheckBox chkGrondsoort_L;
    public CheckBox chkGrondsoort_K;
    public CheckBox chkVoedingsbehoefte;
    public CheckBox chkReactieAntagonistischeOmg;
    public CheckBox chkOntwikkelingssnelheid;
    public CheckBox chkLevensduur_concurrentiekracht;
    public CheckBox chkSociabiliteit;
    public CheckBox chkStrategie;
    public CheckBox chkNectarwaarde;
    public CheckBox chkPollenwaarde;
    public CheckBox chkBijvriendelijk;
    public CheckBox chkVlindervriendelijk;
    public CheckBox chkEetbaar;
    public CheckBox chkKruidgebruik;
    public CheckBox chkGeurend;
    public CheckBox chkVorstgevoelig;
    public CheckBox chkBladvorm;
    public CheckBox chkRatio_bloei_blad;
    public CheckBox chkSpruitfenologie;
    public CheckBox chkBehandeling;
    public CheckBox chkMaand;
    public CheckBox chkPerXJaar;
    public Slider sldVoedingsbehoefte;
    public CheckBox chkSociabiliteit_1;
    public CheckBox chkSociabiliteit_2;
    public CheckBox chkSociabiliteit_3;
    public CheckBox chkSociabiliteit_4;
    public CheckBox chkSociabiliteit_5;
    public RadioButton rdbStrategie_Onbekend;
    public RadioButton rdbStrategie_C;
    public RadioButton rdbStrategie_CR;
    public RadioButton rdbStrategie_CRS;
    public RadioButton rdbStrategie_CS;
    public RadioButton rdbStrategie_R;
    public RadioButton rdbStrategie_RS;
    public RadioButton rdbStrategie_S;
    public Slider sldNectarwaarde;
    public Slider sldPollenwaarde;
    public RadioButton rdbBijvriendelijk_Ja;
    public RadioButton rdbBijvriendelijk_Nee;
    public RadioButton rdbBijvriendelijk_Onbekend;
    public RadioButton rdbVlindervriendelijk_Ja;
    public RadioButton rdbVlindervriendelijk_Nee;
    public RadioButton rdbVlindervriendelijk_Onbekend;
    public RadioButton rdbEetbaar_Ja;
    public RadioButton rdbEetbaar_Nee;
    public RadioButton rdbEetbaar_Onbekend;
    public RadioButton rdbKruidgebruik_Ja;
    public RadioButton rdbKruidgebruik_Nee;
    public RadioButton rdbKruidgebruik_Onbekend;
    public RadioButton rdbGeurend_Ja;
    public RadioButton rdbGeurend_Nee;
    public RadioButton rdbGeurend_Onbekend;
    public RadioButton rdbVorstgevoelig_Ja;
    public RadioButton rdbVorstgevoelig_Nee;
    public RadioButton rdbVorstgevoelig_Onbekend;
    public Spinner<Integer> nudPerXJaar;
    public CheckBox chkMaxBladhoogtePerMaand;
    public Spinner<Integer> nudMaxBladhoogte_Jan;
    public Spinner<Integer> nudMaxBladhoogte_Feb;
    public Spinner<Integer> nudMaxBladhoogte_Maa;
    public Spinner<Integer> nudMaxBladhoogte_Apr;
    public Spinner<Integer> nudMaxBladhoogte_Mei;
    public Spinner<Integer> nudMaxBladhoogte_Jun;
    public Spinner<Integer> nudMaxBladhoogte_Jul;
    public Spinner<Integer> nudMaxBladhoogte_Aug;
    public Spinner<Integer> nudMaxBladhoogte_Sept;
    public Spinner<Integer> nudMaxBladhoogte_Okt;
    public Spinner<Integer> nudMaxBladhoogte_Nov;
    public Spinner<Integer> nudMaxBladhoogte_Dec;
    public CheckBox chkBladkleurPerMaand;
    public CheckBox chkMinBloeihoogtePerMaand;
    public Spinner<Integer> nudMinBloeihoogte_Jan;
    public Spinner<Integer> nudMinBloeihoogte_Feb;
    public Spinner<Integer> nudMinBloeihoogte_Maa;
    public Spinner<Integer> nudMinBloeihoogte_Apr;
    public Spinner<Integer> nudMinBloeihoogte_Mei;
    public Spinner<Integer> nudMinBloeihoogte_Jun;
    public Spinner<Integer> nudMinBloeihoogte_Jul;
    public Spinner<Integer> nudMinBloeihoogte_Aug;
    public Spinner<Integer> nudMinBloeihoogte_Sept;
    public Spinner<Integer> nudMinBloeihoogte_Okt;
    public Spinner<Integer> nudMinBloeihoogte_Nov;
    public Spinner<Integer> nudMinBloeihoogte_Dec;
    public CheckBox chkMaxBloeihoogtePerMaand;
    public Spinner<Integer> nudMaxBloeihoogte_Jan;
    public Spinner<Integer> nudMaxBloeihoogte_Feb;
    public Spinner<Integer> nudMaxBloeihoogte_Maa;
    public Spinner<Integer> nudMaxBloeihoogte_Apr;
    public Spinner<Integer> nudMaxBloeihoogte_Mei;
    public Spinner<Integer> nudMaxBloeihoogte_Jun;
    public Spinner<Integer> nudMaxBloeihoogte_Jul;
    public Spinner<Integer> nudMaxBloeihoogte_Aug;
    public Spinner<Integer> nudMaxBloeihoogte_Sept;
    public Spinner<Integer> nudMaxBloeihoogte_Okt;
    public Spinner<Integer> nudMaxBloeihoogte_Nov;
    public Spinner<Integer> nudMaxBloeihoogte_Dec;
    public CheckBox chkBloeikleurPerMaand;
    public CheckBox chkHabitus;
    public RadioButton rdbHabitus_1;
    public RadioButton rdbHabitus_2;
    public RadioButton rdbHabitus_3;
    public RadioButton rdbHabitus_4;
    public RadioButton rdbHabitus_5;
    public RadioButton rdbHabitus_6;
    public RadioButton rdbHabitus_7;
    public RadioButton rdbHabitus_8;
    public RadioButton rdbHabitus_9;
    public RadioButton rdbHabitus_10;
    public RadioButton rdbHabitus_11;
    public RadioButton rdbHabitus_12;
    public RadioButton rdbHabitus_13;
    public RadioButton rdbHabitus_14;
    public RadioButton rdbHabitus_15;
    public CheckBox chkBloeiwijze;
    public RadioButton rdbBloeiwijze_Aar;
    public RadioButton rdbBloeiwijze_BredePluim;
    public RadioButton rdbBloeiwijze_Etage;
    public RadioButton rdbBloeiwijze_BolofKnop;
    public RadioButton rdbBloeiwijze_Margrietachtig;
    public RadioButton rdbBloeiwijze_Scherm;
    public RadioButton rdbBloeiwijze_SmallePluim;
    public CheckBox chkLevensvormVolgensRaunkhiaer;
    public RadioButton rdbLevensvorm_1;
    public RadioButton rdbLevensvorm_2;
    public RadioButton rdbLevensvorm_3;
    public RadioButton rdbLevensvorm_4;
    public RadioButton rdbLevensvorm_5;
    public RadioButton rdbLevensvorm_6;
    public RadioButton rdbLevensvorm_7;
    public RadioButton rdbLevensvorm_8;
    public RadioButton rdbLevensvorm_9;
    public ComboBox<String> cboType;
    public ComboBox<String> cboFamilie;
    public ComboBox<String> cboBladgrootte;
    public ComboBox<String> cboHabitat;
    public ComboBox<String> cboBladvorm;
    public RadioButton rdbBloeiwijze_Schotel;
    public CheckBox chkHabitat;
    public CheckBox chkType;
    public CheckBox chkFamilie;
    public CheckBox chkBladKleur;
    public CheckBox chkBladHoogte;
    public CheckBox chkBloeiKleur;
    public CheckBox chkBloeiHoogte;
    public CheckBox chkBezonning;
    public CheckBox chkVochtBehoefte;
    public CheckBox chkGrondsoort;
    public CheckBox chkBladgrootte;
    public ComboBox<String> cboBehandeling;
    public ComboBox<String> cboMaand;
    public VBox VboxOutput;
    public TitledPane pnlSearch;
    public TitledPane pnlAdvSearch;
    public Label lblVoedingsBehoefte;
    public Label lblBezonning;
    public Label lblVocht;
    public Label lblPollenwaarde;
    public Label lblNectarwaarde;
    public ToggleGroup tglBloeiwijze;
    public CheckBox chkMinBladhoogtePerMaand;
    public Spinner<Integer> nudMinBladhoogte_Jan;
    public Spinner<Integer> nudMinBladhoogte_Feb;
    public Spinner<Integer> nudMinBladhoogte_Maa;
    public Spinner<Integer> nudMinBladhoogte_Apr;
    public Spinner<Integer> nudMinBladhoogte_Mei;
    public Spinner<Integer> nudMinBladhoogte_Jun;
    public Spinner<Integer> nudMinBladhoogte_Jul;
    public Spinner<Integer> nudMinBladhoogte_Aug;
    public Spinner<Integer> nudMinBladhoogte_Sept;
    public Spinner<Integer> nudMinBladhoogte_Okt;
    public Spinner<Integer> nudMinBladhoogte_Nov;
    public Spinner<Integer> nudMinBladhoogte_Dec;
    public ImageView imgHabitus;
    public ImageView imgBlad;
    public ImageView imgBloei;
    public Label lblType;
    public Label lblFamilie;
    public Label lblGeslacht;
    public Label lblSoort;
    public Label lblVariant;
    public ListView lsvOverzicht;
    public Label lblDichtheidX;
    public Label lblDichtheidY;
    public Label lblOntwikkelingsSnelheid;
    public CheckBox chkSocPlantI;
    public CheckBox chkSocPlantII;
    public CheckBox chkSocPlantIII;
    public CheckBox chkSocPlantIV;
    public CheckBox chkSocPlantV;
    public Label lblStrategie;
    public Label lblBezonning2;
    public Label lblVochtBehoefte;
    public Label lblVoedingsBehoefte2;
    public Label lblReactie;
    public Label lblGrondSoort;
    public Label lblNectarwaarde2;
    public Label lblPollenwaarde2;
    public Label lblBijvriendelijk;
    public Label lblVlinderVriendelijk;
    public Label lblEetbaar;
    public Label lblGeurend;
    public Label lblVorstGevoelig;
    public Label lblBladGrootte;
    public Label lblBladVorm;
    public Label lblRatio;
    public Label lblSpruitFenologie;
    public Label lblLevensVorm;
    public Label lblBloeikleurJan;
    public Label lblBloeikleurFeb;
    public Label lblBloeikleurMaa;
    public Label lblBloeikleurApr;
    public Label lblBloeikleurMei;
    public Label lblBloeikleurJun;
    public Label lblBloeikleurJul;
    public Label lblBloeikleurAug;
    public Label lblBloeikleurSep;
    public Label lblBloeikleurOkt;
    public Label lblBloeikleurNov;
    public Label lblBloeikleurDec;
    public BorderPane pnlUitvoer;
    public Label lblMinBloeihoogteJan;
    public Label lblMinBloeihoogteFeb;
    public Label lblMinBloeihoogteMaa;
    public Label lblMinBloeihoogteApr;
    public Label lblMinBloeihoogteMei;
    public Label lblMinBloeihoogteJun;
    public Label lblMinBloeihoogteJul;
    public Label lblMinBloeihoogteAug;
    public Label lblMinBloeihoogteSep;
    public Label lblMinBloeihoogteOkt;
    public Label lblMinBloeihoogteNov;
    public Label lblMinBloeihoogteDec;
    public Label lblMaxBloeihoogteJan;
    public Label lblMaxBloeihoogteFeb;
    public Label lblMaxBloeihoogteMaa;
    public Label lblMaxBloeihoogteApr;
    public Label lblMaxBloeihoogteMei;
    public Label lblMaxBloeihoogteJun;
    public Label lblMaxBloeihoogteJul;
    public Label lblMaxBloeihoogteAug;
    public Label lblMaxBloeihoogteSep;
    public Label lblMaxBloeihoogteOkt;
    public Label lblMaxBloeihoogteNov;
    public Label lblMaxBloeihoogteDec;
    public Label lblMaxBladgrootteJan;
    public Label lblMaxBladgrootteFeb;
    public Label lblMaxBladgrootteMaa;
    public Label lblMaxBladgrootteApr;
    public Label lblMaxBladgrootteMei;
    public Label lblMaxBladgrootteJun;
    public Label lblMaxBladgrootteJul;
    public Label lblMaxBladgrootteAug;
    public Label lblMaxBladgrootteSep;
    public Label lblMaxBladgrootteOkt;
    public Label lblMaxBladgrootteNov;
    public Label lblMaxBladgrootteDec;
    public Label lblBladkleurJan;
    public Label lblBladkleurFeb;
    public Label lblBladkleurMaa;
    public Label lblBladkleurApr;
    public Label lblBladkleurMei;
    public Label lblBladkleurJun;
    public Label lblBladkleurJul;
    public Label lblBladkleurAug;
    public Label lblBladkleurSep;
    public Label lblBladkleurOkt;
    public Label lblBladkleurNov;
    public Label lblBladkleurDec;
    public Label lblHabitus;
    public Label lblBloeiwijze;
    public Label lblKruidgebruik;
    public ListView lsvLevensduur;
    public ListView lsvHabitat;
    public ImageView imgTufted;
    public ImageView imgUprightArching;
    public ImageView imgArching;
    public ImageView imgUprightDivergent;
    public ImageView imgUprightErect;
    public ImageView imgMounded;
    public ImageView ImgKruipend;
    public ImageView imgWaaiervorming;
    public ImageView imgKussenVormend;
    public ImageView imgZuilvormig;
    public ImageView imgUitbuigend;
    public ImageView imgHabitus2;
    public ImageView imgSucculenten;
    public ImageView imgPollenvormers;
    public ImageView imgParasolVormig;

    private InfoTables infoTables;
    private Connection dbConnection;
    GUIdata guiData;
    SearchHandler handler;
    ChangeListener<Plant> lsvChanged;
    ObservableList<Plant> plants;
    ArrayList<Plant> planten;

    public void initialize() throws SQLException {
        pnlAdvSearch.setExpanded(false);
        dbConnection = Database.getInstance().getConnection();
        /*infotabel object aanmaken*/
        InfoTablesDAO infotablesDAO = new InfoTablesDAO(dbConnection);
        infoTables = infotablesDAO.getInfoTables();
        handler = new SearchHandler(dbConnection);




        guiData = new GUIdata();
        guiData.textFieldDEM.get(ETextfield.SEARCH).Bind(txtSearch);
        guiData.comboBoxDEM.get(EComboBox.TYPE).Bind(chkType, cboType);
        guiData.comboBoxDEM.get(EComboBox.BEHANDELING).Bind(chkBehandeling, cboBehandeling);
        guiData.comboBoxDEM.get(EComboBox.BLADGROOTTE).Bind(chkBladgrootte, cboBladgrootte);
        guiData.comboBoxDEM.get(EComboBox.BLADKLEUR).Bind(chkBladKleur, cboBladkleur);
        guiData.comboBoxDEM.get(EComboBox.BLADVORM).Bind(chkBladvorm, cboBladvorm);
        guiData.comboBoxDEM.get(EComboBox.BLOEIKLEUR).Bind(chkBloeiKleur, cboBloeikleur);
        guiData.comboBoxDEM.get(EComboBox.BLADKLEUR).Bind(chkBladKleur, cboBladkleur);
        guiData.comboBoxDEM.get(EComboBox.FAMILIE).Bind(chkFamilie, cboFamilie);
        guiData.comboBoxDEM.get(EComboBox.HABITAT).Bind(chkHabitat, cboHabitat);
        guiData.comboBoxDEM.get(EComboBox.LEVENSDUUR).Bind(chkLevensduur_concurrentiekracht, cboLevensduur);
        guiData.comboBoxDEM.get(EComboBox.MAAND).Bind(chkMaand, cboMaand);
        guiData.comboBoxDEM.get(EComboBox.ONTWIKKELINGSSNELHEID).Bind(chkOntwikkelingssnelheid, cboOntwikkel);
        guiData.comboBoxDEM.get(EComboBox.RATIOBLOEIBLAD).Bind(chkRatio_bloei_blad, cboRatioBloeiBlad);
        guiData.comboBoxDEM.get(EComboBox.REACTIEANTAGONISTISCHEOMGEVING).Bind(chkReactieAntagonistischeOmg, cboReactieAnta);
        guiData.comboBoxDEM.get(EComboBox.SPRUITFENOLOGIE).Bind(chkSpruitfenologie, cboSpruitfenologie);

        guiData.spinnerDEM.get(ESpinner.PERXJAAR).Bind(chkPerXJaar, nudPerXJaar);

        guiData.sliderLabelDEM.get(ESliderLabel.BEZONNING).Bind(chkBezonning, sldBezonning, lblBezonning);
        guiData.sliderLabelDEM.get(ESliderLabel.VOEDINGSBEHOEFTE).Bind(chkVoedingsbehoefte, sldVoedingsbehoefte, lblVoedingsBehoefte);
        guiData.sliderLabelDEM.get(ESliderLabel.VOCHTBEHOEFTE).Bind(chkVochtBehoefte, sldVochtbehoefte, lblVocht);
        guiData.sliderLabelDEM.get(ESliderLabel.POLLENWAARDE).Bind(chkPollenwaarde, sldPollenwaarde, lblPollenwaarde);
        guiData.sliderLabelDEM.get(ESliderLabel.NECTARWAARDE).Bind(chkNectarwaarde, sldNectarwaarde, lblNectarwaarde);

        guiData.combinedCheckboxDEM.get(EComCheckbox.GRONDSOORT).Bind(chkGrondsoort, new CheckBox[]{chkGrondsoort_Z, chkGrondsoort_L, chkGrondsoort_K});

        guiData.multiCheckboxDEM.get(EMultiCheckbox.SOCIABILITEIT).Bind(chkSociabiliteit, new CheckBox[]{chkSociabiliteit_1, chkSociabiliteit_2, chkSociabiliteit_3, chkSociabiliteit_4, chkSociabiliteit_5});

        guiData.multiComboBoxDEM.get(EMultiComboBox.BLADKLEURPERMAAND).Bind(chkBladkleurPerMaand, new ComboBox[]{cboBladkleurJan, cboBladkleurFeb, cboBladkleurMaa, cboBladkleurApr, cboBladkleurMei, cboBladkleurJun, cboBladkleurJul, cboBladkleurAug, cboBladkleurSep, cboBladkleurOkt, cboBladkleurNov, cboBladkleurDec});
        guiData.multiComboBoxDEM.get(EMultiComboBox.BLOEIKLEURPERMAAND).Bind(chkBloeikleurPerMaand, new ComboBox[]{cboBloeikleurJan, cboBloeikleurFeb, cboBloeikleurMaa, cboBloeikleurApr, cboBloeikleurMei, cboBloeikleurJun, cboBloeikleurJul, cboBloeikleurAug, cboBloeikleurSep, cboBloeikleurOkt, cboBloeikleurNov, cboBloeikleurDec});

        guiData.multiSpinnerDEM.get(EMultiSpinner.BLOEIHOOGTE).Bind(chkBloeiHoogte, new Spinner[]{nudMinBloeihoogte, nudMaxBloeihoogte});
        guiData.multiSpinnerDEM.get(EMultiSpinner.BLADHOOGTE).Bind(chkBladHoogte, new Spinner[]{nudMinBladhoogte, nudMaxBladhoogte});
        guiData.multiSpinnerDEM.get(EMultiSpinner.MINBLOEIHOOGTEPERMAAND).Bind(chkMinBloeihoogtePerMaand, new Spinner[]{nudMinBloeihoogte_Jan, nudMinBloeihoogte_Feb, nudMinBloeihoogte_Maa, nudMinBloeihoogte_Apr, nudMinBloeihoogte_Mei, nudMinBloeihoogte_Jun, nudMinBloeihoogte_Jul, nudMinBloeihoogte_Aug, nudMinBloeihoogte_Sept, nudMinBloeihoogte_Okt, nudMinBloeihoogte_Nov, nudMinBloeihoogte_Dec});
        guiData.multiSpinnerDEM.get(EMultiSpinner.MAXBLOEIHOOGTEPERMAAND).Bind(chkMaxBloeihoogtePerMaand, new Spinner[]{nudMaxBloeihoogte_Jan, nudMaxBloeihoogte_Feb, nudMaxBloeihoogte_Maa, nudMaxBloeihoogte_Apr, nudMaxBloeihoogte_Mei, nudMaxBloeihoogte_Jun, nudMaxBloeihoogte_Jul, nudMaxBloeihoogte_Aug, nudMaxBloeihoogte_Sept, nudMaxBloeihoogte_Okt, nudMaxBloeihoogte_Nov, nudMaxBloeihoogte_Dec});
        guiData.multiSpinnerDEM.get(EMultiSpinner.MINBLADHOOGTEPERMAAND).Bind(chkMinBladhoogtePerMaand, new Spinner[]{nudMinBladhoogte_Jan, nudMinBladhoogte_Feb, nudMinBladhoogte_Maa, nudMinBladhoogte_Apr, nudMinBladhoogte_Mei, nudMinBladhoogte_Jun, nudMinBladhoogte_Jul, nudMinBladhoogte_Aug, nudMinBladhoogte_Sept, nudMinBladhoogte_Okt, nudMinBladhoogte_Nov, nudMinBladhoogte_Dec});
        guiData.multiSpinnerDEM.get(EMultiSpinner.MAXBLADHOOGTEPERMAAND).Bind(chkMaxBladhoogtePerMaand, new Spinner[]{nudMaxBladhoogte_Jan, nudMaxBladhoogte_Feb, nudMaxBladhoogte_Maa, nudMaxBladhoogte_Apr, nudMaxBladhoogte_Mei, nudMaxBladhoogte_Jun, nudMaxBladhoogte_Jul, nudMaxBladhoogte_Aug, nudMaxBladhoogte_Sept, nudMaxBladhoogte_Okt, nudMaxBladhoogte_Nov, nudMaxBladhoogte_Dec});

        guiData.radiogroupDEM.get(ERadiogroup.BIJVRIENDELIJK).Bind(chkBijvriendelijk, new RadioButton[]{rdbBijvriendelijk_Ja, rdbBijvriendelijk_Nee, rdbBijvriendelijk_Onbekend});
        guiData.radiogroupDEM.get(ERadiogroup.VLINDERVRIENDELIJK).Bind(chkVlindervriendelijk, new RadioButton[]{rdbVlindervriendelijk_Ja, rdbVlindervriendelijk_Nee, rdbVlindervriendelijk_Onbekend});
        guiData.radiogroupDEM.get(ERadiogroup.VORSTGEVOELIG).Bind(chkVorstgevoelig, new RadioButton[]{rdbVorstgevoelig_Ja, rdbVorstgevoelig_Nee, rdbVorstgevoelig_Onbekend});
        guiData.radiogroupDEM.get(ERadiogroup.BLOEIWIJZE).Bind(chkBloeiwijze, new RadioButton[]{rdbBloeiwijze_Aar, rdbBloeiwijze_BredePluim, rdbBloeiwijze_Etage, rdbBloeiwijze_BolofKnop, rdbBloeiwijze_Margrietachtig, rdbBloeiwijze_Schotel, rdbBloeiwijze_Scherm, rdbBloeiwijze_SmallePluim});
        guiData.radiogroupDEM.get(ERadiogroup.EETBAAR).Bind(chkEetbaar, new RadioButton[]{rdbEetbaar_Ja, rdbEetbaar_Nee, rdbEetbaar_Onbekend});
        guiData.radiogroupDEM.get(ERadiogroup.HABITUS).Bind(chkHabitus, new RadioButton[]{rdbHabitus_1, rdbHabitus_2, rdbHabitus_3, rdbHabitus_4, rdbHabitus_5, rdbHabitus_6, rdbHabitus_7, rdbHabitus_8, rdbHabitus_9, rdbHabitus_10, rdbHabitus_11, rdbHabitus_12, rdbHabitus_13, rdbHabitus_14, rdbHabitus_15});
        guiData.radiogroupDEM.get(ERadiogroup.GEUREND).Bind(chkGeurend, new RadioButton[]{rdbGeurend_Ja, rdbGeurend_Nee, rdbGeurend_Onbekend});
        guiData.radiogroupDEM.get(ERadiogroup.KRUIDGEBRUIK).Bind(chkKruidgebruik, new RadioButton[]{rdbKruidgebruik_Ja, rdbKruidgebruik_Nee, rdbKruidgebruik_Onbekend});
        guiData.radiogroupDEM.get(ERadiogroup.STRATEGIE).Bind(chkStrategie, new RadioButton[]{rdbStrategie_C, rdbStrategie_S, rdbStrategie_R, rdbStrategie_CS, rdbStrategie_CR, rdbStrategie_RS, rdbStrategie_CRS});
        guiData.radiogroupDEM.get(ERadiogroup.LEVENSVORM).Bind(chkLevensvormVolgensRaunkhiaer, new RadioButton[]{rdbLevensvorm_1, rdbLevensvorm_2, rdbLevensvorm_3, rdbLevensvorm_4, rdbLevensvorm_5, rdbLevensvorm_6, rdbLevensvorm_7, rdbLevensvorm_8, rdbLevensvorm_9});



        InitListView();
        FillComboboxes(infoTables);

        ArrayList<ImageView> habitus = new ArrayList<>();
        habitus.add(imgTufted);
        habitus.add(imgUprightArching);
        habitus.add(imgArching);
        habitus.add(imgUprightDivergent);
        habitus.add(imgUprightErect);
        habitus.add(imgMounded);
        habitus.add(ImgKruipend);
        habitus.add(imgWaaiervorming);
        habitus.add(imgKussenVormend);
        habitus.add(imgZuilvormig);
        habitus.add(imgUitbuigend);
        habitus.add(imgHabitus2); //TODO naam aanpassen
        habitus.add(imgSucculenten);
        habitus.add(imgPollenvormers);
        habitus.add(imgParasolVormig);

        ArrayList<RadioButton> habitusrdb = new ArrayList<>();
        habitusrdb.add(rdbHabitus_1);
        habitusrdb.add(rdbHabitus_2);
        habitusrdb.add(rdbHabitus_3);
        habitusrdb.add(rdbHabitus_4);
        habitusrdb.add(rdbHabitus_5);
        habitusrdb.add(rdbHabitus_6);
        habitusrdb.add(rdbHabitus_7);
        habitusrdb.add(rdbHabitus_8);
        habitusrdb.add(rdbHabitus_9);
        habitusrdb.add(rdbHabitus_10);
        habitusrdb.add(rdbHabitus_11);
        habitusrdb.add(rdbHabitus_12);
        habitusrdb.add(rdbHabitus_13);
        habitusrdb.add(rdbHabitus_14);
        habitusrdb.add(rdbHabitus_15);

        for(int i = 0; i<infoTables.getHabitusFotos().size(); i++){

            habitusrdb.get(i).textProperty().setValue(infoTables.getHabitusFotos().get(i).getWaarde());

            habitus.get(i).setImage(infoTables.getHabitusFotos().get(i).getFoto());
        }

        //TODO familie link met type
        /*
        cboType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                try {
                    InfoTablesDAO dao = new InfoTablesDAO(dbConnection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                cboFamilie.getItems().addAll(infotablesDAO.selectFamilyByType(bindingData.searchRequestData.get(ERequestData.TYPE).Value().))
            }
        });
        */
    }
    /**
     * @author bradley
     */
    public void InitListView() {
       lsvOverzicht.setCellFactory(param -> new ListCell<Plant>() {
            @Override
            protected void updateItem(Plant item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getType() + " " + item.getFamilie() + " " + item.getGeslacht() + " " + item.getSoort() + " " + item.getVariatie());
                }
            }
        });

        lsvChanged = new ChangeListener<Plant>() {
            @Override
            public void changed(ObservableValue<? extends Plant> observable, Plant oldValue, Plant newValue) throws NullPointerException {
                // Your action here

                try {
                    newValue = handler.SelectFullPlant(newValue);
                } catch (Exception e) {
                    e.printStackTrace();

                }

                try {
                    //standaard
                    lblType.setText(newValue.getType());
                    lblFamilie.setText(newValue.getFamilie());
                    lblGeslacht.setText(newValue.getGeslacht());
                    lblSoort.setText(newValue.getSoort());
                    lblVariant.setText(newValue.getVariatie());
                    lblDichtheidX.setText(String.valueOf(newValue.getMinPlantdichtheid()));
                    lblDichtheidY.setText(String.valueOf(newValue.getMaxPlantdichtheid()));

                    //commensalisme
                    lblOntwikkelingsSnelheid.setText(newValue.getCommensalisme().getOntwikkelingssnelheid());
                   /*if (newValue.getCommensalisme().getSociabiliteit().get(0) == 1){chkSocPlantI.setSelected(true);}
                    if (newValue.getCommensalisme().getSociabiliteit().get(0) == 2){chkSocPlantII.setSelected(true);}
                    if (newValue.getCommensalisme().getSociabiliteit().get(0) == 3){chkSocPlantIII.setSelected(true);}
                    if (newValue.getCommensalisme().getSociabiliteit().get(0) == 4){chkSocPlantIV.setSelected(true);}
                    if (newValue.getCommensalisme().getSociabiliteit().get(0) == 5){chkSocPlantV.setSelected(true);}*/
                    lblStrategie.setText(newValue.getCommensalisme().getStrategie());
                    //TODO lsvLevensduur.setItems();

                    //abiotische factoren
                    lblBezonning2.setText(newValue.getAbiotischeFactoren().getBezonning());
                    lblVochtBehoefte.setText(newValue.getAbiotischeFactoren().getVochtbehoefte());
                    lblVoedingsBehoefte2.setText(newValue.getAbiotischeFactoren().getVoedingsbehoefte());
                    lblReactie.setText(newValue.getAbiotischeFactoren().getReactieAntagonistischeOmgeving());
                    lblGrondSoort.setText(newValue.getAbiotischeFactoren().getGrondsoort());
                    //TODO lsvHabitat.setItems();

                    //extra
                    lblNectarwaarde2.setText(String.valueOf(newValue.getExtra().getNectarwaarde()));
                    lblPollenwaarde2.setText(String.valueOf(newValue.getExtra().getPollenwaarde()));
                    lblBijvriendelijk.setText(newValue.getExtra().getBijvriendelijk());
                    lblVlinderVriendelijk.setText(newValue.getExtra().getVlindervriendelijk());
                    lblEetbaar.setText(newValue.getExtra().getEetbaar());
                    lblKruidgebruik.setText(newValue.getExtra().getKruidgebruik());
                    lblGeurend.setText((newValue.getExtra().getGeurend()));
                    lblVorstGevoelig.setText(newValue.getExtra().getVorstgevoelig());

                    //fenotype
                    lblBladGrootte.setText(String.valueOf(newValue.getFenotype().getBladgrootte()));
                    lblBladVorm.setText(newValue.getFenotype().getBladvorm());
                    lblRatio.setText(newValue.getFenotype().getRatio_bloei_blad());
                    lblSpruitFenologie.setText(newValue.getFenotype().getSpruitfenologie());
                    lblLevensVorm.setText(newValue.getFenotype().getLevensvorm());
                    lblHabitus.setText(newValue.getFenotype().getHabitus());
                    lblBloeiwijze.setText(newValue.getFenotype().getBloeiwijze());

                    //maandelijkse gegevens
                    lblMaxBladgrootteJan.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getJan()));
                    lblMaxBladgrootteFeb.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getFeb()));
                    lblMaxBladgrootteMaa.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getMaa()));
                    lblMaxBladgrootteApr.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getApr()));
                    lblMaxBladgrootteMei.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getMei()));
                    lblMaxBladgrootteJun.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getJun()));
                    lblMaxBladgrootteJul.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getJul()));
                    lblMaxBladgrootteAug.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getAug()));
                    lblMaxBladgrootteSep.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getSep()));
                    lblMaxBladgrootteOkt.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getOkt()));
                    lblMaxBladgrootteNov.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getNov()));
                    lblMaxBladgrootteDec.setText(String.valueOf(newValue.getFenotype().getBladhoogte().getDec()));

                    lblBladkleurJan.setText(newValue.getFenotype().getBladkleur().getJan());
                    lblBladkleurFeb.setText(newValue.getFenotype().getBladkleur().getFeb());
                    lblBladkleurMaa.setText(newValue.getFenotype().getBladkleur().getMaa());
                    lblBladkleurApr.setText(newValue.getFenotype().getBladkleur().getApr());
                    lblBladkleurMei.setText(newValue.getFenotype().getBladkleur().getMei());
                    lblBladkleurJun.setText(newValue.getFenotype().getBladkleur().getJun());
                    lblBladkleurJul.setText(newValue.getFenotype().getBladkleur().getJul());
                    lblBladkleurAug.setText(newValue.getFenotype().getBladkleur().getAug());
                    lblBladkleurSep.setText(newValue.getFenotype().getBladkleur().getSep());
                    lblBladkleurOkt.setText(newValue.getFenotype().getBladkleur().getOkt());
                    lblBladkleurNov.setText(newValue.getFenotype().getBladkleur().getNov());
                    lblBladkleurDec.setText(newValue.getFenotype().getBladkleur().getDec());

                    lblMinBloeihoogteJan.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getJan()));
                    lblMinBloeihoogteFeb.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getFeb()));
                    lblMinBloeihoogteMaa.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getMaa()));
                    lblMinBloeihoogteApr.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getApr()));
                    lblMinBloeihoogteMei.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getMei()));
                    lblMinBloeihoogteJun.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getJun()));
                    lblMinBloeihoogteJul.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getJul()));
                    lblMinBloeihoogteAug.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getAug()));
                    lblMinBloeihoogteSep.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getSep()));
                    lblMinBloeihoogteOkt.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getOkt()));
                    lblMinBloeihoogteNov.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getNov()));
                    lblMinBloeihoogteDec.setText(String.valueOf(newValue.getFenotype().getMinBloeihoogte().getDec()));

                    lblMaxBloeihoogteJan.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getJan()));
                    lblMaxBloeihoogteFeb.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getFeb()));
                    lblMaxBloeihoogteMaa.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getMaa()));
                    lblMaxBloeihoogteApr.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getApr()));
                    lblMaxBloeihoogteMei.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getMei()));
                    lblMaxBloeihoogteJun.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getJun()));
                    lblMaxBloeihoogteJul.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getJul()));
                    lblMaxBloeihoogteAug.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getAug()));
                    lblMaxBloeihoogteSep.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getSep()));
                    lblMaxBloeihoogteOkt.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getOkt()));
                    lblMaxBloeihoogteNov.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getNov()));
                    lblMaxBloeihoogteDec.setText(String.valueOf(newValue.getFenotype().getMaxBloeihoogte().getDec()));

                    lblBloeikleurJan.setText(newValue.getFenotype().getBloeikleur().getJan());
                    lblBloeikleurFeb.setText(newValue.getFenotype().getBloeikleur().getFeb());
                    lblBloeikleurMaa.setText(newValue.getFenotype().getBloeikleur().getMaa());
                    lblBloeikleurApr.setText(newValue.getFenotype().getBloeikleur().getApr());
                    lblBloeikleurMei.setText(newValue.getFenotype().getBloeikleur().getMei());
                    lblBloeikleurJun.setText(newValue.getFenotype().getBloeikleur().getJun());
                    lblBloeikleurJul.setText(newValue.getFenotype().getBloeikleur().getJul());
                    lblBloeikleurAug.setText(newValue.getFenotype().getBloeikleur().getAug());
                    lblBloeikleurSep.setText(newValue.getFenotype().getBloeikleur().getSep());
                    lblBloeikleurOkt.setText(newValue.getFenotype().getBloeikleur().getOkt());
                    lblBloeikleurNov.setText(newValue.getFenotype().getBloeikleur().getNov());
                    lblBloeikleurDec.setText(newValue.getFenotype().getBloeikleur().getDec());





                } catch (NullPointerException ex){
                    ex.printStackTrace();
                }
            }
        };
        lsvOverzicht.getSelectionModel().selectedItemProperty().addListener(lsvChanged);
    }

    /**
     * @param infotables -> lijst van alle lijsten van gegevens uit de naakte tabellen
     * @author bradley, angelo
     * Functie om comboboxes te vullen met alle gegevens uit de database
     */
    public void FillComboboxes(InfoTables infotables) {

        //type

        cboType.getItems().addAll(infotables.getTypes());

        cboType.getSelectionModel().selectFirst();
        //familie
        cboFamilie.getItems().addAll(infotables.getFamilies());
        cboFamilie.getSelectionModel().selectFirst();
        //bladkleur

        cboBladkleur.getItems().addAll(infotables.getKleuren());
        cboBladkleur.getSelectionModel().select("nvt");
        //bloeikleur

        cboBloeikleur.getItems().addAll(infotables.getKleuren());
        cboBloeikleur.getSelectionModel().select("nvt");
        //bladgrootte
        cboBladgrootte.getItems().addAll(infotables.getBladgroottes());
        cboBladgrootte.getSelectionModel().selectFirst();
        //habitat
        cboHabitat.getItems().addAll(infotables.getHabitats());
        cboHabitat.getSelectionModel().selectFirst();
        //bladkleuren (per maand)

        cboBladkleurJan.getItems().addAll(infotables.getKleuren());
        cboBladkleurJan.getSelectionModel().select("nvt");

        cboBladkleurFeb.getItems().addAll(infotables.getKleuren());
        cboBladkleurFeb.getSelectionModel().select("nvt");

        cboBladkleurMaa.getItems().addAll(infotables.getKleuren());
        cboBladkleurMaa.getSelectionModel().select("nvt");

        cboBladkleurApr.getItems().addAll(infotables.getKleuren());
        cboBladkleurApr.getSelectionModel().select("nvt");

        cboBladkleurMei.getItems().addAll(infotables.getKleuren());
        cboBladkleurMei.getSelectionModel().select("nvt");

        cboBladkleurJun.getItems().addAll(infotables.getKleuren());
        cboBladkleurJun.getSelectionModel().select("nvt");

        cboBladkleurJul.getItems().addAll(infotables.getKleuren());
        cboBladkleurJul.getSelectionModel().select("nvt");

        cboBladkleurAug.getItems().addAll(infotables.getKleuren());
        cboBladkleurAug.getSelectionModel().select("nvt");

        cboBladkleurSep.getItems().addAll(infotables.getKleuren());
        cboBladkleurSep.getSelectionModel().select("nvt");

        cboBladkleurOkt.getItems().addAll(infotables.getKleuren());
        cboBladkleurOkt.getSelectionModel().select("nvt");

        cboBladkleurNov.getItems().addAll(infotables.getKleuren());
        cboBladkleurNov.getSelectionModel().select("nvt");

        cboBladkleurDec.getItems().addAll(infotables.getKleuren());
        cboBladkleurDec.getSelectionModel().select("nvt");
        //bloeikleuren (per maand)

        cboBloeikleurJan.getItems().addAll(infotables.getKleuren());
        cboBloeikleurJan.getSelectionModel().select("nvt");

        cboBloeikleurFeb.getItems().addAll(infotables.getKleuren());
        cboBloeikleurFeb.getSelectionModel().select("nvt");

        cboBloeikleurMaa.getItems().addAll(infotables.getKleuren());
        cboBloeikleurMaa.getSelectionModel().select("nvt");

        cboBloeikleurApr.getItems().addAll(infotables.getKleuren());
        cboBloeikleurApr.getSelectionModel().select("nvt");

        cboBloeikleurMei.getItems().addAll(infotables.getKleuren());
        cboBloeikleurMei.getSelectionModel().select("nvt");

        cboBloeikleurJun.getItems().addAll(infotables.getKleuren());
        cboBloeikleurJun.getSelectionModel().select("nvt");

        cboBloeikleurJul.getItems().addAll(infotables.getKleuren());
        cboBloeikleurJul.getSelectionModel().select("nvt");

        cboBloeikleurAug.getItems().addAll(infotables.getKleuren());
        cboBloeikleurAug.getSelectionModel().select("nvt");

        cboBloeikleurSep.getItems().addAll(infotables.getKleuren());
        cboBloeikleurSep.getSelectionModel().select("nvt");

        cboBloeikleurOkt.getItems().addAll(infotables.getKleuren());
        cboBloeikleurOkt.getSelectionModel().select("nvt");

        cboBloeikleurNov.getItems().addAll(infotables.getKleuren());
        cboBloeikleurNov.getSelectionModel().select("nvt");

        cboBloeikleurDec.getItems().addAll(infotables.getKleuren());
        cboBloeikleurDec.getSelectionModel().select("nvt");
        //reactie antagonistische omgeving
        cboReactieAnta.getItems().addAll(infotables.getAntagonistischeOmgevingsReacties());
        cboReactieAnta.getSelectionModel().selectFirst();
        //ontwikkelingssnelheid
        cboOntwikkel.getItems().addAll(infotables.getOnstwikkelingssnelheden());
        cboOntwikkel.getSelectionModel().selectFirst();
        //levensduur/concurrentiekracht
        cboLevensduur.getItems().addAll(infotables.getConcurentiekrachten());
        cboLevensduur.getSelectionModel().selectFirst();
        //bladvorm
        cboBladvorm.getItems().addAll(infotables.getBladvormen());
        cboBladvorm.getSelectionModel().selectFirst();
        cboMaand.getItems().addAll("januari", "februari", "maart", "april", "mei", "juni", "juli", "augustus", "september", "oktober", "november", "december");
        cboMaand.getSelectionModel().selectFirst();
        cboBehandeling.getItems().addAll("test", "test2", "test3");
        cboBehandeling.getSelectionModel().selectFirst();

        cboRatioBloeiBlad.getItems().addAll(infotables.getBloeiBladRatios());
        cboRatioBloeiBlad.getSelectionModel().selectFirst();
        cboSpruitfenologie.getItems().addAll(infotables.getSpruitfenologieen());
        cboSpruitfenologie.getSelectionModel().selectFirst();
    }
    public void Click_Search(MouseEvent mouseEvent) throws SQLException {

        lsvOverzicht.getItems().clear();

        if (!pnlUitvoer.isVisible()) {
            pnlUitvoer.setVisible(true);
        }

        if (pnlAdvSearch.isExpanded()) {
            pnlAdvSearch.setExpanded(false);
        }

        planten = new ArrayList<Plant>();
        planten = handler.Search(guiData, dbConnection);

       lsvOverzicht.getItems().addAll(planten);

        lsvOverzicht.getSelectionModel().selectFirst();
    }

}



