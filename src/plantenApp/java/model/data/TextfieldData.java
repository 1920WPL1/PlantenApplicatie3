package plantenApp.java.model.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TextfieldData extends SearchBase {
    private StringProperty value = new SimpleStringProperty("");

    public void Bind(TextField textField) {
        try {
            valueProperty().bind(textField.textProperty());
        } catch (NullPointerException nex) {
            StringBuilder sb = new StringBuilder();
            sb.append(" textfield: ").append(textField).append('\n').append(nex.getMessage());
            System.out.println(sb.toString());
        }
    }

    @Override
    public boolean isDoSearch() {
        if (getValue().isEmpty()) {
            setDoSearch(false);
        } else {
            setDoSearch(true);
        }
        return super.isDoSearch();
    }

    //StringValue
    public StringProperty valueProperty() {
        return value;
    }

    public String getValue() {
        return value.get();
    }

    public void setValue(String value) {
        this.value.set(value);
    }
}
