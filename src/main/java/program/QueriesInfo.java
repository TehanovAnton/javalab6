package program;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class QueriesInfo {
    public static String DB_URL = "jdbc:sqlserver://localhost:1433";
    public static String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static String
            // Найти пользователя, длина писем которого наименьшая
            Task1 =
            "use java6JDBC;\n" +
                    "select people.fio, letter.content from people, letter\n" +
                    "where people.fio = letter.sender;",
    // Вывести информацию о пользователях, а также количестве полученных и отправленных ими письмах
    Task2_peopleInfo =
            "use java6JDBC;\n" +
                    "select * from people",
            Task2_senders =
                    "use java6JDBC;\n" +
                            "select * from letter order by sender;",
            Task2_recipients =
                    "use java6JDBC;\n" +
                            "select * from letter order by recipient;",
    // Вывести информацию о пользователях, которые получили хотя бы одно сообщение с заданной темой
    Task3 =
            "use java6JDBC;\n" +
                    "select fio, birthDate from people p, letter l\n" +
                    "where p.fio = l.recipient and l.topic = 'hobby';",
    // Вывести информацию о пользователях, которые не получали сообщения с заданной темой
    Task4 =
            "use java6JDBC;\n" +
                    "select fio, birthDate from people p, letter l\n" +
                    "where p.fio = l.recipient and l.topic != 'hobby';",
    // Направить письмо заданного человека с заданной темой всем адресатам
    Task5 =
            "use java6JDBC;\n" +
                    "insert into people(fio, birthDate)\n" +
                    "values('admin', '2002-06-06');\n" +
                    "\n" +
                    "insert into letter(sender, recipient, topic, content, sendingDate)\n" +
                    "values('admin', 'teh ant vict', 'wearther', 'a storm warnning', '2019-01-03'),\n" +
                    "\t('admin', 'laz dmi vlad', 'wearther', 'a storm warnning', '2019-01-03'),\n" +
                    "\t('admin', 'fes den mark', 'wearther', 'a storm warnning', '2019-01-03'),\n" +
                    "\t('admin', 'gor ars andr', 'wearther', 'a storm warnning', '2019-01-03')\n" +
                    "\tselect * from letter where sender = 'admin';";

    public static void PeopleInfo(ResultSet rs, Statement stmt) throws SQLException {
        rs = stmt.executeQuery(QueriesInfo.Task2_peopleInfo);
        ArrayList<People> poeples = new ArrayList<>();
        while (rs.next()) {
            People p = new People(rs.getString("fio"), rs.getString("birthDate"));
            poeples.add(p);
            System.out.println(p.fio + ", " + p.date);
        }
        System.out.println("");
    }
    public static void SenderInfo(ResultSet rs, Statement stmt) throws SQLException {
        rs = stmt.executeQuery(QueriesInfo.Task2_senders);
        ArrayList<Letter> letters = new ArrayList<>();
        int counter = 1;
        while (rs.next()) {
            Letter l = new Letter(rs.getString("sender"), rs.getString("recipient"),
                    rs.getString("topic"), rs.getString("content"),
                    rs.getString("sendingDate"));
            letters.add(l);
            if (letters.size() >= 2 && l.sender.equals(letters.get(letters.size() - 2).sender)) {
                counter++;
            }
            else if (letters.size() >= 2) {
                System.out.println(letters.get(letters.size() - 2).sender + " отправил " + counter + " писем");
                counter = 1;
            }
        }
        System.out.println(letters.get(letters.size() - 1).sender + " отправил " + counter + " писем\n");
    }
    public static void RecipientInfo(ResultSet rs, Statement stmt) throws SQLException {
        rs = stmt.executeQuery(QueriesInfo.Task2_recipients);
        ArrayList<Letter> letters = new ArrayList<>();
        int counter = 1;
        while (rs.next()) {
            Letter l = new Letter(rs.getString("sender"), rs.getString("recipient"),
                    rs.getString("topic"), rs.getString("content"),
                    rs.getString("sendingDate"));
            letters.add(l);
            if (letters.size() >= 2 && l.recipient.equals(letters.get(letters.size() - 2).recipient)) {
                counter++;
            }
            else if (letters.size() >= 2) {
                System.out.println(letters.get(letters.size() - 2).recipient + " получил " + counter + " писем");
                counter = 1;
            }
        }
        System.out.println(letters.get(letters.size() - 1).recipient + " получил " + counter + " писем");
    }
}
