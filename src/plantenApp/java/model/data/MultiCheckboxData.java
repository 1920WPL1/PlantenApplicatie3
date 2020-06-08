package plantenApp.java.model.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;

public class MultiCheckboxData extends SearchBase {
    protected BooleanProperty[] values;

    public void Bind(CheckBox cbDoSearch, CheckBox[] checkboxes) {
        values = new BooleanProperty[checkboxes.length];

        doSearchProperty().bind(cbDoSearch.selectedProperty());
        for (int i = 0;i<checkboxes.length;i++)
        {
            values[i] = new SimpleBooleanProperty(false);

            checkboxes[i].disableProperty().bind(cbDoSearch.selectedProperty().not());
            valueProperty(i).bind(checkboxes[i].selectedProperty());
        }
    }

    public BooleanProperty valueProperty(int i) {
        return values[i];
    }

    public boolean getValue(int i) {
        return values[i].get();
    }

    public void setValue(int i,boolean value) {
        values[i].set(value);
    }
}
