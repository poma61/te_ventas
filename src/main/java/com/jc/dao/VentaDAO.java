
package com.jc.dao;
import java.util.List;
import com.jc.modelo.Venta;

public interface VentaDAO {
  
public void insert(Venta venta)throws Exception;
public void update(Venta venta) throws Exception;
public void delete (int id) throws Exception;    
 public Venta getById(int id) throws Exception;
public List<Venta> select() throws Exception; 
    
    
    
}
