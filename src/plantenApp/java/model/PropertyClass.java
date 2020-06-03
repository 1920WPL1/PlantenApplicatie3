package plantenApp.java.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author bradley
 * @param <T> value-type
 */
public class PropertyClass<T> {
    BooleanProperty DoSearch = new SimpleBooleanProperty();
   T value;


    public BooleanProperty DoSearchProperty() {
        return DoSearch;
    }

    public boolean getDoSearch() {
        return DoSearch.get();
    }

    public void setDoSearch(boolean booleanProperty) {
        this.DoSearch.set(booleanProperty);
    }

    public T getValue() {
        return value;
    }


}
