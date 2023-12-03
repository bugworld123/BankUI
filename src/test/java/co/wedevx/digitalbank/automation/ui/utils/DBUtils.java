package co.wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    private static Connection connection=null;
    private static Statement statement=null;
    private static ResultSet resultSet=null;


    //method to establish connection with the db
    public static void establishConnection(){



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection(ConfigReader.
                    getPropertyValue("digitalbank.db.url"),ConfigReader.
                    getPropertyValue("digitalbank.db.username"),ConfigReader.
                    getPropertyValue("digitalbank.db.password"));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
    }
    //a method that can dynamically send select statements
    // and return a list of Map of all columns
    public static List<Map<String,Object>> runSqlSelectQuery(String sqlQuery){
        List<Map<String,Object>> dbResultList =new ArrayList<>();

        try {
            statement = connection.createStatement();
            resultSet=statement.executeQuery(sqlQuery);


            // getMetaData(); method return info about your info
            ResultSetMetaData resultSetMetaData= resultSet.getMetaData();
            int columnCount=resultSetMetaData.getColumnCount();

            while(resultSet.next()){
                Map<String,Object> rowMap=new HashMap<>();

                for(int column=1;column<=columnCount;column++){
                    rowMap.put(resultSetMetaData.getColumnName(column),resultSet.getObject(column));
                }
                dbResultList.add(rowMap);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  dbResultList;
    }


    //create a method that inserts and update info db
    // return the nums of rows updated or 0 when action is not taken
    // update query
    public static int runSqlUpdateQuery(String sqlQuery){
        int rowsUpdated=0;
        try{
            statement=connection.createStatement();
            rowsUpdated=statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rowsUpdated;
    }

    //delete or truncate the table


    // close connection
    public static void closeConnection(){
        try{
            if(resultSet!=null){
                resultSet.close();
            }if(statement!=null){
                statement.close();
            }if (connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
