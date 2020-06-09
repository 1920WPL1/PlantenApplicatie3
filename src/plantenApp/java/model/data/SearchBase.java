package plantenApp.java.model.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SearchBase {
    private BooleanProperty doSearch = new SimpleBooleanProperty(false);

    public BooleanProperty doSearchProperty() {
        return doSearch;
    }
    public boolean isDoSearch() {
        System.out.println(doSearch.get());
        return doSearch.get();
    }
    public void setDoSearch(boolean doSearch) {
        this.doSearch.set(doSearch);
    }
}
