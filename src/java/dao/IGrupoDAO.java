package dao;
import model.Grupo;
import java.util.List;

public interface IGrupoDAO {
    void inserir(Grupo grupo);
    void alterar(Grupo grupo);
    void excluir(int id);
    Grupo buscarPorId(int id);
    List<Grupo> listar();
    List<Grupo> listarTodos();
}
