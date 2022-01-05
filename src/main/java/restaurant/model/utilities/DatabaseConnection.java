package restaurant.model.utilities;

import java.sql.*;


//public class DatabaseConnection implements IDatabaseConnection{
//    private Connection con = null;
//
//    @Override
//    public void connect(String hostName,int port, String databaseName, String userName, String passWord) {
//        try {
//            Class.forName(System.getenv("JDBC_DRIVER"));
//            con = DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + port +"/" + databaseName,
//                    userName, passWord);
//        } catch (SQLException | ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    @Override
//    public ResultSet retrieve(String query) {
//        ResultSet res = null;
//        try {
//            res = con.createStatement().executeQuery(query);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return res;
//    }
//
//}
