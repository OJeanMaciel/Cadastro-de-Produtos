/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Painel;

/**
 *
 * @author Jean
 */
public class PainelDAO {
     public List<Painel> readForMunicipio(String municipio){
         Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Painel> paineis = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM painel WHERE municipio LIKE ? ");
            stmt.setString(1, "%"+municipio+"%");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                
                Painel painel = new Painel();
                
                
                painel.setRegiao(rs.getString("regiao"));
                painel.setEstado(rs.getString("estado"));
                painel.setMunicipio(rs.getString("municipio"));
                painel.setData(rs.getString("data"));
                painel.setCasosAcumulado(rs.getInt("casosacumulado"));
                painel.setObitosAcumulado(rs.getInt("obitosacumulado"));                
                painel.setRecuperadosnovos(rs.getInt("recuperadosnovos"));
                paineis.add(painel);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PainelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        return paineis;
    }
}
