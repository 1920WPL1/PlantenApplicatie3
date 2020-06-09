package plantenApp.java.model.data;

import plantenApp.java.model.data.enums.*;

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

    public GUIdata() {
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
        sliderLabelDEM.get(ESliderLabel.BEZONNING).setCorrespondingValues(new String[]{"schaduw","shaduw - half schaduw","half schaduw", "half schaduw - zon","zon"});
        sliderLabelDEM.get(ESliderLabel.NECTARWAARDE).setCorrespondingValues(new String[]{"0","1","2","3","4","5"});
        sliderLabelDEM.get(ESliderLabel.POLLENWAARDE).setCorrespondingValues(new String[]{"0","1","2","3","4","5"});
        sliderLabelDEM.get(ESliderLabel.VOCHTBEHOEFTE).setCorrespondingValues(new String[]{"droog","droog of fris","fris","fris of vochtig","vochtig","vochtig-nat","nat"});
        sliderLabelDEM.get(ESliderLabel.VOEDINGSBEHOEFTE).setCorrespondingValues(new String[]{"arm","indifferent","matig","rijk"});

        //TODO kijken welke gegevens ik uit de DB kan krijgen en welke niet
        radiogroupDEM.get(ERadiogroup.BIJVRIENDELIJK).setCorrespondingValues(new String[]{"ja", "nee", "onbekend"});
        radiogroupDEM.get(ERadiogroup.VLINDERVRIENDELIJK).setCorrespondingValues(new String[]{"ja", "nee", "onbekend"});
        radiogroupDEM.get(ERadiogroup.VORSTGEVOELIG).setCorrespondingValues(new String[]{"ja", "nee", "onbekend"});
        radiogroupDEM.get(ERadiogroup.GEUREND).setCorrespondingValues(new String[]{"ja", "nee", "onbekend"});
        radiogroupDEM.get(ERadiogroup.KRUIDGEBRUIK).setCorrespondingValues(new String[]{"ja", "nee", "onbekend"});
        radiogroupDEM.get(ERadiogroup.EETBAAR).setCorrespondingValues(new String[]{"ja", "nee", "onbekend"});
        radiogroupDEM.get(ERadiogroup.STRATEGIE).setCorrespondingValues(new String[]{"C", "S", "R", "CS", "CR", "SR", "CSR"});
        radiogroupDEM.get(ERadiogroup.BLOEIWIJZE).setCorrespondingValues(new String[]{"aar", "brede pluim", "etage", "bol of knop", "margrietachtig", "schotel", "scherm", "smalle pluim"});
        radiogroupDEM.get(ERadiogroup.LEVENSVORM).setCorrespondingValues(new String[]{"1.Hydrofyt", "2.Hydrofyt", "3.Helofyt", "4.Cryptophyt", "5.Cryptophyt", "6.Hemikryptofyt", "7.Chamaefyt", "8.Chamaefyt", "9.Fanerophyt"});
        radiogroupDEM.get(ERadiogroup.HABITUS).setCorrespondingValues(new String[]{"tufted", "upright arching", "arching", "upright divergent", "upright erect", "mounded", "kruipend", "waaiervormig", "kussenvormend", "zuilvormig", "uitbuigend", "NOG AAN TE PASSEN", "Succulenten", "Pollenvormers", "parasolvormig"});

        //Set the mins and max values of the spinners
        spinnerDEM.get(ESpinner.PERXJAAR).setSpinnerMinMaxValue(0,100);
        multiSpinnerDEM.get(EMultiSpinner.BLADHOOGTE).setSpinnerMinMaxValue(0,100);
        multiSpinnerDEM.get(EMultiSpinner.BLOEIHOOGTE).setSpinnerMinMaxValue(0,100);
        multiSpinnerDEM.get(EMultiSpinner.MINBLADHOOGTEPERMAAND).setSpinnerMinMaxValue(0,100);
        multiSpinnerDEM.get(EMultiSpinner.MAXBLADHOOGTEPERMAAND).setSpinnerMinMaxValue(0,100);
        multiSpinnerDEM.get(EMultiSpinner.MINBLOEIHOOGTEPERMAAND).setSpinnerMinMaxValue(0,100);
        multiSpinnerDEM.get(EMultiSpinner.MAXBLOEIHOOGTEPERMAAND).setSpinnerMinMaxValue(0,100);
    }
}
