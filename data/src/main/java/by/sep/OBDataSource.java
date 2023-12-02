package by.sep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OBDataSource {
    private static OBDataSource OBDataSource;

    private OBDataSource() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private Connection getListExpensesTestConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/online_banking",
                "user",
                "user");
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (OBDataSource == null) {
            OBDataSource = new OBDataSource();
        }
        return OBDataSource.getListExpensesTestConnection();
    }
}
