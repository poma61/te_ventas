package com.jc.dao;

import com.jc.modelo.Cliente;
import com.jc.utiles.Conexion;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOimplements extends Conexion implements ClienteDAO {

    @Override
    public void insert(Cliente cliente) throws Exception {
        this.conectar();
        String sql = "INSERT INTO clientes(nombre,correo,celular) VALUES(?,?,?)";
        PreparedStatement ps = this.conn.prepareStatement(sql);

        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getCorreo());
        ps.setString(3, String.valueOf(cliente.getCelular()));
        ps.executeUpdate();

        this.desconn();
    }

    @Override
    public void update(Cliente cliente) throws Exception {
        this.conectar();
        String sql = "UPDATE clientes SET nombre=?,correo=?,celular=? WHERE id=?";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getCorreo());
        ps.setString(3, cliente.getCelular());
        ps.setInt(4, cliente.getId());
        ps.executeUpdate();
        this.desconn();
    }
    @Override
    public void delete(int id) throws Exception {
        this.conectar();
        String sql = "DELETE FROM clientes WHERE id=" + id;
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.executeUpdate();
        this.desconn();
    }
    @Override
    public Cliente getById(int id) throws Exception {
        this.conectar();
        Cliente cl = new Cliente();
        String sql = "SELECT * FROM clientes WHERE  id=" + id;
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            cl.setId(rs.getInt("id"));
            cl.setNombre(rs.getString("nombre"));
            cl.setCorreo(rs.getString("correo"));
            cl.setCelular(rs.getString("celular"));
        }
        this.desconn();
        return cl;
    }
    @Override
    public List<Cliente> select() throws Exception {
        List<Cliente> lista = new ArrayList<>();
        this.conectar();
        String sql = "SELECT * FROM clientes";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Cliente cl = new Cliente();
            cl.setId(rs.getInt("id"));
            cl.setNombre(rs.getString("nombre"));
            cl.setCorreo(rs.getString("correo"));
            cl.setCelular(rs.getString("celular"));
            lista.add(cl);
        }
        this.desconn();
        return lista;
    }}
