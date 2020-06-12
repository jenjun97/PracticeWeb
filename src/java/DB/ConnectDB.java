package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Response;
import register.registerServlet;

public class ConnectDB {

//    String url = "jdbc:mysql://localhost:3306/jundb?serverTimezone=GMT%2B8&userSSL=false";
    String url = "jdbc:mysql://localhost:3306/jundb?serverTimezone=GMT%2B8";
    String user = "admin";
    String password = "admin";

    /**
     *
     * @param FieldName
     * @param whereSql
     * @return
     */
    public String getOneData(String FieldName, String whereSql) {
        FieldName = "`" + FieldName + "`";
        String reStr = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();
            String sql = "select " + FieldName + " from user where " + whereSql + ";";
            System.out.println("searchStr>>" + sql);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                reStr = rs.getString(1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reStr;
    }

    public void updataDB(String KeyValue, String whereSql) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();
            String sql = "update user set " + KeyValue + " where " + whereSql + ";";
            System.out.println("updateDB>>" + sql);
            statement.executeUpdate(sql);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertDB(HashMap DBmap) {
        String allKey = "`";
        String allValue = "\"";

        Iterator iterator = DBmap.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            allKey += key + "`,`";
            allValue += DBmap.get(key) + "\",\"";
        }
        allKey = allKey.substring(0, allKey.length() - 2);
        allValue = allValue.substring(0, allValue.length() - 2);
        System.out.println("allKay=" + allKey);
        System.out.println("allValue=" + allValue);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connectDB = DriverManager.getConnection(url, user, password);
            Statement statement = connectDB.createStatement();
            String sql = "insert into user (" + allKey + ") values(" + allValue + ")";
            System.out.println("sql==" + sql);
            System.out.println(statement.executeUpdate(sql));

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(registerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(registerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public HashMap getUserData(String userEmail) {
        HashMap<String, String> DataMap = new HashMap();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();
            String sql = "select * from user where user_email = \"" + userEmail + "\";";
            System.out.println("searchStr>>" + sql);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                DataMap.put("userEmail", rs.getString("user_email"));
                DataMap.put("userPassword", rs.getString("user_password"));
                DataMap.put("userName", rs.getString("user_name"));
                DataMap.put("userBirth", rs.getString("user_birth"));
                DataMap.put("userSex", rs.getString("user_sex"));
                DataMap.put("userAddress", rs.getString("user_address"));
                DataMap.put("userPhone", rs.getString("user_phone"));
                DataMap.put("userLevel", rs.getString("user_level"));

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DataMap;
    }

    public ResultSet getAllUserData(String LikeSql) {
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();

            String sql = "select * from user where " + LikeSql + ";";
            System.out.println("sql>>" + sql);
            rs = statement.executeQuery(sql);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public Boolean deleteUser(String deleteUserEmail) {
        Boolean reBoolean = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();
            String sql = "update user set user_delete = \"Y\" where user_email = \"" + deleteUserEmail + "\";";
            int ans = statement.executeUpdate(sql);
            System.out.println("delete" + ans);
            reBoolean = true;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return reBoolean;
        }
    }
}
