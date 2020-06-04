package plantenApp.java.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Bradley
 */
public class ValueWithBoolean extends Value {
    BooleanProperty booleanProperty = new SimpleBooleanProperty();

    public BooleanProperty boolProperty(){
        return booleanProperty;
    }

    public boolean getBool(){
        return booleanProperty.get();
    }

    public void setBool(Boolean bool){
        booleanProperty.set(bool);
    }


}
