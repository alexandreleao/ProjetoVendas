/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.ItemVendas;
import br.com.projeto.model.Produtos;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author erdle
 */
public class ItemVendasDAO {
      private Connection con;

    public ItemVendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    //Metodo que cadastra itens
    public void cadastraItem(ItemVendas obj){
         try {
            //2 Passo - conectar ao banco de dados e organizar o comando sql
            String sql = "insert into tb_itensvendas(venda_id,produto_id,qtd,subtotal)value(?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1,obj.getVenda().getId());
            stmt.setInt(2,obj.getProduto().getId());
            stmt.setInt(3,obj.getQtd());
            stmt.setDouble(4,obj.getSubtotal());
            
            
            stmt.execute();
            stmt.close();
            
           
            
        } catch (SQLException erro) {
           JOptionPane.showInternalMessageDialog(null,"Erro:" + erro);
        
        }
    }
    //Metodo que lista Itens de uma venda por id
     
    public List<ItemVendas>listarItensPorVendas(int venda_id){
        
        try {
            // 1 Passo -  Criar a lista
            List<ItemVendas> lista = new ArrayList();
            
            //2 Passo - Criar sql organzar e executar
            String sql = "select p.descricao,i.qtd,p.preco,i.subtotal from tb_itensvendas as i "
                    +" inner join tb_produtos as p on(i.produto_id = p.id) where i.venda_id = ?";
                          
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1,venda_id);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ItemVendas item = new ItemVendas();
                Produtos prod = new Produtos();
                
               
                prod.setDescricao(rs.getString("p.descricao"));
                item.setQtd(rs.getInt("i.qtd"));
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));
               
                item.setProduto(prod);
                
                lista.add(item);
              }
                return lista;
            
        } catch (SQLException erro) {
        
            JOptionPane.showMessageDialog(null,"Erro:" + erro);
              
            return null;
        }
      
    }
    
    
    
    
}
