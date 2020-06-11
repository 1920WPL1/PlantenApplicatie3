package plantenApp.java.utils;

import plantenApp.java.model.data.SliderLabelData;
import plantenApp.java.model.data.enums.ESliderLabel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoUtils {
    public static PreparedStatement ReadyStatement(Connection dbConnection, String query, ArrayList<Integer> plantids) throws SQLException {
        StringBuilder list = new StringBuilder();

        for (int i = 0; i < plantids.size(); i++) {
            if (i == plantids.size() - 1) {
                list.append("?");
            } else {
                list.append("?,");
            }
        }
        query = query.replace("(?)", "(" + list + ")");

        PreparedStatement stmt = dbConnection.prepareStatement(query);
        for (int i = 0; i < plantids.size(); i++) {
            stmt.setInt(i + 1, plantids.get(i));
        }
        return stmt;
    }
}
