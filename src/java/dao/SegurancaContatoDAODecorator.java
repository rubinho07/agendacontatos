package dao;
import model.Contato;
import model.Usuario;
import java.util.List;

/**
 * Decorator: seguranca e permissoes por perfil
 * ADMIN  - pode tudo
 * SUB    - pode inserir e alterar, nao pode excluir
 * USER   - apenas visualizar e inserir
 */
public class SegurancaContatoDAODecorator extends ContatoDAODecorator {
    private final Usuario usuario;

    public SegurancaContatoDAODecorator(IContatoDAO dao, Usuario usuario) {
        super(dao); this.usuario = usuario;
    }

    private void verificar(String acao) {
        if (usuario == null) throw new SecurityException("Usuario nao esta logado.");

        String perfil = usuario.getPerfil();

        switch (acao) {
            case "EXCLUIR":
                if (!"ADMIN".equals(perfil))
                    throw new SecurityException("Apenas ADMIN pode excluir contatos.");
                break;
            case "ALTERAR":
                if ("USER".equals(perfil))
                    throw new SecurityException("Perfil USER nao pode alterar contatos. Solicite acesso SUB ou ADMIN.");
                break;
            case "INSERIR":
                // ADMIN, SUB e USER podem inserir
                break;
        }
    }

    @Override public void inserir(Contato c) { verificar("INSERIR"); super.inserir(c); }
    @Override public void alterar(Contato c) { verificar("ALTERAR"); super.alterar(c); }
    @Override public void excluir(int id) { verificar("EXCLUIR"); super.excluir(id); }
    @Override public List<Contato> listarTodos() { return super.listarTodos(); }
}
