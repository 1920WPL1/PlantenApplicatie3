package plantenApp.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class QueryBuilder {
    private Boolean firstWhere = true;
    private Boolean firstOr = false;

    private String query = "";

    private int nextParam = 1;

    private ArrayList<Integer> ids = new ArrayList<>();

    private HashMap<Integer, String> stringValues = new HashMap<>();
    private HashMap<Integer, Integer> intValues = new HashMap<>();

    public QueryBuilder(String what, String from) {
        query = "SELECT " + what + " FROM " + from + " ";
    }

    public void AddIN(String eigenschap, ArrayList<Integer> ids) {
        StringBuilder list = new StringBuilder();
        this.ids = ids;
        nextParam += ids.size();

        for (int i = 0; i < ids.size(); i++) {
            if (i == ids.size() - 1) {
                list.append("?");
            } else {
                list.append("?,");
            }
        }

        if (firstWhere) {
            query += " WHERE ";
            firstWhere = false;
        } else {
            query += " AND ";
        }
        query += eigenschap + " IN (" + list + ") ";
    }

    public void AddBasicString(String eigenschap, String value) {
        if (firstWhere) {
            query += " WHERE ";
            firstWhere = false;
        } else {
            query += " AND ";
        }
        query += eigenschap + " = ? ";

        //Voegt de value toe aan een map voor later via een veilige manier in de statement te steken
        stringValues.put(nextParam, value);
        nextParam++;
    }

    public void AddBasicInt(String eigenschap, int value) {
        if (firstWhere) {
            query += " WHERE ";
            firstWhere = false;
        } else {
            query += " AND ";
        }
        query += eigenschap + " = ? ";

        //Voegt de value toe aan een map voor later via een veilige manier in de statement te steken
        intValues.put(nextParam, value);
        nextParam++;
    }

    public void AddLIKEString(String eigenschap, String value) {
        if (firstWhere) {
            query += " WHERE ";
            firstWhere = false;
        } else {
            query += " AND ";
        }
        query += eigenschap + " LIKE ? ";

        //Voegt de value toe aan een map voor later via een veilige manier in de statement te steken
        stringValues.put(nextParam, value);
        nextParam++;
    }

    public void StartOr() {
        if (firstWhere) {
            query += " WHERE ";
            firstWhere = false;
        } else {
            query += " AND ";
        }

        query += " (";
        firstOr = true;
    }

    public void EndOr() {
        query += ") ";
        firstOr = false;
    }

    public void AddORString(String eigenschap, String value) {
        if (!firstOr) {
            query += " OR ";
        } else {
            firstOr = false;
        }
        query += eigenschap + " = ? ";

        //Voegt de value toe aan een map voor later via een veilige manier in de statement te steken
        stringValues.put(nextParam, value);
        nextParam++;
    }

    public void AddIsBiggerThan(String eigenschap, int value) {
        if (firstWhere) {
            query += " WHERE ";
            firstWhere = false;
        } else {
            query += " AND ";
        }
        query += eigenschap + " >= ? ";

        //Voegt de value toe aan een map voor later via een veilige manier in de statement te steken
        intValues.put(nextParam, value);
        nextParam++;
    }

    public void AddIsSmallerThan(String eigenschap, int value) {
        if (firstWhere) {
            query += " WHERE ";
            firstWhere = false;
        } else {
            query += " AND ";
        }
        query += eigenschap + " <= ? ";

        //Voegt de value toe aan een map voor later via een veilige manier in de statement te steken
        intValues.put(nextParam, value);
        nextParam++;
    }

    public PreparedStatement PrepareStatement(Connection connection) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);

        for (int i = 1; i < nextParam; i++) {
            //Indien de parameter thuishoort in de IN, pak een waarde uit de id list
            if (i - 1 < ids.size()) {
                stmt.setInt(i, ids.get(i - 1));
                //anders check uit de hashmap
            } else {
                if (stringValues.containsKey(i)) {
                    stmt.setString(i, stringValues.get(i));
                } else if (intValues.containsKey(i)) {
                    stmt.setInt(i, intValues.get(i));
                }
            }
        }

        return stmt;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
