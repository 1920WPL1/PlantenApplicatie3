package plantenApp.java.model.data;

import plantenApp.java.dao.InfoTablesDAO;
import plantenApp.java.model.InfoTables;
import plantenApp.java.model.data.enums.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumMap;

//DEM = Data Enum Map

public class GUIdata {
    public EnumMap<ETextfield, TextfieldData> textFieldDEM =
            new EnumMap<>(ETextfield.class);
    public EnumMap<EComboBox, ComboBoxData> comboBoxDEM =
            new EnumMap<>(EComboBox.class);
    public EnumMap<EMultiComboBox, MultiComboBoxData> multiComboBoxDEM =
            new EnumMap<>(EMultiComboBox.class);
    public EnumMap<ESliderLabel, SliderLabelData> sliderLabelDEM =
            new EnumMap<>(ESliderLabel.class);
    public EnumMap<ESpinner, SpinnerData> spinnerDEM =
            new EnumMap<>(ESpinner.class);
    public EnumMap<EMultiSpinner, MultiSpinnerData> multiSpinnerDEM =
            new EnumMap<>(EMultiSpinner.class);
    public EnumMap<EMultiCheckbox, MultiCheckboxData> multiCheckboxDEM =
            new EnumMap<>(EMultiCheckbox.class);
    public EnumMap<EComCheckbox, CombinedCheckboxData> combinedCheckboxDEM =
            new EnumMap<>(EComCheckbox.class);
    public EnumMap<ERadiogroup, RadiogroupData> radiogroupDEM =
            new EnumMap<>(ERadiogroup.class);

