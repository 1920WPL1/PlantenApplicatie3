package plantenApp;
import plantenApp.java.dao.Database;
import plantenApp.java.dao.InfoTablesDAO;
import plantenApp.java.model.BindingData;
import plantenApp.java.model.InfoTables;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import plantenApp.java.model.SearchHandler;
import plantenApp.java.utils.ArrayBindings;
import plantenApp.java.utils.Bindings;


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
    public Spinner<Integer> nudBloeiHoogte;
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


    private InfoTables infoTables;
    private Connection dbConnection;
    BindingData bindingData;

    public void initialize() throws SQLException {


        dbConnection = Database.getInstance().getConnection();
        /*infotabel object aanmaken*/
        InfoTablesDAO infotablesDAO = new InfoTablesDAO(dbConnection);
       infoTables = infotablesDAO.getInfoTables();

       FillComboboxes(infoTables);
        bindingData = new BindingData();


        Bind(Bindings.BLADVORM, chkBladvorm, cboBladvorm);
        Bind(Bindings.SPRUITFENOLOGIE, chkSpruitfenologie, cboSpruitfenologie);
        Bind(Bindings.VOEDINGSBEHOEFTE, chkVoedingsbehoefte, sldVoedingsbehoefte);
        Bind(Bindings.BLADGROOTTE, chkBladgrootte, cboBladgrootte);
        Bind(Bindings.BLADHOOGTE, chkBladHoogte, nudMaxBladhoogte);
        Bind(Bindings.FAMILIE, chkFamilie, cboFamilie);
        Bind(Bindings.TYPE, chkType, cboType);
        Bind(Bindings.HABITAT, chkHabitat, cboHabitat);
        Bind(Bindings.BLADKLEUR, chkBladKleur, cboBladkleur);
        Bind(Bindings.BLOEIKLEUR, chkBloeiKleur, cboBloeikleur);
        Bind(Bindings.REACTIEANTAGONISTISCHEOMGEVING, chkReactieAntagonistischeOmg, cboReactieAnta);
        Bind(Bindings.ONTWIKKELINGSSNELHEID, chkOntwikkelingssnelheid, cboOntwikkel);
        Bind(Bindings.LEVENSDUUR, chkLevensduur_concurrentiekracht, cboLevensduur);
        Bind(Bindings.NECTARWAARDE, chkNectarwaarde, sldNectarwaarde);
        Bind(Bindings.POLLENWAARDE, chkPollenwaarde, sldPollenwaarde);
        Bind(Bindings.RATIOBLOEIBLAD, chkRatio_bloei_blad, cboRatioBloeiBlad);
        Bind(Bindings.BEHANDELING, chkBehandeling, cboBehandeling);
        Bind(Bindings.MAAND, chkMaand, cboMaand);
        Bind(Bindings.PERXJAAR, chkPerXJaar, nudPerXJaar);


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

        BindRadiobutton(ArrayBindings.LEVENSVORM, chkLevensvormVolgensRaunkhiaer, rdbLevensvormen);

        ArrayList<RadioButton> rdbStrategieën = new ArrayList<RadioButton>();
        rdbStrategieën.add(rdbStrategie_C);
        rdbStrategieën.add(rdbStrategie_R);
        rdbStrategieën.add(rdbStrategie_S);
        rdbStrategieën.add(rdbStrategie_CR);
        rdbStrategieën.add(rdbStrategie_CS);
        rdbStrategieën.add(rdbStrategie_RS);
        rdbStrategieën.add(rdbStrategie_CRS);
        rdbStrategieën.add(rdbStrategie_Onbekend);
        BindRadiobutton(ArrayBindings.STRATEGIE, chkStrategie, rdbStrategieën);

        ArrayList<RadioButton> rdbBijvriendelijken = new ArrayList<RadioButton>();
        rdbBijvriendelijken.add(rdbBijvriendelijk_Ja);
        rdbBijvriendelijken.add(rdbBijvriendelijk_Nee);
        rdbBijvriendelijken.add(rdbBijvriendelijk_Onbekend);
        BindRadiobutton(ArrayBindings.BIJVRIENDELIJK, chkBijvriendelijk, rdbBijvriendelijken);

        ArrayList<RadioButton> rdbVlindervriendelijken = new ArrayList<RadioButton>();
        rdbVlindervriendelijken.add(rdbVlindervriendelijk_Ja);
        rdbVlindervriendelijken.add(rdbVlindervriendelijk_Nee);
        rdbVlindervriendelijken.add(rdbVlindervriendelijk_Onbekend);
        BindRadiobutton(ArrayBindings.VLINDERVRIENDELIJK, chkVlindervriendelijk, rdbVlindervriendelijken);

        ArrayList<RadioButton> rdbGeurenden = new ArrayList<RadioButton>();
        rdbGeurenden.add(rdbGeurend_Ja);
        rdbGeurenden.add(rdbGeurend_Nee);
        rdbGeurenden.add(rdbGeurend_Onbekend);
        BindRadiobutton(ArrayBindings.GEUREND, chkGeurend, rdbGeurenden);

        ArrayList<RadioButton> rdbEetbaren = new ArrayList<RadioButton>();
        rdbEetbaren.add(rdbEetbaar_Ja);
        rdbEetbaren.add(rdbEetbaar_Nee);
        rdbEetbaren.add(rdbEetbaar_Onbekend);
        BindRadiobutton(ArrayBindings.EETBAAR, chkEetbaar, rdbEetbaren);

        ArrayList<RadioButton> rdkKruidgebruiken = new ArrayList<RadioButton>();
        rdkKruidgebruiken.add(rdbKruidgebruik_Ja);
        rdkKruidgebruiken.add(rdbKruidgebruik_Nee);
        rdkKruidgebruiken.add(rdbKruidgebruik_Onbekend);
        BindRadiobutton(ArrayBindings.KRUIDGEBRUIK, chkKruidgebruik, rdkKruidgebruiken);

        ArrayList<RadioButton> rdbVorstgevoeligen = new ArrayList<RadioButton>();
        rdbVorstgevoeligen.add(rdbVorstgevoelig_Ja);
        rdbVorstgevoeligen.add(rdbVorstgevoelig_Nee);
        rdbVorstgevoeligen.add(rdbVorstgevoelig_Onbekend);
        BindRadiobutton(ArrayBindings.VORSTGEVOELIG, chkVorstgevoelig, rdbVorstgevoeligen);

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
        BindRadiobutton(ArrayBindings.HABITUS, chkHabitus, rdbHabitusen);

        ArrayList<RadioButton> rdbBloeiwijzes = new ArrayList<RadioButton>();
        rdbBloeiwijzes.add(rdbBloeiwijze_Etage);
        rdbBloeiwijzes.add(rdbBloeiwijze_Aar);
        rdbBloeiwijzes.add(rdbBloeiwijze_BolofKnop);
        rdbBloeiwijzes.add(rdbBloeiwijze_BredePluim);
        rdbBloeiwijzes.add(rdbBloeiwijze_Margrietachtig);
        rdbBloeiwijzes.add(rdbBloeiwijze_Scherm);
        rdbBloeiwijzes.add(rdbBloeiwijze_Schotel);
        rdbBloeiwijzes.add(rdbBloeiwijze_SmallePluim);
        BindRadiobutton(ArrayBindings.BLOEIWIJZE, chkBloeiwijze, rdbBloeiwijzes);

        ArrayList<CheckBox> chkSociabiliteiten = new ArrayList<CheckBox>();
        chkSociabiliteiten.add(chkSociabiliteit_1);
        chkSociabiliteiten.add(chkSociabiliteit_2);
        chkSociabiliteiten.add(chkSociabiliteit_3);
        chkSociabiliteiten.add(chkSociabiliteit_4);
        chkSociabiliteiten.add(chkSociabiliteit_5);
        BindCheckbox(ArrayBindings.SOCIABILITEIT, chkSociabiliteit, chkSociabiliteiten);

        ArrayList<CheckBox> chkGrondsoorten = new ArrayList<CheckBox>();
        chkGrondsoorten.add(chkGrondsoort_K);
        chkGrondsoorten.add(chkGrondsoort_Z);
        chkGrondsoorten.add(chkGrondsoort_L);
        BindCheckbox(ArrayBindings.GRONDSOORT, chkGrondsoort, chkGrondsoorten);






        InitSpinners();
    }

    /**
     * @Author bradley
     * @param E binding Enumeration
     * @param checkBox welke checkbox gebind moet worden
     */
    public void Bind(Bindings E, CheckBox checkBox, Slider slider){
        slider.disableProperty().bind(checkBox.selectedProperty().not());
        bindingData.dataBindings.get(E).DoSearchProperty().bind(checkBox.selectedProperty());
        bindingData.dataBindings.get(E).getValue().valueProperty().bind(slider.valueProperty().asString());
    }

    /**
     * @author bradley
     * @param E enum Bindings
     * @param checkBox te binden checkbox
     * @param comboBox te binden combobox
     */
    public void Bind(Bindings E, CheckBox checkBox, ComboBox<String> comboBox){
        comboBox.disableProperty().bind(checkBox.selectedProperty().not());
        bindingData.dataBindings.get(E).DoSearchProperty().bind(checkBox.selectedProperty());
        bindingData.dataBindings.get(E).getValue().valueProperty().bind(comboBox.valueProperty().asString());
    }

    /**
     * @author bradley
     * @param E enum Bindings
     * @param checkBox te binden checkbox
     * @param spinner te binden spinner
     */
    public void Bind(Bindings E, CheckBox checkBox, Spinner<Integer> spinner){
        spinner.disableProperty().bind(checkBox.selectedProperty().not());
        bindingData.dataBindings.get(E).DoSearchProperty().bind(checkBox.selectedProperty());
        bindingData.dataBindings.get(E).getValue().valueProperty().bind(spinner.valueProperty().asString());
    }

    public void BindSpinner(ArrayBindings E, CheckBox checkBox, ArrayList<Spinner<Integer>> listSpinner){
        bindingData.arrayDataBindings.get(E).DoSearchProperty().bind(checkBox.selectedProperty().not());

        for (int i = 0; i<bindingData.arrayDataBindings.get(E).getValue().length;i++) {

            listSpinner.get(i).disableProperty().bind(checkBox.selectedProperty().not());
            bindingData.arrayDataBindings.get(E).getValue()[i].valueProperty().bind(listSpinner.get(i).valueProperty().asString());

        }
    }

    public void BindRadiobutton(ArrayBindings E, CheckBox checkBox, ArrayList<RadioButton> listRadioButton){

        bindingData.arrayDataBindings.get(E).DoSearchProperty().bind(checkBox.selectedProperty().not());

        for (int i = 0; i<bindingData.arrayDataBindings.get(E).getValue().length;i++) {


            listRadioButton.get(i).disableProperty().bind(checkBox.selectedProperty().not());
            bindingData.arrayDataBindings.get(E).getValue()[i].valueProperty().bind(listRadioButton.get(i).textProperty());
            bindingData.arrayDataBindings.get(E).getValue()[i].boolProperty().bind(listRadioButton.get(i).selectedProperty());

        }
    }

    public void BindCheckbox(ArrayBindings E, CheckBox checkBox, ArrayList<CheckBox> listCheckbox){
        bindingData.arrayDataBindings.get(E).DoSearchProperty().bind(checkBox.selectedProperty().not());

        for (int i = 0; i<bindingData.arrayDataBindings.get(E).getValue().length;i++) {

            listCheckbox.get(i).disableProperty().bind(checkBox.selectedProperty().not());
            bindingData.arrayDataBindings.get(E).getValue()[i].valueProperty().bind(listCheckbox.get(i).textProperty());
            bindingData.arrayDataBindings.get(E).getValue()[i].boolProperty().bind(listCheckbox.get(i).selectedProperty());
        }
    }

    public void BindCombobox(ArrayBindings E, CheckBox checkBox, ArrayList<ComboBox> listComboBOx){
        bindingData.arrayDataBindings.get(E).DoSearchProperty().bind(checkBox.selectedProperty().not());

        for (int i = 0; i<bindingData.arrayDataBindings.get(E).getValue().length;i++) {

            listComboBOx.get(i).disableProperty().bind(checkBox.selectedProperty().not());
            bindingData.arrayDataBindings.get(E).getValue()[i].valueProperty().bind(listComboBOx.get(i).valueProperty());
        }
    }




    /**
     @param infotables -> lijst van alle lijsten van gegevens uit de naakte tabellen
     @author bradley, angelo
     Functie om comboboxes te vullen met alle gegevens uit de database
     */

    public void FillComboboxes(InfoTables infotables){

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
        cboMaand.getItems().addAll("januari", "februari");
        cboBehandeling.getItems().addAll("test", "test2","test3");
    }

    public void InitSpinners(){
        setSpinner(nudPerXJaar, 10);

        setSpinner(nudMinBladhoogte, 1000);
        setSpinner(nudBloeiHoogte, 1000);
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
    }

    public void setSpinner(Spinner spinner, int MaxValue){
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, MaxValue));
    }

    public void Click_Search(MouseEvent mouseEvent) throws SQLException {
        SearchHandler handler = new SearchHandler(dbConnection);
        ArrayList<Integer> ids = handler.Search(bindingData, dbConnection);
        System.out.println(ids.toString());
    }
}


