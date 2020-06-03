package plantenApp.java.utils;

import java.util.List;

public class DaoUtils {
    public static String sqlFormatedList(List<Integer> list){
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Integer i : list){
            sb.append(i+",");
        }
        sb.deleteCharAt(sb.length() -1);
        sb.append(")");
        return sb.toString();
    }
}
