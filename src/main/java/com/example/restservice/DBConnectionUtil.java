// package com.example.restservice;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// public class DBConnectionUtil {
// public static Connection getConnection() {
// try {
// // String driver = "org.mariadb.jdbc.Driver";
// // Class.forName(driver);
// String url = "jdbc:mariadb://localhost:3306/test";
// Connection connection = DriverManager.getConnection(url);
// System.out.println("get connection=" + connection + ", class" +
// connection.getClass());
// return connection;
// } catch (SQLException e) {
// throw new IllegalStateException(e);
// }
// }
// }
