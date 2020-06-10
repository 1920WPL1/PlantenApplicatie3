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
            //Controleert of het aantal meegegeven checkboxes voldoende zijn voor het aan mogelijke waardes
            if (correspondingValues.length != checkboxes.length) {
                throw new IllegalArgumentException();
            }

            values = new BooleanProperty[checkboxes.length]; //Initialiseren van de values array, kijkt naar de hoeveelheid meegegeven waardes

            doSearchProperty().bind(cbDoSearch.selectedProperty());//Bind de checkbox value aan doSearch

            for (int i = 0; i < checkboxes.length; i++) {
                values[i] = new SimpleBooleanProperty(false);//Initialiseren van de values

                checkboxes[i].setText(correspondingValues[i]);//Zet de text van de checkbox naar de mogelijk waarde die hij representeert
                checkboxes[i].disableProperty().bind(cbDoSearch.selectedProperty().not());//Verbind de disable van de control met de checkbox
                valueProperty(i).bind(checkboxes[i].selectedProperty());//Verbind de value van de control met de
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
