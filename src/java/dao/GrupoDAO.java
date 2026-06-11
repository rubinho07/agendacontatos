package dao;
import model.Grupo;
import util.Conexao;
import java.sql.*;
import java.util.*;

public class GrupoDAO implements IGrupoDAO {

    @Override
    public void inserir(Grupo grupo) {
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement("INSERT INTO grupo (nome) VALUES (?)")) {
            stmt.setString(1,grupo.getNome()); stmt.executeUpdate();
        } catch(Exception e){throw new RuntimeException("Erro ao inserir grupo: "+e.getMessage(),e);}
    }

    @Override
    public void alterar(Grupo grupo) {
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement("UPDATE grupo SET nome=? WHERE id=?")) {
            stmt.setString(1,grupo.getNome()); stmt.setInt(2,grupo.getId()); stmt.executeUpdate();
        } catch(Exception e){throw new RuntimeException("Erro ao alterar grupo: "+e.getMessage(),e);}
    }

    @Override
    public void excluir(int id) {
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement("DELETE FROM grupo WHERE id=?")) {
            stmt.setInt(1,id); stmt.executeUpdate();
        } catch(Exception e){throw new RuntimeException("Erro ao excluir grupo: "+e.getMessage(),e);}
    }

    @Override
    public Grupo buscarPorId(int id) {
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement("SELECT * FROM grupo WHERE id=?")) {
            stmt.setInt(1,id); ResultSet rs = stmt.executeQuery();
            if(rs.next()){Grupo g=new Grupo(); g.setId(rs.getInt("id")); g.setNome(rs.getString("nome")); return g;}
        } catch(Exception e){throw new RuntimeException("Erro ao buscar grupo: "+e.getMessage(),e);}
        return null;
    }

    @Override
    public List<Grupo> listar() { return listarTodos(); }

    @Override
    public List<Grupo> listarTodos() {
        List<Grupo> lista = new ArrayList<>();
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement("SELECT * FROM grupo ORDER BY nome"); ResultSet rs = stmt.executeQuery()) {
            while(rs.next()){Grupo g=new Grupo(); g.setId(rs.getInt("id")); g.setNome(rs.getString("nome")); lista.add(g);}
        } catch(Exception e){throw new RuntimeException("Erro ao listar grupos: "+e.getMessage(),e);}
        return lista;
    }
}
