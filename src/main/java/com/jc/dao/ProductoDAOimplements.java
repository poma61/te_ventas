package com.jc.dao;

import com.jc.modelo.Producto;
import com.jc.utiles.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOimplements extends Conexion implements ProductoDAO {

    @Override
    public void insert(Producto producto) throws Exception {
        this.conectar();
        String sql = "INSERT INTO productos(nombre,descripcion,precio) VALUES (?,?,?)";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, producto.getNombre());
        ps.setString(2, producto.getDescripcion());
        ps.setFloat(3, producto.getPrecio());
        ps.executeUpdate();

        this.desconn();

    }

    @Override
    public void update(Producto producto) throws Exception {
        this.conectar();
        String sql = "UPDATE productos SET  nombre=?,descripcion=?,precio=? WHERE id=?";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, producto.getNombre());
        ps.setString(2, producto.getDescripcion());
        ps.setFloat(3, producto.getPrecio());
        ps.setInt(4, producto.getId());
        ps.executeUpdate();

        this.desconn();
    }

    @Override
    public void delete(int id) throws Exception {
        this.conectar();
        String sql = "DELETE FROM productos WHERE id=" + id;
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.executeUpdate();

        this.desconn();
    }
    @Override
    public Producto getById(int id) throws Exception {
        this.conectar();
        String sql = "SELECT * FROM productos WHERE id=" + id;
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Producto p = new Producto();
        while (rs.next()) {
            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setPrecio(rs.getFloat("precio"));
        }
   this.desconn();
        return p;
    }
    @Override
    public List<Producto> select() throws Exception {
        this.conectar();
        List<Producto> list = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Producto p = new Producto();
            p.setId(rs.getInt("id"));
            p.setNombre(rs.getString("nombre"));
            p.setDescripcion(rs.getString("descripcion"));
            p.setPrecio(rs.getFloat("precio"));
            list.add(p);
        }
        this.desconn();
        return list;
    }}
