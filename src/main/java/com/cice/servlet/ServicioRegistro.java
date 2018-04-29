package com.cice.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ServicioRegistro extends ServicioLogin {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Conectando con el servidor");
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        System.out.println("usuario: " + user + " | password: " + pass);
        PrintWriter printWriter = resp.getWriter();

        String sql = "INSERT INTO clientes VALUES (null, '"+user+"', '"+pass+"')";
        Connection connection;
        Statement statement;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/prueba","root","root");
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            printWriter.print("Esta usted registrado con exito");
            printWriter.close();
            connection.close();
            statement.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
