package plantenApp.java.model.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;

import java.util.ArrayList;

public class MultiSpinnerData extends SearchBase{
    private IntegerProperty[] values;

    public void Bind(CheckBox cbDoSearch, ArrayList<Spinner<Integer>> spinners) {
        values = new IntegerProperty[spinners.size()-1];

        doSearchProperty().bind(cbDoSearch.selectedProperty());
        for (int i = 0;i<spinners.size();i++)
        {
            values[i] = new SimpleIntegerProperty(0);

            spinners.get(i).disableProperty().bind(cbDoSearch.selectedProperty().not());
            valueProperty(i).bind(spinners.get(i).valueProperty());
        }
    }

    public IntegerProperty valueProperty(int i) {
        return values[i];
    }

    public Integer getValue(int i) {
        return values[i].get();
    }

    public void setValue(int i,Integer value) {
        values[i].set(value);
    }
}
