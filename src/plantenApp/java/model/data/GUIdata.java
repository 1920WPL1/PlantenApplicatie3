package plantenApp.java.model.data;

import plantenApp.java.model.data.enums.*;

import java.util.EnumMap;

//DEM = Data Enum Map

public class GUIdata {
    public EnumMap<EComboBox,ComboBoxData> comboBoxDEM = new EnumMap<>(EComboBox.class);
    public EnumMap<EMultiComboBox,MultiComboBoxData> multiComboBoxDEM = new EnumMap<>(EMultiComboBox.class);
    public EnumMap<ESliderLabel,SliderLabel> sliderLabelDEM = new EnumMap<>(ESliderLabel.class);
    public EnumMap<ESpinner,SpinnerData> spinnerDEM = new EnumMap<>(ESpinner.class);
    public EnumMap<EMultiSpinner,MultiSpinnerData> multiSpinnerDEM = new EnumMap<>(EMultiSpinner.class);
    public EnumMap<ECheckbox,CheckboxData> checkboxDEM = new EnumMap<>(ECheckbox.class);
    public EnumMap<EMultiCheckbox,MultiCheckboxData> multiCheckboxDEM = new EnumMap<>(EMultiCheckbox.class);
    public EnumMap<EComCheckbox,CombinedCheckboxData> combinedCheckboxDEM = new EnumMap<>(EComCheckbox.class);
    public EnumMap<ERadiogroup,RadiogroupData> radiogroupDEM = new EnumMap<>(ERadiogroup.class);

    public GUIdata() {
        multiCheckboxDEM.put(EMultiCheckbox.SOCIABILITEIT,new MultiCheckboxData());
    }
}
