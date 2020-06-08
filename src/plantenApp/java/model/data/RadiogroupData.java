package plantenApp.java.model.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;

public class RadiogroupData extends SearchBase {
    protected BooleanProperty[] values;
    private String[] correspondingValues;

    public RadiogroupData(String[] correspondingValues) {
        this.correspondingValues = correspondingValues;
    }

    public void setCorrespondingValues(String[] correspondingValues) {
        this.correspondingValues = correspondingValues;
    }

    public void Bind(CheckBox cbDoSearch, ArrayList<RadioButton> radioButtons) {
        values = new BooleanProperty[radioButtons.size()-1];

        doSearchProperty().bind(cbDoSearch.selectedProperty());
        for (int i = 0;i<radioButtons.size();i++)
        {
            values[i] = new SimpleBooleanProperty(false);

            radioButtons.get(i).disableProperty().bind(cbDoSearch.selectedProperty().not());
            valueProperty(i).bind(radioButtons.get(i).selectedProperty());
        }
    }

    public String getActualValue() {
        String result = "";
        for (int i = 0;i<values.length;i++){
            if (values[i].get()){
                result = correspondingValues[i];
            }
        }
        return result;
    }

    public BooleanProperty valueProperty(int i) {
        return values[i];
    }
    public boolean getValue(int i){
        return values[i].get();
    }
    public void setValue(int i,boolean value) {
        values[i].set(value);
    }
}
