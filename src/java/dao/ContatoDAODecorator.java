package dao;
import model.Contato;
import java.util.List;

/**
 * Padrao Decorator base para IContatoDAO
 */
public class ContatoDAODecorator implements IContatoDAO {
    protected IContatoDAO dao;
    public ContatoDAODecorator(IContatoDAO dao) { this.dao = dao; }

    @Override public void inserir(Contato c) { dao.inserir(c); }
    @Override public void alterar(Contato c) { dao.alterar(c); }
    @Override public void excluir(int id) { dao.excluir(id); }
    @Override public void sairDoGrupo(int contatoId) { dao.sairDoGrupo(contatoId); }
    @Override public Contato buscarPorId(int id) { return dao.buscarPorId(id); }
    @Override public List<Contato> listar() { return dao.listar(); }
    @Override public List<Contato> listarTodos() { return dao.listarTodos(); }
}
