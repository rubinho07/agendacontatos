package dao;
import model.Grupo;
import model.Usuario;
import java.util.List;

/**
 * Decorator concreto: seguranca para operacoes de grupo
 */
public class SegurancaGrupoDAODecorator extends GrupoDAODecorator {
    private final Usuario usuario;

    public SegurancaGrupoDAODecorator(IGrupoDAO dao, Usuario usuario) {
        super(dao); this.usuario = usuario;
    }

    private void verificar(String acao) {
        if (usuario == null) throw new SecurityException("Usuario nao logado.");
        if ("EXCLUIR".equals(acao) && !"ADMIN".equals(usuario.getPerfil()))
            throw new SecurityException("Apenas ADMIN pode excluir grupos.");
    }

    @Override public void inserir(Grupo g) { verificar("INSERIR"); super.inserir(g); }
    @Override public void excluir(int id) { verificar("EXCLUIR"); super.excluir(id); }
    @Override public List<Grupo> listar() { return super.listar(); }
    @Override public List<Grupo> listarTodos() { return super.listarTodos(); }
}
