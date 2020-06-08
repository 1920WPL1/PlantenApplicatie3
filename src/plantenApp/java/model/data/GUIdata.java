package plantenApp.java.model.data;

import plantenApp.java.model.data.enums.*;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

//DEM = Data Enum Map

public class GUIdata {
    public EnumMap<EComboBox, ComboBoxData> comboBoxDEM =
            new EnumMap<>(EComboBox.class);
    public EnumMap<EMultiComboBox, MultiComboBoxData> multiComboBoxDEM =
            new EnumMap<>(EMultiComboBox.class);
    public EnumMap<ESliderLabel, SliderLabel> sliderLabelDEM =
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
        //Initialize all dataclasses depending on the enum classes
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
            sliderLabelDEM.put(eSliderLabel, new SliderLabel());
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
        sliderLabelDEM.get(ESliderLabel.BEZONNING).setCorrespondingValues(new HashMap<>() {
            {
                put(1, "schaduw");
                put(2, "shaduw - half schaduw");
                put(3, "half schaduw");
                put(4, "half schaduw - zon");
                put(5, "zon");
            }
        });
        sliderLabelDEM.get(ESliderLabel.NECTARWAARDE).setCorrespondingValues(new HashMap<>() {
            {
                put(0, "0");
                put(1, "1");
                put(2, "2");
                put(3, "3");
                put(4, "4");
                put(5, "5");
            }
        });
        sliderLabelDEM.get(ESliderLabel.POLLENWAARDE).setCorrespondingValues(new HashMap<>() {
            {
                put(0, "0");
                put(1, "1");
                put(2, "2");
                put(3, "3");
                put(4, "4");
                put(5, "5");
            }
        });
        sliderLabelDEM.get(ESliderLabel.VOCHTBEHOEFTE).setCorrespondingValues(new HashMap<>() {
            {
                put(1, "droog");
                put(2, "droog of fris");
                put(3, "fris");
                put(4, "fris of vochtig");
                put(5, "vochtig");
                put(6, "vochtig-nat");
                put(7, "nat");
            }
        });
        sliderLabelDEM.get(ESliderLabel.VOEDINGSBEHOEFTE).setCorrespondingValues(new HashMap<>() {
            {
                put(1, "arm");
                put(2, "indifferent");
                put(3, "matig");
                put(4, "rijk");
            }
        });
    }
}
