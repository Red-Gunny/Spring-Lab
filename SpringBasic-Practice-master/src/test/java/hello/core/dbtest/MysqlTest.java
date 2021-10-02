package hello.core.dbtest;

import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.*;

public class MysqlTest {

    @Test
    void forTest() {

    }

}


/*

public static void main(String args[]) throws SQLException {
        Connection connection = null;

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/test_db";
        String user = "root";
        String pw = "rjsdml94";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver Load Error!");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url);
            System.out.println("정상적으로 연결되었습니다.");
            Statement stmt = connection.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM memberlist WHERE name = 'hong'");

            String name1 = rset.getString("name");
            String phone1 = rset.getString("phone");
            String age1 = rset.getString("age");

            System.out.println("name1 = " + name1);
            System.out.println("phone1 = " + phone1);
            System.out.println("age1 = " + age1);

            rset.close();
            stmt.close();
            connection.close();
        } catch(SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }
 */