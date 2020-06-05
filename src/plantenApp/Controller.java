package plantenApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import plantenApp.java.dao.Database;
import plantenApp.java.dao.InfoTablesDAO;
import plantenApp.java.model.BindingData;
import plantenApp.java.model.InfoTables;
import plantenApp.java.model.SearchHandler;
import plantenApp.java.utils.ERequestArrayData;
import plantenApp.java.utils.ERequestData;

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


    private InfoTables infoTables;
    private Connection dbConnection;
    BindingData bindingData;

    public void initialize() throws SQLException {
        pnlAdvSearch.setExpanded(false);

        dbConnection = Database.getInstance().getConnection();
        /*infotabel object aanmaken*/
        InfoTablesDAO infotablesDAO = new InfoTablesDAO(dbConnection);
        infoTables = infotablesDAO.getInfoTables();

        FillComboboxes(infoTables);


        InitSpinners();

        InitBindings();

        sldNectarwaarde.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                lblNectarwaarde.setText(String.valueOf((int)sldNectarwaarde.getValue()));
            }
        });
        sldPollenwaarde.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                lblPollenwaarde.setText(String.valueOf((int)sldPollenwaarde.getValue()));
            }
        });
        sldVoedingsbehoefte.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                switch ((int) sldVoedingsbehoefte.getValue()) {
                    case 1:
                        lblVoedingsBehoefte.setText("arm");
                        break;
                    case 2:
                        lblVoedingsBehoefte.setText("indifferent");
                        break;
                    case 3:
                        lblVoedingsBehoefte.setText("matig");
                        break;
                    case 4:
                        lblVoedingsBehoefte.setText("rijk");
                        break;
                }
            }
        });
        sldBezonning.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                switch ((int) sldBezonning.getValue()) {
                    case 1:
                        lblBezonning.setText("S");
                        break;
                    case 2:
                        lblBezonning.setText("HS-S");
                        break;
                    case 3:
                        lblBezonning.setText("HS");
                        break;
                    case 4:
                        lblBezonning.setText("Z-HS");
                        break;
                    case 5:
                        lblBezonning.setText("Z");
                        break;
                }
            }
        });
        sldVochtbehoefte.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                switch ((int)sldVochtbehoefte.getValue()){
                    case 1:
                        lblVocht.setText("droog");
                        break;
                    case 2:
                        lblVocht.setText("droog/fris");
                        break;

                    case 3:
                        lblVocht.setText("fris");
                        break;
                    case 4:
                        lblVocht.setText("fris/vochtig");
                        break;
                    case 5:
                        lblVocht.setText("vochtig");
                        break;
                    case 6:
                        lblVocht.setText("vochtig/nat");
                        break;
                    case 7:
                        lblVocht.setText("nat");
                        break;
                }
            }
        });

        InitSliders();

    }


    public void InitSliders() {
        SetSlider(sldNectarwaarde, 0, 5, false);
        SetSlider(sldPollenwaarde, 0, 5, false);

        //schaduw, halfschaduw-shacuw, half schaduw, halfschaduw-zon, zon
        SetSlider(sldBezonning, 1, 5, false);

        //droog, droog of fris, fris, fris of vochtig, vochtig, vochtig-nat, nat
        SetSlider(sldVochtbehoefte, 1, 7, false);


        //arm, indifferent, matig, rijk
        SetSlider(sldVoedingsbehoefte, 1, 4, false);

    }

    /**
     * @param slider     in te stellen slider
     * @param min        minimumwaarde van slider
     * @param max        maximumwaarde van slider
     * @param ticklabels moeten labels getoont worden met geselecteerde waarde
     * @author bradley
     * spinner instellen enkel major ticks in integers
     */
    private void SetSlider(Slider slider, int min, int max, boolean ticklabels) {
        slider.setMin(min);
        slider.setMax(max);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(ticklabels);
        slider.setSnapToTicks(true);
        slider.setValue(1);
    }

    /**
     * @author bradley
     * Aanmaken van connectie bindings tussen verschillende controls
     */
    private void InitBindings() {
        bindingData = new BindingData();

        bindingData.searchRequestData.get(ERequestData.SEARCH).Value().valueProperty().bind(txtSearch.textProperty());

        Bind(ERequestData.BLADVORM, chkBladvorm, cboBladvorm);
        Bind(ERequestData.SPRUITFENOLOGIE, chkSpruitfenologie, cboSpruitfenologie);
        Bind(ERequestData.VOEDINGSBEHOEFTE, chkVoedingsbehoefte, sldVoedingsbehoefte,lblVoedingsBehoefte);
        Bind(ERequestData.BLADGROOTTE, chkBladgrootte, cboBladgrootte);
        Bind(ERequestData.BLADHOOGTE, chkBladHoogte, nudMaxBladhoogte);
        Bind(ERequestData.FAMILIE, chkFamilie, cboFamilie);
        Bind(ERequestData.TYPE, chkType, cboType);
        Bind(ERequestData.HABITAT, chkHabitat, cboHabitat);
        Bind(ERequestData.BLADKLEUR, chkBladKleur, cboBladkleur);
        Bind(ERequestData.BLOEIKLEUR, chkBloeiKleur, cboBloeikleur);
        Bind(ERequestData.REACTIEANTAGONISTISCHEOMGEVING, chkReactieAntagonistischeOmg, cboReactieAnta);
        Bind(ERequestData.ONTWIKKELINGSSNELHEID, chkOntwikkelingssnelheid, cboOntwikkel);
        Bind(ERequestData.LEVENSDUUR, chkLevensduur_concurrentiekracht, cboLevensduur);
        Bind(ERequestData.NECTARWAARDE, chkNectarwaarde, sldNectarwaarde,lblNectarwaarde);
        Bind(ERequestData.POLLENWAARDE, chkPollenwaarde, sldPollenwaarde,lblPollenwaarde);
        Bind(ERequestData.RATIOBLOEIBLAD, chkRatio_bloei_blad, cboRatioBloeiBlad);
        Bind(ERequestData.BEHANDELING, chkBehandeling, cboBehandeling);
        Bind(ERequestData.MAAND, chkMaand, cboMaand);
        Bind(ERequestData.PERXJAAR, chkPerXJaar, nudPerXJaar);
        Bind(ERequestData.BEZONNING, chkBezonning, sldBezonning,lblBezonning);
        Bind(ERequestData.VOCHTBEHOEFTE, chkVochtBehoefte, sldVochtbehoefte,lblVocht);


        ArrayList<RadioButton> rdbLevensvormen = new ArrayList<RadioButton>();
        rdbLevensvormen.add(rdbLevensvorm_1);
        rdbLevensvormen.add(rdbLevensvorm_2);
        rdbLevensvormen.add(rdbLevensvorm_3);
        rdbLevensvormen.add(rdbLevensvorm_4);
        rdbLevensvormen.add(rdbLevensvorm_5);
        rdbLevensvormen.add(rdbLevensvorm_6);
        rdbLevensvormen.add(rdbLevensvorm_7);
        rdbLevensvormen.add(rdbLevensvorm_8);
        rdbLevensvormen.add(rdbLevensvorm_9);

        BindRadiobutton(ERequestArrayData.LEVENSVORM, chkLevensvormVolgensRaunkhiaer, rdbLevensvormen);

        ArrayList<RadioButton> rdbStrategieën = new ArrayList<RadioButton>();
        rdbStrategieën.add(rdbStrategie_C);
        rdbStrategieën.add(rdbStrategie_R);
        rdbStrategieën.add(rdbStrategie_S);
        rdbStrategieën.add(rdbStrategie_CR);
        rdbStrategieën.add(rdbStrategie_CS);
        rdbStrategieën.add(rdbStrategie_RS);
        rdbStrategieën.add(rdbStrategie_CRS);
        rdbStrategieën.add(rdbStrategie_Onbekend);
        BindRadiobutton(ERequestArrayData.STRATEGIE, chkStrategie, rdbStrategieën);

        ArrayList<RadioButton> rdbBijvriendelijken = new ArrayList<RadioButton>();
        rdbBijvriendelijken.add(rdbBijvriendelijk_Ja);
        rdbBijvriendelijken.add(rdbBijvriendelijk_Nee);
        rdbBijvriendelijken.add(rdbBijvriendelijk_Onbekend);
        BindRadiobutton(ERequestArrayData.BIJVRIENDELIJK, chkBijvriendelijk, rdbBijvriendelijken);

        ArrayList<RadioButton> rdbVlindervriendelijken = new ArrayList<RadioButton>();
        rdbVlindervriendelijken.add(rdbVlindervriendelijk_Ja);
        rdbVlindervriendelijken.add(rdbVlindervriendelijk_Nee);
        rdbVlindervriendelijken.add(rdbVlindervriendelijk_Onbekend);
        BindRadiobutton(ERequestArrayData.VLINDERVRIENDELIJK, chkVlindervriendelijk, rdbVlindervriendelijken);

        ArrayList<RadioButton> rdbGeurenden = new ArrayList<RadioButton>();
        rdbGeurenden.add(rdbGeurend_Ja);
        rdbGeurenden.add(rdbGeurend_Nee);
        rdbGeurenden.add(rdbGeurend_Onbekend);
        BindRadiobutton(ERequestArrayData.GEUREND, chkGeurend, rdbGeurenden);

        ArrayList<RadioButton> rdbEetbaren = new ArrayList<RadioButton>();
        rdbEetbaren.add(rdbEetbaar_Ja);
        rdbEetbaren.add(rdbEetbaar_Nee);
        rdbEetbaren.add(rdbEetbaar_Onbekend);
        BindRadiobutton(ERequestArrayData.EETBAAR, chkEetbaar, rdbEetbaren);

        ArrayList<RadioButton> rdkKruidgebruiken = new ArrayList<RadioButton>();
        rdkKruidgebruiken.add(rdbKruidgebruik_Ja);
        rdkKruidgebruiken.add(rdbKruidgebruik_Nee);
        rdkKruidgebruiken.add(rdbKruidgebruik_Onbekend);
        BindRadiobutton(ERequestArrayData.KRUIDGEBRUIK, chkKruidgebruik, rdkKruidgebruiken);

        ArrayList<RadioButton> rdbVorstgevoeligen = new ArrayList<RadioButton>();
        rdbVorstgevoeligen.add(rdbVorstgevoelig_Ja);
        rdbVorstgevoeligen.add(rdbVorstgevoelig_Nee);
        rdbVorstgevoeligen.add(rdbVorstgevoelig_Onbekend);
        BindRadiobutton(ERequestArrayData.VORSTGEVOELIG, chkVorstgevoelig, rdbVorstgevoeligen);

        ArrayList<RadioButton> rdbHabitusen = new ArrayList<RadioButton>();
        rdbHabitusen.add(rdbHabitus_1);
        rdbHabitusen.add(rdbHabitus_2);
        rdbHabitusen.add(rdbHabitus_3);
        rdbHabitusen.add(rdbHabitus_4);
        rdbHabitusen.add(rdbHabitus_5);
        rdbHabitusen.add(rdbHabitus_6);
        rdbHabitusen.add(rdbHabitus_7);
        rdbHabitusen.add(rdbHabitus_8);
        rdbHabitusen.add(rdbHabitus_9);
        rdbHabitusen.add(rdbHabitus_10);
        rdbHabitusen.add(rdbHabitus_11);
        rdbHabitusen.add(rdbHabitus_12);
        rdbHabitusen.add(rdbHabitus_13);
        rdbHabitusen.add(rdbHabitus_14);
        rdbHabitusen.add(rdbHabitus_15);
        BindRadiobutton(ERequestArrayData.HABITUS, chkHabitus, rdbHabitusen);

        ArrayList<RadioButton> rdbBloeiwijzes = new ArrayList<RadioButton>();
        rdbBloeiwijzes.add(rdbBloeiwijze_Etage);
        rdbBloeiwijzes.add(rdbBloeiwijze_Aar);
        rdbBloeiwijzes.add(rdbBloeiwijze_BolofKnop);
        rdbBloeiwijzes.add(rdbBloeiwijze_BredePluim);
        rdbBloeiwijzes.add(rdbBloeiwijze_Margrietachtig);
        rdbBloeiwijzes.add(rdbBloeiwijze_Scherm);
        rdbBloeiwijzes.add(rdbBloeiwijze_Schotel);
        rdbBloeiwijzes.add(rdbBloeiwijze_SmallePluim);
        BindRadiobutton(ERequestArrayData.BLOEIWIJZE, chkBloeiwijze, rdbBloeiwijzes);

        ArrayList<CheckBox> chkSociabiliteiten = new ArrayList<CheckBox>();
        chkSociabiliteiten.add(chkSociabiliteit_1);
        chkSociabiliteiten.add(chkSociabiliteit_2);
        chkSociabiliteiten.add(chkSociabiliteit_3);
        chkSociabiliteiten.add(chkSociabiliteit_4);
        chkSociabiliteiten.add(chkSociabiliteit_5);
        BindCheckbox(ERequestArrayData.SOCIABILITEIT, chkSociabiliteit, chkSociabiliteiten);

        ArrayList<CheckBox> chkGrondsoorten = new ArrayList<CheckBox>();
        chkGrondsoorten.add(chkGrondsoort_K);
        chkGrondsoorten.add(chkGrondsoort_Z);
        chkGrondsoorten.add(chkGrondsoort_L);
        BindCheckbox(ERequestArrayData.GRONDSOORT, chkGrondsoort, chkGrondsoorten);

        ArrayList<Spinner<Integer>> nudBladhoogtes = new ArrayList<Spinner<Integer>>();
        nudBladhoogtes.add(nudMinBladhoogte);
        nudBladhoogtes.add(nudMaxBladhoogte);
        BindSpinner(ERequestArrayData.BLADHOOGTE, chkBladHoogte, nudBladhoogtes);

        ArrayList<Spinner<Integer>> nudBloeihoogtes = new ArrayList<Spinner<Integer>>();
        nudBloeihoogtes.add(nudMinBloeihoogte);
        nudBloeihoogtes.add(nudMaxBloeihoogte);
        BindSpinner(ERequestArrayData.BLOEIHOOGTE, chkBloeiHoogte, nudBloeihoogtes);

        ArrayList<Spinner<Integer>> nudMaxBladhoogteMaand = new ArrayList<Spinner<Integer>>();
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Jan);
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Feb);
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Maa);
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Apr);
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Mei);
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Jun);
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Jul);
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Aug);
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Sept);
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Okt);
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Nov);
        nudMaxBladhoogteMaand.add(nudMaxBladhoogte_Dec);
        BindSpinner(ERequestArrayData.MAXBLADHOOGTEPERMAAND, chkMaxBladhoogtePerMaand, nudMaxBladhoogteMaand);

        ArrayList<Spinner<Integer>> nudMaxBloeihoogteMaand = new ArrayList<Spinner<Integer>>();
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Jan);
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Feb);
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Maa);
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Apr);
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Mei);
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Jun);
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Jul);
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Aug);
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Sept);
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Okt);
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Nov);
        nudMaxBloeihoogteMaand.add(nudMaxBloeihoogte_Dec);
        BindSpinner(ERequestArrayData.MAXBLOEIHOOGTEPERMAAND, chkMaxBloeihoogtePerMaand, nudMaxBloeihoogteMaand);

        ArrayList<Spinner<Integer>> nudMinBloeihoogteMaand = new ArrayList<Spinner<Integer>>();
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Jan);
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Feb);
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Maa);
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Apr);
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Mei);
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Jun);
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Jul);
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Aug);
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Sept);
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Okt);
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Nov);
        nudMinBloeihoogteMaand.add(nudMinBloeihoogte_Dec);
        BindSpinner(ERequestArrayData.MINBLOEIHOOGTEPERMAAND, chkMinBloeihoogtePerMaand, nudMinBloeihoogteMaand);

        ArrayList<ComboBox<String>> cboBladkleurMaand = new ArrayList<ComboBox<String>>();
        cboBladkleurMaand.add(cboBladkleurJan);
        cboBladkleurMaand.add(cboBladkleurFeb);
        cboBladkleurMaand.add(cboBladkleurMaa);
        cboBladkleurMaand.add(cboBladkleurApr);
        cboBladkleurMaand.add(cboBladkleurMei);
        cboBladkleurMaand.add(cboBladkleurJun);
        cboBladkleurMaand.add(cboBladkleurJul);
        cboBladkleurMaand.add(cboBladkleurAug);
        cboBladkleurMaand.add(cboBladkleurSep);
        cboBladkleurMaand.add(cboBladkleurOkt);
        cboBladkleurMaand.add(cboBladkleurNov);
        cboBladkleurMaand.add(cboBladkleurDec);
        BindCombobox(ERequestArrayData.BLADKLEURPERMAAND, chkBladkleurPerMaand, cboBladkleurMaand);

        ArrayList<ComboBox<String>> cboBloeikleurMaand = new ArrayList<ComboBox<String>>();
        cboBloeikleurMaand.add(cboBloeikleurJan);
        cboBloeikleurMaand.add(cboBloeikleurFeb);
        cboBloeikleurMaand.add(cboBloeikleurMaa);
        cboBloeikleurMaand.add(cboBloeikleurApr);
        cboBloeikleurMaand.add(cboBloeikleurMei);
        cboBloeikleurMaand.add(cboBloeikleurJun);
        cboBloeikleurMaand.add(cboBloeikleurJul);
        cboBloeikleurMaand.add(cboBloeikleurAug);
        cboBloeikleurMaand.add(cboBloeikleurSep);
        cboBloeikleurMaand.add(cboBloeikleurOkt);
        cboBloeikleurMaand.add(cboBloeikleurNov);
        cboBloeikleurMaand.add(cboBloeikleurDec);
        BindCombobox(ERequestArrayData.BLOEIKLEURPERMAAND, chkBloeikleurPerMaand, cboBloeikleurMaand);


    }

    /**
     * @param E        binding Enumeration
     * @param checkBox welke checkbox gebind moet worden
     * @Author bradley
     */
    public void Bind(ERequestData E, CheckBox checkBox, Slider slider,Label label) {
        slider.disableProperty().bind(checkBox.selectedProperty().not());
        bindingData.searchRequestData.get(E).DoSearchProperty().bind(checkBox.selectedProperty());
        bindingData.searchRequestData.get(E).Value().valueProperty().bind(label.textProperty());
    }

    /**
     * @param E        enum Bindings
     * @param checkBox te binden checkbox
     * @param comboBox te binden combobox
     * @author bradley
     */
    public void Bind(ERequestData E, CheckBox checkBox, ComboBox<String> comboBox) {
        comboBox.disableProperty().bind(checkBox.selectedProperty().not());
        bindingData.searchRequestData.get(E).DoSearchProperty().bind(checkBox.selectedProperty());
        bindingData.searchRequestData.get(E).Value().valueProperty().bind(comboBox.valueProperty().asString());
    }

    /**
     * @param E        enum Bindings
     * @param checkBox te binden checkbox
     * @param spinner  te binden spinner
     * @author bradley
     */
    public void Bind(ERequestData E, CheckBox checkBox, Spinner<Integer> spinner) {
        spinner.disableProperty().bind(checkBox.selectedProperty().not());
        bindingData.searchRequestData.get(E).DoSearchProperty().bind(checkBox.selectedProperty());
        bindingData.searchRequestData.get(E).Value().valueProperty().bind(spinner.valueProperty().asString());
    }

    public void BindSpinner(ERequestArrayData E, CheckBox checkBox, ArrayList<Spinner<Integer>> listSpinner) {
        bindingData.searchRequestArrayData.get(E).DoSearchProperty().bind(checkBox.selectedProperty());

        for (int i = 0; i < bindingData.searchRequestArrayData.get(E).Value().length; i++) {
            listSpinner.get(i).disableProperty().bind(checkBox.selectedProperty().not());
            bindingData.searchRequestArrayData.get(E).Value()[i].valueProperty().bind(listSpinner.get(i).valueProperty().asString());

        }
    }

    public void BindRadiobutton(ERequestArrayData E, CheckBox checkBox, ArrayList<RadioButton> listRadioButton) {

        bindingData.searchRequestArrayData.get(E).DoSearchProperty().bind(checkBox.selectedProperty());

        for (int i = 0; i < bindingData.searchRequestArrayData.get(E).Value().length; i++) {


            listRadioButton.get(i).disableProperty().bind(checkBox.selectedProperty().not());
            bindingData.searchRequestArrayData.get(E).Value()[i].valueProperty().bind(listRadioButton.get(i).textProperty());
            bindingData.searchRequestArrayData.get(E).Value()[i].isSelectedProperty().bind(listRadioButton.get(i).selectedProperty());

        }
    }

    public void BindCheckbox(ERequestArrayData E, CheckBox checkBox, ArrayList<CheckBox> listCheckbox) {
        bindingData.searchRequestArrayData.get(E).DoSearchProperty().bind(checkBox.selectedProperty());

        for (int i = 0; i < bindingData.searchRequestArrayData.get(E).Value().length; i++) {

            listCheckbox.get(i).disableProperty().bind(checkBox.selectedProperty().not());
            bindingData.searchRequestArrayData.get(E).Value()[i].valueProperty().bind(listCheckbox.get(i).textProperty());
            bindingData.searchRequestArrayData.get(E).Value()[i].isSelectedProperty().bind(listCheckbox.get(i).selectedProperty());
        }
    }

    public void BindCombobox(ERequestArrayData E, CheckBox checkBox, ArrayList<ComboBox<String>> listComboBOx){
        bindingData.searchRequestArrayData.get(E).DoSearchProperty().bind(checkBox.selectedProperty());

        for (int i = 0; i < bindingData.searchRequestArrayData.get(E).Value().length; i++) {

            listComboBOx.get(i).disableProperty().bind(checkBox.selectedProperty().not());
            bindingData.searchRequestArrayData.get(E).Value()[i].valueProperty().bind(listComboBOx.get(i).valueProperty());
        }
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
        cboBladkleur.getSelectionModel().selectFirst();
        //bloeikleur
        cboBloeikleur.getItems().addAll(infotables.getKleuren());
        cboBloeikleur.getSelectionModel().selectFirst();
        //bladgrootte
        cboBladgrootte.getItems().addAll(infotables.getBladgroottes());
        cboBladgrootte.getSelectionModel().selectFirst();
        //habitat
        cboHabitat.getItems().addAll(infotables.getHabitats());
        cboHabitat.getSelectionModel().selectFirst();
        //bladkleuren (per maand)
        cboBladkleurJan.getItems().addAll(infotables.getKleuren());
        cboBladkleurJan.getSelectionModel().selectFirst();
        cboBladkleurFeb.getItems().addAll(infotables.getKleuren());
        cboBladkleurFeb.getSelectionModel().selectFirst();
        cboBladkleurMaa.getItems().addAll(infotables.getKleuren());
        cboBladkleurMaa.getSelectionModel().selectFirst();
        cboBladkleurApr.getItems().addAll(infotables.getKleuren());
        cboBladkleurApr.getSelectionModel().selectFirst();
        cboBladkleurMei.getItems().addAll(infotables.getKleuren());
        cboBladkleurMei.getSelectionModel().selectFirst();
        cboBladkleurJun.getItems().addAll(infotables.getKleuren());
        cboBladkleurJun.getSelectionModel().selectFirst();
        cboBladkleurJul.getItems().addAll(infotables.getKleuren());
        cboBladkleurJul.getSelectionModel().selectFirst();
        cboBladkleurAug.getItems().addAll(infotables.getKleuren());
        cboBladkleurAug.getSelectionModel().selectFirst();
        cboBladkleurSep.getItems().addAll(infotables.getKleuren());
        cboBladkleurSep.getSelectionModel().selectFirst();
        cboBladkleurOkt.getItems().addAll(infotables.getKleuren());
        cboBladkleurOkt.getSelectionModel().selectFirst();
        cboBladkleurNov.getItems().addAll(infotables.getKleuren());
        cboBladkleurNov.getSelectionModel().selectFirst();
        cboBladkleurDec.getItems().addAll(infotables.getKleuren());
        cboBladkleurDec.getSelectionModel().selectFirst();
        //bloeikleuren (per maand)
        cboBloeikleurJan.getItems().addAll(infotables.getKleuren());
        cboBloeikleurJan.getSelectionModel().selectFirst();
        cboBloeikleurFeb.getItems().addAll(infotables.getKleuren());
        cboBloeikleurFeb.getSelectionModel().selectFirst();
        cboBloeikleurMaa.getItems().addAll(infotables.getKleuren());
        cboBloeikleurMaa.getSelectionModel().selectFirst();
        cboBloeikleurApr.getItems().addAll(infotables.getKleuren());
        cboBloeikleurApr.getSelectionModel().selectFirst();
        cboBloeikleurMei.getItems().addAll(infotables.getKleuren());
        cboBloeikleurMei.getSelectionModel().selectFirst();
        cboBloeikleurJun.getItems().addAll(infotables.getKleuren());
        cboBloeikleurJun.getSelectionModel().selectFirst();
        cboBloeikleurJul.getItems().addAll(infotables.getKleuren());
        cboBloeikleurJul.getSelectionModel().selectFirst();
        cboBloeikleurAug.getItems().addAll(infotables.getKleuren());
        cboBloeikleurAug.getSelectionModel().selectFirst();
        cboBloeikleurSep.getItems().addAll(infotables.getKleuren());
        cboBloeikleurSep.getSelectionModel().selectFirst();
        cboBloeikleurOkt.getItems().addAll(infotables.getKleuren());
        cboBloeikleurOkt.getSelectionModel().selectFirst();
        cboBloeikleurNov.getItems().addAll(infotables.getKleuren());
        cboBloeikleurNov.getSelectionModel().selectFirst();
        cboBloeikleurDec.getItems().addAll(infotables.getKleuren());
        cboBloeikleurDec.getSelectionModel().selectFirst();
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

    public void InitSpinners(){
        setSpinner(nudPerXJaar, 10);

        setSpinner(nudMinBloeihoogte, 1000);
        setSpinner(nudMaxBloeihoogte, 1000);
        setSpinner(nudMinBladhoogte, 1000);
        setSpinner(nudMaxBladhoogte, 1000);

        setSpinner(nudMinBloeihoogte_Jan, 1000);
        setSpinner(nudMinBloeihoogte_Feb, 1000);
        setSpinner(nudMinBloeihoogte_Maa, 1000);
        setSpinner(nudMinBloeihoogte_Apr, 1000);
        setSpinner(nudMinBloeihoogte_Mei, 1000);
        setSpinner(nudMinBloeihoogte_Jun, 1000);
        setSpinner(nudMinBloeihoogte_Jul, 1000);
        setSpinner(nudMinBloeihoogte_Aug, 1000);
        setSpinner(nudMinBloeihoogte_Sept, 1000);
        setSpinner(nudMinBloeihoogte_Okt, 1000);
        setSpinner(nudMinBloeihoogte_Nov, 1000);
        setSpinner(nudMinBloeihoogte_Dec, 1000);

        setSpinner(nudMaxBloeihoogte_Jan, 1000);
        setSpinner(nudMaxBloeihoogte_Feb, 1000);
        setSpinner(nudMaxBloeihoogte_Maa, 1000);
        setSpinner(nudMaxBloeihoogte_Apr, 1000);
        setSpinner(nudMaxBloeihoogte_Mei, 1000);
        setSpinner(nudMaxBloeihoogte_Jun, 1000);
        setSpinner(nudMaxBloeihoogte_Jul, 1000);
        setSpinner(nudMaxBloeihoogte_Aug, 1000);
        setSpinner(nudMaxBloeihoogte_Sept, 1000);
        setSpinner(nudMaxBloeihoogte_Okt, 1000);
        setSpinner(nudMaxBloeihoogte_Nov, 1000);
        setSpinner(nudMaxBloeihoogte_Dec, 1000);

        setSpinner(nudMaxBladhoogte_Jan, 1000);
        setSpinner(nudMaxBladhoogte_Feb, 1000);
        setSpinner(nudMaxBladhoogte_Maa, 1000);
        setSpinner(nudMaxBladhoogte_Apr, 1000);
        setSpinner(nudMaxBladhoogte_Mei, 1000);
        setSpinner(nudMaxBladhoogte_Jun, 1000);
        setSpinner(nudMaxBladhoogte_Jul, 1000);
        setSpinner(nudMaxBladhoogte_Aug, 1000);
        setSpinner(nudMaxBladhoogte_Sept, 1000);
        setSpinner(nudMaxBladhoogte_Okt, 1000);
        setSpinner(nudMaxBladhoogte_Nov, 1000);
        setSpinner(nudMaxBladhoogte_Dec, 1000);
    }

    public void setSpinner(Spinner<Integer> spinner, int MaxValue){
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, MaxValue));
    }

    public void Click_Search(MouseEvent mouseEvent) throws SQLException {
        SearchHandler handler = new SearchHandler(dbConnection);
        ArrayList<Integer> ids = handler.Search(bindingData, dbConnection);
        System.out.println(ids.toString());
    }
}


