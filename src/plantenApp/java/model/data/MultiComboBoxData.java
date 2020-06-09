package plantenApp.java.model.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class MultiComboBoxData extends SearchBase {
    private StringProperty[] values;
    private ArrayList<String> possibleValues;

    public void Bind(CheckBox cbDoSearch, ComboBox<String>[] comboBoxes) {
        try {
            doSearchProperty().bind(cbDoSearch.selectedProperty());

            values = new StringProperty[comboBoxes.length];
            for (int i = 0; i < comboBoxes.length; i++) {
                values[i] = new SimpleStringProperty("");

                updateComboBoxWithPossibleValues(comboBoxes[i]);
                comboBoxes[i].disableProperty().bind(cbDoSearch.selectedProperty().not());
                valueProperty(i).bind(comboBoxes[i].valueProperty());
            }
        } catch (NullPointerException nex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Checkbox: ").append(cbDoSearch).append(" comboboxes: ");
            for (int i = 0; i < comboBoxes.length; i++) {
                sb.append(i).append(": ").append(comboBoxes[i]);
            }
            sb.append('\n').append(nex.getMessage());
            System.out.println(sb.toString());
        }
    }

    public ArrayList<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(ArrayList<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public void updateComboBoxWithPossibleValues(ComboBox<String> comboBox){
        comboBox.getItems().clear();
        comboBox.getItems().addAll(possibleValues);
        comboBox.getSelectionModel().selectFirst();
    }

    //StringValue
    public StringProperty valueProperty(int i) {
        return values[i];
    }

    public String getValue(int i) {
        return values[i].get();
    }

    public void setValue(int i, String value) {
        values[i].set(value);
    }
}
