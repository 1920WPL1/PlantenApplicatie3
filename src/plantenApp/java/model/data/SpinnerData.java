package plantenApp.java.model.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;

public class SpinnerData extends SearchBase {
    private IntegerProperty value = new SimpleIntegerProperty(0);

    public void Bind(CheckBox cbDoSearch, Spinner<Integer> spinner) {
        doSearchProperty().bind(cbDoSearch.selectedProperty());
        spinner.disableProperty().bind(cbDoSearch.selectedProperty().not());
        valueProperty().bind(spinner.valueProperty());
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
}