    public GUIdata(Connection dbConnection) throws SQLException {
        //TODO verander naamgeving van enum values array naar iets anders als a,b,c,...
        //Initialize all dataclasses depending on the enum classes
        ETextfield[] z = ETextfield.values();
        for (ETextfield eTextfield : z) {
            textFieldDEM.put(eTextfield, new TextfieldData());
        }
        EComboBox[] a = EComboBox.values();
        for (EComboBox eComboBox : a) {
            comboBoxDEM.put(eComboBox, new ComboBoxData());
        }
        EMultiComboBox[] b = EMultiComboBox.values();
        for (EMultiComboBox eMultiComboBox : b) {
            multiComboBoxDEM.put(eMultiComboBox, new MultiComboBoxData());
        }
        ESliderLabel[] c = ESliderLabel.values();
        for (ESliderLabel eSliderLabel : c) {
            sliderLabelDEM.put(eSliderLabel, new SliderLabelData());
        }
        ESpinner[] d = ESpinner.values();
        for (ESpinner eSpinner : d) {
            spinnerDEM.put(eSpinner, new SpinnerData());
        }
        EMultiSpinner[] e = EMultiSpinner.values();
        for (EMultiSpinner eMultiSpinner : e) {
            multiSpinnerDEM.put(eMultiSpinner, new MultiSpinnerData());
        }
        EMultiCheckbox[] g = EMultiCheckbox.values();
        for (EMultiCheckbox eMultiCheckbox : g) {
            multiCheckboxDEM.put(eMultiCheckbox, new MultiCheckboxData());
        }
        EComCheckbox[] h = EComCheckbox.values();
        for (EComCheckbox eComCheckbox : h) {
            combinedCheckboxDEM.put(eComCheckbox, new CombinedCheckboxData());
        }
        ERadiogroup[] j = ERadiogroup.values();
        for (ERadiogroup eRadiogroup : j) {
            radiogroupDEM.put(eRadiogroup, new RadiogroupData());
        }

        //Set the values for specific data objects
        combinedCheckboxDEM.get(EComCheckbox.GRONDSOORT).setCorrespondingValues(new String[]{"Z", "L", "K"});
        sliderLabelDEM.get(ESliderLabel.BEZONNING).setCorrespondingValues(new String[]{"schaduw", "shaduw - half schaduw", "half schaduw", "half schaduw - zon", "zon"});
        sliderLabelDEM.get(ESliderLabel.NECTARWAARDE).setCorrespondingValues(new String[]{"0", "1", "2", "3", "4", "5"});
        sliderLabelDEM.get(ESliderLabel.POLLENWAARDE).setCorrespondingValues(new String[]{"0", "1", "2", "3", "4", "5"});
        sliderLabelDEM.get(ESliderLabel.VOCHTBEHOEFTE).setCorrespondingValues(new String[]{"droog", "droog of fris", "fris", "fris of vochtig", "vochtig", "vochtig-nat", "nat"});
        sliderLabelDEM.get(ESliderLabel.VOEDINGSBEHOEFTE).setCorrespondingValues(new String[]{"arm", "indifferent", "matig", "rijk"});

        //Set the mins and max values of the spinners
        spinnerDEM.get(ESpinner.PERXJAAR).setSpinnerMinMaxValue(0, 100);
        multiSpinnerDEM.get(EMultiSpinner.BLADHOOGTE).setSpinnerMinMaxValue(0, 100);
        multiSpinnerDEM.get(EMultiSpinner.BLOEIHOOGTE).setSpinnerMinMaxValue(0, 100);
        multiSpinnerDEM.get(EMultiSpinner.MINBLADHOOGTEPERMAAND).setSpinnerMinMaxValue(0, 100);
        multiSpinnerDEM.get(EMultiSpinner.MAXBLADHOOGTEPERMAAND).setSpinnerMinMaxValue(0, 100);
        multiSpinnerDEM.get(EMultiSpinner.MINBLOEIHOOGTEPERMAAND).setSpinnerMinMaxValue(0, 100);
        multiSpinnerDEM.get(EMultiSpinner.MAXBLOEIHOOGTEPERMAAND).setSpinnerMinMaxValue(0, 100);


        InfoTablesDAO infoTablesDAO = new InfoTablesDAO(dbConnection);
        InfoTables infoTables = infoTablesDAO.getInfoTables();

        //Fills the comboboxes
        comboBoxDEM.get(EComboBox.TYPE).setPossibleValues(infoTables.getTypes());
        comboBoxDEM.get(EComboBox.FAMILIE).setPossibleValues(infoTables.getFamilies());
        comboBoxDEM.get(EComboBox.SPRUITFENOLOGIE).setPossibleValues(infoTables.getSpruitfenologieen());
        comboBoxDEM.get(EComboBox.RATIOBLOEIBLAD).setPossibleValues(infoTables.getBloeiBladRatios());
        comboBoxDEM.get(EComboBox.BLADGROOTTE).setPossibleValues(infoTables.getBladgroottes());
        comboBoxDEM.get(EComboBox.MAAND).setPossibleValues(new ArrayList<>() {{
            add("januari");
            add("februari");
            add("maart");
            add("april");
            add("mei");
            add("juni");
            add("juli");
            add("augustus");
            add("september");
            add("oktober");
            add("november");
            add("december");
        }});
        comboBoxDEM.get(EComboBox.REACTIEANTAGONISTISCHEOMGEVING).setPossibleValues(infoTables.getAntagonistischeOmgevingsReacties());
        comboBoxDEM.get(EComboBox.BLADKLEUR).setPossibleValues(infoTables.getKleuren());
        comboBoxDEM.get(EComboBox.ONTWIKKELINGSSNELHEID).setPossibleValues(infoTables.getOnstwikkelingssnelheden());
        comboBoxDEM.get(EComboBox.LEVENSDUUR).setPossibleValues(infoTables.getConcurentiekrachten());
        comboBoxDEM.get(EComboBox.HABITAT).setPossibleValues(infoTables.getHabitats());
        comboBoxDEM.get(EComboBox.BLOEIKLEUR).setPossibleValues(infoTables.getKleuren());
        comboBoxDEM.get(EComboBox.BLADVORM).setPossibleValues(infoTables.getBladvormen());
        comboBoxDEM.get(EComboBox.BEHANDELING).setPossibleValues(infoTables.getBeheerdaden());
        multiComboBoxDEM.get(EMultiComboBox.BLOEIKLEURPERMAAND).setPossibleValues(infoTables.getKleuren());
        multiComboBoxDEM.get(EMultiComboBox.BLADKLEURPERMAAND).setPossibleValues(infoTables.getKleuren());

        //Fills the radio button
        //TODO kijken welke gegevens ik uit de DB kan krijgen en welke niet
        radiogroupDEM.get(ERadiogroup.BIJVRIENDELIJK).setCorrespondingValues(new ArrayList<String>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});
        radiogroupDEM.get(ERadiogroup.VLINDERVRIENDELIJK).setCorrespondingValues(new ArrayList<String>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});
        radiogroupDEM.get(ERadiogroup.VORSTGEVOELIG).setCorrespondingValues(new ArrayList<String>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});
        radiogroupDEM.get(ERadiogroup.GEUREND).setCorrespondingValues(new ArrayList<String>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});
        radiogroupDEM.get(ERadiogroup.KRUIDGEBRUIK).setCorrespondingValues(new ArrayList<String>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});
        radiogroupDEM.get(ERadiogroup.EETBAAR).setCorrespondingValues(new ArrayList<String>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});

        //radiogroupDEM.get(ERadiogroup.STRATEGIE).setCorrespondingValues(new String[]{"C", "S", "R", "CS", "CR", "SR", "CSR"});
        radiogroupDEM.get(ERadiogroup.STRATEGIE).setCorrespondingValues(infoTables.getStratergieen());

        //radiogroupDEM.get(ERadiogroup.BLOEIWIJZE).setCorrespondingValues(new String[]{"aar", "brede pluim", "etage", "bol of knop", "margrietachtig", "schotel", "scherm", "smalle pluim"});
        radiogroupDEM.get(ERadiogroup.BLOEIWIJZE).setCorrespondingValues(infoTables.getBloeiwijzes());

        //radiogroupDEM.get(ERadiogroup.LEVENSVORM).setCorrespondingValues(new String[]{"1.Hydrofyt", "2.Hydrofyt", "3.Helofyt", "4.Cryptophyt", "5.Cryptophyt", "6.Hemikryptofyt", "7.Chamaefyt", "8.Chamaefyt", "9.Fanerophyt"});
        radiogroupDEM.get(ERadiogroup.LEVENSVORM).setCorrespondingValues(new ArrayList<String>() {{
            add("1.Hydrofyt");
            add("2.Hydrofyt");
            add("3.Helofyt");
            add("4.Cryptophyt");
            add("5.Cryptophyt");
            add("6.Hemikryptofyt");
            add("7.Chamaefyt");
            add("8.Chamaefyt");
            add("9.Fanerophyt");
        }});

        //radiogroupDEM.get(ERadiogroup.HABITUS).setCorrespondingValues(new String[]{"tufted", "upright arching", "arching", "upright divergent", "upright erect", "mounded", "kruipend", "waaiervormig", "kussenvormend", "zuilvormig", "uitbuigend", "NOG AAN TE PASSEN", "Succulenten", "Pollenvormers", "parasolvormig"});
        radiogroupDEM.get(ERadiogroup.HABITUS).setCorrespondingValues(infoTables.getHabitusMogelijkheden());
    }
}
