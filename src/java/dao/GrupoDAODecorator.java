package dao;
import model.Grupo;
import java.util.List;

/**
 * Padrao Decorator base para IGrupoDAO
 */
public class GrupoDAODecorator implements IGrupoDAO {
    protected IGrupoDAO dao;
    public GrupoDAODecorator(IGrupoDAO dao) { this.dao = dao; }

    @Override public void inserir(Grupo g) { dao.inserir(g); }
    @Override public void alterar(Grupo g) { dao.alterar(g); }
    @Override public void excluir(int id) { dao.excluir(id); }
    @Override public Grupo buscarPorId(int id) { return dao.buscarPorId(id); }
    @Override public List<Grupo> listar() { return dao.listar(); }
    @Override public List<Grupo> listarTodos() { return dao.listarTodos(); }
}
