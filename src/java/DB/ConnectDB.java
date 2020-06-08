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
import register.registerServlet;

public class ConnectDB {

    String url = "jdbc:mysql://localhost:3306/jundb?serverTimezone=GMT%2B8&userSSL=false";
    String user = "admin";
    String password = "admin";

    /**
     * 
     * @param FieldName
     * @param whereURL
     * @return 
     */
    public String getOneData(String FieldName, String whereURL) {
        FieldName = "`" + FieldName + "`";
        String reStr = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url, user, password);
            Statement statement = connect.createStatement();
            String searchStr = "select " + FieldName + " from user where " + whereURL + ";";
            System.out.println("searchStr>>" + searchStr);
            ResultSet rs = statement.executeQuery(searchStr);

            while (rs.next()) {

                reStr = rs.getString(1);
//                if (rs.getString("user_password").equals(inputPassword)) {
//                    reStr = "({email:true,password:true})";
//                } else {
//                    reStr = "({email:true,password:false})";
//                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reStr;
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

}
