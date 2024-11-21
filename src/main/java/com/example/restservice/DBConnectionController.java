package com.example.restservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = RequestMethod.GET)
public class DBConnectionController {

    @GetMapping("/db")
    public DBTestDTO dbTestDTO() {
        Connection conn = DBConnectionUtil.getConnection();
        return new DBTestDTO(conn.toString(), conn.getClass().toString());
        // return new DBTestDTO("Hello", "test");
    }

    @GetMapping("/dbinsert")
    public DBTestDTO dbinsertTestDTO(
            @RequestParam(value = "username", defaultValue = "testname") String username,
            @RequestParam(value = "email", defaultValue = "") String email) {

        String sql = "insert into users(username, email) values(?,?)";

        PreparedStatement ppst = null;
        Connection conn = null;
        try {
            conn = DBConnectionUtil.getConnection();
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, username);
            ppst.setString(2, email);
            ppst.executeUpdate();

            // return
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, ppst, null);
        }

        return new DBTestDTO(conn.toString(), conn.getClass().toString());
        // return new DBTestDTO("Hello", "test");
    }

    @GetMapping("/dbfindby")
    public DBUserDTO dbfindbyTestDTO(@RequestParam(value = "username", defaultValue = "testname") String username) {

        String sql = "select * from users where username = ?";

        PreparedStatement ppst = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionUtil.getConnection();
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, username);
            rs = ppst.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("user_id");
                String un = rs.getString("username");
                String em = rs.getString("email");
                DBUserDTO user = new DBUserDTO(id, un, em);
                return user;
            } else {
                throw new NoSuchElementException("member not found name = " + username);
            }

            // return
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, ppst, null);
        }

        // return new DBUserDTO(conn.toString(), conn.getClass().toString());
        // return new DBTestDTO("Hello", "test");
    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new IllegalStateException(e);

            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new IllegalStateException(e);

            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new IllegalStateException(e);

            }
        }
    }
}
