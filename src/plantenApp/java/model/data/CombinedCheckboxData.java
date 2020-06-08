package plantenApp.java.model.data;

import java.util.ArrayList;
import java.util.Map;

public class CombinedCheckboxData extends MultiCheckboxData {
    private String[] correspondingValues;

    public void setCorrespondingValues(String[] correspondingValues) {
        this.correspondingValues = correspondingValues;
    }

    public String getActualValue() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (getValue(i)){
                result.append(correspondingValues[i]);
            }
        }
        return result.toString();
    }
}
