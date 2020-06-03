package plantenApp.java.model;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public class ValueWithBoolean extends Value {
    BooleanProperty booleanProperty;
    public BooleanProperty boolProperty(){
        return booleanProperty;
    }
    public boolean getBool(){
        return booleanProperty.get();
    }
    public void set(Boolean bool){
        booleanProperty.set(bool);
    }
}
