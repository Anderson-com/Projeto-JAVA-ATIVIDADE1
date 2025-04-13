package conexao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class conectaDAO {
    
    Connection conn;
    
    public Connection connectDB(){
                
        try {        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11", "root","anderson");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
}
