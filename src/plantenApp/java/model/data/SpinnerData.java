package plantenApp.java.model.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class SpinnerData extends SearchBase {
    private IntegerProperty value = new SimpleIntegerProperty(0);
    private int minSpinnerValue=0;
    private int maxSpinnerValue=100;
    private int initialValue = 0;
    private int step = 5;

    public void Bind(CheckBox cbDoSearch, Spinner<Integer> spinner) {
        try {
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(minSpinnerValue, maxSpinnerValue));

            doSearchProperty().bind(cbDoSearch.selectedProperty());
            spinner.disableProperty().bind(cbDoSearch.selectedProperty().not());
            valueProperty().bind(spinner.valueProperty());
        } catch (NullPointerException nex) {
            StringBuilder sb = new StringBuilder();
            sb.append("Checkbox: ").append(cbDoSearch).append(" spinner: ").append(spinner).append('\n').append(nex.getMessage());
            System.out.println(sb.toString());
        }
    }

    //IntegerValue
    public IntegerProperty valueProperty() {
        return value;
    }

    public int getValue() {
        return value.get();
    }

    public void setValue(int value) {
        this.value.set(value);
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
