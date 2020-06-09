package plantenApp.java.model.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.util.ArrayList;

public class MultiSpinnerData extends SearchBase {
    private IntegerProperty[] values;
    private int minSpinnerValue = 0;
    private int maxSpinnerValue = 100;

    public void Bind(CheckBox cbDoSearch, Spinner<Integer>[] spinners) {
        try {
            doSearchProperty().bind(cbDoSearch.selectedProperty());

            values = new IntegerProperty[spinners.length];
            for (int i = 0; i < spinners.length; i++) {
                values[i] = new SimpleIntegerProperty(0);

                spinners[i].setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(minSpinnerValue, maxSpinnerValue));
                spinners[i].disableProperty().bind(cbDoSearch.selectedProperty().not());
                valueProperty(i).bind(spinners[i].valueProperty());
            }
        } catch (NullPointerException nex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Checkbox: ").append(cbDoSearch).append(" spinners: ");
            for (int i = 0; i < spinners.length; i++) {
                sb.append(i).append(": ").append(spinners[i]);
            }
            sb.append('\n').append(nex.getMessage());
            System.out.println(sb.toString());
        }
    }

    //IntegerValue
    public IntegerProperty valueProperty(int i) {
        return values[i];
    }

    public int getValue(int i) {
        return values[i].get();
    }

    public void setValue(int i, Integer value) {
        values[i].set(value);
    }

    //Spinnervalues
    public int getMinSpinnerValue() {
        return minSpinnerValue;
    }

    public int getMaxSpinnerValue() {
        return maxSpinnerValue;
    }

    public void setSpinnerMinMaxValue(int minSpinnerValue, int maxSpinnerValue) {
        this.minSpinnerValue = minSpinnerValue;
        this.maxSpinnerValue = maxSpinnerValue;
    }
}
