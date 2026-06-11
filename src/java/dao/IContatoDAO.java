package dao;
import model.Contato;
import java.util.List;

public interface IContatoDAO {
    void inserir(Contato c);
    void alterar(Contato c);
    void excluir(int id);
    void sairDoGrupo(int contatoId);
    Contato buscarPorId(int id);
    List<Contato> listar();
    List<Contato> listarTodos();
}
