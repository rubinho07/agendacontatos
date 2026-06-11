package dao;
import model.Contato;
import util.Conexao;
import java.sql.*;
import java.util.*;

public class ContatoDAO implements IContatoDAO {

    // Trata campos vazios como NULL para evitar erro de data invalida
    private String nullIfEmpty(String s) {
        return (s == null || s.trim().isEmpty()) ? null : s.trim();
    }

    @Override
    public void inserir(Contato c) {
        String sql = "INSERT INTO contato (nome,telefone,celular,email,endereco,dataNascimento,empresa,cargo,observacoes,tipoContato,cpf,grupo_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,c.getNome()); stmt.setString(2,nullIfEmpty(c.getTelefone())); stmt.setString(3,nullIfEmpty(c.getCelular()));
            stmt.setString(4,nullIfEmpty(c.getEmail())); stmt.setString(5,nullIfEmpty(c.getEndereco()));
            stmt.setString(6,nullIfEmpty(c.getDataNascimento()));
            stmt.setString(7,nullIfEmpty(c.getEmpresa())); stmt.setString(8,nullIfEmpty(c.getCargo()));
            stmt.setString(9,nullIfEmpty(c.getObservacoes())); stmt.setString(10,nullIfEmpty(c.getTipoContato()));
            stmt.setString(11,nullIfEmpty(c.getCpf()));
            if(c.getGrupoId()>0) stmt.setInt(12,c.getGrupoId()); else stmt.setNull(12,Types.INTEGER);
            stmt.executeUpdate();
        } catch(Exception e){throw new RuntimeException("Erro ao inserir: "+e.getMessage(),e);}
    }

    @Override
    public void alterar(Contato c) {
        String sql = "UPDATE contato SET nome=?,telefone=?,celular=?,email=?,endereco=?,dataNascimento=?,empresa=?,cargo=?,observacoes=?,tipoContato=?,cpf=?,grupo_id=? WHERE id=?";
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1,c.getNome()); stmt.setString(2,nullIfEmpty(c.getTelefone())); stmt.setString(3,nullIfEmpty(c.getCelular()));
            stmt.setString(4,nullIfEmpty(c.getEmail())); stmt.setString(5,nullIfEmpty(c.getEndereco()));
            stmt.setString(6,nullIfEmpty(c.getDataNascimento()));
            stmt.setString(7,nullIfEmpty(c.getEmpresa())); stmt.setString(8,nullIfEmpty(c.getCargo()));
            stmt.setString(9,nullIfEmpty(c.getObservacoes())); stmt.setString(10,nullIfEmpty(c.getTipoContato()));
            stmt.setString(11,nullIfEmpty(c.getCpf()));
            if(c.getGrupoId()>0) stmt.setInt(12,c.getGrupoId()); else stmt.setNull(12,Types.INTEGER);
            stmt.setInt(13,c.getId());
            stmt.executeUpdate();
        } catch(Exception e){throw new RuntimeException("Erro ao alterar: "+e.getMessage(),e);}
    }

    @Override
    public void excluir(int id) {
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement("DELETE FROM contato WHERE id=?")) {
            stmt.setInt(1,id); stmt.executeUpdate();
        } catch(Exception e){throw new RuntimeException("Erro ao excluir: "+e.getMessage(),e);}
    }

    @Override
    public void sairDoGrupo(int contatoId) {
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement("UPDATE contato SET grupo_id=NULL WHERE id=?")) {
            stmt.setInt(1,contatoId); stmt.executeUpdate();
        } catch(Exception e){throw new RuntimeException("Erro ao sair do grupo: "+e.getMessage(),e);}
    }

    @Override
    public Contato buscarPorId(int id) {
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement("SELECT c.*, g.nome as nomeGrupo FROM contato c LEFT JOIN grupo g ON c.grupo_id=g.id WHERE c.id=?")) {
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) return mapear(rs);
        } catch(Exception e){throw new RuntimeException("Erro ao buscar: "+e.getMessage(),e);}
        return null;
    }

    @Override
    public List<Contato> listar() { return listarTodos(); }

    @Override
    public List<Contato> listarTodos() {
        List<Contato> lista = new ArrayList<>();
        String sql = "SELECT c.*, g.nome as nomeGrupo FROM contato c LEFT JOIN grupo g ON c.grupo_id=g.id ORDER BY c.nome";
        try (Connection con = Conexao.getConexao(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while(rs.next()) lista.add(mapear(rs));
        } catch(Exception e){throw new RuntimeException("Erro ao listar: "+e.getMessage(),e);}
        return lista;
    }

    private Contato mapear(ResultSet rs) throws SQLException {
        Contato c = new Contato.Builder()
            .id(rs.getInt("id")).nome(rs.getString("nome")).telefone(rs.getString("telefone"))
            .celular(rs.getString("celular")).email(rs.getString("email")).endereco(rs.getString("endereco"))
            .dataNascimento(rs.getString("dataNascimento")).empresa(rs.getString("empresa"))
            .cargo(rs.getString("cargo")).observacoes(rs.getString("observacoes"))
            .tipoContato(rs.getString("tipoContato")).cpf(rs.getString("cpf"))
            .grupoId(rs.getInt("grupo_id")).build();
        try { c.setNomeGrupo(rs.getString("nomeGrupo")); } catch(Exception ignored){}
        return c;
    }
}
