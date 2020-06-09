package plantenApp.java.model.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class CombinedCheckboxData extends SearchBase {
    protected BooleanProperty[] values;
    private String[] correspondingValues;

    public void setCorrespondingValues(String[] correspondingValues) {
        this.correspondingValues = correspondingValues;
    }

    public void Bind(CheckBox cbDoSearch, CheckBox[] checkboxes) {
        try {
            if (correspondingValues.length != checkboxes.length) {
                throw new IllegalArgumentException();
            }

            doSearchProperty().bind(cbDoSearch.selectedProperty());

            values = new BooleanProperty[checkboxes.length];
            for (int i = 0; i < checkboxes.length; i++) {
                values[i] = new SimpleBooleanProperty(false);

                checkboxes[i].setText(correspondingValues[i]);
                checkboxes[i].disableProperty().bind(cbDoSearch.selectedProperty().not());
                valueProperty(i).bind(checkboxes[i].selectedProperty());
            }
        } catch (NullPointerException nex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Checkbox: ").append(cbDoSearch).append(" checkboxes: ");
            for (int i = 0; i < checkboxes.length; i++) {
                sb.append(i).append(": ").append(checkboxes[i]);
            }
            sb.append('\n').append(nex.getMessage());
            System.out.println(sb.toString());
        } catch (IllegalArgumentException iae) {
            System.out.println("!!!Problem loading data from checkboxes, does not correspond with values: " + Arrays.toString(correspondingValues));
        }
    }

    public int Length() {
        return values.length;
    }

    /**
     * @return The actual value that the multicheckbox represents
     */
    public String getActualValue() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (getValue(i)) {
                result.append(correspondingValues[i]);
            }
        }
        return result.toString();
    }

    //BooleanValue
    public BooleanProperty valueProperty(int i) {
        return values[i];
    }

    public boolean getValue(int i) {
        return values[i].get();
    }

    public void setValue(int i, boolean value) {
        values[i].set(value);
    }
}
