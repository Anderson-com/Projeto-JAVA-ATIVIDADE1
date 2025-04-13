package metodos;

import conexao.conectaDAO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    private conectaDAO conecta;
    private Connection conn;    
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ProdutosDAO (){
        this.conecta = new conectaDAO();
        this.conn = this.conecta.connectDB();        
    }
    
    public void cadastrarProduto (ProdutosDTO produto){  
              
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";
       try{
           PreparedStatement prep = this.conn.prepareStatement(sql);
           prep.setString(1, produto.getNome());
           prep.setInt(2, produto.getValor());
           prep.setString(3, produto.getStatus());
           prep.execute();
           JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!"); 
       
        }  catch (Exception e){
           JOptionPane.showMessageDialog(null,"Erro ao cadastrar o produto: "+ e.getMessage());         
       }
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }     
}

