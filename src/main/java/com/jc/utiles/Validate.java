
package com.jc.utiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Validate extends Conexion{
 
    Connection con= this.conectar();
    PreparedStatement  ps;
    
    public boolean checkUser(String email, String password){
       boolean b=false;
        try {
            
            String sql="SELECT * FROM usuarios WHERE email=? and password=sha1(?)";
           ps=con.prepareStatement(sql);
             ps.setString(1, email);
             ps.setString(2, password);
             
             ResultSet rs=ps.executeQuery();
             b=rs.next();
           
        } catch (SQLException ex) {
            Logger.getLogger(Validate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
    
 
    
}
