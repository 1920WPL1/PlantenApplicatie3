package plantenApp.java.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoUtils {
    public static PreparedStatement ReadyStatement(Connection dbConnection, String query, ArrayList<Integer> plantIds) throws SQLException {
        StringBuilder list = new StringBuilder();

       for (int i = 0; i < plantIds.size(); i++) {
            if (i == plantIds.size() - 1) {
                list.append("?");
            } else {
                list.append("?,");
            }
        }


        query = query.replace("(?)", "(" + list + ")");

        PreparedStatement stmt = dbConnection.prepareStatement(query);
        for (int i = 0; i < plantIds.size(); i++) {
            stmt.setInt(i+1 , plantIds.get(i));
        }
        return stmt;
    }
}
