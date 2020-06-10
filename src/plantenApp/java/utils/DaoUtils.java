package plantenApp.java.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoUtils {
    public static <T> PreparedStatement ReadyStatement(Connection dbConnection, String query, ArrayList<T> inList) throws SQLException {
        try {
            StringBuilder list = new StringBuilder();

            for (int i = 0; i < inList.size(); i++) {
                if (i == inList.size() - 1) {
                    list.append("?");
                } else {
                    list.append("?,");
                }
            }
            query = query.replace("(?)", "(" + list + ")");
            System.out.println(query);
            System.out.println("inlist: " + inList.toString());

            PreparedStatement stmt = dbConnection.prepareStatement(query);
            for (int i = 0; i < inList.size(); i++) {
                if (inList.get(i).getClass() == Integer.class) {
                    stmt.setInt(i + 1, (int) inList.get(i));
                } else if (inList.get(i).getClass() == String.class) {
                    stmt.setString(i + 1, (String) inList.get(i));
                } else {
                    throw new IllegalArgumentException();
                }
            }
            return stmt;
        } catch (IllegalArgumentException iae) {
            System.out.println("!!!No such type allowed");
            return null;
        }

    }
}
