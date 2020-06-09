package plantenApp.java.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


@Deprecated
public class RequestValue {
    private StringProperty value = new SimpleStringProperty("");

    public StringProperty valueProperty(){
        return value;
    }

    public String getValue(){
        return value.get();
    }

    public void setValue(String text){
        value.set(text);
    }
}
