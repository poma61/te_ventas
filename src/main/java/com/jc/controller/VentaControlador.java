package com.jc.controller;

import com.jc.dao.ClienteDAO;
import com.jc.dao.ClienteDAOimplements;
import com.jc.dao.ProductoDAO;
import com.jc.dao.ProductoDAOimplements;
import com.jc.dao.VentaDAO;
import com.jc.dao.VentaDAOimplements;
import com.jc.modelo.Cliente;
import com.jc.modelo.Producto;
import com.jc.modelo.Venta;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet(name = "VentaControlador", urlPatterns = {"/VentaControlador"})
public class VentaControlador extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id;
            VentaDAO dao = new VentaDAOimplements();
            ProductoDAO daoProducto = new ProductoDAOimplements();
            ClienteDAO daoCliente = new ClienteDAOimplements();
            List<Producto> lista_producto = null;
            List<Cliente> lista_cliente = null;
            Venta v = new Venta();
            Cliente c = new Cliente();
            Producto p = new Producto();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    //llenar lista productos 
                    lista_producto = daoProducto.select();
                    //llenamos la lista de clientes
                    lista_cliente = daoCliente.select();
                    request.setAttribute("action", "nuevo");
                    request.setAttribute("lista_pro", lista_producto);
                    request.setAttribute("lista_cli", lista_cliente);
                    request.setAttribute("vent", v);
                    request.getRequestDispatcher("frmventas.jsp").forward(request, response);
                    break;
                case "edit":
                    //llenar lista productos 
                    lista_producto = daoProducto.select();
                    //llenamos la lista de clientes
                    lista_cliente = daoCliente.select();
                    id = Integer.parseInt(request.getParameter("id"));
                    v = dao.getById(id);
                    request.setAttribute("action", "modificar");
                    request.setAttribute("lista_pro", lista_producto);
                    request.setAttribute("lista_cli", lista_cliente);
                    request.setAttribute("vent", v);
                    request.getRequestDispatcher("frmventas.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("VentaControlador");
                    break;
                case "view":
                    //obtener la lista de registros de clientes
                    List<Venta> lista = dao.select();
                    request.setAttribute("ven", lista);
                    request.getRequestDispatcher("ventas.jsp").forward(request, response);
                    break;
            }         
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }      
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        int cliente_id = Integer.parseInt(request.getParameter("cliente_id"));
        int producto_id = Integer.parseInt(request.getParameter("producto_id"));
        String fecha = request.getParameter("fecha");
        
        VentaDAO dao = new VentaDAOimplements();
        Venta ven = new Venta();;
        ven.setId(id);
        ven.setProducto_id(producto_id);
        ven.setCliente_id(cliente_id);
        ven.setFecha(convertFecha(fecha));
        if (id == 0) {
            try {
                dao.insert(ven);
            } catch (Exception ex) {
                Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                //modificar registro
                dao.update(ven);
            } catch (Exception ex) {
                Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
             response.sendRedirect("VentaControlador");       
    }
    public Date convertFecha(String fecha) {
        Date fechaBD = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaTMP;
        try {
            fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(VentaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return fechaBD;       
    }}
