package com.jc.dao;
import com.jc.modelo.Venta;
import com.jc.utiles.Conexion;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class VentaDAOimplements extends Conexion implements VentaDAO {
    @Override
    public void insert(Venta venta) throws Exception {
        this.conectar();
        String sql = "INSERT INTO ventas(producto_id,cliente_id,fecha) VALUES(?,?,?)";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, venta.getProducto_id());
        ps.setInt(2, venta.getCliente_id());
        ps.setDate(3, venta.getFecha());
        ps.executeUpdate();
        ps.close();
        this.desconn();
    }
    @Override
    public void update(Venta venta) throws Exception {
        this.conectar();
        String sql = "UPDATE ventas SET producto_id=?,cliente_id=?,fecha=? WHERE id=?";
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.setInt(1, venta.getProducto_id());
        ps.setInt(2, venta.getCliente_id());
        ps.setDate(3, venta.getFecha());
        ps.setInt(4, venta.getId());
        ps.executeUpdate();
        ps.close();
        this.desconn();
    }
    @Override
    public void delete(int id) throws Exception {
        this.conectar();
        String sql = "DELETE FROM ventas  WHERE id=" + id;
        PreparedStatement ps = this.conn.prepareStatement(sql);
        ps.executeUpdate();
        ps.close();
        this.desconn();
    }
    @Override
    public Venta getById(int id) throws Exception {
        this.conectar();
        String sql = "SELECT * FROM ventas WHERE id=" + id;
        PreparedStatement ps = this.conn.prepareStatement(sql);
        Venta v = new Venta();
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            v.setId(rs.getInt("id"));
            v.setProducto_id(rs.getInt("producto_id"));
            v.setCliente_id(rs.getInt("cliente_id"));
            v.setFecha(rs.getDate("fecha"));
        }
        ps.close();
        rs.close();
        this.desconn();
        return v;
    }

    @Override
    public List<Venta> select() throws Exception {
        List<Venta> list = new ArrayList<Venta>();
        this.conectar();
        String sql = "SELECT ve.*, p.nombre  AS producto, c.nombre AS cliente FROM ventas ve "
                + "LEFT JOIN productos p ON ve.producto_id=p.id "
                + "LEFT JOIN clientes c ON ve.cliente_id=c.id";
        PreparedStatement ps = this.conn.prepareStatement(sql);
    ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Venta v = new Venta();
            v.setId(rs.getInt("id"));
            v.setProducto_id(rs.getInt("producto_id"));
            v.setCliente_id(rs.getInt("cliente_id"));
            v.setFecha(rs.getDate("fecha"));
            v.setCliente(rs.getString("cliente"));
            v.setProducto(rs.getString("producto"));

            list.add(v);
        }

        this.desconn();
        return list;
    }

}
