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
    public EnumMap<EFoto,FotoData> fotoDEM =
            new EnumMap<>(EFoto.class);


    public GUIdata(Connection dbConnection) throws SQLException {
        //TODO verander naamgeving van enum values array naar iets anders als a,b,c,...
        //Initialize all dataclasses depending on the enum classes
        EFoto[] y = EFoto.values();
        for (EFoto eFoto : y) {
            fotoDEM.put(eFoto, new FotoData());
        }
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
        ArrayList<String> l = infoTables.getGeslachten();
        l.sort(String::compareTo);
        comboBoxDEM.get(EComboBox.GESLACHT).setPossibleValues(l);
        l = infoTables.getSoorten();
        l.sort(String::compareTo);
        comboBoxDEM.get(EComboBox.SOORT).setPossibleValues(l);
        l = infoTables.getVariaties();
        l.sort(String::compareTo);
        comboBoxDEM.get(EComboBox.VARIANT).setPossibleValues(l);

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
        radiogroupDEM.get(ERadiogroup.BIJVRIENDELIJK).setCorrespondingValues(new ArrayList<>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});
        radiogroupDEM.get(ERadiogroup.VLINDERVRIENDELIJK).setCorrespondingValues(new ArrayList<>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});
        radiogroupDEM.get(ERadiogroup.VORSTGEVOELIG).setCorrespondingValues(new ArrayList<>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});
        radiogroupDEM.get(ERadiogroup.GEUREND).setCorrespondingValues(new ArrayList<>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});
        radiogroupDEM.get(ERadiogroup.KRUIDGEBRUIK).setCorrespondingValues(new ArrayList<>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});
        radiogroupDEM.get(ERadiogroup.EETBAAR).setCorrespondingValues(new ArrayList<>() {{
            add("ja");
            add("nee");
            add("onbekend");
        }});
        radiogroupDEM.get(ERadiogroup.STRATEGIE).setCorrespondingValues(infoTables.getStratergieen());
        radiogroupDEM.get(ERadiogroup.BLOEIWIJZE).setCorrespondingValues(infoTables.getBloeiwijzes());
        radiogroupDEM.get(ERadiogroup.LEVENSVORM).setCorrespondingValues(new ArrayList<>() {{
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
        radiogroupDEM.get(ERadiogroup.HABITUS).setCorrespondingValues(infoTables.getHabitusMogelijkheden());

        combinedCheckboxDEM.get(EComCheckbox.GRONDSOORT).setCorrespondingValues(new String[]{"Z", "L", "K"});
        sliderLabelDEM.get(ESliderLabel.BEZONNING).setCorrespondingValues(infoTables.getBezonningsMogelijkheden());

        l = new ArrayList<>();
        for (int i = 0; i < infoTables.getNectarwaardes().size(); i++) {
            l.add(String.valueOf(infoTables.getNectarwaardes().get(i)));
        }
        sliderLabelDEM.get(ESliderLabel.NECTARWAARDE).setCorrespondingValues(l);
        l = new ArrayList<>();
        for (int i = 0; i < infoTables.getPollenwaardes().size(); i++) {
            l.add(String.valueOf(infoTables.getPollenwaardes().get(i)));
        }
        sliderLabelDEM.get(ESliderLabel.POLLENWAARDE).setCorrespondingValues(l);
        sliderLabelDEM.get(ESliderLabel.VOCHTBEHOEFTE).setCorrespondingValues(infoTables.getVochtbehoeftes());
        sliderLabelDEM.get(ESliderLabel.VOEDINGSBEHOEFTE).setCorrespondingValues(infoTables.getVoedingsbehoeftes());

        fotoDEM.get(EFoto.BLOEIWIJZE).setImages(infoTables.getBloeiwijzeFotos());
        fotoDEM.get(EFoto.HABITUS).setImages(infoTables.getHabitusFotos());
        //fotoDEM.get(EFoto.LEVENSVORM).setImages();
    }
}
