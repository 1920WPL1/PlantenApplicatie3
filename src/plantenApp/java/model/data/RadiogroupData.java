package plantenApp.java.model.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;
import java.util.Arrays;

public class RadiogroupData extends SearchBase {
    protected BooleanProperty[] values;
    private String[] correspondingValues;

    public RadiogroupData() {
    }

    public void setCorrespondingValues(String[] correspondingValues) {
        this.correspondingValues = correspondingValues;
    }

    public void Bind(CheckBox cbDoSearch, RadioButton[] radioButtons) {
        try {
            if (correspondingValues.length != radioButtons.length){throw new IllegalArgumentException();}

            doSearchProperty().bind(cbDoSearch.selectedProperty());

            values = new BooleanProperty[radioButtons.length];
            for (int i = 0; i < radioButtons.length; i++) {
                values[i] = new SimpleBooleanProperty(false);

                radioButtons[i].setText(correspondingValues[i]);
                radioButtons[i].disableProperty().bind(cbDoSearch.selectedProperty().not());
                valueProperty(i).bind(radioButtons[i].selectedProperty());
            }
        } catch (NullPointerException nex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Checkbox: ").append(cbDoSearch).append(" radioButtons: ");
            for (int i = 0; i < radioButtons.length; i++) {
                sb.append(" ").append(i).append(": ").append(radioButtons[i]);
            }
            sb.append('\n').append(nex.getMessage());
            System.out.println(sb.toString());
        } catch (IllegalArgumentException iae){
            System.out.println("!!!Problem loading data from radioButtons, does not correspond with values: " + Arrays.toString(correspondingValues));
        }
    }

    /**
     * @return The actual value that the multicheckbox represents
     */
    public String getActualValue() {
        String result = "";
        for (int i = 0; i < values.length; i++) {
            if (values[i].get()) {
                result = correspondingValues[i];
            }
        }
        return result;
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
