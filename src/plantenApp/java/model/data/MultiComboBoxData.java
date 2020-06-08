package plantenApp.java.model.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;

public class MultiComboBoxData extends SearchBase{
    private StringProperty[] values;

    public void Bind(CheckBox cbDoSearch, ArrayList<ComboBox<String>>  comboBoxes) {
        values = new StringProperty[comboBoxes.size()-1];

        doSearchProperty().bind(cbDoSearch.selectedProperty());
        for (int i = 0;i<comboBoxes.size();i++)
        {
            values[i] = new SimpleStringProperty("");

            comboBoxes.get(i).disableProperty().bind(cbDoSearch.selectedProperty().not());
            valueProperty(i).bind(comboBoxes.get(i).valueProperty());

        }
    }

    public StringProperty valueProperty(int i) {
        return values[i];
    }

    public String getValue(int i) {
        return values[i].get();
    }

    public void setValue(int i,String value) {
        values[i].set(value);
    }
}
