package by.sep.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EbankingDataSource {
    private static EbankingDataSource ebankingDataSource;

    private EbankingDataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private Connection getOBConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/online_banking",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (ebankingDataSource == null) {
            ebankingDataSource = new EbankingDataSource();
        }
        return ebankingDataSource.getOBConnection();
    }
}
