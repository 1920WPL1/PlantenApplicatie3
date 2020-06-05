package plantenApp.java.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.function.Supplier;

/**
 * @author bradley
 * @param <T> value-type
 */
public class SearchRequest<T> {
    BooleanProperty DoSearch = new SimpleBooleanProperty();


   T value ;

    public SearchRequest(T value) {
        this.value = value;
    }

    public BooleanProperty DoSearchProperty() {
        return DoSearch;
    }

    public boolean getDoSearch() {
        return DoSearch.get();
    }

    public void setDoSearch(boolean booleanProperty) {
        this.DoSearch.set(booleanProperty);
    }

    public T Value() {
        return value;
    }


}
