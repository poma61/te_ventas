
package com.jc.controller;

import com.jc.dao.ProductoDAO;
import com.jc.dao.ProductoDAOimplements;
import com.jc.modelo.Producto;
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


@WebServlet(name = "ProductoControlador", urlPatterns = {"/ProductoControlador"})
public class ProductoControlador extends HttpServlet {
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
            int id;
            ProductoDAO dao = new ProductoDAOimplements();
            Producto p = new Producto();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("action", "nuevo");
                    request.setAttribute("pro", p);
                    request.getRequestDispatcher("frmproductos.jsp").forward(request, response);
                    break;
                case "edit":
                    id=Integer.parseInt(request.getParameter("id"));
                    p=dao.getById(id);
                    request.setAttribute("action","modificar");
                    request.setAttribute("pro",p);
                    request.getRequestDispatcher("frmproductos.jsp").forward(request, response);
                    break;
                case "delete":
                    id=Integer.parseInt(request.getParameter("id"));
                      dao.delete(id);
                    response.sendRedirect("ProductoControlador");
                    break;
                case "view":
                    //obtener la lista de registros de clientes
                    List<Producto> lista = dao.select();
                    request.setAttribute("produc", lista);
                    request.getRequestDispatcher("productos.jsp").forward(request, response);
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
      String descripcion=request.getParameter("descripcion");
      float precio=Float.parseFloat(request.getParameter("precio"));
      ProductoDAO  dao=new ProductoDAOimplements();
      Producto  p=  new Producto ();;
        p.setId(id);
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
       if(id==0){
          try {
              dao.insert(p);
          } catch (Exception ex) {
              Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
          }
       }
       else {
          try {
              //modificar registro
              dao.update(p);
          } catch (Exception ex) {
              Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
          }
       }
        
       response.sendRedirect("ProductoControlador"); 
        
    }
    }

    
  

