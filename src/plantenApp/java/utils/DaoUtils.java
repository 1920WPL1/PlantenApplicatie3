package plantenApp.java.utils;

import plantenApp.java.model.RequestValueWBool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUtils {
    public static PreparedStatement ReadyStatement(Connection dbConnection, String query, ArrayList<Integer> plantIds) throws SQLException {
        StringBuilder list = new StringBuilder();
        System.out.println(plantIds.size());
       for (int i = 0; i < plantIds.size(); i++) {
            if (i == plantIds.size() - 1) {
                list.append("?");
            } else {
                list.append("?,");
            }
        }


        query = query.replace("(?)", "(" + list + ")");
        System.out.println(query);
        PreparedStatement stmt = dbConnection.prepareStatement(query);
        for (int i = 0; i < plantIds.size(); i++) {
            stmt.setInt(i+1 , plantIds.get(i));
        }
        return stmt;
    }
    public static String GetCheckedValue(RequestValueWBool[] array){
        for (int i = 0; i < array.length; i++) {
            if (array[i].getIsSelected()){
                return array[i].getValue();
            }
        }
        System.out.println("Radiobuttons zonder default value");
        return null;
    }
}
