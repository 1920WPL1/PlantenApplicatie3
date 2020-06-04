package plantenApp.java.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUtils {
    public static String sqlFormatedList(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Integer i : list) {
            sb.append(i).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    public static String SetParameterCount(String querry, Integer count) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                list.append("?");
            } else {
                list.append("?,");
            }
        }
        return querry.replaceFirst("(?)", "(" + list + ")");
    }

    public static PreparedStatement ReadyStatement(Connection dbConnection, String query, ArrayList<Integer> plantIds) throws SQLException {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < plantIds.size(); i++) {
            if (i == plantIds.size() - 1) {
                list.append("?");
            } else {
                list.append("?,");
            }
        }
        query = query.replaceFirst("(?)", "(" + list + ")");
        PreparedStatement stmt = dbConnection.prepareStatement(query);
        for (int i = 0; i < plantIds.size(); i++) {
            stmt.setInt(i + 1, plantIds.get(i));
        }
        return stmt;
    }
}
