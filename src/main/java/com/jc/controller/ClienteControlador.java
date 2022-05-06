package com.jc.controller;

import com.jc.dao.ClienteDAO;
import com.jc.dao.ClienteDAOimplements;
import com.jc.modelo.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet(name = "ClienteControlador", urlPatterns = {"/ClienteControlador"})
public class ClienteControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id;
            ClienteDAO dao = new ClienteDAOimplements();
            Cliente c = new Cliente();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("action", "nuevo");
                    request.setAttribute("cliente", c);
                    request.getRequestDispatcher("frmclientes.jsp").forward(request, response);
                    break;
                case "edit":
                    id=Integer.parseInt(request.getParameter("id"));
                    c=dao.getById(id);
                    request.setAttribute("action","modificar");
                    request.setAttribute("cliente",c);
                    request.getRequestDispatcher("frmclientes.jsp").forward(request, response);
                    break;
                case "delete":
                    id=Integer.parseInt(request.getParameter("id"));
                      dao.delete(id);
                    response.sendRedirect("ClienteControlador");
                    break;
                case "view":
                    //obtener la lista de registros de clientes
                    List<Cliente> lista = dao.select();
                    request.setAttribute("cli", lista);
                    request.getRequestDispatcher("clientes.jsp").forward(request, response);
                    break;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

      int id =Integer.parseInt(request.getParameter("id"));
      String nombre =request.getParameter("nombre");
      String correo=request.getParameter("correo");
      String celular=request.getParameter("celular");
      ClienteDAO  dao=new ClienteDAOimplements();
      Cliente cli=  new Cliente ();;
        cli.setId(id);
        cli.setNombre(nombre);
        cli.setCorreo(correo);
        cli.setCelular(celular);
       if(id==0){
          try {
              dao.insert(cli);
          } catch (Exception ex) {
              Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
          }
       }
       else {
          try {
              //modificar registro
              dao.update(cli);
          } catch (Exception ex) {
              Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
          }
       }
        
       response.sendRedirect("ClienteControlador"); 
        
    }

}
