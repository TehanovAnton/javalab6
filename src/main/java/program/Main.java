package program;

import java.sql.*;
import java.util.ArrayList;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class Main {

    public static void Task1(ResultSet rs, Statement stmt){
        try {
            rs = stmt.executeQuery(QueriesInfo.Task1);

            String resPeople = "", resContent = "";
            for (String preContent = "", curContent = ""; rs.next(); ) {
                if (preContent.equals("")) {
                    preContent = rs.getString("content");
                    resPeople = rs.getString("fio");
                    continue;
                }

                curContent = rs.getString("content");
                resPeople = curContent.length() <= preContent.length() ? rs.getString("fio") : resPeople;
                resContent = curContent.length() <= preContent.length() ? curContent : preContent;
            }

            System.out.println("пользователь с наименшей длинной письм: " + resPeople + " (" + resContent + ")\n");
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void Task2() throws ClassNotFoundException, SQLException {

        Connection conFrom = null;
        Connection conTo = null;
        Statement stmtFrom = null;
        Statement stmtTo = null;
        ResultSet rsFrom = null;
        ResultSet rsTo = null;

        try {
            Class.forName(QueriesInfo.JDBC_DRIVER);
            conFrom = DriverManager.getConnection(QueriesInfo.DB_URL, "Anton", "ewqqwe");
            conFrom.setAutoCommit(false);
            conTo = DriverManager.getConnection(QueriesInfo.DB_URL, "Anton", "ewqqwe");
            conTo.setAutoCommit(false);
            stmtFrom = conFrom.createStatement();
            stmtTo = conTo.createStatement();


            //транзакция
            rsFrom = stmtFrom.executeQuery(QueriesInfo.Task2_peopleInfo);
            ArrayList<People> poeples = new ArrayList<People>();
            while (rsFrom.next()) {
                People p = new People(rsFrom.getString("fio"), rsFrom.getString("birthDate"));
                poeples.add(p);
                System.out.println(p.fio + ", " + p.date);
            }
            System.out.println("");

            stmtTo.executeUpdate("use java6JDBC; update letter set sendingDate = '2019-01-03' ");

            conFrom.commit();
            conTo.commit();
        }
        catch (SQLException throwables)
        {
            conFrom.rollback();
            conTo.rollback();
        }

        stmtFrom.close();
        stmtTo.close();
    }
    public static void Task3(ResultSet rs, Statement stmt) throws SQLException {
        rs = stmt.executeQuery(QueriesInfo.Task3);

        while (rs.next()){
            System.out.println(rs.getString("fio") + " " + rs.getString("birthDate"));
        }
    }
    public static void Task4(ResultSet rs, Statement stmt) throws SQLException {
        rs = stmt.executeQuery(QueriesInfo.Task4);

        while (rs.next()){
            System.out.println(rs.getString("fio") + " " + rs.getString("birthDate"));
        }
    }
    public static void Task5(ResultSet rs, Statement stmt) throws SQLException{
        rs = stmt.executeQuery(QueriesInfo.Task5);
        ArrayList<Letter> letters = new ArrayList<>();
        while (rs.next()) {
            Letter l = new Letter(rs.getString("sender"), rs.getString("recipient"),
                    rs.getString("topic"), rs.getString("content"),
                    rs.getString("sendingDate"));
            letters.add(l);

            System.out.println(l.sender + " " + l.recipient + " " + l.topic + " " +
                    l.content + " " + l.sendingDate);
        }
    }

    public static void main(String[] args) {
        try {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            Class.forName(QueriesInfo.JDBC_DRIVER);
            conn = DriverManager.getConnection(QueriesInfo.DB_URL, "Anton", "ewqqwe");
            stmt = conn.createStatement();

            Task1(rs, stmt);
//            Task2();
//            Task3(rs, stmt);
//            Task4(rs, stmt);
//            Task5(rs, stmt);

            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
