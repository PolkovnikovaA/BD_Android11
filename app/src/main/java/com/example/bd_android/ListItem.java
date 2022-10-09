package com.example.bd_android;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListItem {
    Connection connection;
    String ConnectionResult = "";
    Boolean isSuucess = false;

    public List<Map<String, String>> getlist() {
        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "Select * From Sotrudnik";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    Map<String, String> dtname = new HashMap<String, String>();
                    dtname.put("ID", resultSet.getString("id"));
                    dtname.put("Familiya", resultSet.getString("Familiya"));
                    dtname.put("Dolzhnost", resultSet.getString("Dolzhnost"));
                    data.add(dtname);
                }
                ConnectionResult = "Success";
                isSuucess = true;
                connection.close();
            } else {
                ConnectionResult = "Failed";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}