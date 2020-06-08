package plantenApp.java.model.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import plantenApp.java.utils.ERequestData;

import java.util.HashMap;
import java.util.Map;

public class SliderLabel extends SearchBase {
    private IntegerProperty value = new SimpleIntegerProperty(0);
    private StringProperty actualValue = new SimpleStringProperty("");
    private HashMap<Integer,String> correspondingValues;

    public void setCorrespondingValues(HashMap<Integer,String> correspondingValues) {
        this.correspondingValues = correspondingValues;
    }

    public void Bind(CheckBox cbDoSearch, Slider slider, Label label) {
        slider.disableProperty().bind(cbDoSearch.selectedProperty().not());
        doSearchProperty().bind(cbDoSearch.selectedProperty());
        value.bind(slider.valueProperty());
        label.textProperty().bind(actualValue);
    }
    public void Update(){
        setActualValue(correspondingValues.get(getValue()));
    }

    public IntegerProperty valueProperty() {
        return value;
    }
    public int getValue() {
        return value.get();
    }
    public void setValue(int value) {
        this.value.set(value);
    }

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
