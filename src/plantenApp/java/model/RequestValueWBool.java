package plantenApp.java.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author Bradley
 */
@Deprecated
public class RequestValueWBool extends RequestValue {
    private BooleanProperty isSelected = new SimpleBooleanProperty(false);

    public BooleanProperty isSelectedProperty(){
        return isSelected;
    }

    public boolean getIsSelected(){
        return isSelected.get();
    }

    public void setIsSelected(Boolean bool){
        isSelected.set(bool);
    }


}
