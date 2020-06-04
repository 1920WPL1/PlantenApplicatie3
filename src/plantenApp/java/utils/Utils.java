package plantenApp.java.utils;

import plantenApp.java.model.PropertyClass;
import plantenApp.java.model.ValueWithBoolean;

public class Utils {
    public static String GetCheckedValue(ValueWithBoolean[] array){
        for (int i = 0; i < array.length; i++) {
            if (array[i].getBool()){
                return array[i].get();
            }
        }
        return null;
    }
}
