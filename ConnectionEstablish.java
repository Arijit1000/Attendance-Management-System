package com.student.attedence;
import java.sql.*;
public class ConnectionEstablish {
    static Connection con;
    public static Connection createRelation() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students_attendances","root","adminarijit");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
