package metodos;

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProdutosDAO {

    private Connection conn;

    public ProdutosDAO() {
        this.conn = conectar();
    }

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?useSSL=false&serverTimezone=UTC",
                    "root",
                    "anderson");
            System.out.println("Conexão realizada com sucesso");
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Falha na conexão com o banco " + ex.getMessage());
            return null;
        }
    }

    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)";
        try {
            PreparedStatement prep = this.conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto: " + e.getMessage());
        }

    }

    public List<ProdutosDTO> listarProdutos() {
        List<ProdutosDTO> listaProdutos = new ArrayList<>();

        if (conn == null) {
            JOptionPane.showMessageDialog(null,
                    "Não há conexão com o banco de dados",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return listaProdutos;
        }

        String sql = "SELECT * FROM produtos";
        try (PreparedStatement st = this.conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listaProdutos.add(produto);  // Corrigido: adiciona à lista local
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao listar produtos: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return listaProdutos;
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {

        }
    }
}
