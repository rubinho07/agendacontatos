package dao;
import model.Usuario;
import util.Conexao;
import java.sql.*;

public class UsuarioDAO {
    public Usuario autenticar(String email, String senha) {
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE email=? AND senha=?")) {
            stmt.setString(1,email); stmt.setString(2,senha);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt("id")); u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email")); u.setSenha(rs.getString("senha"));
                u.setPerfil(rs.getString("perfil")); return u;
            }
        } catch(Exception e){throw new RuntimeException("Erro ao autenticar: "+e.getMessage(),e);}
        return null;
    }
    public int contarUsuarios() {
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM usuario"); ResultSet rs = stmt.executeQuery()) {
            if(rs.next()) return rs.getInt(1);
        } catch(Exception e){throw new RuntimeException(e.getMessage(),e);}
        return 0;
    }
}
