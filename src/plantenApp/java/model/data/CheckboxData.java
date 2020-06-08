package plantenApp.java.model.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;

public class CheckboxData extends SearchBase {
    private BooleanProperty value = new SimpleBooleanProperty(false);

    public void Bind(CheckBox cbDoSearch, CheckBox checkBox) {
        doSearchProperty().bind(cbDoSearch.selectedProperty());
        checkBox.disableProperty().bind(cbDoSearch.selectedProperty().not());
        valueProperty().bind(checkBox.selectedProperty());
    }

    public boolean getValue() {
        return value.get();
    }

    public BooleanProperty valueProperty() {
        return value;
    }

    public void setValue(boolean value) {
        this.value.set(value);
    }
}
