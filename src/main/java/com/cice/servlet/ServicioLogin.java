package com.cice.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ServicioLogin extends HttpServlet{

    public ServicioLogin() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("llamando a doPost");
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        System.out.println("usuario: " + user + " | password: " + pass);

        PrintWriter writer = resp.getWriter();

        //String sql = "INSERT INTO prueba VALUES (null, '"+user+"', '"+pass+"')";
        //Manager manager = new Manager();
        //manager.insertarActualizarEliminar(sql);

        String sql = "SELECT * FROM clientes WHERE user = '"+user+"' AND pass = '"+pass+"'";
        // Ejemplo de inyeccion SQL , hackinEtico a nuestra propia pagina.
        // SELECT * FROM prueba WHERE user = 'user' AND pass = ''or '1'='1'

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/prueba", "root", "root");
            Statement statement = connection.createStatement();
            //statement.executeUpdate(sql);
            ResultSet rs = statement.executeQuery(sql);
            if (rs.first()) {
                //tenemos una coincidencia
                writer.print("WELLCOME USUARIO: " + user);
            } else {
                //NO HAY COINCIDENCIAS
                writer.print("403 - DENIED ACCESS");
            }
            rs.close();
            writer.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
