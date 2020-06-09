package plantenApp.java.model.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.util.HashMap;

public class SliderLabelData extends SearchBase {
    private IntegerProperty value = new SimpleIntegerProperty(0);
    private StringProperty actualValue = new SimpleStringProperty("");
    private String[] correspondingValues;

    public void setCorrespondingValues(String[] correspondingValues) {
        this.correspondingValues = correspondingValues;
    }

    public void Bind(CheckBox cbDoSearch, Slider slider, Label label) {
        try {

            slider.setMin(0);
            slider.setMax(correspondingValues.length - 1);
            slider.setMajorTickUnit(1);
            slider.setMinorTickCount(0);
            slider.setShowTickMarks(true);
            slider.setShowTickLabels(false);
            slider.setSnapToTicks(true);
            slider.setValue(1);

            slider.valueProperty().addListener((observableValue, aBoolean, t1) -> {
                setActualValue(correspondingValues[getValue()]);
            });

            doSearchProperty().bind(cbDoSearch.selectedProperty());
            value.bind(slider.valueProperty());
            label.textProperty().bind(actualValue);
            slider.setValue(0);
            slider.disableProperty().bind(cbDoSearch.selectedProperty().not());

        } catch (NullPointerException nex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Checkbox: ").append(cbDoSearch).append(" slider: ").append(slider).append(" label: ").append(label).append('\n').append(nex.getMessage());
            System.out.println(sb.toString());
        }
    }

    //IntegerValue
    public IntegerProperty valueProperty() {
        return value;
    }

    public int getValue() {
        return value.get();
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    //ActualValue
    public StringProperty actualValueProperty() {
        return actualValue;
    }

    public String getActualValue() {
        return actualValue.get();
    }

    public void setActualValue(String actualValue) {
        this.actualValue.set(actualValue);
    }
}
