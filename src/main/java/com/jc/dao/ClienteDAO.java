
package com.jc.dao;

import com.jc.modelo.Cliente;
import java.util.List;


public interface ClienteDAO {
    
 public void  insert(Cliente cliente) throws Exception;
  public void update(Cliente cliente) throws Exception;   
  public  void delete(int id) throws Exception;
public Cliente getById(int id) throws Exception;
public List<Cliente> select() throws Exception;
    
}
