package plantenApp.java.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Value {
    StringProperty value = new SimpleStringProperty("test");

    public StringProperty valueProperty(){
        return value;
    }

    public String get(){
        return value.get();
    }

    public void set(String text){
        value.set(text);
    }
}
